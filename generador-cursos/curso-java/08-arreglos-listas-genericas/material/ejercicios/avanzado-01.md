# 🔴 Avanzado 01 — Corregir un programa con errores de arreglos/List

## 🧩 Problema

**Biblioteca Universitaria** tiene un programa `RevisionDeInventario` con **dos errores**, uno de
ejecución y uno de compilación. Corrígelos en dos rondas.

## 💻 Código o contexto de partida

**Ronda 1** (falla en ejecución):

```java falla-en-ejecucion
package com.biblioteca;

public class RevisionDeInventario {
    public static void main(String[] args) {
        int[] ejemplares = {5, 3, 8};
        int indice = 3;
        System.out.println("Ejemplares en sede " + indice + ": " + ejemplares[indice]);
    }
}
```

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
```

El panel Problems no marca este error antes de ejecutar.

**Ronda 2** (tras corregir la ronda 1, no compila):

```java no-compila
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class RevisionDeInventario {
    public static void main(String[] args) {
        List<String> catalogo = new ArrayList<>();
        catalogo.add("Rayuela");
        catalogo.add("Cien años de soledad");
        System.out.println("Cantidad de títulos: " + catalogo.length());
    }
}
```

```text
✖ The method length() is undefined for the type List<String> Java(67108964) [Ln 11, Col 63]
```

## 🧪 Casos de prueba

| Ronda | Síntoma | Corrección |
|---|---|---|
| 1 | `ArrayIndexOutOfBoundsException` al ejecutar | ? |
| 2 (tras corregir la 1) | El compilador rechaza `.length()` sobre `catalogo` | ? |

## 📏 Criterios de evaluación de la solución

- **Ronda 1**: cambia el índice para que quede dentro del rango válido del arreglo `ejemplares`
  (posiciones `0` a `ejemplares.length - 1`).
- **Ronda 2**: cambia `catalogo.length()` por `catalogo.size()` (`catalogo` es una `List`, no un
  arreglo).
- El programa final compila, se ejecuta sin excepciones y produce dos líneas de salida.

## 🚧 Restricciones

- No cambies el tipo de `ejemplares` (arreglo) ni el de `catalogo` (`List`).
- Corrige un error a la vez, verificando el panel Problems después de cada corrección.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-3**: identificar y corregir un acceso fuera de rango de un arreglo.
- **RA-15**: distinguir `.length` (arreglo) de `.size()` (`List`).
- **RA-16**: identificar y corregir los errores frecuentes de esta etapa.
