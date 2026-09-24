# 🟡 Intermedio 03 — Declarar una composición

## 🧩 Problema

Tienes esta clase de Biblioteca Universitaria, todavía sin `Comprobante`:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

// TODO: agregar un Comprobante creado dentro de este mismo constructor (composicion).
public class Prestamo {
    private String libro;

    public Prestamo(String libro) {
        this.libro = libro;
    }

    public String getLibro() {
        return libro;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Prestamo prestamo = new Prestamo("Cien anios de soledad");

        System.out.println(prestamo.getLibro());
    }
}
```

Agrega una clase `Comprobante` (con `numero` y `fecha`) y haz que cada `Prestamo` cree su propio
`Comprobante` **dentro de su constructor** (composición: sin recibirlo por parámetro).

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `new Prestamo("Cien anios de soledad", 9001, "2026-09-24")` | `prestamo.getComprobante().getNumero()` | `9001` |
| Mismo caso | ¿Existe algún constructor de `Prestamo` que reciba un `Comprobante` por parámetro? | No |

## 📏 Criterios de evaluación de la solución

- `Comprobante` se crea dentro del constructor de `Prestamo`, nunca se recibe por parámetro.
- No existe ninguna forma de construir un `Prestamo` pasándole un `Comprobante` ya hecho.

## 🚧 Restricciones

- No se usa `try`/`catch`.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-8**: explicar qué es la composición.
- **RA-9**: declarar y usar una composición.
