# ❓ Quiz 01 — Relaciones entre Clases (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿cuál de estos enunciados describe una asociación ("tiene un") en vez
de una herencia ("es un")?

- **A.** "Un `Medico` es un `Empleado`."
- **B.** "Un `Medico` tiene una lista de `Paciente` asignados."
- **C.** "Un `Enfermero` extiende de `Empleado`."
- **D.** "Un `Medico` es un tipo de `Persona`."

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** A, C y D describen la relación "es un" (herencia); B describe "tiene un"
(asociación): `Medico` guarda una referencia a varios `Paciente`, sin heredar de `Paciente`.

</details>

**2. [Selección]** **Pregunta:** en el diagrama `Medico "1" -- "0..*" Paciente`, ¿qué significa `0..*`
del lado de `Paciente`?

- **A.** Un `Medico` tiene exactamente cero pacientes.
- **B.** Un `Medico` puede tener cero o más pacientes asignados.
- **C.** Un `Paciente` puede tener cero o más médicos.
- **D.** Un `Medico` tiene exactamente un paciente.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La multiplicidad `0..*` se lee del lado donde está escrita (`Paciente`) y
describe cuántos objetos de esa clase participan del lado de `Medico`: cero o más.

</details>

**3. [Abierta]** **Pregunta:** ¿cuál es la diferencia entre una multiplicidad `1` y una `0..1` en un
extremo de una relación?

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** `1` significa que ese extremo participa siempre, exactamente una vez (es
obligatorio); `0..1` significa que ese extremo puede participar cero veces (es opcional) o, como mucho,
una vez.

</details>

**4. [Selección]** **Pregunta:** ¿qué caracteriza a una asociación **unidireccional**?

- **A.** Ambas clases se referencian mutuamente.
- **B.** Solo una clase tiene un atributo con una referencia a la otra; la otra no puede navegar de
  vuelta.
- **C.** Una clase crea a la otra dentro de su constructor.
- **D.** Las dos clases heredan de una superclase común.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Es navegable en un solo sentido: por ejemplo, `Libro` conoce a su `Autor`,
pero `Autor` no tiene ningún método para navegar a sus libros.

</details>

**5. [Selección]** **Pregunta:** dado `Medico` con `pacientes` (`List<Paciente>`) y `Paciente` con
`medico` (`Medico`), ¿qué método mantiene consistente esa asociación bidireccional al crear el vínculo?

- **A.** Un método que solo hace `paciente.setMedico(medico)`.
- **B.** Un método que solo hace `medico.getPacientes().add(paciente)`.
- **C.** Un método en `Medico` que hace `pacientes.add(paciente)` **y** `paciente.setMedico(this)` en el
  mismo lugar.
- **D.** No hace falta ningún método: alcanza con declarar los dos atributos.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** Un único método que actualiza los dos extremos a la vez evita que uno quede
desactualizado respecto del otro.

</details>

**6. [Selección múltiple]** **Pregunta:** dado el par `Medico`/`Paciente` del Ejemplo 04, si se crea el
vínculo llamando **solo** a `paciente.setMedico(medico)` (sin pasar por `medico.agregarPaciente(...)`),
¿cuáles afirmaciones son verdaderas?

- **A.** El programa no compila.
- **B.** El programa compila y se ejecuta sin lanzar ninguna excepción.
- **C.** `medico.getPacientes().size()` da `0`, aunque `paciente.getMedico()` devuelva el `medico`
  correcto.
- **D.** El panel Problems de VS Code marca ese código como un error antes de ejecutarlo.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B y C.** El código es válido en Java: compila y se ejecuta sin fallar, pero el
extremo `pacientes` de `Medico` queda vacío porque nunca se actualizó. A y D son falsas: no hay error de
compilación ni de panel, porque no es un error de sintaxis — es un error lógico.

</details>

**7. [Abierta]** **Pregunta:** ¿por qué ni el compilador ni el panel Problems detectan la inconsistencia
de una asociación bidireccional actualizada en un solo extremo?

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** porque el código es sintácticamente correcto — `paciente.setMedico(medico)` es
una llamada válida a un método existente. Ni `javac` ni el servidor de lenguaje analizan si el *diseño*
es consistente, solo si el código compila; el error solo se nota al leer el resultado real de
`medico.getPacientes()`.

