# 🟡 Intermedio 02 — Declarar y recorrer una matriz

## 🧩 Problema

**Biblioteca Universitaria** tiene 2 sedes, cada una con ejemplares de 3 categorías (Ficción, No
ficción, Técnico).

## 🗺️ Diagrama

```mermaid
flowchart TB
    subgraph Sede0["Sede 0"]
        a0["Ficción: 120"] --- a1["No ficción: 80"] --- a2["Técnico: 45"]
    end
    subgraph Sede1["Sede 1"]
        b0["Ficción: 90"] --- b1["No ficción: 60"] --- b2["Técnico: 30"]
    end
```

**Tarea**: declara `int[][] ejemplaresPorSedeYCategoria` con los valores del diagrama, y calcula el
total de ejemplares de ambas sedes con bucles anidados.

## 🧪 Casos de prueba

| Expresión | Resultado esperado |
|---|---|
| Total de ejemplares | `425` |

## 📏 Criterios de evaluación de la solución

- La matriz tiene 2 filas (sedes) y 3 columnas (categorías) cada una, con los valores del diagrama.
- El total se calcula recorriendo la matriz con dos bucles anidados.
- El programa imprime `425`.

## 🚧 Restricciones

- No sumes los valores a mano: el programa debe calcular el total recorriendo la matriz.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-6**: declarar y recorrer un arreglo multidimensional.
