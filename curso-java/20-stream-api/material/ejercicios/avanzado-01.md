# 🔴 Avanzado 01 — Corregir un diseño con dos sub-temas técnicos ausentes

## 🧩 Problema

La Biblioteca Universitaria organiza sus libros por estantería y necesita la lista de títulos
disponibles, sin importar en qué estantería están, con este código:

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
        List<List<LibroCatalogo>> estanterias = new ArrayList<>();

        List<LibroCatalogo> estanteria1 = new ArrayList<>();
        estanteria1.add(new LibroCatalogo("El Quijote", true));
        estanteria1.add(new LibroCatalogo("Rayuela", false));
        estanterias.add(estanteria1);

        List<LibroCatalogo> estanteria2 = new ArrayList<>();
        estanteria2.add(new LibroCatalogo("Cien Anios de Soledad", true));
        estanteria2.add(new LibroCatalogo("El Principito", false));
        estanterias.add(estanteria2);

        List<String> titulosDisponibles = new ArrayList<>();
        for (List<LibroCatalogo> estanteria : estanterias) {
            for (LibroCatalogo libro : estanteria) {
                if (libro.isDisponible()) {
                    titulosDisponibles.add(libro.getTitulo());
                }
            }
        }

        System.out.println("Libros disponibles en todas las estanterias:");
        for (String titulo : titulosDisponibles) {
            System.out.println("- " + titulo);
        }
    }
}
```

Este diseño tiene **dos** problemas técnicos ausentes a la vez: identifícalos y corrígelos por separado.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Estantería 1: "El Quijote" (disponible), "Rayuela" (no disponible); Estantería 2: "Cien Anios de Soledad" (disponible), "El Principito" (no disponible) | Títulos disponibles aplanados | `El Quijote`, `Cien Anios de Soledad`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- **Corrección 1 (flatMap ausente)**: reemplaza el bucle `for` anidado por `flatMap` para aplanar las
  estanterías en un único stream de libros.
- **Corrección 2 (filter ausente)**: reemplaza el `if` manual por `filter` con un
  `Predicate<LibroCatalogo>` para seleccionar los libros disponibles.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map`, referencias a métodos (`::`) ni excepciones propias como parte del diseño.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-11**: combinar varios operadores de Stream API para un problema que integra varios sub-temas.
