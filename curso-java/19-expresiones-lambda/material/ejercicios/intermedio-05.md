# 🟡 Intermedio 05 — Diseñar e implementar una interfaz funcional propia

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 05:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public interface DescriptorPrestamo {
    String describir(String titulo, String socio);
}
```

```java
package com.biblioteca;

public class DescriptorPrestamoSimple implements DescriptorPrestamo {
    @Override
    public String describir(String titulo, String socio) {
        return titulo + " prestado a " + socio;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        DescriptorPrestamo descriptor = new DescriptorPrestamoSimple();
        System.out.println(descriptor.describir("El Quijote", "Carla Nunez"));
    }
}
```

Rediséñalo para que `DescriptorPrestamo` esté anotada `@FunctionalInterface` y se implemente con una
expresión lambda, en vez de la clase `DescriptorPrestamoSimple`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| `describir("El Quijote", "Carla Nunez")` | Salida por consola | `El Quijote prestado a Carla Nunez`, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Anota `DescriptorPrestamo` con `@FunctionalInterface`.
- Implementa la interfaz con una expresión lambda de dos parámetros, sin declarar ninguna clase.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-6**: implementar una interfaz funcional propia para un caso dado.
