# 🏆 Desafío 01 — Diseñar una clase nueva con salida exacta

## 🧩 Problema

La **Biblioteca Universitaria** quiere controlar el estado de sus préstamos. Diseña una clase
`Prestamo` (no usada en los ejemplos anteriores) que represente un préstamo de un libro a un usuario, y
un programa que cree varios préstamos y muestre el estado de cada uno.

La clase debe tener:

- Al menos tres atributos (por ejemplo, el título del libro, el nombre del usuario y los días
  restantes).
- Un constructor que inicialice los tres atributos.
- Al menos dos métodos de instancia, uno de los cuales debe devolver un valor (por ejemplo, si el
  préstamo está vencido).

Justifica en un comentario de una línea sobre cada método por qué lo elegiste.

## 💻 Código o contexto de partida

No hay código de partida. Estos son los **datos de entrada** de los tres préstamos:

| Préstamo | `tituloLibro` | `nombreUsuario` | `diasRestantes` |
|---|---|---|---|
| 1 | `"Cien años de soledad"` | `"Ana Torres"` | `5` |
| 2 | `"El principito"` | `"Carlos Ramírez"` | `0` |
| 3 | `"Rayuela"` | `"Lucía Gómez"` | `-2` |

## ✅ Salida esperada al ejecutar

```text
Cien años de soledad (Ana Torres): 5 días restantes
El principito (Carlos Ramírez): vence hoy
Rayuela (Lucía Gómez): vencido
```

## 📏 Criterios de evaluación de la solución

- Con los tres préstamos, el programa produce **exactamente** la salida indicada, línea por línea.
- El préstamo con `diasRestantes = 0` muestra "vence hoy", no "vencido" ni "0 días restantes": es el
  caso límite.
- El préstamo con `diasRestantes` negativo muestra "vencido".
- Cada comentario justifica el método elegido.

## 🚧 Restricciones

- Usa solo lo aprendido en el módulo; no uses `private`, herencia, ni colecciones.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar qué es la POO.
- **RA-2**: explicar por qué conviene la POO.
- **RA-8**: leer y escribir una clase completa.
- **RA-9**: crear un objeto con `new`.
- **RA-10**: declarar un constructor con parámetros.
- **RA-11**: crear y usar varios objetos de la misma clase, cada uno con su propio estado.
