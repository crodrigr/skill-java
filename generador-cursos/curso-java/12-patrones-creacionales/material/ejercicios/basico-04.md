# 🟢 Básico 04 — Identificar violación de Prototype

## 🧩 Problema

La Biblioteca Universitaria arma listas de lectura por curso:

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

## ✅ Salida real del programa

```text
original: Literatura I: [Cien anios de soledad, Rayuela, Ficciones]
copia: Literatura II: [Cien anios de soledad, Rayuela, Ficciones]
```

Sin escribir código, respondé: ¿por qué el título agregado a la lista `copia` aparece también en la
lista `original`, si nadie pidió modificar el original?

## 📏 Criterios de evaluación de la solución

- Identifica que `clonar()` reutiliza la misma referencia a la lista de títulos (`this.titulos`), en vez
  de crear una copia nueva.
- Explica que por eso agregar un título a la copia también lo agrega, sin excepción ni aviso, a la lista
  del original.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-11**: reconocer qué resuelve Prototype.
- **RA-12**: reconocer cuándo clonar es mejor que construir desde cero.
