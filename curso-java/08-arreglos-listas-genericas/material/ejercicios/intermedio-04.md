# 🟡 Intermedio 04 — Declarar y operar una LinkedList

## 🧩 Problema

**MediSalud** tiene una lista de espera de pacientes: `"Luis Peña"` y `"Marta Salinas"`, en ese orden.
Llega `"Ana Torres"`, un caso urgente que debe pasar al frente de la fila, sin importar el orden de
llegada.

**Tarea**: declara `LinkedList<String> espera` con los dos pacientes originales, agrega a `"Ana Torres"`
al **principio** de la lista, e imprime la lista completa recorrida con `for-each`, seguida del tamaño.

## 🧪 Casos de prueba

| Salida esperada |
|---|
| `Ana Torres` |
| `Luis Peña` |
| `Marta Salinas` |
| `3` |

## 📏 Criterios de evaluación de la solución

- `espera` es una `LinkedList<String>`, no un `ArrayList` ni un arreglo.
- `"Ana Torres"` se agrega con la operación que inserta al principio, no con `add` (que agrega al
  final).
- El recorrido usa `for-each`.

## 🚧 Restricciones

- No uses `add(0, ...)`: usa la operación propia de `LinkedList` para insertar al principio.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-11**: declarar y operar una `LinkedList`, incluyendo inserción al principio.
