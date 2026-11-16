# 🟡 Intermedio 04 — Aplicar Prototype (clonado profundo)

## 🧩 Problema

Tienes la misma clase de la Biblioteca Universitaria del ejercicio Básico 04:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

import java.util.List;

public class ListaDeLectura {
    private String curso;
    private List<String> titulos;

    public ListaDeLectura(String curso, List<String> titulos) {
        this.curso = curso;
        this.titulos = titulos;
    }

    public ListaDeLectura clonar(String nuevoCurso) {
        // Clonado superficial: reutiliza la misma lista de titulos, no crea una copia
        return new ListaDeLectura(nuevoCurso, this.titulos);
    }

    public void agregarTitulo(String titulo) {
        titulos.add(titulo);
    }

    public String resumen() {
        return curso + ": " + titulos;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        List<String> titulos = new ArrayList<>();
        titulos.add("Cien anios de soledad");
        titulos.add("Rayuela");
        ListaDeLectura original = new ListaDeLectura("Literatura I", titulos);

        ListaDeLectura copia = original.clonar("Literatura II");
        copia.agregarTitulo("Ficciones");

        System.out.println("original: " + original.resumen());
        System.out.println("copia: " + copia.resumen());
    }
}
```

Corrígela para que `clonar()` haga un clonado profundo: la lista de títulos de la copia debe ser
independiente de la del original.

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| Clonar la lista y agregar un título nuevo solo a la copia | `original.resumen()` | El original no incluye el título nuevo |
| Mismo experimento | `copia.resumen()` | La copia sí incluye el título nuevo |

## 📏 Criterios de evaluación de la solución

- `clonar()` crea una lista nueva (`new ArrayList<>(this.titulos)`) en vez de reutilizar la referencia
  original.
- Modificar la lista de títulos de la copia no afecta a la lista del original.

## 🚧 Restricciones

- No se usa `Set` ni `Map` (temas fuera de alcance de este módulo).

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-13**: implementar un clonado profundo real.
