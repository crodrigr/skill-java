# 🟡 Intermedio 02 — Descomponer un código

## 🧩 Problema

Cada préstamo de la **Biblioteca Universitaria** tiene un código con el formato `PR-CATEGORIA-NUMERO`
(por ejemplo, `PR-N-000045`, donde `N` es "narrativa"). Completa el programa de partida: reemplaza el
`TODO` por código que use `indexOf` y `substring` para obtener la categoría y el número, sin importar
cuántos caracteres tenga cada parte.

## 💻 Código o contexto de partida

```java
public class DescomponerCodigo {

    public static void main(String[] args) {
        // Datos de entrada
        String codigoPrestamo = "PR-N-000045";

        String categoria = "";
        String numero = "";
        // TODO: usa indexOf y substring para obtener la categoría (entre los dos guiones) y el número (después del segundo guion)

        System.out.println("Categoría: " + categoria + " | Número: " + numero);
    }
}
```

## 🧪 Casos de prueba

| Código | Categoría esperada | Número esperado |
|---|---|---|
| PR-N-000045 | N | `000045` |
| PR-C-5 | C | `5` |

## 📏 Criterios de evaluación de la solución

- La categoría y el número se extraen correctamente en los dos casos, incluido el código con una
  categoría y un número más cortos.
- La solución encuentra el **segundo** guion (no asume una posición fija), por ejemplo con
  `indexOf("-", primerGuion + 1)`.
- El programa compila y produce el formato de salida indicado.

## 🚧 Restricciones

- Usa `indexOf` y `substring`; no asumas que la categoría siempre tiene un carácter.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: usar `indexOf` y `substring` para descomponer un texto con formato fijo.
- **RA-14**: comprobar la solución con más de un caso de prueba.
