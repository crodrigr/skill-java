# 📚 Explicación conceptual — Módulo 20

## 🧠 Concepto: ¿Qué es Stream API?

- Un **Stream** es una secuencia de elementos sobre la que se encadenan operaciones (transformar,
  filtrar, verificar condiciones), sin escribir el bucle explícito que las recorre.
- Reemplaza el patrón repetitivo de "recorrer con un bucle, aplicar lógica dentro, hacer algo con el
  resultado" por un pipeline de operadores encadenados.

📎 Ver en la práctica: [Ejemplo 01 — ¿Qué es Stream API?](01-que-es-stream-api.md)

## 🧠 Concepto: Características

- **No almacena datos**: describe operaciones sobre una fuente existente, no es una estructura de datos.
- **No modifica su fuente**: aplicar operadores nunca cambia la `List`/arreglo/colección original.
- **Es de un solo uso**: un stream solo puede recorrerse una vez (ver Ejemplo 07).
- **Puede evaluarse de forma perezosa**: los operadores no se ejecutan hasta que hace falta un resultado.

📎 Ver en la práctica: [Ejemplo 01 — ¿Qué es Stream API?](01-que-es-stream-api.md)

## 🧠 Concepto: Cómo crear un Stream

- Un stream se crea a partir de una fuente de datos: valores sueltos, un arreglo, elementos agregados uno
  a uno, o una colección ya existente.
- Las cuatro formas producen el mismo tipo, `Stream<T>`; solo cambia la fuente.

📎 Ver en la práctica: [Ejemplo 02 — Cómo crear un Stream](02-como-crear-un-stream.md)

## 🧠 Concepto: Stream.of()

- `Stream.of(valor1, valor2, ...)` crea un stream directamente a partir de valores sueltos.

📎 Ver en la práctica: [Ejemplo 02 — Cómo crear un Stream](02-como-crear-un-stream.md)

## 🧠 Concepto: A partir de un arreglo

- `Arrays.stream(arreglo)` crea un stream a partir de un arreglo ya existente.

📎 Ver en la práctica: [Ejemplo 02 — Cómo crear un Stream](02-como-crear-un-stream.md)

## 🧠 Concepto: Stream.<>builder()

- `Stream.<T>builder()` permite agregar elementos uno por uno con `.add(...)`, y construir el stream al
  final con `.build()`.

📎 Ver en la práctica: [Ejemplo 02 — Cómo crear un Stream](02-como-crear-un-stream.md)

## 🧠 Concepto: A partir de una colección

- Toda colección que implementa `Collection` (como `List`) tiene un método `.stream()` que crea un
  stream a partir de sus elementos.

📎 Ver en la práctica: [Ejemplo 02 — Cómo crear un Stream](02-como-crear-un-stream.md)

## 🧠 Concepto: Operadores

- Los **operadores** encadenan transformaciones y condiciones sobre un stream, sin necesitar un bucle
  explícito con lógica dentro.
- `map`, `filter` y `anyMatch` reciben una `Function`/`Predicate` del Módulo 19, implementados con
  expresiones lambda.

📎 Ver en la práctica: [Ejemplo 03 — Operador map](03-operador-map.md)

## 🧠 Concepto: Operador map

- `map` transforma cada elemento del stream con una `Function<T, R>`, produciendo un nuevo stream con la
  **misma cantidad** de elementos, uno transformado por cada uno de entrada.

📎 Ver en la práctica: [Ejemplo 03 — Operador map](03-operador-map.md)

## 🧠 Concepto: Operador filter

- `filter` selecciona, con un `Predicate<T>`, los elementos del stream que cumplen una condición; el
  resultado puede tener **menos** elementos que el original.

📎 Ver en la práctica: [Ejemplo 04 — Operador filter](04-operador-filter.md)

## 🧠 Concepto: Operador anyMatch

- `anyMatch` es una operación **terminal**: recibe un `Predicate<T>` y devuelve `true` si al menos un
  elemento del stream lo cumple, `false` si ninguno lo cumple. Consume el stream.

📎 Ver en la práctica: [Ejemplo 05 — Operador anyMatch](05-operador-anymatch.md)

## 🧠 Concepto: Operador flatMap

- `flatMap` combina transformación con aplanado: recibe una función que transforma cada elemento en un
  stream, y combina todos esos streams en uno solo. Sirve cuando cada elemento se transforma en
  **varios** elementos (por ejemplo, una colección anidada) que hace falta aplanar en un único stream.
- Es distinto de `map`: `map` sobre una estructura anidada (`List<List<T>>`) produce un
  `Stream<List<T>>` — la estructura anidada queda intacta. `flatMap` produce un `Stream<T>` aplanado.

📎 Ver en la práctica: [Ejemplo 06 — Operador flatMap](06-operador-flatmap.md)

## 🧠 Concepto: Conversión de List a Stream

- Una `List` se convierte en `Stream` con `.stream()` (ya visto en "A partir de una colección"); el
  camino inverso, de `Stream` a `List`, se hace con `.toList()` al final de la cadena de operadores.
- Un stream es de un solo uso: una vez consumido por una operación terminal (como `.forEach()` o
  `.toList()`), volver a operar sobre la misma variable produce un error real,
  `IllegalStateException: stream has already been operated upon or closed`.

📎 Ver en la práctica: [Ejemplo 07 — Conversión de List a Stream](07-conversion-de-list-a-stream.md)

## 📋 Resumen de las herramientas

| Herramienta | Tipo | Qué hace |
|---|---|---|
| `Stream.of(valores...)` | Creación | Crea un stream a partir de valores sueltos |
| `Arrays.stream(arreglo)` | Creación | Crea un stream a partir de un arreglo existente |
| `Stream.<T>builder()` | Creación | Crea un stream agregando elementos uno por uno con `.add(...)` y `.build()` |
| `coleccion.stream()` | Creación | Crea un stream a partir de una colección existente (`List`, etc.) |
| `map(Function<T, R>)` | Operador intermedio | Transforma cada elemento; misma cantidad de elementos |
| `filter(Predicate<T>)` | Operador intermedio | Selecciona los elementos que cumplen una condición; puede haber menos |
| `flatMap(Function<T, Stream<R>>)` | Operador intermedio | Transforma cada elemento en un stream y aplana todos en uno solo |
| `anyMatch(Predicate<T>)` | Operador terminal | Devuelve `true` si al menos un elemento cumple la condición; consume el stream |
| `.toList()` | Conversión | Recolecta los elementos del stream en una `List<T>` nueva; consume el stream |
