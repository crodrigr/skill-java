# 🏆 Desafío 01 — Elegir y aplicar el patrón adecuado a un caso nuevo

## 🧩 Problema

La Biblioteca Universitaria quiere que su editor de reseñas de libros permita deshacer cambios de
texto, volviendo a versiones anteriores de una reseña en edición, sin que quien guarda esas versiones
necesite conocer los detalles internos de cómo se representa el texto de la reseña.

Diseña un sistema nuevo (no usado en los ejemplos de este módulo) que resuelva este problema aplicando
el patrón de comportamiento que mejor encaje.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá en cuál de los cuatro
patrones de comportamiento de este módulo resuelve, específicamente, el problema de guardar y restaurar
el estado de un objeto en distintos momentos, sin romper su encapsulamiento.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Escribir un primer texto de reseña y guardar un instante | Texto actual de la reseña | El primer texto |
| Escribir un segundo texto (por error) | Texto actual de la reseña | El segundo texto |
| Restaurar el instante guardado | Texto actual de la reseña | Exactamente el primer texto, no el segundo |

## 📏 Criterios de evaluación de la solución

- Identifica y aplica el patrón de comportamiento adecuado (justificando por qué encaja mejor que los
  otros tres).
- La clase que guarda las versiones no necesita ningún método público para leer el contenido de un
  instante guardado.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño
  es nuevo.
- No se usan `Set`, `Map` ni excepciones como parte del diseño (temas fuera de alcance de este módulo).

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-29**: dado un problema de diseño nuevo, elegir el patrón de comportamiento adecuado y
  justificarlo.
