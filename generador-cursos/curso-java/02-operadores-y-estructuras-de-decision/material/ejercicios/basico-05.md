# 🟢 Básico 05 — ¿Qué rama ejecuta?

## 🧩 Problema

La **Biblioteca Universitaria** clasifica el estado de un usuario según sus días de retraso
con el programa de abajo. Para cada valor de `diasRetraso` de la tabla, indica **qué rama del
`if - else if - else` se ejecuta** y qué texto muestra el programa.

## 💻 Código o contexto de partida

```java
public class SuspensionBiblioteca {

    public static void main(String[] args) {
        // Datos de entrada
        int diasRetraso = 30;

        String estado;
        if (diasRetraso > 30) {
            estado = "Suspendido";
        } else if (diasRetraso > 0) {
            estado = "Con multa";
        } else {
            estado = "Al día";
        }
        System.out.println("Estado: " + estado);
    }
}
```

| `diasRetraso` | Rama que se ejecuta (`if`, `else if` o `else`) | Texto que se muestra |
|---|---|---|
| 0 | | |
| 1 | | |
| 30 | | |
| 31 | | |

## 📏 Criterios de evaluación de la solución

- Las cuatro filas son correctas.
- Se explica que Java evalúa las condiciones de arriba hacia abajo y ejecuta solo la primera
  que sea verdadera.
- Se identifica que `30` y `31` están en el borde entre dos tramos y que `> 30` no incluye el
  `30`.

## 🚧 Restricciones

- Resuélvelo sin ejecutar el programa; después puedes comprobarlo cambiando `diasRetraso`.
- Solo se pide leer y seguir el código: no se modifica.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-6**: seguir la ejecución de un `if - else if - else`.
- **RA-10**: comprobar una regla con tramos con un valor de cada rama y los valores límite.
