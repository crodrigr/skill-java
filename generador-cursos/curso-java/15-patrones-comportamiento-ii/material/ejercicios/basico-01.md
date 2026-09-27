# 🟢 Básico 01 — Identificar violación de Chain of Responsibility

## 🧩 Problema

La Biblioteca Universitaria aprueba préstamos especiales de ejemplares con esta clase:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class SolicitudDePrestamoEspecial {
    private String tituloEjemplar;
    private String excepcionalidad;

    public SolicitudDePrestamoEspecial(String tituloEjemplar, String excepcionalidad) {
        this.tituloEjemplar = tituloEjemplar;
        this.excepcionalidad = excepcionalidad;
    }

    public String getTituloEjemplar() {
        return tituloEjemplar;
    }

    public String getExcepcionalidad() {
        return excepcionalidad;
    }
}
```

```java
package com.biblioteca;

public class GestorDePrestamos {
    public String aprobar(SolicitudDePrestamoEspecial solicitud) {
        if (solicitud.getExcepcionalidad().equals("BAJA")) {
            return "Aprobado por la sala: " + solicitud.getTituloEjemplar();
        } else if (solicitud.getExcepcionalidad().equals("MEDIA")) {
            return "Aprobado por la seccion: " + solicitud.getTituloEjemplar();
        } else if (solicitud.getExcepcionalidad().equals("ALTA")) {
            return "Aprobado por la direccion: " + solicitud.getTituloEjemplar();
        }
        throw new IllegalArgumentException("Excepcionalidad desconocida: " + solicitud.getExcepcionalidad());
    }
}
```

Sin escribir código, responde: si se agrega un nivel de excepcionalidad nuevo (por ejemplo, "CRITICA"),
¿qué método hay que modificar y qué parte exacta de ese método?

## 📏 Criterios de evaluación de la solución

- Identifica que hay que modificar `GestorDePrestamos.aprobar()`, agregando una rama más al
  condicional.
- Explica por qué eso es una violación de Chain of Responsibility: un único método concentra la
  decisión de todos los niveles de aprobación posibles.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: reconocer qué resuelve Chain of Responsibility.
- **RA-3**: identificar el condicional único que decide quién resuelve una solicitud.
