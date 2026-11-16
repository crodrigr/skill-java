# 🔑 Solución — Taller 01: Factura de una consulta médica

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/02-operadores-y-estructuras-de-decision.md`).

## 🌳 Árbol de archivos del proyecto final

```text
FacturacionConsulta
└── src
    └── com
        └── medisalud
            └── FacturacionConsulta.java
```

## 💻 Archivo: FacturacionConsulta.java

```java
package com.medisalud;

public class FacturacionConsulta {

    public static void main(String[] args) {
        // Datos de entrada
        final String NOMBRE_CLINICA = "MediSalud";
        final double VALOR_CONSULTA = 85000.0;
        final double DESCUENTO_AFILIADO = 0.10;
        int edadPaciente = 34;
        boolean esAfiliado = true;
        int cantidadConsultas = 3;
        char planCobertura = 'P';

        // 1. Total y descuento: solo los afiliados con 3 o más consultas
        double total = VALOR_CONSULTA * cantidadConsultas;
        double descuento = 0.0;
        if (esAfiliado && cantidadConsultas >= 3) {
            descuento = total * DESCUENTO_AFILIADO;
        }
        double neto = total - descuento;

        // 2. Categoría del paciente según su edad
        String categoria;
        if (edadPaciente < 12) {
            categoria = "Pediátrico";
        } else if (edadPaciente < 18) {
            categoria = "Adolescente";
        } else if (edadPaciente < 65) {
            categoria = "Adulto";
        } else {
            categoria = "Adulto mayor";
        }

        // 3. Copago según el plan de cobertura
        double porcentajeCopago = switch (planCobertura) {
            case 'B' -> 0.30;
            case 'E' -> 0.20;
            case 'P' -> 0.10;
            default -> 1.00;
        };
        double valorAPagar = neto * porcentajeCopago;

        // 4. Tipo de paciente y prioridad de la atención
        String tipoPaciente = esAfiliado ? "Afiliado" : "Particular";
        boolean prioritaria = edadPaciente >= 65 || edadPaciente < 5;
        String atencion = prioritaria ? "Prioritaria" : "General";

        // 5. Factura
        System.out.println("=== " + NOMBRE_CLINICA + " ===");
        System.out.println("Paciente: " + tipoPaciente + ", " + categoria + " (" + edadPaciente + " años)");
        System.out.println("Consultas: " + cantidadConsultas);
        System.out.printf("Total: %.0f%n", total);
        System.out.printf("Descuento: %.0f%n", descuento);
        System.out.printf("A pagar (plan %s): %.0f%n", planCobertura, valorAPagar);
        System.out.println("Atención: " + atencion);
    }
}
```

## ✅ Resultado esperado

Caso 1 (el de los datos de entrada del código):

```text
=== MediSalud ===
Paciente: Afiliado, Adulto (34 años)
Consultas: 3
Total: 255000
Descuento: 25500
A pagar (plan P): 22950
Atención: General
```

Caso 2 (`edadPaciente = 8`, `esAfiliado = false`, `cantidadConsultas = 1`, `planCobertura =
'X'`):

```text
=== MediSalud ===
Paciente: Particular, Pediátrico (8 años)
Consultas: 1
Total: 85000
Descuento: 0
A pagar (plan X): 85000
Atención: General
```

Caso 3 (`edadPaciente = 65`, `esAfiliado = true`, `cantidadConsultas = 2`, `planCobertura =
'B'`):

```text
=== MediSalud ===
Paciente: Afiliado, Adulto mayor (65 años)
Consultas: 2
Total: 170000
Descuento: 0
A pagar (plan B): 51000
Atención: Prioritaria
```

Los montos se muestran con `%.0f`, así que la salida es idéntica en cualquier configuración
regional.

## 🧭 Explicación paso a paso

1. El descuento usa `esAfiliado && cantidadConsultas >= 3`. En el caso 3 el paciente es afiliado
   pero tiene 2 consultas: la comparación es falsa y no hay descuento.
2. El `if - else if - else` de la categoría evalúa de arriba hacia abajo; con `65`, las tres
   primeras condiciones son falsas y entra al `else`: `Adulto mayor`.
3. El `switch` como expresión obtiene el porcentaje de copago; `'X'` no coincide con ningún caso
   y toma el `default` (100%).
4. Dos ternarios asignan el tipo de paciente y la atención. La condición de la atención es
   compuesta: `edadPaciente >= 65 || edadPaciente < 5`.

## 🐞 Errores comunes observados

| Error | Tipo | Cómo se ve | Corrección |
|---|---|---|---|
| `if (edadPaciente = 65)` | de compilación | mensaje del panel Problems: `Type mismatch: cannot convert from int to boolean` | `edadPaciente == 65` |
| Poner `edadPaciente < 65` antes de `edadPaciente < 12` en el `if - else if` | lógico | un niño de 8 años queda como `Adulto`; el panel no marca ningún problema | ordenar las condiciones de la más específica a la más general |
| `switch` sin `default` | de compilación (como expresión) | `A switch expression should have a default case` | agregar `default -> 1.00;` |
| Ternario con tipos distintos en las ramas | de compilación | `Type mismatch: cannot convert from String to int` | que las dos ramas produzcan el mismo tipo |
| Aplicar el descuento con `esAfiliado || cantidadConsultas >= 3` | lógico | descuenta a un afiliado con 1 consulta; el panel no marca ningún problema | usar `&&` |
