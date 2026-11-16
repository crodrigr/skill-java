# 🔴 Avanzado 01 — Corregir un diseño con dos temas técnicos ausentes

## 🧩 Problema

La Biblioteca Universitaria gestiona reservas de salas y equipos con este código, que tiene dos
problemas de diseño distintos:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class SalaDeEstudio {
}
```

```java
package com.biblioteca;

public class Equipo {
}
```

```java
package com.biblioteca;

public class SistemaDeReservas {

    private int contadorDeReservas = 0;
    private final SalaDeEstudio salaDeEstudio = new SalaDeEstudio();
    private final Equipo equipo = new Equipo();

    public void reservar() {
        contadorDeReservas++;
    }

    public int getContadorDeReservas() {
        return contadorDeReservas;
    }

    public void confirmarReserva() {
        synchronized (salaDeEstudio) {
            synchronized (equipo) {
                // coordina salaDeEstudio y equipo para confirmar la reserva
            }
        }
    }

    public void cancelarReserva() {
        synchronized (equipo) {
            synchronized (salaDeEstudio) {
                // coordina equipo y salaDeEstudio para cancelar la reserva
            }
        }
    }
}
```

```java
package com.biblioteca;

public class Demo {

    private static final int CANTIDAD_HILOS = 10;
    private static final int RESERVAS_POR_HILO = 100_000;

    public static void main(String[] args) throws InterruptedException {
        SistemaDeReservas sistema = new SistemaDeReservas();

        Thread[] hilos = new Thread[CANTIDAD_HILOS];
        for (int i = 0; i < CANTIDAD_HILOS; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < RESERVAS_POR_HILO; j++) {
                    sistema.reservar();
                }
            });
            hilos[i].start();
        }
        for (Thread hilo : hilos) {
            hilo.join();
        }

        int esperado = CANTIDAD_HILOS * RESERVAS_POR_HILO;
        System.out.println("Esperado: " + esperado);
        System.out.println("Real: " + sistema.getContadorDeReservas());
        System.out.println("total_correcto: " + (sistema.getContadorDeReservas() == esperado));
    }
}
```

Encuentra y corrige las dos violaciones por separado:

1. `reservar()` y `getContadorDeReservas()` no sincronizan el acceso a `contadorDeReservas` — viola
   sincronización.
2. `confirmarReserva()` toma `salaDeEstudio` y luego `equipo`; `cancelarReserva()` toma `equipo` y luego `salaDeEstudio`
   (orden inverso) — riesgo de interbloqueo si dos hilos las invocan al mismo tiempo.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar 100.000 reservas desde cada uno de 10 hilos | Total final | Exactamente 1.000.000, de forma repetible en ejecuciones sucesivas |
| `confirmarReserva()` y `cancelarReserva()` corregidas | Orden de adquisición de `salaDeEstudio` y `equipo` en ambos métodos | Idéntico en los dos métodos |

## 📏 Criterios de evaluación de la solución

- `reservar()` y `getContadorDeReservas()` quedan `synchronized`.
- `confirmarReserva()` y `cancelarReserva()` adquieren `salaDeEstudio` y `equipo` siempre en el mismo orden entre
  sí.
- El total final de reservas es siempre exactamente 1.000.000.
- Las dos correcciones son independientes entre sí: una no depende de la otra.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni `Lock`/`ReentrantLock` como parte del diseño.
- Este ejercicio no ejecuta `confirmarReserva()`/`cancelarReserva()` concurrentemente para demostrar el
  interbloqueo (eso ya se demostró, verificado con un límite de tiempo real, en el Ejemplo 03): aquí se
  identifica y corrige por lectura del código.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-7**: reconocer y evitar el riesgo de interbloqueo.
- **RA-8**: combinar sincronización con el resto del diseño sin introducir un riesgo nuevo.
