# 🟡 Intermedio 06 — Aplicar la conversión List/Stream

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 06:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final int paginas;

    public LibroCatalogo(String titulo, int paginas) {
        this.titulo = titulo;
        this.paginas = paginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPaginas() {
        return paginas;
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
        libros.add(new LibroCatalogo("El Quijote", 863));
        libros.add(new LibroCatalogo("Rayuela", 635));
        libros.add(new LibroCatalogo("El Principito", 96));

        List<String> titulosExtensos = new ArrayList<>();
        for (LibroCatalogo libro : libros) {
            if (libro.getPaginas() > 300) {
                titulosExtensos.add(libro.getTitulo());
            }
        }

        System.out.println("Libros con mas de 300 paginas:");
        for (String titulo : titulosExtensos) {
            System.out.println("- " + titulo);
        }
    }
}
```

Rediséñalo para que use `.stream()`, `filter`, `map` y `.toList()`, en vez del bucle `for` con `if` y la
lista acumuladora manual.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| "El Quijote" (863 pág.), "Rayuela" (635 pág.), "El Principito" (96 pág.) | Títulos con más de 300 páginas | `El Quijote`, `Rayuela`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `.stream().filter(...).map(...).toList()`, sin bucle `for` ni lista acumuladora manual.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-10**: implementar la conversión `List → Stream → List` para un caso dado.
