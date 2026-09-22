# ❓ Quiz 01 — Encapsulamiento (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿cuál de estas opciones describe mejor el encapsulamiento?

- **A.** Ocultar el estado interno de un objeto y controlar cómo se accede a él y se modifica.
- **B.** Declarar todos los atributos como `static`.
- **C.** Evitar declarar constructores.
- **D.** Usar solo atributos públicos para que el código sea más simple.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** El encapsulamiento oculta el estado interno de un objeto y controla su
acceso, en vez de exponerlo directamente.

</details>

---

**2. [Abierta]** ¿Cuál es una ventaja concreta de encapsular un atributo numérico que puede recibir un
valor sin sentido de negocio (por ejemplo, una edad negativa)?

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Un atributo `private` con un `set` validado puede rechazar el valor inválido antes de asignarlo,
dejando el atributo con un valor coherente. Un atributo público no tiene forma de impedir esa
asignación.

</details>

---

**3. [Selección]** Una clase declara `private String nombreCompleto;` y
`public String getNombreCompleto() { return nombreCompleto; }`. **Pregunta:** ¿cómo se lee el nombre
desde otra clase?

- **A.** `objeto.nombreCompleto`
- **B.** `objeto.getNombreCompleto()`
- **C.** `nombreCompleto`
- **D.** No se puede leer desde otra clase.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El atributo es `private`; se lee a través de su método `get`.

</details>

---

**4. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre un método `set` que valida son
verdaderas?

- **A.** Puede rechazar un valor inválido sin detener el programa.
- **B.** Siempre asigna el valor recibido, lo valide o no.
- **C.** Puede dejar el atributo con su valor anterior si el nuevo no es válido.
- **D.** Es un método público, aunque el atributo que modifica sea privado.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: un `set` que valida puede rechazar un valor inválido sin
asignarlo.

</details>

---

**5. [Selección]** Una clase declara `private String dato;` y otra clase, del **mismo paquete**,
intenta acceder con `objeto.dato = "x";`. **Pregunta:** ¿qué ocurre?

- **A.** Compila, porque es el mismo paquete.
- **B.** No compila: `private` solo permite el acceso desde la misma clase.
- **C.** Compila solo si `dato` es `final`.
- **D.** No compila, pero solo en tiempo de ejecución.

_RA: RA-2, RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `private` es el modificador más restrictivo: ni el mismo paquete alcanza
para acceder a él directamente.

</details>

---

**6. [Selección]** Un atributo se declara sin ningún modificador (acceso por defecto). **Pregunta:**
¿qué clases pueden acceder a él directamente?

- **A.** Solo la misma clase.
- **B.** Cualquier clase del mismo paquete.
- **C.** Cualquier clase del proyecto, sin importar el paquete.
- **D.** Ninguna clase, ni siquiera la propia.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El acceso por defecto permite el acceso desde cualquier clase del mismo
paquete, pero no desde otro.

</details>

---

**7. [Abierta]** Sin usar herencia, ¿en qué se diferencia el alcance de `protected` del alcance del
acceso por defecto?

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Sin herencia, no se diferencian: los dos permiten el acceso desde cualquier clase del mismo paquete y lo
rechazan desde otro paquete. La diferencia real de `protected` (acceso también desde una subclase de
otro paquete) solo aparece cuando hay herencia de por medio.

</details>

---

**8. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `public` son verdaderas?

- **A.** Es accesible desde cualquier clase, de cualquier paquete.
- **B.** Es más restrictivo que `private`.
- **C.** Es el modificador habitual para los métodos `get`/`set`.
- **D.** Declarar todos los atributos como `public` es una buena práctica de encapsulamiento.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** B es falsa: `public` es el menos restrictivo, no el más. D es falsa:
un atributo público renuncia al control que el encapsulamiento busca dar.

</details>

---

**9. [Selección múltiple]** Una clase tiene un dato que solo ella debe poder modificar, y otro dato que
cualquier parte del sistema debe poder leer y mostrar. **Pregunta:** ¿qué modificadores elegirías para
cada uno, respectivamente?

- **A.** `private` para el primero, `public` para el segundo.
- **B.** `public` para los dos.
- **C.** Por defecto para el primero, `private` para el segundo.
- **D.** `private` para los dos.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** El dato exclusivo de la clase va `private`; el que debe verse desde
cualquier parte va `public`.

</details>

---

**10. [Abierta]** Una clase tiene un atributo `private int cupo` con un `set` validado que rechaza
valores negativos. Explica por qué esto es más seguro que declarar `cupo` como `public`, dando un
ejemplo de un valor inválido que el `set` rechazaría.

_RA: RA-9, RA-10_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Con `cupo` público, cualquier código podría asignarle un valor sin sentido, como `-5`, y nada lo
impediría. Con `cupo` privado y un `set` validado, `setCupo(-5)` detecta que el valor es inválido, no lo
asigna, deja el valor anterior y puede avisar por consola, protegiendo el estado del objeto.

</details>

---

**11. [Selección]** Una clase tiene el atributo `private boolean activo`. **Pregunta:** ¿cómo se llama
su método `get`, según la convención de Java?

- **A.** `getActivo()`
- **B.** `isActivo()`
- **C.** `activo()`
- **D.** `obtenerActivo()`

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La convención de Java usa `isNombre()` para el `get` de un atributo
`boolean`.

</details>

---

**12. [Selección múltiple]** **Pregunta:** ¿cuáles de estas situaciones son errores frecuentes de esta
etapa del curso?

- **A.** Acceder a un atributo `private` directamente desde otra clase.
- **B.** Asignar un atributo directamente en vez de con su `set` validado, saltándose la validación.
- **C.** Declarar un método `get` que devuelve el valor de un atributo `private`.
- **D.** Declarar un constructor que use un `set` para inicializar un atributo validado.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** C y D son prácticas correctas, no errores.

</details>
