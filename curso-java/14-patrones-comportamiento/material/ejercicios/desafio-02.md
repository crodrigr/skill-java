# 🏆 Desafío 02 — Elegir y aplicar el patrón adecuado a un caso nuevo

## 🧩 Problema

La Biblioteca Universitaria quiere controlar el ciclo de vida de una reserva de sala de estudio: una
reserva puede estar Pendiente, Confirmada, Vencida (si nadie la confirmó a tiempo) o Cancelada, y cada
estado determina qué acciones son válidas. Agregar un estado nuevo en el futuro (por ejemplo, "en espera
de pago") no debería exigir revisar condicionales repartidos en varios métodos.

Diseña un sistema nuevo (no usado en los ejemplos de este módulo) que resuelva este problema aplicando
el patrón de comportamiento que mejor encaje.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá en cuál de los cinco
patrones de comportamiento resuelve, específicamente, el problema de un comportamiento que depende del
estado interno de un objeto, sin condicionales dispersos en varios métodos.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Confirmar una reserva Pendiente | Resultado | Se confirma correctamente |
| Intentar vencer o volver a confirmar una reserva ya Confirmada | Resultado | Ambos intentos se rechazan, cada uno con un mensaje específico |
| Cancelar una reserva Confirmada | Resultado | Se cancela correctamente |

## 📏 Criterios de evaluación de la solución

- Identifica y aplica el patrón de comportamiento adecuado (justificando por qué encaja mejor que los
  otros cuatro).
- Ningún método del objeto principal (la reserva) contiene un condicional sobre su propio estado.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map` ni excepciones como parte del diseño (temas fuera de alcance de este módulo).

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-29**: dado un problema de diseño nuevo, elegir el patrón de comportamiento adecuado y
  justificarlo.
