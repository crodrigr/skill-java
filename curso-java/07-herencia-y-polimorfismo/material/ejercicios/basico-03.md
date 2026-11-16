# 🟢 Básico 03 — Sobrecarga o sobre-escritura

## 🧩 Problema

**MediSalud** tiene estos fragmentos de código reales.

## 💻 Código o contexto de partida

```java
package com.medisalud;

public class Recordatorio {
    private String mensaje;

    public Recordatorio(String mensaje) {
        this.mensaje = mensaje;
    }

    public String enviar() {
        return "Recordatorio: " + mensaje;
    }

    public String enviar(String canal) {
        return "Recordatorio por " + canal + ": " + mensaje;
    }
}
```

```java
package com.medisalud;

public class Notificacion {
    protected String mensaje;

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String enviar() {
        return "Notificación: " + mensaje;
    }
}
```

```java
package com.medisalud;

public class NotificacionUrgente extends Notificacion {
    public NotificacionUrgente(String mensaje) {
        super(mensaje);
    }

    @Override
    public String enviar() {
        return "URGENTE — " + super.enviar();
    }
}
```

**Pregunta**: clasifica cada situación como **sobrecarga** o **sobre-escritura**:

1. `Recordatorio` declara `enviar()` y `enviar(String canal)`.
2. `NotificacionUrgente` declara `enviar()` con `@Override`.

## 🧪 Casos de prueba

| Situación | Sobrecarga / Sobre-escritura |
|---|---|
| `Recordatorio.enviar()` y `Recordatorio.enviar(String)` | ? |
| `NotificacionUrgente.enviar()` frente a `Notificacion.enviar()` | ? |

## 📏 Criterios de evaluación de la solución

- Clasifica correctamente ambas situaciones.
- Justifica con el criterio correcto: misma clase + firma distinta (sobrecarga) frente a relación de
  herencia + misma firma (sobre-escritura).

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de clasificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-9**: sobreescribir un método con `@Override`.
- **RA-10**: distinguir sobrecarga de sobre-escritura.
