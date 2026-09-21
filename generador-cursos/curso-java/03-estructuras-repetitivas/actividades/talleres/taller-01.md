# 🛠️ Taller 01 — Cierre de la agenda diaria

## 🎯 Objetivo

Crear desde cero, en Visual Studio Code, un proyecto de consola que recorra los turnos de la agenda de
un día en MediSalud, omita los turnos de control, acumule la facturación, se detenga al alcanzar la meta
y muestre un resumen, comprobándolo con tres casos de prueba (RA-2, RA-4, RA-6, RA-7, RA-8, RA-9,
RA-10).

## 🌍 Contexto

Al cerrar el día, **MediSalud** revisa la agenda con estas reglas:

- La agenda tiene `cuposDia` turnos, numerados desde 1.
- Cada cuarto turno (los múltiplos de 4) es de **control**: se cuenta aparte y **no se factura**.
- Cada turno de consulta factura `VALOR_CONSULTA = 85000.0`.
- La clínica tiene una **meta diaria** (`metaDiaria`): cuando el total facturado la alcanza, se **deja de
  recorrer** la agenda.

Al final se muestra un resumen con los turnos recorridos, atendidos, de control y el total facturado.
Hoy el cierre se hace a mano. Vas a automatizarlo y a probarlo con tres agendas.

## 🪜 Pasos

1. **Crear el proyecto.** En VS Code abre la paleta de comandos (`Ctrl+Shift+P`) y ejecuta **Java:
   Create Java Project... → No build tools**. Llámalo `CierreAgenda`. Dentro de `src` crea la carpeta
   `com/medisalud` y la clase `CierreAgenda.java`, y borra `App.java` (como en el Módulo 1).
2. **Declarar los datos de entrada** al inicio de `main`: las constantes `NOMBRE_CLINICA` y
   `VALOR_CONSULTA`, y las variables `cuposDia` (`int`) y `metaDiaria` (`double`). Después, **antes del
   bucle**, declara los contadores `recorridos`, `atendidos` y `controles` y el acumulador
   `totalFacturado`.
3. **Recorrer los turnos** de 1 a `cuposDia` con un `for`, contando cada turno recorrido.
4. **Omitir los turnos de control** (múltiplos de 4) con `continue`: cuéntalos en `controles` y salta a
   la vuelta siguiente sin facturar.
5. **Facturar los demás turnos**: cuenta el turno atendido, suma `VALOR_CONSULTA` al acumulador y, si
   `totalFacturado` alcanza `metaDiaria`, **detén el bucle con `break`**.
6. **Mostrar el resumen** con el formato exacto de los casos de prueba (`printf` con `%.0f` para el
   total).
7. **Ejecutar** el programa con los tres casos de prueba, cambiando **solo** `cuposDia` y
   `metaDiaria`.
8. **Provocar un error a propósito.** Usa `turno` **después** del `for` (por ejemplo, en un `println`),
   mira el panel **Problems** (`Ctrl+Shift+M`), anota qué dice y corrígelo.

## 💡 Ejemplo resuelto

Este es el comienzo del programa. **No es la solución completa**: completa los `TODO`.

```java
package com.medisalud;

public class CierreAgenda {

    public static void main(String[] args) {
        // Datos de entrada
        final String NOMBRE_CLINICA = "MediSalud";
        final double VALOR_CONSULTA = 85000.0;
        int cuposDia = 8;
        double metaDiaria = 1000000.0;

        // Contadores y acumulador: se declaran antes del bucle
        int recorridos = 0;
        int atendidos = 0;
        int controles = 0;
        double totalFacturado = 0.0;

        // TODO: recorre los turnos de 1 a cuposDia con un for
        //       - cuenta cada turno recorrido
        //       - omite con continue los turnos de control (múltiplos de 4) y cuéntalos
        //       - factura los demás turnos y detén el bucle con break al alcanzar metaDiaria

        // TODO: muestra el resumen con el formato de los casos de prueba
    }
}
```

## 📦 Entregable

El proyecto de Visual Studio Code `CierreAgenda` con esta estructura:

```text
CierreAgenda
└── src
    └── com
        └── medisalud
            └── CierreAgenda.java
```

`CierreAgenda.java` debe compilar sin errores en el panel Problems y producir la salida de cada caso de
prueba.

## 🧪 Casos de prueba

Ejecuta el programa tres veces, cambiando solo `cuposDia` y `metaDiaria`, y compara la salida.

**Caso 1** — `cuposDia = 8`, `metaDiaria = 1000000.0` (la meta no se alcanza: se recorre toda la agenda):

```text
=== MediSalud: cierre de agenda ===
Turnos recorridos: 8
Turnos atendidos: 6
Turnos de control: 2
Total facturado: 510000
```

**Caso 2** — `cuposDia = 8`, `metaDiaria = 340000.0` (**corte por la meta**: el bucle se detiene antes de
terminar la agenda):

```text
=== MediSalud: cierre de agenda ===
Turnos recorridos: 5
Turnos atendidos: 4
Turnos de control: 1
Total facturado: 340000
```

**Caso 3** — `cuposDia = 0`, `metaDiaria = 340000.0` (**cero repeticiones**: el bucle no se ejecuta):

```text
=== MediSalud: cierre de agenda ===
Turnos recorridos: 0
Turnos atendidos: 0
Turnos de control: 0
Total facturado: 0
```

Para el paso 8, este es el mensaje que muestra el panel Problems cuando se usa `turno` fuera del
`for`, en un programa mínimo (la posición depende de tu archivo):

```text
✖ turno cannot be resolved to a variable Java(33554515) [Ln 10, Col 57]
```

## 📏 Criterios de evaluación

- Los tres casos producen exactamente la salida indicada.
- Los turnos de control se omiten con `continue` y no se facturan (el caso 1 tiene dos).
- El bucle se detiene con `break` al alcanzar la meta (el caso 2 corta en el turno 5).
- Con `cuposDia = 0`, el bucle no se ejecuta y el resumen muestra ceros (caso 3).
- Los contadores y el acumulador se declaran **antes** del bucle.
- Los datos de entrada están al inicio de `main` y los identificadores siguen las convenciones del curso.
- Se identifica el mensaje del panel Problems del paso 8 y se corrige el error.
