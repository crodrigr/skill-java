# 📝 Prueba práctica — Nueva funcionalidad sobre MediSalud

**Curso de Java · Proyecto final** · **Tiempo: 3 horas** · **Puntaje: 100 puntos**

## 🎯 Objetivo

El estudiante ya construyó el proyecto MediSalud siguiendo la [guía del proyecto final](../guia/guia-proyecto-final.md).
En esta prueba se evalúa que **comprende el código que armó**, no solo que lo transcribió:
debe agregar una funcionalidad nueva que atraviesa **todas las capas** (entidad, servicio,
persistencia JDBC, controlador y vista) y que exige reutilizar los patrones y conceptos
del curso (State, Observer, excepciones propias, JDBC, Stream API).

## 📋 Condiciones

- Se parte **del proyecto propio** terminado (el de la guía). Si no compila al empezar, debe
  dejarse compilando antes de agregar nada.
- Se puede consultar la guía, los apuntes y la documentación oficial de Java. **No** se permite
  copiar código de otro estudiante ni usar asistentes de IA.
- Sin librerías nuevas: solo Java del temario y el conector MySQL ya utilizado.
- MySQL debe estar corriendo con Docker Compose (`docker compose up -d`).
- Se entrega **un repositorio en GitHub** con el proyecto y el archivo `ENTREGA.md` descrito al final.

## 🧩 La funcionalidad: Cancelación de citas con motivo

Hoy `ServicioCitas.cancelarCita(...)` existe, pero **el menú no permite cancelar** y la
cancelación no guarda por qué se canceló. MediSalud necesita:

1. Una opción de menú para **cancelar una cita indicando el motivo**.
2. Que el **motivo quede guardado en MySQL** y se recupere al leer la cita.
3. Un **reporte** de cancelaciones agrupadas por médico.

### Reglas de negocio

| # | Regla |
|---|-------|
| RN1 | El motivo es obligatorio: no puede ser `null` ni vacío/solo espacios, y tiene máximo **200 caracteres**. Se valida con `IllegalArgumentException` (igual que el resto de validaciones de las entidades). |
| RN2 | Solo se pueden cancelar citas `PENDIENTE` o `CONFIRMADA`. Cancelar una `ATENDIDA` o ya `CANCELADA` se rechaza con la `TransicionInvalidaException` existente (no se duplica la lógica del State). |
| RN3 | **No se puede cancelar una cita cuya fecha ya pasó** (fecha anterior a hoy). Se rechaza con una **nueva excepción propia** `CancelacionTardiaException`, con mensaje claro que incluya el código y la fecha de la cita. |
| RN4 | Si la cita **no existe**, se informa con la `CitaNoEncontradaException` existente. Ninguna regla detiene el programa: la vista muestra el mensaje y vuelve al menú. |
| RN5 | Al cancelar, el hilo de notificaciones debe seguir imprimiendo el aviso de cambio de estado (el Observer ya existente debe seguir funcionando; **no** se reescribe). |
| RN6 | Las citas que ya estaban canceladas antes de esta funcionalidad (por ejemplo `C004`, `C010`, `C016`) **no tienen motivo**: al mostrarlas deben decir `Sin motivo registrado`, sin lanzar `NullPointerException`. |

### Cambios de interfaz

- Menú principal: nueva opción **7. Cancelar cita**; **Salir** pasa a ser la opción **8**.
- Flujo de la opción 7: se pide el código de cita y el motivo, se cancela y se muestra la cita con su estado y motivo.
- Reporte (opción 6): la tabla «Citas del médico» gana una columna **Motivo** (vacía si la cita no está cancelada; `Sin motivo registrado` si lo está pero no tiene motivo). Además, se agrega una cuarta sección **«Cancelaciones por médico»** con el código del
  médico y la cantidad de citas canceladas, ordenado de mayor a menor cantidad. Debe calcularse con
  **Stream API** (`filter` + `Collectors.groupingBy` + `counting`), sin bucles manuales de conteo.

## ⏱️ Plan sugerido (3 horas)

| Tiempo | Actividad |
|--------|-----------|
| 0:00 – 0:20 | Verificar que el proyecto compila y corre. Leer la prueba completa y ubicar los archivos a tocar. |
| 0:20 – 0:50 | Base de datos: script de migración y ajuste de `CitaDAO`. |
| 0:50 – 1:30 | Entidad, nueva excepción y servicio (RN1–RN4). |
| 1:30 – 2:00 | Controlador y vista (opción 7, renumerar Salir). |
| 2:00 – 2:25 | Reporte con Stream API. |
| 2:25 – 2:50 | Pruebas manuales de la tabla de casos y corrección de errores. |
| 2:50 – 3:00 | Completar `ENTREGA.md`, hacer `push` y enviar la URL. |

