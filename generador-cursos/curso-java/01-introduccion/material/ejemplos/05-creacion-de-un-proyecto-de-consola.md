# 💡 Ejemplo 05 — Creación de un proyecto de consola

## 🌍 Contexto

Un **proyecto** es la carpeta donde guardás todo lo de un programa: el código, la
configuración y los archivos que genera al compilar. Un **proyecto de consola** es un
programa que se comunica por texto: muestra sus resultados en una ventana de texto (la
*consola*), sin ventanas ni botones.

En VS Code, un proyecto es simplemente una **carpeta**. La extensión de Java sabe crear
una carpeta con la estructura correcta. Usamos la opción **No build tools**
("trabajar con el código fuente directamente, sin herramientas de construcción"), la
más simple, que es la que corresponde a este módulo.

**Qué busca demostrar este ejemplo**: que crear un proyecto de consola es un
procedimiento corto y repetible, y que los nombres del proyecto, del paquete y de la
clase principal siguen reglas que evitan errores desde el primer minuto.

## 🏥 Caso de estudio

Vas a crear el proyecto **BienvenidaMediSalud**: el programa que, al ejecutarse, muestra
en pantalla el mensaje de bienvenida de la red de clínicas. Será tu primer programa
Java.

## 🪜 Pasos para crear el proyecto

1. Abrí VS Code y abrí la paleta de comandos con `Ctrl+Shift+P` (`Cmd+Shift+P` en
   macOS).
2. Escribí **Java: Create Java Project...** y presioná `Enter`.
3. Elegí la opción **No build tools**.
4. VS Code muestra el cuadro **Select the project location**: elegí la carpeta donde
   guardás tus trabajos.
5. Escribí el nombre en el cuadro **Input a Java project name**: `BienvenidaMediSalud`, y
   presioná `Enter`.
6. VS Code abre la carpeta nueva. Si pregunta si confiás en los autores de los archivos
   de la carpeta, elegí **Yes, I trust the authors**: es tu propia carpeta.
7. En la vista **Explorer** (`Ctrl+Shift+E`) vas a ver la estructura que creó la
   extensión. Incluye un archivo de ejemplo, `src/App.java`, que no vamos a usar.
8. Creá el **paquete**: clic derecho sobre la carpeta `src`, elegí **New Folder...** y
   escribí `com/medisalud`. VS Code crea las dos carpetas, una dentro de la otra (puede
   mostrarlas juntas como `com/medisalud`).
9. Creá la **clase**: clic derecho sobre la carpeta `medisalud`, elegí **New File...** y
   escribí `BienvenidaMediSalud.java`. La extensión de Java completa el archivo con la
   declaración del paquete y la estructura de la clase.
10. Borrá el archivo de ejemplo: clic derecho sobre `src/App.java` y **Delete**.
11. Completá el método `main` (ver más abajo) y guardá con `Ctrl+S`.

> 📝 Los comandos de la extensión de Java aparecen en inglés aunque VS Code esté en
> español. Los menús propios de VS Code (`New Folder...`, `New File...`, `Delete`)
> pueden aparecer traducidos ("Nueva carpeta...", "Nuevo archivo...", "Eliminar").

## 🔍 Reglas para los nombres

| Nombre propuesto | ¿Adecuado? | Motivo |
|---|---|---|
| `BienvenidaMediSalud` | ✅ Sí | Empieza con letra, sin espacios ni tildes, en `PascalCase`. |
| `Bienvenida MediSalud` | ❌ No | Tiene un espacio: no es un nombre válido de clase Java. |
| `1erProyecto` | ❌ No | Empieza con un número. |
| `BienvenidaClínica` | ⚠️ Evitar | La tilde puede causar problemas de codificación; en el curso usamos nombres sin tildes. |

El **paquete** se escribe en minúsculas y separado por puntos (`com.medisalud`). Es como
una carpeta que agrupa las clases relacionadas, y coincide con las carpetas
`src/com/medisalud`.

## 🌳 Árbol de archivos (como se vería en VS Code)

Así se ve el **Explorer** justo después de crear el proyecto (paso 7):

```text
BienvenidaMediSalud
├── .vscode
│   └── settings.json
├── lib
├── src
│   └── App.java
└── README.md
```

Y así queda después de los pasos 8 a 10:

```text
BienvenidaMediSalud
├── .vscode
│   └── settings.json
├── lib
├── src
│   └── com
│       └── medisalud
│           └── BienvenidaMediSalud.java
└── README.md
```

