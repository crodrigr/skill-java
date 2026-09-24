# ❓ Quiz 01 — Conjuntos, Mapas, Enumeraciones y Excepciones (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respondela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿por qué conviene usar un `Set` en vez de una `List` para guardar las
especialidades disponibles en una clínica?

- **A.** Porque un `Set` es más rápido en todos los casos.
- **B.** Porque un `Set` garantiza que ninguna especialidad se repita.
- **C.** Porque una `List` no permite guardar `String`.
- **D.** No hay ninguna diferencia real.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un `Set` no permite valores duplicados; una `List` sí, y tendrías que
comprobarlo vos mismo antes de cada `add`.

</details>

**2. [Selección]** **Pregunta:** dado `Set<String> categorias = new HashSet<>();` con `add("Novela")`
llamado dos veces, ¿qué da `categorias.size()`?

- **A.** `0`
- **B.** `1`
- **C.** `2`
- **D.** Lanza una excepción.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El segundo `add` con el mismo valor no agrega nada: el conjunto no crece.

</details>

**3. [Selección múltiple]** **Pregunta:** dados `Set<String> a = {"Novela", "Realismo magico"}` y
`Set<String> b = {"Realismo magico", "Poesia"}`, ¿cuáles afirmaciones son verdaderas?

- **A.** La unión de `a` y `b` tiene 3 elementos.
- **B.** La intersección de `a` y `b` es `{"Realismo magico"}`.
- **C.** La diferencia de `a` menos `b` es `{"Novela"}`.
- **D.** `a.retainAll(b)` modifica `b`.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: `retainAll` modifica el conjunto sobre el que se
invoca (`a` en este caso, o una copia si se quiere preservar el original), nunca el argumento.

</details>

**4. [Selección]** **Pregunta:** ¿cuándo conviene usar un `Map` en vez de un `Set` o una `List`?

- **A.** Cuando cada dato se identifica con una clave única y hay que asociarle un valor.
- **B.** Cuando los datos no deben repetirse, sin necesidad de asociarles nada.
- **C.** Cuando el orden de inserción es lo único importante.
- **D.** Nunca: un `Set` siempre alcanza.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Un `Map` asocia una clave única a un valor; si solo importa evitar
duplicados sin asociar nada, un `Set` alcanza (B describe a `Set`, no a `Map`).

</details>

**5. [Selección]** **Pregunta:** dado un `HashMap<String, Integer> inventario` con `put("Paracetamol",
50)` seguido de `put("Paracetamol", 80)`, ¿qué pasa?

- **A.** Se lanza una excepción por clave repetida.
- **B.** El `Map` termina con dos pares para `"Paracetamol"`.
- **C.** El valor de `"Paracetamol"` queda en `80`; el `Map` no crece.
- **D.** El segundo `put` se ignora; el valor queda en `50`.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** Un `put` con una clave existente **actualiza** el valor asociado a esa
clave.

</details>

**6. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `HashMap` y `TreeMap` son
verdaderas?

- **A.** Ambos implementan las mismas operaciones básicas (`put`, `get`, `containsKey`, etc.).
- **B.** `TreeMap` garantiza que las claves queden ordenadas al recorrerlo; `HashMap` no.
- **C.** Conviene `HashMap` cuando el programa necesita que las claves queden ordenadas.
- **D.** `TreeMap` es siempre más rápido que `HashMap`.

_RA: RA-6, RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** C es falsa: si se necesita orden, conviene `TreeMap`, no `HashMap`. D
es falsa: `TreeMap` no es "siempre más rápido"; mantener el orden tiene su propio costo.

</details>

**7. [Abierta]** **Pregunta:** ¿qué es una enumeración (`enum`) y por qué conviene usarla en vez de
texto suelto (por ejemplo, `String estado = "PROGRAMADA";`) para representar el estado de una cita?

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** un `enum` declara un conjunto cerrado y fijo de valores válidos. A diferencia de
un `String` suelto, el compilador no permite asignarle ningún valor que no esté entre los declarados
(por ejemplo, `EstadoCita.INVALIDO` ni siquiera compila), evitando errores de tipeo o valores
inconsistentes que un `String` sí permitiría.

</details>

**8. [Selección múltiple]** **Pregunta:** dado `enum EstadoPrestamo { ACTIVO, DEVUELTO, VENCIDO }`,
¿cuáles afirmaciones son verdaderas?

- **A.** `EstadoPrestamo.values()` devuelve los tres valores en el orden en que se declararon.
- **B.** `EstadoPrestamo.DEVUELTO.ordinal()` da `1`.
- **C.** Un valor de `EstadoPrestamo` puede usarse como condición de un `switch`.
- **D.** `EstadoPrestamo.ACTIVO.ordinal()` da `1`.

