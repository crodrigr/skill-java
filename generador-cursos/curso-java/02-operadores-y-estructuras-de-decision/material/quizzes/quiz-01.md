# ❓ Quiz 01 — Operadores y estructuras de decisión (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** En la biblioteca se ejecutan estas líneas:

- `int librosPrestados = 2;`
- `librosPrestados += 3;`
- `librosPrestados -= 1;`

**Pregunta:** ¿cuánto vale `librosPrestados` al final?

- **A.** `4`
- **B.** `5`
- **C.** `6`
- **D.** `2`

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** `2 + 3 = 5` y luego `5 - 1 = 4`. Cada asignación compuesta actualiza
el valor que la variable tenía en ese momento.

</details>

---

**2. [Selección múltiple]** Con `int a = 17;` y `int b = 5;`. **Pregunta:** ¿cuáles afirmaciones
son verdaderas?

- **A.** `a / b` da `3`.
- **B.** `a % b` da `2`.
- **C.** `(double) a / b` da `3.4`.
- **D.** `(double) (a / b)` da `3.4`.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** La división entre enteros da `3` y el residuo es `2`. El cast
antes de dividir da `3.4`. En D el cast se aplica **después** de la división entera, así que
da `3.0`.

</details>

---

**3. [Selección]** **Pregunta:** ¿cuánto vale `2 + 3 * 4 - 1`?

- **A.** `19`
- **B.** `13`
- **C.** `15`
- **D.** `11`

_RA: RA-3, RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La multiplicación va primero: `3 * 4 = 12`; luego `2 + 12 - 1 = 13`.
Las otras opciones salen de poner paréntesis que la expresión no tiene.

</details>

---

**4. [Selección]** Un compañero escribe esta línea, con `int diasRetraso = 0;`:

- `boolean sinRetraso = (diasRetraso = 0);`

**Pregunta:** ¿qué ocurre?

- **A.** Guarda `true` en `sinRetraso`.
- **B.** Guarda `false` en `sinRetraso`.
- **C.** Da un error de compilación: un `int` no se puede convertir a `boolean`.
- **D.** Compila y deja `diasRetraso` en `0`, sin más.

_RA: RA-4, RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** `diasRetraso = 0` es una **asignación** y su resultado es un número,
no un `boolean`. Para comparar se escribe `diasRetraso == 0`. (Con una variable `boolean`, en
cambio, `=` en lugar de `==` compilaría y cambiaría el dato sin avisar.)

</details>

---

**5. [Abierta]** Una regla dice: "Los usuarios con 30 días de retraso **o más** quedan
suspendidos". ¿Qué operador usas para `diasRetraso` y qué valor debes probar sí o sí?

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Uso `diasRetraso >= 30`. Con `>` el usuario de exactamente 30 días no quedaría suspendido. Hay
que probar el valor límite `30`, porque es donde `>` y `>=` dan resultados distintos.

</details>

---

**6. [Selección múltiple]** Un paciente tiene `esAfiliado = true`, `tieneDeuda = true` y
`edadPaciente = 70`. **Pregunta:** ¿cuáles de estas condiciones dan `false`?

- **A.** `esAfiliado && !tieneDeuda`
- **B.** `edadPaciente >= 65 || tieneDeuda`
- **C.** `!esAfiliado || tieneDeuda`
- **D.** `esAfiliado && edadPaciente < 65`

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y D.** A: `true && false` da `false`. B: `true || true` da `true`. C:
`false || true` da `true`. D: `true && false` da `false`.

</details>

---

**7. [Selección]** Con `int librosPrestados = 0;`, se evalúa:

- `boolean r = librosPrestados > 0 && 40 / librosPrestados > 10;`

**Pregunta:** ¿qué ocurre?

- **A.** El programa se detiene con un error de división entre cero.
- **B.** `r` vale `false`, sin ningún error.
- **C.** `r` vale `true`.
- **D.** Da un error de compilación.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `librosPrestados > 0` es `false`; con `&&` el resultado ya es falso y
Java **no evalúa** la división. La primera condición protege a la segunda (cortocircuito).

</details>

---

**8. [Selección]** Un programa clasifica así el retraso:

- Si `diasRetraso > 30`, el estado es `Suspendido`.
- Si no, y `diasRetraso > 0`, el estado es `Con multa`.
- En cualquier otro caso, el estado es `Al día`.

**Pregunta:** ¿qué estado resulta con `diasRetraso = 30`?

- **A.** `Suspendido`
- **B.** `Con multa`
- **C.** `Al día`
- **D.** Ninguno de los tres.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `30 > 30` es falso, pero `30 > 0` es verdadero: se ejecuta la segunda
rama.

</details>

---

**9. [Abierta]** Una regla clasifica pacientes por edad en cuatro tramos. ¿Por qué no basta con
probarla con un valor típico de cada tramo?

_RA: RA-10, RA-6_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque los errores de `<` frente a `<=` aparecen justo en el borde entre dos tramos. Además de
un valor típico de cada tramo hay que probar los **valores límite**: el último de un tramo y el
primero del siguiente (por ejemplo, `11` y `12`).

</details>

---

**10. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `switch` son verdaderas?

- **A.** En un `switch` clásico, sin `break` la ejecución continúa en el caso siguiente.
- **B.** En un `switch` con flecha (`->`), un caso no cae al siguiente.
- **C.** `default` es obligatorio en todo `switch`.
- **D.** Un `switch` usado como expresión debe cubrir todos los valores posibles.

_RA: RA-7, RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: en un `switch` como sentencia `default` es
opcional (aunque conviene ponerlo para atender los valores inesperados).

</details>

---

**11. [Selección]** Con `int cantidadConsultas = 1;`, se ejecuta:

- `String texto = cantidadConsultas == 1 ? "consulta" : "consultas";`

**Pregunta:** ¿qué valor guarda `texto`?

- **A.** `consulta`
- **B.** `consultas`
- **C.** `true`
- **D.** `1`

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** La condición `cantidadConsultas == 1` es verdadera, así que el
ternario entrega el valor que va entre `?` y `:`.

</details>

---

**12. [Abierta]** Elige la estructura más adecuada (`if - else if`, `switch` o ternario) y
justifícala, para cada regla:

- (1) Mostrar `Afiliado` o `Particular` según `esAfiliado`.
- (2) Asignar un descuento según cuatro tramos de edad.
- (3) Obtener el nombre de un plan a partir de una letra (`'B'`, `'E'`, `'P'`).

_RA: RA-9, RA-11_

<details>
<summary>🔑 Ver respuesta modelo</summary>

(1) Ternario: son dos resultados y solo se asigna un valor. (2) `if - else if - else`: son
tramos (rangos) y un `switch` no los expresa bien. (3) `switch`: compara un mismo valor
(`char`) con opciones exactas, con `default` para una letra desconocida.

</details>
