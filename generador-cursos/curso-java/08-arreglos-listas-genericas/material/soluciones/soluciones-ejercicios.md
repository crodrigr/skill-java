# 🔑 Soluciones — Ejercicios Módulo 8

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/08-arreglos-listas-genericas.md`).

## 🟢 Básico 01 — Identificar la estructura de datos adecuada

1. Un arreglo agrupa los 4 títulos en una sola variable, sin repetir la declaración por cada libro.
2. El arreglo escala mejor: agregar 50 libros más no requiere declarar variables nuevas, solo ampliar
   el arreglo (o, más adelante, usar una `List`, cuyo tamaño cambia sin declararlo de antemano).

## 🟢 Básico 02 — Predecir un acceso por índice

`ejemplaresDisponibles[2]` imprime `3` (tercera posición, índice `2`: `5, 12, 3, 8`).

## 🟢 Básico 03 — El error de un índice fuera de rango

El programa **compila** sin problema. Al **ejecutarse**, termina con:

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4
```

El arreglo tiene 4 elementos (índices `0` a `3`); el índice `4` está fuera de rango.

## 🟢 Básico 04 — Calcular el tamaño

`autores.length` imprime `3`.

## 🟢 Básico 05 — Predecir una iteración

El bucle imprime, en orden: `3`, `7`, `5`.

## 🟡 Intermedio 01 — Declarar y recorrer un arreglo

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        int[] multas = {1500, 3000, 500};
        for (int i = 0; i < multas.length; i++) {
            System.out.println(multas[i]);
        }
    }
}
```

Salida real del programa:

```text
1500
3000
500
```

## 🟡 Intermedio 02 — Declarar y recorrer una matriz

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Filas = sedes, columnas = categorías (Ficción, No ficción, Técnico)
        int[][] ejemplaresPorSedeYCategoria = {
            {120, 80, 45},
            {90, 60, 30}
        };

        int total = 0;
        for (int sede = 0; sede < ejemplaresPorSedeYCategoria.length; sede++) {
            for (int categoria = 0; categoria < ejemplaresPorSedeYCategoria[sede].length; categoria++) {
                total = total + ejemplaresPorSedeYCategoria[sede][categoria];
            }
        }
        System.out.println(total);
    }
}
```

Salida real del programa:

```text
425
```

## 🟢 Básico 06 — Leer la jerarquía y .length frente a .size()

1. `List`.
2. `Set` y `Map`.
3. Ninguna de las dos compila:

```text
✖ The method length() is undefined for the type List<String> Java(67108964) [Ln 8, Col 36]
```

```text
✖ Cannot invoke size() on the array type int[] Java(67108980) [Ln 4, Col 28]
```

## 🟢 Básico 07 — Predecir operaciones de una List

Tras `pacientes.remove(1)` (elimina `"Luis Peña"`), `"Marta Salinas"` pasa a ocupar el índice `1`:
`pacientes.get(1)` imprime `Marta Salinas`, y `pacientes.size()` imprime `2`.

## 🟡 Intermedio 03 — Migrar un arreglo a ArrayList

```java
package com.medisalud;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<String> citas = new ArrayList<>();
        citas.add("Ana Torres");
        citas.add("Luis Peña");
        citas.add("Marta Salinas");
        citas.add("Carlos Ibáñez");

        for (String cita : citas) {
            System.out.println(cita);
        }
        System.out.println(citas.size());
    }
}
```

Salida real del programa:

```text
Ana Torres
Luis Peña
Marta Salinas
Carlos Ibáñez
4
```

## 🟡 Intermedio 04 — Declarar y operar una LinkedList

```java
package com.medisalud;

import java.util.LinkedList;

public class Demo {
    public static void main(String[] args) {
        LinkedList<String> espera = new LinkedList<>();
        espera.add("Luis Peña");
        espera.add("Marta Salinas");
        espera.addFirst("Ana Torres");

        for (String paciente : espera) {
            System.out.println(paciente);
        }
        System.out.println(espera.size());
    }
}
```

Salida real del programa:

```text
Ana Torres
Luis Peña
Marta Salinas
3
```

## 🔴 Avanzado 01 — Corregir un programa con errores de arreglos/List

**Ronda 1** — cambiar el índice `3` por uno dentro del rango válido (`0` a `2`); **ronda 2** — cambiar
`catalogo.length()` por `catalogo.size()`. Programa final:

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class RevisionDeInventario {
    public static void main(String[] args) {
        int[] ejemplares = {5, 3, 8};
        int indice = 2;
        System.out.println("Ejemplares en sede " + indice + ": " + ejemplares[indice]);

        List<String> catalogo = new ArrayList<>();
        catalogo.add("Rayuela");
        catalogo.add("Cien años de soledad");
        System.out.println("Cantidad de títulos: " + catalogo.size());
    }
}
```

Salida real del programa:

```text
Ejemplares en sede 2: 8
Cantidad de títulos: 2
```

## 🟡 Intermedio 05 — Declarar una clase genérica

```java
package com.biblioteca;

public class Registro<T> {
    private T dato;
    private String fecha;

    public Registro(T dato, String fecha) {
        this.dato = dato;
        this.fecha = fecha;
    }

    public T getDato() {
        return dato;
    }

    public String getFecha() {
        return fecha;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        Registro<String> registroTitulo = new Registro<>("Rayuela", "2026-03-01");
        System.out.println(registroTitulo.getDato());
        System.out.println(registroTitulo.getFecha());

        Registro<Integer> registroEjemplares = new Registro<>(5, "2026-03-02");
        System.out.println(registroEjemplares.getDato());
        System.out.println(registroEjemplares.getFecha());
    }
}
```

Salida real del programa:

```text
Rayuela
2026-03-01
5
2026-03-02
```

## 🏆 Desafío 01 — Diseñar una clase genérica nueva

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Pila<T> {
    private List<T> elementos = new ArrayList<>();

    public void apilar(T elemento) {
        elementos.add(elemento);
    }

    public T desapilar() {
        return elementos.remove(elementos.size() - 1);
    }

    public int size() {
        return elementos.size();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        Pila<String> pilaTitulos = new Pila<>();
        pilaTitulos.apilar("Rayuela");
        pilaTitulos.apilar("El principito");
        System.out.println(pilaTitulos.desapilar());
        System.out.println(pilaTitulos.size());

        Pila<Integer> pilaNumeros = new Pila<>();
        pilaNumeros.apilar(10);
        pilaNumeros.apilar(20);
        pilaNumeros.apilar(30);
        System.out.println(pilaNumeros.desapilar());
        System.out.println(pilaNumeros.size());
    }
}
```

Salida real del programa:

```text
El principito
1
30
2
```
