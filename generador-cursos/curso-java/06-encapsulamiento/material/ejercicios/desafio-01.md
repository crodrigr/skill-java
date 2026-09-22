# 🏆 Desafío 01 — Encapsular una clase nueva

## 🧩 Problema

La **Biblioteca Universitaria** quiere registrar reservas de libros. Diseña una clase `Reserva` (no
usada en los ejemplos anteriores) que represente la reserva de un libro por un usuario, con:

- Al menos tres atributos `private` (por ejemplo, el nombre del usuario, el título del libro y los
  días de reserva).
- Un constructor que inicialice los tres, usando un `set` para el atributo que necesite validación.
- Al menos dos métodos de instancia (uno de ellos, un `set` que rechace un valor inválido, por ejemplo
  días de reserva menores a 1).

Justifica en un comentario de una línea por qué elegiste esa validación.

## 💻 Código o contexto de partida

No hay código de partida. Estos son los **datos de entrada** de los dos casos:

| Caso | `nombreUsuario` | `tituloLibro` | `diasReserva` |
|---|---|---|---|
| 1 | `"Ana Torres"` | `"Cien años de soledad"` | `7` |
| 2 | `"Carlos Ramírez"` | `"El principito"` | `-3` (inválido) |

## ✅ Salida esperada al ejecutar

```text
Ana Torres reservó "Cien años de soledad" por 7 días
Días de reserva inválidos (-3): se conserva el valor actual (0)
Carlos Ramírez reservó "El principito" por 0 días
```

## 📏 Criterios de evaluación de la solución

- Con los dos casos, el programa produce **exactamente** la salida indicada.
- El caso con `diasReserva = -3` no lo asigna: el atributo queda en su valor por defecto y se muestra
  un aviso, sin detener el programa.
- Cada comentario justifica la validación elegida.

## 🚧 Restricciones

- Usa solo lo aprendido en el módulo; no uses herencia, colecciones ni excepciones.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-2**: explicar la diferencia entre los cuatro modificadores de acceso.
- **RA-3**: declarar un atributo `private`.
- **RA-7**: elegir el modificador de acceso adecuado.
- **RA-9**: declarar un método `set` que valide antes de asignar.
