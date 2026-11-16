# 🟡 Intermedio 01 — Clasificar el índice de masa corporal

## 🧩 Problema

En **MediSalud**, el consultorio calcula el índice de masa corporal (IMC) de cada paciente y lo
clasifica. El IMC se calcula así: `imc = pesoKg / (estaturaM * estaturaM)`. La clasificación es:

| IMC | Clasificación |
|---|---|
| menor que 18.5 | Bajo peso |
| desde 18.5 y menor que 25 | Normal |
| desde 25 y menor que 30 | Sobrepeso |
| 30 o más | Obesidad |

Completa el programa de partida: reemplaza el `TODO` por un `if - else if - else` que asigne la
clasificación correcta. El programa ya compila, pero todavía no clasifica.

## 💻 Código o contexto de partida

```java
public class ClasificacionImc {

    public static void main(String[] args) {
        // Datos de entrada
        double pesoKg = 100.0;
        double estaturaM = 2.0;

        double imc = pesoKg / (estaturaM * estaturaM);
        String clasificacion = "";
        // TODO: asigna a clasificacion "Bajo peso", "Normal", "Sobrepeso" u "Obesidad" según el imc

        System.out.printf("IMC: %.1f%n", imc);
        System.out.println("Clasificación: " + clasificacion);
    }
}
```

## 🧪 Casos de prueba

Cambia `pesoKg` en los datos de entrada, con `estaturaM = 2.0`, y comprueba la clasificación de
cada caso. Fíjate en los valores límite: `74.0`, `100.0` y `120.0` dan un IMC de `18.5`, `25.0`
y `30.0` exactos.

| `pesoKg` | `estaturaM` | Clasificación esperada |
|---|---|---|
| `73.0` | `2.0` | (la calculas tú) |
| `74.0` | `2.0` | (la calculas tú) |
| `99.0` | `2.0` | (la calculas tú) |
| `100.0` | `2.0` | (la calculas tú) |
| `119.0` | `2.0` | (la calculas tú) |
| `120.0` | `2.0` | (la calculas tú) |

## 📏 Criterios de evaluación de la solución

- La clasificación es correcta en los seis casos, incluidos los tres valores límite.
- Se usa un `if - else if - else` con las condiciones en el orden correcto y sin condiciones
  redundantes.
- El programa compila y muestra el IMC y la clasificación.
- El código conserva los datos de entrada al inicio de `main`.

## 🚧 Restricciones

- Usa solo `if - else if - else` (todavía no un `switch`: la regla trabaja con rangos).
- Los identificadores siguen las convenciones del curso.
- El IMC se muestra con `%.1f`; el separador decimal puede verse con coma o con punto según la
  configuración de tu equipo.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-6**: escribir un `if - else if - else` para una regla con tramos.
- **RA-10**: comprobar la regla con casos de prueba y valores límite.
