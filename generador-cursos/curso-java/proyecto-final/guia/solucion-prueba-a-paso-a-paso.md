# 🧭 Solución paso a paso — Prueba A: Cancelación de citas con motivo

Material docente. Enunciado: [prueba-nueva-funcionalidad.md](prueba-nueva-funcionalidad.md).
Código final y ejecutable: [../solucion-prueba-a/](../solucion-prueba-a/).

Todas las rutas Java son relativas a `src/main/java/com/medisalud/`. Parte del proyecto de la
guía, ya compilando. **Orden recomendado:** base de datos → DAO → dominio → excepción → servicio →
Facade → controlador → vista → pruebas. Compila (`javac -d out $(find src/main/java -name "*.java")`)
al terminar cada paso.

| Paso | Capa | Archivo(s) | Cubre |
|-----:|------|-----------|-------|
| 1 | Base de datos | `sql/002-motivo-cancelacion.sql`, `sql/medisalud.sql` | R1, RN6 |
| 2 | Dominio | `entity/Cita.java` | RN1, R3 |
| 3 | Persistencia | `persistencia/jdbc/CitaDAO.java` | R2 |
| 4 | Excepción | `exception/CancelacionTardiaException.java` | RN3 |
| 5 | Servicio | `service/ServicioCitas.java` | RN1–RN4, reporte |
| 6 | Facade | `service/FachadaAgendamiento.java` | RN5 |
| 7 | Controlador | `controlador/ControladorMediSalud.java` | R4 |
| 8 | Vista | `vista/VistaConsola.java` | menú, reporte, RN6 |
| 9 | Verificación | casos 1–12 | — |

---

## Paso 1 — Base de datos (R1)

**Objetivo:** agregar la columna sin borrar datos y sin obligar a que exista (RN6).

1. Crear `sql/002-motivo-cancelacion.sql`:

```sql
USE medisalud;

ALTER TABLE citas ADD COLUMN motivo_cancelacion VARCHAR(200) NULL;
```

2. Aplicarlo sobre la base ya creada:

```bash
docker compose exec -T mysql mysql -uroot -proot < sql/002-motivo-cancelacion.sql
```

3. En `sql/medisalud.sql`, dentro de `CREATE TABLE IF NOT EXISTS citas`, añadir la columna después de `estado`
   (una instalación nueva ya la trae):

```sql
    estado VARCHAR(20) NOT NULL,
    motivo_cancelacion VARCHAR(200) NULL,
```

> ⚠️ `NULL` es obligatorio: `C004`, `C010`, `C016`… ya existían canceladas y no tienen motivo.
> El script de la guía solo usa `CREATE TABLE IF NOT EXISTS`, por eso hace falta la migración aparte.

## Paso 2 — Dominio: `Cita` (RN1)

**Objetivo:** que la cita guarde el motivo, lo valide y pueda reconstruirse sin disparar notificaciones.

1. Constante y campo (el motivo **no** es `final`):

```java
private static final int MAXIMO_MOTIVO = 200;
...
private String motivoCancelacion;
```

2. Segundo constructor, para reconstruir una cita leída de MySQL. Asigna el estado directamente, sin pasar
   por `cambiarEstado` (así no se notifica al listar):

```java
public Cita(String codigo, Paciente paciente, Medico medico, LocalDate fecha,
            EstadoCita estado, String motivoCancelacion) {
    this(codigo, paciente, medico, fecha);
    this.estado = estado;
    this.motivoCancelacion = motivoCancelacion;
}
```

3. Método de dominio para cancelar. Valida **antes** de cambiar el estado: si el motivo es inválido, la cita
   sigue `PENDIENTE` (caso 7 y 8):

```java
public void cancelar(String motivo) {
    String motivoValidado = validarMotivo(motivo);
    cambiarEstado(EstadoCita.CANCELADA);
    this.motivoCancelacion = motivoValidado;
}

private static String validarMotivo(String motivo) {
    if (motivo == null || motivo.trim().isEmpty()) {
        throw new IllegalArgumentException("El motivo de cancelacion es obligatorio");
    }
    String motivoLimpio = motivo.trim();
    if (motivoLimpio.length() > MAXIMO_MOTIVO) {
        throw new IllegalArgumentException("El motivo de cancelacion no puede superar los "
                + MAXIMO_MOTIVO + " caracteres");
    }
    return motivoLimpio;
}
```

