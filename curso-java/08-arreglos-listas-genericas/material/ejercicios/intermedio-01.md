# 🟡 Intermedio 01 — Declarar y recorrer un arreglo

## 🧩 Problema

**Biblioteca Universitaria** registra el monto de tres multas con variables sueltas.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

// TODO: registra las multas usando variables sueltas; conviértelo a un arreglo.
public class Demo {
    public static void main(String[] args) {
        int multa1 = 1500;
        int multa2 = 3000;
        int multa3 = 500;
        System.out.println(multa1);
        System.out.println(multa2);
        System.out.println(multa3);
    }
}
```

**Tarea**: reemplaza las tres variables por un arreglo `int[] multas`, y recorre e imprime sus valores
con un bucle `for`, en vez de tres `System.out.println` independientes.

## 🧪 Casos de prueba

| Salida esperada |
|---|
| `1500` |
| `3000` |
| `500` |

## 📏 Criterios de evaluación de la solución

- `multas` es un arreglo `int[]` con los tres valores originales.
- Se recorre con un bucle `for`, no con tres `println` independientes.
- El programa produce exactamente la misma salida que la versión con variables sueltas.

## 🚧 Restricciones

- No cambies el orden ni los valores de las multas.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-2**: declarar un arreglo y asignarle valores.
- **RA-5**: iterar un arreglo con `for`.
