# 🟢 Básico 03 — Identificar código que una expresión lambda simplificaría (Function)

## 🧩 Problema

La Biblioteca Universitaria formatea una línea de recibo por libro con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;
    private final String autor;

    public LibroBiblioteca(String titulo, String autor) {
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

public interface FormateadorRecibo {
    String formatear(LibroBiblioteca libro);
}
```

```java
package com.biblioteca;

public class FormateadorReciboSimple implements FormateadorRecibo {
    @Override
    public String formatear(LibroBiblioteca libro) {
        return libro.getTitulo() + " - " + libro.getAutor();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        LibroBiblioteca libro = new LibroBiblioteca("El Quijote", "Cervantes");
        FormateadorRecibo formateador = new FormateadorReciboSimple();
        System.out.println(formateador.formatear(libro));
    }
}
```

Sin escribir código, responde: ¿qué interfaz estándar de `java.util.function` podría reemplazar a
`FormateadorRecibo` y `FormateadorReciboSimple`, y por qué encaja?

## 📏 Criterios de evaluación de la solución

- Identifica que `FormateadorRecibo`/`FormateadorReciboSimple` reciben un `LibroBiblioteca` y devuelven
  un `String`.
- Explica que `Function<LibroBiblioteca, String>` encaja exactamente en esa forma, sin necesitar una
  interfaz ni una clase propias.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-4**: usar `Function<T, R>`.
