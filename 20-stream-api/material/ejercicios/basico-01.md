# 🟢 Básico 01 — Identificar código que Stream API simplificaría (creación)

## 🧩 Problema

La Biblioteca Universitaria recorre su catálogo de libros con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;

    public LibroCatalogo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        LibroCatalogo[] catalogo = {
            new LibroCatalogo("El Quijote"),
            new LibroCatalogo("Rayuela")
        };

        System.out.println("Catalogo (bucle for):");
        for (int i = 0; i < catalogo.length; i++) {
            System.out.println("- " + catalogo[i].getTitulo());
        }
    }
}
```

Sin escribir código, responde: ¿qué método de Stream API podría reemplazar el bucle `for` que recorre
`catalogo`, dado que es un arreglo?

## 📏 Criterios de evaluación de la solución

- Identifica que `catalogo` es un arreglo (`LibroCatalogo[]`).
- Explica que `Arrays.stream(catalogo)` crea un stream a partir de ese arreglo, recorrible con
  `forEach`, sin necesitar un índice manual.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: crear un `Stream` a partir de un arreglo.
