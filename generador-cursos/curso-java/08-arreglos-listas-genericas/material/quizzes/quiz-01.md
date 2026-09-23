# ❓ Quiz 01 — Arreglos, Listas y Clases Genéricas (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿por qué conviene usar una estructura de datos en vez de variables
independientes cuando se tienen varios valores relacionados del mismo tipo?

- **A.** Porque siempre reduce la cantidad de líneas de código.
- **B.** Porque el programa escala mejor cuando la cantidad de datos crece.
- **C.** Porque las variables independientes no compilan.
- **D.** No hay ninguna ventaja real.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La ventaja aparece cuando la cantidad de datos crece: no hace falta declarar
una variable nueva por cada uno.

</details>

**2. [Selección]** **Pregunta:** ¿cuál de estas líneas asigna valores a un arreglo con un literal?

- **A.** `int[] edades = new int[3];`
- **B.** `int[] edades = {34, 41, 29};`
- **C.** `edades[0] = 34;`
- **D.** `int edades = 34;`

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un literal entre llaves asigna todos los valores del arreglo en la misma
línea de la declaración.

</details>

**3. [Selección múltiple]** **Pregunta:** dado `int[] numeros = {10, 20, 30};`, ¿cuáles afirmaciones son
verdaderas?

- **A.** `numeros[0]` vale `10`.
- **B.** `numeros[3]` es un acceso válido.
- **C.** Acceder a `numeros[3]` compila sin problema.
- **D.** Acceder a `numeros[3]` termina el programa con una excepción real al ejecutarse.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: el último índice válido es `2` (`.length - 1`);
`numeros[3]` está fuera de rango, aunque compile.

</details>

**4. [Selección]** **Pregunta:** ¿qué devuelve `.length` sobre un arreglo?

- **A.** El último valor del arreglo.
- **B.** La cantidad de elementos del arreglo.
- **C.** El primer valor del arreglo.
- **D.** Un valor que cambia si se agregan elementos.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `.length` es un atributo fijo que devuelve la cantidad de elementos del
arreglo, desde su creación.

</details>

**5. [Abierta]** ¿Qué diferencia hay entre recorrer un arreglo con un `for` indexado y con un
`for-each`?

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta modelo</summary>

El `for` indexado usa una variable de índice (`i`) y `.length` como límite, dando acceso a la posición
de cada elemento. El `for-each` recorre los elementos directamente, sin exponer un índice; es más simple
cuando solo se necesita leer cada valor.

</details>

**6. [Selección]** **Pregunta:** dado `int[][] matriz`, ¿cómo se accede al elemento de la fila `1`,
columna `2`?

- **A.** `matriz[2][1]`.
- **B.** `matriz[1][2]`.
- **C.** `matriz[1, 2]`.
- **D.** `matriz.get(1, 2)`.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El primer índice es la fila, el segundo la columna: `matriz[fila][columna]`.

</details>

**7. [Abierta]** ¿Por qué conviene el framework de colecciones frente a un arreglo cuando la cantidad
de elementos cambia en tiempo de ejecución?

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque un arreglo tiene tamaño fijo desde su creación: no se puede agrandar ni achicar. Una `List` (y el
framework de colecciones en general) tiene tamaño dinámico: se pueden agregar o quitar elementos en
tiempo de ejecución sin declarar una nueva estructura.

</details>

**8. [Selección]** **Pregunta:** ¿qué interfaz implementan `ArrayList` y `LinkedList` en la jerarquía
del framework de colecciones?

- **A.** `Set`.
- **B.** `Map`.
- **C.** `List`.
- **D.** `Collection`, sin ninguna interfaz intermedia.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** Ambas son implementaciones distintas de la interfaz `List`.

</details>

**9. [Selección]** **Pregunta:** dado `List<String> lista` con tres elementos, ¿qué hace
`lista.remove(1)`?

- **A.** Elimina el elemento en la posición `1` y los siguientes se recorren una posición hacia atrás.
- **B.** Elimina el último elemento de la lista.
- **C.** Elimina el elemento cuyo valor es `1`.
- **D.** No compila: `List` no tiene el método `remove`.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** `remove(indice)` elimina el elemento en esa posición; los elementos
siguientes recorren una posición hacia atrás, y `size()` disminuye en 1.

</details>

**10. [Abierta]** ¿Por qué el acceso por índice (`get(indice)`) es eficiente en un `ArrayList`?

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque `ArrayList` está respaldada internamente por un arreglo: acceder a una posición es tan directo
como acceder a esa misma posición en el arreglo interno, sin tener que recorrer los elementos
anteriores.

</details>

