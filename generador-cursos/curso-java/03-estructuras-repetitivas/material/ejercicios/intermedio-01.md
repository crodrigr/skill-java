# 🟡 Intermedio 01 — Acumular con while

## 🧩 Problema

En la **Biblioteca Universitaria**, la multa por retraso es de `MULTA_POR_DIA` pesos **por cada día**.
Completa el programa de partida: reemplaza el `TODO` por un `while` que acumule en `multa` el valor de
la multa de todos los días de retraso. El programa ya compila, pero por ahora muestra siempre una
multa de $0.

## 💻 Código o contexto de partida

```java
public class MultaAcumulada {

    public static void main(String[] args) {
        // Datos de entrada
        final double MULTA_POR_DIA = 1500.0;
        int diasRetraso = 5;

        int dia = 1;
        double multa = 0.0;
        // TODO: con un while, acumula en multa el valor MULTA_POR_DIA por cada día de retraso

        System.out.printf("Días de retraso: %d, multa: %.0f%n", diasRetraso, multa);
    }
}
```

## 🧪 Casos de prueba

Cambia `diasRetraso` en los datos de entrada y ejecuta de nuevo. Prueba **cero, una y varias**
repeticiones:

| `diasRetraso` | Multa esperada |
|---|---|
| `0` | (la calculas tú) |
| `1` | (la calculas tú) |
| `5` | (la calculas tú) |

## 📏 Criterios de evaluación de la solución

- La multa es correcta en los tres casos, incluido el de cero repeticiones.
- El `while` usa un contador (`dia`) que se actualiza dentro del bucle y un acumulador (`multa`) que
  se declara antes.
- La condición usa `<=` de modo que se cobre exactamente `diasRetraso` veces.
- El programa compila y termina.

## 🚧 Restricciones

- Usa un `while`; todavía no un `for`.
- No cambies los datos de entrada ni el formato de la salida.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-2**: escribir un `while` con contador.
- **RA-6**: usar un contador y un acumulador dentro de un bucle.
- **RA-10**: comprobar la regla con cero, una y varias repeticiones.
