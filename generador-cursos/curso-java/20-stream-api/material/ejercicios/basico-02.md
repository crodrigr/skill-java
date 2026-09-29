# 🟢 Básico 02 — Identificar código que Stream API simplificaría (map)

## 🧩 Problema

La Biblioteca Universitaria arma las líneas de su catálogo con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final String autor;

    public LibroCatalogo(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
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
        libros.add(new LibroCatalogo("El Quijote", "Cervantes"));
        libros.add(new LibroCatalogo("Rayuela", "Cortazar"));

        List<String> lineasCatalogo = new ArrayList<>();
        for (LibroCatalogo libro : libros) {
            lineasCatalogo.add(libro.getTitulo() + " - " + libro.getAutor());
        }

        System.out.println("Catalogo:");
        for (String linea : lineasCatalogo) {
            System.out.println("- " + linea);
        }
    }
}
```

Sin escribir código, responde: ¿qué operador de Stream API podría reemplazar el bucle que arma
`lineasCatalogo`, y por qué encaja?

## 📏 Criterios de evaluación de la solución

- Identifica que el bucle transforma cada `LibroCatalogo` en un `String`, manteniendo la misma
  cantidad de elementos.
- Explica que `map` con una `Function<LibroCatalogo, String>` encaja exactamente en esa forma.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-6**: usar el operador `map`.
