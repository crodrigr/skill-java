# 📚 Explicación conceptual — Módulo 3

## 🧠 Concepto: Estructuras repetitivas

Una **estructura repetitiva** (o **bucle**) ejecuta un bloque de instrucciones varias veces, en vez
de escribirlo a mano una y otra vez.

- **Iteración**: cada ejecución del bloque (también llamada "vuelta").
- **Condición del bucle**: una expresión `boolean` que decide si se ejecuta otra vuelta.
- **Tres estructuras**: `while`, `do-while` y `for`.
- **Dos sentencias que alteran el recorrido**: `break` (termina el bucle) y `continue` (salta a la
  siguiente vuelta).
- **Prueba de escritorio**: una tabla con el valor de las variables en cada vuelta, para predecir el
  resultado antes de ejecutar.
- **Regla de oro**: algo dentro del bucle debe hacer que la condición llegue a ser falsa; si no, el
  bucle **no termina**.

📎 Ver en la práctica: [Ejemplo 01 — While](01-while.md)

## 🧠 Concepto: While

`while` repite un bloque **mientras una condición sea verdadera**.

- **Forma**: `while (condición) { ... }`.
- **Orden**: evalúa la condición, ejecuta el bloque, y vuelve a evaluar la condición.
- **Puede ejecutarse cero veces**: si la condición es falsa desde el principio.
- **Variable de control**: una variable (el contador) que se actualiza dentro del bloque para que la
  condición llegue a ser falsa.
- **Errores típicos**: olvidar actualizar la variable de control (no termina), poner un `;` después
  del `while` (no termina), una repetición de menos por `<` en vez de `<=` y declarar el acumulador
  dentro del bucle.

📎 Ver en la práctica: [Ejemplo 01 — While](01-while.md)

## 🧠 Concepto: Do-while

`do-while` repite un bloque y comprueba la condición **al final** de cada vuelta.

- **Forma**: `do { ... } while (condición);`, con un `;` obligatorio al final.
- **Orden**: ejecuta el bloque, evalúa la condición y, si es verdadera, vuelve a ejecutar el bloque.
- **Al menos una vez**: el bloque se ejecuta siempre, aunque la condición sea falsa desde el principio.
- **Diferencia con `while`**: con la condición falsa desde el principio, `while` hace 0 vueltas y
  `do-while` hace 1; en los demás casos coinciden.
- **Cuándo usarlo**: cuando algo debe ocurrir una vez como mínimo (imprimir un comprobante, mostrar un
  resumen).
- **Errores típicos**: olvidar el `;` final (error de compilación) y usar `while` donde el bloque debe
  ejecutarse una vez.

📎 Ver en la práctica: [Ejemplo 02 — Do-while](02-do-while.md)

## 🧠 Concepto: For

`for` reúne en una línea la inicialización, la condición y la actualización de un contador.

- **Forma**: `for (inicialización; condición; actualización) { ... }`.
- **Orden**: la inicialización se ejecuta **una vez**; luego se evalúa la condición, se ejecuta el bloque
  y se ejecuta la actualización, y se vuelve a evaluar la condición.
- **Hacia adelante**: `cuota++`. **Hacia atrás**: `dia--`. **Con paso**: `turno += 2`.
- **Alcance**: la variable declarada en la inicialización solo existe dentro del bucle.
- **Cuándo usarlo**: cuando se sabe de antemano cuántas veces repetir.
- **Errores típicos**: `<` frente a `<=` (una repetición de más o de menos), un `;` después del `for`,
  un contador `double`, usar el contador fuera del bucle y dejar la actualización en blanco.

📎 Ver en la práctica: [Ejemplo 03 — For](03-for.md)

## 🧠 Concepto: Contadores y acumuladores

Dos variables acompañan a casi todos los bucles.

- **Contador**: una variable **entera** que cuenta las vueltas (`turno++`, `cuota++`).
- **Acumulador**: una variable que va sumando o totalizando un valor (`totalFacturado += ...`).
- **Regla fija**: se **inicializan antes** del bucle y se **actualizan dentro** de él.
- **Si el acumulador se declara dentro**: se reinicia en cada vuelta (el total sale mal) o no existe
  fuera del bucle (error de compilación).
- **Contadores enteros**: un contador `double` puede dar una repetición de más.

📎 Ver en la práctica: [Ejemplo 01 — While](01-while.md) y [Ejemplo 03 — For](03-for.md)

## 🧠 Concepto: Cómo elegir la estructura repetitiva

Tres preguntas, en este orden, bastan para elegir:

- **¿Debe ejecutarse al menos una vez?** Sí: `do-while`.
- **¿Se sabe de antemano cuántas veces repetir (un rango de números)?** Sí: `for`.
- **En los demás casos**: `while` (se repite mientras algo se cumpla y puede no ejecutarse nunca).

| | `while` | `do-while` | `for` |
|---|---|---|---|
| Condición | antes de cada vuelta | después de cada vuelta | antes de cada vuelta |
| Vueltas mínimas | 0 | 1 | 0 |
| Cuándo usarlo | se repite mientras algo se cumpla | debe ocurrir al menos una vez | se sabe cuántas veces |
| Actualización | dentro del bloque | dentro del bloque | en la cabecera |

📎 Ver en la práctica: [Ejemplo 03 — For](03-for.md)

## 🧠 Concepto: Break

`break` termina el bucle de inmediato.

- **Qué hace**: sale del bucle más interno y el programa sigue con la instrucción que hay después.
- **Dentro de un `if`**: casi siempre se usa así, para decidir cuándo parar.
- **`while (true)`**: un bucle cuya única salida es un `break`; termina cuando el `break` se ejecuta.
- **Dentro de un `switch`**: el `break` de un `switch` que está dentro de un bucle solo sale del `switch`.
- **Errores típicos**: olvidar el `break`, creer que el de un `switch` termina el bucle, `break` fuera de un
  bucle, instrucciones después de un `break` y después de un `while (true)` sin `break`.

📎 Ver en la práctica: [Ejemplo 04 — Break](04-break.md)

## 🧠 Concepto: Continue

`continue` salta el resto de la vuelta actual y pasa a la siguiente.

- **Qué hace**: omite las instrucciones que quedan en el bloque, pero el bucle **no termina**.
- **Diferencia con `break`**: `break` termina el bucle; `continue` solo omite una vuelta.
- **En un `for`**: después de un `continue` se ejecuta la actualización, así que el contador siempre avanza.
- **En un `while` y en un `do-while`**: `continue` salta directamente a la condición; si la
  actualización del contador está al final del bloque, se salta y el bucle puede no terminar.
- **Solución en un `while`**: actualizar el contador **antes** del `continue`.
- **Errores típicos**: `continue` que salta la actualización (no termina), usar `break` cuando se quería
  `continue`, `continue` fuera de un bucle e instrucciones después de un `continue`.

📎 Ver en la práctica: [Ejemplo 05 — Continue](05-continue.md)
