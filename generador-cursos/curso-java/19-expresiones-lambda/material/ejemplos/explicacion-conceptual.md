# 📚 Explicación conceptual — Módulo 19

## 🧠 Concepto: Expresiones lambda

- Una interfaz con un único método abstracto se llama **interfaz funcional**.
- Antes de Java 8, implementarla "al vuelo" exigía una clase completa o una clase anónima.
- Una **expresión lambda** (`(parámetros) -> cuerpo`) implementa esa misma interfaz de forma concisa; el
  compilador infiere la interfaz por el contexto.
- No es un mecanismo nuevo: produce exactamente el mismo resultado que una clase que implementa la misma
  interfaz.

📎 Ver en la práctica: [Ejemplo 01 — ¿Qué son las expresiones lambda?](01-que-son-las-expresiones-lambda.md)

## 🧠 Concepto: Consumer

- `Consumer<T>` declara `void accept(T t)`: recibe un valor y ejecuta una acción, sin devolver nada.
- Reemplaza una interfaz propia de un solo método (y su clase completa) para cualquier acción que no
  devuelve resultado.
- Se implementa con una expresión lambda: `valor -> { ... }` o `valor -> expresion`.

📎 Ver en la práctica: [Ejemplo 02 — Consumer](02-consumer.md)

## 🧠 Concepto: Supplier

- `Supplier<T>` declara `T get()`: no recibe ningún parámetro y devuelve un valor de tipo `T`.
- Es la forma opuesta a `Consumer<T>`: `Supplier` provee un valor bajo demanda; `Consumer` recibe un
  valor y actúa sobre él.
- Se implementa con una expresión lambda sin parámetros: `() -> valor`.

📎 Ver en la práctica: [Ejemplo 03 — Supplier](03-supplier.md)

## 🧠 Concepto: Funciones lambda

- `Function<T, R>` declara `R apply(T t)`: recibe un valor de tipo `T` y devuelve un valor de tipo `R`
  (puede ser un tipo distinto).
- Se usa para transformar datos, a diferencia de `Consumer<T>` (no devuelve nada) o `Supplier<T>` (no
  recibe nada).
- Se implementa con una expresión lambda: `valor -> expresion`.

📎 Ver en la práctica: [Ejemplo 04 — Funciones lambda](04-funciones-lambda.md)

## 🧠 Concepto: Predicate

- `Predicate<T>` declara `boolean test(T t)`: recibe un valor y evalúa una condición, devolviendo
  siempre un `boolean`.
- Se diferencia de `Function<T, R>` en que su tipo de retorno está fijo (`boolean`), no es genérico.
- Se implementa con una expresión lambda: `valor -> condicion_booleana`.

📎 Ver en la práctica: [Ejemplo 05 — Predicate](05-predicate.md)

## 🧠 Concepto: Expresiones lambda propias

- Cuando un caso no encaja en `Consumer`, `Supplier`, `Function` ni `Predicate` (por ejemplo, recibe dos
  parámetros de tipos distintos), se diseña una interfaz funcional propia.
- `@FunctionalInterface` documenta la intención y exige, en tiempo de compilación, que la interfaz
  declare exactamente un método abstracto.
- Si se agrega un segundo método abstracto a una interfaz anotada así, el compilador rechaza la interfaz
  con un error real (`multiple non-overriding abstract methods found`).

📎 Ver en la práctica: [Ejemplo 06 — Expresiones lambda propias](06-expresiones-lambda-propias.md)

## 📋 Resumen de las interfaces funcionales

| Interfaz | Método abstracto | Recibe | Devuelve | Cuándo usarla |
|---|---|---|---|---|
| `Consumer<T>` | `accept(T t)` | Un valor | Nada (`void`) | Ejecutar una acción sobre un valor |
| `Supplier<T>` | `get()` | Nada | Un valor | Proveer un valor bajo demanda |
| `Function<T, R>` | `apply(T t)` | Un valor | Otro valor (de otro tipo) | Transformar un valor en otro |
| `Predicate<T>` | `test(T t)` | Un valor | `boolean` | Evaluar una condición |
| Interfaz propia | El que declares | Lo que necesites | Lo que necesites | Ninguna interfaz estándar encaja en la forma del problema |
