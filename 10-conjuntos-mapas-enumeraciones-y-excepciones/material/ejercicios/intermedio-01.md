# 🟡 Intermedio 01 — Declarar y operar un Set

## 🧩 Problema

Tienes este programa de Biblioteca Universitaria que evita categorías repetidas a mano, comprobando con
`contains` antes de cada `add` sobre una `List`:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

// TODO: convertir categorias en un Set para no tener que comprobar manualmente los duplicados.
public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        List<String> categorias = new ArrayList<>();
        String[] nuevas = { "Novela", "Realismo magico", "Novela", "Clasico" };

        for (String categoria : nuevas) {
            if (!categorias.contains(categoria)) {
                categorias.add(categoria);
            }
        }

        System.out.println("size=" + categorias.size());
    }
}
```

Reescribilo usando un `Set<String>` en vez de una `List<String>` con comprobación manual, y agrega una
segunda parte que calcule la intersección entre las categorías obtenidas y un conjunto de "categorías
destacadas" (`"Novela"` y `"Clasico"`).

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `{"Novela", "Realismo magico", "Novela", "Clasico"}` agregadas a un `Set` | `categorias.size()` | `3` |
| Mismo `Set`, intersección con `{"Novela", "Clasico"}` | `interseccion` | Contiene `Novela` y `Clasico` |

## 📏 Criterios de evaluación de la solución

- Usa un `Set<String>` en vez de una `List` con comprobación manual de duplicados.
- Calcula la intersección con `retainAll` sobre una copia del conjunto original.

## 🚧 Restricciones

- No se usa `Map` ni `enum` (temas de puntos posteriores del módulo).
- No se usa `try`/`catch`.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-2**: declarar un `Set` y usar sus operaciones más comunes.
- **RA-3**: combinar dos `Set`.
