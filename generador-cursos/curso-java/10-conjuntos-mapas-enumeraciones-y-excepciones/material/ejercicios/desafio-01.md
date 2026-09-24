# 🏆 Desafío 01 — Combinar Set y Map

## 🧩 Problema

Biblioteca Universitaria quiere agrupar sus libros por categoría, permitiendo que cada categoría tenga
varios títulos, sin duplicados dentro de la misma categoría.

Diseña `Map<String, Set<String>> librosPorCategoria`: cada categoría (clave) se asocia a un `Set` de
títulos (valor). Agrega al menos tres títulos distribuidos en al menos dos categorías, incluido un
título repetido dentro de la misma categoría.

## 💻 Código o contexto de partida

No se provee ningún archivo de partida: diseña el programa completo desde cero.

## ✅ Salida esperada al ejecutar

```text
categorias=2
Novela.size=2
Infantil.size=1
```

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| Tres títulos agregados a `"Novela"` (uno repetido) y uno a `"Infantil"` | `librosPorCategoria.get("Novela").size()` | `2` (el repetido no crece el `Set`) |
| Mismo caso | `librosPorCategoria.keySet().size()` | `2` categorías |

## 📏 Criterios de evaluación de la solución

- `librosPorCategoria` es un `Map<String, Set<String>>`.
- Agregar un título repetido a la misma categoría no hace crecer su `Set`.
- Se usan al menos dos categorías distintas.

## 🚧 Restricciones

- No se usa `enum` ni excepciones personalizadas (temas de otros puntos del módulo).
- Solo se usan las operaciones de `Map` y `Set` ya vistas en los ejemplos (`put`, `get`, `containsKey`,
  `add`).

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-2**: usar las operaciones más comunes de un `Set`.
- **RA-5**: declarar y usar un `Map`.
