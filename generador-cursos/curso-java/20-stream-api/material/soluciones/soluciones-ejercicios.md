# 🔑 Soluciones de los ejercicios — Módulo 20

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar código que Stream API simplificaría (creación)

`catalogo` es un arreglo (`LibroCatalogo[]`): `Arrays.stream(catalogo)` crea un stream a partir de él,
recorrible con `forEach`, sin necesitar un índice manual.

## 🟡 Intermedio 01 — Aplicar la creación de un Stream

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;

    public LibroCatalogo(String titulo) {
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
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        LibroCatalogo quijote = new LibroCatalogo("El Quijote");
        LibroCatalogo rayuela = new LibroCatalogo("Rayuela");
        LibroCatalogo[] catalogo = {quijote, rayuela};

        Consumer<LibroCatalogo> imprimir = libro -> System.out.println("- " + libro.getTitulo());

        System.out.println("Catalogo con Stream.of():");
        Stream.of(quijote, rayuela).forEach(imprimir);

        System.out.println("Catalogo a partir del arreglo:");
        Arrays.stream(catalogo).forEach(imprimir);

        System.out.println("Catalogo con Stream.<LibroCatalogo>builder():");
        Stream.<LibroCatalogo>builder()
                .add(quijote)
                .add(rayuela)
                .build()
                .forEach(imprimir);

        List<LibroCatalogo> lista = new ArrayList<>();
        lista.add(quijote);
        lista.add(rayuela);

        System.out.println("Catalogo a partir de una lista:");
        lista.stream().forEach(imprimir);
    }
}
```

```text
Catalogo con Stream.of():
- El Quijote
- Rayuela
Catalogo a partir del arreglo:
- El Quijote
- Rayuela
Catalogo con Stream.<LibroCatalogo>builder():
- El Quijote
- Rayuela
Catalogo a partir de una lista:
- El Quijote
- Rayuela
```

## 🟢 Básico 02 — Identificar código que Stream API simplificaría (map)

El bucle transforma cada `LibroCatalogo` en un `String`, manteniendo la misma cantidad de elementos:
`map` con una `Function<LibroCatalogo, String>` encaja exactamente en esa forma.

## 🟡 Intermedio 02 — Aplicar el operador map

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final String autor;

    public LibroCatalogo(String titulo, String autor) {
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Demo {
    public static void main(String[] args) {
        List<LibroCatalogo> libros = new ArrayList<>();
        libros.add(new LibroCatalogo("El Quijote", "Cervantes"));
        libros.add(new LibroCatalogo("Rayuela", "Cortazar"));

        Function<LibroCatalogo, String> aLinea = libro -> libro.getTitulo() + " - " + libro.getAutor();

        System.out.println("Catalogo:");
        libros.stream().map(aLinea).forEach(linea -> System.out.println("- " + linea));
    }
}
```

```text
Catalogo:
- El Quijote - Cervantes
- Rayuela - Cortazar
```

## 🟢 Básico 03 — Identificar código que Stream API simplificaría (filter)

El bucle selecciona los libros que cumplen una condición (`isDisponible()`), pudiendo reducir la
cantidad de elementos: `filter` con un `Predicate<LibroCatalogo>` encaja exactamente en esa forma.

## 🟡 Intermedio 03 — Aplicar el operador filter

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final boolean disponible;

    public LibroCatalogo(String titulo, boolean disponible) {
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
        List<LibroCatalogo> libros = new ArrayList<>();
        libros.add(new LibroCatalogo("El Quijote", true));
        libros.add(new LibroCatalogo("Rayuela", false));

        Predicate<LibroCatalogo> estaDisponible = libro -> libro.isDisponible();

        System.out.println("Libros disponibles:");
        libros.stream().filter(estaDisponible).forEach(libro -> System.out.println("- " + libro.getTitulo()));
    }
}
```

```text
Libros disponibles:
- El Quijote
```

## 🟢 Básico 04 — Identificar código que Stream API simplificaría (anyMatch)

El bucle solo necesita un resultado `true`/`false` (si existe al menos un libro con ese autor), no la
lista de coincidencias: `anyMatch` con un `Predicate<LibroCatalogo>` encaja exactamente en esa forma.

## 🟡 Intermedio 04 — Aplicar el operador anyMatch

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final String autor;

    public LibroCatalogo(String titulo, String autor) {
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        List<LibroCatalogo> libros = new ArrayList<>();
        libros.add(new LibroCatalogo("El Quijote", "Cervantes"));
        libros.add(new LibroCatalogo("Rayuela", "Cortazar"));

        Predicate<LibroCatalogo> esDeCervantes = libro -> libro.getAutor().equals("Cervantes");
        boolean hayCervantes = libros.stream().anyMatch(esDeCervantes);
        System.out.println("¿Hay algun libro de Cervantes? " + hayCervantes);
    }
}
```

```text
¿Hay algun libro de Cervantes? true
```

## 🟢 Básico 05 — Identificar código que Stream API simplificaría (flatMap)

El bucle anidado recorre una estructura de listas dentro de una lista (`List<List<LibroCatalogo>>`)
para producir una única lista aplanada de libros: `flatMap` encaja exactamente en esa forma, a
diferencia de `map` (que dejaría la estructura anidada intacta).

