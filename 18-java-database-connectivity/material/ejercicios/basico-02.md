# 🟢 Básico 02 — Identificar uso incorrecto de JDBC

## 🧩 Problema

La Biblioteca Universitaria muestra su catálogo de libros con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) {
        System.out.println("Catalogo de libros (segun lista fija en el codigo):");
        System.out.println("- El Quijote, Cervantes");
        System.out.println("- Cien anios de soledad, Garcia Marquez");
    }
}
```

La tabla real `libros` tiene, en este momento, **tres** libros:
`El Quijote`, `Cien años de soledad` y `Rayuela`.

Sin escribir código, responde: ¿por qué el programa solo muestra dos libros, si la base de datos real
tiene tres?

## 📏 Criterios de evaluación de la solución

- Identifica que el programa nunca ejecuta ninguna consulta SQL: los datos están escritos directamente en
  el código.
- Explica que, sin usar `Statement`/`ResultSet` contra la base real, el programa no puede reflejar el
  catálogo actual.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: usar `Statement`/`ResultSet`.
