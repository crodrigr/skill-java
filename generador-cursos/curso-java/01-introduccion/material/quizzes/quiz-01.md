# ❓ Quiz 01 — Introducción (formato entrevista técnica)

Este quiz simula las primeras preguntas que podrías recibir en una entrevista técnica
para un puesto de programador Java junior. Cada pregunta indica su tipo
(**Selección**, **Selección múltiple** o **Abierta**). Respondé primero por tu cuenta
y después abrí "Ver respuesta" para comparar.

---

**1. [Selección]** Un entrevistador te pregunta: "¿Cuál de las siguientes es una
característica de Java?"

- **A.** Cada programa debe reescribirse para cada sistema operativo.
- **B.** El compilador revisa los tipos de los datos antes de que el programa se ejecute.
- **C.** El programador debe liberar manualmente la memoria que ya no usa.
- **D.** Solo puede realizar una tarea a la vez.

_RA: RA-1, RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**

Java es de tipado estático: el compilador revisa los tipos antes de ejecutar. Java es
portable (A es falsa), tiene recolector de basura (C es falsa) y es multihilo (D es
falsa).

</details>

---

**2. [Selección]** Escribiste `SaludoClinica.java` y ejecutás `javac
SaludoClinica.java` sin errores. **Pregunta:** ¿qué archivo aparece en la carpeta?

- **A.** `SaludoClinica.class`, con el *bytecode*.
- **B.** `SaludoClinica.exe`, con el programa listo para Windows.
- **C.** `SaludoClinica.jvm`, con la máquina virtual.
- **D.** Ninguno: `javac` solo muestra un mensaje.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A**

El compilador traduce el código fuente a *bytecode* y lo guarda en un archivo `.class`
con el mismo nombre de la clase.

</details>

---

**3. [Abierta]** "Explicame por qué el mismo archivo `.class` puede ejecutarse en
Windows, macOS y Linux."

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta modelo</summary>

**Respuesta modelo**: Porque el archivo `.class` no contiene instrucciones para un
sistema operativo concreto, sino *bytecode*, un formato común. Cada sistema tiene su
propia JVM, que sabe leer ese *bytecode* y ejecutarlo con las instrucciones de ese
sistema. Por eso se compila una vez y se ejecuta en cualquier equipo que tenga una JVM.

</details>

---

**4. [Selección múltiple]** Te muestran tres siglas y te piden: "Seleccioná **todas**
las afirmaciones correctas."

- **A.** El JDK incluye al JRE.
- **B.** El JRE incluye a la JVM.
- **C.** La JVM incluye al JDK.
- **D.** Para compilar programas se necesita el JDK.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A, B y D**

La relación es JDK ⊃ JRE ⊃ JVM. La C invierte la relación. El compilador `javac` es
una herramienta de desarrollo y solo viene en el JDK.

</details>

---

**5. [Selección]** Una sala de la biblioteca solo ejecuta un programa ya compilado, y
en el equipo de sistemas se escribe código nuevo. **Pregunta:** ¿qué necesita
instalar el equipo de sistemas que no necesita la sala?

- **A.** La JVM.
- **B.** Las bibliotecas estándar de Java.
- **C.** Las herramientas de desarrollo, como `javac` (el JDK).
- **D.** Nada: ambos necesitan lo mismo.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C**

Ejecutar solo requiere el entorno de ejecución (JVM y bibliotecas estándar). Para
desarrollar hace falta además el compilador y las demás herramientas, que vienen en el
JDK.

</details>

---

**6. [Selección]** Instalaste el JDK y abriste una terminal nueva. Ejecutás los dos
comandos de verificación y obtenés:

- `java -version` muestra `openjdk version "25"`.
- `javac -version` responde `command not found`.

**Pregunta:** ¿qué conclusión es correcta?

- **A.** El entorno está completo: `javac` no hace falta para compilar.
- **B.** Hay una JVM, pero falta el compilador: no se instaló el JDK completo.
- **C.** Hay que instalar Visual Studio Code para que aparezca `javac`.
- **D.** La JVM está dañada y debe reinstalarse.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**

`java` prueba que existe una JVM, pero el compilador `javac` solo viene en el JDK. Hay
que instalar el JDK completo (o corregir el `PATH` si sí está instalado).

</details>

---

**7. [Selección múltiple]** Ejecutás un proyecto de consola en VS Code y aparece el
cuadro "Build failed, do you want to continue?". El panel **Problems** muestra:

