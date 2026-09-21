# 🟢 Básico 03 — Secuencias de un for

## 🧩 Problema

La **Biblioteca Universitaria** recorre días, libros y renovaciones con bucles `for`. Sin ejecutar el
programa, predice qué **secuencia** muestra cada uno de los dos primeros y **cuántas veces** se
ejecuta cada uno de los dos últimos (los del apartado C).

## 💻 Código o contexto de partida

```java
public class SecuenciasFor {

    public static void main(String[] args) {
        // Secuencia A: paso 2
        System.out.print("A:");
        for (int dia = 1; dia <= 9; dia += 2) {
            System.out.print(" " + dia);
        }
        System.out.println();

        // Secuencia B: cuenta regresiva
        System.out.print("B:");
        for (int libro = 5; libro >= 1; libro--) {
            System.out.print(" " + libro);
        }
        System.out.println();

        // Secuencia C: "<" frente a "<="
        int conMenor = 0;
        for (int renovacion = 1; renovacion < 4; renovacion++) {
            conMenor++;
        }
        int conMenorOIgual = 0;
        for (int renovacion = 1; renovacion <= 4; renovacion++) {
            conMenorOIgual++;
        }
        System.out.println("C: " + conMenor + " y " + conMenorOIgual);
    }
}
```

Completa la tabla:

| Secuencia | Qué muestra (o cuántas veces se ejecuta) |
|---|---|
| A | |
| B | |
| C, con `renovacion < 4` | |
| C, con `renovacion <= 4` | |

## 📏 Criterios de evaluación de la solución

- Las cuatro respuestas son correctas.
- En A se explica que `dia += 2` avanza de a dos y que el 11 ya no cumple la condición.
- En B se explica la cuenta regresiva y por qué el 0 no se muestra.
- En C se explica por qué `<` y `<=` dan una repetición de diferencia.

## 🚧 Restricciones

- Resuélvelo a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-4**: escribir y leer un `for` hacia adelante, hacia atrás y con paso distinto de 1.
- **RA-7**: predecir la salida y el número de repeticiones de un bucle.
