# 📝 Prueba práctica B — Reprogramación de citas en MediSalud

**Curso de Java · Proyecto final** · **Tiempo: 3 horas** · **Puntaje: 100 puntos**

## 🎯 Objetivo

El estudiante ya construyó el proyecto MediSalud siguiendo la [guía del proyecto final](../guia/guia-proyecto-final.md).
En esta prueba demuestra que **entiende el código que armó**, no solo que lo transcribió: agrega
una funcionalidad nueva que atraviesa **todas las capas** (entidad, servicio, persistencia JDBC,
controlador y vista) y que lo obliga a reutilizar los patrones y conceptos del curso (State,
Observer, Facade, excepciones propias, JDBC, Stream API).

## 📋 Condiciones

- La prueba se resuelve **sobre el proyecto propio del estudiante**, terminado según la guía. Si
  no compila al empezar, el estudiante debe dejarlo compilando antes de agregar nada.
- Se permite consultar la guía, los apuntes personales y la documentación oficial de Java. **No**
  se permite copiar código de otro estudiante ni usar asistentes de IA.
- Sin librerías nuevas: solo Java del temario y el conector MySQL que ya usa el proyecto.
- MySQL debe estar corriendo con Docker Compose (`docker compose up -d`).
- Se entrega **un repositorio en GitHub** con el proyecto y el archivo `ENTREGA.md` descrito al final.

## 🧩 La funcionalidad: Reprogramación de citas

Hoy una cita, una vez agendada, **no puede cambiar de fecha**: `Cita.fecha` es `final` y
`CitaDAO` ni siquiera actualiza la fecha al guardar. Muchos pacientes piden mover su cita a otro
día. MediSalud necesita:

1. Una opción de menú para **reprogramar una cita a una nueva fecha**.
2. Que la **nueva fecha, la fecha original y la cantidad de reprogramaciones** queden guardadas en
   MySQL y se recuperen al leer la cita.
3. Un **reporte** de reprogramaciones por médico.

### Reglas de negocio

| # | Regla |
|---|-------|
| RN1 | La nueva fecha debe ser **posterior a hoy** (mañana o después) y **distinta de la fecha actual** de la cita. Se valida con `IllegalArgumentException`, igual que el resto de validaciones de las entidades. Una fecha mal escrita (por ejemplo `2026-13-45`) se informa como formato inválido. |
| RN2 | Solo se pueden reprogramar citas `PENDIENTE` o `CONFIRMADA`. Reprogramar una `ATENDIDA` o `CANCELADA` se rechaza con la `TransicionInvalidaException` existente. |
| RN3 | Una cita reprogramada **queda `PENDIENTE`**: si estaba `CONFIRMADA`, vuelve a `PENDIENTE` (el paciente debe confirmar la nueva fecha). Esta transición `CONFIRMADA → PENDIENTE` **hoy no existe** en `EstadoCita`: el estudiante debe incorporarla al patrón State sin romper confirmar, atender ni cancelar. |
| RN4 | Una cita se puede reprogramar **como máximo 2 veces**, y el médico **no puede tener otra cita no cancelada en la nueva fecha**. Los dos casos se rechazan con una **nueva excepción propia** `ReprogramacionNoPermitidaException`, con un mensaje claro que diga el motivo (límite alcanzado, con la cantidad; o agenda ocupada, con la fecha y el código de la otra cita). |
| RN5 | Cuando una cita `CONFIRMADA` vuelve a `PENDIENTE`, el hilo de notificaciones debe imprimir el aviso de cambio de estado, **sin reescribir el Observer**. Ojo: las citas leídas de MySQL **no traen su observador** conectado (solo lo recibe la cita recién agendada en `FachadaAgendamiento`). |
| RN6 | Si la cita **no existe**, se informa con la `CitaNoEncontradaException` existente. Ninguna regla detiene el programa: la vista muestra el mensaje y vuelve al menú. |
| RN7 | Las citas que existían antes de esta funcionalidad (las de los datos de ejemplo, de `C001` a `C030`) **nunca fueron reprogramadas**: deben mostrarse con `0` reprogramaciones y `Sin reprogramar` como fecha original, sin lanzar `NullPointerException`. |

### Cambios de interfaz

