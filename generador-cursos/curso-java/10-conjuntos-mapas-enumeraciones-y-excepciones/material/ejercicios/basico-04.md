# 🟢 Básico 04 — Predecir el orden de un TreeMap

## 🧩 Problema

Mira este código de MediSalud, con los mismos pares del ejercicio anterior:

## 💻 Código o contexto de partida

```java
package com.medisalud;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada (mismos pares que Basico 03)
        Map<String, Integer> inventarioHash = new HashMap<>();
        inventarioHash.put("Paracetamol", 50);
        inventarioHash.put("Amoxicilina", 20);
        inventarioHash.put("Ibuprofeno", 35);

        Map<String, Integer> inventarioOrdenado = new TreeMap<>(inventarioHash);

        System.out.println("HashMap keySet=" + inventarioHash.keySet());
        System.out.println("TreeMap keySet=" + inventarioOrdenado.keySet());
    }
}
```

**Pregunta**: sin ejecutar todavía, predice si `inventarioHash.keySet()` e `inventarioOrdenado.keySet()`
van a imprimir las claves en el mismo orden o en uno distinto, y cuál de los dos vas a poder predecir
con seguridad.

## 🧪 Casos de prueba

| Expresión | Resultado esperado |
|---|---|
| `inventarioOrdenado.keySet()` | Orden alfabético: `[Amoxicilina, Ibuprofeno, Paracetamol]` |
| `inventarioHash.keySet()` | Un orden que no se puede predecir solo mirando el código |

## 📏 Criterios de evaluación de la solución

- Predice con seguridad el orden del `TreeMap` (alfabético).
- Reconoce que el orden del `HashMap` no se puede predecir solo leyendo el código, aunque en la
  práctica sea siempre el mismo para los mismos datos en la misma versión del JDK.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de lectura y predicción.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-6**: explicar que `TreeMap` mantiene sus claves ordenadas.
- **RA-7**: elegir entre `HashMap` y `TreeMap` según si se necesita orden.