4. Getter (puede devolver `null`):

```java
public String getMotivoCancelacion() {
    return motivoCancelacion;
}
```

## Paso 3 — Persistencia: `CitaDAO` (R2)

**Objetivo:** guardar y leer el motivo; no perderlo al reabrir; no notificar al leer.

1. Constante con la lista de columnas, para no repetirla en los tres `SELECT`:

```java
private static final String COLUMNAS =
        "codigo, paciente_codigo, medico_codigo, fecha, estado, motivo_cancelacion";
```

2. `guardar`: el motivo va en el `INSERT` **y** en el `ON DUPLICATE KEY UPDATE` (si solo estuviera en el
   `INSERT`, al cancelar una cita existente el motivo se perdería en silencio). `setString` con `null`
   guarda `NULL`:

```java
String sql = "INSERT INTO citas (codigo, paciente_codigo, medico_codigo, fecha, estado, motivo_cancelacion) "
        + "VALUES (?, ?, ?, ?, ?, ?) "
        + "ON DUPLICATE KEY UPDATE estado = ?, motivo_cancelacion = ?";
...
sentencia.setString(5, cita.getEstado().name());
sentencia.setString(6, cita.getMotivoCancelacion());
sentencia.setString(7, cita.getEstado().name());
sentencia.setString(8, cita.getMotivoCancelacion());
```

3. Los tres `SELECT` (`buscarPorCodigo`, `listarTodas`, `listarPorMedico`) usan `"SELECT " + COLUMNAS + " FROM citas ..."`.
   Solo se concatena una constante; los valores siguen entrando por `?`.

4. `mapearCita`: usar el constructor de reconstrucción en lugar de `cambiarEstado(...)`:

```java
EstadoCita estado = EstadoCita.valueOf(resultado.getString("estado"));
String motivo = resultado.getString("motivo_cancelacion");   // null si la columna es NULL
return new Cita(codigo, paciente, medico, fecha, estado, motivo);
```

## Paso 4 — Nueva excepción (RN3)

Crear `exception/CancelacionTardiaException.java`, con el mismo estilo que `CitaNoEncontradaException`
(extiende `Exception`, mensaje claro con código y fecha):

```java
package com.medisalud.exception;

import java.time.LocalDate;

public class CancelacionTardiaException extends Exception {

    public CancelacionTardiaException(String codigoCita, LocalDate fechaCita) {
        super("La cita " + codigoCita + " era para el " + fechaCita
                + " y ya paso: no se puede cancelar");
    }
}
```

## Paso 5 — Servicio: `ServicioCitas` (RN1–RN4 y reporte)

**Objetivo:** reemplazar `cancelarCita(String)` por la versión con motivo y agregar el reporte con Stream API.

1. **Eliminar** `cancelarCita(String codigo)` (permitiría cancelar sin motivo; nada lo usa) y agregar dos métodos.
   El segundo recibe la `Cita` ya leída para que la Facade pueda conectarle el observador antes (paso 6):

```java
public Cita cancelarCita(String codigo, String motivo)
        throws CitaNoEncontradaException, TransicionInvalidaException, CancelacionTardiaException {
    return cancelarCita(buscarPorCodigo(codigo), motivo);               // RN4: lanza CitaNoEncontradaException
}

public Cita cancelarCita(Cita cita, String motivo)
        throws TransicionInvalidaException, CancelacionTardiaException {
    if (!cita.getEstado().puedeTransicionarA(EstadoCita.CANCELADA)) {    // RN2: reutiliza el State
        throw new TransicionInvalidaException(cita.getCodigo(), cita.getEstado(), EstadoCita.CANCELADA);
    }
    if (cita.getFecha().isBefore(LocalDate.now())) {                     // RN3
        throw new CancelacionTardiaException(cita.getCodigo(), cita.getFecha());
    }
    cita.cancelar(motivo);                                               // RN1 (IllegalArgumentException)
    repositorioCitas.guardar(cita);
    return cita;
}
```

   **Orden de validación:** existencia → transición → fecha → motivo. El estado solo cambia si todo es válido,
   y un segundo intento sobre `T01` ya cancelada falla en la transición sin sobrescribir el motivo (caso 4).

