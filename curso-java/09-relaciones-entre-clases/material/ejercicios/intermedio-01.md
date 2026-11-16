# 🟡 Intermedio 01 — Declarar una asociación bidireccional

## 🧩 Problema

Tienes estas dos clases de Biblioteca Universitaria, todavía sin ninguna relación entre sí:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

// TODO: agregar la asociación bidireccional con Libro (método que actualice ambos extremos a la vez).
public class Estudiante {
    private String nombre;

    public Estudiante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

```java
package com.biblioteca;

public class Libro {
    private String titulo;

    public Libro(String titulo) {
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
        // Datos de entrada
        Estudiante estudiante = new Estudiante("Sofia Gomez");
        Libro libro = new Libro("El principito");

        // Todavia no hay ninguna relacion entre Estudiante y Libro
        System.out.println(estudiante.getNombre());
        System.out.println(libro.getTitulo());
    }
}
```

Agrega una asociación **bidireccional** entre `Estudiante` y `Libro`: cada `Estudiante` debe poder tener
varios `Libro` prestados, y cada `Libro` debe poder saber qué `Estudiante` lo tiene. Usa un único método
(por ejemplo, `prestar(Libro)` en `Estudiante`) que actualice los dos extremos a la vez.

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `Estudiante("Sofia Gomez")`, `Libro("El principito")` | `estudiante.prestar(libro)`, luego `estudiante.getLibrosPrestados().size()` | `1` |
| Mismo caso | `libro.getEstudiante().getNombre()` | `Sofia Gomez` |

## 📏 Criterios de evaluación de la solución

- `Estudiante` tiene una lista de `Libro` prestados.
- `Libro` tiene una referencia a su `Estudiante`.
- Un único método actualiza ambos extremos a la vez (sin dejar la posibilidad de actualizar solo uno).

## 🚧 Restricciones

- No se usa `Set`, `Map` ni ninguna otra colección fuera de `List` (Módulo 8).
- No se usa `try`/`catch`.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-3**: declarar una asociación unidireccional (como paso intermedio).
- **RA-4**: declarar una asociación bidireccional consistente.
