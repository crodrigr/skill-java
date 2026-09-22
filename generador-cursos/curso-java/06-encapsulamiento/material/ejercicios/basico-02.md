# 🟢 Básico 02 — ¿Compila o no?

## 🧩 Problema

En **MediSalud**, la siguiente clase (`com.medisalud`) tiene cuatro atributos, uno por cada modificador
de acceso, y se accede a ella tanto desde el mismo paquete como desde una clase de **Biblioteca
Universitaria** (`com.biblioteca`). Para cada una de las ocho combinaciones de la tabla, predice si el
acceso directo (`objeto.atributo = valor;`) compila.

## 💻 Código o contexto de partida

```java
package com.medisalud;

public class Multimodificador {
    private String atributoPrivate;
    String atributoPorDefecto;
    protected String atributoProtected;
    public String atributoPublic;
}
```

## 🧪 Casos de prueba

| Atributo | Desde el mismo paquete (`com.medisalud`) | Desde otro paquete (`com.biblioteca`) |
|---|---|---|
| `atributoPrivate` | ? | ? |
| `atributoPorDefecto` | ? | ? |
| `atributoProtected` | ? | ? |
| `atributoPublic` | ? | ? |

## 📏 Criterios de evaluación de la solución

- Predice correctamente las ocho combinaciones (ver la solución para las respuestas reales).
- Explica con sus palabras por qué `atributoPrivate` es el único que no compila ni siquiera desde el
  mismo paquete.

## 🚧 Restricciones

- No modifiques el código: solo predice el resultado de cada acceso.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: explicar la diferencia entre los cuatro modificadores de acceso.
- **RA-4**: declarar un atributo o método por defecto.
- **RA-5**: declarar un atributo o método `protected`.
- **RA-6**: declarar un atributo o método `public`.
