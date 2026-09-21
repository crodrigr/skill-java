# 🏆 Desafío 01 — Autorización de préstamo con salida exacta

## 🧩 Problema

La **Biblioteca Universitaria** quiere que cada solicitud de préstamo produzca un comunicado
con **formato exacto**. Escribe un programa (proyecto nuevo, paquete `com.biblioteca`, clase
`AutorizacionPrestamo`) que, a partir de los datos de entrada, muestre el comunicado aplicando
estas reglas:

- **Multa**: `diasRetraso * MULTA_POR_DIA`, con `MULTA_POR_DIA = 1500.0`. Nunca supera el tope
  `MULTA_MAXIMA = 30000.0`: si lo supera, se cobra el tope.
- **Días de préstamo** según `tipoUsuario`: `"ESTUDIANTE"` 7; `"DOCENTE"` o `"INVESTIGADOR"` 15;
  cualquier otro valor, 0.
- **Estado** según los días de retraso: más de 30, `Suspendido`; de 1 a 30, `Con multa`; con 0,
  `Al día`.
- **Autorización**: el préstamo se autoriza solo si el libro está disponible, el usuario tiene
  menos libros que `MAX_LIBROS_PRESTAMO = 3` **y** no tiene días de retraso. La decisión se
  muestra como `Préstamo autorizado` o `Préstamo denegado`.
- **Motivo** (por este orden): si el libro no está disponible, `El libro no está disponible`; si
  alcanzó el límite de libros, `Alcanzó el límite de libros`; si tiene retraso, `Tiene retraso
  pendiente`; en otro caso, `Cumple todas las condiciones`.

Elige tú la estructura de decisión que mejor expresa cada regla (`if`, `switch` o ternario) y
**justifícala** en un comentario de una línea sobre cada decisión.

## 💻 Código o contexto de partida

No hay código de partida. Estos son los **datos de entrada** de los cuatro casos (siempre con
`disponible = true`):

| Caso | `tipoUsuario` | `librosPrestados` | `diasRetraso` |
|---|---|---|---|
| 1 | `"DOCENTE"` | 2 | 0 |
| 2 | `"ESTUDIANTE"` | 3 | 0 |
| 3 | `"INVESTIGADOR"` | 1 | 12 |
| 4 | `"ESTUDIANTE"` | 2 | 35 |

## ✅ Salida esperada al ejecutar

**Caso 1** (préstamo autorizado):

```text
=== Biblioteca Universitaria ===
Usuario: DOCENTE
Libros prestados: 2 de 3
Días de retraso: 0
Multa: 0
Estado: Al día
Días de préstamo: 15
Decisión: Préstamo autorizado
Motivo: Cumple todas las condiciones
```

**Caso 2** (límite de libros alcanzado):

```text
=== Biblioteca Universitaria ===
Usuario: ESTUDIANTE
Libros prestados: 3 de 3
Días de retraso: 0
Multa: 0
Estado: Al día
Días de préstamo: 7
Decisión: Préstamo denegado
Motivo: Alcanzó el límite de libros
```

**Caso 3** (multa por retraso):

```text
=== Biblioteca Universitaria ===
Usuario: INVESTIGADOR
Libros prestados: 1 de 3
Días de retraso: 12
Multa: 18000
Estado: Con multa
Días de préstamo: 15
Decisión: Préstamo denegado
Motivo: Tiene retraso pendiente
```

**Caso 4** (suspensión, con el tope de la multa):

```text
=== Biblioteca Universitaria ===
Usuario: ESTUDIANTE
Libros prestados: 2 de 3
Días de retraso: 35
Multa: 30000
Estado: Suspendido
Días de préstamo: 7
Decisión: Préstamo denegado
Motivo: Tiene retraso pendiente
```

## 📏 Criterios de evaluación de la solución

- Con los datos de cada caso, el programa produce **exactamente** la salida indicada, línea por
  línea.
- Cada regla usa la estructura adecuada y el comentario de una línea justifica la elección
  (por ejemplo, `switch` para el tipo de usuario, porque compara un mismo valor con opciones
  exactas).
- La multa respeta el tope en el caso 4.
- El motivo respeta el orden de las condiciones.
- Los datos de entrada están al inicio de `main` y se cambian solo esos valores para recorrer
  los cuatro casos.

## 🚧 Restricciones

- Usa solo lo aprendido en el módulo; no uses bucles ni métodos propios.
- Los montos se muestran sin decimales (`%.0f`), para que la salida sea idéntica en cualquier
  equipo.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-5**: combinar condiciones con Y.
- **RA-6**: programar tramos con `if - else if - else`.
- **RA-7**: elegir entre opciones exactas con `switch`.
- **RA-8**: obtener un valor con el operador ternario.
- **RA-9**: elegir y justificar la estructura de decisión adecuada.
- **RA-10**: comprobar las reglas con casos de prueba y valores límite.
- **RA-11**: evitar los errores frecuentes al decidir.
