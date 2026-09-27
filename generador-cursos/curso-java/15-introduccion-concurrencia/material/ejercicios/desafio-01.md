# 🏆 Desafío 01 — Diseñar un caso nuevo combinando los tres temas técnicos

## 🧩 Problema

La Biblioteca Universitaria quiere renovar en paralelo los accesos digitales de un grupo de usuarios,
con un límite de tiempo máximo para todo el proceso: pasado ese límite, las renovaciones que sigan en
curso deben cancelarse, y el sistema debe reportar cuántas renovaciones se completaron realmente antes de
continuar.

Diseña un sistema nuevo (no usado en los ejemplos de este módulo) que resuelva este problema combinando
los tres temas técnicos de este módulo.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá en cómo estructurar la
creación de varios hilos de forma que puedan consultarse después, cómo detenerlos cooperativamente si se
supera el tiempo máximo, y cómo asegurarte de que el conteo final de renovaciones completadas sea
correcto.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Renovar cinco accesos con un tiempo máximo corto | Cantidad de renovaciones completadas impresa | Menor al total de accesos, confirmando que algunas se cancelaron |
| Renovar cinco accesos con un tiempo máximo generoso | Cantidad de renovaciones completadas impresa | Igual al total de accesos |

## 📏 Criterios de evaluación de la solución

- Crea un hilo por renovación (con `Thread` o `Runnable`, a elección), guardando una referencia a cada
  uno.
- Cada hilo revisa su estado de interrupción y se detiene cooperativamente cuando corresponde.
- El proceso invoca `join()` sobre cada hilo antes de contar cuántas renovaciones se completaron
  realmente.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map` ni excepciones propias como parte del diseño (`InterruptedException` es la
  única excepción que se maneja).

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-11**: dado un problema de diseño nuevo, diseñar una solución que combine creación, interrupción y
  unión de hilos.
