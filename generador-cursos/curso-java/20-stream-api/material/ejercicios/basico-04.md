# 🟢 Básico 04 — Identificar código que Stream API simplificaría (anyMatch)

## 🧩 Problema

La Biblioteca Universitaria verifica si existe un libro de cierto autor con este código:

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

        boolean hayCervantes = false;
        for (LibroCatalogo libro : libros) {
            if (libro.getAutor().equals("Cervantes")) {
                hayCervantes = true;
                break;
            }
        }
        System.out.println("¿Hay algun libro de Cervantes? " + hayCervantes);
    }
}
```

Sin escribir código, responde: ¿qué operador de Stream API podría reemplazar la bandera booleana
(`hayCervantes`) y el bucle con `break`, y por qué encaja?

## 📏 Criterios de evaluación de la solución

- Identifica que el bucle solo necesita un resultado `true`/`false` (si existe al menos un libro con
  ese autor), no la lista de coincidencias.
- Explica que `anyMatch` con un `Predicate<LibroCatalogo>` encaja exactamente en esa forma.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: usar el operador `anyMatch`.
