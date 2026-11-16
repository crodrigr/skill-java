# 🟢 Básico 03 — ¿Cómo comparo?

## 🧩 Problema

La **Biblioteca Universitaria** compara títulos y autores de distintas formas. Sin ejecutar el
programa, predice el resultado de cada línea marcada con una letra e indica qué método usarías en
cada caso.

## 💻 Código o contexto de partida

```java
public class ComoComparo {

    public static void main(String[] args) {
        // a) Dos títulos iguales, mismo objeto
        String tituloA = "Cien años de soledad";
        String tituloB = "Cien años de soledad";
        System.out.println("a) " + tituloA.equals(tituloB));

        // b) Mismo título, distintas mayúsculas
        System.out.println("b) " + tituloA.equalsIgnoreCase("CIEN AÑOS DE SOLEDAD"));

        // c) Ordenar dos autores
        System.out.println("c) " + "Marquez".compareTo("Borges"));

        // d) Buscar dentro de un título
        System.out.println("d) " + tituloA.contains("años"));

        // e) Empieza con
        System.out.println("e) " + tituloA.startsWith("Cien"));

        // f) Comparar sin normalizar antes
        String tituloConEspacios = " Cien años de soledad";
        System.out.println("f) " + tituloA.equals(tituloConEspacios));
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
- En c) se explica que solo el signo del resultado importa, no el valor exacto.
- En f) se explica por qué agregar un espacio hace que `equals` deje de coincidir, y qué método o
  transformación lo arreglaría (`trim` antes de comparar).

## 🚧 Restricciones

- Resuélvelo primero a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: elegir entre `equals`, `equalsIgnoreCase`, `compareTo`, `contains`, `startsWith` y
  `endsWith` según lo que se necesite comparar.
