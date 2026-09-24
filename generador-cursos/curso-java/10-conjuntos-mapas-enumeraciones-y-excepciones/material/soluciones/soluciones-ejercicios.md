# 🔑 Soluciones — Ejercicios del Módulo 10

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Predecir un Set con duplicados

`categorias.size()` da `2`: `"Novela"` se agrega dos veces, pero la segunda no cambia nada. Salida real:

```text
size=2
```

## 🟢 Básico 02 — Predecir una operación de conjuntos

Salida real:

```text
interseccion=[Realismo magico]
union.size=3
```

## 🟡 Intermedio 01 — Declarar y operar un Set

```java
package com.biblioteca;

import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Set<String> categorias = new HashSet<>();
        String[] nuevas = { "Novela", "Realismo magico", "Novela", "Clasico" };

        for (String categoria : nuevas) {
            categorias.add(categoria);
        }

        System.out.println("size=" + categorias.size());

        Set<String> categoriasDestacadas = new HashSet<>();
        categoriasDestacadas.add("Novela");
        categoriasDestacadas.add("Clasico");

        Set<String> interseccion = new HashSet<>(categorias);
        interseccion.retainAll(categoriasDestacadas);
        System.out.println("interseccion=" + interseccion);
    }
}
```

Salida real:

```text
size=3
interseccion=[Clasico, Novela]
```

## 🟢 Básico 03 — Predecir un Map tras varios put

Salida real:

```text
size=3
Paracetamol=80
```

## 🟢 Básico 04 — Predecir el orden de un TreeMap

Salida real:

```text
HashMap keySet=[Paracetamol, Amoxicilina, Ibuprofeno]
TreeMap keySet=[Amoxicilina, Ibuprofeno, Paracetamol]
```

## 🟡 Intermedio 02 — Declarar y operar un Map

```java
package com.medisalud;

import java.util.Map;
import java.util.TreeMap;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada: el listado debe quedar ordenado por historia clinica, asi que se
        // elige TreeMap en vez de HashMap.
        Map<String, String> pacientesPorHistoria = new TreeMap<>();
        pacientesPorHistoria.put("HC-1002", "Jorge Paz");
        pacientesPorHistoria.put("HC-1001", "Marta Diaz");
        pacientesPorHistoria.put("HC-1003", "Ana Torres");

        for (Map.Entry<String, String> entrada : pacientesPorHistoria.entrySet()) {
            System.out.println(entrada.getKey() + " -> " + entrada.getValue());
        }
    }
}
```

Salida real:

```text
HC-1001 -> Marta Diaz
HC-1002 -> Jorge Paz
HC-1003 -> Ana Torres
```

## 🟢 Básico 05 — Predecir values()/ordinal() de un enum

Salida real:

```text
ACTIVO=0
DEVUELTO=1
VENCIDO=2
```

## 🟡 Intermedio 03 — Declarar un enum y usarlo en un switch

```java
package com.biblioteca;

public class Demo {
    enum EstadoPrestamo {
        ACTIVO, DEVUELTO, VENCIDO
    }

    public static void main(String[] args) {
        EstadoPrestamo estado = EstadoPrestamo.ACTIVO;
        switch (estado) {
            case ACTIVO:
                System.out.println("El prestamo esta activo");
                break;
            case DEVUELTO:
                System.out.println("El prestamo ya se devolvio");
                break;
            case VENCIDO:
                System.out.println("El prestamo esta vencido");
                break;
        }
    }
}
```

Salida real:

```text
El prestamo esta activo
```

## 🟢 Básico 06 — Identificar qué catch se ejecuta

En el Fragmento 1 se ejecuta el `catch (NullPointerException e)`, porque coincide exactamente con lo
que se lanza:

```text
A: capturado NullPointerException
```

El Fragmento 2 no compila:

```text
./Fragmento2.java:7: error: exception NumberFormatException has already been caught
        } catch (NumberFormatException e) {
          ^
1 error
```

## 🟡 Intermedio 04 — Agregar try/catch/finally a un programa que falla

