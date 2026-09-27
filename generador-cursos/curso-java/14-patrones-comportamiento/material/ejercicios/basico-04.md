# 🟢 Básico 04 — Identificar violación de Mediator

## 🧩 Problema

La Biblioteca Universitaria coordina la devolución de un ejemplar con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class AreaPrestamos {
    private AreaReservas areaReservas;
    private AreaMultas areaMultas;

    public AreaPrestamos(AreaReservas areaReservas, AreaMultas areaMultas) {
        this.areaReservas = areaReservas;
        this.areaMultas = areaMultas;
    }

    public void registrarDevolucion(String ejemplar) {
        System.out.println("Prestamos: registrando devolucion de " + ejemplar);
        areaReservas.recibirAvisoDeDevolucion(ejemplar);
        areaMultas.recibirAvisoDeDevolucion(ejemplar);
    }
}
```

```java
package com.biblioteca;

public class AreaReservas {
    public void recibirAvisoDeDevolucion(String ejemplar) {
        System.out.println("Reservas: notificando al proximo interesado en " + ejemplar);
    }
}
```

```java
package com.biblioteca;

public class AreaMultas {
    public void recibirAvisoDeDevolucion(String ejemplar) {
        System.out.println("Multas: verificando atraso de " + ejemplar);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        AreaReservas areaReservas = new AreaReservas();
        AreaMultas areaMultas = new AreaMultas();
        AreaPrestamos areaPrestamos = new AreaPrestamos(areaReservas, areaMultas);

        areaPrestamos.registrarDevolucion("Cien anios de soledad");
    }
}
```

Sin escribir código, responde: ¿a cuántas otras áreas conoce y llama directamente `AreaPrestamos`, y qué
pasaría si mañana se agrega un área nueva que también debe enterarse?

## 📏 Criterios de evaluación de la solución

- Identifica que `AreaPrestamos` conoce y llama directamente a `AreaReservas` y a `AreaMultas`.
- Explica que agregar un área nueva exigiría modificar `AreaPrestamos` (el constructor y el método que
  coordina la devolución), en vez de agregar el área nueva sin tocar las existentes.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-11**: reconocer qué resuelve Mediator.
- **RA-12**: reconocer acoplamiento de todos con todos entre varios objetos.
