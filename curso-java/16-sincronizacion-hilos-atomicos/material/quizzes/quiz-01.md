# ❓ Quiz 01 — Sincronización de Hilos y Procesos Atómicos (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué es una condición de carrera, y qué hace `synchronized` para
resolverla?

- **A.** Es un error de compilación que Java detecta automáticamente; `synchronized` lo corrige en
  tiempo de compilación.
- **B.** Es el resultado incorrecto que ocurre cuando varios hilos leen y escriben el mismo dato mutable
  sin coordinarse; `synchronized` resuelve esto con exclusión mutua, permitiendo que solo un hilo a la
  vez ejecute el código protegido.
- **C.** Es una excepción que Java lanza cuando dos hilos acceden al mismo objeto.
- **D.** Es un problema exclusivo de las bases de datos, no de los programas Java.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Una condición de carrera es un resultado incorrecto (silencioso, sin
excepción) por acceso concurrente sin coordinar; `synchronized` la resuelve con exclusión mutua.

</details>

**2. [Selección múltiple]** Dado un método que incrementa un campo `int` compartido sin ninguna
sincronización, invocado por varios hilos a la vez, ¿cuáles afirmaciones son verdaderas?

- A. El resultado final puede ser menor al esperado.
- B. El programa lanza una excepción cuando se pierde un incremento.
- C. La cantidad exacta de incrementos perdidos puede variar entre ejecuciones.
- D. Es una condición de carrera real, no un evento raro o poco probable con suficientes hilos e
  iteraciones.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: perder un incremento no lanza ninguna excepción, lo que
hace este tipo de error especialmente difícil de detectar.

</details>

**3. [Abierta]** Explica, con tus palabras, cómo protegerías un contador compartido incrementado por
varios hilos, usando `synchronized`.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara el método que incrementa el contador (y el que lo lee) como `synchronized`, sobre la misma
instancia compartida por todos los hilos. Eso garantiza que solo un hilo a la vez pueda ejecutar ese
código: mientras uno lo ejecuta, los demás esperan su turno, eliminando la posibilidad de que dos hilos
se intercalen entre los pasos de leer, sumar y escribir.

</details>

**4. [Selección]** **Pregunta:** ¿qué ofrecen las clases de `java.util.concurrent.atomic`?

- **A.** Operaciones que se completan como una única unidad indivisible, sin necesitar un bloqueo
  explícito.
- **B.** Un reemplazo universal de `synchronized` para cualquier caso.
- **C.** Colecciones que se pueden recorrer de forma concurrente sin errores.
- **D.** Un mecanismo para crear hilos sin usar `Thread` ni `Runnable`.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Las clases atómicas (`AtomicInteger`, `AtomicLong`, `AtomicBoolean`,
`AtomicReference`) ofrecen operaciones indivisibles sin bloqueo explícito.

</details>

**5. [Selección múltiple]** ¿Cuáles de los siguientes casos siguen necesitando `synchronized`, aunque
una clase atómica esté disponible?

- A. Incrementar un contador compartido por varios hilos.
- B. Verificar el saldo de una cuenta y, solo si alcanza, descontar un monto, como una sola unidad.
- C. Actualizar dos campos relacionados de forma consistente entre sí.
- D. Leer el valor actual de un contador compartido.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B y C.** Ambas necesitan ejecutar más de una operación relacionada como una sola
unidad, algo que una clase atómica (que protege una única variable con una única operación) no puede
garantizar por sí sola.

</details>

**6. [Abierta]** Explica, con tus palabras, cómo reemplazarías `synchronized` por una clase atómica en
un contador compartido simple.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

Se reemplaza el campo `int` compartido por un `AtomicInteger` (inicializado en el valor de partida), y
cada incremento pasa de `total++` a `total.incrementAndGet()`. No hace falta declarar ningún método
`synchronized`: la propia clase atómica garantiza que la operación se complete como una única unidad
indivisible, sin que otro hilo pueda intercalarse en medio.

</details>

**7. [Selección múltiple]** Dado un diseño donde dos hilos sincronizan sobre los mismos dos recursos
compartidos (A y B), ¿cuáles afirmaciones son verdaderas?

- A. Si ambos hilos los adquieren siempre en el mismo orden, no puede haber interbloqueo entre ellos.
- B. Si un hilo adquiere A y luego B, mientras el otro adquiere B y luego A, puede ocurrir un
  interbloqueo real.
- C. Un interbloqueo se resuelve solo esperando más tiempo.
- D. Verificar un interbloqueo real requiere un límite de tiempo, porque el programa no termina por sí
  solo.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: un interbloqueo real (ambos hilos ya sosteniendo su
primer recurso) es un bloqueo permanente, no una cuestión de esperar más tiempo.

</details>

**8. [Abierta, integrador]** Diseña, con tus palabras (sin código), una solución nueva que combine al
menos una herramienta de hilos del Módulo 15 (creación, interrupción o unión) con al menos una
herramienta de protección de este módulo (sincronización o acceso atómico), sin introducir ningún riesgo
de interbloqueo.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

Por ejemplo: un sistema que renueva en paralelo los accesos digitales de varios usuarios (creación de
hilos, uno por renovación), contando cuántas se completan en un `AtomicInteger` compartido (acceso
atómico, sin necesitar `synchronized` porque es una única variable con una única operación); si se
supera un tiempo máximo, el hilo principal interrumpe cooperativamente las renovaciones en curso
(interrupción); y, en cualquier caso, invoca `join()` sobre cada hilo antes de leer cuántas se
completaron realmente (unión). Como el diseño no sincroniza sobre más de un recurso compartido a la vez,
no hay ningún riesgo de interbloqueo que evitar.

</details>
