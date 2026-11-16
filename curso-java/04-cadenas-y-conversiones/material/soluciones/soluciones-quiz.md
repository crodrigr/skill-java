# 🔑 Clave del Quiz 01 — Cadenas y conversiones

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/04-cadenas-y-conversiones.md`).

| N.º | Tipo | Respuesta correcta / síntesis | RA |
|---|---|---|---|
| 1 | Selección | C — dos literales iguales son el mismo objeto del pool; `new String(...)` crea uno aparte. | RA-1 |
| 2 | Selección múltiple | B y D — la suma ocurre antes de tocar el texto solo cuando los dos primeros operandos son números. | RA-2 |
| 3 | Selección | B — el último índice válido es `length() - 1 = 9`. | RA-3, RA-4 |
| 4 | Selección | C — el índice 3 se pasa de rango: `StringIndexOutOfBoundsException` en tiempo de ejecución. | RA-4, RA-15 |
| 5 | Selección múltiple | A, B y D — el primer guion está en la posición 2, no en la 3. | RA-7 |
| 6 | Abierta | `indexOf` devuelve `-1` si no encuentra el texto; usarlo sin comprobar en un `substring` detiene el programa. | RA-7, RA-15 |
| 7 | Selección | B — `equalsIgnoreCase` compara el contenido ignorando mayúsculas y minúsculas. | RA-5 |
| 8 | Selección múltiple | A, C y D — el valor exacto de `compareTo` no siempre es 1, 0 o -1. | RA-5 |
| 9 | Abierta | `nombre` sigue con espacios: `trim()` no modifica la cadena original, devuelve una nueva. | RA-6, RA-15 |
| 10 | Selección | B — mezclar `int` y `double` da siempre `double`. | RA-8 |
| 11 | Selección múltiple | B y D — el `cast` corta los decimales sin redondear; el desbordamiento es silencioso. | RA-9, RA-15 |
| 12 | Selección | C — texto no numérico detiene el programa con `NumberFormatException`. | RA-11, RA-15 |
| 13 | Selección múltiple | A y C — 100 está en el rango cacheado; `equals` siempre compara el valor. | RA-10 |
| 14 | Selección | B — `String` es inmutable: `toUpperCase()` no cambia `s`, devuelve una cadena nueva. | RA-12 |
| 15 | Abierta | Conviene con texto que se arma por partes en un bucle; se usa `.append(...)` para agregar cada parte. | RA-13, RA-15 |
