# 🟡 Intermedio 01 — Aplicar la creación de un Stream

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 01:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroCatalogo {
    private final String titulo;

    public LibroCatalogo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        LibroCatalogo[] catalogo = {
            new LibroCatalogo("El Quijote"),
            new LibroCatalogo("Rayuela")
        };

        System.out.println("Catalogo (bucle for):");
        for (int i = 0; i < catalogo.length; i++) {
            System.out.println("- " + catalogo[i].getTitulo());
        }
    }
}
```

Rediséñalo para que muestre las **cuatro** formas de crear un `Stream` sobre este mismo catálogo:
`Stream.of()`, a partir del arreglo, `Stream.<LibroCatalogo>builder()`, y a partir de una lista.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Catálogo con "El Quijote" y "Rayuela" | Salida de las cuatro formas de creación | Las cuatro imprimen exactamente los mismos dos títulos, en el mismo orden |

## 📏 Criterios de evaluación de la solución

- Implementa las cuatro formas de creación sobre el mismo catálogo.
- Cada stream se recorre con `forEach` y una expresión lambda.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-2**: crear un `Stream` con `Stream.of()` para un caso dado.
- **RA-3**: crear un `Stream` a partir de un arreglo para un caso dado.
- **RA-4**: crear un `Stream` con `Stream.<T>builder()` para un caso dado.
- **RA-5**: crear un `Stream` a partir de una colección para un caso dado.
