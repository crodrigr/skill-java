# 🟡 Intermedio 01 — Aplicar sincronización

## 🧩 Problema

Tienes el mismo contador de la Biblioteca Universitaria del ejercicio Básico 01:

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

Rediséñalo para que el total final sea siempre correcto, sin importar cuántos hilos lo actualicen a la
vez.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar 100.000 préstamos desde cada uno de 10 hilos | Total final | Exactamente 1.000.000, de forma repetible en ejecuciones sucesivas |

## 📏 Criterios de evaluación de la solución

- Declara `registrarPrestamo()` (y `getTotal()`) como `synchronized`.
- El total final es siempre exactamente 1.000.000, sin variar entre ejecuciones.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni clases atómicas como parte del diseño (`AtomicInteger` es el tema del
  próximo ejercicio).

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-3**: implementar sincronización para un caso dado.
