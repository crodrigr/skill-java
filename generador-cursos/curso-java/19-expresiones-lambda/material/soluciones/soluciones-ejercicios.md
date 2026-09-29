# 🔑 Soluciones de los ejercicios — Módulo 19

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar código que una expresión lambda simplificaría (Consumer)

`AccionLibro`/`RegistradorDevolucion` solo reciben un `LibroBiblioteca` y no devuelven nada:
`Consumer<LibroBiblioteca>` encaja exactamente en esa forma, sin necesitar una interfaz ni una clase
propias.

## 🟡 Intermedio 01 — Aplicar Consumer

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;

    public LibroBiblioteca(String titulo) {
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
import java.util.function.Consumer;

public class Demo {
    public static void main(String[] args) {
        List<LibroBiblioteca> libros = new ArrayList<>();
        libros.add(new LibroBiblioteca("El Quijote"));
        libros.add(new LibroBiblioteca("Rayuela"));

        Consumer<LibroBiblioteca> accion = libro -> System.out.println("Devuelto: " + libro.getTitulo());
        for (LibroBiblioteca libro : libros) {
            accion.accept(libro);
        }
    }
}
```

```text
Devuelto: El Quijote
Devuelto: Rayuela
```

## 🟢 Básico 02 — Identificar código que una expresión lambda simplificaría (Supplier)

`GeneradorCodigo`/`GeneradorCodigoSecuencial` no reciben ningún parámetro y devuelven un `String`:
`Supplier<String>` encaja exactamente en esa forma, sin necesitar una interfaz ni una clase propias.

## 🟡 Intermedio 02 — Aplicar Supplier

```java
package com.biblioteca;

import java.util.function.Supplier;

public class Demo {
    private static int contador = 0;

    public static void main(String[] args) {
        Supplier<String> generador = () -> "PRESTAMO-" + (++contador);
        System.out.println(generador.get());
        System.out.println(generador.get());
    }
}
```

```text
PRESTAMO-1
PRESTAMO-2
```

## 🟢 Básico 03 — Identificar código que una expresión lambda simplificaría (Function)

`FormateadorRecibo`/`FormateadorReciboSimple` reciben un `LibroBiblioteca` y devuelven un `String`:
`Function<LibroBiblioteca, String>` encaja exactamente en esa forma, sin necesitar una interfaz ni una
clase propias.

## 🟡 Intermedio 03 — Aplicar Function

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;
    private final String autor;

    public LibroBiblioteca(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}
```

```java
package com.biblioteca;

import java.util.function.Function;

public class Demo {
    public static void main(String[] args) {
        LibroBiblioteca libro = new LibroBiblioteca("El Quijote", "Cervantes");
        Function<LibroBiblioteca, String> formateador = l -> l.getTitulo() + " - " + l.getAutor();
        System.out.println(formateador.apply(libro));
    }
}
```

```text
El Quijote - Cervantes
```

## 🟢 Básico 04 — Identificar código que una expresión lambda simplificaría (Predicate)

`CondicionLibro`/`EstaDisponible` reciben un `LibroBiblioteca` y devuelven un `boolean`:
`Predicate<LibroBiblioteca>` encaja exactamente en esa forma, sin necesitar una interfaz ni una clase
propias.

## 🟡 Intermedio 04 — Aplicar Predicate

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;
    private final boolean disponible;

    public LibroBiblioteca(String titulo, boolean disponible) {
        this.titulo = titulo;
        this.disponible = disponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponible() {
        return disponible;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        List<LibroBiblioteca> libros = new ArrayList<>();
        libros.add(new LibroBiblioteca("El Quijote", true));
        libros.add(new LibroBiblioteca("Rayuela", false));

        Predicate<LibroBiblioteca> condicion = libro -> libro.isDisponible();
        for (LibroBiblioteca libro : libros) {
            if (condicion.test(libro)) {
                System.out.println(libro.getTitulo());
            }
        }
    }
}
```

```text
El Quijote
```

## 🟢 Básico 05 — Identificar un caso que necesita una interfaz funcional propia

`DescriptorPrestamo` recibe dos parámetros de tipos distintos (`titulo`, `socio`), algo que ninguna de
las cuatro interfaces estándar admite: este caso necesita una interfaz funcional propia, anotada
`@FunctionalInterface`.

## 🟡 Intermedio 05 — Diseñar e implementar una interfaz funcional propia

```java
package com.biblioteca;

@FunctionalInterface
public interface DescriptorPrestamo {
    String describir(String titulo, String socio);
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        DescriptorPrestamo descriptor = (titulo, socio) -> titulo + " prestado a " + socio;
        System.out.println(descriptor.describir("El Quijote", "Carla Nunez"));
    }
}
```

```text
El Quijote prestado a Carla Nunez
```

## 🔴 Avanzado 01 — Corregir un diseño con dos sub-temas técnicos ausentes

**Corrección 1 (Function ausente) y Corrección 2 (Predicate ausente)**, aplicadas juntas:

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;
    private final boolean disponible;

    public LibroBiblioteca(String titulo, boolean disponible) {
        this.titulo = titulo;
        this.disponible = disponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponible() {
        return disponible;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        List<LibroBiblioteca> libros = new ArrayList<>();
        libros.add(new LibroBiblioteca("El Quijote", true));
        libros.add(new LibroBiblioteca("Rayuela", false));

        Function<LibroBiblioteca, String> formateador = libro -> libro.getTitulo();
        Predicate<LibroBiblioteca> condicion = libro -> libro.isDisponible();

        for (LibroBiblioteca libro : libros) {
            if (condicion.test(libro)) {
                System.out.println(formateador.apply(libro));
            }
        }
    }
}
```

```text
El Quijote
```

Comparado con la versión original: `Formateador`/`FormateadorSimple` se reemplazan por
`Function<LibroBiblioteca, String>`, y `CondicionLibro`/`EstaDisponible` por
`Predicate<LibroBiblioteca>`, ambos con expresiones lambda, produciendo el mismo resultado.

## 🏆 Desafío 01 — Diseñar un caso nuevo combinando interfaces funcionales

Una solución posible: un sistema de multas que combina `Predicate<LibroBiblioteca>` (tiene multa),
`Function<LibroBiblioteca, Double>` (calcula el monto) y `Consumer<LibroBiblioteca>` (notifica), cada uno
con una responsabilidad clara.

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;
    private final int diasVencido;

    public LibroBiblioteca(String titulo, int diasVencido) {
        this.titulo = titulo;
        this.diasVencido = diasVencido;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDiasVencido() {
        return diasVencido;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        List<LibroBiblioteca> libros = new ArrayList<>();
        libros.add(new LibroBiblioteca("El Quijote", 5));
        libros.add(new LibroBiblioteca("Rayuela", 0));

        Predicate<LibroBiblioteca> tieneMulta = libro -> libro.getDiasVencido() > 0;
        Function<LibroBiblioteca, Double> calcularMulta = libro -> libro.getDiasVencido() * 0.5;
        Consumer<LibroBiblioteca> notificar = libro ->
                System.out.println(libro.getTitulo() + ": multa de $" + calcularMulta.apply(libro));

        for (LibroBiblioteca libro : libros) {
            if (tieneMulta.test(libro)) {
                notificar.accept(libro);
            }
        }
    }
}
```

```text
El Quijote: multa de $2.5
```
