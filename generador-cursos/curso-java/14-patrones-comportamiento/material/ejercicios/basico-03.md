# 🟢 Básico 03 — Identificar violación de Iterator

## 🧩 Problema

La Biblioteca Universitaria guarda fichas de catálogo en esta colección propia:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class ColeccionDeFichas {
    private String[] fichas;
    private int cantidad;

    public ColeccionDeFichas(int capacidad) {
        this.fichas = new String[capacidad];
        this.cantidad = 0;
    }

    public void agregar(String ficha) {
        fichas[cantidad] = ficha;
        cantidad = cantidad + 1;
    }

    public String[] getFichas() {
        return fichas;
    }

    public int getCantidad() {
        return cantidad;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ColeccionDeFichas coleccion = new ColeccionDeFichas(3);
        coleccion.agregar("Rayuela");
        coleccion.agregar("El Aleph");
        coleccion.agregar("Ficciones");

        for (int i = 0; i < coleccion.getCantidad(); i = i + 1) {
            System.out.println(coleccion.getFichas()[i]);
        }
    }
}
```

Sin escribir código, responde: si `ColeccionDeFichas` cambiara su representación interna (por ejemplo,
de un arreglo a otra estructura), ¿qué le pasaría al código de `Demo` que la recorre?

## 📏 Criterios de evaluación de la solución

- Identifica que `Demo` accede directamente a `coleccion.getFichas()[i]`, acoplado a que la colección
  use un arreglo.
- Explica que, si la representación interna cambiara, `Demo` también tendría que cambiar, porque
  depende de esa estructura.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: reconocer qué resuelve Iterator.
- **RA-9**: identificar acoplamiento a la estructura interna de una colección.
