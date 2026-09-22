# ❓ Quiz 01 — Cadenas y conversiones (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** Se ejecuta `String a = "Java"; String b = "Java"; String c = new String("Java");`.
**Pregunta:** ¿cuál comparación con `==` da `true`?

- **A.** `a == c`
- **B.** `b == c`
- **C.** `a == b`
- **D.** Ninguna de las anteriores.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** `a` y `b` son literales iguales: Java reutiliza el mismo objeto del pool de
cadenas. `c` se creó con `new String(...)`: es un objeto aparte.

</details>

---

**2. [Selección múltiple]** **Pregunta:** ¿cuáles expresiones dan como resultado el texto `"5 citas"`?

- **A.** `"" + 2 + 3 + " citas"`
- **B.** `2 + 3 + " citas"`
- **C.** `"citas: " + 2 + 3`
- **D.** `(2 + 3) + " citas"`

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B y D.** En B y D, la suma `2 + 3` ocurre antes de tocar el texto: primero se
suma (`5`) y después se concatena. En A, el primer operando ya es `""` (texto), así que `2` y `3` se
concatenan uno por uno (`"23 citas"`). En C ocurre lo mismo (`"citas: 23"`).

</details>

---

**3. [Selección]** Con `String s = "Biblioteca";`. **Pregunta:** ¿cuál es el índice del último
carácter?

- **A.** `10`
- **B.** `9`
- **C.** `s.length()`
- **D.** No se puede saber sin ejecutar el programa.

_RA: RA-3, RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `"Biblioteca"` tiene 10 letras; el último índice válido es
`length() - 1 = 9`. C es incorrecta porque `s.length()` (10) ya se pasa de rango.

</details>

---

**4. [Selección]** **Pregunta:** ¿qué ocurre al ejecutar `"Ana".charAt(3)`?

- **A.** Devuelve un espacio en blanco.
- **B.** Devuelve `null`.
- **C.** El programa se detiene con una excepción.
- **D.** Da un error de compilación.

_RA: RA-4, RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** `"Ana"` tiene longitud 3 (índices 0 a 2); el índice 3 ya se pasa de rango y
Java lanza `StringIndexOutOfBoundsException` en tiempo de ejecución, no un error de compilación.

</details>

---

**5. [Selección múltiple]** Con `String codigo = "PR-N-000045";`. **Pregunta:** ¿cuáles resultados son
correctos?

- **A.** `codigo.substring(0, 2)` da `"PR"`.
- **B.** `codigo.substring(3, 4)` da `"N"`.
- **C.** `codigo.indexOf("-")` da `3`.
- **D.** `codigo.substring(5)` da `"000045"`.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: el primer guion está en la posición `2`, no en la `3`.

</details>

---

**6. [Abierta]** ¿Qué devuelve `indexOf` cuando no encuentra el texto buscado, y por qué hay que
comprobarlo antes de usar ese resultado en un `substring`?

_RA: RA-7, RA-15_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Devuelve `-1`. Si ese `-1` se usa directamente como índice de `substring` (por ejemplo,
`substring(0, -1)`), el rango es inválido y el programa se detiene con una excepción. Por eso hay que
comprobar que el resultado sea distinto de `-1` antes de usarlo.

</details>

---

**7. [Selección]** **Pregunta:** ¿qué método usarías para saber si `"Cien Años"` y `"cien años"`
"dicen lo mismo" sin importar las mayúsculas?

- **A.** `equals`
- **B.** `equalsIgnoreCase`
- **C.** `compareTo`
- **D.** `==`

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `equalsIgnoreCase` compara el contenido ignorando mayúsculas y minúsculas.

</details>

---

**8. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `compareTo` son verdaderas?

- **A.** Da `0` cuando las dos cadenas son iguales.
- **B.** El valor exacto que devuelve siempre es `1`, `0` o `-1`.
- **C.** Sirve para ordenar textos alfabéticamente.
- **D.** Solo el signo del resultado (positivo, negativo o cero) debe usarse para decidir.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: el valor exacto depende de las letras comparadas (por
ejemplo, puede dar 14 u 11), no siempre es 1 o -1.

