# 🟡 Intermedio 01 — Declarar una clase con atributos y un método

## 🧩 Problema

La **Biblioteca Universitaria** quiere saber si un usuario tiene una multa pendiente, según cuántos
préstamos activos tiene. Completa el programa de partida: reemplaza los `TODO` con los atributos y el
método necesarios.

## 💻 Código o contexto de partida

```java
public class UsuarioBiblioteca {

    // TODO: 1. declara los atributos nombreUsuario (String) y prestamosActivos (int)

    // TODO: 2. declara el método tieneMultaPendiente() (boolean), true si prestamosActivos > 3

    public static void main(String[] args) {
        UsuarioBiblioteca usuario = new UsuarioBiblioteca();
        // TODO: 3. asigna nombreUsuario y prestamosActivos, e imprime si tiene multa pendiente
    }
}
```

## 🧪 Casos de prueba

| Préstamos activos | ¿Tiene multa pendiente? |
|---|---|
| `0` | `false` |
| `3` | `false` |
| `4` | `true` |

## 📏 Criterios de evaluación de la solución

- Declara los atributos `nombreUsuario` (`String`) y `prestamosActivos` (`int`), públicos.
- El método `tieneMultaPendiente()` devuelve `true` solo cuando `prestamosActivos` es mayor que 3.
- Con 3 préstamos activos (el límite) no hay multa; con 4 sí.
- El programa compila y produce el resultado indicado.

## 🚧 Restricciones

- No cambies los datos de entrada.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: declarar una clase con atributos públicos.
- **RA-5**: declarar un método de instancia.
- **RA-8**: leer y escribir una clase completa.
