# 🟢 Básico 04 — Calcular el tamaño

## 🧩 Problema

**Biblioteca Universitaria** registra los autores de tres libros nuevos.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        String[] autores = {"Gabriel García Márquez", "Julio Cortázar", "Jorge Luis Borges"};
        System.out.println(autores.length);
    }
}
```

**Pregunta**: sin ejecutar el programa, predice qué imprime `autores.length`.

## 🧪 Casos de prueba

| Expresión | Predicción |
|---|---|
| `autores.length` | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente `3`.
- Explica que `.length` no lleva paréntesis (es un atributo, no un método).

## 🚧 Restricciones

- No ejecutes el código antes de predecir.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-4**: obtener el tamaño de un arreglo con `.length`.
