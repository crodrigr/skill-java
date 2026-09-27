# 🟡 Intermedio 02 — Aplicar acceso atómico

## 🧩 Problema

Tienes el mismo contador de la Biblioteca Universitaria del ejercicio Básico 02:

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

Rediséñalo usando una clase atómica, en vez de `synchronized`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar 100.000 devoluciones desde cada uno de 10 hilos | Total final | Exactamente 1.000.000, de forma repetible en ejecuciones sucesivas |

## 📏 Criterios de evaluación de la solución

- Reemplaza el campo `int total` por un `AtomicInteger`, usando `incrementAndGet()` para registrar cada
  devolución.
- No declara ningún método `synchronized`.
- El total final es siempre exactamente 1.000.000, sin variar entre ejecuciones.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni `synchronized` como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-6**: implementar acceso atómico para un caso dado.
