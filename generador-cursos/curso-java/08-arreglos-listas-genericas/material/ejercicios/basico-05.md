# 🟢 Básico 05 — Predecir una iteración

## 🧩 Problema

**Biblioteca Universitaria** registra los préstamos realizados en tres días.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        int[] prestamosPorDia = {3, 7, 5};
        for (int i = 0; i < prestamosPorDia.length; i++) {
            System.out.println(prestamosPorDia[i]);
        }
    }
}
```

**Pregunta**: sin ejecutar el programa, predice qué imprime, en orden.

## 🧪 Casos de prueba

| Vuelta del bucle | Predicción |
|---|---|
| 1.ª | ? |
| 2.ª | ? |
| 3.ª | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente `3`, `7`, `5`, en ese orden.
- Explica que el `for` recorre desde el índice `0` hasta `prestamosPorDia.length - 1`.

## 🚧 Restricciones

- No ejecutes el código antes de predecir.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: iterar un arreglo con `for`.
