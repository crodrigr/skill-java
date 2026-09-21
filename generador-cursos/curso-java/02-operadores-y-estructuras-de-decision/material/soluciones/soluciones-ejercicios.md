# 🔑 Soluciones — Ejercicios Módulo 2

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/02-operadores-y-estructuras-de-decision.md`).

Las salidas de este archivo se obtuvieron ejecutando cada programa con JDK 25 y sus mensajes de
error salen del panel Problems de Visual Studio Code.

## 🟢 Básico 01 — Predecir expresiones aritméticas

| Expresión | Resultado | Por qué |
|---|---|---|
| a) `cantidadConsultas * 2 + 1` | `7` | `3 * 2 = 6` y luego `6 + 1` |
| b) `2 + 3 * cantidadConsultas` | `11` | la multiplicación va primero: `3 * 3 = 9`, y luego `2 + 9` |
| c) `(2 + 3) * cantidadConsultas` | `15` | los paréntesis suman primero: `5 * 3` |
| d) `edadPaciente / 10` | `3` | división entera: `34 / 10` |
| e) `edadPaciente % 10` | `4` | el residuo de `34 / 10` |
| f) `diasIncapacidad / 4 * 2.0` | `4.0` | `10 / 4` es entera (`2`) y después `2 * 2.0` |

Salida real del programa que evalúa las seis expresiones:

```text
a) 7
b) 11
c) 15
d) 3
e) 4
f) 4.0
```

## 🟢 Básico 02 — Valor final tras asignaciones compuestas

| Paso | `librosPrestados` | `diasRetraso` | `multaAcumulada` |
|---|---|---|---|
| Inicio | 2 | 0 | 0 |
| 1 | 3 | 0 | 0 |
| 2 | 3 | 5 | 0 |
| 3 | 3 | 5 | 7500 |
| 4 | 1 | 5 | 7500 |
| 5 | 1 | 10 | 7500 |
| 6 | 1 | 3 | 7500 |
| 7 | 1 | 1 | 7500 |
| 8 | 2 | 1 | 7500 |

En el paso 6, `10 / 3` es una división entera y da `3`; en el paso 7, `3 % 2` da el residuo `1`.
Salida real del programa (muestra la variable que cambia en cada paso):

```text
Paso 1: librosPrestados = 3
Paso 2: diasRetraso = 5
Paso 3: multaAcumulada = 7500
Paso 4: librosPrestados = 1
Paso 5: diasRetraso = 10
Paso 6: diasRetraso = 3
Paso 7: diasRetraso = 1
Paso 8: librosPrestados = 2
```

## 🟢 Básico 03 — ¿Verdadero o falso?

| Comparación | Resultado | Por qué |
|---|---|---|
| a) `edadPaciente >= 18` | `true` | `18` es igual a `18` |
| b) `edadPaciente > 18` | `false` | `18` no es mayor que `18` (el valor límite) |
| c) `cantidadConsultas != 3` | `false` | `3` es igual a `3` |
| d) `pesoKg < 60.0` | `false` | `62.5` no es menor que `60.0` |
| e) `pesoKg <= 62.5` | `true` | son iguales |
| f) `edadPaciente == cantidadConsultas * 6` | `true` | `3 * 6 = 18` y `18 == 18` |
| g) `cantidadConsultas + 1 > edadPaciente` | `false` | `4 > 18` es falso |

Salida real del programa:

```text
a) true
b) false
c) false
d) false
e) true
f) true
g) false
```

## 🟢 Básico 04 — Tabla de verdad

**Parte A.**

| Caso | `disponible` | `librosPrestados` | `diasRetraso` | ¿Autorizado? | Por qué |
|---|---|---|---|---|---|
| 1 | `true` | 2 | 0 | `true` | cumple las tres condiciones |
| 2 | `true` | 3 | 0 | `false` | `3 < 3` es falso (alcanzó el límite) |
| 3 | `false` | 1 | 0 | `false` | el libro no está disponible |
| 4 | `true` | 1 | 4 | `false` | tiene retraso |
| 5 | `true` | 0 | 0 | `true` | cumple las tres condiciones |

**Parte B.** A: `false`, B: `true` (`40 / 2 = 20 > 10`), C: `false` (`40 / 4 = 10`, que no es mayor
que 10). En el caso A (`librosPrestados = 0`) la segunda condición **no se evalúa**: como la
primera es falsa, `&&` ya es falso y la división entre cero nunca se ejecuta (cortocircuito).

Salida real del programa (casos 1 a 5 y A a C):

```text
1) true
2) false
3) false
4) false
5) true
A) false
B) true
C) false
```

## 🟢 Básico 05 — ¿Qué rama ejecuta?

| `diasRetraso` | Rama | Texto que se muestra |
|---|---|---|
| 0 | `else` | `Estado: Al día` |
| 1 | `else if` | `Estado: Con multa` |
| 30 | `else if` | `Estado: Con multa` |
| 31 | `if` | `Estado: Suspendido` |

Con `30`, la condición `diasRetraso > 30` es falsa (no incluye el `30`), así que se evalúa la
siguiente, `diasRetraso > 0`, que es verdadera. Estos valores salen de ejecutar la regla:

| diasRetraso | Estado |
|---|---|
| `0` | Al día |
| `1` | Con multa |
| `30` | Con multa |
| `31` | Suspendido |

## 🟡 Intermedio 01 — Clasificar el índice de masa corporal

```java
public class ClasificacionImc {

