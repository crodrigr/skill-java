# 🟢 Básico 05 — Predecir una asociación bidireccional bien mantenida

## 🧩 Problema

Mira estas clases de Biblioteca Universitaria:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private List<Libro> librosPrestados = new ArrayList<>();

    public Estudiante(String nombre) {
        this.nombre = nombre;
    }

    public void prestar(Libro libro) {
        librosPrestados.add(libro);
        libro.setEstudiante(this);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }
}
```

```java
package com.biblioteca;

public class Libro {
    private String titulo;
    private Estudiante estudiante;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
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
        Estudiante estudiante = new Estudiante("Lucia Fernandez");
        Libro libro = new Libro("Cien anios de soledad");

        estudiante.prestar(libro);

        System.out.println(estudiante.getNombre() + " tiene " + estudiante.getLibrosPrestados().size() + " libro(s) prestado(s)");
        System.out.println(libro.getTitulo() + " -> estudiante: " + libro.getEstudiante().getNombre());
    }
}
```

**Pregunta**: sin ejecutar todavía, predice qué imprime `Demo`.

## 🧪 Casos de prueba

| Expresión | Resultado esperado |
|---|---|
| `estudiante.getLibrosPrestados().size()` | ? |
| `libro.getEstudiante().getNombre()` | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente que `estudiante.getLibrosPrestados().size()` da `1`.
- Predice correctamente que `libro.getEstudiante()` devuelve el estudiante correcto.
- Explica que `prestar(Libro)` mantiene ambos extremos consistentes, a diferencia de la versión
  inconsistente del Ejemplo 04.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de lectura y predicción.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-4**: mantener consistente una asociación bidireccional.
