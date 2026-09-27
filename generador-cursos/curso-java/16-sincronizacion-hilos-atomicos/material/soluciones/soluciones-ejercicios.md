# 🔑 Soluciones de los ejercicios — Módulo 16

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar violación de sincronización

`registrarPrestamo()` incrementa `total` sin sincronizar: `total++` en realidad son tres pasos (leer,
sumar, escribir), y varios hilos pueden intercalarse entre esos pasos, perdiendo incrementos. Con diez
hilos y 100.000 préstamos cada uno, el total final observado puede ser (y en la práctica casi siempre
es) menor a 1.000.000.

## 🟡 Intermedio 01 — Aplicar sincronización

```java
package com.biblioteca;

public class RegistroDePrestamos {

    private int total = 0;

    public synchronized void registrarPrestamo() {
        total++;
    }

    public synchronized int getTotal() {
        return total;
    }
}
```

```java
package com.biblioteca;

public class Demo {

    private static final int CANTIDAD_HILOS = 10;
    private static final int PRESTAMOS_POR_HILO = 100_000;

    public static void main(String[] args) throws InterruptedException {
        RegistroDePrestamos registro = new RegistroDePrestamos();

        Thread[] hilos = new Thread[CANTIDAD_HILOS];
        for (int i = 0; i < CANTIDAD_HILOS; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < PRESTAMOS_POR_HILO; j++) {
                    registro.registrarPrestamo();
                }
            });
            hilos[i].start();
        }
        for (Thread hilo : hilos) {
            hilo.join();
        }

        int esperado = CANTIDAD_HILOS * PRESTAMOS_POR_HILO;
        System.out.println("Esperado: " + esperado);
        System.out.println("Real: " + registro.getTotal());
        System.out.println("total_correcto: " + (registro.getTotal() == esperado));
    }
}
```

```text
Esperado: 1000000
Real: 1000000
total_correcto: true
```

## 🟢 Básico 02 — Identificar violación de acceso atómico

Mismo problema del Básico 01 (condición de carrera sobre `total` sin sincronizar). Al tratarse de una
única variable con una única operación (incrementar), una clase atómica (`AtomicInteger`) alcanza para
resolverlo, sin necesitar declarar ningún método `synchronized`.

## 🟡 Intermedio 02 — Aplicar acceso atómico

```java
package com.biblioteca;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorDeDevoluciones {

    private final AtomicInteger total = new AtomicInteger(0);

    public void registrarDevolucion() {
        total.incrementAndGet();
    }

    public int getTotal() {
        return total.get();
    }
}
```

```java
package com.biblioteca;

public class Demo {

    private static final int CANTIDAD_HILOS = 10;
    private static final int DEVOLUCIONES_POR_HILO = 100_000;

    public static void main(String[] args) throws InterruptedException {
        ContadorDeDevoluciones contador = new ContadorDeDevoluciones();

        Thread[] hilos = new Thread[CANTIDAD_HILOS];
        for (int i = 0; i < CANTIDAD_HILOS; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < DEVOLUCIONES_POR_HILO; j++) {
                    contador.registrarDevolucion();
                }
            });
            hilos[i].start();
        }
        for (Thread hilo : hilos) {
            hilo.join();
        }

        int esperado = CANTIDAD_HILOS * DEVOLUCIONES_POR_HILO;
        System.out.println("Esperado: " + esperado);
        System.out.println("Real: " + contador.getTotal());
        System.out.println("total_correcto: " + (contador.getTotal() == esperado));
    }
}
```

```text
Esperado: 1000000
Real: 1000000
total_correcto: true
```

## 🔴 Avanzado 01 — Corregir un diseño con dos temas técnicos ausentes

