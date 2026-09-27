# 🟡 Intermedio 05 — Aplicar Template Method

## 🧩 Problema

Tienes las mismas clases de la Biblioteca Universitaria del ejercicio Básico 05:

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

Rediséñalas para que respeten Template Method: fija el esqueleto de cuatro pasos en una clase abstracta
con un método `final`, delegando en subclases solo los pasos que varían.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Ficha de un libro y de una revista | Presencia del encabezado (`=== Ficha de ... ===`) en ambas salidas | Presente en las dos, sin excepción |
| Se agrega una tercera variante nueva extendiendo la clase abstracta | Posibilidad de omitir el encabezado | Ninguna: el método `generar()` es `final` |

## 📏 Criterios de evaluación de la solución

- Declara una clase abstracta (por ejemplo, `GeneradorDeFicha`) con un método `generar()` marcado
  `final`, que fija el esqueleto de cuatro pasos.
- `FichaDeLibro` y `FichaDeRevista` extienden esa clase, implementando solo los dos pasos que varían.
- Ninguna subclase puede omitir ni reordenar el esqueleto.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-16**: implementar Template Method para un caso dado.
