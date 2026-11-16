# 🟡 Intermedio 04 — Aplicar Predicate

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 04:

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

Rediséñalo para que use `Predicate<LibroBiblioteca>` con una expresión lambda, en vez de la interfaz
`CondicionLibro` y la clase `EstaDisponible`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| "El Quijote" (disponible) y "Rayuela" (no disponible) | Salida por consola | Solo `El Quijote`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `Predicate<LibroBiblioteca>` con una expresión lambda, sin declarar ninguna interfaz ni clase
  propia.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map`, referencias a métodos (`::`) ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-5**: implementar una condición con `Predicate<T>` para un caso dado.
