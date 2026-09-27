# 📚 Explicación conceptual — Módulo 15

## 🧠 Concepto: Concurrencia en Java

Un programa **secuencial** ejecuta sus instrucciones una detrás de otra, en un único hilo de control. La
**concurrencia** permite que varias tareas avancen al mismo tiempo, cada una en su propio **hilo**
(`Thread`):

- Un hilo es una línea de ejecución independiente dentro del mismo programa, con su propia pila de
  llamadas, pero compartiendo la memoria del proceso con los demás hilos.
- Un programa puede tener un solo hilo (el hilo principal, `main`) o crear hilos adicionales.
- El orden en que terminan varios hilos que corren sin sincronizarse entre sí **no está garantizado**:
  puede variar de una ejecución a otra, incluso sin cambiar el código.
- La concurrencia es útil cuando varias tareas son independientes entre sí (el resultado final no
  depende del orden en que terminan): ejecutarlas en paralelo reduce el tiempo total, en vez de sumar el
  tiempo de cada una.

📎 Ver en la práctica: [Ejemplo 01 — Qué es la concurrencia en Java](01-que-es-la-concurrencia.md)

## 🧠 Concepto: Creación de hilos

Java ofrece dos formas estándar de crear un hilo:

- **Extender `Thread`** y sobrescribir su método `run()` con el código que ese hilo debe ejecutar.
- **Implementar `Runnable`** (con una clase, una clase anónima o una expresión lambda) y pasar esa
  implementación al constructor de `Thread`. Es la forma preferida cuando la clase ya necesita extender
  otra cosa, porque Java no permite herencia múltiple de clases.

En ambos casos, el hilo nuevo solo empieza a ejecutarse cuando se invoca `start()` — nunca invocando
`run()` directamente, que ejecutaría el código de forma síncrona en el hilo actual, sin crear ningún hilo
nuevo.

📎 Ver en la práctica: [Ejemplo 02 — Creación de hilos](02-creacion-de-hilos.md)

## 🧠 Concepto: Interrupción de hilos

La interrupción de hilos en Java es **cooperativa**, no forzada:

- `hilo.interrupt()` marca un indicador interno de ese hilo, o lanza `InterruptedException` si el hilo
  está bloqueado en una espera como `Thread.sleep()`, `wait()` o `join()`.
- El hilo interrumpido sigue corriendo hasta que su propio código decide terminar: revisando
  `Thread.currentThread().isInterrupted()` en algún punto (por ejemplo, en cada iteración de un bucle
  largo), o capturando `InterruptedException` y decidiendo salir.
- Un hilo que nunca revisa su estado de interrupción ni se bloquea en ninguna espera simplemente ignora
  cualquier pedido de interrupción.
- Java no ofrece ninguna forma segura de forzar la detención inmediata de un hilo desde afuera; los
  métodos antiguos que lo intentaban (`Thread.stop()`, `Thread.suspend()`) están obsoletos.

📎 Ver en la práctica: [Ejemplo 03 — Interrupción de hilos](03-interrupcion-de-hilos.md)

## 🧠 Concepto: Unir hilos

`join()` sincroniza la finalización de un hilo con el hilo que lo invoca:

- `hiloSecundario.join()`, invocado desde el hilo principal, hace que el hilo principal espere a que
  `hiloSecundario` termine antes de continuar su propia ejecución.
- Sin `join()`, leer o usar el resultado producido por un hilo desde otro hilo es una **condición de
  carrera**: no hay ninguna garantía de en qué punto de su trabajo está el otro hilo en ese momento.
- Invocar `join()` sobre un hilo que ya terminó es seguro: retorna de inmediato.
- `join()` puede combinarse con interrupción: si el hilo que espera es interrumpido mientras está
  bloqueado en `join()`, recibe `InterruptedException`.

📎 Ver en la práctica: [Ejemplo 04 — Unir hilos](04-union-de-hilos.md)

## 📋 Resumen de los tres temas técnicos

| Tema técnico | Qué resuelve | Método de `Thread` |
|---|---|---|
| Creación de hilos | Ejecutar una tarea en un hilo propio, en paralelo con el resto del programa | `extends Thread` + `start()`, o `new Thread(Runnable)` + `start()` |
| Interrupción de hilos | Pedirle a un hilo en ejecución que se detenga antes de tiempo, de forma cooperativa | `interrupt()`, revisado con `isInterrupted()` o manifestado como `InterruptedException` |
| Unir hilos | Esperar a que un hilo termine antes de usar su resultado, evitando una condición de carrera | `join()` |
