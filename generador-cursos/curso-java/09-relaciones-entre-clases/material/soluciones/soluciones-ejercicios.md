# 🔑 Soluciones — Ejercicios del Módulo 9

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar el tipo de relación en un diagrama

1. `Empleado <|-- Medico`: **herencia** ("es un").
2. `Medico "1" -- "0..*" Paciente`: **asociación** ("tiene un", línea simple con multiplicidad).
3. `Departamento o-- Bibliotecario`: **agregación** (diamante hueco).

## 🟢 Básico 02 — Identificar una asociación unidireccional en código

`Factura` conoce a `Paciente` (atributo `paciente`); `Paciente` no tiene ningún atributo ni método hacia
`Factura`. Es una asociación **unidireccional**. Salida real de `Demo`:

```text
Factura N5001 - Marta Diaz
```

## 🟢 Básico 03 — Predecir multiplicidad, lado Libro

`0..*` del lado de `Libro` significa que una `Biblioteca` puede tener **cero o más** `Libro`: no hay un
mínimo obligatorio (puede empezar sin ninguno) ni un máximo fijo.

## 🟢 Básico 04 — Predecir multiplicidad, lado Biblioteca

`1` del lado de `Biblioteca` significa que, en este diagrama, cada `Libro` pertenece a exactamente
**una** `Biblioteca`: ni cero (no puede estar sin ninguna) ni más de una a la vez.

## 🟢 Básico 05 — Predecir una asociación bidireccional bien mantenida

`estudiante.prestar(libro)` actualiza ambos extremos a la vez. Salida real de `Demo`:

```text
Lucia Fernandez tiene 1 libro(s) prestado(s)
Cien anios de soledad -> estudiante: Lucia Fernandez
```

## 🟡 Intermedio 01 — Declarar una asociación bidireccional

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
        Estudiante estudiante = new Estudiante("Sofia Gomez");
        Libro libro = new Libro("El principito");

        estudiante.prestar(libro);

        System.out.println(estudiante.getNombre() + " tiene " + estudiante.getLibrosPrestados().size() + " libro(s) prestado(s)");
        System.out.println(libro.getTitulo() + " -> estudiante: " + libro.getEstudiante().getNombre());
    }
}
```

Salida real:

```text
Sofia Gomez tiene 1 libro(s) prestado(s)
El principito -> estudiante: Sofia Gomez
```

## 🟡 Intermedio 02 — Declarar una agregación

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

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Libro> libros = new ArrayList<>();

    public Biblioteca(String nombre) {
        this.nombre = nombre;
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        Libro libro1 = new Libro("Cien anios de soledad");
        Libro libro2 = new Libro("El principito");

        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);

        System.out.println(biblioteca.getNombre() + " tiene " + biblioteca.getLibros().size() + " libro(s)");
        for (Libro libro : biblioteca.getLibros()) {
            System.out.println("- " + libro.getTitulo());
        }
    }
}
```

Salida real (con libros agregados):

```text
Biblioteca Central tiene 2 libro(s)
- Cien anios de soledad
- El principito
```

Y el caso de la colección agregada vacía:

```java falla-en-ejecucion
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

class Libro {
    private String titulo;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}

class Biblioteca {
    private String nombre;
    private List<Libro> libros = new ArrayList<>();

    public Biblioteca(String nombre) {
        this.nombre = nombre;
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }
}

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        // Todavia no se agrego ningun Libro
        System.out.println(biblioteca.getLibros().get(0).getTitulo());
    }
}
```

```text
Exception in thread "main" java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
```

## 🟡 Intermedio 03 — Declarar una composición

```java
package com.biblioteca;

public class Comprobante {
    private int numero;
    private String fecha;

    public Comprobante(int numero, String fecha) {
        this.numero = numero;
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public String getFecha() {
        return fecha;
    }
}
```

