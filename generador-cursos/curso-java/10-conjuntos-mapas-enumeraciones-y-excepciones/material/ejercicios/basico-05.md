# 🟢 Básico 05 — Predecir values()/ordinal() de un enum

## 🧩 Problema

Mira este código de Biblioteca Universitaria:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {
    enum EstadoPrestamo {
        ACTIVO, DEVUELTO, VENCIDO
    }

    public static void main(String[] args) {
        // Datos de entrada
        for (EstadoPrestamo estado : EstadoPrestamo.values()) {
            System.out.println(estado + "=" + estado.ordinal());
        }
    }
}
```

**Pregunta**: sin ejecutar todavía, predice qué imprime `Demo`.

## 🧪 Casos de prueba

| Expresión | Resultado esperado |
|---|---|
| `EstadoPrestamo.values()` | ? |
| `EstadoPrestamo.DEVUELTO.ordinal()` | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente el orden de `values()` (`ACTIVO`, `DEVUELTO`, `VENCIDO`).
- Predice correctamente que `DEVUELTO.ordinal()` da `1`.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de lectura y predicción.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: explicar qué es una enumeración.
- **RA-9**: usar `values()` y `ordinal()` de un `enum`.
