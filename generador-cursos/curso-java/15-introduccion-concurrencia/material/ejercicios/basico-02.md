# 🟢 Básico 02 — Identificar violación de interrupción de hilos

## 🧩 Problema

La Biblioteca Universitaria escanea el inventario completo de una estantería con este código:

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

Sin escribir código, responde: si el bibliotecario ya no necesita el resultado del escaneo y quiere
cancelarlo antes de tiempo, ¿qué pasa si invoca `escaneo.interrupt()`?

## 📏 Criterios de evaluación de la solución

- Identifica que `EscaneoDeInventario` no revisa en ningún punto su estado de interrupción, así que
  `interrupt()` no tiene ningún efecto visible: el escaneo completa los diez libros igual.
- Explica que eso es una violación de la interrupción cooperativa: el hilo debería revisar
  `isInterrupted()` (o reaccionar a `InterruptedException`) para poder detenerse antes de tiempo.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: explicar la interrupción cooperativa de hilos.
- **RA-6**: identificar un hilo que no revisa su estado de interrupción en un fragmento dado.
