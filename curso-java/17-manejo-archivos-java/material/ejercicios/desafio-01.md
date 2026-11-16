# 🏆 Desafío 01 — Diseñar un caso nuevo combinando texto plano y serialización

## 🧩 Problema

La Biblioteca Universitaria quiere un sistema que registre las devoluciones del día en un archivo de
texto (simple, legible), y que además guarde un registro completo y serializado de cada usuario que
devuelve un libro, recuperable en una ejecución posterior.

Diseña un sistema nuevo (no usado en los ejemplos de este módulo) que combine texto plano y
serialización, según corresponda a cada dato.

## 💻 Código o contexto de partida

No se provee código de partida: el diseño es completamente tuyo. Como guía, pensá en qué dato es simple
y cambia seguido (mejor como texto plano) y cuál es un objeto completo que necesita reconstruirse exacto
(mejor serializado).

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar 3 devoluciones del día | Archivo de texto con el registro | Contiene las 3 devoluciones, releído correctamente |
| Serializar el registro de un usuario y deserializarlo en una ejecución separada | Campos del usuario deserializado | Exactamente iguales a los originales |

## 📏 Criterios de evaluación de la solución

- Usa texto plano (lectura/escritura) para los datos simples del registro de devoluciones del día.
- Usa serialización (`Serializable` + `ObjectOutputStream`/`ObjectInputStream`) para el objeto completo
  del usuario, verificado entre dos ejecuciones separadas del programa.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se reutiliza ninguna clase de los ejemplos ni de los demás ejercicios del módulo: todo el diseño es
  nuevo.
- No se usan `Set`, `Map`, `RandomAccessFile` ni `java.nio.file` como parte del diseño.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-11**: dado un problema de diseño nuevo, combinar lectura, escritura y/o borrado con
  serialización/deserialización, según corresponda a cada dato.
