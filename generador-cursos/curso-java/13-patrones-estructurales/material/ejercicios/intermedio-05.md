# 🟡 Intermedio 05 — Aplicar Facade

## 🧩 Problema

Tienes las mismas cuatro clases de subsistema de la Biblioteca Universitaria del ejercicio Básico 05:

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

Rediséñalo para que respete Facade: declara una única clase que orqueste los cuatro subsistemas en el
mismo orden, de forma que `Demo` solo conozca esa clase.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Procesar la devolución de `"L001"` para `"Ana"` | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter |
| Cantidad de clases de subsistema que `Demo` crea directamente, después de refactorizar | Código de `Demo.java` | Ninguna: `Demo` solo crea la fachada |

## 📏 Criterios de evaluación de la solución

- Declara una clase (por ejemplo, `DevolucionFacade`) que crea y orquesta las cuatro clases de
  subsistema, en el mismo orden que la versión original.
- `Demo` solo crea y llama a la fachada.
- La salida por consola no cambia respecto de la versión original.
- Ninguna de las cuatro clases de subsistema se modifica.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-16**: implementar Facade para un caso dado.
