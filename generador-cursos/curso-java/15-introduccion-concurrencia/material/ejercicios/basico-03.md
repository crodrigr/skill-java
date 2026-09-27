# 🟢 Básico 03 — Identificar violación de unión de hilos

## 🧩 Problema

La Biblioteca Universitaria calcula el total de multas pendientes de un usuario con este código:

## 💻 Código o contexto de partida

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

    public static void main(String[] args) {
        CalculadorDeMultas calculador = new CalculadorDeMultas();
        calculador.start();

        // Se lee el total inmediatamente, sin esperar a que el hilo termine.
        System.out.println("Total de multas (leido de inmediato): " + calculador.getTotalMultas());
    }
}
```

Sin escribir código, responde: ¿por qué el total impreso no es el esperado, si el cálculo suma
correctamente los diez préstamos vencidos?

## 📏 Criterios de evaluación de la solución

- Identifica que `Demo` lee `getTotalMultas()` inmediatamente después de `start()`, sin ninguna garantía
  de que el hilo `calculador` haya avanzado su cálculo.
- Explica que es una condición de carrera: el resultado observado depende de en qué punto del cálculo
  está el otro hilo en el momento exacto de la lectura, no de un error en la lógica de suma en sí.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: explicar `join()`.
- **RA-9**: identificar una condición de carrera por leer un resultado antes de `join()`.
