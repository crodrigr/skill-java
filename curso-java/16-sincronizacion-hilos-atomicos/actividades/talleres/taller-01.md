# 🛠️ Taller 01 — Sistema de Registro de Pagos Concurrentes de MediSalud

## 🎯 Objetivo

Diseñar, desde cero, un sistema de registro de pagos concurrentes que combine sincronización con las
tres herramientas de hilos del Módulo 15, sin ningún riesgo de interbloqueo (RA-7, RA-8).

## 🌍 Contexto

MediSalud registra los pagos del día en una caja compartida por varios hilos, y también deja un registro
de auditoría de cada pago. Un hilo adicional verifica el stock de insumos y puede tardar más de lo
esperado.

## 🪜 Pasos

1. **Creación de hilos** (Módulo 15): crea tres hilos `RegistradorDePagos`, cada uno registrando 50.000
   pagos de $10 en una `CajaDiaria` compartida.
2. **Sincronización** (este módulo): protege tanto la `CajaDiaria` como un `LibroDeAuditoria` compartido
   (que registra un asiento por cada pago), sincronizando ambos en un bloque `synchronized` anidado.
3. **Orden consistente** (este módulo): si tu diseño sincroniza sobre `CajaDiaria` y `LibroDeAuditoria`
   a la vez, asegurate de que **todos** los hilos los adquieran siempre en el mismo orden, para no
   introducir el riesgo de interbloqueo del Ejemplo 03.
4. **Interrupción de hilos** (Módulo 15): crea un hilo `VerificadorDeStock` que revise su estado de
   interrupción en cada iteración, para poder cancelarse cooperativamente si supera un tiempo máximo.
5. **Unión de hilos** (Módulo 15): invoca `join()` sobre los tres registradores y el verificador antes
   de imprimir el resumen final. Ejecuta con los casos de prueba.

## 💡 Ejemplo resuelto

Así se ve el registro de un pago, una vez resueltos los pasos 2 y 3: sincroniza `caja` y luego
`auditoria`, siempre en ese orden:

```java
// dentro de RegistradorDePagos extends Thread
public void run() {
    for (int i = 0; i < CANTIDAD_PAGOS; i++) {
        synchronized (caja) {
            synchronized (auditoria) {
                caja.registrarPago(MONTO_POR_PAGO);
                auditoria.registrar();
            }
        }
    }
}
```

El resto del diseño (la caja, el libro de auditoría, el verificador cancelable, y la orquestación con
`join()` en `Demo`) queda para ti.

## 📦 Entregable

```text
GestionDePagosConcurrentes/
└── com/medisalud/
    ├── CajaDiaria.java               (Sincronización)
    ├── LibroDeAuditoria.java         (Sincronización)
    ├── RegistradorDePagos.java       (Creación de hilos, orden consistente)
    ├── VerificadorDeStock.java       (Interrupción de hilos)
    └── Demo.java                     (Unión de hilos, integración)
```

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar 3 × 50.000 pagos de $10 | Total recaudado impreso en el resumen | `1500000`, de forma repetible en ejecuciones sucesivas |
| Mismo caso | Registros de auditoría impresos en el resumen | `150000`, igual al total de pagos |
| Iniciar los cuatro hilos y esperar un tiempo máximo de 350 ms antes de interrumpir la verificación | Chequeos de stock completados | Menor a 20, confirmando que la cancelación funcionó |
| Cualquier ejecución | Orden de impresión del resumen | El resumen final se imprime solo después de los cuatro hilos, nunca antes |

## 📏 Criterios de evaluación

- Los tres registradores corren de forma concurrente, no secuencial.
- `CajaDiaria` y `LibroDeAuditoria` están protegidos, y todos los hilos los adquieren siempre en el mismo
  orden.
- `VerificadorDeStock` se cancela cooperativamente cuando se supera el tiempo máximo.
- `Demo` invoca `join()` sobre los cuatro hilos antes de imprimir el resumen final.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
