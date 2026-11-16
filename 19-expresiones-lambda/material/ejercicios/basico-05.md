# 🟢 Básico 05 — Identificar un caso que necesita una interfaz funcional propia

## 🧩 Problema

La Biblioteca Universitaria describe un préstamo (título y socio) con este código:

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

Sin escribir código, responde: ¿por qué ninguna de las cuatro interfaces estándar (`Consumer`,
`Supplier`, `Function`, `Predicate`) puede reemplazar a `DescriptorPrestamo`?

## 📏 Criterios de evaluación de la solución

- Identifica que `DescriptorPrestamo` recibe **dos** parámetros de tipos distintos, algo que ninguna de
  las cuatro interfaces estándar admite.
- Explica que este caso necesita una interfaz funcional propia, anotada `@FunctionalInterface`.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-6**: diseñar interfaces funcionales propias con `@FunctionalInterface`.
