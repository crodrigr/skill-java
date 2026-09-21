# 💡 Ejemplo 06 — Ejecución de un proyecto

## 🌍 Contexto

Escribir el código es solo la mitad del trabajo: falta **ejecutarlo** y **mirar el
resultado**. En VS Code, ejecutar hace por vos las dos etapas del Ejemplo 02: **compila**
tu código y, si no hay errores, lo **ejecuta** en la JVM. El resultado aparece en la
**terminal integrada** del editor.

Mientras escribís, la extensión de Java revisa tu código todo el tiempo y **subraya con
una línea ondulada** lo que encuentra. Los problemas se listan en el panel **Problems**.
Aprender a leer esos mensajes es una habilidad clave: los errores son normales y casi
siempre dicen qué pasó y dónde.

**Qué busca demostrar este ejemplo**: cómo se ejecuta un proyecto, dónde se ve su
salida, y cómo se lee un error de compilación simple para corregirlo.

## 🏥 Caso de estudio

Seguimos con el proyecto **BienvenidaMediSalud** del Ejemplo 05. Primero lo ejecutás
con el código correcto y después provocás un error a propósito (olvidar un punto y
coma) para practicar la lectura del mensaje.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
BienvenidaMediSalud
└── src
    └── com
        └── medisalud
            └── BienvenidaMediSalud.java
```

## 💻 Archivo: BienvenidaMediSalud.java

<details>
<summary>📄 Ver código completo de BienvenidaMediSalud.java (creado en el Ejemplo 05)</summary>

```java
package com.medisalud;

public class BienvenidaMediSalud {

    public static void main(String[] args) {
        System.out.println("Bienvenido a MediSalud");
        System.out.println("Su salud, nuestra prioridad.");
    }
}
```

</details>

## 🪜 Ejecutar el proyecto

1. Abrí el archivo `BienvenidaMediSalud.java` en el editor.
2. Encima de la línea `public static void main` aparecen dos enlaces pequeños: **Run**
   y **Debug**. Hacé clic en **Run**.
3. VS Code abre la **terminal integrada** (panel inferior) y muestra ahí el resultado.

Otras formas de ejecutar lo mismo:

- El botón **Run Java** (un triángulo ▷) en la esquina superior derecha del editor.
- La tecla `F5` (o **Run → Start Debugging**), que ejecuta el programa en modo de
  depuración.

Si el panel inferior no se abre, hacelo desde el menú **Terminal → New Terminal** (en
español: *Terminal → Nuevo terminal*) o con `` Ctrl+` ``.

## ✅ Resultado esperado

En la terminal aparece la salida de tu programa:

```text
Bienvenido a MediSalud
Su salud, nuestra prioridad.
```

Antes de esas líneas, VS Code escribe en la terminal la orden con la que lanzó tu
programa: una línea larga que empieza con la ruta de `java` y termina con el nombre
completo de tu clase, `com.medisalud.BienvenidaMediSalud`. Su texto exacto cambia según
tu sistema operativo; ignorala. **Tu salida es lo que aparece después.**

## 🧩 Leer un error de compilación

Ahora borrá el `;` al final de la primera instrucción `println`:

```java
System.out.println("Bienvenido a MediSalud")
```

Enseguida el editor **subraya en rojo** el final de la línea. De estas tres formas podés
ver el mensaje:

- Poniendo el mouse sobre el subrayado.
- Abriendo el panel **Problems** con `Ctrl+Shift+M` (menú **View → Problems**, en español
  *Ver → Problemas*).
- Mirando el número que aparece junto al ícono de errores en la barra inferior.

El panel muestra una línea como esta:

```text
✖ Syntax error, insert ";" to complete BlockStatements Java(1610612976) [Ln 6, Col 52]
```

Si probás ejecutar con el error, VS Code muestra un cuadro con el texto **"Build failed,
do you want to continue?"** y tres botones (`Continue`, `Always Continue` y `Fix...`), y
abre el panel **Problems**. **No elijas `Continue` ni `Always Continue`**: podrías
ejecutar una versión vieja del programa o recibir un error confuso al ejecutar. Cerrá el
cuadro, corregí el error y volvé a ejecutar.

## 🔍 Los tres colores del editor

| Color | Ícono | Qué significa | ¿Impide ejecutar? |
|---|---|---|---|
| Rojo | ✖ | **Error**: el código no compila | Sí |
| Amarillo | ⚠ | **Advertencia**: algo raro pero válido (por ejemplo, una variable que declaraste y no usás) | No |
| Azul | ℹ | **Información**: por ejemplo, los comentarios `TODO` | No |

## 🧭 Explicación paso a paso

1. **`Syntax error, insert ";" to complete BlockStatements`**: el **qué**. Dice que el
   compilador esperaba un punto y coma para terminar la instrucción.
2. **`Java(1610612976)`**: un **código** interno del error. Sirve para buscarlo en
   internet; no necesitás memorizarlo.
3. **`[Ln 6, Col 52]`**: el **dónde**: línea 6, columna 52 (justo donde debía ir el `;`).
4. Hacé clic sobre el mensaje del panel y el editor te lleva a esa línea.
5. **Corregir**: agregá el `;` al final de la línea 6, guardá y volvé a ejecutar. El
   subrayado desaparece y la salida vuelve a ser la del resultado esperado.
6. Con más de un error, **corregí primero el de arriba**: a veces uno solo provoca otros
   en cadena.
7. **Dos herramientas, dos mensajes**: VS Code revisa tu código con el compilador de
   Eclipse, y `javac` (el de la terminal, Ejemplo 02) usa otras palabras para el mismo
   problema. Ejemplo con el punto y coma faltante:

| Herramienta | Mensaje |
|---|---|
| VS Code (panel Problems) | `Syntax error, insert ";" to complete BlockStatements` |
| `javac` (terminal) | `BienvenidaMediSalud.java:6: error: ';' expected` |

Los dos dicen lo mismo. En este curso vas a leer, sobre todo, los del panel Problems.

## ❓ Preguntas de repaso

**1. [Selección]** En el panel Problems ves `[Ln 9, Col 20]` junto a un error.
**Pregunta:** ¿qué indica?

- **A.** Que hay 9 errores en total.
- **B.** La línea 9 y la columna 20 donde se detectó el problema.
- **C.** La versión de Java.
- **D.** El número de veces que se ejecutó el programa.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**. `Ln` es la línea y `Col` la columna donde el compilador
detectó el problema.

</details>

**2. [Selección múltiple]** Seleccioná **todas** las afirmaciones correctas sobre la
ejecución de un proyecto en VS Code.

- **A.** Ejecutar compila primero y ejecuta después.
- **B.** La salida del programa aparece en la terminal integrada.
- **C.** Una línea subrayada en amarillo es un error que impide ejecutar.
- **D.** Los errores se listan en el panel **Problems**.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A, B y D**. La C es falsa: el amarillo es una advertencia y no
impide ejecutar; el rojo es el que indica un error de compilación.

</details>

**3. [Abierta]** Al ejecutar tu proyecto, la terminal no muestra ninguno de tus
mensajes. Nombrá dos cosas que revisarías.

<details>
<summary>🔑 Ver respuesta modelo</summary>

**Respuesta modelo**: 1) Que los mensajes estén escritos con `System.out.println(...)`
dentro del método `main` (si las instrucciones quedaron fuera de `main` o comentadas con
`//`, no se ejecutan). 2) Que haya subrayados rojos o errores en el panel **Problems**,
y que hayas guardado el archivo; y que estés mirando la terminal del proyecto y no otra.

</details>
