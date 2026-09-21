# 📚 Explicación conceptual — Módulo 2

Este módulo enseña a que un programa **calcule** con datos y **decida** qué hacer. Primero
aprenderás los operadores (símbolos que combinan valores) y luego las estructuras que eligen
qué código se ejecuta. Necesitas lo que aprendiste en el Módulo 1: variables, constantes, tipos
primitivos e impresión en consola.

## 🧠 Concepto: Operadores de asignación y aritméticos

Un **operador** combina valores para producir un resultado, y una **expresión** es una
combinación de valores, variables y operadores que produce un valor.

- **Aritméticos**: `+`, `-`, `*`, `/` y `%` (residuo).
- **División entre enteros**: descarta los decimales (`7 / 2` da `3`); el residuo es lo que
  sobra (`7 % 2` da `1`).
- **División con decimales**: si un valor es `double`, el resultado es `double` (`7 / 2.0` da
  `3.5`). Para forzarlo se convierte **antes** de dividir: `(double) a / b`.
- **Asignación**: `=` guarda un valor en una variable.
- **Asignación compuesta**: `+=`, `-=`, `*=`, `/=` y `%=` calculan y guardan en un solo paso
  (`cupos -= 3` equivale a `cupos = cupos - 3`).
- **Incremento y decremento**: `++` y `--`; `turno++` entrega el valor y luego suma, `++turno`
  suma y luego entrega.
- **El `+` con texto** une el texto con el valor y se evalúa de izquierda a derecha.
- **Precedencia**: cuando una expresión mezcla operadores, Java evalúa primero los de mayor
  nivel; los paréntesis siempre se evalúan primero.

| Nivel | Operadores | Se evalúan |
|---|---|---|
| 1 (primero) | Paréntesis `( )` | de adentro hacia afuera |
| 2 | `++` `--` `!` y la conversión `(double)` | de derecha a izquierda |
| 3 | `*` `/` `%` | de izquierda a derecha |
| 4 | `+` `-` | de izquierda a derecha |
| 5 | `<` `>` `<=` `>=` | de izquierda a derecha |
| 6 | `==` `!=` | de izquierda a derecha |
| 7 | `&&` | de izquierda a derecha |
| 8 | `\|\|` | de izquierda a derecha |
| 9 | `? :` (ternario) | de derecha a izquierda |
| 10 (último) | `=` `+=` `-=` `*=` `/=` `%=` | de derecha a izquierda |

- **Errores típicos**: la división entera inesperada, dividir un entero entre cero (detiene el
  programa), el desbordamiento de un `int` y `+=` con un decimal sobre un `int` (trunca sin
  avisar).

📎 Ver en la práctica: [Ejemplo 01 — Operadores de asignación y aritméticos](01-operadores-de-asignacion-y-aritmeticos.md)

## 🧠 Concepto: Operadores de igualdad y relacionales

Un operador de comparación recibe dos valores y produce un `boolean`: `true` o `false`.

- **Igualdad**: `==` (igual a) y `!=` (distinto de).
- **Relacionales**: `<`, `>`, `<=` y `>=`.
- **`=` frente a `==`**: `=` **asigna** y `==` **compara**.
- **Valor límite**: elegir `>` o `>=` cambia el resultado justo en el borde (con `20` y `>= 20`
  es verdadero; con `> 20`, falso).
- **Decimales**: no se comparan de forma fiable con `==` (`0.1 + 0.2` no es exactamente `0.3`).
- **Texto**: no se compara con `==` sino con `equals`, que compara lo que dice el texto.
- **Errores típicos**: `if (x = 5)`, un número usado como condición, comparaciones encadenadas
  (`18 <= edad < 65`) y texto comparado con `==`.

📎 Ver en la práctica: [Ejemplo 02 — Operadores de igualdad y relacionales](02-operadores-de-igualdad-y-relacionales.md)

## 🧠 Concepto: Operadores condicionales

Los operadores condicionales combinan condiciones (valores `boolean`) en una sola.

