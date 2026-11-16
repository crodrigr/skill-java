# 🟡 Intermedio 03 — Aplicar unión de hilos

## 🧩 Problema

Tienes el mismo calculador de la Biblioteca Universitaria del ejercicio Básico 03:

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

Rediséñalo para que el total se lea siempre completo y correcto.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Calcular el total de 10 préstamos vencidos ($500 cada uno) y leerlo tras esperar al hilo | Total impreso | `5000.0`, de forma repetible en ejecuciones sucesivas |

## 📏 Criterios de evaluación de la solución

- Invoca `join()` sobre el hilo `calculador` antes de leer `getTotalMultas()`.
- El total impreso es siempre `5000.0`, sin variar entre ejecuciones sucesivas.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-10**: implementar `join()` para un caso dado.
