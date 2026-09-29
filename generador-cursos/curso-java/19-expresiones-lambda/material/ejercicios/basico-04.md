# 🟢 Básico 04 — Identificar código que una expresión lambda simplificaría (Predicate)

## 🧩 Problema

La Biblioteca Universitaria filtra los libros disponibles de una lista con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;
    private final boolean disponible;

    public LibroBiblioteca(String titulo, boolean disponible) {
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

public interface CondicionLibro {
    boolean cumple(LibroBiblioteca libro);
}
```

```java
package com.biblioteca;

public class EstaDisponible implements CondicionLibro {
    @Override
    public boolean cumple(LibroBiblioteca libro) {
        return libro.isDisponible();
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<LibroBiblioteca> libros = new ArrayList<>();
        libros.add(new LibroBiblioteca("El Quijote", true));
        libros.add(new LibroBiblioteca("Rayuela", false));

        CondicionLibro condicion = new EstaDisponible();
        for (LibroBiblioteca libro : libros) {
            if (condicion.cumple(libro)) {
                System.out.println(libro.getTitulo());
            }
        }
    }
}
```

Sin escribir código, responde: ¿qué interfaz estándar de `java.util.function` podría reemplazar a
`CondicionLibro` y `EstaDisponible`, y por qué encaja?

## 📏 Criterios de evaluación de la solución

- Identifica que `CondicionLibro`/`EstaDisponible` reciben un `LibroBiblioteca` y devuelven un
  `boolean`.
- Explica que `Predicate<LibroBiblioteca>` encaja exactamente en esa forma, sin necesitar una interfaz
  ni una clase propias.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: usar `Predicate<T>`.
