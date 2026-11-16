# 🟢 Básico 01 — Predecir expresiones aritméticas

## 🧩 Problema

La recepción de **MediSalud** calcula datos de cada paciente con expresiones. Sin ejecutar el
programa, **predice el valor** de cada una de las seis expresiones de la tabla, con los datos
de abajo. Recuerda la división entre enteros, el residuo y el orden de evaluación.

## 💻 Código o contexto de partida

Datos de entrada:

```java
int edadPaciente = 34;
int cantidadConsultas = 3;
int diasIncapacidad = 10;
```

| Expresión | Tu resultado |
|---|---|
| a) `cantidadConsultas * 2 + 1` | |
| b) `2 + 3 * cantidadConsultas` | |
| c) `(2 + 3) * cantidadConsultas` | |
| d) `edadPaciente / 10` | |
| e) `edadPaciente % 10` | |
| f) `diasIncapacidad / 4 * 2.0` | |

Cuando termines, pega las expresiones en un programa dentro de `println` y compara.

## 📏 Criterios de evaluación de la solución

- Las seis predicciones son correctas.
- En b) y c) se nota que la multiplicación se evalúa antes que la suma y que los paréntesis
  cambian el orden.
- En d) y e) se distingue el cociente entero del residuo.
- En f) se explica que `diasIncapacidad / 4` es una división entera y por eso el resultado
  final tiene un `.0` pero no decimales significativos.

## 🚧 Restricciones

- Predice primero, ejecuta después: el objetivo es entender el orden de evaluación.
- No se usan condiciones ni decisiones en este ejercicio.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: calcular con operadores aritméticos, incluida la división entera y el residuo.
- **RA-3**: determinar el orden de evaluación de una expresión.
