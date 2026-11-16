# 🟡 Intermedio 01 — Aplicar Consumer

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 01:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class LibroBiblioteca {
    private final String titulo;

    public LibroBiblioteca(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
```

```java
package com.biblioteca;

public interface AccionLibro {
    void ejecutar(LibroBiblioteca libro);
}
```

```java
package com.biblioteca;

public class RegistradorDevolucion implements AccionLibro {
    @Override
    public void ejecutar(LibroBiblioteca libro) {
        System.out.println("Devuelto: " + libro.getTitulo());
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<LibroBiblioteca> libros = new ArrayList<>();
        libros.add(new LibroBiblioteca("El Quijote"));
        libros.add(new LibroBiblioteca("Rayuela"));

        AccionLibro accion = new RegistradorDevolucion();
        for (LibroBiblioteca libro : libros) {
            accion.ejecutar(libro);
        }
    }
}
```

Rediséñalo para que use `Consumer<LibroBiblioteca>` con una expresión lambda, en vez de la interfaz
`AccionLibro` y la clase `RegistradorDevolucion`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Lista con "El Quijote" y "Rayuela" | Salida por consola | `Devuelto: El Quijote` y `Devuelto: Rayuela`, en ese orden, igual que la versión original |

## 📏 Criterios de evaluación de la solución

- Usa `Consumer<LibroBiblioteca>` con una expresión lambda, sin declarar ninguna interfaz ni clase
  propia para la acción.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-2**: implementar una acción con `Consumer<T>` para un caso dado.
