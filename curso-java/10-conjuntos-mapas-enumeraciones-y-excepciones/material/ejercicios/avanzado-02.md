# 🔴 Avanzado 02 — Diseñar una excepción personalizada

## 🧩 Problema

Biblioteca Universitaria necesita rechazar el registro de un préstamo cuando el libro ya tiene un
préstamo activo.

## 💻 Código o contexto de partida

No se provee ningún archivo de partida: diseña `PrestamoInvalidoException` (`extends RuntimeException`)
y un método `validarPrestamo(String libro, Set<String> librosPrestados)` que la lance con `throw` cuando
el libro ya está en `librosPrestados`.

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| `librosPrestados = {"LIB-012"}` | `validarPrestamo("LIB-012", librosPrestados)` | Lanza `PrestamoInvalidoException`, capturable con `catch` |
| Mismo `librosPrestados` | `validarPrestamo("LIB-030", librosPrestados)` | No lanza nada |

## 📏 Criterios de evaluación de la solución

- `PrestamoInvalidoException` extiende `RuntimeException`, con un constructor que recibe un mensaje.
- `validarPrestamo` lanza la excepción solo cuando el libro ya tiene un préstamo activo.
- Probado con un caso inválido (lanza, capturado) y uno válido (no lanza, se registra) en el mismo
  programa.

## 🚧 Restricciones

- No reutiliza `CitaInvalidaException` del Ejemplo 08: es una excepción nueva, propia de este ejercicio.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-14**: lanzar una excepción propia con `throw`, incluida una personalizada.