## 🛠️ Requisitos técnicos

### R1 — Base de datos
- Crear `sql/002-motivo-cancelacion.sql` que agregue la columna `motivo_cancelacion VARCHAR(200) NULL`
  a la tabla `citas` (la columna **debe permitir NULL**: ver RN6).
- Aplicarlo sobre la base ya creada **sin borrarla ni perder datos** (el script de la guía solo usa
  `CREATE TABLE IF NOT EXISTS`, así que no agregará la columna por sí solo).
- Actualizar también `sql/medisalud.sql` para que una instalación nueva ya incluya la columna.

### R2 — Persistencia (`CitaDAO`)
- `guardar` debe persistir el motivo, tanto en el `INSERT` como en el `UPDATE` de `ON DUPLICATE KEY`
  (hoy solo actualiza `estado`: si no se ajusta, el motivo se pierde en silencio).
- `mapearCita` debe reconstruir la cita **con** su motivo. Atención: hoy reconstruye el estado con
  `cambiarEstado(...)`, que dispara notificaciones; no debe imprimirse una notificación por cada
  cita leída al listar.
- Usar siempre `PreparedStatement` con parámetros (nada de concatenar SQL).

### R3 — Dominio y servicio
- `Cita` guarda el motivo y lo expone con un getter. Se agrega un método de dominio para cancelar
  con motivo que valide RN1.
- `ServicioCitas` ofrece un método nuevo `cancelarCita(String codigo, String motivo)` que aplique
  RN1–RN4. El método `cancelarCita(String codigo)` anterior debe **eliminarse o dejar de usarse**:
  la decisión se justifica en `ENTREGA.md`.
- `CancelacionTardiaException` sigue el estilo de las excepciones existentes (paquete `exception`,
  mensaje claro, sin silenciar la causa).

### R4 — Controlador, vista y reporte
- El controlador solo delega al servicio (sin lógica de negocio). La vista solo pide datos y muestra
  resultados, y es la única clase que usa `Scanner`.
- El reporte de cancelaciones por médico vive en el servicio (no en la vista) y la vista lo imprime.

### R5 — Calidad
- Se respeta el estilo del proyecto: nombres en español, una responsabilidad por clase, mismas
  convenciones de excepciones y `try-with-resources`.
- No debe quedar código comentado ni `System.out.println` de depuración.
- El proyecto debe compilar con el comando de la guía:
  `javac -d out $(find src/main/java -name "*.java")`.

## ✅ Casos de prueba que se ejecutarán al calificar

Las fechas de los datos de ejemplo se vuelven pasadas con el tiempo (y eso activaría RN3), así que
**los casos usan citas creadas al momento** con la opción 3 (el sistema permite agendar con
cualquier fecha). Preparación, con paciente `P001` y médico `M009`:

| Cita | Fecha | Estado inicial |
|------|-------|----------------|
| `T01` | mañana | `PENDIENTE` |
| `T02` | mañana | `CONFIRMADA` |
| `T03` | mañana | `ATENDIDA` (con diagnóstico) |
| `T04` | mañana | `PENDIENTE` (se cancela en el caso 1 y se reutiliza en el 4) |
| `T05` | mañana | `PENDIENTE` (casos 7 y 8) |
| `TPASADA` | ayer | `PENDIENTE` |

| # | Entrada (menú → opción 7) | Resultado esperado |
|---|---------------------------|--------------------|
| 1 | `T01`, motivo `Paciente viaja` | Queda `CANCELADA` con motivo; se imprime la notificación en segundo plano. |
| 2 | `T02`, motivo `Medico en congreso` | `CANCELADA` con motivo. |
| 3 | `T03`, motivo `Error` | Mensaje de transición inválida; la cita **no cambia**. |
| 4 | `T01` otra vez, motivo `Otro` | Mensaje de transición inválida; el motivo **no** se sobrescribe. |
| 5 | `TPASADA`, motivo `Tarde` | Mensaje de `CancelacionTardiaException` con código y fecha; sin cambios. |
| 6 | `ZZZ`, motivo `X` | Mensaje de cita no encontrada; el menú sigue funcionando. |
| 7 | `T05`, motivo vacío o solo espacios | Mensaje de motivo obligatorio; la cita **sigue** `PENDIENTE`. |
| 8 | `T05`, motivo de 201 caracteres | Mensaje de longitud máxima; la cita **sigue** `PENDIENTE`. |
| 9 | Cerrar el programa (opción 8), volver a abrirlo y ver en el reporte (opción 6) las citas del médico `M009` | `T01` y `T02` siguen `CANCELADA` con sus motivos (persistió en MySQL). |
| 10 | En ese mismo reporte, con las citas de `M010` (incluye `C004`, cancelada antes de la funcionalidad) | `C004` muestra `Sin motivo registrado`, sin excepción. |
| 11 | Opción 6 → sección de cancelaciones | Conteo por médico, de mayor a menor, consistente con `SELECT medico_codigo, COUNT(*) FROM citas WHERE estado='CANCELADA' GROUP BY medico_codigo`. |
| 12 | Opción 8 | El hilo de notificaciones se detiene de forma ordenada y el programa termina. |

