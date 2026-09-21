# 🔑 Clave del Quiz 01 — Operadores y estructuras de decisión

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/02-operadores-y-estructuras-de-decision.md`).

El archivo `quizzes/quiz-01.md` (formato entrevista técnica) ya incluye la respuesta de cada
ítem oculta en un bloque `<details>` colapsado, pensada como autoevaluación inmediata para el
estudiante. Esta tabla es un resumen de referencia rápida para el docente (por ejemplo, para
corregir en grupo o verificar respuestas sin abrir cada bloque uno por uno).

| N.º | Tipo | Respuesta correcta / síntesis | RA |
|---|---|---|---|
| 1 | Selección | A — `2 + 3 - 1 = 4`: cada asignación compuesta actualiza el valor vigente. | RA-1 |
| 2 | Selección múltiple | A, B y C — `17 / 5 = 3`, `17 % 5 = 2`, `(double) a / b = 3.4`; D da `3.0`. | RA-2 |
| 3 | Selección | B — la multiplicación va primero: `2 + 12 - 1 = 13`. | RA-3, RA-2 |
| 4 | Selección | C — `diasRetraso = 0` asigna y su resultado es un `int`: error de compilación. | RA-4, RA-11 |
| 5 | Abierta | `diasRetraso >= 30`; probar el valor límite `30`. | RA-4 |
| 6 | Selección múltiple | A y D dan `false`; B y C dan `true`. | RA-5 |
| 7 | Selección | B — cortocircuito: la división no se evalúa, `r` vale `false`. | RA-5 |
| 8 | Selección | B — `30 > 30` es falso y `30 > 0` es verdadero: `Con multa`. | RA-6 |
| 9 | Abierta | Probar los valores límite (último de un tramo y primero del siguiente). | RA-10, RA-6 |
| 10 | Selección múltiple | A, B y D; `default` es opcional en un `switch` como sentencia. | RA-7, RA-11 |
| 11 | Selección | A — la condición es verdadera: `consulta`. | RA-8 |
| 12 | Abierta | (1) ternario; (2) `if - else if - else`; (3) `switch`. | RA-9, RA-11 |