    public static void main(String[] args) {
        // Datos de entrada
        double pesoKg = 100.0;
        double estaturaM = 2.0;

        double imc = pesoKg / (estaturaM * estaturaM);
        String clasificacion;
        if (imc < 18.5) {
            clasificacion = "Bajo peso";
        } else if (imc < 25) {
            clasificacion = "Normal";
        } else if (imc < 30) {
            clasificacion = "Sobrepeso";
        } else {
            clasificacion = "Obesidad";
        }

        System.out.printf("IMC: %.1f%n", imc);
        System.out.println("Clasificación: " + clasificacion);
    }
}
```

Salida con los datos de partida (`pesoKg = 100.0`, `estaturaM = 2.0`):

```text
IMC: 25.0
Clasificación: Sobrepeso
```

Resultado de los seis casos de prueba (con `estaturaM = 2.0`); los valores `18.50`, `25.00` y
`30.00` son los límites y caen en el tramo **de arriba** porque las condiciones usan `<`:

| pesoKg | IMC | Clasificación |
|---|---|---|
| `73.0` | `18.25` | Bajo peso |
| `74.0` | `18.50` | Normal |
| `99.0` | `24.75` | Normal |
| `100.0` | `25.00` | Sobrepeso |
| `119.0` | `29.75` | Sobrepeso |
| `120.0` | `30.00` | Obesidad |

El separador decimal de tu pantalla puede ser coma o punto según la configuración regional.

## 🟡 Intermedio 02 — Menú de planes de cobertura con switch

```java
public class MenuPlanes {

    public static void main(String[] args) {
        // Datos de entrada
        final double VALOR_CONSULTA = 85000.0;
        char planCobertura = 'E';

        double porcentajeCopago = switch (planCobertura) {
            case 'B' -> 0.30;
            case 'E' -> 0.20;
            case 'P' -> 0.10;
            default -> 1.00;
        };

        double copago = VALOR_CONSULTA * porcentajeCopago;
        System.out.printf("Plan %s: copago de %.0f%n", planCobertura, copago);
    }
}
```

Salida con los datos de partida (`planCobertura = 'E'`):

```text
Plan E: copago de 17000
```

Copago de cada caso de prueba:

| planCobertura | Copago |
|---|---|
| `'B'` | `25500` |
| `'E'` | `17000` |
| `'P'` | `8500` |
| `'X'` | `85000` |

Es válida también la forma clásica (`case 'B': ... break;`) siempre que cada caso termine con
`break` y exista `default`.

## 🟡 Intermedio 03 — De if-else a ternario

**Parte A.**

```java
public class EstadoPrestamo {

