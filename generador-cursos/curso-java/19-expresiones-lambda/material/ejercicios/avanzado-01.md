# 🔴 Avanzado 01 — Corregir un diseño con dos sub-temas técnicos ausentes

## 🧩 Problema

La Biblioteca Universitaria imprime el título de cada libro disponible con este código:

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

public interface Formateador {
    String formatear(LibroBiblioteca libro);
}
```

```java
package com.biblioteca;

public class FormateadorSimple implements Formateador {
    @Override
    public String formatear(LibroBiblioteca libro) {
        return libro.getTitulo();
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

        Formateador formateador = new FormateadorSimple();
        CondicionLibro condicion = new EstaDisponible();

        for (LibroBiblioteca libro : libros) {
            if (condicion.cumple(libro)) {
                System.out.println(formateador.formatear(libro));
            }
        }
    }
}
```

Este diseño tiene **dos** problemas técnicos ausentes a la vez: identifícalos y corrígelos por separado.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| "El Quijote" (disponible) y "Rayuela" (no disponible) | Salida por consola | Solo `El Quijote`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- **Corrección 1 (Function ausente)**: reemplaza `Formateador`/`FormateadorSimple` por
  `Function<LibroBiblioteca, String>` con una expresión lambda.
- **Corrección 2 (Predicate ausente)**: reemplaza `CondicionLibro`/`EstaDisponible` por
  `Predicate<LibroBiblioteca>` con una expresión lambda.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map`, referencias a métodos (`::`) ni excepciones propias como parte del diseño.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-7**: elegir la interfaz funcional adecuada para un problema que combina varios sub-temas.
