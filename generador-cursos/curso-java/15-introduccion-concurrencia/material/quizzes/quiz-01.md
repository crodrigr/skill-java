# ❓ Quiz 01 — Introducción a la Concurrencia en Java (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué es un hilo (`Thread`) y qué distingue a la ejecución concurrente
de la secuencial?

- **A.** Un hilo es un proceso completo con su propia memoria, sin relación con el programa que lo creó.
- **B.** Un hilo es una línea de ejecución independiente dentro del mismo programa; la ejecución
  concurrente permite que varios hilos avancen al mismo tiempo, sin garantizar en qué orden terminan.
- **C.** Un hilo solo existe si el programa usa `Runnable`; con `Thread` no hace falta ningún hilo.
- **D.** La ejecución concurrente siempre termina en el mismo orden que la secuencial.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un hilo es una línea de ejecución independiente dentro del mismo programa,
con su propia pila pero compartiendo memoria con los demás hilos; el orden de finalización entre hilos
sin sincronizar no está garantizado.

</details>

**2. [Selección]** **Pregunta:** ¿cuáles son las dos formas estándar de crear un hilo en Java?

- **A.** Extender `Thread` sobrescribiendo `run()`, o implementar `Runnable` y pasarlo al constructor de
  `Thread`.
- **B.** Extender `Runnable` e implementar `Thread`.
- **C.** Solo se puede crear un hilo extendiendo `Thread`.
- **D.** Declarando un método `static void run()` en cualquier clase.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Extender `Thread` (sobrescribiendo `run()`) e implementar `Runnable` (pasado
al constructor de `Thread`) son las dos formas estándar; `Runnable` puede ser una clase, una clase
anónima o una expresión lambda.

</details>

**3. [Selección múltiple]** Dado un fragmento donde tres tareas independientes entre sí se ejecutan una
después de otra desde el mismo hilo, ¿cuáles afirmaciones son verdaderas?

- A. El tiempo total es, aproximadamente, la suma de los tres tiempos individuales.
- B. Ejecutar esas mismas tres tareas en hilos separados reduciría el tiempo total.
- C. El orden en que se completan las tres tareas depende del sistema operativo, no del código.
- D. Es una candidata razonable para paralelizar con hilos, porque las tareas no dependen entre sí.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa en este caso: al ser secuencial (un solo hilo), el orden
de finalización es siempre el mismo, determinado por el orden de las llamadas en el código.

</details>

**4. [Abierta]** Explica, con tus palabras, cómo implementarías la creación de tres hilos para una tarea
dada, usando una vez `extends Thread` y una vez `Runnable` con expresión lambda.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase que extienda `Thread` y sobrescriba `run()` con el código de la tarea, y se crea una
instancia por cada caso que use esa forma. Para los casos con `Runnable` y lambda, se crea directamente
`new Thread(() -> { /* código de la tarea */ })`, sin declarar ninguna clase nueva. En ambos casos, se
invoca `start()` sobre cada instancia — nunca `run()` directamente — para que el trabajo corra en un
hilo nuevo en vez de ejecutarse de forma síncrona en el hilo que lo crea.

</details>

**5. [Selección]** **Pregunta:** ¿qué hace exactamente `hilo.interrupt()`?

- **A.** Detiene el hilo de inmediato, sin que el hilo pueda hacer nada al respecto.
- **B.** Marca un indicador cooperativo que el hilo debe revisar (`isInterrupted()`), o lanza
  `InterruptedException` si el hilo está bloqueado en una espera; el hilo sigue corriendo si no
  reacciona.
- **C.** Lanza una excepción que termina el programa completo.
- **D.** Solo funciona si el hilo fue creado con `Runnable`, no con `extends Thread`.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `interrupt()` es cooperativo: no detiene el hilo por la fuerza, solo pide la
interrupción de una forma que el propio hilo debe revisar o que se manifiesta como
`InterruptedException` en una espera bloqueante.

</details>

