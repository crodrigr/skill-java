# 🔑 Soluciones — Ejercicios Módulo 7

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/07-herencia-y-polimorfismo.md`).

## 🟢 Básico 01 — Leer un diagrama de clases

1. `MaterialBibliografico` es la superclase (la flecha `MaterialBibliografico <|-- Libro` apunta a ella).
2. `Libro` y `Revista` heredan `getTitulo()` (y el atributo `titulo`) de `MaterialBibliografico`.
3. `Libro` tiene `paginas`; `Revista` tiene `numeroEdicion`.
4. `#` marca `protected`: accesible desde la misma clase, el mismo paquete, y (a partir de este módulo)
   una subclase de otro paquete.

## 🟢 Básico 02 — ¿Qué hereda esta subclase?

1. `Libro` hereda `getTitulo()` (`public`) y `titulo` (`protected`): al ser `protected`, `Libro` puede
   acceder a `titulo` directamente, sin pasar por `getTitulo()`.
2. No: los constructores nunca se heredan.
3. No compilaría: al no invocar `super(...)`, Java intenta invocar `MaterialBibliografico()` (sin
   argumentos) de forma implícita, y esa clase no tiene ese constructor.
4. Sí podría: con herencia real, `protected` alcanza a una subclase de otro paquete (Ejemplo 03) —
   siempre que acceda a su **propio** miembro heredado, no a través de una referencia del tipo de la
   superclase.

| Fragmento | ¿Compila? |
|---|---|
| Con `super(titulo)` | Sí |
| Sin `super(titulo)` | No — `Implicit super constructor MaterialBibliografico() is undefined. Must explicitly invoke another constructor` |

## 🟡 Intermedio 01 — Declarar una jerarquía de dos niveles

```java
package com.biblioteca;

public class MaterialBibliografico {
    protected String titulo;

    public MaterialBibliografico(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
```

```java
package com.biblioteca;

public class Libro extends MaterialBibliografico {
    private int paginas;

    public Libro(String titulo, int paginas) {
        super(titulo);
        this.paginas = paginas;
    }

    public int getPaginas() {
        return paginas;
    }

    public static void main(String[] args) {
        Libro libro = new Libro("El principito", 96);
        System.out.println(libro.getTitulo());
        System.out.println(libro.getPaginas());
    }
}
```

Salida real del programa:

```text
El principito
96
```

## 🟢 Básico 03 — Sobrecarga o sobre-escritura

1. `Recordatorio.enviar()`/`enviar(String)` es **sobrecarga**: misma clase, firma distinta.
2. `NotificacionUrgente.enviar()` con `@Override` es **sobre-escritura**: relación de herencia
   (`extends Notificacion`), misma firma exacta.

## 🟢 Básico 04 — ¿Qué versión se ejecuta?

`m1.calcularDiasPrestamo()` imprime `14` (tipo real `Libro`); `m2.calcularDiasPrestamo()` imprime `7`
(tipo real `Revista`). El tipo declarado (`MaterialBibliografico`) no determina qué versión se ejecuta:
lo decide el tipo real del objeto, en tiempo de ejecución.

## 🟡 Intermedio 02 — Sobrecargar un método o constructor

```java
package com.biblioteca;

public class MaterialBibliografico {
    protected String titulo;

    public MaterialBibliografico(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int calcularDiasPrestamo() {
        return 10;
    }

    public String registrarPrestamo(String usuario) {
        return titulo + " prestado a " + usuario + " (sin fecha de devolución fijada)";
    }

    public String registrarPrestamo(String usuario, String fechaDevolucion) {
        return titulo + " prestado a " + usuario + " (devolver antes del " + fechaDevolucion + ")";
    }
}
```

Salida real del programa:

```text
Cien años de soledad prestado a Ana Torres (sin fecha de devolución fijada)
Cien años de soledad prestado a Carlos Ramírez (devolver antes del 2026-10-01)
```

Es sobrecarga porque ambas versiones de `registrarPrestamo` están en la misma clase
(`MaterialBibliografico`) y se distinguen por su lista de parámetros, no por una relación de herencia.

## 🟡 Intermedio 03 — Sobreescribir con @Override

```java
package com.biblioteca;

public class MaterialBibliografico {
    protected String titulo;

    public MaterialBibliografico(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int calcularDiasPrestamo() {
        return 10;
    }
}
```

```java
package com.biblioteca;

public class MaterialEspecial extends MaterialBibliografico {
    public MaterialEspecial(String titulo) {
        super(titulo);
    }

    @Override
    public int calcularDiasPrestamo() {
        return super.calcularDiasPrestamo() + 3;
    }
}
```