- Menú principal: nueva opción **7. Reprogramar cita**; **Salir** pasa a ser la opción **8**.
- Flujo de la opción 7: pide el código de la cita y la nueva fecha (`aaaa-mm-dd`), reprograma y
  muestra la cita con su nueva fecha, su estado, su fecha original y la cantidad de
  reprogramaciones.
- Reporte (opción 6): la tabla «Citas del médico» gana dos columnas, **Original** (la fecha original,
  o `Sin reprogramar`) y **Reprog.** (la cantidad de reprogramaciones). Además, agregar una cuarta
  sección **«Reprogramaciones por médico»** con el código del médico y el **total de
  reprogramaciones** de sus citas, solo para los médicos con al menos una, ordenado de mayor a menor.
  Debe calcularse con **Stream API** (`filter` + `Collectors.groupingBy` + `summingInt`), sin bucles
  manuales de suma.

## ⏱️ Plan sugerido (3 horas)

| Tiempo | Actividad |
|--------|-----------|
| 0:00 – 0:20 | Verificar que el proyecto compila y corre. Leer esta prueba completa y ubicar los archivos a tocar. |
| 0:20 – 0:50 | Base de datos: script de migración y ajuste de `CitaDAO`. |
| 0:50 – 1:40 | Entidad, `EstadoCita`, nueva excepción y servicio (RN1–RN4, RN6). |
| 1:40 – 2:05 | Facade, controlador y vista (opción 7, renumerar Salir, RN5). |
| 2:05 – 2:25 | Reporte con Stream API. |
| 2:25 – 2:50 | Pruebas manuales de la tabla de casos y corrección de errores. |
| 2:50 – 3:00 | Completar `ENTREGA.md`, hacer `push` y enviar la URL. |

## 🛠️ Requisitos técnicos

### R1 — Base de datos

- Crear `sql/002-reprogramacion.sql` que agregue a la tabla `citas` dos columnas:
  - `veces_reprogramada INT NOT NULL DEFAULT 0`, para que las citas existentes queden con `0`;
  - `fecha_original DATE NULL`, que queda en `NULL` mientras la cita nunca se reprogramó (RN7).
- Aplicarlo sobre la base ya creada **sin borrarla ni perder datos** (el script de la guía solo usa
  `CREATE TABLE IF NOT EXISTS`, así que no agregará las columnas por sí solo).
- Actualizar también `sql/medisalud.sql` para que una instalación nueva ya incluya las columnas.

### R2 — Persistencia (`CitaDAO`)

- `guardar` debe persistir la **fecha**, `veces_reprogramada` y `fecha_original`, tanto en el
  `INSERT` como en el `UPDATE` de `ON DUPLICATE KEY` (hoy solo actualiza `estado`: si no se ajusta,
  la nueva fecha se pierde en silencio).
- `mapearCita` debe reconstruir la cita **con** su fecha original y su cantidad de
  reprogramaciones. Cuidado: no se puede reconstruir llamando al método de dominio que reprograma,
  porque ese método valida que la fecha sea futura y suma una reprogramación; leer una cita de la
  base no es reprogramarla.
- Usar siempre `PreparedStatement` con parámetros (nada de concatenar SQL); `fecha_original` se
  guarda como `NULL` cuando corresponde (`setNull` o `setDate` con `null`).

### R3 — Dominio, State y servicio

- `Cita` guarda la fecha original y la cantidad de reprogramaciones, y las expone con getters.
  `fecha` deja de ser `final`, pero **no** se agrega un `setFecha` público: se agrega un método de
  dominio para reprogramar que valide RN1, registre la fecha original **solo la primera vez** y sume
  una reprogramación.
- `EstadoCita` incorpora la transición de RN3. En `ENTREGA.md` se justifica cómo se hizo y por qué no
  habilita transiciones que antes estaban prohibidas en los otros flujos.
- `ServicioCitas` ofrece un método nuevo `reprogramarCita(String codigo, LocalDate nuevaFecha)` que
  aplique RN1–RN4 y RN6. La verificación de agenda ocupada usa **Stream API** (por ejemplo,
  `anyMatch`) sobre las citas del médico, sin contar las canceladas ni la propia cita.
