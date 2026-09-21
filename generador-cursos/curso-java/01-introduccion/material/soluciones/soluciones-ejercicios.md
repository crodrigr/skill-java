# 🔑 Soluciones — Ejercicios Módulo 1

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la
subsección "Soluciones" de `specs/01-introduccion.md`).

## 🟢 Básico 01 — Características de Java y etapas de ejecución

**Parte A1**

| N.º | Respuesta | Justificación |
|---|---|---|
| 1 | Falsa | El *bytecode* es portable: no hace falta recompilar para otro sistema. |
| 2 | Verdadera | Java es de tipado estático; el compilador revisa los tipos. |
| 3 | Falsa | El recolector de basura libera la memoria automáticamente. |
| 4 | Falsa | Se usa también en servicios web, sistemas empresariales y más. |
| 5 | Verdadera | Java es multihilo. |
| 6 | Falsa | El `.class` contiene *bytecode*, no el código fuente. |

**Parte A2** (respuesta modelo; se aceptan otras si la característica justifica de
verdad la elección)

- MediSalud: sistema de agenda de citas usado en recepción, farmacia y dirección. Se
  justifica por la **portabilidad**: el mismo programa corre en Windows, Linux y macOS.
- Biblioteca Universitaria: sistema de préstamos que atiende a muchos usuarios a la
  vez. Se justifica por el **multihilo**: puede atender varias solicitudes en
  simultáneo.

**Parte B1**: `C, E, B, A, D`.

1. **C**: el programador escribe `SaludoClinica.java`.
2. **E**: `javac` compila y genera `SaludoClinica.class`.
3. **B**: se ejecuta `java SaludoClinica`.
4. **A**: la JVM lee el *bytecode* y lo ejecuta.
5. **D**: aparece el saludo en la consola.

**Parte B2**: el error del punto y coma se detecta en la acción **E** (compilación con
`javac`), porque el compilador revisa el código antes de generar el *bytecode*. Si hay
errores, se detiene y no genera el archivo `.class`, por lo que nunca se llega a la
ejecución.

## 🟢 Básico 02 — ¿JDK, JRE o JVM?

**Parte 1**

| N.º | Pieza | Capa más pequeña que la contiene |
|---|---|---|
| 1 | El compilador `javac` | JDK |
| 2 | Las bibliotecas estándar de Java | JRE |
| 3 | El componente que ejecuta el *bytecode* | JVM |
| 4 | El recolector de basura | JVM |
| 5 | La herramienta `javadoc` | JDK |
| 6 | La herramienta `jshell` | JDK |

**Parte 2**

- **a.** Como mínimo, un entorno de ejecución: el JRE (JVM más bibliotecas estándar).
  En la práctica, muchas distribuciones solo ofrecen el JDK, que también sirve.
- **b.** El JDK, porque necesitan compilar y usar las herramientas de desarrollo.
- **c.** Porque el JDK incluye al JRE y este a la JVM (JDK ⊃ JRE ⊃ JVM): instalar el
  JDK deja disponible todo lo necesario para desarrollar y ejecutar.

## 🟢 Básico 03 — Verificar el entorno

| Computador | ¿Listo? | Diagnóstico y corrección |
|---|---|---|
| 1 | Sí | `java` muestra la versión 25 y `javac` responde `javac 25`: hay JVM y compilador de la versión exigida. |
| 2 | No | Hay una JVM 25, pero `javac` no existe: se instaló solo un entorno de ejecución. Instalar el JDK completo (`temurin-25-jdk`). |
| 3 | No | Ni `java` ni `javac` se encuentran. O no hay ningún JDK instalado, o la terminal no lo encuentra (terminal anterior a la instalación, o falta agregarlo al `PATH`). Abrir una terminal nueva; si persiste, instalar el JDK 25 o corregir el `PATH`. |
| 4 | No | El entorno está completo (JVM y compilador), pero es la versión 17. Instalar el JDK 25 y ponerlo primero en el `PATH` (o en `JAVA_HOME`), y volver a verificar. |

## 🟡 Intermedio 01 — Bienvenida de la clínica

**Solución propuesta** (`BienvenidaClinica.java`, verificada con Temurin 25):

```java
package com.medisalud;

public class BienvenidaClinica {

    public static void main(String[] args) {
        System.out.println("Clínica MediSalud - Sede Centro");
        System.out.println("Atención: lunes a viernes de 7:00 a 19:00");
        System.out.println("Gracias por elegirnos");
    }
}
```

**Salida real al ejecutar**:

```text
Clínica MediSalud - Sede Centro
Atención: lunes a viernes de 7:00 a 19:00
Gracias por elegirnos
```

