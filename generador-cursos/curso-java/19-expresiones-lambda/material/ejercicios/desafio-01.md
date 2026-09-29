# 🏆 Desafío 01 — Diseñar un caso nuevo combinando interfaces funcionales

## 🧩 Problema

La Biblioteca Universitaria quiere calcular y notificar la multa de los libros vencidos: dado un libro
con una cantidad de días de atraso, debe determinar si tiene multa, calcular su monto, y notificarla —
solo para los libros que realmente tienen multa.

Diseña un sistema nuevo (no usado en los ejemplos de este módulo) que combine al menos dos interfaces
funcionales (estándar y/o propias), según corresponda a cada parte del problema.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá qué parte del problema
es una condición (¿tiene multa o no?), cuál es una transformación (¿cuánto debe pagar?), y cuál es una
acción (¿cómo se notifica?).

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Libro con 5 días de atraso | Notificación | Se notifica, con el monto de la multa calculado |
| Libro con 0 días de atraso | Notificación | No se notifica nada |

## 📏 Criterios de evaluación de la solución

- Usa al menos dos interfaces funcionales (estándar y/o propias), cada una con una responsabilidad clara.
- Ningún libro sin multa genera una notificación.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map`, referencias a métodos (`::`) ni la API de Streams como parte del diseño.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-7**: dado un problema de diseño nuevo, elegir o diseñar la interfaz funcional adecuada para cada
  parte.
