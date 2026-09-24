# 🟢 Básico 03 — Predecir un Map tras varios put

## 🧩 Problema

Mira este código de MediSalud:

## 💻 Código o contexto de partida

```java
package com.medisalud;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Map<String, Integer> inventario = new HashMap<>();
        inventario.put("Paracetamol", 50);
        inventario.put("Amoxicilina", 20);
        inventario.put("Ibuprofeno", 35);
        inventario.put("Paracetamol", 80);

        System.out.println("size=" + inventario.size());
        System.out.println("Paracetamol=" + inventario.get("Paracetamol"));
    }
}
```

**Pregunta**: sin ejecutar todavía, predice qué imprime `Demo`.

## 🧪 Casos de prueba

| Expresión | Resultado esperado |
|---|---|
| `inventario.size()` | ? |
| `inventario.get("Paracetamol")` | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente que `inventario.size()` da `3`, no `4`: la clave repetida actualiza el valor.
- Predice correctamente que `inventario.get("Paracetamol")` da `80` (el último valor puesto), no `50`.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de lectura y predicción.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-4**: explicar qué es un `Map`.
- **RA-5**: predecir el efecto de `put` sobre un `HashMap`.
