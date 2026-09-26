# 🟢 Básico 06 — Identificar violación de Flyweight

## 🧩 Problema

La Biblioteca Universitaria clasifica cada ejemplar con estas clases:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class GeneroLiterario {
    private String nombre;
    private String clasificacionDewey;

    public GeneroLiterario(String nombre, String clasificacionDewey) {
        this.nombre = nombre;
        this.clasificacionDewey = clasificacionDewey;
    }

    public String describir() {
        return nombre + " (Dewey " + clasificacionDewey + ")";
    }
}
```

```java
package com.biblioteca;

public class Ejemplar {
    private String titulo;
    private GeneroLiterario genero;

    public Ejemplar(String titulo, String nombreGenero) {
        this.titulo = titulo;
        this.genero = new GeneroLiterario(nombreGenero, "800");
    }

    public String describir() {
        return titulo + " - " + genero.describir();
    }

    public GeneroLiterario getGenero() {
        return genero;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        Ejemplar ejemplar1 = new Ejemplar("Rayuela", "Novela");
        Ejemplar ejemplar2 = new Ejemplar("El Aleph", "Novela");

        System.out.println(ejemplar1.describir());
        System.out.println(ejemplar2.describir());

        boolean mismaInstancia = ejemplar1.getGenero() == ejemplar2.getGenero();
        System.out.println("mismaInstancia=" + mismaInstancia);
    }
}
```

Ejecutando el `Demo` de arriba con solo dos ejemplares del mismo género, esta es la salida real:

```text
Rayuela - Novela (Dewey 800)
El Aleph - Novela (Dewey 800)
mismaInstancia=false
```

Sin escribir código, responde: si la biblioteca tiene 10.000 ejemplares del género "Novela", ¿cuántos
objetos `GeneroLiterario` distintos existen en memoria, y qué problema tiene eso?

## 📏 Criterios de evaluación de la solución

- Identifica que existen 10.000 objetos `GeneroLiterario` distintos (uno por ejemplar), aunque todos
  tengan exactamente los mismos datos (`"Novela"`, `"800"`).
- Explica que eso desperdicia memoria repitiendo el mismo estado una y otra vez, cuando los ejemplares
  del mismo género podrían compartir un único objeto.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-17**: reconocer qué resuelve Flyweight.
- **RA-18**: reconocer objetos que desperdician memoria repitiendo el mismo estado.