    public static void main(String[] args) {
        // Datos de entrada
        int diasRetraso = 5;

        String estado = diasRetraso > 0 ? "Con retraso" : "Al día";
        System.out.println("Estado: " + estado);
    }
}
```

```text
Estado: Con retraso
```

Resultado de los tres casos de prueba:

| diasRetraso | Estado |
|---|---|
| `0` | Al día |
| `1` | Con retraso |
| `5` | Con retraso |

**Parte B (respuesta modelo).** No conviene un ternario: la regla tiene **tres** resultados
(`Al día`, `Con multa`, `Suspendido`) y se expresa con rangos. Se usaría `if - else if - else`,
como en el Básico 05. Un ternario anidado sería difícil de leer y de probar.

## 🔴 Avanzado 01 — Corregir un programa con errores

**Ronda 1 — errores de compilación.** Mensajes del panel Problems para el código de partida:

```text
✖ The operator < is undefined for the argument type(s) boolean, int Java(536871072) [Ln 12, Col 28]
✖ A switch expression should have a default case Java(1073743531) [Ln 14, Col 36]
✖ Type mismatch: cannot convert from int to boolean Java(16777233) [Ln 19, Col 13]
```

| Mensaje | Causa | Corrección |
|---|---|---|
| `The operator < is undefined for the argument type(s) boolean, int` | `18 <= edadPaciente` da un `boolean` que no se puede comparar con `65` | `edadPaciente >= 18 && edadPaciente < 65` |
| `A switch expression should have a default case` | una expresión `switch` debe cubrir todos los valores | agregar `default -> 0.0;` |
| `Type mismatch: cannot convert from int to boolean` | `edadPaciente = 65` asigna en lugar de comparar | `edadPaciente == 65` |

**Ronda 2 — el error que el panel no marca.** Con los tres errores corregidos, el programa
compila (el panel Problems no marca ningún problema):

```java error-logico
package com.medisalud;

public class TarifaPaciente {

    public static void main(String[] args) {
        // Datos de entrada
        final double VALOR_CONSULTA = 85000.0;
        int edadPaciente = 30;
        String prefijoPlan = "PRE";
        String tipoPlan = prefijoPlan + "MIUM";

        boolean esAdulto = edadPaciente >= 18 && edadPaciente < 65;
        boolean esPremium = tipoPlan == "PREMIUM";
        double descuento = switch (tipoPlan) {
            case "PREMIUM" -> 0.20;
            case "ESTANDAR" -> 0.10;
            default -> 0.0;
        };

        if (edadPaciente == 65) {
            System.out.println("Atención prioritaria");
        }

        double tarifa = VALOR_CONSULTA - VALOR_CONSULTA * descuento;
        System.out.println("¿Es adulto? " + esAdulto);
        System.out.println("¿Es premium? " + esPremium);
        System.out.printf("Tarifa: %.0f%n", tarifa);
    }
}
```

Pero su salida no coincide con la esperada:

```text
¿Es adulto? true
¿Es premium? false
Tarifa: 68000
```

`¿Es premium?` muestra `false` para un plan `PREMIUM`: el texto del plan se armó al ejecutar el
programa y `tipoPlan == "PREMIUM"` compara **si es el mismo texto en memoria**, no lo que dice.
Se corrige con `equals`:

```java
package com.medisalud;

public class TarifaPaciente {

    public static void main(String[] args) {
        // Datos de entrada
        final double VALOR_CONSULTA = 85000.0;
        int edadPaciente = 30;
        String prefijoPlan = "PRE";
        String tipoPlan = prefijoPlan + "MIUM";

        boolean esAdulto = edadPaciente >= 18 && edadPaciente < 65;
        boolean esPremium = tipoPlan.equals("PREMIUM");
        double descuento = switch (tipoPlan) {
            case "PREMIUM" -> 0.20;
            case "ESTANDAR" -> 0.10;
            default -> 0.0;
        };

        if (edadPaciente == 65) {
            System.out.println("Atención prioritaria");
        }

        double tarifa = VALOR_CONSULTA - VALOR_CONSULTA * descuento;
        System.out.println("¿Es adulto? " + esAdulto);
        System.out.println("¿Es premium? " + esPremium);
        System.out.printf("Tarifa: %.0f%n", tarifa);
    }
}
```

```text
¿Es adulto? true
¿Es premium? true
Tarifa: 68000
```

## 🏆 Desafío 01 — Autorización de préstamo con salida exacta

Solución (la del caso 1; los otros casos solo cambian los datos de entrada):

```java
package com.biblioteca;