**Paso 4** (error a propósito): al quitar el `;` de la segunda instrucción, el panel
**Problems** muestra:

```text
✖ Syntax error, insert ";" to complete Statement Java(1610612976) [Ln 7, Col 71]
```

Es decir, en la línea 7 (la segunda instrucción) y la columna 71 (el final de la línea) se
esperaba un punto y coma. Al agregarlo y volver a ejecutar, la salida vuelve a ser la de
arriba. Además, mientras los tres `TODO` seguían como comentarios, el panel los listaba
como mensajes de información (ícono azul).

## 🟢 Básico 04 — Rotular las partes de un proyecto

**Parte 1**

| Elemento | Letra |
|---|---|
| a. El proyecto | A |
| b. La carpeta de código fuente | E |
| c. La carpeta del paquete `com.medisalud` | F |
| d. El archivo de la clase principal | G |
| e. La carpeta con los archivos `.class` | C |
| f. La carpeta de bibliotecas externas | D |

(La letra B es la carpeta `.vscode`, con la configuración del proyecto.)

**Parte 2**

| Elemento | Línea |
|---|---|
| a. Declaración del paquete | 1 |
| b. Declaración de la clase principal | 3 |
| c. Método `main` | 5 |
| d. Instrucción que muestra un texto | 6 |

**Parte 3**

- **a.** La clase se declara como `Citas`, pero el archivo se llama `CitasMedicas.java`.
  Java exige que una clase pública se llame igual que su archivo (el mensaje dice que el
  tipo público "must be defined in its own file").
- **b.** Dos correcciones válidas: cambiar el nombre de la clase a `CitasMedicas` (para que
  coincida con el archivo) o cambiar el nombre del archivo a `Citas.java` (para que
  coincida con la clase), con clic derecho → **Rename**.

## 🟢 Básico 05 — Nombre y tipo de variables

| N.º | Dato | Identificador | Tipo | ¿Variable o constante? |
|---|---|---|---|---|
| 1 | Nombre del paciente | `nombrePaciente` | `String` (texto) | Variable |
| 2 | Edad del paciente | `edadPaciente` | `int` | Variable |
| 3 | Número de historia clínica | `numeroHistoria` | `long` (no cabe en un `int`; se escribe `9876543210L`) | Variable |
| 4 | Peso en kilogramos | `pesoKg` | `double` | Variable |
| 5 | Grupo sanguíneo | `grupoSanguineo` | `char` (`'O'`, comillas simples) | Variable |
| 6 | ¿Es afiliado? | `esAfiliado` | `boolean` (`true`) | Variable |
| 7 | Nombre de la clínica | `NOMBRE_CLINICA` | `String` (texto) | Constante (`final`) |
| 8 | Valor de la consulta | `VALOR_CONSULTA` | `double` (`85000.0`) | Constante (`final`) |

Los identificadores pueden variar mientras respeten las reglas y las convenciones
(`camelCase` para variables, `MAYUSCULAS_CON_GUION_BAJO` para constantes, sin tildes).

## 🟡 Intermedio 02 — Elegir el tipo primitivo

**Parte 1**

| N.º | Dato | Tipo recomendado | Justificación |
|---|---|---|---|
| 1 | ISBN | `long` | 13 dígitos: supera el máximo de un `int`; se escribe con `L`. |
| 2 | Año de publicación | `short` (también sirve `int`) | 1967 cabe en 16 bits (máximo 32 767). |
| 3 | Número de páginas | `short` (también sirve `int`) | 471 cabe en 16 bits. |
| 4 | Precio | `double` (también se acepta `float` con `f`) | Es un decimal; `double` es el decimal por defecto y más preciso. |
| 5 | Categoría | `char` | Una sola letra, con comillas simples. |
| 6 | ¿Disponible? | `boolean` | Solo hay dos valores posibles. |
| 7 | Días de préstamo | `byte` (también sirve `int`) | Entre 1 y 30: cabe en 8 bits (máximo 127). |
| 8 | Código de usuario | `int` | 20240123 cabe en un `int`. |

**Parte 2** — solución propuesta (`DatosLibro.java`, verificada con Temurin 25):

```java
package com.biblioteca;

public class DatosLibro {

    public static void main(String[] args) {
        long isbn = 9780307474728L;
        short anioPublicacion = 1967;
        short numeroPaginas = 471;
        double precioLibro = 59.9;
        char codigoCategoria = 'N';
        boolean disponible = true;
        byte diasPrestamo = 7;
        int codigoUsuario = 20240123;

        System.out.println(isbn);
        System.out.println(anioPublicacion);
        System.out.println(numeroPaginas);
        System.out.println(precioLibro);
        System.out.println(codigoCategoria);
        System.out.println(disponible);
        System.out.println(diasPrestamo);
        System.out.println(codigoUsuario);
    }
}
```