## 🟡 Intermedio 05 — Aplicar el operador flatMap

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;

    public LibroCatalogo(String titulo) {
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
        List<List<LibroCatalogo>> estanterias = new ArrayList<>();

        List<LibroCatalogo> estanteria1 = new ArrayList<>();
        estanteria1.add(new LibroCatalogo("El Quijote"));
        estanteria1.add(new LibroCatalogo("Rayuela"));
        estanterias.add(estanteria1);

        List<LibroCatalogo> estanteria2 = new ArrayList<>();
        estanteria2.add(new LibroCatalogo("Cien Anios de Soledad"));
        estanterias.add(estanteria2);

        Consumer<LibroCatalogo> mostrarLibro = libro -> System.out.println("- " + libro.getTitulo());
        List<LibroCatalogo> todos = estanterias.stream()
                .flatMap(estanteria -> estanteria.stream())
                .toList();

        System.out.println("Total de libros en todas las estanterias: " + todos.size());
        todos.forEach(mostrarLibro);
    }
}
```

```text
Total de libros en todas las estanterias: 3
- El Quijote
- Rayuela
- Cien Anios de Soledad
```

## 🟢 Básico 06 — Identificar código que Stream API simplificaría (conversión)

El bucle selecciona libros según una condición (`paginas > 300`) y transforma el resultado en una
lista de títulos: la combinación `filter` + `map`, cerrada con `.toList()`, encaja exactamente en esa
forma.

## 🟡 Intermedio 06 — Aplicar la conversión List/Stream

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final int paginas;

    public LibroCatalogo(String titulo, int paginas) {
        this.titulo = titulo;
        this.paginas = paginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPaginas() {
        return paginas;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<LibroCatalogo> libros = new ArrayList<>();
        libros.add(new LibroCatalogo("El Quijote", 863));
        libros.add(new LibroCatalogo("Rayuela", 635));
        libros.add(new LibroCatalogo("El Principito", 96));

        List<String> titulosExtensos = libros.stream()
                .filter(libro -> libro.getPaginas() > 300)
                .map(libro -> libro.getTitulo())
                .toList();

        System.out.println("Libros con mas de 300 paginas:");
        for (String titulo : titulosExtensos) {
            System.out.println("- " + titulo);
        }
    }
}
```

```text
Libros con mas de 300 paginas:
- El Quijote
- Rayuela
```

## 🔴 Avanzado 01 — Corregir un diseño con dos sub-temas técnicos ausentes

**Corrección 1 (`flatMap` ausente) y Corrección 2 (`filter` ausente)**, aplicadas juntas:

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;
    private final boolean disponible;

    public LibroCatalogo(String titulo, boolean disponible) {
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

public class Demo {
    public static void main(String[] args) {
        List<List<LibroCatalogo>> estanterias = new ArrayList<>();

        List<LibroCatalogo> estanteria1 = new ArrayList<>();
        estanteria1.add(new LibroCatalogo("El Quijote", true));
        estanteria1.add(new LibroCatalogo("Rayuela", false));
        estanterias.add(estanteria1);

        List<LibroCatalogo> estanteria2 = new ArrayList<>();
        estanteria2.add(new LibroCatalogo("Cien Anios de Soledad", true));
        estanteria2.add(new LibroCatalogo("El Principito", false));
        estanterias.add(estanteria2);

        List<String> titulosDisponibles = estanterias.stream()
                .flatMap(estanteria -> estanteria.stream())
                .filter(libro -> libro.isDisponible())
                .map(libro -> libro.getTitulo())
                .toList();

        System.out.println("Libros disponibles en todas las estanterias:");
        for (String titulo : titulosDisponibles) {
            System.out.println("- " + titulo);
        }
    }
}
```

```text
Libros disponibles en todas las estanterias:
- El Quijote
- Cien Anios de Soledad
```

## 🏆 Desafío 01 — Diseñar un pipeline nuevo combinando operadores

Una solución posible: un pipeline que combina `filter` (reservas con más de 7 días de espera) y `map`
(genera el mensaje de notificación), cerrado con `.toList()`.

```java
package com.biblioteca;

public class ReservaLibro {
    private final String estudiante;
    private final int diasEspera;

    public ReservaLibro(String estudiante, int diasEspera) {
        this.estudiante = estudiante;
        this.diasEspera = diasEspera;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public int getDiasEspera() {
        return diasEspera;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<ReservaLibro> reservas = new ArrayList<>();
        reservas.add(new ReservaLibro("Ana Torres", 9));
        reservas.add(new ReservaLibro("Luis Rios", 3));
        reservas.add(new ReservaLibro("Marta Diaz", 12));

        List<String> notificaciones = reservas.stream()
                .filter(reserva -> reserva.getDiasEspera() > 7)
                .map(reserva -> reserva.getEstudiante() + ": reserva con " + reserva.getDiasEspera()
                        + " dias de espera")
                .toList();

        System.out.println("Notificaciones a enviar:");
        for (String notificacion : notificaciones) {
            System.out.println("- " + notificacion);
        }
    }
}
```

```text
Notificaciones a enviar:
- Ana Torres: reserva con 9 dias de espera
- Marta Diaz: reserva con 12 dias de espera
```
