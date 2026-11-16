# 🟡 Intermedio 02 — Aplicar el operador map

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 02:

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

        List<String> lineasCatalogo = new ArrayList<>();
        for (LibroCatalogo libro : libros) {
            lineasCatalogo.add(libro.getTitulo() + " - " + libro.getAutor());
        }

        System.out.println("Catalogo:");
        for (String linea : lineasCatalogo) {
            System.out.println("- " + linea);
        }
    }
}
```

Rediséñalo para que use `map` con una `Function<LibroCatalogo, String>`, en vez del bucle `for` que
arma la lista de líneas a mano.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| "El Quijote" (Cervantes) y "Rayuela" (Cortazar) | Salida por consola | `El Quijote - Cervantes` y `Rayuela - Cortazar`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `map` con una `Function<LibroCatalogo, String>`, sin armar una lista intermedia a mano.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-6**: implementar una transformación con `map` para un caso dado.