_RA: RA-9, RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: `ACTIVO` es el primer valor declarado, así que su
`ordinal()` es `0`, no `1`.

</details>

**9. [Abierta]** **Pregunta:** ¿qué es una excepción y por qué conviene manejarla con `try`/`catch` en
vez de dejar que el programa termine?

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** una excepción es un error real que ocurre mientras el programa corre (por
ejemplo, pedir una clave inexistente de un `Map` y usar el resultado `null`). Si nadie la maneja, el
programa termina de forma abrupta, sin darle al usuario ninguna oportunidad de continuar o de enterarse
de qué pasó de una forma controlada; capturarla con `try`/`catch` permite responder a ese error sin que
el programa se caiga entero.

</details>

**10. [Selección múltiple]** **Pregunta:** dado un `try` que intenta invocar un método sobre el
resultado `null` de `catalogo.get(codigoInexistente)`, envuelto en
`catch (NullPointerException e)`, ¿cuáles afirmaciones son verdaderas?

- **A.** El programa termina abruptamente, igual que sin el `try`/`catch`.
- **B.** El bloque `catch` se ejecuta.
- **C.** El programa sigue corriendo después del bloque `try`/`catch`.
- **D.** El panel Problems marca ese código como un error antes de ejecutarlo.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B y C.** A es falsa: justamente por el `try`/`catch`, el programa **no**
termina. D es falsa: el panel no detecta estáticamente que ese `get` va a devolver `null`.

</details>

**11. [Selección]** **Pregunta:** si el código dentro de un `try` no lanza ninguna excepción, ¿qué
pasa?

- **A.** El `catch` se ejecuta igual, sin ningún error real.
- **B.** El `catch` no se ejecuta; el programa sigue normalmente.
- **C.** El programa termina con un error de compilación.
- **D.** El `try` se vuelve a ejecutar automáticamente.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El `catch` solo se ejecuta si el `try` lanza una excepción del tipo que
declara.

</details>

**12. [Selección]** **Pregunta:** ¿cuándo se ejecuta el bloque `finally` de un `try`/`catch`/`finally`?

- **A.** Solo si el `try` lanza una excepción.
- **B.** Solo si el `catch` se ejecuta.
- **C.** Siempre, se haya lanzado una excepción capturada o no.
- **D.** Nunca, si el `try` termina sin errores.

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** `finally` se ejecuta siempre: haya o no una excepción, y se haya capturado o
no.

</details>

**13. [Selección]** **Pregunta:** ¿cómo se lanza una excepción propia desde un método de validación?

- **A.** Con `catch (Exception e) { return; }`.
- **B.** Con `throw new MiExcepcion("mensaje");`, donde `MiExcepcion` extiende `RuntimeException`.
- **C.** Devolviendo `null` cuando el dato es inválido.
- **D.** No se puede: solo Java puede lanzar excepciones.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `throw` lanza una instancia de una excepción, propia o de la biblioteca
estándar; una excepción personalizada se declara extendiendo `RuntimeException` (o `Exception`).

</details>

**14. [Selección múltiple]** **Pregunta:** ¿cuáles son errores frecuentes de esta etapa?

- **A.** Declarar un `catch` de un tipo después de otro `catch` que ya lo cubre por jerarquía.
- **B.** Usar una clave de un tipo distinto al declarado en un `Map` (por ejemplo, un `int` en un
  `Map<String, Integer>`).
- **C.** Usar un valor de `enum` que no fue declarado.
- **D.** Declarar un `catch` de un tipo no relacionado con lo que el `try` puede lanzar.

_RA: RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D no es un error real: un `catch` de un tipo `RuntimeException` no
relacionado **compila igual** (queda como código muerto, pero es válido) — el error real es el orden
(A), no la falta de relación entre tipos.

</details>

**15. [Abierta]** **Pregunta:** ¿por qué un `catch (ArithmeticException e)` alrededor de un
`Integer.parseInt("abc")` (que lanza `NumberFormatException`, no `ArithmeticException`) compila sin
problema, mientras que un `catch (NumberFormatException e)` colocado **después** de un
`catch (RuntimeException e)` no compila?

_RA: RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** para excepciones no marcadas (`RuntimeException` y sus subtipos), Java no exige
que el compilador pueda probar que ese tipo exacto se lanza — el primer caso compila, aunque el `catch`
nunca se ejecute en la práctica (código muerto, pero válido). El segundo caso es distinto: como
`NumberFormatException` es subtipo de `RuntimeException`, el primer `catch` (`RuntimeException`) ya
captura cualquier `NumberFormatException` posible, así que el segundo `catch` queda **inalcanzable** —
eso sí es un error real de compilación ("exception ... has already been caught"), verificado con el JDK
real en `research.md`.

</details>
