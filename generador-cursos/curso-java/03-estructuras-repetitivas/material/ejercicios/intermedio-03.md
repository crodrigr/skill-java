# 🟡 Intermedio 03 — Recorrido con continue y break

## 🧩 Problema

En la **Biblioteca Universitaria**, el plazo de un préstamo (`diasPrestamo`) se cuenta en **días
hábiles**: los domingos (los días que son múltiplo de 7) **no cuentan**. Además, nunca se cuentan más
de `MAX_DIAS_HABILES` días hábiles: al llegar a ese máximo, el recorrido se detiene.

Completa el programa de partida: reemplaza el `TODO` por un `for` que recorra los días de 1 a
`diasPrestamo`, **omita los domingos con `continue`** y **se detenga con `break`** al llegar al máximo.
El programa ya compila, pero todavía cuenta cero días.

## 💻 Código o contexto de partida

```java
public class DiasHabiles {

    public static void main(String[] args) {
        // Datos de entrada
        final int MAX_DIAS_HABILES = 10;
        int diasPrestamo = 15;

        int habiles = 0;
        // TODO: recorre los días de 1 a diasPrestamo con un for; omite los domingos (múltiplos de 7) con continue
        //       y detén el recorrido con break cuando habiles llegue a MAX_DIAS_HABILES

        System.out.println("Días hábiles contados: " + habiles);
    }
}
```

## 🧪 Casos de prueba

Cambia `diasPrestamo` en los datos de entrada y comprueba los días hábiles contados. Incluye los
valores límite (el primer domingo y el máximo):

| `diasPrestamo` | Días hábiles esperados |
|---|---|
| `6` | (los calculas tú) |
| `7` | (los calculas tú) |
| `14` | (los calculas tú) |
| `15` | (los calculas tú) |

## 📏 Criterios de evaluación de la solución

- Los cuatro casos dan el resultado correcto, incluido el que llega al máximo.
- Los domingos se omiten con `continue` (sin contarlos) y el máximo se controla con `break`.
- El contador `habiles` se declara antes del bucle y se actualiza dentro.
- El programa compila y termina.

## 🚧 Restricciones

- Usa un `for` con `continue` y `break`; no uses un `while`.
- No cambies los datos de entrada ni el formato de la salida.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-8**: terminar un bucle al alcanzar un tope con `break`.
- **RA-9**: omitir vueltas con `continue`.
- **RA-10**: comprobar la regla con casos de prueba y valores límite.
