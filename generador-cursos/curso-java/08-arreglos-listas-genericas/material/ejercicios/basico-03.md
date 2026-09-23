# 🟢 Básico 03 — El error de un índice fuera de rango

## 🧩 Problema

**Biblioteca Universitaria** tiene este programa real.

## 💻 Código o contexto de partida

```java falla-en-ejecucion
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        int[] ejemplaresDisponibles = {5, 12, 3, 8};
        System.out.println("Antes del error");
        System.out.println(ejemplaresDisponibles[4]);
    }
}
```

**Pregunta**: ¿el programa compila? ¿Qué ocurre al ejecutarlo?

## 📏 Criterios de evaluación de la solución

- Identifica que el programa **compila** sin problema.
- Identifica que **al ejecutarse** termina con `ArrayIndexOutOfBoundsException`.
- Explica que el arreglo tiene 4 elementos (índices `0` a `3`), y el índice `4` está fuera de rango.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de identificación del error.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: explicar el error de índice fuera de rango.