**Corrección 1 (sincronización) y Corrección 2 (orden consistente de adquisición)**, aplicadas juntas:

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

    public synchronized void reservar() {
        contadorDeReservas++;
    }

    public synchronized int getContadorDeReservas() {
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
        synchronized (salaDeEstudio) {
            synchronized (equipo) {
                // coordina salaDeEstudio y equipo para cancelar la reserva (mismo orden que confirmarReserva)
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

```text
Esperado: 1000000
Real: 1000000
total_correcto: true
```

Comparado con la versión original: `reservar()` y `getContadorDeReservas()` ahora son `synchronized`,
así que el total final es siempre exactamente 1.000.000, en vez de perder incrementos. Además,
`cancelarReserva()` ahora adquiere `salaDeEstudio` y luego `equipo` — el mismo orden que `confirmarReserva()` — en
vez del orden inverso original, eliminando el riesgo de interbloqueo entre ambos métodos.

## 🏆 Desafío 01 — Diseñar un caso nuevo combinando el Módulo 15 y este módulo

Una solución posible: un hilo por renovación (`RenovacionDeAccesoDigital extends Thread`), cada uno revisando
su estado de interrupción en cada paso, y un `ContadorDeRenovaciones` con `AtomicInteger` para contar
cuántas se completan (alcanza con acceso atómico: es una única variable con una única operación). El
proceso interrumpe todos los hilos al superar el tiempo máximo, invoca `join()` sobre cada uno, y solo
entonces lee el conteo final.

```java
package com.biblioteca;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorDeRenovaciones {

    private final AtomicInteger completadas = new AtomicInteger(0);

    public void registrarCompletada() {
        completadas.incrementAndGet();
    }

    public int getCompletadas() {
        return completadas.get();
    }
}
```

```java
package com.biblioteca;

public class RenovacionDeAccesoDigital extends Thread {

    private static final int CANTIDAD_PASOS = 5;
    private static final long DURACION_POR_PASO_MS = 60;

    private final ContadorDeRenovaciones contador;

    public RenovacionDeAccesoDigital(ContadorDeRenovaciones contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 1; i <= CANTIDAD_PASOS; i++) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            try {
                Thread.sleep(DURACION_POR_PASO_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        contador.registrarCompletada();
    }
}
```

```java
package com.biblioteca;

public class Demo {

    private static final long TIEMPO_MAXIMO_CORTO_MS = 150;

    public static void main(String[] args) throws InterruptedException {
        int cantidadUsuarios = 5;
        ContadorDeRenovaciones contador = new ContadorDeRenovaciones();

        RenovacionDeAccesoDigital[] hilos = new RenovacionDeAccesoDigital[cantidadUsuarios];
        for (int i = 0; i < cantidadUsuarios; i++) {
            hilos[i] = new RenovacionDeAccesoDigital(contador);
            hilos[i].start();
        }

        Thread.sleep(TIEMPO_MAXIMO_CORTO_MS);
        for (RenovacionDeAccesoDigital hilo : hilos) {
            hilo.interrupt();
        }
        for (RenovacionDeAccesoDigital hilo : hilos) {
            hilo.join();
        }

        System.out.println("Renovaciones completadas (tiempo maximo corto): " + contador.getCompletadas() + " de " + cantidadUsuarios);
    }
}
```

```text
Renovaciones completadas (tiempo maximo corto): 0 de 5
```

Con un tiempo máximo generoso, en cambio, las cinco renovaciones se completan (mismo código, sin
modificar nada más que la constante del tiempo máximo):

```java
package com.biblioteca;

public class DemoTiempoGeneroso {

    private static final long TIEMPO_MAXIMO_GENEROSO_MS = 1000;

    public static void main(String[] args) throws InterruptedException {
        int cantidadUsuarios = 5;
        ContadorDeRenovaciones contador = new ContadorDeRenovaciones();

        RenovacionDeAccesoDigital[] hilos = new RenovacionDeAccesoDigital[cantidadUsuarios];
        for (int i = 0; i < cantidadUsuarios; i++) {
            hilos[i] = new RenovacionDeAccesoDigital(contador);
            hilos[i].start();
        }

        Thread.sleep(TIEMPO_MAXIMO_GENEROSO_MS);
        for (RenovacionDeAccesoDigital hilo : hilos) {
            hilo.interrupt();
        }
        for (RenovacionDeAccesoDigital hilo : hilos) {
            hilo.join();
        }

        System.out.println("Renovaciones completadas (tiempo maximo generoso): " + contador.getCompletadas() + " de " + cantidadUsuarios);
    }
}
```

```text
Renovaciones completadas (tiempo maximo generoso): 5 de 5
```
