# 🟢 Básico 06 — Identificar código que Stream API simplificaría (conversión)

## 🧩 Problema

La Biblioteca Universitaria filtra los libros con más de 300 páginas y arma una lista con sus títulos
con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final int paginas;

    public LibroCatalogo(String titulo, int paginas) {
        this.titulo = titulo;
        this.paginas = paginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPaginas() {
        return paginas;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<LibroCatalogo> libros = new ArrayList<>();
        libros.add(new LibroCatalogo("El Quijote", 863));
        libros.add(new LibroCatalogo("Rayuela", 635));
        libros.add(new LibroCatalogo("El Principito", 96));

        List<String> titulosExtensos = new ArrayList<>();
        for (LibroCatalogo libro : libros) {
            if (libro.getPaginas() > 300) {
                titulosExtensos.add(libro.getTitulo());
            }
        }

        System.out.println("Libros con mas de 300 paginas:");
        for (String titulo : titulosExtensos) {
            System.out.println("- " + titulo);
        }
    }
}
```

Sin escribir código, responde: ¿qué combinación de operadores y qué método de conversión final podrían
reemplazar el bucle `for` con `if` y la lista acumuladora manual (`titulosExtensos`)?

## 📏 Criterios de evaluación de la solución

- Identifica que hace falta seleccionar (condición sobre `paginas`) y transformar (a `titulo`) antes de
  convertir el resultado de vuelta a `List<String>`.
- Explica que `.stream().filter(...).map(...).toList()` encaja exactamente en esa forma.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-10**: usar la conversión de `List` a `Stream` y de vuelta a `List`.
