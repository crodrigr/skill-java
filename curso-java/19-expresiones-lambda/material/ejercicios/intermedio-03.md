# 🟡 Intermedio 03 — Aplicar Function

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 03:

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

Rediséñalo para que use `Function<LibroBiblioteca, String>` con una expresión lambda, en vez de la
interfaz `FormateadorRecibo` y la clase `FormateadorReciboSimple`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| `LibroBiblioteca("El Quijote", "Cervantes")` | Salida por consola | `El Quijote - Cervantes`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `Function<LibroBiblioteca, String>` con una expresión lambda, sin declarar ninguna interfaz ni
  clase propia.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar una transformación con `Function<T, R>` para un caso dado.
