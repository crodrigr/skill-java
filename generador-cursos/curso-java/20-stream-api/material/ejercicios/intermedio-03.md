# 🟡 Intermedio 03 — Aplicar el operador filter

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 03:

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

Rediséñalo para que use `filter` con un `Predicate<LibroCatalogo>`, en vez del bucle `for` con `if`
que arma la lista de disponibles a mano.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| "El Quijote" (disponible) y "Rayuela" (no disponible) | Salida por consola | Solo `El Quijote`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `filter` con un `Predicate<LibroCatalogo>`, sin armar una lista intermedia a mano.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: implementar una selección con `filter` para un caso dado.
