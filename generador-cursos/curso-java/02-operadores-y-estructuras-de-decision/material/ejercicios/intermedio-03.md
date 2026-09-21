# 🟡 Intermedio 03 — De if-else a ternario

## 🧩 Problema

La **Biblioteca Universitaria** muestra el estado de un préstamo con el programa de abajo.

**Parte A.** Reescribe la decisión con un **operador ternario**, de modo que `estado` se declare
y reciba su valor en **una sola línea**. El programa debe mostrar lo mismo que antes.

**Parte B.** La biblioteca quiere ahora clasificar el retraso en tres estados: `Al día` (0 días),
`Con multa` (de 1 a 30 días) y `Suspendido` (más de 30 días). ¿Usarías un operador ternario para
esta regla? Responde con una o dos frases y di qué estructura elegirías.

## 💻 Código o contexto de partida

```java
public class EstadoPrestamo {

    public static void main(String[] args) {
        // Datos de entrada
        int diasRetraso = 5;

        String estado;
        if (diasRetraso > 0) {
            estado = "Con retraso";
        } else {
            estado = "Al día";
        }
        System.out.println("Estado: " + estado);
    }
}
```

## 🧪 Casos de prueba

Comprueba la parte A con estos valores de `diasRetraso`:

| `diasRetraso` | Estado esperado |
|---|---|
| `0` | (lo calculas tú) |
| `1` | (lo calculas tú) |
| `5` | (lo calculas tú) |

## 📏 Criterios de evaluación de la solución

- La parte A produce el mismo resultado que el `if - else` original para los tres casos.
- La declaración y la asignación de `estado` están en una sola línea, con la forma
  `condición ? valor1 : valor2`.
- La parte B concluye que **no** conviene un ternario (hay más de dos resultados) y elige
  `if - else if - else` justificándolo.

## 🚧 Restricciones

- Usa un solo operador ternario en la parte A; no los encadenes.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-8**: escribir un operador ternario y comprobar que equivale al `if - else`.
- **RA-9**: decidir cuándo conviene un ternario y cuándo otra estructura.
