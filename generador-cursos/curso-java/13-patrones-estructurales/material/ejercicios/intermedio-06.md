# 🟡 Intermedio 06 — Aplicar Flyweight

## 🧩 Problema

Tienes las mismas clases de la Biblioteca Universitaria del ejercicio Básico 06:

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

Rediséñalo para que respete Flyweight: declara una fábrica con caché que comparta una única instancia de
`GeneroLiterario` entre todos los ejemplares del mismo género.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Dos ejemplares del mismo género ("Novela") | `ejemplar1.getGenero() == ejemplar2.getGenero()` (igualdad de referencia) | `true` (antes de refactorizar da `false`) |
| Descripción de cada ejemplar | Salida completa del programa | Idéntica a la de la versión original, salvo la línea de `mismaInstancia` |

## 📏 Criterios de evaluación de la solución

- Declara una clase (por ejemplo, `FabricaDeGeneros`) con un método estático que devuelve una instancia
  cacheada de `GeneroLiterario` por nombre de género, creándola solo la primera vez.
- `Ejemplar` pide su género a la fábrica en vez de crear uno con `new`.
- Dos ejemplares del mismo género comparten la misma instancia (`mismaInstancia=true`).
- `GeneroLiterario.java` no se modifica: no almacena ningún dato propio de un ejemplar en particular.

## 🚧 Restricciones

- No se usan `Set` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-19**: implementar Flyweight para un caso dado.
