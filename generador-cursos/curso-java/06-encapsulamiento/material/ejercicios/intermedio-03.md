# 🟡 Intermedio 03 — Elegir el modificador correcto

## 🧩 Problema

La **Biblioteca Universitaria** modela una `Membresia` con tres datos:

1. El **número de membresía**: nadie fuera de la clase debe poder modificarlo directamente; solo se
   lee a través de un método.
2. Un **contador de renovaciones**: otras clases del mismo paquete de administración interna sí
   necesitan poder ajustarlo directamente, pero clases de otros paquetes no.
3. El **nombre del usuario**: cualquier parte del sistema, de cualquier paquete, debe poder mostrarlo.

Declara cada atributo con el modificador de acceso adecuado, justificando en una frase cada elección.

## 🧪 Casos de prueba

```text
Ana Torres - M-2026-001
Renovaciones: 2
```

## 📏 Criterios de evaluación de la solución

- `numeroMembresia` se declara `private`, con un método `get` para leerlo.
- `contadorRenovaciones` se declara sin modificador (por defecto).
- `nombreUsuario` se declara `public`.
- Cada elección está justificada con el criterio de "quién debe poder usar este miembro".

## 🚧 Restricciones

- No cambies los nombres de los atributos.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: elegir el modificador de acceso adecuado según quién debe usar el miembro.
