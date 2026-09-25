# 🟢 Básico 01 — Identificar violación de Factory Method

## 🧩 Problema

La Biblioteca Universitaria tiene esta clase para prestar materiales:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public interface MaterialBibliografico {
    String describir();
}
```

```java
package com.biblioteca;

public class LibroFisico implements MaterialBibliografico {
    public String describir() {
        return "Libro fisico, disponible en estanteria";
    }
}
```

```java
package com.biblioteca;

public class Biblioteca {
    public MaterialBibliografico prestarParaSala(String tipo) {
        if (tipo.equals("FISICO")) {
            return new LibroFisico();
        }
        throw new IllegalArgumentException("Tipo de material desconocido: " + tipo);
    }

    public MaterialBibliografico prestarADomicilio(String tipo) {
        if (tipo.equals("FISICO")) {
            return new LibroFisico();
        }
        throw new IllegalArgumentException("Tipo de material desconocido: " + tipo);
    }
}
```

Sin escribir código, respondé: si la biblioteca agrega un tipo de material nuevo (por ejemplo, un
e-book), ¿qué archivo hay que modificar y qué parte exacta de ese archivo?

## 📏 Criterios de evaluación de la solución

- Identifica que hay que modificar `Biblioteca.java`, en los dos métodos que crean el material.
- Explica por qué eso es una violación de Factory Method: el código cliente está acoplado a la clase
  concreta `LibroFisico`.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: reconocer qué resuelve Factory Method.
- **RA-3**: identificar el problema de acoplamiento en un fragmento dado.
