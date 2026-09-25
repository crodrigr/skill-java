# 🟡 Intermedio 01 — Refactorizar SRP

## 🧩 Problema

Tienes esta clase de la Biblioteca Universitaria, la misma del ejercicio Básico 01, que mezcla tres
responsabilidades en `registrarPrestamo`:

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

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        GestorPrestamos gestor = new GestorPrestamos();
        gestor.registrarPrestamo("Elena Ruiz", "Cien anios de soledad");
        gestor.registrarPrestamo("Pablo Sosa", "Rayuela");
    }
}
```

Refactorízala para que respete SRP: extrae cada responsabilidad mezclada a su propia clase, y haz que
`GestorPrestamos` las reciba por constructor. La salida del programa, ejecutado con los mismos datos de
entrada, debe ser **exactamente la misma**.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| `registrarPrestamo("Elena Ruiz", "Cien anios de soledad")` seguido de `registrarPrestamo("Pablo Sosa", "Rayuela")` | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter |

## 📏 Criterios de evaluación de la solución

- `GestorPrestamos` ya no imprime el comprobante ni actualiza el contador directamente.
- Existen dos clases nuevas, una por responsabilidad extraída, cada una con un único método propio.
- `GestorPrestamos` recibe las dos clases nuevas por constructor.
- La salida por consola no cambia respecto de la versión original.

## 🚧 Restricciones

- No se agregan responsabilidades nuevas: solo se reorganiza el código existente.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: refactorizar una clase que viola SRP sin cambiar su comportamiento observable.
