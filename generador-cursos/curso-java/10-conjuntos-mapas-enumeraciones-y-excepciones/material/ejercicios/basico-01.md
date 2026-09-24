# 🟢 Básico 01 — Predecir un Set con duplicados

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
        Set<String> categorias = new HashSet<>();
        categorias.add("Novela");
        categorias.add("Realismo magico");
        categorias.add("Novela");

        System.out.println("size=" + categorias.size());
    }
}
```

**Pregunta**: sin ejecutar todavía, predice qué imprime `Demo`.

## 🧪 Casos de prueba

| Expresión | Resultado esperado |
|---|---|
| `categorias.size()` | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente que `categorias.size()` da `2`, no `3`: el segundo `add("Novela")` no agrega
  nada.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de lectura y predicción.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar qué es un `Set`.
- **RA-2**: predecir el efecto de `add` sobre un `Set`.