```java
package com.biblioteca;

public class Prestamo {
    private String libro;
    private Comprobante comprobante;

    public Prestamo(String libro, int numeroComprobante, String fecha) {
        this.libro = libro;
        this.comprobante = new Comprobante(numeroComprobante, fecha);
    }

    public String getLibro() {
        return libro;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Prestamo prestamo = new Prestamo("Cien anios de soledad", 9001, "2026-09-24");

        System.out.println(prestamo.getLibro() + " - Comprobante N" + prestamo.getComprobante().getNumero()
                + " (" + prestamo.getComprobante().getFecha() + ")");
    }
}
```

Salida real:

```text
Cien anios de soledad - Comprobante N9001 (2026-09-24)
```

## 🔴 Avanzado 01 — Corregir un programa con errores de relaciones

Solución completa, con las dos rondas corregidas en el mismo programa:

```java
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

class Comprobante {
    private int numero;

    public Comprobante(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}

class Prestamo {
    private String libro;
    private Comprobante comprobante;

    public Prestamo(String libro, int numeroComprobante) {
        this.libro = libro;
        this.comprobante = new Comprobante(numeroComprobante);
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
        // Ronda 1 corregida: el vinculo se crea con estudiante.prestar(libro), que actualiza ambos extremos
        Estudiante estudiante = new Estudiante("Lucia Fernandez");
        Libro libro = new Libro("El principito");
        estudiante.prestar(libro);
        System.out.println(estudiante.getNombre() + " tiene " + estudiante.getLibrosPrestados().size() + " libro(s) prestado(s)");
        System.out.println(libro.getTitulo() + " -> estudiante: " + libro.getEstudiante().getNombre());

        // Ronda 2 corregida: cada Prestamo crea su propio Comprobante dentro del constructor
        Prestamo prestamo1 = new Prestamo("Cien anios de soledad", 9001);
        Prestamo prestamo2 = new Prestamo("El principito", 9002);
        boolean mismoComprobante = prestamo1.getComprobante() == prestamo2.getComprobante();
        System.out.println(prestamo1.getLibro() + " - Comprobante N" + prestamo1.getComprobante().getNumero());
        System.out.println(prestamo2.getLibro() + " - Comprobante N" + prestamo2.getComprobante().getNumero());
        System.out.println("prestamo1 y prestamo2 comparten el mismo Comprobante: " + mismoComprobante);
    }
}
```

Salida real, ya corregida:

```text
Lucia Fernandez tiene 1 libro(s) prestado(s)
El principito -> estudiante: Lucia Fernandez
Cien anios de soledad - Comprobante N9001
El principito - Comprobante N9002
prestamo1 y prestamo2 comparten el mismo Comprobante: false
```

## 🏆 Desafío 01 — Diseñar una relación nueva que combine dos tipos

```java
package com.biblioteca;

public class Bibliotecario {
    private String nombre;

    public Bibliotecario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

```java
package com.biblioteca;

public class Presupuesto {
    private double montoAnual;

    public Presupuesto(double montoAnual) {
        this.montoAnual = montoAnual;
    }

    public double getMontoAnual() {
        return montoAnual;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nombre;
    private List<Bibliotecario> bibliotecarios = new ArrayList<>();
    private Presupuesto presupuesto;

    public Departamento(String nombre, double montoAnualPresupuesto) {
        this.nombre = nombre;
        this.presupuesto = new Presupuesto(montoAnualPresupuesto);
    }

    public void agregarBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarios.add(bibliotecario);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Departamento departamento = new Departamento("Departamento de Circulacion", 25000.0);
        Bibliotecario bibliotecario1 = new Bibliotecario("Pedro Soto");
        Bibliotecario bibliotecario2 = new Bibliotecario("Elena Rios");

        departamento.agregarBibliotecario(bibliotecario1);
        departamento.agregarBibliotecario(bibliotecario2);

        System.out.println(departamento.getNombre() + " tiene " + departamento.getBibliotecarios().size() + " bibliotecario(s)");
        for (Bibliotecario bibliotecario : departamento.getBibliotecarios()) {
            System.out.println("- " + bibliotecario.getNombre());
        }
        System.out.println("Presupuesto anual: " + departamento.getPresupuesto().getMontoAnual());
    }
}
```

Salida real:

```text
Departamento de Circulacion tiene 2 bibliotecario(s)
- Pedro Soto
- Elena Rios
Presupuesto anual: 25000.0
```
