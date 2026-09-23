# 🟢 Básico 02 — Predecir un acceso por índice

## 🧩 Problema

**Biblioteca Universitaria** registra los ejemplares disponibles de 4 títulos.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        int[] ejemplaresDisponibles = {5, 12, 3, 8};
        System.out.println(ejemplaresDisponibles[2]);
    }
}
```

**Pregunta**: sin ejecutar el programa, predice qué imprime `ejemplaresDisponibles[2]`.

## 🧪 Casos de prueba

| Expresión | Predicción |
|---|---|
| `ejemplaresDisponibles[2]` | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente `3` (tercera posición, índice `2`).
- Explica que el primer índice es `0`, no `1`.

## 🚧 Restricciones

- No ejecutes el código antes de predecir.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: acceder a un elemento por índice.
