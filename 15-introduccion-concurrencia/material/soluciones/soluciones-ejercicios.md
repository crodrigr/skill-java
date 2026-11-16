# 🔑 Soluciones de los ejercicios — Módulo 15

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar violación de creación de hilos

Las tres notificaciones se envían una detrás de otra, desde el mismo hilo: el tiempo total es la suma de
los tres tiempos individuales. Con cien usuarios, el tiempo total crecería en la misma proporción (cien
veces el tiempo de una sola notificación), porque nada se ejecuta en paralelo — cada notificación espera
a que la anterior termine, aunque no dependen entre sí.

## 🟡 Intermedio 01 — Aplicar creación de hilos

```java
package com.biblioteca;

public class GestorDeNotificaciones {

    public static void notificarVencimiento(String usuario) {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.println("Notificacion de vencimiento enviada a " + usuario);
    }
}
```

```java
package com.biblioteca;

public class Demo {

    private static final long TOTAL_SECUENCIAL_ESPERADO_MS = 150 * 3;

    public static void main(String[] args) throws InterruptedException {
        String[] usuarios = {"Carla Nunez", "Diego Perez", "Elena Ruiz"};

        long inicio = System.currentTimeMillis();
        Thread[] hilos = new Thread[usuarios.length];
        for (int i = 0; i < usuarios.length; i++) {
            String usuario = usuarios[i];
            hilos[i] = new Thread(() -> GestorDeNotificaciones.notificarVencimiento(usuario));
            hilos[i].start();
        }
        // Margen generoso de espera (sin join(), tema de un ejemplo posterior).
        Thread.sleep(300);

        long fin = System.currentTimeMillis();
        long totalConHilos = fin - inicio;
        boolean concurrenteMasRapido = totalConHilos < (TOTAL_SECUENCIAL_ESPERADO_MS * 0.85);

        System.out.println("Tiempo total con hilos: " + totalConHilos + " ms");
        System.out.println("concurrente_mas_rapido: " + concurrenteMasRapido);
    }
}
```

```text
Notificacion de vencimiento enviada a Elena Ruiz
Notificacion de vencimiento enviada a Diego Perez
Notificacion de vencimiento enviada a Carla Nunez
Tiempo total con hilos: 308 ms
concurrente_mas_rapido: true
```

## 🟢 Básico 02 — Identificar violación de interrupción de hilos

`EscaneoDeInventario` no revisa en ningún punto su estado de interrupción, así que `interrupt()` no
tiene ningún efecto visible: el hilo completa el escaneo de los diez libros igual, ignorando el pedido.

## 🟡 Intermedio 02 — Aplicar interrupción de hilos

```java
package com.biblioteca;

public class EscaneoDeInventario extends Thread {

    private static final int CANTIDAD_LIBROS = 10;
    private static final long DURACION_POR_LIBRO_MS = 100;

    private int cantidadEscaneada = 0;

    @Override
    public void run() {
        for (int i = 1; i <= CANTIDAD_LIBROS; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupcion detectada: se detiene antes de completar el inventario");
                return;
            }
            try {
                Thread.sleep(DURACION_POR_LIBRO_MS);
            } catch (InterruptedException e) {
                System.out.println("Interrupcion detectada durante la espera: se detiene antes de completar el inventario");
                Thread.currentThread().interrupt();
                return;
            }
            cantidadEscaneada++;
            System.out.println("Libro " + i + " de " + CANTIDAD_LIBROS + " escaneado");
        }
    }

    public int getCantidadEscaneada() {
        return cantidadEscaneada;
    }
}
```

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        EscaneoDeInventario escaneo = new EscaneoDeInventario();
        escaneo.start();

        Thread.sleep(250);
        escaneo.interrupt();

        // Margen generoso de espera (sin join(), tema de un ejemplo posterior).
        Thread.sleep(1200);

        System.out.println("Cantidad escaneada tras interrupcion: " + escaneo.getCantidadEscaneada());
    }
}
```

```text
Libro 1 de 10 escaneado
Libro 2 de 10 escaneado
Interrupcion detectada durante la espera: se detiene antes de completar el inventario
Cantidad escaneada tras interrupcion: 2
```

## 🟢 Básico 03 — Identificar violación de unión de hilos

`Demo` lee `getTotalMultas()` inmediatamente después de `start()`, sin ninguna garantía de que
`calculador` haya avanzado su cálculo: es una condición de carrera, no un error en la lógica de suma —
el hilo principal simplemente no espera a que el otro termine antes de leer su resultado.

## 🟡 Intermedio 03 — Aplicar unión de hilos

```java
package com.biblioteca;