Tu código va en **src**. La carpeta `bin` (el resultado de compilar) aparece más adelante,
cuando la extensión compila tu código; el detalle de cada carpeta se explica en el
Ejemplo 07.

## 💻 Archivo: BienvenidaMediSalud.java

Al crear el archivo (paso 9), la extensión lo completa con el paquete y la clase:

```java
package com.medisalud;

public class BienvenidaMediSalud {

}
```

Dentro de las llaves de la clase, escribí `main` y elegí el atajo `main` de la lista
(con `Tab` o `Enter`): VS Code escribe por vos el método `main`. Dentro de sus llaves
escribí `sysout`, elegí el atajo `sysout` y completá el texto entre comillas. Repetí
`sysout` para la segunda línea. El resultado final es:

```java
package com.medisalud;

public class BienvenidaMediSalud {

    public static void main(String[] args) {
        System.out.println("Bienvenido a MediSalud");
        System.out.println("Su salud, nuestra prioridad.");
    }
}
```

Guardá el archivo con `Ctrl+S`. En el Ejemplo 06 lo ejecutamos.

## 🧭 Explicación paso a paso

1. `package com.medisalud;` declara a qué paquete pertenece la clase. Tiene que
   coincidir con la carpeta donde está el archivo (`src/com/medisalud`).
2. `public class BienvenidaMediSalud` declara la clase principal. Su nombre **debe
   coincidir** con el nombre del archivo (`BienvenidaMediSalud.java`); si no, el
   compilador da error.
3. `public static void main(String[] args)` es el **punto donde empieza el programa**:
   cuando lo ejecutás, Java busca este `main` y ejecuta, de arriba hacia abajo, las
   instrucciones que están dentro de sus llaves `{ }`. Por ahora copialo tal cual; sus
   palabras se explican cuando lleguemos a clases y métodos.
4. `System.out.println("...")` muestra un texto en la consola y salta a una línea nueva.
   Cada instrucción termina con **punto y coma** (`;`).
5. Los atajos `main` y `sysout` son plantillas del editor: escriben el código repetitivo
   por vos. El resultado es código Java normal.
6. Desde Java 25 existen formas simplificadas de escribir `main` para programas muy
   pequeños. En este curso usamos la forma clásica de arriba porque es la que verás en
   el código profesional.

## ✅ Resultado esperado

Tras los pasos, el proyecto **BienvenidaMediSalud** aparece en el **Explorer** con el
segundo árbol de arriba, y la clase principal contiene las dos instrucciones
`System.out.println`. Al ejecutarlo (Ejemplo 06), la consola muestra:

```text
Bienvenido a MediSalud
Su salud, nuestra prioridad.
```

## ❓ Preguntas de repaso

**1. [Selección]** ¿Cuál de estos es un nombre válido para la clase principal (y, por lo
tanto, para su archivo)?

- **A.** `Citas Medicas`
- **B.** `2CitasMedicas`
- **C.** `CitasMedicas`
- **D.** `Citas-Médicas`

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C**. Los nombres no llevan espacios (A), no empiezan con número
(B) ni llevan guiones ni tildes (D).

</details>

**2. [Selección múltiple]** Seleccioná **todas** las afirmaciones correctas sobre la
clase principal.

- **A.** Su nombre debe coincidir con el nombre del archivo `.java`.
- **B.** Contiene el método `main`, donde empieza el programa.
- **C.** Cada instrucción dentro de `main` termina con punto y coma.
- **D.** Debe estar escrita siempre en mayúsculas.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A, B y C**. La D es falsa: el nombre de la clase se escribe en
`PascalCase`, no todo en mayúsculas.

</details>

**3. [Abierta]** Vas a crear el proyecto de consola para el sistema de préstamos de la
Biblioteca Universitaria. Nombrá el proyecto y el paquete que usarías, y explicá por
qué.

<details>
<summary>🔑 Ver respuesta modelo</summary>

**Respuesta modelo**: Proyecto `PrestamosBiblioteca` (o similar) y paquete
`com.biblioteca`. El proyecto va en `PascalCase`, sin espacios ni tildes, y el paquete
en minúsculas separado por puntos, para agrupar las clases del sistema. La clase
principal se llamaría, por ejemplo, `com.biblioteca.PrestamosBiblioteca`, y su archivo
estaría en `src/com/biblioteca/PrestamosBiblioteca.java`.

</details>
