# 📚 Explicación conceptual — Módulo 10

## 🧠 Concepto: Operaciones con conjuntos

- Un `Set` agrupa valores **sin duplicados**: si agregás un valor que ya está, no pasa nada, el
  conjunto no crece.
- No tiene índice: no existe `set.get(0)`; se recorre con `for-each` o se consulta con `contains`.
- Operaciones más comunes: `add`, `remove`, `contains`, `size`, `isEmpty`.
- Combinar dos conjuntos (sobre una copia, para no modificar los originales):
  - **Unión** (`addAll`): todos los valores de ambos.
  - **Intersección** (`retainAll`): solo los valores presentes en ambos.
  - **Diferencia** (`removeAll`): solo los valores exclusivos del primero.

📎 Ver en la práctica: [Ejemplo 01 — Operaciones con conjuntos](01-operaciones-con-conjuntos.md)

## 🧠 Concepto: HashMap

- Un `Map` asocia una **clave única** a un **valor**: cada clave aparece una sola vez.
- `HashMap` es la implementación más común: `put(clave, valor)`, `get(clave)`, `containsKey(clave)`,
  `remove(clave)`, `size()`.
- Si haces `put` con una clave que ya existe, el valor se **actualiza**: el `Map` no crece.
- No garantiza ningún orden al recorrerlo con `keySet()` o `entrySet()`.

📎 Ver en la práctica: [Ejemplo 02 — HashMap](02-hashmap.md)

## 🧠 Concepto: TreeMap

- `TreeMap` es otra implementación de `Map`, con las mismas operaciones que `HashMap`.
- A diferencia de `HashMap`, mantiene sus claves **siempre ordenadas** al recorrerlas.
- Conviene elegirlo cuando el programa necesita que las claves queden ordenadas; si no importa el
  orden, `HashMap` alcanza.

📎 Ver en la práctica: [Ejemplo 03 — TreeMap](03-treemap.md)

## 🧠 Concepto: Enumeraciones

- Un `enum` declara un **conjunto cerrado de valores válidos**, en vez de usar texto suelto o
  constantes sin relación entre sí.
- `values()` devuelve todos los valores, en el orden en que se declararon.
- `ordinal()` devuelve la posición de un valor, empezando en `0`.
- Un valor de `enum` puede usarse como condición de un `switch`, con un `case` por valor.

📎 Ver en la práctica: [Ejemplo 04 — Enumeraciones](04-enumeraciones.md)

## 🧠 Concepto: Excepciones

- Una **excepción** es un error real que ocurre mientras el programa se ejecuta. Si nadie la maneja, el
  programa termina abruptamente (lo que viste en `falla-en-ejecucion` desde el Módulo 4).
- `try`/`catch` permite **capturarla**: el código dentro del `try` que falla se interrumpe, y el
  `catch` correspondiente se ejecuta en su lugar; el programa sigue corriendo después.
- `finally` ejecuta código que debe correr siempre, se haya capturado una excepción o no.
- `throw` lanza una excepción propia, incluida una **personalizada** (una clase que extiende
  `RuntimeException`), cuando un método detecta un dato inválido.

📎 Ver en la práctica: [Ejemplo 05 — Excepción sin capturar](05-excepcion-sin-capturar.md),
[Ejemplo 06 — try/catch](06-try-catch.md), [Ejemplo 07 — finally](07-finally.md),
[Ejemplo 08 — throw y excepción personalizada](08-throw-y-excepcion-personalizada.md)
