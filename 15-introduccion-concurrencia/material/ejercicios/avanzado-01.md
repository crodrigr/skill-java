# 🔴 Avanzado 01 — Corregir un diseño con dos temas técnicos ausentes

## 🧩 Problema

La Biblioteca Universitaria genera el reporte de préstamos de varias sedes con este código, que tiene
dos problemas de diseño distintos:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class GeneradorReportePorSede extends Thread {

    private static final int CANTIDAD_PRESTAMOS = 10;
    private static final long DURACION_POR_PRESTAMO_MS = 100;

    private final String sede;

    public GeneradorReportePorSede(String sede) {
        this.sede = sede;
    }

    @Override
    public void run() {
        for (int i = 1; i <= CANTIDAD_PRESTAMOS; i++) {
            try {
                Thread.sleep(DURACION_POR_PRESTAMO_MS);
            } catch (InterruptedException e) {
                // No revisa ni reacciona al estado de interrupcion: sigue procesando igual.
            }
        }
        System.out.println("Reporte de " + sede + " generado (" + CANTIDAD_PRESTAMOS + " prestamos)");
    }
}
```

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        String[] sedes = {"Sede Centro", "Sede Norte", "Sede Sur"};

        long inicio = System.currentTimeMillis();
        for (String sede : sedes) {
            new GeneradorReportePorSede(sede).start();
        }

        // Margen generoso de espera: no hay ninguna referencia guardada a los hilos, asi que
        // tampoco hay ninguna forma de cancelarlos antes de tiempo si el proceso tarda demasiado.
        Thread.sleep(1200);
        long fin = System.currentTimeMillis();

        System.out.println("Tiempo total: " + (fin - inicio) + " ms (sin posibilidad de cancelar)");
    }
}
```

Encuentra y corrige las dos violaciones por separado:

1. `Demo` crea un hilo por sede sin guardar ninguna referencia — no hay ninguna estructura que permita
   luego consultarlos o cancelarlos.
2. `GeneradorReportePorSede` no revisa en ningún punto su estado de interrupción — aunque se pudiera
   invocar `interrupt()`, no tendría ningún efecto.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Iniciar los tres reportes y esperar un tiempo máximo de 350 ms antes de cancelar | Préstamos procesados antes de cancelar (de un total de 30) | Menor a 30, confirmando que la cancelación funcionó |
| Ejecutar el proceso corregido varias veces | Valor booleano impreso por el programa | `true` en todas las ejecuciones |

## 📏 Criterios de evaluación de la solución

- Guarda una referencia a cada hilo creado (por ejemplo, en un arreglo), para poder consultarlos o
  interrumpirlos más tarde.
- `GeneradorReportePorSede` revisa `isInterrupted()` en cada iteración de su trabajo, deteniéndose antes
  de completar el reporte cuando corresponde.
- El proceso completo invoca `join()` sobre cada hilo antes de leer cuántos préstamos se alcanzaron a
  procesar.
- Las dos correcciones son independientes entre sí: una no depende de la otra.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño (`InterruptedException` es la
  única excepción que se maneja).

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar la creación de hilos para un caso dado.
- **RA-7**: implementar interrupción cooperativa para un caso dado.
- **RA-11**: dado un problema de diseño nuevo, combinar creación, interrupción y unión de hilos.
