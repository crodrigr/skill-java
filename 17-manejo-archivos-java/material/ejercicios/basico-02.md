# 🟢 Básico 02 — Identificar violación de lectura de archivos

## 🧩 Problema

La Biblioteca Universitaria muestra su catálogo de libros con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) {
        String[] libros = {
            "El Quijote,Cervantes",
            "Cien anios de soledad,Garcia Marquez",
            "Rayuela,Cortazar"
        };

        System.out.println("Catalogo de libros (hardcodeado en el codigo):");
        for (String libro : libros) {
            System.out.println(libro);
        }
    }
}
```

Sin escribir código, responde: si la biblioteca agrega un libro nuevo al catálogo, ¿el programa lo
mostraría? ¿Por qué?

## 📏 Criterios de evaluación de la solución

- Identifica que el catálogo está hardcodeado directamente en el código.
- Explica que agregar un libro nuevo requeriría modificar y recompilar el programa, en vez de solo
  actualizar un archivo de datos externo.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: identificar datos que deberían leerse de un archivo.
