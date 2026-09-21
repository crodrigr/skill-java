# ❓ Quiz 01 — Estructuras repetitivas (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** En la agenda de MediSalud se atienden los turnos con un `while (turno <= cuposDia)`.
**Pregunta:** ¿cuándo evalúa Java la condición de un `while`?

- **A.** Solo una vez, antes de empezar el bucle.
- **B.** Antes de cada vuelta.
- **C.** Después de cada vuelta.
- **D.** Solo cuando el bucle termina.

_RA: RA-1, RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El `while` evalúa la condición **antes** de cada vuelta; por eso puede no
ejecutarse ninguna vez. Evaluarla después de cada vuelta es lo que hace el `do-while`.

</details>

---

**2. [Selección múltiple]** Se ejecuta este código:

- `int turno = 1;`
- `int atendidos = 0;`
- `while (turno <= 4) { atendidos += 2; turno++; }`

**Pregunta:** ¿cuáles afirmaciones son verdaderas?

- **A.** El bloque se ejecuta 4 veces.
- **B.** `atendidos` vale `8` al terminar.
- **C.** `turno` vale `4` al terminar.
- **D.** La condición se evalúa solo 4 veces.

_RA: RA-2, RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** Con `turno` de 1 a 4 hay 4 vueltas y `2 * 4 = 8`. C es falsa: al
terminar, `turno` vale `5` (por eso la condición `5 <= 4` es falsa). D es falsa: la condición se
evalúa 5 veces (cuatro verdaderas y la última falsa).

</details>

---

**3. [Selección]** Con `int copias = 0;` y `int copiasSolicitadas = 0;`, se ejecuta:

- `do { copias++; } while (copias < copiasSolicitadas);`

**Pregunta:** ¿cuántas veces se ejecuta el bloque?

- **A.** Ninguna vez.
- **B.** Una vez.
- **C.** Dos veces.
- **D.** Infinitas veces.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un `do-while` ejecuta el bloque **antes** de evaluar la condición: hace
una vuelta y después `1 < 0` es falsa. Un `while` con la misma condición no se habría ejecutado.

</details>

---

**4. [Selección]** Se ejecuta `for (int cuota = 1; cuota <= 3; cuota++) { ... }`. **Pregunta:** ¿en qué
orden actúan las tres partes de la cabecera?

- **A.** Condición, inicialización y actualización, en cada vuelta.
- **B.** Inicialización (una vez), condición, bloque y actualización; después, de nuevo condición.
- **C.** Inicialización, actualización y condición.
- **D.** Bloque, inicialización y condición.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La inicialización se ejecuta una sola vez al empezar. Luego, en cada vuelta,
se evalúa la condición, se ejecuta el bloque y se ejecuta la actualización.

</details>

---

**5. [Selección múltiple]** Se ejecuta `for (int libro = 5; libro >= 1; libro -= 2) { System.out.print(libro + " "); }`.
**Pregunta:** ¿cuáles afirmaciones son verdaderas?

- **A.** Muestra `5 3 1`.
- **B.** El bloque se ejecuta 3 veces.
- **C.** Con la condición `libro > 1`, mostraría solo `5 3`.
- **D.** Después del bucle, `libro` vale 1 y se puede usar.

_RA: RA-4, RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** La secuencia es 5, 3, 1 (tres vueltas); con `libro > 1` el 1 ya no
cumple la condición. D es falsa: la variable del `for` solo existe dentro del bucle.

</details>

---

**6. [Abierta]** En un bucle que suma el valor de cada consulta, ¿por qué el acumulador se declara
**antes** del bucle y qué pasa si se declara dentro?

_RA: RA-6, RA-10_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Se declara y se inicializa antes para que conserve su valor de una vuelta a la siguiente. Si se declara
dentro, se crea de nuevo (en 0) en cada vuelta y el total sale mal (error lógico); además, fuera del bucle
esa variable no existe (error de compilación al usarla).

