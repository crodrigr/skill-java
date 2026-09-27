# 📚 Explicación conceptual — Módulo 16

## 🧠 Concepto: Sincronización

Cuando varios hilos leen y escriben el mismo dato mutable sin coordinarse, puede ocurrir una
**condición de carrera**:

- Una operación aparentemente simple como `total++` en realidad son tres pasos (leer, sumar, escribir);
  dos hilos pueden intercalarse entre esos pasos y perder un incremento.
- `synchronized` (en un método o en un bloque `synchronized (objeto) { ... }`) resuelve esto con
  **exclusión mutua**: solo un hilo a la vez puede ejecutar el código protegido sobre el mismo objeto;
  los demás esperan su turno.
- Hay que sincronizar tanto el código que escribe el dato compartido como el que lo lee.

📎 Ver en la práctica: [Ejemplo 01 — Sincronización](01-sincronizacion.md)

## 🧠 Concepto: Acceso atómico

Las clases de `java.util.concurrent.atomic` (`AtomicInteger`, `AtomicLong`, `AtomicBoolean`,
`AtomicReference`) ofrecen operaciones atómicas sin necesitar un bloqueo explícito:

- Cada operación (`incrementAndGet()`, `get()`, `compareAndSet()`, ...) se completa como una única
  unidad indivisible: ningún otro hilo puede intercalarse en medio.
- Protegen **una única variable con una única operación**. Un caso que necesita ejecutar varias
  operaciones relacionadas como una sola unidad (por ejemplo, verificar-y-descontar un saldo) sigue
  necesitando `synchronized`.

📎 Ver en la práctica: [Ejemplo 02 — Acceso atómico](02-acceso-atomico.md)

## 🧠 Concepto: Ejemplos prácticos de concurrencia

Combinar hilos que comparten más de un recurso protegido introduce un riesgo nuevo: el **interbloqueo**
(deadlock).

- Ocurre cuando dos o más hilos adquieren los mismos recursos sincronizados en un orden inconsistente:
  cada uno termina esperando el recurso que otro hilo ya sostiene, y ninguno puede continuar jamás.
- Se evita por completo adquiriendo siempre los mismos recursos en el mismo orden, en todos los hilos.
- Los ejemplos prácticos combinan las herramientas de creación, interrupción y unión de hilos del
  Módulo 15 con sincronización y/o acceso atómico de este módulo: no todo dato usado por varios hilos
  necesita protección, solo el que más de uno **escribe** al mismo tiempo.

📎 Ver en la práctica: [Ejemplo 03 — Interbloqueo](03-interbloqueo.md)
📎 Ver en la práctica: [Ejemplo 04 — Ejemplo práctico integrador](04-ejemplo-practico-integrador.md)

## 📋 Resumen de las herramientas de protección de datos compartidos

| Herramienta | Qué resuelve | Cuándo usarla |
|---|---|---|
| `synchronized` | Exclusión mutua: solo un hilo a la vez ejecuta el código protegido | Cuando hace falta ejecutar varias operaciones relacionadas como una sola unidad |
| Clases atómicas (`AtomicInteger`, ...) | Operaciones indivisibles sin bloqueo explícito | Cuando alcanza con proteger una única variable con una única operación |