</details>

---

**9. [Abierta]** Un compañero escribe `nombre.trim();` en su propia línea, sin asignar el resultado a
ninguna variable, y después usa `nombre` esperando que ya no tenga espacios. **Pregunta:** ¿qué le
pasa a su programa y por qué?

_RA: RA-6, RA-14, RA-15_

<details>
<summary>🔑 Ver respuesta modelo</summary>

`nombre` sigue teniendo los espacios: `trim()` (como todos los métodos de transformación de `String`)
devuelve una cadena **nueva** y no modifica la original. Si no se guarda ese resultado (por ejemplo,
`nombre = nombre.trim();`), la transformación se pierde. El panel Problems no marca ningún error,
porque el código compila y se ejecuta sin problemas: solo hace algo distinto de lo que se esperaba.

</details>

---

**10. [Selección]** Se ejecuta `int a = 3; double b = 2.0; double r = a / b;`. **Pregunta:** ¿qué tipo
tiene `a / b`?

- **A.** `int`
- **B.** `double`
- **C.** No compila.
- **D.** Depende del valor de `a`.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Al mezclar `int` y `double`, Java convierte implícitamente el `int` a
`double`: el resultado siempre es `double`.

</details>

---

**11. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `(int) 29.8` y `(byte) 200` son
verdaderas?

- **A.** `(int) 29.8` da `30` (redondea hacia arriba).
- **B.** `(int) 29.8` da `29` (corta los decimales).
- **C.** `(byte) 200` da un error de compilación.
- **D.** `(byte) 200` produce un desbordamiento silencioso.

_RA: RA-9, RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B y D.** Un `cast` a `int` siempre corta la parte decimal, nunca redondea. Un
valor que no cabe en `byte` (rango -128 a 127) se desborda sin ningún error ni aviso.

</details>

---

**12. [Selección]** **Pregunta:** ¿qué hace `Integer.parseInt("120 unidades")`?

- **A.** Devuelve `120`.
- **B.** Devuelve `0`.
- **C.** Detiene el programa con una excepción.
- **D.** Da un error de compilación.

_RA: RA-11, RA-14, RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** El texto no es un número válido: Java lanza `NumberFormatException` en
tiempo de ejecución, no un error de compilación.

</details>

---

**13. [Selección múltiple]** Con `Integer a = 100; Integer b = 100; Integer c = 300; Integer d = 300;`.
**Pregunta:** ¿cuáles afirmaciones son verdaderas?

- **A.** `a == b` da `true`.
- **B.** `c == d` da `true`.
- **C.** `c.equals(d)` da `true`.
- **D.** Asignar un `int` a un `Integer` requiere código adicional.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** B es falsa: fuera del rango -128 a 127, `Integer` no reutiliza el
objeto. D es falsa: el empaquetado es automático (autoboxing).

</details>

---

**14. [Selección]** Se ejecuta `String s = "hola"; s.toUpperCase();`. **Pregunta:** ¿qué vale `s`
después de esa línea?

- **A.** `"HOLA"`
- **B.** `"hola"` (sin cambios)
- **C.** `null`
- **D.** Da un error de compilación.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `String` es inmutable: `toUpperCase()` devuelve una cadena nueva que, al no
guardarse, se pierde. `s` sigue igual.

</details>

---

**15. [Abierta]** ¿Cuándo conviene usar `StringBuilder` en vez de concatenar cadenas con `+` dentro de
un bucle, y qué método usarías para agregar cada parte?

_RA: RA-13, RA-15_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Conviene cuando el texto se construye por partes y el bucle se repite varias veces, porque cada `+=`
sobre un `String` crea una cadena nueva en cada vuelta. Con `StringBuilder` se usa `.append(...)` para
agregar cada parte al mismo objeto, sin crear cadenas intermedias de más.

</details>
