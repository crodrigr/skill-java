# 🟢 Básico 02 — Identificar violación de Command

## 🧩 Problema

La Biblioteca Universitaria ocupa y libera puestos de lectura con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class PuestoDeLectura {
    private String numero;
    private String usuarioAsignado;

    public PuestoDeLectura(String numero) {
        this.numero = numero;
    }

    public void ocupar(String usuario) {
        this.usuarioAsignado = usuario;
        System.out.println("Puesto " + numero + " ocupado por " + usuario);
    }

    public void liberar() {
        System.out.println("Puesto " + numero + " liberado (estaba " + usuarioAsignado + ")");
        this.usuarioAsignado = null;
    }

    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        PuestoDeLectura puesto = new PuestoDeLectura("12");
        puesto.ocupar("Ana Torres");
        System.out.println("ocupado por=" + puesto.getUsuarioAsignado());
    }
}
```

Sin escribir código, responde: si el bibliotecario ocupa un puesto por error y quiere revertir esa
acción exacta, ¿qué forma tiene de hacerlo con el código de arriba?

## 📏 Criterios de evaluación de la solución

- Identifica que no existe ninguna forma uniforme de deshacer la última acción: el bibliotecario solo
  podría llamar manualmente a `liberar()`, y solo si recuerda cuál fue la acción anterior.
- Explica que eso es una violación de Command: no hay ningún objeto que represente la acción ejecutada,
  así que no se puede registrar ni deshacer de forma genérica.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: reconocer qué resuelve Command.
- **RA-6**: identificar una llamada directa al receptor sin poder deshacer en un fragmento dado.
