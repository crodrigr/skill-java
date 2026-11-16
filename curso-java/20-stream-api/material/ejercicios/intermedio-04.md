# 🟡 Intermedio 04 — Aplicar el operador anyMatch

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 04:

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

Rediséñalo para que use `anyMatch` con un `Predicate<LibroCatalogo>`, en vez de la bandera booleana y
el bucle con `break`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| "El Quijote" (Cervantes) y "Rayuela" (Cortazar) | `¿Hay algun libro de Cervantes?` | `true`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `anyMatch` con un `Predicate<LibroCatalogo>`, sin bandera booleana ni `break` manual.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-8**: implementar una verificación con `anyMatch` para un caso dado.
