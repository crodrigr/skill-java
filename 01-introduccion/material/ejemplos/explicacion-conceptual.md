# 📚 Explicación conceptual — Módulo 1

## 🧠 Concepto: ¿Qué es Java?

Java es un lenguaje de programación de propósito general, presentado en 1995 y
mantenido hoy como un proyecto abierto. Sirve para escribir instrucciones que un
computador ejecuta y se usa en sistemas empresariales, servicios web y herramientas de
desarrollo, entre otros. En este curso usamos Java 25, una versión de soporte a largo
plazo (LTS).

Sus características principales son:

- **Portable**: el mismo programa compilado corre en distintos sistemas operativos.
- **Orientado a objetos**: el programa se organiza en objetos que representan cosas
  del negocio.
- **De tipado estático**: cada dato tiene un tipo y el compilador lo revisa antes de
  ejecutar.
- **Con gestión automática de memoria**: el recolector de basura libera lo que ya no
  se usa.
- **Seguro** y **multihilo**: verifica el código antes de ejecutarlo y puede hacer
  varias tareas a la vez.

📎 Ver en la práctica: [Ejemplo 01 — ¿Qué es Java y cuáles son sus características?](01-que-es-java-y-sus-caracteristicas.md)

Un programa Java pasa por tres etapas, y cada una produce algo distinto:

- **Escribir**: el programador crea un archivo de texto `.java` con el código fuente.
- **Compilar**: el compilador `javac` revisa el código y genera un archivo `.class`
  con *bytecode*.
- **Ejecutar**: la JVM lee el *bytecode* y lo ejecuta en el computador donde esté
  instalada.

Como el *bytecode* es igual en todos los sistemas y cada sistema tiene su propia JVM,
el mismo archivo `.class` sirve en Windows, macOS y Linux.

📎 Ver en la práctica: [Ejemplo 02 — ¿Cómo funciona Java?](02-como-funciona-java.md)

La plataforma Java se distribuye en tres capas que se incluyen unas en otras
(JDK ⊃ JRE ⊃ JVM):

- **JVM**: ejecuta el *bytecode*, administra la memoria y optimiza el código mientras
  corre.
- **JRE**: la JVM más las bibliotecas estándar; sirve para **ejecutar** programas.
- **JDK**: el JRE más las herramientas de desarrollo (`javac`, `jar`, `javadoc`,
  `jshell`); sirve para **desarrollar** programas.

Hoy las distribuciones suelen entregar el JDK completo, por lo que el JDK alcanza
para todo lo que hacemos en el curso.

📎 Ver en la práctica: [Ejemplo 03 — JDK, JRE y JVM](03-jdk-jre-y-jvm.md)

## 🧠 Concepto: Configuración del entorno de desarrollo

Un entorno de desarrollo es el conjunto de herramientas que permite escribir,
compilar y ejecutar programas. En este curso se compone de tres piezas que se instalan
en orden:

- **JDK 25** (Eclipse Temurin): aporta el compilador `javac` y la JVM.
- **Visual Studio Code**: el editor de código, con explorador de archivos, terminal
  integrada y paleta de comandos.
- **Extension Pack for Java**: la extensión que le enseña a VS Code a revisar, ejecutar
  y depurar programas Java; usa el JDK por debajo.

Cada pieza se verifica antes de pasar a la siguiente:

- En la terminal, `java -version` debe mostrar la versión **25** y `javac -version`
  debe mostrar `javac 25`.
- `code --version` confirma que VS Code está instalado.
- En VS Code, el comando **Java: Configure Java Runtime** debe listar un JDK **25**.
- Si `java` funciona pero `javac` no, solo hay un entorno de ejecución, sin
  herramientas de desarrollo.

📎 Ver en la práctica: [Ejemplo 04 — Configuración del entorno de desarrollo](04-configuracion-del-entorno.md)

## 🧠 Concepto: Creación de proyectos (Visual Studio Code)

Un proyecto es la carpeta donde guardás todo lo de un programa. Un proyecto de consola
es un programa que muestra sus resultados como texto. Se crea con el comando **Java:
Create Java Project...** y la opción **No build tools**.

Los pasos para crearlo son:

- Abrir la paleta de comandos (`Ctrl+Shift+P`), ejecutar **Java: Create Java
  Project...** y elegir **No build tools**.
- Elegir la carpeta donde se guarda y escribir el nombre del proyecto (`PascalCase`, sin
  espacios ni tildes).
