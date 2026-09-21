# 🔑 Clave del Quiz 01 — Introducción

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la
subsección "Soluciones" de `specs/01-introduccion.md`).

El archivo `quizzes/quiz-01.md` (formato entrevista técnica) ya incluye la respuesta de
cada ítem oculta en un bloque `<details>` colapsado, pensada como autoevaluación
inmediata para el estudiante. Esta tabla es un resumen de referencia rápida para el
docente (por ejemplo, para corregir en grupo o verificar respuestas sin abrir cada
bloque uno por uno).

| N.º | Tipo | Respuesta correcta / síntesis | RA |
|---|---|---|---|
| 1 | Selección | B — Java es de tipado estático: el compilador revisa los tipos antes de ejecutar. | RA-1, RA-2 |
| 2 | Selección | A — `javac` genera `SaludoClinica.class` con *bytecode*. | RA-3 |
| 3 | Abierta | El `.class` contiene *bytecode* común; cada sistema tiene su propia JVM que lo ejecuta. | RA-3 |
| 4 | Selección múltiple | A, B, D — JDK ⊃ JRE ⊃ JVM; compilar requiere el JDK (la C invierte la relación). | RA-4 |
| 5 | Selección | C — las herramientas de desarrollo (`javac`, etc.) vienen en el JDK. | RA-4 |
| 6 | Selección | B — hay JVM pero falta el compilador: no se instaló el JDK completo (o no está en el `PATH`). | RA-5 |
| 7 | Selección múltiple | B, C, D — el mensaje indica la posición `[Ln, Col]` y qué falta (`insert ";"`); con un error de compilación no se elige `Continue`: se corrige y se vuelve a ejecutar. | RA-6, RA-7 |
| 8 | Selección | B — el programa empieza en el método `main` de la clase principal. | RA-8 |
| 9 | Selección | B — la constante lleva `final` y la variable no (la D usa `const`, que no existe para esto en Java). | RA-9 |
| 10 | Selección múltiple | A, B, D — la C es falsa: para un `float` se escribe `59.9f`. | RA-10 |
| 11 | Abierta | ISBN `long` (no cabe en `int`, con `L`); disponible `boolean`; precio `double` (o `float`); días de préstamo `int` o `byte`. | RA-10 |
| 12 | Selección | B — `printf("Precio: %.2f%n", precioLibro)`: `%.2f` da dos decimales y `%n` salta de línea. | RA-11 |
