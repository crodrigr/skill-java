# 🟢 Básico 05 — El error de instanciar

## 🧩 Problema

**Biblioteca Universitaria** declara `RecursoDigital` como una clase abstracta.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public abstract class RecursoDigital {
    protected String titulo;

    public RecursoDigital(String titulo) {
        this.titulo = titulo;
    }

    public abstract String descargar();
}
```

```java
package com.biblioteca;

public class EBook extends RecursoDigital {
    public EBook(String titulo) {
        super(titulo);
    }

    @Override
    public String descargar() {
        return titulo + " descargado en formato EPUB";
    }
}
```

```java
RecursoDigital recurso = new RecursoDigital("Cien años de soledad");
```

**Pregunta**: ¿compila esta línea? Si no, ¿qué mensaje real produce?

## 🧪 Casos de prueba

| Expresión | ¿Compila? |
|---|---|
| `new EBook("Cien años de soledad")` | ? |
| `new RecursoDigital("Cien años de soledad")` | ? |

## 📏 Criterios de evaluación de la solución

- Identifica que `new RecursoDigital(...)` no compila porque `RecursoDigital` es `abstract`.
- Cita el mensaje real: `RecursoDigital is abstract; cannot be instantiated`.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de identificación del error.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-14**: identificar el error de instanciar una clase abstracta o interfaz.
