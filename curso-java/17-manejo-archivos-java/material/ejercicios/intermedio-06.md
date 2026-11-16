# 🟡 Intermedio 06 — Aplicar deserialización

## 🧩 Problema

Tienes el mismo registro de usuario de la Biblioteca Universitaria del ejercicio Básico 06, pero ahora
como un caso de dos ejecuciones separadas: un programa que serializa el usuario y termina, y otro que lo
recupera después.

## 💻 Código o contexto de partida

No se provee código de partida para el par de ejecuciones: usa `SocioBiblioteca implements Serializable` (como
en el Intermedio 05) como base.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Ejecutar el programa que serializa (`Escritor`) y termina | Archivo `usuario.ser` | Existe después de que el proceso termina |
| Ejecutar, en un proceso Java separado, el programa que deserializa (`Lector`) | Campos del usuario recuperado | Exactamente iguales a los del usuario original serializado |

## 📏 Criterios de evaluación de la solución

- Declara una clase `Escritor` (serializa un `SocioBiblioteca` y termina) y una clase `Lector` (deserializa el
  mismo archivo), cada una con su propio `main`.
- `Lector` reconstruye el objeto con una sola llamada a `ObjectInputStream.readObject()`, sin convertir
  ningún campo manualmente.
- Los campos del usuario deserializado coinciden exactamente con los originales.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni clases atómicas como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-10**: implementar deserialización con `ObjectInputStream`, entre ejecuciones separadas.
