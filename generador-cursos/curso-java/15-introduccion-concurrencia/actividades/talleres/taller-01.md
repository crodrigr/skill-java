# 🛠️ Taller 01 — Sistema de Procesamiento de Tareas de Fin de Día de MediSalud

## 🎯 Objetivo

Diseñar, desde cero, un sistema de procesamiento de tareas de fin de día que combine los tres temas
técnicos de este módulo: creación, interrupción y unión de hilos (RA-11).

## 🌍 Contexto

MediSalud necesita ejecutar, al cierre del día, tres tareas independientes entre sí: enviar los
recordatorios de las citas de mañana, generar el reporte de facturación diaria, y procesar los análisis
de laboratorio pendientes. El procesamiento de análisis puede tardar más de lo esperado; si supera un
tiempo máximo, debe cancelarse cooperativamente sin perder el trabajo ya hecho. El sistema solo debe
imprimir el resumen final una vez que las tres tareas terminaron (completas o canceladas), nunca antes.

## 🪜 Pasos

1. **Creación de hilos**: Crea un hilo para enviar los recordatorios de las citas de mañana (con
   `Runnable`, por ejemplo una expresión lambda o una referencia a método), y un hilo
   `GeneradorDeReporteDeFacturacion extends Thread` para generar el reporte de facturación.
2. **Interrupción de hilos**: Crea un hilo `ProcesadorDeAnalisisPendientes extends Thread` que procese
   un lote de análisis pendientes, revisando su estado de interrupción en cada iteración para poder
   detenerse cooperativamente antes de completar el lote.
3. **Unión de hilos**: Tras iniciar los tres hilos, espera un tiempo máximo e interrumpe el
   procesamiento de análisis si sigue en curso; luego invoca `join()` sobre los tres hilos antes de leer
   cualquiera de sus resultados.
4. **Integración**: Con las tres piezas anteriores, imprime un resumen final consolidado (facturación
   total y cantidad de análisis procesados) solo después de que los tres hilos terminaron. Ejecuta con
   los casos de prueba.

## 💡 Ejemplo resuelto

Así se ve el procesador de análisis, una vez resuelto el paso 2: revisa su estado de interrupción en cada
iteración, deteniéndose antes de completar el lote cuando corresponde, sin perder la cuenta de lo ya
procesado:

```java
// dentro de ProcesadorDeAnalisisPendientes extends Thread
public void run() {
    for (int i = 1; i <= CANTIDAD_ANALISIS; i++) {
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        try {
            Thread.sleep(DURACION_POR_ANALISIS_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        cantidadProcesada++;
    }
}
```

El resto del diseño (el envío de recordatorios, el reporte de facturación, y la orquestación con
`join()` en `Demo`) queda para ti.

## 📦 Entregable

```text
GestionDeTareasClinicas/
└── com/medisalud/
    ├── EnviadorDeRecordatorios.java           (Creación de hilos)
    ├── GeneradorDeReporteDeFacturacion.java   (Creación de hilos)
    ├── ProcesadorDeAnalisisPendientes.java    (Interrupción de hilos)
    └── Demo.java                              (Unión de hilos, integración)
```

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| Iniciar los tres hilos y esperar un tiempo máximo de 350 ms antes de interrumpir el análisis | Análisis procesados impresos en el resumen | Menor a 10, confirmando que la cancelación funcionó |
| Ejecutar el programa completo varias veces | Facturación total impresa en el resumen | `10000.0`, de forma repetible en ejecuciones sucesivas |
| Cualquier ejecución | Orden de impresión del resumen | El resumen final se imprime solo después de los tres hilos, nunca antes |

## 📏 Criterios de evaluación

- Los tres hilos (recordatorios, facturación, análisis) corren de forma concurrente, no secuencial.
- `ProcesadorDeAnalisisPendientes` revisa su estado de interrupción en cada iteración y se detiene
  cooperativamente cuando se supera el tiempo máximo.
- `Demo` invoca `join()` sobre los tres hilos antes de imprimir el resumen final.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
