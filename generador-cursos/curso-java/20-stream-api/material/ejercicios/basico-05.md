# 🟢 Básico 05 — Identificar código que Stream API simplificaría (flatMap)

## 🧩 Problema

La Biblioteca Universitaria organiza sus libros por estantería y necesita la lista completa de todos
los libros, sin importar en qué estantería están, con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;

    public LibroCatalogo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<List<LibroCatalogo>> estanterias = new ArrayList<>();

        List<LibroCatalogo> estanteria1 = new ArrayList<>();
        estanteria1.add(new LibroCatalogo("El Quijote"));
        estanteria1.add(new LibroCatalogo("Rayuela"));
        estanterias.add(estanteria1);

        List<LibroCatalogo> estanteria2 = new ArrayList<>();
        estanteria2.add(new LibroCatalogo("Cien Anios de Soledad"));
        estanterias.add(estanteria2);

        List<LibroCatalogo> todos = new ArrayList<>();
        for (List<LibroCatalogo> estanteria : estanterias) {
            for (LibroCatalogo libro : estanteria) {
                todos.add(libro);
            }
        }

        System.out.println("Total de libros en todas las estanterias: " + todos.size());
        for (LibroCatalogo libro : todos) {
            System.out.println("- " + libro.getTitulo());
        }
    }
}
```

Sin escribir código, responde: ¿qué operador de Stream API podría reemplazar el bucle `for` anidado
(estanterías dentro, libros dentro), y por qué encaja?

## 📏 Criterios de evaluación de la solución

- Identifica que la fuente es una estructura anidada (`List<List<LibroCatalogo>>`) y que hace falta
  aplanarla en un único stream de libros.
- Explica que `flatMap` encaja exactamente en esa forma, a diferencia de `map` (que dejaría la
  estructura anidada intacta).

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-9**: usar el operador `flatMap`.
