# 🏆 Desafío 01 — Plan de cobro de una multa con salida exacta

## 🧩 Problema

La **Biblioteca Universitaria** quiere que cada multa por retraso produzca un comunicado con **formato
exacto**. Escribe un programa (proyecto nuevo, paquete `com.biblioteca`, clase `PlanCobroMulta`) que, a
partir de los datos de entrada, aplique estas reglas y muestre el comunicado:

- **Multa por día**: `MULTA_POR_DIA = 1500.0` por cada día de retraso **hábil**. Los **domingos** (los
  días que son múltiplo de 7, contando desde el día 1) **no se cobran**.
- **Tope**: la multa nunca supera `MULTA_MAXIMA = 30000.0`. Al alcanzarlo, se **deja de recorrer** los
  días.
- **Plan de pago**: la multa se reparte en `numeroCuotas` cuotas iguales, y se muestra **una línea por
  cada cuota**.

Elige tú la estructura repetitiva y las sentencias (`for`, `while` o `do-while`, `break`, `continue`)
que mejor expresan cada parte y **justifícalas** en un comentario de una línea.

## 💻 Código o contexto de partida

No hay código de partida. Estos son los **datos de entrada** de los cuatro casos:

| Caso | `diasRetraso` | `numeroCuotas` |
|---|---|---|
| 1 | 0 | 1 |
| 2 | 10 | 3 |
| 3 | 30 | 4 |
| 4 | 7 | 2 |

## ✅ Salida esperada al ejecutar

**Caso 1** (sin retraso: el bucle no se ejecuta):

```text
=== Biblioteca Universitaria ===
Días de retraso: 0
Días cobrados: 0
Multa: 0
Cuotas del plan de pago: 1
Cuota 1: 0
```

**Caso 2** (10 días: un domingo que no se cobra):

```text
=== Biblioteca Universitaria ===
Días de retraso: 10
Días cobrados: 9
Multa: 13500
Cuotas del plan de pago: 3
Cuota 1: 4500
Cuota 2: 4500
Cuota 3: 4500
```

**Caso 3** (30 días: se alcanza el tope):

```text
=== Biblioteca Universitaria ===
Días de retraso: 30
Días cobrados: 20
Multa: 30000
Cuotas del plan de pago: 4
Cuota 1: 7500
Cuota 2: 7500
Cuota 3: 7500
Cuota 4: 7500
```

**Caso 4** (7 días: el límite con un solo domingo):

```text
=== Biblioteca Universitaria ===
Días de retraso: 7
Días cobrados: 6
Multa: 9000
Cuotas del plan de pago: 2
Cuota 1: 4500
Cuota 2: 4500
```

## 📏 Criterios de evaluación de la solución

- Con los datos de cada caso, el programa produce **exactamente** la salida indicada, línea por línea.
- Los domingos se omiten con `continue` y el tope se controla con `break`.
- El reparto en cuotas usa un bucle con la estructura adecuada y justificada en un comentario.
- Los contadores y el acumulador se declaran antes de los bucles.
- Los datos de entrada están al inicio de `main` y se cambian solo esos valores para recorrer los cuatro casos.

## 🚧 Restricciones

- Usa solo lo aprendido en el módulo; no uses bucles anidados ni métodos propios.
- Los montos se muestran sin decimales (`%.0f`), para que la salida sea idéntica en cualquier equipo.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-4**: escribir un `for` con contador.
- **RA-5**: elegir y justificar la estructura repetitiva de cada parte.
- **RA-6**: usar contadores y acumuladores.
- **RA-8**: terminar un bucle al alcanzar un tope con `break`.
- **RA-9**: omitir vueltas con `continue`.
- **RA-10**: comprobar las reglas con casos de prueba y valores límite.
