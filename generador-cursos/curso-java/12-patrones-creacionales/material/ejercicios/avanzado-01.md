# 🔴 Avanzado 01 — Corregir un diseño con dos patrones ausentes

## 🧩 Problema

La Biblioteca Universitaria gestiona reservas de salas desde dos módulos distintos. Este diseño tiene
**dos** patrones ausentes: uno de Factory Method y uno de Singleton.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public interface Reserva {
    String describir();
}
```

```java
package com.biblioteca;

public class ReservaNormal implements Reserva {
    public String describir() {
        return "Reserva NORMAL";
    }
}
```

```java
package com.biblioteca;

public class ReservaUrgente implements Reserva {
    public String describir() {
        return "Reserva URGENTE";
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class GestorDeReservas {
    private List<String> reservas = new ArrayList<>();

    public void crearReserva(String tipo) {
        Reserva reserva;
        if (tipo.equals("NORMAL")) {
            reserva = new ReservaNormal();
        } else if (tipo.equals("URGENTE")) {
            reserva = new ReservaUrgente();
        } else {
            throw new IllegalArgumentException("Tipo de reserva desconocido: " + tipo);
        }
        reservas.add(reserva.describir());
    }

    public int totalReservas() {
        return reservas.size();
    }
}
```

```java
package com.biblioteca;

public class ModuloSala {
    private GestorDeReservas gestor = new GestorDeReservas();

    public void reservar(String tipo) {
        gestor.crearReserva(tipo);
    }

    public GestorDeReservas getGestor() {
        return gestor;
    }
}
```

```java
package com.biblioteca;

public class ModuloApp {
    private GestorDeReservas gestor = new GestorDeReservas();

    public void reservar(String tipo) {
        gestor.crearReserva(tipo);
    }

    public GestorDeReservas getGestor() {
        return gestor;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        ModuloSala sala = new ModuloSala();
        ModuloApp app = new ModuloApp();

        sala.reservar("NORMAL");
        app.reservar("URGENTE");
        app.reservar("NORMAL");

        boolean mismaInstancia = sala.getGestor() == app.getGestor();
        System.out.println("mismaInstancia=" + mismaInstancia);
        System.out.println("total en sala=" + sala.getGestor().totalReservas());
        System.out.println("total en app=" + app.getGestor().totalReservas());
    }
}
```

## 🧪 Casos de prueba

| Patrón ausente | Dónde está | Cómo se corrige |
|---|---|---|
| Factory Method | `GestorDeReservas.crearReserva` decide con `if`/`else` e invoca `new` sobre el tipo concreto | Delegar la creación en una `FabricaDeReservas` |
| Singleton | `GestorDeReservas` tiene un constructor público; `ModuloSala` y `ModuloApp` crean cada uno el suyo | Constructor privado + instancia estática única |

## 📏 Criterios de evaluación de la solución

- Corrige la ausencia de Factory Method: declara `FabricaDeReservas` con un método `crear(String tipo)`.
- Corrige la ausencia de Singleton: `GestorDeReservas` con constructor privado y `getInstancia()`.
- Cada corrección se verifica por separado: la de Factory Method comprobando que `GestorDeReservas` ya
  no invoca `new` sobre los tipos concretos de reserva; la de Singleton comprobando que
  `ModuloSala` y `ModuloApp` comparten el mismo total de reservas.

## 🚧 Restricciones

- Las dos correcciones son independientes entre sí.
- No se usa `synchronized` ni verificación de hilos.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar Factory Method para un caso dado.
- **RA-16**: implementar Singleton correctamente para un caso dado.
- **RA-17**: dado un fragmento de código, identificar qué patrón (o patrones) hace falta aplicar.