- **`&&` (Y)**: verdadero solo si las **dos** condiciones son verdaderas.
- **`||` (O)**: verdadero si **al menos una** es verdadera.
- **`!` (NO)**: invierte el valor.
- **Tabla de verdad**: lista el resultado de un operador para cada combinación de valores.
- **Cortocircuito**: con `&&`, si la primera es falsa no se evalúa la segunda; con `||`, si la
  primera es verdadera tampoco. Sirve para proteger una operación riesgosa (`cantidadConsultas
  > 0 && total / cantidadConsultas > 100000`).
- **Rangos**: "entre 18 y 64" se escribe `edad >= 18 && edad < 65`.
- **Errores típicos**: cambiar Y por O, olvidar la guarda del cortocircuito o poner la guarda a
  la derecha.

📎 Ver en la práctica: [Ejemplo 03 — Operadores condicionales](03-operadores-condicionales.md)

## 🧠 Concepto: If-else

`if` hace que el programa **elija** qué código ejecutar según una condición.

- **`if`**: ejecuta un bloque solo si la condición es verdadera.
- **`if - else`**: elige entre dos caminos; se ejecuta exactamente uno.
- **`if - else if - else`**: elige entre varios tramos; se ejecuta el primero cuya condición sea
  verdadera y el `else` recoge el resto.
- **Anidado**: un `if` dentro de otro.
- **Llaves**: delimitan qué instrucciones dependen de la condición; úsalas siempre.
- **Orden**: las condiciones se evalúan de arriba hacia abajo, de la más específica a la más
  general.
- **Casos de prueba**: una regla con tramos se prueba con un valor de cada tramo y con cada
  valor límite.
- **Errores típicos**: `;` después del `if`, olvidar las llaves, condiciones en orden
  equivocado y variables sin valor en alguna rama.

📎 Ver en la práctica: [Ejemplo 04 — If-else](04-if-else.md)

## 🧠 Concepto: Switch

`switch` elige entre varias opciones exactas de **un mismo valor**.

- **Valores admitidos**: `int`, `char` y `String`.
- **Forma clásica**: `case X:` y `break` al final de cada caso.
- **Caída**: sin `break`, la ejecución continúa en el caso siguiente.
- **Forma con flecha**: `case X ->`; no cae y admite varios valores (`case "A", "B" ->`).
- **Expresión**: `int dias = switch (...) { ... };` produce un valor y debe cubrir todos los
  casos; con `yield` se entrega el valor desde un bloque.
- **`default`**: atiende el valor que no coincide con ningún caso.
- **Errores típicos**: olvidar `break`, no poner `default`, mezclar `->` con `:` y repetir un
  caso.

📎 Ver en la práctica: [Ejemplo 05 — Switch](05-switch.md)

## 🧠 Concepto: Operador ternario

El operador ternario resume una decisión de dos resultados en una expresión.

- **Forma**: `condición ? valorSiVerdadera : valorSiFalsa`.
- **Produce un valor**: puede guardarse en una variable o usarse dentro de otra expresión.
- **Tipos**: las dos ramas deben producir valores de tipos compatibles.
- **Cuándo usarlo**: dos resultados y una asignación simple.
- **Cuándo no**: más de dos resultados, varias instrucciones por rama o ternarios anidados.

📎 Ver en la práctica: [Ejemplo 06 — Operador ternario](06-operador-ternario.md)

## 🧠 Concepto: Cómo elegir la estructura de decisión

Una regla de negocio se traduce a la estructura que mejor expresa su forma.

- **Rangos o condiciones combinadas** (`edad < 18`, `esAfiliado && !tieneDeuda`):
  `if - else if - else`.
- **Un mismo valor con varias opciones exactas** (tipo de usuario, código de categoría):
  `switch`.
- **Dos resultados y una asignación simple**: operador ternario.
- **Varias instrucciones por rama**: `if - else`, no ternario.
- **Siempre**: prueba la decisión con un valor de cada rama y con los valores límite.

📎 Ver en la práctica: [Ejemplo 06 — Operador ternario](06-operador-ternario.md)