- Crear dentro de `src` la carpeta del paquete (`com/medisalud`) y un archivo `.java`
  con el nombre de la clase principal; la extensión completa el paquete y la clase.
- Escribir el método `main` con el atajo `main` y las impresiones con el atajo `sysout`.

Para ejecutarlo y leer el resultado:

- Ejecutar con el enlace **Run** que aparece sobre `main` (o el botón **Run Java**).
- La salida del programa aparece en la terminal integrada.
- Si el compilador encuentra un error, lo subraya en rojo y lo lista en el panel
  **Problems** (`Ctrl+Shift+M`) con el mensaje, un código `Java(nnn)` y la posición
  `[Ln, Col]`; rojo es un error, amarillo una advertencia y azul información.
- Ante el cuadro "Build failed, do you want to continue?" no se elige `Continue`: se
  corrige el error y se vuelve a ejecutar.

📎 Ver en la práctica: [Ejemplo 05 — Creación de un proyecto de consola](05-creacion-de-un-proyecto-de-consola.md)

📎 Ver en la práctica: [Ejemplo 06 — Ejecución de un proyecto](06-ejecucion-de-un-proyecto.md)

Un proyecto de consola tiene una estructura que conviene reconocer:

- **`src`**: la carpeta donde vive tu código, organizado en carpetas que forman paquetes.
- **Paquete**: agrupa clases relacionadas (por ejemplo, `com.medisalud`).
- **Clase principal**: contiene el método `main`; su nombre debe coincidir con el del
  archivo `.java`.
- **Método `main`**: el punto donde empieza el programa.
- **`bin`**: el resultado de compilar (archivos `.class` con *bytecode*), que no se edita.
- **`lib`** y **`.vscode/settings.json`**: las bibliotecas externas y la configuración
  del proyecto.

📎 Ver en la práctica: [Ejemplo 07 — Estructura de un proyecto de consola](07-estructura-de-un-proyecto-de-consola.md)

## 🧠 Concepto: Variables y constantes en Java

Un programa guarda los datos que usa en espacios de memoria con nombre y tipo. Hay dos
clases:

- **Variable**: su valor puede cambiar mientras el programa se ejecuta
  (`int edadPaciente = 34;`).
- **Constante**: su valor no cambia una vez asignado; se declara con `final`
  (`final String NOMBRE_CLINICA = "MediSalud";`).

Las reglas y convenciones para los nombres son:

- Empiezan con una letra, sin espacios, y no pueden ser palabras reservadas.
- Las variables usan `camelCase` (`edadPaciente`) y las constantes
  `MAYUSCULAS_CON_GUION_BAJO` (`NOMBRE_CLINICA`).
- Se evitan las tildes y la `ñ` en los nombres.

📎 Ver en la práctica: [Ejemplo 08 — Variables y constantes](08-variables-y-constantes.md)

Java tiene ocho tipos primitivos, agrupados en cuatro familias:

- **Enteros**: `byte` (8 bits), `short` (16), `int` (32) y `long` (64).
- **Decimales**: `float` (32 bits) y `double` (64).
- **Un carácter**: `char`, escrito con comillas simples (`'O'`).
- **Lógico**: `boolean`, con los valores `true` y `false`.

Al escribir valores hay que respetar sufijos y comillas: `L` para un `long`
(`9876543210L`), `f` para un `float` (`59.9f`), comillas simples para un `char` y
comillas dobles para un texto. Para elegir, se usa `int` y `double` por defecto, y se
pasa a `long` cuando el valor no cabe en un `int`.

📎 Ver en la práctica: [Ejemplo 09 — Datos primitivos](09-datos-primitivos.md)

Para mostrar datos en la consola hay tres instrucciones:

- **`System.out.print`**: muestra el texto y se queda en la misma línea.
- **`System.out.println`**: muestra el texto y salta a una línea nueva; con `+` se
  puede unir un texto con el valor de una variable (`"ISBN: " + isbn`).
- **`System.out.printf`**: muestra un texto con marcadores de formato: `%s` (texto),
  `%d` (entero), `%.2f` (decimal con dos cifras) y `%n` (salto de línea).

Las secuencias de escape controlan el texto: `\n` (salto de línea), `\t` (tabulación) y
`\"` (comilla doble). El separador decimal de `printf` depende de la configuración
regional del computador.

📎 Ver en la práctica: [Ejemplo 10 — Impresión en consola](10-impresion-en-consola.md)