```java
package com.medisalud;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        List<String> pacientesEnEspera = new ArrayList<>();
        pacientesEnEspera.add("Marta Diaz");
        pacientesEnEspera.add("Jorge Paz");

        try {
            System.out.println("Siguiente paciente: " + pacientesEnEspera.get(5));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No hay ningun paciente en esa posicion de la lista de espera");
        } finally {
            System.out.println("Consulta de la lista de espera finalizada");
        }
        System.out.println("El programa sigue despues del try/catch/finally");
    }
}
```

Salida real:

```text
No hay ningun paciente en esa posicion de la lista de espera
Consulta de la lista de espera finalizada
El programa sigue despues del try/catch/finally
```

## 🔴 Avanzado 01 — Corregir un programa con errores de esta etapa

Solución completa, con las dos rondas corregidas en el mismo programa:

```java
package com.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class RevisionDeCatalogo {
    public static void main(String[] args) {
        // Ronda 1 corregida: el catch mas especifico va primero
        try {
            Integer.parseInt("no-es-un-numero");
        } catch (NumberFormatException e) {
            System.out.println("Numero invalido: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error general: " + e.getMessage());
        }

        // Ronda 2 corregida: el acceso a una clave inexistente se maneja con try/catch
        Map<String, String> catalogoLibros = new HashMap<>();
        catalogoLibros.put("LIB-012", "Cien anios de soledad");

        try {
            String titulo = catalogoLibros.get("LIB-999");
            System.out.println("longitud=" + titulo.length());
        } catch (NullPointerException e) {
            System.out.println("No se encontro el libro pedido");
        }
    }
}
```

Salida real, ya corregida:

```text
Numero invalido: For input string: "no-es-un-numero"
No se encontro el libro pedido
```

## 🔴 Avanzado 02 — Diseñar una excepción personalizada

```java
package com.biblioteca;

public class PrestamoInvalidoException extends RuntimeException {
    public PrestamoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
```

```java
package com.biblioteca;

import java.util.HashSet;
import java.util.Set;

public class Demo {
    static void validarPrestamo(String libro, Set<String> librosPrestados) {
        if (librosPrestados.contains(libro)) {
            throw new PrestamoInvalidoException("El libro ya tiene un prestamo activo: " + libro);
        }
    }

    public static void main(String[] args) {
        // Datos de entrada
        Set<String> librosPrestados = new HashSet<>();
        librosPrestados.add("LIB-012");

        try {
            validarPrestamo("LIB-012", librosPrestados);
            System.out.println("Prestamo registrado");
        } catch (PrestamoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            validarPrestamo("LIB-030", librosPrestados);
            System.out.println("Prestamo registrado");
        } catch (PrestamoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

Salida real:

```text
Error: El libro ya tiene un prestamo activo: LIB-012
Prestamo registrado
```

## 🏆 Desafío 01 — Combinar Set y Map

```java
package com.biblioteca;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Demo {
    static void agregarLibro(Map<String, Set<String>> librosPorCategoria, String categoria, String titulo) {
        if (!librosPorCategoria.containsKey(categoria)) {
            librosPorCategoria.put(categoria, new HashSet<>());
        }
        librosPorCategoria.get(categoria).add(titulo);
    }

    public static void main(String[] args) {
        // Datos de entrada
        Map<String, Set<String>> librosPorCategoria = new HashMap<>();

        agregarLibro(librosPorCategoria, "Novela", "Cien anios de soledad");
        agregarLibro(librosPorCategoria, "Novela", "Rayuela");
        agregarLibro(librosPorCategoria, "Novela", "Cien anios de soledad");
        agregarLibro(librosPorCategoria, "Infantil", "El principito");

        System.out.println("categorias=" + librosPorCategoria.keySet().size());
        System.out.println("Novela.size=" + librosPorCategoria.get("Novela").size());
        System.out.println("Infantil.size=" + librosPorCategoria.get("Infantil").size());
    }
}
```

Salida real:

```text
categorias=2
Novela.size=2
Infantil.size=1
```

