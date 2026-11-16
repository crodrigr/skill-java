# 🏆 Desafío 01 — Elegir y aplicar el patrón adecuado a un caso nuevo

## 🧩 Problema

La Biblioteca Universitaria quiere controlar el acceso a su Sala de Consulta de Manuscritos Antiguos:
solo los socios con un permiso especial pueden ingresar. Abrir la sala tiene un costo (hay que activar el
control climático y de humedad de los manuscritos), así que no conviene hacerlo para un socio al que
después de todo se le va a negar el acceso.

Diseña un sistema nuevo (no usado en los ejemplos ni en los demás ejercicios de este módulo) que resuelva
este problema aplicando el patrón estructural que mejor encaje.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá en cuál de los siete
patrones estructurales resuelve, específicamente, el problema de controlar el acceso a un recurso antes
de decidir si vale la pena pagar el costo de activarlo.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Un socio sin permiso intenta ingresar | Mensaje de acceso | Denegado, sin que se active el control climático de la sala |
| Un socio con permiso intenta ingresar | Mensaje de acceso | Autorizado, y recién ahí se activa el control climático |

## 📏 Criterios de evaluación de la solución

- Identifica y aplica el patrón estructural adecuado (justificando por qué encaja mejor que los otros
  seis).
- El control de permiso ocurre antes de crear o activar el recurso costoso (la sala real), de forma que
  un socio sin permiso nunca llega a activarlo.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map` ni excepciones como parte del diseño (temas fuera de alcance de este módulo).

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-23**: dado un problema de diseño nuevo, elegir el patrón estructural adecuado y justificarlo.
