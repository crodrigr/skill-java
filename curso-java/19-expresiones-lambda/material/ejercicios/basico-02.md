# 🟢 Básico 02 — Identificar código que una expresión lambda simplificaría (Supplier)

## 🧩 Problema

La Biblioteca Universitaria genera un código de préstamo con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public interface GeneradorCodigo {
    String generar();
}
```

```java
package com.biblioteca;

public class GeneradorCodigoSecuencial implements GeneradorCodigo {
    private int contador = 0;

    @Override
    public String generar() {
        contador++;
        return "PRESTAMO-" + contador;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        GeneradorCodigo generador = new GeneradorCodigoSecuencial();
        System.out.println(generador.generar());
        System.out.println(generador.generar());
    }
}
```

Sin escribir código, responde: ¿qué interfaz estándar de `java.util.function` podría reemplazar a
`GeneradorCodigo` y `GeneradorCodigoSecuencial`, y por qué encaja?

## 📏 Criterios de evaluación de la solución

- Identifica que `GeneradorCodigo`/`GeneradorCodigoSecuencial` no reciben ningún parámetro y devuelven
  un `String`.
- Explica que `Supplier<String>` encaja exactamente en esa forma, sin necesitar una interfaz ni una
  clase propias.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: usar `Supplier<T>`.
