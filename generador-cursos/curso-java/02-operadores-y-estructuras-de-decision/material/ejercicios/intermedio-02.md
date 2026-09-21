# 🟡 Intermedio 02 — Menú de planes de cobertura con switch

## 🧩 Problema

En **MediSalud**, el copago de una consulta depende del plan de cobertura del paciente, que se
identifica con una letra:

| Plan | Letra | Porcentaje de copago |
|---|---|---|
| Básico | `'B'` | 30% |
| Estándar | `'E'` | 20% |
| Premium | `'P'` | 10% |
| Cualquier otra letra (sin plan) | — | 100% |

Completa el programa de partida: reemplaza el `TODO` por un `switch` que asigne el porcentaje
de copago según `planCobertura`. El programa ya compila, pero por ahora cobra siempre el 100%.

## 💻 Código o contexto de partida

```java
public class MenuPlanes {

    public static void main(String[] args) {
        // Datos de entrada
        final double VALOR_CONSULTA = 85000.0;
        char planCobertura = 'E';

        double porcentajeCopago = 1.00;
        // TODO: con un switch sobre planCobertura, asigna el porcentaje: 'B' 0.30, 'E' 0.20, 'P' 0.10 y 1.00 en otro caso

        double copago = VALOR_CONSULTA * porcentajeCopago;
        System.out.printf("Plan %s: copago de %.0f%n", planCobertura, copago);
    }
}
```

## 🧪 Casos de prueba

Cambia `planCobertura` en los datos de entrada y ejecuta de nuevo. Prueba una letra de cada plan
**y una letra que no exista**, para comprobar el caso por defecto:

| `planCobertura` | Copago esperado |
|---|---|
| `'B'` | (lo calculas tú) |
| `'E'` | (lo calculas tú) |
| `'P'` | (lo calculas tú) |
| `'X'` | (lo calculas tú) |

## 📏 Criterios de evaluación de la solución

- El copago es correcto para las cuatro letras, incluida la que no existe.
- Se usa un `switch` sobre un `char`, con las letras entre comillas simples.
- El `switch` incluye el caso por defecto.
- El programa compila y muestra el plan y el copago con el formato de partida.

## 🚧 Restricciones

- Usa un `switch` (con flecha o clásico, con `break`); no una cadena de `if`.
- Los porcentajes se guardan en una variable `double` (`0.30`, `0.20`, `0.10` y `1.00`).
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: escribir un `switch` con caso por defecto sobre un valor de tipo `char`.
- **RA-10**: comprobar la regla con un caso por opción y un valor no previsto.
