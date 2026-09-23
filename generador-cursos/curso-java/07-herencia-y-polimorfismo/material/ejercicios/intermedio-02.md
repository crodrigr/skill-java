# 🟡 Intermedio 02 — Sobrecargar un método o constructor

## 🧩 Problema

**Biblioteca Universitaria** quiere registrar un préstamo con o sin una fecha de devolución explícita:

- Si no se indica fecha, el mensaje debe avisar que no hay fecha fijada.
- Si se indica, el mensaje debe incluirla.

## 💻 Código o contexto de partida

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

**Tarea**: ya está resuelto arriba (dos versiones de `registrarPrestamo`); confírmalo ejecutando los
casos de prueba y explica en una frase por qué es sobrecarga y no sobre-escritura.

## 🧪 Casos de prueba

| Llamada | Resultado esperado |
|---|---|
| `registrarPrestamo("Ana Torres")` | `"Cien años de soledad prestado a Ana Torres (sin fecha de devolución fijada)"` |
| `registrarPrestamo("Carlos Ramírez", "2026-10-01")` | `"Cien años de soledad prestado a Carlos Ramírez (devolver antes del 2026-10-01)"` |

## 📏 Criterios de evaluación de la solución

- Los dos métodos tienen el mismo nombre y distinta lista de parámetros, en la misma clase.
- El programa produce exactamente la salida de los casos de prueba.
- Explica por qué esto es sobrecarga (misma clase, firma distinta) y no sobre-escritura.

## 🚧 Restricciones

- Los dos métodos deben llamarse igual (`registrarPrestamo`).

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-8**: declarar métodos y constructores sobrecargados.
