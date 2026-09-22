# 🟢 Básico 04 — Predecir subcadenas

## 🧩 Problema

La **Biblioteca Universitaria** identifica cada préstamo con un código de formato fijo. Sin ejecutar el
programa, predice el resultado de cada línea marcada con una letra.

## 💻 Código o contexto de partida

```java
public class PrediccionSubcadenas {

    public static void main(String[] args) {
        String codigoPrestamo = "PR-N-000045";
        System.out.println("a) " + codigoPrestamo.substring(0, 2));
        System.out.println("b) " + codigoPrestamo.substring(3, 4));
        System.out.println("c) " + codigoPrestamo.substring(5));
        System.out.println("d) " + codigoPrestamo.indexOf("-"));
        System.out.println("e) " + codigoPrestamo.lastIndexOf("-"));
        String codigoCorto = "PR-C-5";
        System.out.println("f) " + codigoCorto.substring(5));
    }
}
```

| Línea | Tu predicción |
|---|---|
| a) | |
| b) | |
| c) | |
| d) | |
| e) | |
| f) | |

## 📏 Criterios de evaluación de la solución

- Las seis respuestas son correctas.
- Se explica que el segundo índice de `substring` **no se incluye**.
- Se explica la diferencia entre `indexOf` (primera aparición) y `lastIndexOf` (última).
- En f) se comprueba que `substring(5)` sobre un código más corto también funciona, porque toma "hasta
  el final" sin importar la longitud exacta.

## 🚧 Restricciones

- Resuélvelo a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-7**: extraer subcadenas con `substring` y localizar posiciones con `indexOf`.
