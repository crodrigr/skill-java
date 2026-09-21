# 💡 Ejemplo 10 — Impresión en consola

## 🌍 Contexto

La **consola** es la ventana donde un programa de este tipo muestra sus resultados. Ya
usaste `System.out.println` en los ejemplos anteriores; ahora vas a conocer sus dos
compañeras y a controlar el formato de lo que se muestra:

- **`System.out.print`**: muestra el texto y **se queda en la misma línea**.
- **`System.out.println`**: muestra el texto y **salta a una línea nueva**.
- **`System.out.printf`**: muestra un texto con **marcadores de formato** que se
  reemplazan por los valores que le pasás (por ejemplo, un decimal con dos cifras).

**Qué busca demostrar este ejemplo**: cómo mostrar texto y valores de variables de forma
legible, eligiendo entre `print`, `println` y `printf`, y usando secuencias de escape
para controlar saltos de línea y tabulaciones.

## 📚 Caso de estudio

La **Biblioteca Universitaria** quiere imprimir la ficha de un libro con su título, ISBN,
páginas, precio y disponibilidad. Vas a mostrar los mismos datos de tres maneras
distintas y a comparar el resultado.

## 🔍 Las herramientas de impresión

**Unir texto y valores con `+`.** Dentro de `print` o `println`, el signo `+` **une** un
texto con el valor de una variable para mostrarlos juntos: `"ISBN: " + isbn` produce
`ISBN: 9780307474728`. En este curso lo usamos solo para eso.

**Marcadores de formato de `printf`:**

| Marcador | Qué muestra | Ejemplo |
|---|---|---|
| `%s` | Texto (sirve para cualquier valor) | `%s` con `"Ana"` → `Ana` |
| `%d` | Un número **entero** (`byte`, `short`, `int`, `long`) | `%d` con `471` → `471` |
| `%.2f` | Un número **decimal** con 2 cifras decimales (`float`, `double`) | `%.2f` con `59.9` → `59.90` |
| `%n` | Un salto de línea | — |

**Secuencias de escape** (se escriben dentro de un texto):

| Secuencia | Qué hace |
|---|---|
| `\n` | Salto de línea |
| `\t` | Tabulación (un espacio ancho) |
| `\"` | Muestra una comilla doble dentro del texto |

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
FichaLibro
└── src
    └── com
        └── biblioteca
            └── FichaLibro.java
```

## 💻 Archivo: FichaLibro.java

```java
package com.biblioteca;

public class FichaLibro {