**Salida real al ejecutar**:

```text
9780307474728
1967
471
59.9
N
true
7
20240123
```

## 🔴 Avanzado 01 — Corregir un programa con errores

El compilador informa los errores **en rondas**: primero los de tipos y nombres, y
después, cuando esos ya no están, los de "flujo" (como usar una variable sin valor o
reasignar una constante). Estos son los mensajes reales del panel **Problems** en cada
ronda.

**Ronda 0** — código original (5 errores):

```text
✖ Type mismatch: cannot convert from String to int Java(16777233) [Ln 8, Col 28]
✖ The literal 9876543210 of type int is out of range Java(536871066) [Ln 9, Col 31]
✖ Type mismatch: cannot convert from String to char Java(16777233) [Ln 10, Col 31]
✖ Type mismatch: cannot convert from double to int Java(16777233) [Ln 11, Col 22]
✖ The method printn(String) is undefined for the type PrintStream Java(67108964) [Ln 15, Col 20]
```

| Error | Causa | Corrección |
|---|---|---|
| `int edadPaciente = "34";` | Un texto (`"34"`, comillas dobles) no se puede guardar en un `int`. | `int edadPaciente = 34;` |
| `long numeroHistoria = 9876543210;` | El número escrito se lee como `int` y "está fuera de rango" (*out of range*). | `9876543210L` |
| `char grupoSanguineo = "O";` | Las comillas dobles crean un texto; un `char` usa comillas simples. | `char grupoSanguineo = 'O';` |
| `int pesoKg = 62.5;` | Un `int` no guarda decimales. | Cambiar el **tipo**: `double pesoKg = 62.5;` |
| `System.out.printn(...)` | `printn` no existe: el método se llama `println`. | `System.out.println(nombrePaciente);` |

**Ronda 1** — tras corregir esos cinco (2 errores nuevos):

```text
✖ The final local variable TASA_IVA cannot be assigned. It must be blank and not using a compound assignment Java(536870970) [Ln 14, Col 9]
✖ The local variable esAfiliado may not have been initialized Java(536870963) [Ln 16, Col 28]
```

| Error | Causa | Corrección |
|---|---|---|
| `TASA_IVA = 0.21;` | `TASA_IVA` es una constante (`final`): no se puede reasignar. | Eliminar la reasignación y conservar la constante. |
| `System.out.println(esAfiliado);` | `esAfiliado` se declaró sin valor y se lee antes de asignarle uno. | `boolean esAfiliado = true;` |

**Ronda 2** — el programa compila; quedan solo **advertencias** (amarillas):

```text
⚠ The value of the local variable TASA_IVA is not used Java(536870973) [Ln 6, Col 22]
⚠ The value of the local variable edadPaciente is not used Java(536870973) [Ln 8, Col 13]
⚠ The value of the local variable numeroHistoria is not used Java(536870973) [Ln 9, Col 14]
⚠ The value of the local variable grupoSanguineo is not used Java(536870973) [Ln 10, Col 14]
⚠ The value of the local variable pesoKg is not used Java(536870973) [Ln 11, Col 16]
```

Son advertencias, no errores: el programa se puede ejecutar. Aparecen porque se
declararon variables (`TASA_IVA`, `edadPaciente`, `numeroHistoria`, `grupoSanguineo` y
`pesoKg`) cuyo valor nunca se usa. Se pueden dejar, usarlas en una impresión, o eliminarlas
si no hacen falta; lo importante es que el estudiante las reconozca como avisos y no como
errores.

Código final:

```java
package com.medisalud;

public class FichaConErrores {

    public static void main(String[] args) {
        final double TASA_IVA = 0.19;
        String nombrePaciente = "Ana Torres";
        int edadPaciente = 34;
        long numeroHistoria = 9876543210L;
        char grupoSanguineo = 'O';
        double pesoKg = 62.5;
        boolean esAfiliado = true;

        System.out.println(nombrePaciente);
        System.out.println(esAfiliado);
    }
}
```

Salida real:

```text
Ana Torres
true
```

Por qué aparecen en rondas: el compilador revisa primero los tipos y los nombres; solo
cuando esos están bien hace el análisis de flujo. Con `javac` en la terminal el orden de
las rondas es distinto (por ejemplo, primero informa solo `integer number too large`),
porque es otra herramienta con otras etapas.

## 🟡 Intermedio 03 — Ficha de un libro

**Solución propuesta** (`FichaBiblioteca.java`, verificada con Temurin 25):

