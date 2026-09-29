# 🏆 Desafío 01 — Diseñar un pipeline nuevo combinando operadores

## 🧩 Problema

La Biblioteca Universitaria quiere generar las notificaciones de reservas atrasadas: dada una lista de
reservas de estudiantes con sus días de espera, debe seleccionar solo las que llevan más de 7 días, y
generar un mensaje de notificación para cada una.

Diseña un pipeline de Stream API nuevo (no usado en los ejemplos de este módulo) que combine al menos
dos operadores, y convierta el resultado final a una `List<String>` de notificaciones.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá qué parte del
problema es una selección (¿cuáles reservas llevan más de 7 días?) y cuál es una transformación
(¿cómo se arma el mensaje de cada notificación?).

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Reservas con 9, 3 y 12 días de espera | Notificaciones generadas | Se notifican solo las de 9 y 12 días; la de 3 días no aparece |

## 📏 Criterios de evaluación de la solución

- Usa al menos dos operadores de Stream API (por ejemplo, `filter` y `map`), cada uno con una
  responsabilidad clara.
- Ninguna reserva con 7 días de espera o menos genera una notificación.
- El resultado final es una `List<String>`, obtenida con `.toList()`.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map`, referencias a métodos (`::`), streams paralelos ni excepciones propias como
  parte del diseño.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-11**: dado un problema de diseño nuevo, combinar los operadores de Stream API adecuados para cada
  parte.
