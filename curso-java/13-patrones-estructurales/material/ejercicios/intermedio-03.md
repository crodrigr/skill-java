# 🟡 Intermedio 03 — Aplicar Composite

## 🧩 Problema

Tienes las mismas clases de la Biblioteca Universitaria del ejercicio Básico 03:

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

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        Estanteria seccionA = new Estanteria();
        seccionA.agregar(new Libro("Cien anios de soledad"));
        seccionA.agregar(new Libro("Rayuela"));

        Estanteria seccionB = new Estanteria();
        seccionB.agregar(new Libro("El Aleph"));

        Estanteria salaGeneral = new Estanteria();
        salaGeneral.agregar(seccionA);
        salaGeneral.agregar(seccionB);
        salaGeneral.agregar(new Libro("Ficciones"));

        System.out.println("libros en sala general=" + salaGeneral.contarLibros());
    }
}
```

Rediséñalo para que respete Composite: declara una interfaz común para `Libro` y `Estanteria`, de forma
que `contarLibros()` no necesite ningún `instanceof`, sin importar cuántos niveles de anidamiento tenga
la estructura.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| La misma estructura anidada de la versión original (dos secciones dentro de una sala general, más un libro suelto) | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter (`libros en sala general=4`) |
| El código de `Estanteria.contarLibros()` después de refactorizar | Presencia de `instanceof` en el método | Ninguna |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `ElementoDeCatalogo`) con el método `contarLibros()`, implementada
  por `Libro` y por `Estanteria`.
- La lista de contenido de `Estanteria` está tipada con la interfaz común, no con `Object` ni con un tipo
  más general.
- `contarLibros()` no usa ningún `instanceof`.
- El resultado (`4`) es idéntico al de la versión original.

## 🚧 Restricciones

- No se usan `Set` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-10**: implementar Composite para un caso dado.
