# 🟡 Intermedio 02 — Aplicar interrupción de hilos

## 🧩 Problema

Tienes el mismo escaneo de la Biblioteca Universitaria del ejercicio Básico 02:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class EscaneoDeInventario extends Thread {

    private static final int CANTIDAD_LIBROS = 10;
    private static final long DURACION_POR_LIBRO_MS = 100;

    private int cantidadEscaneada = 0;

    @Override
    public void run() {
        for (int i = 1; i <= CANTIDAD_LIBROS; i++) {
            try {
                Thread.sleep(DURACION_POR_LIBRO_MS);
            } catch (InterruptedException e) {
                // No revisa ni reacciona al estado de interrupcion: sigue escaneando igual.
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

Rediséñalo para que revise su estado de interrupción y se detenga antes de tiempo cuando se le pide.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Iniciar el escaneo e interrumpirlo a los 250 ms | Cantidad escaneada impresa | Cercana a 2 libros, sin completar el inventario |

## 📏 Criterios de evaluación de la solución

- Revisa `Thread.currentThread().isInterrupted()` al inicio de cada iteración del bucle.
- Maneja `InterruptedException` (si el pedido de interrupción llega mientras el hilo duerme) saliendo
  del método en vez de seguir escaneando.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño (`InterruptedException` es la
  única excepción que se maneja).

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: implementar interrupción cooperativa para un caso dado.