## 📦 Entrega

Se entrega **la URL de un repositorio en GitHub**.

1. Se crea un repositorio (nombre sugerido: `medisalud-prueba-<apellido>`). Puede ser público, o
   privado con acceso para el docente (usuario indicado por el docente).
2. El repositorio contiene el proyecto completo (`src/`, `sql/`, `docker-compose.yml`, `lib/`) y
   un `.gitignore` que excluya `out/` y archivos del IDE. No se suben contraseñas reales.
3. Se trabaja con **commits pequeños y con mensajes claros** durante la prueba (por ejemplo, uno por
   requisito R1–R4). Se revisa el historial: un único commit gigante o commits posteriores al
   tiempo límite restan puntos.
4. Los cambios de la prueba van en la rama `main`, partiendo de un primer commit con el proyecto
   original sin modificar (para que se vea con claridad qué se agregó).
5. Se agrega en la raíz un `README.md` con cómo compilar y ejecutar, y un `ENTREGA.md` con:
   1. **Lista de archivos** creados y modificados (una línea de por qué cada uno).
   2. **Decisión sobre `cancelarCita(String)`**: qué se hizo con el método viejo y por qué.
   3. **Dónde se aplicó cada concepto**, con archivo y método: State, Observer, excepción propia,
      `PreparedStatement`, Stream API.
   4. **Resultado de los 12 casos de prueba**: ✅/❌ y, si alguno falla, qué se considera que falta.
   5. **Un riesgo o mejora** que se vería en esta funcionalidad (2–3 líneas).
6. Al terminar, se envía la URL del repositorio antes de la hora límite. El docente clona el
   repositorio en limpio y lo compila con el comando de la guía; si no compila, aplica el tope
   indicado en la rúbrica.

## 📊 Rúbrica (100 puntos)

| Criterio | Puntos |
|----------|-------:|
| Base de datos: migración sin pérdida de datos, columna nullable, script base actualizado (R1) | 10 |
| `CitaDAO`: persiste y recupera el motivo, `PreparedStatement`, sin notificaciones espurias al leer (R2) | 15 |
| Dominio y servicio: RN1–RN4 correctas, reutilizando State y excepciones existentes (R3) | 25 |
| Nueva excepción `CancelacionTardiaException` bien diseñada | 5 |
| Controlador y vista: opción 7, Salir = 8, separación de responsabilidades, errores sin detener el programa (R4) | 15 |
| Reporte con Stream API (`groupingBy` + `counting`, orden descendente) | 10 |
| Casos de prueba 1–12 ejecutados por el docente (su fallo se refleja aquí y no se descuenta de nuevo en los demás criterios) | 10 |
| Calidad y estilo (R5), compila con el comando de la guía | 5 |
| Repositorio: historial de commits claro, `README.md`, `.gitignore` y `ENTREGA.md` coherente con el código | 5 |
| **Total** | **100** |

> Descuentos: proyecto que **no compila** → máximo 40 puntos. Lógica de negocio en la vista o SQL
> concatenado → −10 por cada caso.

## 💡 Pistas (sin dar la solución)

- Conviene releer en la guía el Bloque 6 (`ServicioCitas`, State) y el Bloque 11 (`CitaDAO`).
- Si el motivo "desaparece" al reabrir el programa, se debe revisar el `ON DUPLICATE KEY UPDATE`.
- Si llegan notificaciones al listar citas, se debe revisar cómo `mapearCita` reconstruye el estado.
- Para NULL en JDBC: `resultado.getString(...)` devuelve `null` cuando la columna es NULL.
- Para comparar fechas: `LocalDate.now()` y `isBefore(...)`.