Salida real del programa:

```text
10
13
```

## 🟢 Básico 05 — El error de instanciar

`new RecursoDigital(...)` no compila: `RecursoDigital` es `abstract`. Mensaje real:

```text
✖ Cannot instantiate the type RecursoDigital Java(16777373) [Ln 5, Col 38]
```

`new EBook(...)` sí compila: `EBook` es una subclase concreta que implementa `descargar()`.

## 🟢 Básico 06 — ¿Clase abstracta o interfaz?

- **Escenario A**: clase abstracta. Todo material bibliográfico comparte estado (`titulo`) y parte del
  comportamiento; son subclases emparentadas de un mismo concepto.
- **Escenario B**: interfaz. `Libro` y `SalaDeEstudio` no comparten ninguna relación de herencia ni
  estado: solo la capacidad de "reservarse", un contrato puro.

## 🟡 Intermedio 04 — Declarar una interfaz completa

```java
package com.biblioteca;

public interface Renovable {
    boolean renovar();
}
```

```java
package com.biblioteca;

public class Prestamo implements Renovable {
    private static final int MAXIMO_RENOVACIONES = 2;

    private String titulo;
    private int renovaciones;

    public Prestamo(String titulo) {
        this.titulo = titulo;
        this.renovaciones = 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getRenovaciones() {
        return renovaciones;
    }

    @Override
    public boolean renovar() {
        if (renovaciones >= MAXIMO_RENOVACIONES) {
            return false;
        }
        renovaciones++;
        return true;
    }
}
```

Salida real del programa:

```text
true
true
false
2
```

## 🔴 Avanzado 01 — Corregir un programa con errores de herencia

**Ronda 1** (falta `super(...)`):

```text
⚠ The value of the field Libro.paginas is not used Java(570425421) [Ln 4, Col 17]
✖ Implicit super constructor MaterialBibliografico() is undefined. Must explicitly invoke another constructor Java(134217871) [Ln 6, Col 12]
```

**Ronda 2** (acceso más restrictivo al sobreescribir, tras corregir la ronda 1):

```text
⚠ The value of the field Libro.paginas is not used Java(570425421) [Ln 4, Col 17]
✖ Cannot reduce the visibility of the inherited method from MaterialBibliografico Java(67109273) [Ln 12, Col 19]
```

**Solución final**:

```java
package com.biblioteca;

public class MaterialBibliografico {
    protected String titulo;

    public MaterialBibliografico(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int calcularDiasPrestamo() {
        return 10;
    }
}
```

```java
package com.biblioteca;

class Libro extends MaterialBibliografico {
    private int paginas;

    public Libro(String titulo, int paginas) {
        super(titulo);
        this.paginas = paginas;
    }

    @Override
    public int calcularDiasPrestamo() {
        return 14;
    }
}

public class RevisionDePrestamos {
    public static void main(String[] args) {
        Libro libro = new Libro("Cien años de soledad", 471);
        System.out.println(libro.getTitulo());
        System.out.println(libro.calcularDiasPrestamo());
    }
}
```

Salida real del programa:

```text
Cien años de soledad
14
```

## 🏆 Desafío 01 — Diseñar una jerarquía nueva

```java
package com.biblioteca;

public abstract class Usuario {
    protected String nombreCompleto;

    public Usuario(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public abstract String describirRol();
}
```

```java
package com.biblioteca;

public interface Prestable {
    boolean puedeAmpliarPrestamo();
}
```

```java
package com.biblioteca;

public class Estudiante extends Usuario implements Prestable {
    private String carrera;
    private int prestamosActivos;

    public Estudiante(String nombreCompleto, String carrera, int prestamosActivos) {
        super(nombreCompleto);
        this.carrera = carrera;
        this.prestamosActivos = prestamosActivos;
    }

    public Estudiante(String nombreCompleto, String carrera) {
        this(nombreCompleto, carrera, 0);
    }

    public String getCarrera() {
        return carrera;
    }

    @Override
    public String describirRol() {
        return "Estudiante de " + carrera;
    }

    @Override
    public boolean puedeAmpliarPrestamo() {
        return prestamosActivos < 3;
    }
}
```

```java
package com.biblioteca;

public class Docente extends Usuario {
    private String departamento;

    public Docente(String nombreCompleto, String departamento) {
        super(nombreCompleto);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String describirRol() {
        return "Docente del departamento de " + departamento;
    }
}
```

Salida real del programa:

```text
Estudiante de Ingeniería
Estudiante de Biología
Docente del departamento de Literatura
false
true
```
