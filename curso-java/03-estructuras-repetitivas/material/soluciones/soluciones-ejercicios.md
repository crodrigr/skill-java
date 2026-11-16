# 🔑 Soluciones — Ejercicios Módulo 3

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/03-estructuras-repetitivas.md`).

## 🟢 Básico 01 — Prueba de escritorio de un while

| vuelta | dia | ¿dia <= diasRetraso? | multa |
|---|---|---|---|
| `1` | `1` | `true` | `1500` |
| `2` | `2` | `true` | `3000` |
| `3` | `3` | `true` | `4500` |
| `4` | `4` | `true` | `6000` |
| — | `5` | `false` | `6000` |

El bloque se ejecuta **4 veces** y `multa` vale **6000** al final. La última fila es la
comprobación que termina el bucle: con `dia = 5`, la condición `5 <= 4` es falsa. Salida real del
programa:

```text
Multa: 6000
```

Con `diasRetraso = 0`, la condición `1 <= 0` es falsa desde el principio: el bloque no se ejecuta ninguna vez
y `multa` queda en `0`.

## 🟡 Intermedio 01 — Acumular con while

```java
public class MultaAcumulada {

    public static void main(String[] args) {
        // Datos de entrada
        final double MULTA_POR_DIA = 1500.0;
        int diasRetraso = 5;

        int dia = 1;
        double multa = 0.0;
        while (dia <= diasRetraso) {
            multa += MULTA_POR_DIA;
            dia++;
        }

        System.out.printf("Días de retraso: %d, multa: %.0f%n", diasRetraso, multa);
    }
}
```

Salida con los datos de partida (`diasRetraso = 5`):

```text
Días de retraso: 5, multa: 7500
```

Resultado de los tres casos de prueba:

| diasRetraso | multa |
|---|---|
| `0` | `0` |
| `1` | `1500` |
| `5` | `7500` |

El contador `dia` y el acumulador `multa` se declaran **antes** del bucle, y `dia++` está dentro del
bloque; sin esa línea, el bucle no terminaría.

## 🟢 Básico 02 — Do-while frente a while

| turno inicial | Veces con while | Veces con do-while | turno al terminar el do-while |
|---|---|---|---|
| `9` | `0` | `1` | `10` |
| `7` | `2` | `2` | `9` |

- Con `turno = 9` y `cuposDia = 8`, la condición `9 <= 8` es falsa desde el principio: el `while` hace
  **0** vueltas y el `do-while` hace **1** (ejecuta el bloque y **después** evalúa la condición).
- Con `turno = 7`, la condición es verdadera al principio: los dos bucles hacen **2** vueltas (turnos 7 y
  8) y coinciden.

Salida real del programa con `turno = 9`:

```text
Con while: 0 veces
Con do-while: 1 veces
```

## 🟢 Básico 03 — Secuencias de un for

| Secuencia | Qué muestra (o cuántas veces se ejecuta) |
|---|---|
| A | `1 3 5 7 9` (el siguiente valor, 11, no cumple `dia <= 9`) |
| B | `5 4 3 2 1` (con 0 la condición `libro >= 1` es falsa) |
| C, con `renovacion < 4` | 3 veces (renovaciones 1, 2 y 3) |
| C, con `renovacion <= 4` | 4 veces (renovaciones 1, 2, 3 y 4) |

Salida real del programa:

```text
A: 1 3 5 7 9
B: 5 4 3 2 1
C: 3 y 4
```

## 🟢 Básico 05 — ¿Termina este bucle?

| Bucle | ¿Termina? | ¿Cuántas veces se ejecuta el bloque? | ¿Qué problema tiene? |
|---|---|---|---|
| A | no termina | infinitas | falta libro++ dentro del while |
| B | no termina | infinitas | el ; deja el while con cuerpo vacío |
| C | termina | `4` | empieza en 0 y usa <=: una repetición de más (se esperaban 3) |
| D | termina | `3` | ninguno: es correcto |

Salida real de cada bucle (los de A y B se ejecutaron con un tiempo límite porque **no terminan**; solo
alcanzan a mostrar su primera línea):

- **A:**

```text
Revisando libros...
```

- **B:**

```text
Revisando libros...
```

- **C:**

```text
Renovaciones registradas: 4
```

- **D:**

```text
Multa: 4500
```

## 🟡 Intermedio 02 — Tabla de cuotas con for

**Parte A.**

```java
public class CuotasMulta {

    public static void main(String[] args) {
        // Datos de entrada
        int multaTotal = 30000;
        int numeroCuotas = 3;

        int valorCuota = multaTotal / numeroCuotas;
        for (int cuota = 1; cuota <= numeroCuotas; cuota++) {
            System.out.println("Cuota " + cuota + ": " + valorCuota);
        }
    }
}
```

```text
Cuota 1: 10000
Cuota 2: 10000
Cuota 3: 10000
```

Valor de cada cuota en los cuatro casos de prueba:

| numeroCuotas | valorCuota |
|---|---|
| `1` | `30000` |
| `2` | `15000` |
| `3` | `10000` |
| `4` | `7500` |

**Parte B.**

```java
public class CuotasMultaWhile {

