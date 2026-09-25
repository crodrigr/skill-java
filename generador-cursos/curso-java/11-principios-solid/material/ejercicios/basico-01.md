# 🟢 Básico 01 — Identificar violación de SRP

## 🧩 Problema

La Biblioteca Universitaria tiene esta clase para registrar préstamos:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class GestorPrestamos {
    private int totalCatalogados = 120;

    public void registrarPrestamo(String usuario, String libro) {
        // Responsabilidad 1: registrar el prestamo
        System.out.println("Prestamo registrado: " + usuario + " -> " + libro);

        // Responsabilidad 2: imprimir el comprobante (mezclada con registrar)
        System.out.println("--- Comprobante ---");
        System.out.println("Usuario: " + usuario);
        System.out.println("Libro: " + libro);
        System.out.println("-------------------");

        // Responsabilidad 3: actualizar el contador del catalogo (mezclada con registrar)
        totalCatalogados = totalCatalogados - 1;
        System.out.println("Ejemplares disponibles: " + totalCatalogados);
    }
}
```

Sin escribir código, respondé: ¿cuántas responsabilidades distintas mezcla el método
`registrarPrestamo`? Nombrá cada una y explica qué motivo de cambio distinto tiene cada una (por
ejemplo: "cambiar el formato del comprobante" no debería afectar la forma en que se registra el
préstamo).

## 📏 Criterios de evaluación de la solución

- Identifica las tres responsabilidades mezcladas: registrar el préstamo, imprimir el comprobante y
  actualizar el contador de ejemplares disponibles.
- Explica, para al menos dos de ellas, un cambio futuro que afectaría a una sin necesidad de afectar a
  las otras.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: reconocer qué significa que una clase tenga una sola razón para cambiar.
- **RA-3**: identificar responsabilidades mezcladas en una clase dada.
