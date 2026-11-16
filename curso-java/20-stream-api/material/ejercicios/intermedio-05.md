# 🟡 Intermedio 05 — Aplicar el operador flatMap

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 05:

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

Rediséñalo para que use `flatMap` sobre las estanterías, en vez del bucle `for` anidado.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Estantería 1: "El Quijote", "Rayuela"; Estantería 2: "Cien Anios de Soledad" | Total de libros aplanados | `3`, con los 3 títulos listados, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `flatMap` sobre el stream de estanterías, sin bucle `for` anidado.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-9**: implementar un aplanado con `flatMap` para un caso dado.
