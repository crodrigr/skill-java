# 🟢 Básico 01 — Predecir concatenaciones

## 🧩 Problema

La **Biblioteca Universitaria** arma varios textos combinando literales, `new String`, `+=` y
`.concat(...)`. Sin ejecutar el programa, predice el resultado exacto de cada línea marcada con una
letra.

## 💻 Código o contexto de partida

```java
public class PrediccionConcatenaciones {

    public static void main(String[] args) {
        // a) Cadena literal y new String con ==
        String libroA = "Cien años de soledad";
        String libroB = new String("Cien años de soledad");
        System.out.println("a) " + (libroA == libroB));

        // b) y c) orden de evaluación
        System.out.println("b) " + ("Préstamo " + 3 + 1));
        System.out.println("c) " + (3 + 1 + " préstamos"));

        // d) += sobre texto
        String resumen = "Usuarios activos: ";
        resumen += 5;
        System.out.println("d) " + resumen);

        // e) concat encadenado
        String codigo = "PR-".concat("N").concat("-000045");
        System.out.println("e) " + codigo);

        // f) concatenar un decimal
        System.out.println("f) " + ("Multa: " + 1500.0));
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

- Las seis predicciones son correctas.
- En a) se explica que `libroA` y `libroB` tienen el mismo contenido, pero son objetos distintos porque
  uno se creó con `new String(...)`.
- En b) y c) se explica el orden de evaluación de `+` según si el primer operando es texto o número.
- En f) se identifica que concatenar un `double` con texto conserva sus decimales tal como Java los
  muestra.

## 🚧 Restricciones

- Resuélvelo primero a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar la diferencia entre un literal y `new String(...)`.
- **RA-2**: concatenar con `+`, `+=` y `concat`, y predecir el orden de evaluación.