public class AutorizacionPrestamo {

    public static void main(String[] args) {
        // Datos de entrada
        final int MAX_LIBROS_PRESTAMO = 3;
        final double MULTA_POR_DIA = 1500.0;
        final double MULTA_MAXIMA = 30000.0;
        String tipoUsuario = "DOCENTE";
        int librosPrestados = 2;
        int diasRetraso = 0;
        boolean disponible = true;

        // Multa por retraso, con tope
        double multa = diasRetraso * MULTA_POR_DIA;
        if (multa > MULTA_MAXIMA) {
            multa = MULTA_MAXIMA;
        }

        // Días de préstamo según el tipo de usuario
        int diasPrestamo = switch (tipoUsuario) {
            case "ESTUDIANTE" -> 7;
            case "DOCENTE", "INVESTIGADOR" -> 15;
            default -> 0;
        };

        // Estado del usuario según el retraso
        String estado;
        if (diasRetraso > 30) {
            estado = "Suspendido";
        } else if (diasRetraso > 0) {
            estado = "Con multa";
        } else {
            estado = "Al día";
        }

        // Autorización del préstamo
        boolean autorizado = disponible && librosPrestados < MAX_LIBROS_PRESTAMO && diasRetraso == 0;
        String decision = autorizado ? "Préstamo autorizado" : "Préstamo denegado";
        String motivo;
        if (!disponible) {
            motivo = "El libro no está disponible";
        } else if (librosPrestados >= MAX_LIBROS_PRESTAMO) {
            motivo = "Alcanzó el límite de libros";
        } else if (diasRetraso > 0) {
            motivo = "Tiene retraso pendiente";
        } else {
            motivo = "Cumple todas las condiciones";
        }

        System.out.println("=== Biblioteca Universitaria ===");
        System.out.println("Usuario: " + tipoUsuario);
        System.out.println("Libros prestados: " + librosPrestados + " de " + MAX_LIBROS_PRESTAMO);
        System.out.println("Días de retraso: " + diasRetraso);
        System.out.printf("Multa: %.0f%n", multa);
        System.out.println("Estado: " + estado);
        System.out.println("Días de préstamo: " + diasPrestamo);
        System.out.println("Decisión: " + decision);
        System.out.println("Motivo: " + motivo);
    }
}
```

Salida de cada caso (las mismas del enunciado):

**Caso 1:**

```text
=== Biblioteca Universitaria ===
Usuario: DOCENTE
Libros prestados: 2 de 3
Días de retraso: 0
Multa: 0
Estado: Al día
Días de préstamo: 15
Decisión: Préstamo autorizado
Motivo: Cumple todas las condiciones
```

**Caso 2:**

```text
=== Biblioteca Universitaria ===
Usuario: ESTUDIANTE
Libros prestados: 3 de 3
Días de retraso: 0
Multa: 0
Estado: Al día
Días de préstamo: 7
Decisión: Préstamo denegado
Motivo: Alcanzó el límite de libros
```

**Caso 3:**

```text
=== Biblioteca Universitaria ===
Usuario: INVESTIGADOR
Libros prestados: 1 de 3
Días de retraso: 12
Multa: 18000
Estado: Con multa
Días de préstamo: 15
Decisión: Préstamo denegado
Motivo: Tiene retraso pendiente
```

**Caso 4:**

```text
=== Biblioteca Universitaria ===
Usuario: ESTUDIANTE
Libros prestados: 2 de 3
Días de retraso: 35
Multa: 30000
Estado: Suspendido
Días de préstamo: 7
Decisión: Préstamo denegado
Motivo: Tiene retraso pendiente
```

Justificación de las estructuras (la que debe aparecer en los comentarios del estudiante):

- **Multa con tope**: `if`, porque solo corrige el valor cuando supera el tope.
- **Días de préstamo**: `switch` como expresión, porque compara un mismo valor con opciones
  exactas.
- **Estado por retraso**: `if - else if - else`, porque son tramos.
- **Autorización**: operador `&&`, porque deben cumplirse las tres condiciones.
- **Decisión final**: operador ternario, porque son dos resultados y solo se asigna un texto.
- **Motivo**: `if - else if - else`, porque el orden de las condiciones define el motivo.
