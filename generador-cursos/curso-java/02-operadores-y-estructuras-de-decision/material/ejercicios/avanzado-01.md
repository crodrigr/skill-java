# 🔴 Avanzado 01 — Corregir un programa con errores

## 🧩 Problema

Un compañero escribió el cálculo de la tarifa de una consulta de **MediSalud**, pero el programa
tiene errores. Debe mostrar, para un paciente de 30 años con plan `PREMIUM`, si es adulto, si su
plan es premium y la tarifa con el descuento del plan (20% para `PREMIUM`, 10% para
`ESTANDAR` y ninguno para cualquier otro).

Resuélvelo en **dos rondas**:

- **Ronda 1.** Copia el código en VS Code y corrige los errores que marca el panel **Problems**
  (`Ctrl+Shift+M`) hasta que el programa compile. Anota qué dice cada mensaje.
- **Ronda 2.** Ejecuta el programa corregido y compara su salida con la esperada. Si algo no
  coincide, el panel Problems **no** te lo va a decir: encuéntralo tú y corrígelo.

## 💻 Código o contexto de partida

```java no-compila
package com.medisalud;

public class TarifaPaciente {

    public static void main(String[] args) {
        // Datos de entrada
        final double VALOR_CONSULTA = 85000.0;
        int edadPaciente = 30;
        String prefijoPlan = "PRE";
        String tipoPlan = prefijoPlan + "MIUM";

        boolean esAdulto = 18 <= edadPaciente < 65;
        boolean esPremium = tipoPlan == "PREMIUM";
        double descuento = switch (tipoPlan) {
            case "PREMIUM" -> 0.20;
            case "ESTANDAR" -> 0.10;
        };

        if (edadPaciente = 65) {
            System.out.println("Atención prioritaria");
        }

        double tarifa = VALOR_CONSULTA - VALOR_CONSULTA * descuento;
        System.out.println("¿Es adulto? " + esAdulto);
        System.out.println("¿Es premium? " + esPremium);
        System.out.printf("Tarifa: %.0f%n", tarifa);
    }
}
```

## 🧪 Casos de prueba

Datos de entrada del caso: `edadPaciente = 30` y plan `PREMIUM`. Salida que debe mostrar el
programa corregido:

```text
¿Es adulto? true
¿Es premium? true
Tarifa: 68000
```

Cambia después `edadPaciente` a `65` para comprobar el mensaje de atención prioritaria, y el
plan a `ESTANDAR` y a otro valor para comprobar el descuento por defecto.

## 📏 Criterios de evaluación de la solución

- El programa compila y, con los datos del caso, muestra exactamente la salida esperada.
- Los errores de compilación se corrigen entendiendo el mensaje, no probando al azar.
- Se corrige también el error que el compilador no marca, y se explica por qué ocurría.
- El programa conserva la estructura original (los mismos datos de entrada y la misma tarifa).

## 🚧 Restricciones

- No elimines ninguna de las decisiones del programa: corrígelas.
- Usa los operadores y estructuras del módulo; no cambies el tipo de los datos.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-4**: distinguir la asignación de la comparación de igualdad y comparar texto con `equals`.
- **RA-5**: expresar un rango con operadores condicionales.
- **RA-7**: completar un `switch` como expresión con su caso por defecto.
- **RA-11**: identificar y corregir errores frecuentes de compilación y errores lógicos.
