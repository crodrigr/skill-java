# 🔴 Avanzado 01 — Corregir un cierre de agenda

## 🧩 Problema

Un compañero escribió el cierre parcial de la agenda de **MediSalud**, pero el programa tiene errores.
Debe hacer tres cosas con una agenda de 7 cupos: recorrer los turnos (los múltiplos de 4 son de
control y **no se facturan**), repartir el total facturado en 3 cuotas iguales y mostrar un resumen.

Resuélvelo en **tres rondas**:

- **Ronda 1 — el editor te avisa.** Copia el código en VS Code y corrige los errores que marca el panel
  **Problems** (`Ctrl+Shift+M`) hasta que el programa compile. Anota qué dice cada mensaje.
- **Ronda 2 — el programa no termina.** Ejecútalo con **Run**. Si el programa se queda esperando sin
  mostrar nada más, **no es un fallo de tu equipo**: hay un bucle que no termina.

  > ⚠️ **Advertencia**: en esta ronda el programa puede quedarse en un bucle sin fin. Detenlo con
  > `Ctrl+C` en la terminal donde se ejecuta, o con el botón de detener de la barra de depuración.
  > El panel Problems **no** te avisará. Encuentra la causa y corrígela.
- **Ronda 3 — la salida no coincide.** Cuando el programa termine, compara su salida con la esperada
  (más abajo). Si algo no coincide, el panel Problems tampoco te lo va a decir: encuéntralo tú y
  corrígelo.

## 💻 Código o contexto de partida

```java no-compila
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
        System.out.println("Cuotas generadas hasta la " + cuota);

        int copias = 0;
        do {
            copias++;
            System.out.printf("Resumen %d: %d turnos, total %.0f%n", copias, atendidos, totalFacturado);
        } while (copias < resumenesSolicitados)
    }
}
```

## 🧪 Casos de prueba

Con los datos de entrada del código (`cuposDia = 7`), la salida que debe mostrar el programa
corregido:

```text
Cerrando la agenda...
Cuota 1: 170000
Cuota 2: 170000
Cuota 3: 170000
Resumen 1: 6 turnos, total 510000
```

Cambia después `cuposDia` a `3` (sin turnos de control) y a `8` (con dos) para comprobar el recorrido.

## 📏 Criterios de evaluación de la solución

- El programa compila, termina y, con los datos del caso, muestra exactamente la salida esperada.
- Los errores de compilación se corrigen entendiendo el mensaje, no probando al azar.
- Se identifica la causa del bucle que no termina y se explica por qué el editor no la marcó.
- Se corrige también el error de la ronda 3 y se explica por qué ocurría.
- El programa conserva su estructura original (el `while`, el `for` y el `do-while`).

## 🚧 Restricciones

- No elimines ninguno de los bucles: corrígelos.
- Usa las estructuras y sentencias del módulo; no agregues bucles anidados.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-3**: escribir un `do-while` con su punto y coma final.
- **RA-7**: seguir un bucle y comparar su salida con la esperada.
- **RA-9**: entender el efecto de un `continue` sobre la actualización del contador.
- **RA-11**: identificar y corregir errores de compilación, un bucle que no termina y una repetición de menos.