2. Reporte con Stream API (vive en el servicio, no en la vista):

```java
public List<Map.Entry<String, Long>> cancelacionesPorMedico() {
    Map<String, Long> conteo = repositorioCitas.listarTodas().stream()
            .filter(cita -> cita.getEstado() == EstadoCita.CANCELADA)
            .collect(Collectors.groupingBy(cita -> cita.getMedico().getCodigo(), Collectors.counting()));
    return conteo.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                    .thenComparing(Map.Entry.comparingByKey()))
            .collect(Collectors.toList());
}
```

3. Imports nuevos: `CancelacionTardiaException`, `java.util.Map`, `java.util.stream.Collectors`.

## Paso 6 — Facade: `FachadaAgendamiento` (RN5)

**Problema:** una cita leída de MySQL no trae observador (solo la cita recién agendada lo recibe). Si el
servicio cancela la que leyó, el hilo de notificaciones no imprime nada.

**Solución sin tocar el Observer:** la Facade lee la cita, le conecta el observador y recién entonces cancela.

```java
public Cita cancelar(String codigoCita, String motivo)
        throws CitaNoEncontradaException, TransicionInvalidaException, CancelacionTardiaException {
    Cita cita = servicioCitas.buscarPorCodigo(codigoCita);
    cita.agregarObservador(observadorCita);
    return servicioCitas.cancelarCita(cita, motivo);
}
```

Imports: `CancelacionTardiaException`, `CitaNoEncontradaException`, `TransicionInvalidaException`.

## Paso 7 — Controlador: `ControladorMediSalud` (R4)

Solo delega; sin lógica de negocio:

```java
public Cita cancelarCita(String codigoCita, String motivo)
        throws CitaNoEncontradaException, TransicionInvalidaException, CancelacionTardiaException {
    return fachadaAgendamiento.cancelar(codigoCita, motivo);
}

public List<Map.Entry<String, Long>> generarReporteCancelacionesPorMedico() {
    return servicioCitas.cancelacionesPorMedico();
}
```

## Paso 8 — Vista: `VistaConsola`

1. **Menú:** nueva opción 7 y Salir pasa a 8 (en el texto **y** en el `switch`):

```java
System.out.println("7. Cancelar cita");
System.out.println("8. Salir");
...
case "7":
    cancelarCita();
    break;
case "8":
    continuar = false;
    break;
```

2. **Flujo de la opción 7.** Captura todas las reglas para que el programa nunca se detenga (RN4).
   `IllegalArgumentException` cubre el motivo vacío o demasiado largo:

```java
private void cancelarCita() {
    try {
        System.out.print("Codigo de la cita: ");
        String codigoCita = lector.nextLine();
        System.out.print("Motivo de la cancelacion: ");
        String motivo = lector.nextLine();

        Cita cita = controlador.cancelarCita(codigoCita, motivo);
        System.out.println("Cita cancelada: " + cita + " | Motivo: " + textoMotivo(cita));
    } catch (CitaNoEncontradaException | TransicionInvalidaException | CancelacionTardiaException
            | IllegalArgumentException excepcion) {
        System.out.println("No se pudo cancelar la cita: " + excepcion.getMessage());
    }
}

private String textoMotivo(Cita cita) {                       // RN6: sin NullPointerException
    return cita.getMotivoCancelacion() == null ? "Sin motivo registrado" : cita.getMotivoCancelacion();
}
```

3. **Reporte (opción 6), tabla de citas:** columna *Motivo*, vacía si la cita no está cancelada:

```java
String formato = "  %-6s | %-24s | %-10s | %-10s | %s%n";
System.out.printf(formato, "Cita", "Paciente", "Fecha", "Estado", "Motivo");
System.out.println("  " + "-".repeat(7) + "+" + "-".repeat(26) + "+" + "-".repeat(12) + "+" + "-".repeat(12) + "+" + "-".repeat(24));
for (Cita cita : citas) {
    String motivo = cita.getEstado() == EstadoCita.CANCELADA ? textoMotivo(cita) : "";
    System.out.printf(formato, cita.getCodigo(), cita.getPaciente().getNombreCompleto(),
            cita.getFecha(), cita.getEstado(), motivo);
}
```

4. **Cuarta sección** al final de `generarReporte()`, antes del `System.out.println()` final. La vista solo imprime
   lo que calculó el servicio:

```java
List<Map.Entry<String, Long>> cancelaciones = controlador.generarReporteCancelacionesPorMedico();
imprimirTitulo("Cancelaciones por medico");
if (cancelaciones.isEmpty()) {
    System.out.println("  Sin citas canceladas.");
} else {
    String formato = "  %-10s | %12s%n";
    System.out.printf(formato, "Medico", "Canceladas");
    System.out.println("  " + "-".repeat(11) + "+" + "-".repeat(13));
    for (Map.Entry<String, Long> fila : cancelaciones) {
        System.out.printf(formato, fila.getKey(), fila.getValue());
    }
}
```

5. Imports nuevos: `EstadoCita`, `CancelacionTardiaException`.

## Paso 9 — Verificación

```bash
docker compose up -d
docker compose exec -T mysql mysql -uroot -proot < sql/002-motivo-cancelacion.sql   # solo si la base ya existía
javac -d out $(find src/main/java -name "*.java")
java -cp "out:lib/mysql-connector-j-8.3.0.jar" com.medisalud.Principal
```

Crear con la opción 3 las citas `T01`–`T05` (mañana) y `TPASADA` (ayer) con `P001`/`M009`; confirmar `T02`,
y confirmar + atender `T03`. Luego ejecutar la opción 7:

| # | Entrada | Mensaje / resultado esperado |
|---|---------|------------------------------|
| 1 | `T01`, `Paciente viaja` | `Cita cancelada: ... Motivo: Paciente viaja` + `[notificacion] La cita T01 ahora esta CANCELADA` |
| 2 | `T02`, `Medico en congreso` | cancelada con motivo |
| 3 | `T03`, `Error` | `La cita T03 no puede pasar de ATENDIDA a CANCELADA` |
| 4 | `T01`, `Otro` | `La cita T01 no puede pasar de CANCELADA a CANCELADA`; el motivo no cambia |
| 5 | `TPASADA`, `Tarde` | `La cita TPASADA era para el <ayer> y ya paso: no se puede cancelar` |
| 6 | `ZZZ`, `X` | `No existe una cita con el codigo ZZZ` |
| 7 | `T05`, `   ` | `El motivo de cancelacion es obligatorio`; sigue `PENDIENTE` |
| 8 | `T05`, 201 caracteres | `... no puede superar los 200 caracteres`; sigue `PENDIENTE` |
| 9 | opción 8, reabrir, opción 6 → `M009` | `T01` y `T02` siguen `CANCELADA` con motivo |
| 10 | opción 6 → `M010` | `C004` muestra `Sin motivo registrado` |
| 11 | sección «Cancelaciones por médico» | coincide con `SELECT medico_codigo, COUNT(*) FROM citas WHERE estado='CANCELADA' GROUP BY medico_codigo` |
| 12 | opción 8 | el hilo se detiene y el programa termina |

## ❌ Errores típicos

- **Motivo que desaparece al reabrir:** falta `motivo_cancelacion = ?` en el `ON DUPLICATE KEY UPDATE`.
- **Una notificación por cada cita al listar:** `mapearCita` sigue usando `cambiarEstado(...)`.
- **No aparece la notificación del caso 1:** se canceló una cita leída de MySQL sin conectarle el observador (paso 6).
- **`NullPointerException` en `C004`:** se llamó a `.length()`/`.trim()` sobre un motivo `null` al mostrarlo.
- **Estado `CANCELADA` con motivo inválido:** se cambió el estado antes de validar el motivo.
- **Salir sigue siendo `7`:** se cambió el texto del menú pero no el `case` del `switch` (o al revés).
