# 🟡 Intermedio 02 — Tabla de cuotas con for

## 🧩 Problema

La **Biblioteca Universitaria** permite pagar una multa en cuotas iguales. El valor de cada cuota es
`multaTotal / numeroCuotas`.

**Parte A.** Completa el programa de partida: reemplaza el `TODO` por un `for` que muestre **una línea
por cada cuota**, con el formato `Cuota 1: 10000`. El programa ya compila, pero todavía no muestra
ninguna cuota.

**Parte B.** Reescribe el mismo recorrido con un `while` y responde en una o dos frases: **¿cuál de
las dos estructuras conviene aquí y por qué?**

## 💻 Código o contexto de partida

```java
public class CuotasMulta {

    public static void main(String[] args) {
        // Datos de entrada
        int multaTotal = 30000;
        int numeroCuotas = 3;

        int valorCuota = multaTotal / numeroCuotas;
        // TODO: con un for, muestra una línea por cada cuota, por ejemplo "Cuota 1: 10000"
    }
}
```

## 🧪 Casos de prueba

Cambia `numeroCuotas` en los datos de entrada (con `multaTotal = 30000`) y comprueba las líneas que
muestra cada caso:

| `numeroCuotas` | Valor de cada cuota esperado |
|---|---|
| `1` | (lo calculas tú) |
| `2` | (lo calculas tú) |
| `3` | (lo calculas tú) |
| `4` | (lo calculas tú) |

## 📏 Criterios de evaluación de la solución

- La parte A muestra exactamente `numeroCuotas` líneas con el valor correcto de la cuota, incluido el
  caso de una sola cuota.
- El `for` cuenta de 1 a `numeroCuotas` con `<=` y actualiza el contador en la cabecera.
- La parte B produce la misma salida con un `while` (el contador se declara antes y se actualiza
  dentro).
- La justificación indica que el `for` conviene cuando se sabe cuántas veces se repite.

## 🚧 Restricciones

- No cambies los datos de entrada ni el formato de la salida.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: escribir un `for` con contador.
- **RA-5**: comparar `for` y `while` y justificar la elección.
- **RA-10**: comprobar la regla con una, varias y otros valores de `numeroCuotas`.
