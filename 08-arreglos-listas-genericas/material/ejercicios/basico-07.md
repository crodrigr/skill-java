# 🟢 Básico 07 — Predecir operaciones de una List

## 🧩 Problema

**MediSalud** registra sus pacientes del día en una `List`.

## 💻 Código o contexto de partida

```java
package com.medisalud;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<String> pacientes = new ArrayList<>();
        pacientes.add("Ana Torres");
        pacientes.add("Luis Peña");
        pacientes.add("Marta Salinas");
        pacientes.remove(1);
        System.out.println(pacientes.get(1));
        System.out.println(pacientes.size());
    }
}
```

**Pregunta**: sin ejecutar el programa, predice qué imprime cada línea.

## 🧪 Casos de prueba

| Expresión | Predicción |
|---|---|
| `pacientes.get(1)` (tras `remove(1)`) | ? |
| `pacientes.size()` (tras `remove(1)`) | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente que, tras eliminar `"Luis Peña"` (índice `1`), `"Marta Salinas"` pasa a ocupar
  el índice `1`.
- Predice correctamente que `size()` es `2` tras la eliminación.

## 🚧 Restricciones

- No ejecutes el código antes de predecir.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-9**: usar `add`/`get`/`remove`/`size` de una `List`.
