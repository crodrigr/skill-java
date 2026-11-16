# 🟡 Intermedio 01 — Aplicar Factory Method

## 🧩 Problema

Tienes la misma clase de la Biblioteca Universitaria del ejercicio Básico 01:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public interface MaterialBibliografico {
    String describir();
}
```

```java
package com.biblioteca;

public class LibroFisico implements MaterialBibliografico {
    public String describir() {
        return "Libro fisico, disponible en estanteria";
    }
}
```

```java
package com.biblioteca;

public class Biblioteca {
    public MaterialBibliografico prestarParaSala(String tipo) {
        if (tipo.equals("FISICO")) {
            return new LibroFisico();
        }
        throw new IllegalArgumentException("Tipo de material desconocido: " + tipo);
    }

    public MaterialBibliografico prestarADomicilio(String tipo) {
        if (tipo.equals("FISICO")) {
            return new LibroFisico();
        }
        throw new IllegalArgumentException("Tipo de material desconocido: " + tipo);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Biblioteca biblioteca = new Biblioteca();

        System.out.println(biblioteca.prestarParaSala("FISICO").describir());
        System.out.println(biblioteca.prestarADomicilio("FISICO").describir());
    }
}
```

Rediséñala para que respete Factory Method: declara una fábrica que decida qué clase concreta
instanciar, de forma que agregar un tipo nuevo (`EBook`) no exija modificar `Biblioteca.java`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| `prestarParaSala("FISICO")` y `prestarADomicilio("FISICO")` | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter |
| Se agrega un tipo nuevo (`EBook`) como una clase que implementa `MaterialBibliografico` | Archivo `Biblioteca.java` | Sin ninguna modificación |

## 📏 Criterios de evaluación de la solución

- Declara una clase `FabricaDeMateriales` con un método estático `crear(String tipo)`.
- `Biblioteca` delega en la fábrica en vez de invocar `new` directamente.
- La salida por consola no cambia respecto de la versión original.
- Un tipo de material nuevo se agrega con una clase nueva y una rama en la fábrica, sin modificar
  `Biblioteca.java`.

## 🚧 Restricciones

- No se usan anotaciones ni un framework de inyección de dependencias.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar Factory Method para un caso dado.
