# 📚 Explicación conceptual — Módulo 4

## 🧠 Concepto: Objetos y referencias (lo mínimo)

Un **objeto** es un valor que vive en la memoria. Una variable de tipo `String` (o `StringBuilder`,
o `Integer`) no guarda el texto o el número directamente: guarda una **referencia** a ese objeto.

- Dos variables pueden apuntar **al mismo objeto** o a **objetos distintos** con el mismo contenido.
- `==` compara referencias: ¿es el mismo objeto?
- `.equals(...)` compara contenido: ¿dicen lo mismo?
- Esta distinción explica por qué `==` no sirve para comparar el contenido de un texto (Módulo 2) ni el
  de las clases envolventes (punto 3 de este módulo).
- Este módulo **usa** objetos que ya existen (`String`, `StringBuilder`, las envolventes); definir
  clases propias queda para un módulo posterior.

📎 Ver en la práctica: [Ejemplo 01 — Creación de cadenas](01-creacion-de-cadenas.md)

## 🧠 Concepto: Creación de cadenas

- **Literal**: `"texto"`. Java reutiliza el mismo objeto para literales iguales (el "pool" de cadenas).
- **`new String("texto")`**: crea siempre un objeto nuevo, aunque el contenido sea igual a uno que ya
  existe.
- **Cadena vacía** (`""`): un objeto válido, de longitud 0.
- **Cadena nula** (`null`): no es un objeto; llamar a un método sobre ella detiene el programa.
- **Errores típicos**: usar una cadena nula que llega de otra parte del programa sin comprobarlo antes.

📎 Ver en la práctica: [Ejemplo 01 — Creación de cadenas](01-creacion-de-cadenas.md)

## 🧠 Concepto: Concatenación de cadenas

- **`+`**: une dos valores en una expresión nueva.
- **`+=`**: une y actualiza la variable.
- **`.concat(...)`**: método de `String` que une dos cadenas (no admite números directamente).
- **Orden de evaluación**: `+` se evalúa de izquierda a derecha; si el primer operando ya es texto, los
  números que siguen se concatenan uno por uno en vez de sumarse.

📎 Ver en la práctica: [Ejemplo 02 — Concatenación de cadenas](02-concatenacion-de-cadenas.md)

## 🧠 Concepto: Longitud de cadena

- `length()`: cuántos caracteres tiene la cadena.
- `isEmpty()`: `true` si `length()` es `0`.
- Sirve como límite de un `for` que recorre la cadena carácter por carácter (Módulo 3).
- Cada tilde y la `ñ` cuentan como un carácter.

📎 Ver en la práctica: [Ejemplo 03 — Longitud de cadena](03-longitud-de-cadena.md)

## 🧠 Concepto: Extracción de caracteres

- `charAt(indice)`: devuelve el carácter en esa posición. Los índices empiezan en `0`.
- El último índice válido es siempre `length() - 1`.
- Pedir un índice igual o mayor que `length()` (o negativo) detiene el programa: es un error en
  **tiempo de ejecución**, no de compilación.
- Se usa dentro de un `for` para recorrer y procesar cada carácter (contar, comparar, transformar).

📎 Ver en la práctica: [Ejemplo 04 — Extracción de caracteres](04-extraccion-de-caracteres.md)

## 🧠 Concepto: Comparación de cadenas

- `.equals(otro)`: ¿el contenido es exactamente igual? (distingue mayúsculas)
- `.equalsIgnoreCase(otro)`: igual, ignorando mayúsculas y minúsculas.
- `.compareTo(otro)`: da negativo, cero o positivo según el orden alfabético; **solo el signo** es
  fiable.
- `.contains`, `.startsWith`, `.endsWith`: preguntas de sí o no sobre el contenido.
- `==` **nunca** se usa para comparar el contenido de un texto (Módulo 2): compara si son el mismo
  objeto.

📎 Ver en la práctica: [Ejemplo 05 — Comparación de cadenas](05-comparacion-de-cadenas.md)

## 🧠 Concepto: Conversión de cadenas

