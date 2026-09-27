# 🟢 Básico 01 — Identificar violación de sincronización

## 🧩 Problema

La Biblioteca Universitaria registra préstamos con este contador compartido:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class RegistroDePrestamos {

    private int total = 0;

    public void registrarPrestamo() {
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

Sin escribir código, responde: si diez hilos registran 100.000 préstamos cada uno, ¿por qué el total
final observado puede ser menor a 1.000.000?

## 📏 Criterios de evaluación de la solución

- Identifica que `registrarPrestamo()` incrementa `total` sin ninguna sincronización.
- Explica que `total++` en realidad son tres pasos (leer, sumar, escribir), y que varios hilos pueden
  intercalarse entre esos pasos, perdiendo incrementos — una condición de carrera real, no un error de
  sintaxis.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar la condición de carrera y qué hace `synchronized`.
- **RA-2**: identificar un dato mutable compartido sin sincronizar.
