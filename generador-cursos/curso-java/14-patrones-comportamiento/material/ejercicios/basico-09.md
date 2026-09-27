# 🟢 Básico 09 — Identificar violación de Template Method

## 🧩 Problema

La Biblioteca Universitaria genera fichas de catálogo con estas clases:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class FichaDeLibro {
    public String generar(String titulo) {
        StringBuilder ficha = new StringBuilder();
        ficha.append("Catalogando ").append(titulo).append("\n");
        String datoPropio = "Genero: Novela";
        ficha.append("=== Ficha de Libro ===\n");
        ficha.append("Titulo: ").append(titulo).append("\n");
        ficha.append(datoPropio);
        return ficha.toString();
    }
}
```

```java
package com.biblioteca;

public class FichaDeRevista {
    public String generar(String titulo) {
        StringBuilder ficha = new StringBuilder();
        ficha.append("Catalogando ").append(titulo).append("\n");
        String datoPropio = "Periodicidad: Mensual";
        // Copiado y pegado de FichaDeLibro: aca se olvido el encabezado "=== Ficha de X ===".
        ficha.append("Titulo: ").append(titulo).append("\n");
        ficha.append(datoPropio);
        return ficha.toString();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        System.out.println(new FichaDeLibro().generar("Rayuela"));
        System.out.println("---");
        System.out.println(new FichaDeRevista().generar("National Geographic"));
    }
}
```

Ejecutando el `Demo` de arriba, esta es la salida real:

```text
Catalogando Rayuela
=== Ficha de Libro ===
Titulo: Rayuela
Genero: Novela
---
Catalogando National Geographic
Titulo: National Geographic
Periodicidad: Mensual
```

Sin escribir código, responde: ¿qué diferencia real hay entre la salida de `FichaDeLibro` y la de
`FichaDeRevista`, y por qué ocurrió?

## 📏 Criterios de evaluación de la solución

- Identifica que a `FichaDeRevista` le falta la línea `=== Ficha de Revista ===`, mientras que
  `FichaDeLibro` sí la tiene.
- Explica que ocurrió porque `FichaDeRevista.generar()` fue copiada y pegada de `FichaDeLibro`, y ese
  paso del esqueleto se omitió por error — nada en el lenguaje impide que un método copiado y pegado
  omita un paso.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-26**: reconocer qué resuelve Template Method.
- **RA-27**: reconocer un esqueleto copiado y pegado con riesgo de omitir un paso.
