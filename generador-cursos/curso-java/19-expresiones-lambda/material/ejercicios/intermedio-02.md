# 🟡 Intermedio 02 — Aplicar Supplier

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 02:

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

Rediséñalo para que use `Supplier<String>` con una expresión lambda, en vez de la interfaz
`GeneradorCodigo` y la clase `GeneradorCodigoSecuencial`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Dos llamadas consecutivas | Salida por consola | `PRESTAMO-1` y `PRESTAMO-2`, en ese orden, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `Supplier<String>` con una expresión lambda, sin declarar ninguna interfaz ni clase propia.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-3**: implementar un valor por defecto con `Supplier<T>` para un caso dado.
