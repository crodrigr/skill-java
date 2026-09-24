# 🟢 Básico 02 — Predecir una operación de conjuntos

## 🧩 Problema

Mira este código de Biblioteca Universitaria:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Set<String> categoriasLibro1 = new HashSet<>();
        categoriasLibro1.add("Novela");
        categoriasLibro1.add("Realismo magico");

        Set<String> categoriasLibro2 = new HashSet<>();
        categoriasLibro2.add("Realismo magico");
        categoriasLibro2.add("Clasico");

        Set<String> interseccion = new HashSet<>(categoriasLibro1);
        interseccion.retainAll(categoriasLibro2);
        System.out.println("interseccion=" + interseccion);

        Set<String> union = new HashSet<>(categoriasLibro1);
        union.addAll(categoriasLibro2);
        System.out.println("union.size=" + union.size());
    }
}
```

**Pregunta**: sin ejecutar todavía, predice qué imprime `Demo`.

## 🧪 Casos de prueba

| Expresión | Resultado esperado |
|---|---|
| `interseccion` | ? |
| `union.size()` | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente que `interseccion` es `[Realismo magico]` (la única categoría compartida).
- Predice correctamente que `union.size()` da `3` (`Novela`, `Realismo magico`, `Clasico`).

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de lectura y predicción.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: predecir el resultado de una operación de conjuntos.
