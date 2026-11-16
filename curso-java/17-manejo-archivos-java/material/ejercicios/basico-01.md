# 🟢 Básico 01 — Identificar uso de la clase File

## 🧩 Problema

La Biblioteca Universitaria consulta los préstamos activos con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) {
        String[] prestamosEsperados = {"prestamo1.txt", "prestamo2.txt"};

        System.out.println("Prestamos activos (segun lista fija en el codigo):");
        for (String nombre : prestamosEsperados) {
            System.out.println("- " + nombre);
        }
    }
}
```

La carpeta real `prestamos_activos/` tiene, en este momento, **tres** archivos:
`prestamo1.txt`, `prestamo2.txt` y `prestamo3.txt`.

Sin escribir código, responde: ¿por qué el programa solo menciona dos préstamos, si la carpeta real tiene
tres?

## 📏 Criterios de evaluación de la solución

- Identifica que el programa usa una lista fija de nombres en el código, en vez de consultar la carpeta
  real.
- Explica que esa lista quedó desactualizada: no refleja el tercer préstamo agregado a la carpeta.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: explicar qué es un archivo en Java y usar la clase `File`.
