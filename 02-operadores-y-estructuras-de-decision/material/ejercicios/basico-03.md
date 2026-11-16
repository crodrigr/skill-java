# 🟢 Básico 03 — ¿Verdadero o falso?

## 🧩 Problema

En **MediSalud**, el sistema compara datos de un paciente para decidir trámites. Con los datos
de abajo, indica si cada comparación da `true` o `false`. Dos de ellas están justo en el valor
límite: fíjate bien en la diferencia entre `>` y `>=`.

## 💻 Código o contexto de partida

Datos de entrada:

```java
int edadPaciente = 18;
double pesoKg = 62.5;
int cantidadConsultas = 3;
```

| Comparación | ¿`true` o `false`? |
|---|---|
| a) `edadPaciente >= 18` | |
| b) `edadPaciente > 18` | |
| c) `cantidadConsultas != 3` | |
| d) `pesoKg < 60.0` | |
| e) `pesoKg <= 62.5` | |
| f) `edadPaciente == cantidadConsultas * 6` | |
| g) `cantidadConsultas + 1 > edadPaciente` | |

## 📏 Criterios de evaluación de la solución

- Las siete respuestas son correctas.
- En a) y b) se explica por qué el mismo valor límite da resultados distintos.
- En f) se evalúa primero la multiplicación y después la comparación.
- Se reconoce que toda comparación produce un valor `boolean`.

## 🚧 Restricciones

- Resuélvelo sin ejecutar el programa; después puedes comprobarlo imprimiendo cada expresión.
- Ninguna comparación usa `=`: todas comparan.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-4**: comparar valores con los operadores de igualdad y relacionales y reconocer que el
  resultado es verdadero o falso.
