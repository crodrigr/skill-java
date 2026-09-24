# 🔴 Avanzado 01 — Corregir un programa con errores de relaciones

## 🧩 Problema

Este programa de Biblioteca Universitaria tiene dos errores de diseño distintos, cada uno en su propia
ronda. Ninguno de los dos es un error de compilación: los dos programas de abajo compilan y se ejecutan
sin lanzar ninguna excepción.

## 💻 Código o contexto de partida

**Ronda 1** — una asociación bidireccional inconsistente:

```java error-logico
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

class Estudiante {
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

class Libro {
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

public class RevisionDePrestamos {
    public static void main(String[] args) {
        // Datos de entrada
        Estudiante estudiante = new Estudiante("Lucia Fernandez");
        Libro libro = new Libro("El principito");

        // Ronda 1: se actualiza solo un extremo, nunca se llama a estudiante.prestar(libro)
        libro.setEstudiante(estudiante);

        System.out.println(estudiante.getNombre() + " tiene " + estudiante.getLibrosPrestados().size() + " libro(s) prestado(s)");
        System.out.println(libro.getTitulo() + " -> estudiante: " + libro.getEstudiante().getNombre());
    }
}
```

**Ronda 2** — una composición mal implementada como agregación:

```java error-logico
package com.biblioteca;

class Comprobante {
    private int numero;

    public Comprobante(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}

// Version incorrecta: el Comprobante se recibe por parametro en vez de crearse dentro del
// constructor de Prestamo, asi que dos Prestamo distintos pueden terminar compartiendo la misma
// instancia (composicion mal implementada como agregacion).
class Prestamo {
    private String libro;
    private Comprobante comprobante;

    public Prestamo(String libro, Comprobante comprobante) {
        this.libro = libro;
        this.comprobante = comprobante;
    }

    public String getLibro() {
        return libro;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }
}

public class RevisionDePrestamos {
    public static void main(String[] args) {
        // Datos de entrada
        Comprobante comprobanteCompartido = new Comprobante(9001);
        Prestamo prestamo1 = new Prestamo("Cien anios de soledad", comprobanteCompartido);
        Prestamo prestamo2 = new Prestamo("El principito", comprobanteCompartido);

        boolean mismoComprobante = prestamo1.getComprobante() == prestamo2.getComprobante();
        System.out.println(prestamo1.getLibro() + " - Comprobante N" + prestamo1.getComprobante().getNumero());
        System.out.println(prestamo2.getLibro() + " - Comprobante N" + prestamo2.getComprobante().getNumero());
        System.out.println("prestamo1 y prestamo2 comparten el mismo Comprobante: " + mismoComprobante);
    }
}
```

## 🧪 Casos de prueba

Salida real de la Ronda 1 — `estudiante.getLibrosPrestados().size()` debería dar `1`, no `0`:

```text
Lucia Fernandez tiene 0 libro(s) prestado(s)
El principito -> estudiante: Lucia Fernandez
```

Salida real de la Ronda 2 — dos `Prestamo` distintos no deberían compartir el mismo `Comprobante`:

```text
Cien anios de soledad - Comprobante N9001
El principito - Comprobante N9001
prestamo1 y prestamo2 comparten el mismo Comprobante: true
```

Corrige ambas rondas:

1. En la Ronda 1, cambia el vínculo para que se cree con un único método que actualice los dos
   extremos a la vez (como `estudiante.prestar(libro)` del Ejemplo 04/Intermedio 01), en vez de llamar
   solo a `libro.setEstudiante(estudiante)`.
2. En la Ronda 2, cambia `Prestamo` para que **cree** su propio `Comprobante` dentro del constructor
   (composición), en vez de recibirlo por parámetro.

## 📏 Criterios de evaluación de la solución

- Ronda 1: tras la corrección, `estudiante.getLibrosPrestados().size()` da `1`.
- Ronda 2: tras la corrección, dos `Prestamo` distintos nunca comparten la misma instancia de
  `Comprobante` (`prestamo1.getComprobante() == prestamo2.getComprobante()` da `false`).
- El programa corregido compila y se ejecuta sin ninguna excepción en ambas rondas.

## 🚧 Restricciones

- No se agregan clases nuevas: solo se corrige el diseño de las que ya existen.
- No se usa `try`/`catch`.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-5**: reconocer el riesgo de una asociación bidireccional inconsistente.
- **RA-10**: distinguir asociación, agregación y composición dado un escenario.
- **RA-12**: identificar y corregir los errores frecuentes de esta etapa.
