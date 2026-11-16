# 🟢 Básico 01 — Prueba de escritorio de un while

## 🧩 Problema

La **Biblioteca Universitaria** cobra una multa por cada día de retraso de un libro. Sigue el
programa de abajo **a mano**, sin ejecutarlo, y completa la **tabla de valores**: qué valen `dia` y
`multa` al terminar cada vuelta y si la condición del `while` sigue cumpliéndose. Después responde:
**¿cuántas veces se ejecuta el bloque y cuánto vale `multa` al final?**

## 💻 Código o contexto de partida

```java
public class PruebaEscritorio {

    public static void main(String[] args) {
        // Datos de entrada
        final double MULTA_POR_DIA = 1500.0;
        int diasRetraso = 4;

        int dia = 1;
        double multa = 0.0;
        while (dia <= diasRetraso) {
            multa += MULTA_POR_DIA;
            dia++;
        }
        System.out.printf("Multa: %.0f%n", multa);
    }
}
```

Completa la tabla (la primera fila ya está resuelta; la última fila debe mostrar la comprobación que
termina el bucle):

| vuelta | `dia` | ¿`dia <= diasRetraso`? | `multa` |
|---|---|---|---|
| 1 | 1 | `true` | 1500 |
| 2 | | | |
| 3 | | | |
| 4 | | | |
| — | | | |

## 📏 Criterios de evaluación de la solución

- La tabla tiene las filas correctas y la última muestra la condición en falso.
- Se indica el número correcto de repeticiones y el valor final de `multa`.
- Se explica que la condición se evalúa **antes** de cada vuelta.
- Se explica qué pasaría con `diasRetraso = 0`.

## 🚧 Restricciones

- Resuélvelo primero a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar qué es una iteración y cuántas hace un bucle.
- **RA-2**: seguir la ejecución de un `while`.
- **RA-7**: completar una tabla de valores y predecir la salida y las repeticiones.
