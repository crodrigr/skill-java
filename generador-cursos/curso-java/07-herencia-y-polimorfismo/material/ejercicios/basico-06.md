# 🟢 Básico 06 — ¿Clase abstracta o interfaz?

## 🧩 Problema

Para cada escenario de la **Biblioteca Universitaria**, elige y justifica: clase abstracta o interfaz.

**Escenario A**: Todo material de la biblioteca (libros, revistas, tesis) comparte un `titulo` y un
método para calcular sus días de préstamo, pero cada tipo lo calcula distinto. Todos son, además, un
mismo concepto general: "material bibliográfico", con datos y comportamiento en común.

**Escenario B**: Tanto un `Libro` como una `SalaDeEstudio` (una clase totalmente distinta, sin relación
de herencia entre ellas) pueden "reservarse" con anticipación. No comparten ningún atributo ni código:
solo comparten la capacidad de ser reservados.

**Pregunta**: para cada escenario, ¿usarías una clase abstracta o una interfaz? Justifica en una frase.

## 📏 Criterios de evaluación de la solución

- Escenario A: clase abstracta (estado y comportamiento compartido entre subclases emparentadas).
- Escenario B: interfaz (contrato sin estado entre clases no emparentadas).
- La justificación menciona el criterio correcto (estado/código compartido frente a contrato sin
  estado).

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de decisión y justificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-13**: elegir entre clase abstracta e interfaz según el escenario.
