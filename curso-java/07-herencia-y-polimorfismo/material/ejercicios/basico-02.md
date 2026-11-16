# 🟢 Básico 02 — ¿Qué hereda esta subclase?

## 🧩 Problema

**Biblioteca Universitaria** declara `Libro` como una subclase de `MaterialBibliografico`.

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
}
```

**Pregunta**: responde, sin ejecutar nada todavía:

1. ¿Qué miembros de `MaterialBibliografico` hereda `Libro`?
2. ¿`Libro` hereda el constructor `MaterialBibliografico(String)`?
3. Si `Libro` declarara su constructor sin invocar `super(...)`, ¿compilaría? Justifica.
4. `titulo` es `protected`. Si una clase de **otro paquete** (por ejemplo, `com.medisalud`) extendiera
   `MaterialBibliografico`, ¿podría acceder a `titulo` directamente, sin pasar por `getTitulo()`?
   Justifica con lo visto en el Ejemplo 03.

## 🧪 Casos de prueba

Predice si cada uno de estos fragmentos, agregado a `Libro`, compila:

| Fragmento | ¿Compila? |
|---|---|
| `public Libro(String titulo, int paginas) { super(titulo); this.paginas = paginas; }` | ? |
| `public Libro(String titulo, int paginas) { this.paginas = paginas; }` | ? |

## 📏 Criterios de evaluación de la solución

- Identifica `getTitulo()` como heredado; el constructor, como no heredado.
- Predice correctamente que el segundo fragmento no compila (falta `super(...)`).
- Explica el mensaje real que produciría ese error.

## 🚧 Restricciones

- No modifiques el código de partida: solo predice y justifica.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: explicar qué es la herencia y la relación "es un tipo de".
- **RA-3**: declarar una subclase con `extends` e identificar qué hereda y qué no.
- **RA-4**: invocar `super(...)` en el constructor de una subclase.
- **RA-6**: explicar que `protected` con herencia sí alcanza una subclase de otro paquete.
