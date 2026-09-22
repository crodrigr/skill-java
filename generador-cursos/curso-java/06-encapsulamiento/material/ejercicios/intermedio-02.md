# 🟡 Intermedio 02 — Agregar validación a un set

## 🧩 Problema

La **Biblioteca Universitaria** tiene la clase `Usuario`, con un `setPrestamosActivos` que todavía no
valida. Completa el `TODO` para que rechace un valor negativo.

## 💻 Código o contexto de partida

```java
public class Usuario {
    private int prestamosActivos;

    public int getPrestamosActivos() {
        return prestamosActivos;
    }

    public void setPrestamosActivos(int prestamosActivos) {
        // TODO: 1. valida que prestamosActivos no sea negativo antes de asignar
        this.prestamosActivos = prestamosActivos;
    }

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setPrestamosActivos(2);
        System.out.println("Préstamos activos: " + usuario.getPrestamosActivos());
    }
}
```

## 🧪 Casos de prueba

```text
Préstamos activos: 2
Préstamos activos inválido (-1): se conserva el valor actual (2)
Préstamos activos tras valor inválido: 2
Préstamos activos tras valor límite: 0
```

## 📏 Criterios de evaluación de la solución

- `setPrestamosActivos` no asigna un valor negativo: conserva el valor anterior y avisa por consola.
- `0` (el límite) sí se acepta.
- El programa no se detiene en ningún caso.

## 🚧 Restricciones

- No cambies el nombre del atributo ni del método.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-9**: declarar un método `set` que valide antes de asignar.
- **RA-10**: explicar por qué un `set` validado es más seguro que un atributo público.
