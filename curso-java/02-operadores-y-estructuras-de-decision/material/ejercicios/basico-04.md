# 🟢 Básico 04 — Tabla de verdad

## 🧩 Problema

La **Biblioteca Universitaria** autoriza un préstamo solo si se cumplen **las tres** condiciones
siguientes a la vez:

- el libro está disponible (`disponible`);
- el usuario tiene menos libros que el máximo (`librosPrestados < MAX_LIBROS_PRESTAMO`, con
  `MAX_LIBROS_PRESTAMO = 3`);
- el usuario no tiene días de retraso (`diasRetraso == 0`).

Es decir: `disponible && librosPrestados < MAX_LIBROS_PRESTAMO && diasRetraso == 0`.

**Parte A.** Completa la tabla con el resultado de la condición en cada caso.

**Parte B.** La biblioteca también quiere saber si el promedio de días por libro supera 10:
`librosPrestados > 0 && totalDiasPrestamo / librosPrestados > 10`, con `totalDiasPrestamo = 40`.
Indica el resultado para cada valor de `librosPrestados` y responde: **¿en qué caso la segunda
condición no llega a evaluarse y por qué eso evita un error?**

## 💻 Código o contexto de partida

**Parte A:**

| Caso | `disponible` | `librosPrestados` | `diasRetraso` | ¿Autorizado? |
|---|---|---|---|---|
| 1 | `true` | 2 | 0 | |
| 2 | `true` | 3 | 0 | |
| 3 | `false` | 1 | 0 | |
| 4 | `true` | 1 | 4 | |
| 5 | `true` | 0 | 0 | |

**Parte B:**

| Caso | `librosPrestados` | Resultado |
|---|---|---|
| A | 0 | |
| B | 2 | |
| C | 4 | |

## 📏 Criterios de evaluación de la solución

- Los cinco casos de la parte A son correctos y se explica que basta una condición falsa para
  que `&&` dé `false`.
- Los tres casos de la parte B son correctos.
- Se explica que con `librosPrestados = 0` la primera condición es falsa, la segunda no se
  evalúa y así no se divide entre cero (cortocircuito).

## 🚧 Restricciones

- Resuélvelo a mano; después puedes comprobarlo con un programa.
- No hace falta programar decisiones con `if`: trabaja solo con las condiciones.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: combinar condiciones con Y y explicar el cortocircuito.
