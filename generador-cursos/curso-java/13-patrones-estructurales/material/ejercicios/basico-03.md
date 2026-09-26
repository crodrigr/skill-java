# 🟢 Básico 03 — Identificar violación de Composite

## 🧩 Problema

La Biblioteca Universitaria organiza estanterías con esta clase:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Libro {
    private String titulo;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Estanteria {
    private List<Object> contenido = new ArrayList<>();

    public void agregar(Object elemento) {
        contenido.add(elemento);
    }

    public int contarLibros() {
        int total = 0;
        for (Object elemento : contenido) {
            if (elemento instanceof Libro) {
                total += 1;
            } else if (elemento instanceof Estanteria) {
                total += ((Estanteria) elemento).contarLibros();
            }
        }
        return total;
    }
}
```

Sin escribir código, responde: si se agrega un tercer tipo de elemento a una estantería (por ejemplo,
`Revista`), ¿qué método hay que modificar y qué parte exacta de ese método?

## 📏 Criterios de evaluación de la solución

- Identifica que hay que modificar `Estanteria.contarLibros()`, agregando una rama más al condicional
  `instanceof`.
- Explica por qué eso es una violación de Composite: cualquier otro método futuro que recorra la misma
  estructura (por ejemplo, uno que liste títulos) necesitaría el mismo condicional repetido.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: reconocer qué resuelve Composite.
- **RA-9**: identificar condicionales que distinguen individuo de compuesto en un fragmento dado.
