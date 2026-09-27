# 🟢 Básico 04 — Identificar violación de State

## 🧩 Problema

La Biblioteca Universitaria controla el estado de un ítem del catálogo con esta clase:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class ItemDeCatalogo {
    private String estado = "DISPONIBLE";

    public String prestar() {
        if (estado.equals("DISPONIBLE")) {
            estado = "PRESTADO";
            return "Prestamo registrado, pasa a PRESTADO";
        }
        return "No se puede prestar: el item no esta disponible";
    }

    public String devolver() {
        if (estado.equals("PRESTADO")) {
            estado = "DISPONIBLE";
            return "Devolucion registrada, pasa a DISPONIBLE";
        }
        return "No se puede devolver: el item no esta prestado";
    }

    public String enviarAReparacion() {
        if (estado.equals("DISPONIBLE")) {
            estado = "EN_REPARACION";
            return "Enviado a reparacion";
        }
        return "No se puede enviar a reparacion: el item no esta disponible";
    }

    public String getEstado() {
        return estado;
    }
}
```

Sin escribir código, responde: si se agrega un estado nuevo (por ejemplo, "RESERVADO"), ¿cuántos
métodos hay que revisar y modificar?

## 📏 Criterios de evaluación de la solución

- Identifica que hay que revisar los tres métodos (`prestar()`, `devolver()`,
  `enviarAReparacion()`), porque cada uno repite su propio condicional sobre el campo `estado`.
- Explica que eso es una violación de State: el comportamiento válido en cada momento depende de un
  campo disperso en varios condicionales, repetidos en cada método.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-11**: reconocer qué resuelve State.
- **RA-12**: identificar condicionales dispersos sobre un campo de estado en un fragmento dado.
