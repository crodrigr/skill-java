# 🟢 Básico 05 — Identificar violación de Memento

## 🧩 Problema

La Biblioteca Universitaria edita fichas de catálogo con esta clase:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class BorradorDeFicha {
    private String descripcion;

    public void escribir(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        BorradorDeFicha borrador = new BorradorDeFicha();
        borrador.escribir("Novela de realismo magico");
        System.out.println(borrador.getDescripcion());

        borrador.escribir("Novela de ciencia ficcion");
        System.out.println(borrador.getDescripcion());
    }
}
```

Sin escribir código, responde: si se escribe una descripción por error y se quiere volver a la
descripción anterior, ¿qué forma tiene de hacerlo con el código de arriba?

## 📏 Criterios de evaluación de la solución

- Identifica que `escribir()` sobrescribe la descripción sin dejar ningún registro anterior.
- Explica que no existe ninguna forma de volver a la descripción anterior una vez sobrescrita.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-14**: reconocer qué resuelve Memento.
- **RA-15**: reconocer un objeto editable sin ninguna forma de deshacer cambios.
