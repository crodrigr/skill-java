# 🟢 Básico 02 — Identificar violación de acceso atómico

## 🧩 Problema

La Biblioteca Universitaria registra devoluciones con este contador compartido:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class ContadorDeDevoluciones {

    private int total = 0;

    public void registrarDevolucion() {
        total++;
    }

    public int getTotal() {
        return total;
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

Sin escribir código, responde: ¿qué herramienta de este módulo alcanza para resolver este problema con
el menor cambio posible, sin declarar ningún método `synchronized`?

## 📏 Criterios de evaluación de la solución

- Identifica el mismo problema del Básico 01 (condición de carrera sobre `total`, sin sincronizar).
- Reconoce que, al tratarse de una única variable con una única operación (incrementar), una clase
  atómica (`AtomicInteger`) alcanza, sin necesitar `synchronized`.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-4**: explicar el acceso atómico.
- **RA-5**: reconocer cuándo una clase atómica alcanza en vez de `synchronized`.