- El archivo abierto es `Ficha.java`.
- Una línea con `✖ Syntax error, insert ";" to complete BlockStatements Java(1610612976)
  [Ln 5, Col 31]`.

**Pregunta:** seleccioná **todas** las afirmaciones correctas.

- **A.** Conviene elegir `Continue` para ver qué pasa.
- **B.** El compilador detectó el problema en la línea 5, columna 31 de `Ficha.java`.
- **C.** El mensaje indica que falta un punto y coma.
- **D.** Hay que corregir el código y volver a ejecutar.

_RA: RA-6, RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B, C y D**

Con un error de compilación no conviene continuar (la A es falsa): se corrige y se vuelve
a ejecutar. El mensaje dice qué pasa (`insert ";"`), y `[Ln 5, Col 31]` indica dónde.

</details>

---

**8. [Selección]** Te muestran un proyecto de consola de VS Code y te preguntan: "¿En cuál
de estas partes empieza a ejecutarse el programa?"

- **A.** En la línea `package`.
- **B.** En el método `main` de la clase principal.
- **C.** En la carpeta `bin`.
- **D.** En la carpeta `lib`.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**

El método `main` es el punto donde empieza el programa. El paquete solo agrupa clases,
`bin` guarda el resultado de compilar y `lib` es para bibliotecas externas.

</details>

---

**9. [Selección]** El sistema de facturación de **MediSalud** tiene estos dos datos:

- La tasa de IVA aplicada a las facturas, que **no debe cambiar** durante el programa.
- La edad de un paciente, que **sí puede cambiar**.

**Pregunta:** ¿qué par de declaraciones es el correcto?

- **A.** `double TASA_IVA = 0.19;` y `final int edadPaciente = 34;`
- **B.** `final double TASA_IVA = 0.19;` y `int edadPaciente = 34;`
- **C.** `final double tasa iva = 0.19;` y `int edadPaciente = 34;`
- **D.** `const double TASA_IVA = 0.19;` y `int edadPaciente = 34;`

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**

La constante lleva `final` y la variable no. La A invierte los roles, la C tiene
espacios en el nombre y la D usa `const`, que no se utiliza para declarar constantes en
Java.

</details>

---

**10. [Selección múltiple]** Te muestran varias afirmaciones sobre los tipos primitivos y
te piden: "Seleccioná **todas** las correctas."

- **A.** Un `long` escrito en el código lleva la letra `L` al final cuando el número no
  cabe en un `int`.
- **B.** Un `char` se escribe entre comillas simples.
- **C.** Un `float` puede recibir el valor `59.9` sin ninguna letra adicional.
- **D.** `boolean` solo admite los valores `true` y `false`.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A, B y D**

La C es falsa: `59.9` es un `double`; para un `float` se escribe `59.9f`.

</details>

---

**11. [Abierta]** "Estás modelando un libro de la Biblioteca Universitaria. Indicá qué
tipo primitivo usarías para: el ISBN de 13 dígitos, la disponibilidad (sí o no), el
precio y los días de préstamo, y justificá el ISBN."

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta modelo</summary>

**Respuesta modelo**: ISBN: `long`, porque tiene 13 dígitos y no cabe en un `int`
(máximo de unos 2 147 millones), y se escribe con `L`. Disponibilidad: `boolean`
(`true` o `false`). Precio: `double` (o `float` si se acepta menos precisión). Días de
préstamo: `int`, o `byte` si se sabe que el valor es pequeño (entre −128 y 127).

</details>

---

**12. [Selección]** Tenés la variable `double precioLibro = 59.9;` y querés mostrar
`Precio: 59.90` con **dos decimales** y saltar a una línea nueva.

**Pregunta:** ¿qué instrucción produce ese resultado?

- **A.** `System.out.println("Precio: %.2f", precioLibro);`
- **B.** `System.out.printf("Precio: %.2f%n", precioLibro);`
- **C.** `System.out.printf("Precio: %d%n", precioLibro);`
- **D.** `System.out.print("Precio: %.2f", "precioLibro");`

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**

`printf` usa el marcador `%.2f` para un decimal con dos cifras y `%n` para el salto de
línea. La A usa `println`, que no interpreta marcadores; la C usa `%d`, que es para
enteros y falla al ejecutar con un `double`; la D pasa el nombre de la variable como
texto.

</details>
