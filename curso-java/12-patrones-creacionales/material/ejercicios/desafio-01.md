# 🏆 Desafío 01 — Elegir y aplicar el patrón adecuado a un caso nuevo

## 🧩 Problema

La Biblioteca Universitaria quiere un sistema para armar la ficha de un evento cultural (charla, taller,
club de lectura). Cada evento tiene dos datos obligatorios (título, fecha) y varios datos opcionales que
no siempre se combinan igual: cupo máximo, si requiere inscripción previa, si es virtual (y en ese caso
el enlace), y quién lo modera. Distintos eventos usan combinaciones distintas de estos datos opcionales.

Diseña un sistema nuevo (no usado en los ejemplos de este módulo) que resuelva este problema aplicando
el patrón creacional que mejor encaje.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá en cuál de los cinco
patrones creacionales resuelve, específicamente, el problema de combinar datos opcionales de formas
distintas sin necesitar un constructor con una cantidad inmanejable de parámetros.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Un evento presencial con cupo, inscripción requerida y moderador, pero sin datos de virtualidad | Resumen del evento | Los datos no usados (enlace) aparecen como ausentes, sin necesitar pasarlos |
| Un evento virtual con enlace, sin cupo ni moderador | Resumen del evento | Los datos no usados (cupo, moderador) aparecen como ausentes |

## 📏 Criterios de evaluación de la solución

- Identifica y aplica el patrón creacional adecuado (justificando por qué encaja mejor que los otros
  cuatro).
- El diseño permite combinar los datos opcionales de formas distintas sin un constructor de muchos
  parámetros.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map` ni excepciones como parte del diseño (temas fuera de alcance de este módulo).

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-17**: dado un problema de diseño nuevo, elegir el patrón creacional adecuado y justificarlo.
