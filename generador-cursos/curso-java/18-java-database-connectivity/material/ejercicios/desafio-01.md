# 🏆 Desafío 01 — Diseñar un caso nuevo combinando JDBC y MVC

## 🧩 Problema

La Biblioteca Universitaria quiere un sistema para gestionar sus socios (registrar, actualizar y borrar),
persistiendo los cambios en su base de datos MySQL.

Diseña un sistema nuevo (no usado en los ejemplos de este módulo) que combine `PreparedStatement` con una
organización en capas Modelo-Vista-Controlador.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá en qué operaciones
necesita el Modelo (consultar, registrar, actualizar, borrar), qué necesita mostrar la Vista, y qué
coordina el Controlador.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar un socio nuevo, consultarlo, actualizar su categoría y borrarlo | Estado de la tabla `socios` en cada paso | Cada operación se refleja exactamente en una nueva consulta a la base de datos |

## 📏 Criterios de evaluación de la solución

- Todas las operaciones (registrar, consultar, actualizar, borrar) usan `PreparedStatement` parametrizado.
- El Modelo, la Vista y el Controlador viven en clases (o paquetes) separados, con responsabilidades
  claras.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map`, ORMs ni pools de conexiones como parte del diseño.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-8**: dado un problema de diseño nuevo, combinar JDBC con `PreparedStatement` y una organización
  MVC.