    public static void main(String[] args) {
        // Datos de entrada
        int multaTotal = 30000;
        int numeroCuotas = 3;

        int valorCuota = multaTotal / numeroCuotas;
        int cuota = 1;
        while (cuota <= numeroCuotas) {
            System.out.println("Cuota " + cuota + ": " + valorCuota);
            cuota++;
        }
    }
}
```

```text
Cuota 1: 10000
Cuota 2: 10000
Cuota 3: 10000
```

**Respuesta modelo de la justificación.** Conviene el `for`: se sabe de antemano cuántas veces se
repite (`numeroCuotas`) y la inicialización, la condición y la actualización quedan juntas en una
línea, sin riesgo de olvidar `cuota++`.

## 🟢 Básico 04 — ¿Qué se omite?

| Recorrido | Turnos que se suman | Turnos que no se suman | Suma final |
|---|---|---|---|
| A (con `break`) | 1, 2 y 3 | 4, 5 y 6 | 6 |
| B (con `continue`) | 1, 2, 3, 5 y 6 | 4 | 17 |

En A, el `break` termina el bucle cuando `turno` vale 4: los turnos 4, 5 y 6 **no se recorren**. En B, el
`continue` solo omite el turno 4 y el bucle sigue con el 5 y el 6. Salida real:

```text
A: suma = 6
B: suma = 17
```

## 🟡 Intermedio 03 — Recorrido con continue y break

```java
public class DiasHabiles {

    public static void main(String[] args) {
        // Datos de entrada
        final int MAX_DIAS_HABILES = 10;
        int diasPrestamo = 15;

        int habiles = 0;
        for (int dia = 1; dia <= diasPrestamo; dia++) {
            if (dia % 7 == 0) {
                continue;
            }
            habiles++;
            if (habiles == MAX_DIAS_HABILES) {
                break;
            }
        }

        System.out.println("Días hábiles contados: " + habiles);
    }
}
```

Salida con los datos de partida (`diasPrestamo = 15`):

```text
Días hábiles contados: 10
```

Resultado de los cuatro casos de prueba:

| diasPrestamo | días hábiles |
|---|---|
| `6` | `6` |
| `7` | `6` |
| `14` | `10` |
| `15` | `10` |

Con `14` y `15` se alcanza el máximo de 10 días hábiles y el `break` detiene el recorrido: con 14 días
habría 12 hábiles sin ese tope.

## 🔴 Avanzado 01 — Corregir un cierre de agenda

**Ronda 1 — errores de compilación.** Mensajes del panel Problems para el código de partida:

```text
✖ cuota cannot be resolved to a variable Java(33554515) [Ln 28, Col 59]
✖ Syntax error on token ")", ; expected after this token Java(1610612967) [Ln 34, Col 47]
```

| Mensaje | Causa | Corrección |
|---|---|---|
| `cuota cannot be resolved to a variable` | `cuota` solo existe dentro del `for` y se usa después | quitar esa línea o declarar la variable antes del bucle |
| `Syntax error on token ")", ; expected after this token` | falta el `;` final del `do-while` | escribir `} while (copias < resumenesSolicitados);` |

**Ronda 2 — el programa no termina.** Con los dos errores corregidos, el programa compila; el panel
Problems no marca ningún error:

> ⚠️ **Este programa no termina.** Si lo ejecutas, quedará en un bucle sin fin. Detenlo con `Ctrl+C`
> en la terminal donde se ejecuta, o con el botón de detener de la barra de depuración.

```java no-termina
package com.medisalud;

public class CierreParcial {

    public static void main(String[] args) {
        // Datos de entrada
        final double VALOR_CONSULTA = 85000.0;
        int cuposDia = 7;
        int resumenesSolicitados = 1;

        int turno = 1;
        int atendidos = 0;
        double totalFacturado = 0.0;
        System.out.println("Cerrando la agenda...");
        while (turno <= cuposDia) {
            if (turno % 4 == 0) {
                continue;
            }
            atendidos++;
            totalFacturado += VALOR_CONSULTA;
            turno++;
        }

        double valorCuota = totalFacturado / 3;
        for (int cuota = 1; cuota <= 3; cuota++) {
            System.out.printf("Cuota %d: %.0f%n", cuota, valorCuota);
        }

        int copias = 0;
        do {
            copias++;
            System.out.printf("Resumen %d: %d turnos, total %.0f%n", copias, atendidos, totalFacturado);
        } while (copias < resumenesSolicitados);
    }
}
```

```text
Cerrando la agenda...
```

El programa muestra su primera línea y no avanza más. Al llegar al turno 4 (de control), el `continue`
**salta el `turno++`** que está al final del bloque: `turno` sigue valiendo 4 y la condición siguiente es
verdadera para siempre. La corrección es actualizar el contador **antes** del `continue` (`turno++;
continue;`).

**Ronda 3 — el error que el panel no marca.** Con el bucle arreglado, el programa termina y el panel
Problems no marca ningún problema:

```java error-logico
package com.medisalud;