</details>

**8. [Abierta]** **Pregunta:** ¿qué es la agregación y en qué se diferencia, a primera vista, de la
composición?

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** la agregación es una relación todo-parte donde la parte existe independiente del
todo y puede compartirse entre varios todos. Se diferencia de la composición en que la parte **no** la
crea el todo: se recibe ya creada (por constructor o por un método), y sigue siendo válida aunque el
todo deje de existir.

</details>

**9. [Selección]** **Pregunta:** ¿cómo recibe el objeto "parte" una clase que lo **agrega**?

- **A.** Lo crea dentro de su propio constructor, sin recibirlo por parámetro.
- **B.** Lo recibe ya creado, por constructor o por un método.
- **C.** No lo recibe: lo busca en una base de datos.
- **D.** Extiende de la clase de la parte.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** En la agregación, el objeto "parte" ya existe antes de que el "todo" lo
reciba; el "todo" nunca lo construye internamente.

</details>

**10. [Selección]** **Pregunta:** ¿qué caracteriza a la composición frente a la agregación?

- **A.** La parte se recibe ya creada, como en la agregación.
- **B.** La parte se crea dentro del constructor del todo, y no tiene sentido fuera de él.
- **C.** La parte puede compartirse entre varios todos.
- **D.** No hay ninguna diferencia real entre ambas.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** En la composición, el todo crea la parte dentro de su propio constructor; la
parte depende por completo del ciclo de vida del todo.

</details>

**11. [Abierta]** **Pregunta:** ¿por qué el código externo no puede construir la `HistoriaClinica` de un
`Paciente` por su cuenta y pasarla al constructor?

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** porque `Paciente` no declara ningún constructor que reciba una `HistoriaClinica`
por parámetro: la crea internamente, dentro de su propio constructor. No existir ese constructor público
es justamente lo que garantiza la composición: la única forma de tener una `HistoriaClinica` es a través
de un `Paciente`.

</details>

**12. [Selección múltiple]** **Pregunta:** dado un escenario donde una clase `Biblioteca` recibe una
lista de objetos `Libro` ya creados (que también podrían pertenecer a otra `Biblioteca` o a ninguna),
¿cuáles afirmaciones son verdaderas?

- **A.** Es una composición: `Biblioteca` crea sus `Libro`.
- **B.** Es una agregación: `Biblioteca` recibe los `Libro` ya creados.
- **C.** Los `Libro` pueden seguir existiendo aunque la `Biblioteca` deje de usarse.
- **D.** Es una asociación bidireccional obligatoria.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B y C.** `Biblioteca` no crea los `Libro`: los recibe ya construidos, así que es
agregación; por eso mismo, un `Libro` puede seguir existiendo (y perteneciendo a otra `Biblioteca`)
aunque esta deje de usarse. A es falsa (no los crea) y D es falsa (nada exige que sea bidireccional).

</details>

**13. [Selección múltiple]** **Pregunta:** ¿cuáles de estos son errores frecuentes al trabajar con
relaciones entre clases?

- **A.** Actualizar solo un extremo de una asociación bidireccional.
- **B.** Recibir el objeto "parte" por parámetro cuando en realidad debía ser una composición.
- **C.** Acceder a un elemento de una colección agregada vacía sin comprobar antes su tamaño.
- **D.** Declarar un atributo de tipo referencia a otra clase.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** Los tres son los errores frecuentes de este módulo. D no es un
error: es, sencillamente, cómo se declara cualquier asociación, agregación o composición en Java.

</details>

**14. [Selección]** **Pregunta:** ¿qué notación Mermaid corresponde a cada relación?

- **A.** `ClassA --> ClassB` es composición; `ClassA *-- ClassB` es asociación.
- **B.** `ClassA --> ClassB` es asociación unidireccional; `ClassA o-- ClassB` es agregación; `ClassA
  *-- ClassB` es composición.
- **C.** Las tres relaciones se dibujan igual: `ClassA -- ClassB`.
- **D.** `ClassA o-- ClassB` es composición; `ClassA *-- ClassB` es agregación.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La flecha simple (`-->`) es asociación unidireccional, el diamante hueco
(`o--`) es agregación y el diamante relleno (`*--`) es composición, siempre del lado del "todo".

</details>
