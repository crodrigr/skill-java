# 🟢 Básico 02 — Valor final tras asignaciones compuestas

## 🧩 Problema

La **Biblioteca Universitaria** registra los movimientos de un usuario con asignaciones
compuestas. Sigue el programa **línea por línea** y anota el valor de cada variable después de
cada paso. Cuida la división entera de los pasos 6 y 7.

## 💻 Código o contexto de partida

```java
int librosPrestados = 2;
int diasRetraso = 0;
int multaAcumulada = 0;

librosPrestados += 1;               // paso 1
diasRetraso += 5;                   // paso 2
multaAcumulada = diasRetraso * 1500; // paso 3
librosPrestados -= 2;               // paso 4
diasRetraso *= 2;                   // paso 5
diasRetraso /= 3;                   // paso 6
diasRetraso %= 2;                   // paso 7
librosPrestados++;                  // paso 8
```

Completa la tabla con el valor de cada variable **después** del paso indicado:

| Paso | `librosPrestados` | `diasRetraso` | `multaAcumulada` |
|---|---|---|---|
| Inicio | 2 | 0 | 0 |
| 1 | | | |
| 2 | | | |
| 3 | | | |
| 4 | | | |
| 5 | | | |
| 6 | | | |
| 7 | | | |
| 8 | | | |

## 📏 Criterios de evaluación de la solución

- Los valores finales de las tres variables son correctos.
- En el paso 6 se aplica la división entera (se descartan los decimales).
- En el paso 7 se usa el residuo de la división.
- Se distingue `=` (asigna) de las asignaciones compuestas (actualizan el valor anterior).

## 🚧 Restricciones

- Resuélvelo primero a mano; después puedes comprobarlo ejecutando el programa.
- No se usan condiciones.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: actualizar variables con la asignación simple y compuesta.
- **RA-2**: calcular con operadores aritméticos, incluidos el residuo, el incremento y la
  división entera.
