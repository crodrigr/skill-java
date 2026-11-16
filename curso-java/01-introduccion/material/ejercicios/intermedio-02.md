# 🟡 Intermedio 02 — Elegir el tipo primitivo

## 🧩 Problema

La **Biblioteca Universitaria** va a guardar los datos básicos de cada libro y de cada
préstamo. Tu tarea es elegir el tipo primitivo más adecuado para cada dato, justificar
la elección y escribir las declaraciones.

## 💻 Código o contexto de partida

**Parte 1.** Para cada dato, elegí un tipo primitivo y justificá en una frase por qué:

| N.º | Dato | Valor de ejemplo | Identificador sugerido |
|---|---|---|---|
| 1 | ISBN del libro (13 dígitos) | `9780307474728` | `isbn` |
| 2 | Año de publicación | `1967` | `anioPublicacion` |
| 3 | Número de páginas | `471` | `numeroPaginas` |
| 4 | Precio del libro | `59.9` | `precioLibro` |
| 5 | Categoría (una sola letra: `N`, narrativa) | `N` | `codigoCategoria` |
| 6 | ¿Está disponible? (sí o no) | `sí` | `disponible` |
| 7 | Días de préstamo (entre 1 y 30) | `7` | `diasPrestamo` |
| 8 | Código del usuario | `20240123` | `codigoUsuario` |

**Parte 2.** Completá el código de partida. Ya compila, pero todavía no declara nada.
Declará las ocho variables con los valores de la tabla y mostralas con `println`:

```java
package com.biblioteca;

public class DatosLibro {

    public static void main(String[] args) {
        // TODO: declarar aquí las ocho variables, cada una con su tipo y su valor
    }
}
```

## 📏 Criterios de evaluación de la solución

- Cada tipo puede contener el valor de ejemplo: el ISBN necesita `long` (y la `L`), y el
  número de categoría es un `char` con comillas simples.
- Las justificaciones se apoyan en el rango del tipo o en la naturaleza del dato (por
  ejemplo, "un ISBN de 13 dígitos no cabe en un `int`").
- Se aceptan alternativas razonables donde varios tipos sirven (por ejemplo, `int` para
  el año o para los días de préstamo), siempre que estén justificadas.
- El programa compila y muestra los ocho valores, con los sufijos y las comillas
  correctos.

## 🚧 Restricciones

- Usá solo tipos primitivos (más el valor de texto si lo necesitás); no uses constantes
  para este ejercicio.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

RA-10