public class CierreParcial {

    public static void main(String[] args) {
        // Datos de entrada
        final double VALOR_CONSULTA = 85000.0;
        int cuposDia = 7;
        int resumenesSolicitados = 1;

        int turno = 1;
        int atendidos = 0;
        double totalFacturado = 0.0;
        System.out.println("Cerrando la agenda...");
        while (turno < cuposDia) {
            if (turno % 4 == 0) {
                turno++;
                continue;
            }
            atendidos++;
            totalFacturado += VALOR_CONSULTA;
            turno++;
        }

        double valorCuota = totalFacturado / 3;
        for (int cuota = 1; cuota <= 3; cuota++) {
            System.out.printf("Cuota %d: %.0f%n", cuota, valorCuota);
        }

        int copias = 0;
        do {
            copias++;
            System.out.printf("Resumen %d: %d turnos, total %.0f%n", copias, atendidos, totalFacturado);
        } while (copias < resumenesSolicitados);
    }
}
```

Pero su salida no coincide con la esperada:

```text
Cerrando la agenda...
Cuota 1: 141667
Cuota 2: 141667
Cuota 3: 141667
Resumen 1: 5 turnos, total 425000
```

Se atienden 5 turnos en lugar de 6: la condición `turno < cuposDia` deja **fuera el último turno**
(el 7). Se corrige con `<=`:

```java
package com.medisalud;

public class CierreParcial {

    public static void main(String[] args) {
        // Datos de entrada
        final double VALOR_CONSULTA = 85000.0;
        int cuposDia = 7;
        int resumenesSolicitados = 1;

        int turno = 1;
        int atendidos = 0;
        double totalFacturado = 0.0;
        System.out.println("Cerrando la agenda...");
        while (turno <= cuposDia) {
            if (turno % 4 == 0) {
                turno++;
                continue;
            }
            atendidos++;
            totalFacturado += VALOR_CONSULTA;
            turno++;
        }

        double valorCuota = totalFacturado / 3;
        for (int cuota = 1; cuota <= 3; cuota++) {
            System.out.printf("Cuota %d: %.0f%n", cuota, valorCuota);
        }

        int copias = 0;
        do {
            copias++;
            System.out.printf("Resumen %d: %d turnos, total %.0f%n", copias, atendidos, totalFacturado);
        } while (copias < resumenesSolicitados);
    }
}
```

```text
Cerrando la agenda...
Cuota 1: 170000
Cuota 2: 170000
Cuota 3: 170000
Resumen 1: 6 turnos, total 510000
```

## 🏆 Desafío 01 — Plan de cobro de una multa con salida exacta

Solución (la del caso 1; los otros casos solo cambian los datos de entrada):

```java
package com.biblioteca;

public class PlanCobroMulta {

    public static void main(String[] args) {
        // Datos de entrada
        final double MULTA_POR_DIA = 1500.0;
        final double MULTA_MAXIMA = 30000.0;
        int diasRetraso = 0;
        int numeroCuotas = 1;

        // 1. La multa se acumula solo en días hábiles y se detiene en el tope
        double multa = 0.0;
        int diasCobrados = 0;
        for (int dia = 1; dia <= diasRetraso; dia++) {
            if (dia % 7 == 0) {
                continue;
            }
            multa += MULTA_POR_DIA;
            diasCobrados++;
            if (multa >= MULTA_MAXIMA) {
                break;
            }
        }

        // 2. Se reparte en cuotas iguales
        double valorCuota = multa / numeroCuotas;

        // 3. Comunicado
        System.out.println("=== Biblioteca Universitaria ===");
        System.out.println("Días de retraso: " + diasRetraso);
        System.out.println("Días cobrados: " + diasCobrados);
        System.out.printf("Multa: %.0f%n", multa);
        System.out.println("Cuotas del plan de pago: " + numeroCuotas);
        for (int cuota = 1; cuota <= numeroCuotas; cuota++) {
            System.out.printf("Cuota %d: %.0f%n", cuota, valorCuota);
        }
    }
}
```

Las salidas de los cuatro casos son las del enunciado. Justificación de las estructuras (la que debe
aparecer en los comentarios del estudiante):

- **Recorrer los días de retraso**: `for`, porque es un rango conocido (1 a `diasRetraso`).
- **Omitir los domingos**: `continue`, porque solo se salta la vuelta y el recorrido sigue.
- **Detener al llegar al tope**: `break`, porque el resto de los días ya no cambia la multa.
- **Listar las cuotas**: `for`, porque se sabe cuántas veces se repite (`numeroCuotas`).
- **Contadores y acumulador**: `diasCobrados` y `multa` se declaran antes del bucle.
