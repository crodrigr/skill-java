# 🔑 Clave del Quiz 01 — Estructuras repetitivas

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/03-estructuras-repetitivas.md`).

| N.º | Tipo | Respuesta correcta / síntesis | RA |
|---|---|---|---|
| 1 | Selección | B — el `while` evalúa la condición antes de cada vuelta. | RA-1, RA-2 |
| 2 | Selección múltiple | A y B — 4 vueltas y `atendidos = 8`; `turno` termina en `5` y la condición se evalúa 5 veces. | RA-2, RA-7 |
| 3 | Selección | B — un `do-while` ejecuta el bloque una vez antes de evaluar la condición. | RA-3 |
| 4 | Selección | B — la inicialización se ejecuta una vez; luego condición, bloque y actualización en cada vuelta. | RA-4 |
| 5 | Selección múltiple | A, B y C — muestra `5 3 1` (3 vueltas); con `libro > 1` solo `5 3`; la variable del `for` no existe después. | RA-4, RA-7 |
| 6 | Abierta | El acumulador se declara antes para conservar su valor; dentro se reinicia en cada vuelta y fuera no existe. | RA-6, RA-10 |
| 7 | Selección | A — el `break` termina el bucle antes del `println` cuando `dia` vale 4: muestra `1 2 3`. | RA-8 |
| 8 | Selección múltiple | A y C — el `break` de un `switch` solo sale del `switch`; `break` fuera de un bucle no compila. | RA-8, RA-11 |
| 9 | Selección | B — `continue` omite el resto de la vuelta y el bucle sigue con el día 4. | RA-9 |
| 10 | Selección múltiple | A, B y C — en un `while`, un `continue` antes de la actualización puede no terminar; en un `for` la actualización se ejecuta. | RA-9, RA-11 |
| 11 | Selección | A — empezar en 0 con `<=` da una repetición de más; un bucle que no termina casi siempre no actualiza la variable de la condición. | RA-11 |
| 12 | Abierta | (1) `for`; (2) `for` con `break`; (3) `for` con `continue`. | RA-5, RA-9 |
