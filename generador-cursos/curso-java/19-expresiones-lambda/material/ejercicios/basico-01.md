# 🟢 Básico 01 — Identificar código que una expresión lambda simplificaría (Consumer)

## 🧩 Problema

La Biblioteca Universitaria registra la devolución de cada libro de una lista con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;

    public LibroBiblioteca(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
```

```java
package com.biblioteca;

public interface AccionLibro {
    void ejecutar(LibroBiblioteca libro);
}
```

```java
package com.biblioteca;

public class RegistradorDevolucion implements AccionLibro {
    @Override
    public void ejecutar(LibroBiblioteca libro) {
        System.out.println("Devuelto: " + libro.getTitulo());
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
        libros.add(new LibroBiblioteca("El Quijote"));
        libros.add(new LibroBiblioteca("Rayuela"));

        AccionLibro accion = new RegistradorDevolucion();
        for (LibroBiblioteca libro : libros) {
            accion.ejecutar(libro);
        }
    }
}
```

Sin escribir código, responde: ¿qué interfaz estándar de `java.util.function` podría reemplazar a
`AccionLibro` y `RegistradorDevolucion`, y por qué encaja?

## 📏 Criterios de evaluación de la solución

- Identifica que `AccionLibro`/`RegistradorDevolucion` solo reciben un `LibroBiblioteca` y no devuelven
  nada.
- Explica que `Consumer<LibroBiblioteca>` encaja exactamente en esa forma, sin necesitar una interfaz ni
  una clase propias.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: usar `Consumer<T>`.
