# 🟢 Básico 02 — Do-while frente a while

## 🧩 Problema

En **MediSalud**, un mismo bloque se escribe con un `while` y con un `do-while`, con **la misma
condición**. Sin ejecutar el programa, predice cuántas veces se ejecuta cada bucle en las dos
situaciones de la tabla y cuánto vale `turno` al terminar cada uno.

## 💻 Código o contexto de partida

```java
public class DoWhileFrenteAWhile {

    public static void main(String[] args) {
        // Datos de entrada
        int cuposDia = 8;
        int turno = 9;

        int conWhile = 0;
        while (turno <= cuposDia) {
            conWhile++;
            turno++;
        }
        System.out.println("Con while: " + conWhile + " veces");

        turno = 9;
        int conDoWhile = 0;
        do {
            conDoWhile++;
            turno++;
        } while (turno <= cuposDia);
        System.out.println("Con do-while: " + conDoWhile + " veces");
    }
}
```

Ese programa usa `turno = 9`. Resuelve la tabla para **dos** valores iniciales de `turno` (con
`cuposDia = 8` en ambos casos):

| `turno` inicial | Veces con `while` | Veces con `do-while` | `turno` al terminar el `do-while` |
|---|---|---|---|
| 9 | | | |
| 7 | | | |

## 📏 Criterios de evaluación de la solución

- Las cuatro celdas de veces y las dos de `turno` son correctas.
- Se explica por qué en el primer caso los dos bucles dan resultados distintos.
- Se explica por qué en el segundo caso coinciden.
- Se indica que el `do-while` evalúa la condición **después** de la primera vuelta.

## 🚧 Restricciones

- Resuélvelo a mano; después puedes comprobarlo cambiando `turno` y ejecutando el programa.
- No modifiques el código, salvo el valor inicial de `turno` para comprobar.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: explicar que un `do-while` se ejecuta al menos una vez y en qué se diferencia del `while`.
- **RA-7**: predecir la salida y el número de repeticiones de un bucle.
