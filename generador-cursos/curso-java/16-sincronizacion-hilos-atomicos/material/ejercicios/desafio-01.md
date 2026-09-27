# 🏆 Desafío 01 — Diseñar un caso nuevo combinando el Módulo 15 y este módulo

## 🧩 Problema

La Biblioteca Universitaria quiere renovar en paralelo los accesos digitales de varios usuarios,
registrando en un contador compartido cuántas renovaciones se completaron, con un límite de tiempo
máximo para cancelar las que sigan en curso.

Diseña un sistema nuevo (no usado en los ejemplos de este módulo) que combine al menos una herramienta
del Módulo 15 (creación, interrupción o unión de hilos) con al menos una de este módulo (sincronización o
acceso atómico), sin introducir ningún riesgo de interbloqueo.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá en qué dato va a ser
escrito por más de un hilo a la vez (ese es el que necesita protección), y en si tu diseño necesita
sincronizar sobre más de un recurso compartido a la vez (en cuyo caso, todos los hilos deben adquirirlos
siempre en el mismo orden).

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Renovar varios accesos en paralelo, con un límite de tiempo generoso | Cantidad de renovaciones completadas | Igual al total de accesos |
| Renovar varios accesos en paralelo, con un límite de tiempo corto | Cantidad de renovaciones completadas | Menor al total de accesos, confirmando que la cancelación funcionó |
| Ejecutar cualquiera de los dos casos varias veces | Cantidad de renovaciones completadas | El mismo resultado en cada ejecución, sin variar |

## 📏 Criterios de evaluación de la solución

- Usa al menos una herramienta del Módulo 15 (creación, interrupción o unión de hilos) y al menos una de
  este módulo (sincronización o acceso atómico).
- El dato compartido y modificado por varios hilos está protegido correctamente.
- Si el diseño sincroniza sobre más de un recurso compartido, todos los hilos lo hacen siempre en el
  mismo orden.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map`, `Lock`/`ReentrantLock` ni `ExecutorService` como parte del diseño.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-8**: diseñar una solución integradora combinando el Módulo 15 y este módulo, sin interbloqueo.
