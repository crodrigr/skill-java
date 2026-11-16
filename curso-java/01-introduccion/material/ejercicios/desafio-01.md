# 🏆 Desafío 01 — Carnet de biblioteca con salida exacta

## 🧩 Problema

La **Biblioteca Universitaria** quiere imprimir el carnet de cada usuario en la consola.
Te entregan la **salida exacta** que debe producir el programa y los datos que hay que
usar. Tu tarea es escribir el programa desde cero (sin código de partida), reproducir la
salida **carácter por carácter** y **justificar el tipo** que elegiste para cada dato.

## 💻 Código o contexto de partida

Creá un proyecto `CarnetBiblioteca` con el paquete `com.biblioteca`. No hay código de
partida: escribí todo el programa.

**Datos** que debe usar el programa:

| Dato | Valor | ¿Cambia? |
|---|---|---|
| Nombre de la biblioteca | `BIBLIOTECA UNIVERSITARIA` | No |
| Máximo de libros en préstamo | `3` | No |
| Multa por día de retraso | `1500.0` | No |
| Nombre del usuario | `Valentina Ríos` | Sí |
| Código del usuario | `20240123` | Sí |
| Tipo de usuario (una letra: `E` estudiante, `D` docente) | `E` | Sí |
| Libros prestados actualmente | `2` | Sí |
| Días de préstamo | `7` | Sí |
| ¿Está al día? | `true` | Sí |

**Salida que debe producir** (respetá espacios, mayúsculas y símbolos):

```text
*************************************
 BIBLIOTECA UNIVERSITARIA - CARNET
*************************************
Usuario:     Valentina Ríos
Código:      20240123
Tipo:        E (E = estudiante, D = docente)
Préstamos:   2 de 3 libros
Plazo:       7 días
Multa/día:   1500.00
Al día:      true
*************************************
```

## 📏 Criterios de evaluación de la solución

- La salida coincide exactamente con la salida objetivo.
- Los datos que no cambian son constantes (`final`) y los demás, variables; los nombres
  siguen las convenciones del curso.
- Cada dato usa un tipo primitivo adecuado (o `String` para el texto), y los valores
  aparecen **en variables o constantes**, no escritos directamente dentro de los textos
  de impresión.
- Se escribe una **justificación** del tipo elegido para cada dato: por qué ese tipo y
  no otro (por ejemplo, por el rango o por la naturaleza del dato).
- Se usan `println` con `+` y `printf` (al menos para la multa y para la línea de
  préstamos).

## 🚧 Restricciones

- Sin código de partida: el programa se escribe completo.
- Un solo archivo con la clase `CarnetBiblioteca`.
- Solo tipos primitivos (más `String` para el texto).

## 📊 Dificultad

Desafío

## ✅ Salida esperada al ejecutar

```text
*************************************
 BIBLIOTECA UNIVERSITARIA - CARNET
*************************************
Usuario:     Valentina Ríos
Código:      20240123
Tipo:        E (E = estudiante, D = docente)
Préstamos:   2 de 3 libros
Plazo:       7 días
Multa/día:   1500.00
Al día:      true
*************************************
```

> ⚠️ En un computador configurado en español, la multa puede mostrarse con coma
> (`1500,00`). Es normal (Ejemplo 10).

## 🎓 Resultados de aprendizaje

RA-9, RA-10, RA-11