public class CalculadorDeMultas extends Thread {

    private static final int CANTIDAD_PRESTAMOS_VENCIDOS = 10;
    private static final long DURACION_POR_PRESTAMO_MS = 30;

    private double totalMultas = 0.0;

    @Override
    public void run() {
        for (int i = 1; i <= CANTIDAD_PRESTAMOS_VENCIDOS; i++) {
            try {
                Thread.sleep(DURACION_POR_PRESTAMO_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            totalMultas += 500.0;
        }
    }

    public double getTotalMultas() {
        return totalMultas;
    }
}
```

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        CalculadorDeMultas calculador = new CalculadorDeMultas();
        calculador.start();

        calculador.join();

        // Se lee el total solo despues de que el hilo termino, garantizado por join().
        System.out.println("Total de multas (tras join): " + calculador.getTotalMultas());
    }
}
```

```text
Total de multas (tras join): 5000.0
```

## 🔴 Avanzado 01 — Corregir un diseño con dos temas técnicos ausentes

**Corrección 1 (creación estructurada) y Corrección 2 (interrupción cooperativa)**, aplicadas juntas:

```java
package com.biblioteca;

public class GeneradorReportePorSede extends Thread {

    private static final int CANTIDAD_PRESTAMOS = 10;
    private static final long DURACION_POR_PRESTAMO_MS = 100;

    private final String sede;
    private int cantidadProcesada = 0;

    public GeneradorReportePorSede(String sede) {
        this.sede = sede;
    }

    @Override
    public void run() {
        for (int i = 1; i <= CANTIDAD_PRESTAMOS; i++) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            try {
                Thread.sleep(DURACION_POR_PRESTAMO_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            cantidadProcesada++;
        }
        System.out.println("Reporte de " + sede + " generado (" + CANTIDAD_PRESTAMOS + " prestamos)");
    }

    public int getCantidadProcesada() {
        return cantidadProcesada;
    }
}
```

```java
package com.biblioteca;

public class Demo {

    private static final long TIEMPO_MAXIMO_MS = 350;

    public static void main(String[] args) throws InterruptedException {
        String[] sedes = {"Sede Centro", "Sede Norte", "Sede Sur"};

        GeneradorReportePorSede[] hilos = new GeneradorReportePorSede[sedes.length];
        for (int i = 0; i < sedes.length; i++) {
            hilos[i] = new GeneradorReportePorSede(sedes[i]);
            hilos[i].start();
        }

        Thread.sleep(TIEMPO_MAXIMO_MS);
        for (GeneradorReportePorSede hilo : hilos) {
            hilo.interrupt();
        }
        for (GeneradorReportePorSede hilo : hilos) {
            hilo.join();
        }

        int totalProcesado = 0;
        for (GeneradorReportePorSede hilo : hilos) {
            totalProcesado += hilo.getCantidadProcesada();
        }
        int totalSiSeCompletara = sedes.length * 10;
        System.out.println("Prestamos procesados antes de cancelar: " + totalProcesado + " de " + totalSiSeCompletara);
        System.out.println("proceso_cancelado_a_tiempo: " + (totalProcesado < totalSiSeCompletara));
    }
}
```

```text
Prestamos procesados antes de cancelar: 9 de 30
proceso_cancelado_a_tiempo: true
```

Comparado con la versión original: en vez de crear los tres hilos sin guardar ninguna referencia,
`Demo` los guarda en un arreglo (`hilos`), lo que permite interrumpirlos y unirlos (`join()`)
individualmente. `GeneradorReportePorSede` ahora revisa `isInterrupted()` en cada iteración, por lo que
al superarse el tiempo máximo (350 ms) los tres hilos se detienen antes de completar sus diez préstamos
cada uno — 9 procesados de un total de 30, en vez de completar los 30 igual como en la versión original.

## 🏆 Desafío 01 — Diseñar un caso nuevo combinando los tres temas técnicos

Una solución posible: un hilo por renovación (`RenovacionDeAcceso extends Thread`), cada uno revisando su
estado de interrupción en cada paso; el proceso guarda las referencias en un arreglo, interrumpe todos
los hilos al superar el tiempo máximo, invoca `join()` sobre cada uno, y solo entonces cuenta cuántas
renovaciones se completaron.

```java
package com.biblioteca;

public class RenovacionDeAcceso extends Thread {

    private static final int CANTIDAD_PASOS = 5;
    private static final long DURACION_POR_PASO_MS = 60;

    private final String usuario;
    private boolean completada = false;

    public RenovacionDeAcceso(String usuario) {
        this.usuario = usuario;
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
        completada = true;
        System.out.println("Acceso renovado: " + usuario);
    }

    public boolean isCompletada() {
        return completada;
    }
}
```

```java
package com.biblioteca;

public class Demo {

    private static final long TIEMPO_MAXIMO_CORTO_MS = 150;

    public static void main(String[] args) throws InterruptedException {
        String[] usuarios = {"Carla Nunez", "Diego Perez", "Elena Ruiz", "Fabian Soto", "Gaby Leiva"};

        RenovacionDeAcceso[] hilos = new RenovacionDeAcceso[usuarios.length];
        for (int i = 0; i < usuarios.length; i++) {
            hilos[i] = new RenovacionDeAcceso(usuarios[i]);
            hilos[i].start();
        }

        Thread.sleep(TIEMPO_MAXIMO_CORTO_MS);
        for (RenovacionDeAcceso hilo : hilos) {
            hilo.interrupt();
        }
        for (RenovacionDeAcceso hilo : hilos) {
            hilo.join();
        }

        int completadas = 0;
        for (RenovacionDeAcceso hilo : hilos) {
            if (hilo.isCompletada()) {
                completadas++;
            }
        }
        System.out.println("Renovaciones completadas (tiempo maximo corto): " + completadas + " de " + usuarios.length);
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

public class DemoCompleto {

    private static final long TIEMPO_MAXIMO_GENEROSO_MS = 1000;

    public static void main(String[] args) throws InterruptedException {
        String[] usuarios = {"Carla Nunez", "Diego Perez", "Elena Ruiz", "Fabian Soto", "Gaby Leiva"};

        RenovacionDeAcceso[] hilos = new RenovacionDeAcceso[usuarios.length];
        for (int i = 0; i < usuarios.length; i++) {
            hilos[i] = new RenovacionDeAcceso(usuarios[i]);
            hilos[i].start();
        }

        Thread.sleep(TIEMPO_MAXIMO_GENEROSO_MS);
        for (RenovacionDeAcceso hilo : hilos) {
            hilo.interrupt();
        }
        for (RenovacionDeAcceso hilo : hilos) {
            hilo.join();
        }

        int completadas = 0;
        for (RenovacionDeAcceso hilo : hilos) {
            if (hilo.isCompletada()) {
                completadas++;
            }
        }
        System.out.println("Renovaciones completadas (tiempo maximo generoso): " + completadas + " de " + usuarios.length);
    }
}
```

```text
Acceso renovado: Diego Perez
Acceso renovado: Gaby Leiva
Acceso renovado: Fabian Soto
Acceso renovado: Elena Ruiz
Acceso renovado: Carla Nunez
Renovaciones completadas (tiempo maximo generoso): 5 de 5
```