- `ReprogramacionNoPermitidaException` sigue el estilo de las excepciones existentes (paquete
  `exception`, mensaje claro, sin silenciar la causa).

### R4 — Facade, controlador, vista y reporte

- La reprogramación pasa por `FachadaAgendamiento` (nuevo método), que conecta el observador a la
  cita antes de cambiar su estado: así se cumple RN5 sin modificar `ObservadorCitaNotificacion`.
- El controlador solo delega (sin lógica de negocio). La vista solo pide datos y muestra
  resultados, y es la única clase que usa `Scanner`.
- El reporte de reprogramaciones por médico vive en el servicio (no en la vista) y la vista lo
  imprime.

### R5 — Calidad

- Se respeta el estilo del proyecto: nombres en español, una responsabilidad por clase, mismas
  convenciones de excepciones y `try-with-resources`.
- No queda código comentado ni `System.out.println` de depuración.
- El proyecto debe compilar con el comando de la guía:
  `javac -d out $(find src/main/java -name "*.java")`.

## ✅ Casos de prueba que se ejecutarán al calificar

Las fechas de los datos de ejemplo se vuelven pasadas con el tiempo, así que **los casos usan citas
creadas al momento** con la opción 3 (el sistema permite agendar con cualquier fecha). En la tabla,
«día +N» significa la fecha de hoy más N días. Preparación, con paciente `P001` y médico `M009`:

| Cita | Fecha | Estado inicial |
|------|-------|----------------|
| `R01` | día +1 | `PENDIENTE` |
| `R02` | día +1 | `CONFIRMADA` |
| `R03` | día +1 | `ATENDIDA` (con diagnóstico) |
| `R05` | día +5 | `PENDIENTE` (ocupa la agenda de `M009` ese día) |

| # | Entrada (menú → opción 7) | Resultado esperado |
|---|---------------------------|--------------------|
| 1 | `R01` → día +3 | Queda en día +3, `PENDIENTE`, con 1 reprogramación y fecha original día +1. |
| 2 | `R02` → día +4 | Queda en día +4, **vuelve a `PENDIENTE`**, con 1 reprogramación; se imprime la notificación de cambio de estado en segundo plano. |
| 3 | `R03` → día +6, y después `C004` (cancelada en los datos de ejemplo) → día +6 | Mensaje de transición inválida en los dos; ninguna cambia. |
| 4 | `R01` → día +5 | Mensaje de `ReprogramacionNoPermitidaException` por agenda ocupada, que nombra a `R05`; `R01` sigue en día +3. |
| 5 | `R01` → la fecha de ayer, y después → la fecha de hoy | Mensaje de fecha que debe ser posterior a hoy en los dos; sin cambios. |
| 6 | `R01` → día +3 (su fecha actual) | Mensaje de fecha que debe ser distinta de la actual; sin cambios. |
| 7 | `R01` → día +6 y después `R01` → día +7 | La primera funciona (2 reprogramaciones, la fecha original **sigue siendo** día +1). La segunda se rechaza por límite alcanzado; `R01` sigue en día +6 con 2 reprogramaciones. |
| 8 | `ZZZ` → día +2 | Mensaje de cita no encontrada; el menú sigue funcionando. |
| 9 | `R05` → `2026-13-45` | Mensaje de formato de fecha inválido; el menú sigue funcionando y `R05` no cambia. |
| 10 | Cerrar el programa (opción 8), volver a abrirlo y ver en el reporte (opción 6) las citas del médico `M009` | `R01` en día +6 con original día +1 y 2 reprogramaciones; `R02` en día +4, `PENDIENTE`, con 1 (todo persistió en MySQL). `C007`, `C020` y `C023` muestran `Sin reprogramar` y `0`, sin excepción. |
| 11 | Opción 6 → sección de reprogramaciones | Total por médico, de mayor a menor, consistente con `SELECT medico_codigo, SUM(veces_reprogramada) FROM citas WHERE veces_reprogramada > 0 GROUP BY medico_codigo ORDER BY 2 DESC`. |
| 12 | Opción 8 | El hilo de notificaciones se detiene de forma ordenada y el programa termina. |

## 📦 Entrega

El estudiante entrega **la URL de un repositorio en GitHub**.

