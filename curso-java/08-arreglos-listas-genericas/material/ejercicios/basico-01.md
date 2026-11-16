# 🟢 Básico 01 — Identificar la estructura de datos adecuada

## 🧩 Problema

**Biblioteca Universitaria** necesita mostrar los títulos de 4 libros nuevos de la colección.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        String[] titulos = {"Cien años de soledad", "El principito", "Rayuela", "Ficciones"};
        for (String titulo : titulos) {
            System.out.println(titulo);
        }
    }
}
```

**Pregunta**: responde, sin ejecutar el programa todavía:

1. ¿Por qué conviene usar un arreglo aquí en vez de cuatro variables `String` independientes
   (`titulo1`, `titulo2`, `titulo3`, `titulo4`)?
2. Si la biblioteca agregara 50 libros más, ¿qué opción escalaría mejor: variables independientes o un
   arreglo?

## 📏 Criterios de evaluación de la solución

- Explica que un arreglo agrupa varios datos relacionados del mismo tipo sin declarar una variable por
  cada uno.
- Reconoce que un arreglo escala mejor cuando la cantidad de datos crece.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de identificación conceptual.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar qué es una estructura de datos y por qué conviene agruparlas.
