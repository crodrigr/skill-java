# 🔑 Solución — Taller 01: Cierre de la agenda diaria

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/03-estructuras-repetitivas.md`).

## 🌳 Árbol de archivos del proyecto final

```text
CierreAgenda
└── src
    └── com
        └── medisalud
            └── CierreAgenda.java
```

## 💻 Archivo: CierreAgenda.java

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

        for (int turno = 1; turno <= cuposDia; turno++) {
            recorridos++;
            if (turno % 4 == 0) {
                controles++;
                continue;
            }
            atendidos++;
            totalFacturado += VALOR_CONSULTA;
            if (totalFacturado >= metaDiaria) {
                break;
            }
        }

        System.out.println("=== " + NOMBRE_CLINICA + ": cierre de agenda ===");
        System.out.println("Turnos recorridos: " + recorridos);
        System.out.println("Turnos atendidos: " + atendidos);
        System.out.println("Turnos de control: " + controles);
        System.out.printf("Total facturado: %.0f%n", totalFacturado);
    }
}
```

## ✅ Resultado esperado

Caso 1 (`cuposDia = 8`, `metaDiaria = 1000000.0`, los datos del código):

```text
=== MediSalud: cierre de agenda ===
Turnos recorridos: 8
Turnos atendidos: 6
Turnos de control: 2
Total facturado: 510000
```

Caso 2 (`cuposDia = 8`, `metaDiaria = 340000.0`):

```text
=== MediSalud: cierre de agenda ===
Turnos recorridos: 5
Turnos atendidos: 4
Turnos de control: 1
Total facturado: 340000
```

Caso 3 (`cuposDia = 0`, `metaDiaria = 340000.0`):

```text
=== MediSalud: cierre de agenda ===
Turnos recorridos: 0
Turnos atendidos: 0
Turnos de control: 0
Total facturado: 0
```

Los montos se muestran con `%.0f`, así que la salida es idéntica en cualquier configuración regional.

## 📋 Tabla de valores del caso 2 (corte por la meta)

Valor de las variables al terminar cada vuelta, hasta que el `break` detiene el bucle en el turno 5:

| vuelta | turno | ¿turno de control? | atendidos | controles | totalFacturado | ¿meta alcanzada? |
|---|---|---|---|---|---|---|
| `1` | `1` | `false` | `1` | `0` | `85000` | `false` |
| `2` | `2` | `false` | `2` | `0` | `170000` | `false` |
| `3` | `3` | `false` | `3` | `0` | `255000` | `false` |
| `4` | `4` | `true (continue)` | `3` | `1` | `255000` | `false` |
| `5` | `5` | `false` | `4` | `1` | `340000` | `true (break)` |

## 🧭 Explicación paso a paso

1. Los contadores (`recorridos`, `atendidos`, `controles`) y el acumulador (`totalFacturado`) se declaran
   **antes** del `for`; dentro se reiniciarían en cada vuelta.
2. `recorridos++` va al **inicio** del bloque, antes de cualquier `continue`, para contar todos los turnos
   que se visitan (incluidos los de control y el último, en que salta el `break`).
3. En los turnos 4 y 8, `turno % 4 == 0` es verdadero: `controles++` y `continue` saltan el resto del
   bloque. El `for` ejecuta `turno++` y sigue.
4. En cada turno facturado se cuenta el turno y se suma la consulta. El `if (totalFacturado >=
   metaDiaria)` va **después** de sumar; así el turno que alcanza la meta **sí** queda facturado.
5. En el caso 2, el turno 5 lleva el total a 340000: se ejecuta el `break` y los turnos 6, 7 y 8 **nunca se
   recorren** (`recorridos` vale 5, no 8).
6. En el caso 3, `1 <= 0` es falso desde el principio: el `for` no ejecuta ninguna vuelta.

## 🐞 Errores comunes observados

| Error | Tipo | Cómo se ve | Corrección |
|---|---|---|---|
| Declarar `totalFacturado` dentro del bucle | lógico | el total se reinicia en cada vuelta; el panel no marca ningún problema | declararlo antes del `for` |
| Usar `turno < cuposDia` | lógico | se recorre un turno de menos | usar `<=` y probar el valor límite |
| Poner `recorridos++` después del `continue` | lógico | los turnos de control no se cuentan como recorridos | ponerlo al inicio del bloque |
| Poner el `break` **antes** de sumar la consulta | lógico | el turno que alcanza la meta queda sin facturar | sumar primero y comprobar la meta después |
| Usar `turno` después del `for` | de compilación | `turno cannot be resolved to a variable` | declararlo antes del bucle o no usarlo fuera |
