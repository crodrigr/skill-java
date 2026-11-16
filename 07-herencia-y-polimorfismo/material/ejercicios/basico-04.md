# 🟢 Básico 04 — ¿Qué versión se ejecuta?

## 🧩 Problema

**Biblioteca Universitaria** calcula los días de préstamo según el tipo de material.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class MaterialBibliografico {
    protected String titulo;

    public MaterialBibliografico(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int calcularDiasPrestamo() {
        return 10;
    }
}
```

```java
package com.biblioteca;

public class Libro extends MaterialBibliografico {
    private int paginas;

    public Libro(String titulo, int paginas) {
        super(titulo);
        this.paginas = paginas;
    }

    public int getPaginas() {
        return paginas;
    }

    @Override
    public int calcularDiasPrestamo() {
        return 14;
    }
}
```

```java
package com.biblioteca;

public class Revista extends MaterialBibliografico {
    private int numeroEdicion;

    public Revista(String titulo, int numeroEdicion) {
        super(titulo);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    @Override
    public int calcularDiasPrestamo() {
        return 7;
    }
}
```

```java
MaterialBibliografico m1 = new Libro("Cien años de soledad", 471);
MaterialBibliografico m2 = new Revista("National Geographic", 128);

System.out.println(m1.calcularDiasPrestamo());
System.out.println(m2.calcularDiasPrestamo());
```

**Pregunta**: sin ejecutar el programa, predice qué imprime cada línea.

## 🧪 Casos de prueba

| Llamada | Predicción |
|---|---|
| `m1.calcularDiasPrestamo()` | ? |
| `m2.calcularDiasPrestamo()` | ? |

## 📏 Criterios de evaluación de la solución

- Predice `14` para `m1` (tipo real `Libro`) y `7` para `m2` (tipo real `Revista`).
- Explica que la decisión se toma según el tipo **real** del objeto, no el tipo declarado
  (`MaterialBibliografico`).

## 🚧 Restricciones

- No ejecutes el código antes de predecir: primero razona con el enlace dinámico.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-7**: explicar qué es el polimorfismo (enlace dinámico).