    public static void main(String[] args) {
        String tituloLibro = "Cien años de soledad";
        long isbn = 9780307474728L;
        short numeroPaginas = 471;
        double precioLibro = 59.9;
        boolean disponible = true;

        System.out.print("Título: ");
        System.out.println(tituloLibro);
        System.out.println("ISBN: " + isbn);
        System.out.println("Páginas: " + numeroPaginas);
        System.out.printf("Precio: %.2f%n", precioLibro);
        System.out.printf("%s tiene %d páginas y está disponible: %s%n", tituloLibro, numeroPaginas, disponible);
        System.out.println("Código\tCategoría");
        System.out.println("N-001\tNarrativa");
        System.out.println("Libro: \"Cien años de soledad\"");
        System.out.println("Fin de la ficha\nGracias por usar la biblioteca");
    }
}
```

## ✅ Resultado esperado

```text
Título: Cien años de soledad
ISBN: 9780307474728
Páginas: 471
Precio: 59.90
Cien años de soledad tiene 471 páginas y está disponible: true
Código	Categoría
N-001	Narrativa
Libro: "Cien años de soledad"
Fin de la ficha
Gracias por usar la biblioteca
```

> ⚠️ **Separador decimal.** La línea `Precio:` la genera `printf`, que respeta la
> **configuración regional** del computador. En un equipo configurado en español lo
> más habitual es ver una **coma** (`59,90`) en lugar de un punto. Es normal y no es un
> error. Con la configuración regional `es-CO` el resultado es este:
>
> ```text
> Título: Cien años de soledad
> ISBN: 9780307474728
> Páginas: 471
> Precio: 59,90
> Cien años de soledad tiene 471 páginas y está disponible: true
> Código	Categoría
> N-001	Narrativa
> Libro: "Cien años de soledad"
> Fin de la ficha
> Gracias por usar la biblioteca
> ```

Nota: los decimales que se unen con `+` (como en `"Precio: " + precioLibro`) se muestran
siempre con punto (`59.9`), sin importar la configuración regional.

## 🧭 Explicación paso a paso

1. `System.out.print("Título: ")` muestra el texto **sin** saltar de línea, y la
   siguiente instrucción, `println(tituloLibro)`, escribe el título justo a continuación.
   Por eso ambas quedan en la misma línea.
2. `"ISBN: " + isbn` y `"Páginas: " + numeroPaginas` unen una etiqueta con el valor de
   una variable. Java convierte el número en texto para poder mostrarlo.
3. `printf("Precio: %.2f%n", precioLibro)` reemplaza `%.2f` por el valor de
   `precioLibro` con **dos decimales**. El `%n` al final salta de línea: `printf`,
   a diferencia de `println`, **no** salta de línea por sí solo.
4. En `printf("%s tiene %d páginas y está disponible: %s%n", ...)` cada marcador se
   reemplaza, **en orden**, por cada valor que sigue al texto: `tituloLibro`,
   `numeroPaginas` y `disponible`.
5. `\t` deja una tabulación entre `Código` y `Categoría`, y entre `N-001` y `Narrativa`,
   que alinea las dos líneas como si fueran una tabla.
6. `\"` permite mostrar comillas dobles dentro de un texto que ya está encerrado entre
   comillas dobles.
7. `\n` dentro de un `println` parte el texto en dos líneas con una sola instrucción.

## 🧩 Error frecuente: un marcador que no corresponde al valor

```java
System.out.printf("Precio: %d%n", precioLibro);
```

`%d` es para enteros, pero `precioLibro` es un `double`. Este error **no** lo detecta el
compilador (el editor no subraya nada): aparece **al ejecutar**, y el programa se detiene
en esa línea, con un mensaje en la terminal:

```text
Precio: Exception in thread "main" java.util.IllegalFormatConversionException: d != java.lang.Double
	at ... (el resto del detalle técnico se omite)
```

`d != java.lang.Double` significa "el marcador `%d` (entero) no coincide con un valor
`Double`". Corregilo usando `%.2f`.

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué diferencia hay entre `System.out.print` y `System.out.println`?

- **A.** `print` muestra números y `println` muestra texto.
- **B.** `println` salta a una línea nueva después de mostrar, `print` no.
- **C.** `print` muestra el texto con dos decimales.
- **D.** No hay diferencia.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**. `println` agrega un salto de línea al final; `print` deja el
cursor en la misma línea.

</details>

**2. [Selección múltiple]** Seleccioná **todas** las afirmaciones correctas sobre
`printf`.

- **A.** `%d` se usa para mostrar un número entero.
- **B.** `%.2f` muestra un decimal con dos cifras decimales.
- **C.** `%n` salta de línea.
- **D.** `printf` salta de línea automáticamente al final, igual que `println`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A, B y C**. La D es falsa: `printf` no salta de línea por sí solo;
hay que escribir `%n`.

</details>

**3. [Abierta]** Querés mostrar en la consola dos líneas con una sola instrucción
`println`: `Ficha completa` y `Gracias por usar la biblioteca`. Escribí la instrucción y
explicá qué hace la secuencia de escape que usaste.

<details>
<summary>🔑 Ver respuesta modelo</summary>

**Respuesta modelo**: `System.out.println("Ficha completa\nGracias por usar la
biblioteca");`. La secuencia `\n` es un salto de línea: hace que lo que sigue se
muestre en una línea nueva.

</details>