```java
package com.biblioteca;

public class FichaBiblioteca {

    public static void main(String[] args) {
        String tituloLibro = "Don Quijote de la Mancha";
        long isbn = 9788420412146L;
        short numeroPaginas = 1376;
        double precioLibro = 89.5;
        boolean disponible = false;

        System.out.print("Título: ");
        System.out.println(tituloLibro);
        System.out.println("ISBN: " + isbn);
        System.out.printf("Precio: %.2f%n", precioLibro);
        System.out.printf("%s tiene %d páginas%n", tituloLibro, numeroPaginas);
        System.out.println("Ficha completa\nDisponible: " + disponible);
    }
}
```

**Salida real al ejecutar** (configuración regional por defecto del equipo de
verificación):

```text
Título: Don Quijote de la Mancha
ISBN: 9788420412146
Precio: 89.50
Don Quijote de la Mancha tiene 1376 páginas
Ficha completa
Disponible: false
```

Con la configuración regional `es-CO`, la línea del precio se ve `Precio: 89,50`:
`printf` respeta la configuración regional; el resto de la salida no cambia.

Puntos a revisar en las soluciones de los estudiantes:

- TODO 1: `print("Título: ")` seguido de `println(tituloLibro)` deja ambos en la misma
  línea.
- TODO 2: `"ISBN: " + isbn` une el texto con el valor.
- TODO 3: `%n` es necesario porque `printf` no salta de línea por sí solo.
- TODO 4: el orden de los argumentos debe coincidir con el orden de los marcadores
  (`%s` para el título, `%d` para las páginas).
- TODO 5: `\n` dentro del texto produce las dos líneas con una sola instrucción.


## 🏆 Desafío 01 — Carnet de biblioteca con salida exacta

**Solución propuesta** (`CarnetBiblioteca.java`, verificada con Temurin 25):

```java
package com.biblioteca;

public class CarnetBiblioteca {

    public static void main(String[] args) {
        final String NOMBRE_BIBLIOTECA = "BIBLIOTECA UNIVERSITARIA";
        final byte MAX_LIBROS_PRESTAMO = 3;
        final double MULTA_POR_DIA = 1500.0;

        String nombreUsuario = "Valentina Ríos";
        int codigoUsuario = 20240123;
        char tipoUsuario = 'E';
        byte librosPrestados = 2;
        byte diasPrestamo = 7;
        boolean alDia = true;

        System.out.println("*************************************");
        System.out.println(" " + NOMBRE_BIBLIOTECA + " - CARNET");
        System.out.println("*************************************");
        System.out.println("Usuario:     " + nombreUsuario);
        System.out.println("Código:      " + codigoUsuario);
        System.out.println("Tipo:        " + tipoUsuario + " (E = estudiante, D = docente)");
        System.out.printf("Préstamos:   %d de %d libros%n", librosPrestados, MAX_LIBROS_PRESTAMO);
        System.out.println("Plazo:       " + diasPrestamo + " días");
        System.out.printf("Multa/día:   %.2f%n", MULTA_POR_DIA);
        System.out.println("Al día:      " + alDia);
        System.out.println("*************************************");
    }
}
```

**Salida real al ejecutar**:

```text
*************************************
 BIBLIOTECA UNIVERSITARIA - CARNET
*************************************
Usuario:     Valentina Ríos
Código:      20240123
Tipo:        E (E = estudiante, D = docente)
Préstamos:   2 de 3 libros
Plazo:       7 días
Multa/día:   1500.00
Al día:      true
*************************************
```

**Justificación de los tipos** (respuesta modelo):

| Dato | Tipo | Justificación |
|---|---|---|
| `NOMBRE_BIBLIOTECA` | `String` (constante) | Es texto y no cambia. |
| `MAX_LIBROS_PRESTAMO` | `byte` (constante) | El valor `3` es pequeño y cabe en 8 bits; `int` también es válido. |
| `MULTA_POR_DIA` | `double` (constante) | Es un decimal; `double` es el decimal por defecto. |
| `nombreUsuario` | `String` | Es texto. |
| `codigoUsuario` | `int` | `20240123` cabe en un `int` (máximo de unos 2 147 millones). |
| `tipoUsuario` | `char` | Una sola letra, con comillas simples. |
| `librosPrestados` | `byte` | Un valor entre 0 y 3; `int` también es válido. |
| `diasPrestamo` | `byte` | Un valor entre 1 y 30; `int` también es válido. |
| `alDia` | `boolean` | Solo hay dos estados posibles. |

Puntos a revisar: la salida debe coincidir carácter por carácter (espacios tras cada
etiqueta, línea de asteriscos de 37 caracteres, un espacio delante del título);
`printf` necesita `%n` para saltar de línea; y `%d` acepta `byte` (no hace falta
convertir).
