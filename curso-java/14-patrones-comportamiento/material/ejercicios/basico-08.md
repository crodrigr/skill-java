# 🟢 Básico 08 — Identificar violación de Strategy

## 🧩 Problema

La Biblioteca Universitaria calcula el costo de reservar una sala de estudio grupal con esta clase:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class SolicitudDeSala {
    private String horario;

    public SolicitudDeSala(String horario) {
        this.horario = horario;
    }

    public double calcularCosto() {
        if (horario.equals("DIURNO")) {
            return 500.0;
        } else if (horario.equals("NOCTURNO")) {
            return 700.0;
        } else if (horario.equals("FIN_DE_SEMANA")) {
            return 900.0;
        }
        throw new IllegalArgumentException("Horario desconocido: " + horario);
    }
}
```

Sin escribir código, responde: si se agrega un horario nuevo (por ejemplo, "FERIADO"), ¿qué método hay
que modificar y qué parte exacta de ese método?

## 📏 Criterios de evaluación de la solución

- Identifica que hay que modificar `SolicitudDeSala.calcularCosto()`, agregando una rama más al
  condicional.
- Explica por qué eso es una violación de Strategy: el código cliente (o la propia clase) está
  acoplado a un condicional que crece con cada horario nuevo.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-23**: reconocer qué resuelve Strategy.
- **RA-24**: identificar el condicional que elige un algoritmo en un fragmento dado.