1. Crea un repositorio (nombre sugerido: `medisalud-prueba-b-<apellido>`). Puede ser público, o
   privado con acceso para el docente (usuario indicado por el docente).
2. El repositorio contiene el proyecto completo (`src/`, `sql/`, `docker-compose.yml`, `lib/`) y
   un `.gitignore` que excluya `out/` y archivos del IDE. No se suben contraseñas reales.
3. Trabaja con **commits pequeños y con mensajes claros** durante la prueba (por ejemplo, uno por
   requisito R1–R4). Se revisa el historial: un único commit gigante o commits posteriores al
   tiempo límite restan puntos.
4. Los cambios de la prueba van en la rama `main`, partiendo de un primer commit con el proyecto
   original sin modificar (para que se vea con claridad qué se agregó).
5. Agrega en la raíz un `README.md` con cómo compilar y ejecutar, y un `ENTREGA.md` con:
   1. **Lista de archivos** creados y modificados (una línea de por qué cada uno).
   2. **Decisión sobre el State**: cómo se incorporó `CONFIRMADA → PENDIENTE` y por qué no habilita
      transiciones indebidas en confirmar, atender o cancelar.
   3. **Dónde se aplicó cada concepto**, con archivo y método: State, Observer, Facade, excepción
      propia, `PreparedStatement`, Stream API.
   4. **Resultado de los 12 casos de prueba**: ✅/❌ y, si alguno falló, qué falta según el estudiante.
   5. **Un riesgo o mejora** que el estudiante identifica en esta funcionalidad (2–3 líneas).
6. Al terminar, envía la URL del repositorio antes de la hora límite. El docente clona el
   repositorio en limpio y lo compila con el comando de la guía; si no compila, aplica el tope
   indicado en la rúbrica.

## 📊 Rúbrica (100 puntos)

| Criterio | Puntos |
|----------|-------:|
| Base de datos: migración sin pérdida de datos, columnas con el valor por defecto y la nulabilidad pedidas, script base actualizado (R1) | 10 |
| `CitaDAO`: persiste y recupera fecha, fecha original y reprogramaciones, `PreparedStatement`, `NULL` bien manejado, reconstrucción sin reprogramar (R2) | 15 |
| Dominio, State y servicio: RN1–RN4 y RN6 correctas, transición nueva sin romper las existentes, agenda ocupada con Stream API (R3) | 25 |
| Nueva excepción `ReprogramacionNoPermitidaException` bien diseñada | 5 |
| Facade, controlador y vista: opción 7, Salir = 8, notificación de RN5, separación de responsabilidades, errores sin detener el programa (R4) | 15 |
| Reporte con Stream API (`groupingBy` + `summingInt`, solo médicos con reprogramaciones, orden descendente) | 10 |
| Casos de prueba 1–12 ejecutados por el docente (su fallo se refleja aquí y no se descuenta de nuevo en los demás criterios) | 10 |
| Calidad y estilo (R5), compila con el comando de la guía | 5 |
| Repositorio: historial de commits claro, `README.md`, `.gitignore` y `ENTREGA.md` coherente con el código | 5 |
| **Total** | **100** |

> Descuentos: proyecto que **no compila** → máximo 40 puntos. Lógica de negocio en la vista o SQL
> concatenado → −10 por cada caso.

## 💡 Pistas (sin dar la solución)

- Conviene releer en la guía el Bloque 1 (`EstadoCita`, State), el Bloque 6 (`ServicioCitas`), el Bloque 9
  (`FachadaAgendamiento`) y el Bloque 11 (`CitaDAO`).
- Si la nueva fecha "desaparece" al reabrir el programa, conviene revisar el `ON DUPLICATE KEY UPDATE`.
- Si la fecha original cambia en la segunda reprogramación, conviene revisar cuándo se guarda.
- Si no aparece la notificación del caso 2, conviene preguntarse qué objeto `Cita` recibe el
  observador y qué objeto se lee de MySQL al reprogramar.
- Para `NULL` en JDBC: `resultado.getDate(...)` devuelve `null` cuando la columna es `NULL`;
  no se le debe llamar `toLocalDate()` sin comprobarlo.
- Para comparar fechas: `LocalDate.now()`, `isAfter(...)` y `equals(...)`.