</details>

---

**7. [Selección]** Se ejecuta:

- `for (int dia = 1; dia <= 10; dia++) { if (dia == 4) { break; } System.out.println(dia); }`

**Pregunta:** ¿qué muestra?

- **A.** `1 2 3`
- **B.** `1 2 3 4`
- **C.** `1 2 3 5 6 7 8 9 10`
- **D.** No muestra nada.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Cuando `dia` vale 4, el `break` termina el bucle antes del `println`: dejan de
ejecutarse el resto de esa vuelta y todas las siguientes.

</details>

---

**8. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones son verdaderas?

- **A.** Un `while (true)` con un `break` alcanzable termina cuando se ejecuta el `break`.
- **B.** Un `break` dentro de un `switch` que está dentro de un bucle termina el bucle.
- **C.** Una instrucción escrita después de un `while (true)` sin `break` es un error de compilación.
- **D.** `break` se puede usar fuera de cualquier bucle o `switch`.

_RA: RA-8, RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** B es falsa: ese `break` solo sale del `switch`. D es falsa: fuera de un
bucle o `switch` es un error de compilación.

</details>

---

**9. [Selección]** Un `for` recorre los días 1 a 5 y, en el día 3, ejecuta `continue`. **Pregunta:** ¿qué
ocurre?

- **A.** El bucle termina en el día 3.
- **B.** Se salta el resto de la vuelta del día 3 y el bucle sigue con el día 4.
- **C.** El bucle vuelve a empezar desde el día 1.
- **D.** Da un error de compilación.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `continue` omite lo que queda de la vuelta actual, pero el bucle **no termina**.
Terminarlo es lo que hace `break`.

</details>

---

**10. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `continue` y la actualización del
contador son verdaderas?

- **A.** En un `while` con la actualización al final del bloque, un `continue` anterior a ella puede causar un bucle infinito.
- **B.** En un `for`, la actualización se ejecuta aunque haya un `continue`.
- **C.** En un `while`, el problema se evita actualizando el contador antes del `continue`.
- **D.** El panel Problems marca un error de compilación en ese `continue`.

_RA: RA-9, RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: el código compila y el editor no avisa de que el bucle no
termina.

</details>

---

**11. [Selección]** Un `for (int cuota = 0; cuota <= 3; cuota++)` debe listar 3 cuotas y lista 4. Otro
programa muestra solo su primera línea y nunca termina. **Pregunta:** ¿cuál es la causa más probable de
cada síntoma?

- **A.** El primero empieza en 0 y usa `<=` (una repetición de más); el segundo no actualiza dentro del bucle la variable de la condición.
- **B.** El primero usa `<` (una de más); el segundo olvidó el `;` de un `do-while`.
- **C.** El primero tiene un `;` después del `for`; el segundo usa `break` dentro de un `switch`.
- **D.** Los dos se deben a un acumulador `double`.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Empezar en 0 con `<=` da una repetición de más (0, 1, 2 y 3). Un bucle que no
termina casi siempre se debe a que nada dentro cambia la variable de la condición (o a un `;` después
del `while`). Un `do-while` sin `;` no compila, así que no se ejecuta.

</details>

---

**12. [Abierta]** Para cada regla, elige la estructura repetitiva y la sentencia (`break`, `continue` o
ninguna) y justifícala:

- (1) Mostrar las 6 cuotas de un pago.
- (2) Cobrar la multa día a día y dejar de cobrar al llegar al tope.
- (3) Recorrer los turnos de una agenda sin facturar los de control.

_RA: RA-5, RA-9_

<details>
<summary>🔑 Ver respuesta modelo</summary>

(1) `for` sin `break` ni `continue`: se sabe cuántas veces se repite. (2) `for` con `break`: se recorre un
rango de días y se detiene al alcanzar el tope. (3) `for` con `continue`: se recorren todos los turnos y
solo se omite la facturación de los de control.

</details>
