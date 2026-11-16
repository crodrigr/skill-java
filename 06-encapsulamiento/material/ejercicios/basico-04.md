# 🟢 Básico 04 — get o is

## 🧩 Problema

La **Biblioteca Universitaria** tiene un atributo `private boolean disponible`. ¿Cómo se llama
correctamente su método `get`, según la convención de Java: `getDisponible()` o `isDisponible()`?
Justifica tu respuesta en una frase.

## 💻 Código o contexto de partida

```java
public class Libro {
    private boolean disponible;

    public Libro(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }
}
```

```java
public class GetOIs {
    public static void main(String[] args) {
        Libro libro = new Libro(true);
        System.out.println("Disponible: " + libro.isDisponible());
    }
}
```

## 📏 Criterios de evaluación de la solución

- Elige `isDisponible()`, no `getDisponible()`.
- Justifica que la convención de Java usa `isNombre()` para el `get` de un atributo `boolean`.

## 🚧 Restricciones

- No modifiques el código: solo elige la forma correcta y justifícala.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-11**: aplicar la convención `isNombre()` para un atributo `boolean`.
