# 🟢 Básico 02 — Identificar violación de OCP

## 🧩 Problema

La Biblioteca Universitaria calcula la multa por atraso según el tipo de usuario:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class CalculadoraMulta {
    public double calcularMulta(String tipoUsuario, int diasAtraso) {
        if (tipoUsuario.equals("ESTUDIANTE")) {
            return diasAtraso * 50.0;
        } else if (tipoUsuario.equals("DOCENTE")) {
            return diasAtraso * 20.0;
        } else if (tipoUsuario.equals("INVITADO")) {
            return diasAtraso * 100.0;
        }
        throw new IllegalArgumentException("Tipo de usuario desconocido: " + tipoUsuario);
    }
}
```

Sin escribir código, respondé: si la biblioteca agrega un nuevo tipo de usuario, `"EXTERNO"`, con su
propia tarifa de multa, ¿qué archivo hay que modificar y qué parte exacta de ese archivo?

## 📏 Criterios de evaluación de la solución

- Identifica que hay que modificar el método `calcularMulta`, agregando una rama `else if` más.
- Explica por qué eso es una violación de OCP: el código existente, que ya funcionaba para los tipos
  anteriores, se modifica para agregar el caso nuevo.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: reconocer qué significa "abierto a extensión, cerrado a modificación".
- **RA-6**: identificar una estructura que viola OCP.
