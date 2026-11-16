# 🟢 Básico 03 — Identificar código que Stream API simplificaría (filter)

## 🧩 Problema

La Biblioteca Universitaria selecciona los libros disponibles con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final boolean disponible;

    public LibroCatalogo(String titulo, boolean disponible) {
        this.titulo = titulo;
        this.disponible = disponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponible() {
        return disponible;
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
        libros.add(new LibroCatalogo("El Quijote", true));
        libros.add(new LibroCatalogo("Rayuela", false));

        List<LibroCatalogo> disponibles = new ArrayList<>();
        for (LibroCatalogo libro : libros) {
            if (libro.isDisponible()) {
                disponibles.add(libro);
            }
        }

        System.out.println("Libros disponibles:");
        for (LibroCatalogo libro : disponibles) {
            System.out.println("- " + libro.getTitulo());
        }
    }
}
```

Sin escribir código, responde: ¿qué operador de Stream API podría reemplazar el bucle que arma
`disponibles`, y por qué encaja?

## 📏 Criterios de evaluación de la solución

- Identifica que el bucle selecciona los libros que cumplen una condición (`isDisponible()`), pudiendo
  reducir la cantidad de elementos.
- Explica que `filter` con un `Predicate<LibroCatalogo>` encaja exactamente en esa forma.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-7**: usar el operador `filter`.
