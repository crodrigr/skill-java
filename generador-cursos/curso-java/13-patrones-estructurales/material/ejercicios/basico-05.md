# 🟢 Básico 05 — Identificar violación de Facade

## 🧩 Problema

La Biblioteca Universitaria procesa una devolución con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class VerificadorMulta {
    public String verificar(String socio) {
        return "Sin multas pendientes para " + socio;
    }
}
```

```java
package com.biblioteca;

public class ActualizadorInventario {
    public String actualizar(String codigo) {
        return "Inventario actualizado: " + codigo + " disponible";
    }
}
```

```java
package com.biblioteca;

public class NotificadorReserva {
    public String notificar(String codigo) {
        return "Reserva notificada para el proximo socio en espera de " + codigo;
    }
}
```

```java
package com.biblioteca;

public class RegistradorHistorial {
    public String registrar(String socio, String codigo) {
        return "Historial actualizado: " + socio + " devolvio " + codigo;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        VerificadorMulta verificadorMulta = new VerificadorMulta();
        ActualizadorInventario actualizadorInventario = new ActualizadorInventario();
        NotificadorReserva notificadorReserva = new NotificadorReserva();
        RegistradorHistorial registradorHistorial = new RegistradorHistorial();

        System.out.println(verificadorMulta.verificar("Ana"));
        System.out.println(actualizadorInventario.actualizar("L001"));
        System.out.println(notificadorReserva.notificar("L001"));
        System.out.println(registradorHistorial.registrar("Ana", "L001"));
    }
}
```

Sin escribir código, responde: ¿cuántas clases de subsistema conoce `Demo` directamente, y qué pasaría si
mañana se agrega un quinto paso al proceso de devolución (por ejemplo, verificar reservas cruzadas con
otra sede)?

## 📏 Criterios de evaluación de la solución

- Identifica que `Demo` conoce y llama directamente a las cuatro clases de subsistema.
- Explica que agregar un quinto paso exigiría modificar `Demo` (y cualquier otro lugar del código que
  también proceda una devolución de la misma forma), en vez de modificar un único punto central.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-14**: reconocer qué resuelve Facade.
- **RA-15**: reconocer un cliente que orquesta demasiados subsistemas directamente.