**6. [Selección múltiple]** Dado un hilo cuyo `run()` hace un bucle largo sin revisar en ningún punto
`isInterrupted()` ni bloquearse en `sleep()`/`wait()`/`join()`, ¿cuáles afirmaciones son verdaderas si se
invoca `interrupt()` sobre él?

- A. El hilo sigue corriendo hasta terminar su trabajo por su cuenta.
- B. El pedido de interrupción no tiene ningún efecto visible.
- C. El hilo lanza `InterruptedException` de inmediato, aunque no esté bloqueado en ninguna espera.
- D. Esta es una violación frecuente de la interrupción cooperativa.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: `InterruptedException` solo se lanza desde una llamada
bloqueante como `sleep()`, `wait()` o `join()`, nunca de forma espontánea en medio de un bucle.

</details>

**7. [Abierta]** Explica, con tus palabras, cómo implementarías la interrupción cooperativa de un hilo
que procesa un lote de elementos en un bucle.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Al inicio de cada iteración del bucle, se revisa `Thread.currentThread().isInterrupted()`; si es
`true`, se sale del método de inmediato, sin procesar el resto del lote. Si el procesamiento incluye una
espera bloqueante (por ejemplo, `Thread.sleep()` para simular trabajo), se captura
`InterruptedException` en un `try`/`catch` y, dentro del `catch`, también se sale del método (y
opcionalmente se vuelve a marcar el indicador con `Thread.currentThread().interrupt()`, por si algún
código externo necesita revisarlo más tarde).

</details>

**8. [Selección]** **Pregunta:** ¿qué hace `hiloSecundario.join()`, invocado desde el hilo principal?

- **A.** Detiene el hilo secundario de inmediato.
- **B.** Hace que el hilo principal espere a que el hilo secundario termine, antes de continuar su
  propia ejecución.
- **C.** Crea un hilo nuevo que combina el trabajo de ambos.
- **D.** Interrumpe el hilo secundario.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `join()` bloquea al hilo que lo invoca hasta que el hilo sobre el que se
invoca termina.

</details>

**9. [Selección múltiple]** Dado un hilo principal que lee el resultado de un hilo secundario
inmediatamente después de `start()`, sin `join()` de por medio, ¿cuáles afirmaciones son verdaderas?

- A. Es una condición de carrera: el resultado leído depende de en qué punto de su trabajo está el hilo
  secundario en ese momento.
- B. El programa lanza necesariamente una excepción.
- C. Agregar `join()` antes de la lectura elimina esa condición de carrera.
- D. El resultado observado podría variar entre ejecuciones, incluso sin cambiar el código.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: leer un resultado antes de tiempo no lanza ninguna
excepción, solo da un valor incompleto o desactualizado.

</details>

**10. [Abierta]** Explica, con tus palabras, cómo usarías `join()` para asegurarte de que el resultado de
un hilo esté completo antes de leerlo.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

Después de invocar `start()` sobre el hilo, y antes de leer cualquier dato que ese hilo produzca, se
invoca `hilo.join()` desde el hilo que necesita el resultado. Esa llamada bloquea hasta que el hilo
termina; recién después de que `join()` retorna hay garantía de que el resultado está completo y puede
leerse con seguridad.

</details>

**11. [Abierta, integrador]** Diseña, con tus palabras (sin código), una solución que combine los tres
temas técnicos de este módulo: cree uno o más hilos, los detenga cooperativamente cuando corresponda, y
sincronice su finalización con `join()` antes de usar su resultado.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

Por ejemplo: un sistema que renueva en paralelo los accesos digitales de varios usuarios (un hilo por
renovación, `extends Thread` o `Runnable`), con un límite de tiempo máximo total; si ese límite se supera,
el hilo principal interrumpe cooperativamente las renovaciones que sigan en curso (cada una revisando
`isInterrupted()` entre pasos); y, en cualquier caso —se hayan interrumpido o no—, el hilo principal
invoca `join()` sobre cada hilo de renovación antes de leer cuántas se completaron realmente, para no
reportar un número incompleto.

</details>