- `.trim()`: quita espacios de los extremos.
- `.toUpperCase()` / `.toLowerCase()`: cambia mayúsculas y minúsculas.
- `.replace(viejo, nuevo)`: reemplaza todas las apariciones.
- `String.valueOf(valor)`: convierte cualquier valor a texto.
- **Ninguno de estos métodos cambia la cadena original**: todos devuelven una nueva. Si no se guarda
  el resultado, la transformación se pierde.

📎 Ver en la práctica: [Ejemplo 06 — Conversión de cadenas](06-conversion-de-cadenas.md)

## 🧠 Concepto: Subcadenas

- `substring(inicio)`: desde `inicio` hasta el final.
- `substring(inicio, fin)`: desde `inicio` hasta `fin`, **sin incluir** `fin`.
- `indexOf(texto)`: la posición de la primera aparición, o `-1` si no la encuentra.
- **Siempre** hay que comprobar que `indexOf` no haya devuelto `-1` antes de usarlo en un `substring`.
- Errores típicos: pedir un rango que se pasa de la longitud, y usar el `-1` de `indexOf` sin
  comprobarlo.

📎 Ver en la práctica: [Ejemplo 07 — Subcadenas](07-subcadenas.md)

## 🧠 Concepto: Conversiones implícitas

- Java las hace **solo**, sin pedirlas, cuando el tipo destino puede contener cualquier valor del
  origen sin perder información.
- Escalera de tamaños: `byte → short → int → long → float → double`. Solo se sube sin pedirlo.
- Un `char` se convierte implícitamente a `int` (su valor numérico Unicode).
- Mezclar `int` y `double` en una expresión da como resultado `double`.

📎 Ver en la práctica: [Ejemplo 08 — Conversiones implícitas](08-conversiones-implicitas.md)

## 🧠 Concepto: Conversiones explícitas

- Se piden con un `cast`: `(tipoDestino) valor`.
- Van "hacia abajo" en la escalera de tamaños (de `double` a `int`, de `int` a `byte`...).
- Tres riesgos: **corte de decimales** (no redondea), **desbordamiento** (el valor "da la vuelta" sin
  avisar) y **pérdida de precisión** (un número muy grande puede no representarse exacto).

📎 Ver en la práctica: [Ejemplo 09 — Conversiones explícitas](09-conversiones-explicitas.md)

## 🧠 Concepto: Clases Wrappers

- Cada primitivo tiene una envolvente: `int`→`Integer`, `double`→`Double`, `boolean`→`Boolean`,
  `char`→`Character`, `long`→`Long`.
- **Empaquetado** (autoboxing) y **desempaquetado** (unboxing): Java convierte entre primitivo y
  envolvente sin código extra.
- `Integer.parseInt(texto)` / `Double.parseDouble(texto)`: convierten texto a número; con texto no
  numérico, detienen el programa.
- `Character.isDigit(caracter)`: valida un carácter antes de convertir.
- Java reutiliza los `Integer` de -128 a 127 (por eso `==` puede dar `true` ahí), pero no fuera de ese
  rango: las envolventes se comparan con `.equals(...)`.

📎 Ver en la práctica: [Ejemplo 10 — Clases Wrappers](10-clases-wrappers.md)

## 🧠 Concepto: Mutabilidad

- `String` es **inmutable**: ningún método cambia su contenido; todos devuelven una cadena nueva.
- Las clases envolventes también son inmutables.
- `StringBuilder` es **mutable**: `.append`, `.insert` y `.reverse` modifican el mismo objeto.
- `.equals()` en `StringBuilder` **no** compara contenido (a diferencia de `String`); para comparar
  contenido, usa `.toString()` en ambos y compáralos con `.equals()`.

📎 Ver en la práctica: [Ejemplo 11 — Mutabilidad](11-mutabilidad.md)

## 🧠 Concepto: Cuándo usar StringBuilder

- Cuando el texto se construye **por partes**, sobre todo dentro de un bucle (Módulo 3): cada `+=`
  sobre un `String` crea una cadena nueva y descarta la anterior.
- `StringBuilder` modifica el mismo objeto en cada `.append(...)`, sin ese costo.
- Para un texto que no cambia (o cambia poco), `String` sigue siendo la opción más simple.

📎 Ver en la práctica: [Ejemplo 11 — Mutabilidad](11-mutabilidad.md)
