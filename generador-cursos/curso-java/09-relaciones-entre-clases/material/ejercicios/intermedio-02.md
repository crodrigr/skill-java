# 🟡 Intermedio 02 — Declarar una agregación

## 🧩 Problema

Tienes estas dos clases de Biblioteca Universitaria:

## 💻 Código o contexto de partida

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

// TODO: agregar una lista de Libro ya creados (agregacion) y un metodo para agregarlos.
public class Biblioteca {
    private String nombre;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        System.out.println(biblioteca.getNombre());
    }
}
```

Agrega a `Biblioteca` una lista de `Libro` (agregación: los `Libro` se reciben ya creados, con un método
`agregarLibro(Libro)`, y podrían pertenecer a otra `Biblioteca` o a ninguna).

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `Biblioteca("Biblioteca Central")`, dos `Libro` agregados | `biblioteca.getLibros().size()` | `2` |
| `Biblioteca` recién creada, sin libros agregados | `biblioteca.getLibros().get(0)` | Termina con `IndexOutOfBoundsException` (colección agregada vacía) |

## 📏 Criterios de evaluación de la solución

- `Biblioteca` guarda una `List<Libro>`, inicializada vacía.
- `agregarLibro(Libro)` recibe el `Libro` ya creado y lo agrega a la lista (no lo construye).
- El programa termina, con éxito o con la excepción real de un índice fuera de rango sobre una lista
  vacía.

## 🚧 Restricciones

- No se usa `Set` ni `Map`: solo `List`/`ArrayList` (Módulo 8).
- No se usa `try`/`catch`.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-6**: explicar qué es la agregación.
- **RA-7**: declarar y usar una agregación.