**11. [Selección]** **Pregunta:** ¿qué hace eficiente a `LinkedList` para insertar o eliminar un
elemento al principio o al final?

- **A.** Que está respaldada por un arreglo interno que crece automáticamente.
- **B.** Que sus elementos están enlazados entre sí, sin necesidad de desplazar los demás elementos.
- **C.** Que ordena sus elementos automáticamente.
- **D.** Que no permite acceder por índice.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Cada elemento de una `LinkedList` "conoce" al siguiente (y al anterior);
insertar o eliminar en un extremo solo cambia esos enlaces, sin desplazar el resto de los elementos.

</details>

**12. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `ArrayList` y `LinkedList` son
verdaderas?

- **A.** Ambas implementan la interfaz `List`.
- **B.** `ArrayList` es más eficiente para acceder por índice.
- **C.** `LinkedList` es más eficiente para insertar o eliminar en los extremos.
- **D.** Solo `ArrayList` ofrece el método `size()`.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: `size()` es parte de la interfaz `List`, así que ambas
lo ofrecen.

</details>

**13. [Selección múltiple]** **Pregunta:** MediSalud necesita guardar los signos vitales de un paciente,
tomados cada minuto durante una hora fija (60 valores, cantidad que nunca cambia). ¿cuáles estructuras
son una elección razonable?

- **A.** Un arreglo `double[60]`.
- **B.** Un `ArrayList<Double>`.
- **C.** Un `LinkedList<Double>`, porque siempre es más eficiente que un arreglo.
- **D.** Tres variables `double` sueltas.

_RA: RA-12, RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** Como la cantidad de valores es fija y conocida de antemano, un arreglo
es la opción más simple; un `ArrayList` también funciona, aunque no aprovecha esa ventaja. C es falsa:
`LinkedList` no es "siempre" más eficiente, depende del patrón de uso (aquí no hay inserciones en los
extremos). D no escala: 60 variables sueltas es inmanejable.

</details>

**14. [Abierta]** ¿Qué problema resuelve una clase genérica frente a escribir una clase distinta por
cada tipo que necesita guardar?

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Evita duplicar la misma lógica (los mismos atributos y métodos) una vez por cada tipo concreto. Una sola
clase genérica, con un parámetro de tipo, sirve para cualquier tipo de referencia, y el compilador
verifica que cada instancia use consistentemente su propio tipo.

</details>

**15. [Selección]** **Pregunta:** ¿cómo se declara un parámetro de tipo en una clase?

- **A.** `class Caja<T> { ... }`.
- **B.** `class Caja(T) { ... }`.
- **C.** `class Caja[T] { ... }`.
- **D.** `class Caja { T tipo; }`.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** El parámetro de tipo se declara entre `< >`, justo después del nombre de la
clase.

</details>

**16. [Selección múltiple]** **Pregunta:** dado `class Caja<T>` con `getContenido()`/`setContenido(T)`,
¿cuáles afirmaciones son verdaderas?

- **A.** `Caja<String>` y `Caja<Integer>` pueden convivir en el mismo programa.
- **B.** Una vez instanciada como `Caja<String>`, `setContenido(...)` solo acepta `String`.
- **C.** `Caja<int>` es una declaración válida.
- **D.** `getContenido()` de una `Caja<Integer>` devuelve `Integer`, sin necesidad de *cast*.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: el argumento de tipo genérico debe ser un tipo de
referencia (`Caja<Integer>`, no `Caja<int>`), igual que con `List`.

</details>

**17. [Selección múltiple]** **Pregunta:** ¿cuáles de estos errores **no** los detecta el panel Problems
antes de ejecutar el programa?

- **A.** Acceder a un índice de arreglo fuera de rango.
- **B.** Acceder a un índice de `List` fuera de rango con `get(...)`.
- **C.** Invocar `.length()` sobre una `List`.
- **D.** Pasar un tipo incompatible a `setContenido(T)` de una clase genérica.

_RA: RA-16_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** Ambos son errores de **ejecución** (excepciones reales sin capturar);
C y D son errores de **compilación**, que el panel sí detecta antes de ejecutar.

</details>

**18. [Selección]** **Pregunta:** ¿qué ocurre al declarar `List<int> numeros`?

- **A.** Compila y funciona igual que `List<Integer>`.
- **B.** No compila: el argumento de tipo genérico debe ser un tipo de referencia, no un primitivo.
- **C.** Compila, pero lanza una excepción al ejecutar.
- **D.** Solo falla si se le agrega algún elemento.

_RA: RA-16_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Los argumentos de tipo genérico deben ser tipos de referencia; `int` es
primitivo. Se usa `Integer` (su clase envolvente) en su lugar.

</details>
