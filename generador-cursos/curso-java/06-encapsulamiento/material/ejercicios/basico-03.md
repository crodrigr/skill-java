# 🟢 Básico 03 — El error de un private

## 🧩 Problema

La **Biblioteca Universitaria** intenta acceder a un atributo `private` de otra clase directamente.
Identifica el error y explica cómo corregirlo.

## 💻 Código o contexto de partida

```java no-compila
class Usuario {
    private String nombreUsuario;
}

public class AccesoPrivateInvalido {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.nombreUsuario = "Carlos Ramírez";
    }
}
```

```text
⚠ The value of the field Usuario.nombreUsuario is not used Java(570425421) [Ln 2, Col 20]
✖ The field Usuario.nombreUsuario is not visible Java(33554503) [Ln 8, Col 17]
```

## 📏 Criterios de evaluación de la solución

- Identifica que `nombreUsuario` es `private` y que `AccesoPrivateInvalido`, aunque esté en el mismo
  archivo, es una clase distinta.
- Propone la corrección: agregar un método `setNombreUsuario(...)` y usarlo en vez del acceso directo.

## 🚧 Restricciones

- No necesitas corregir el código, solo identificar el error y explicarlo.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: declarar un atributo `private` y explicar el error de acceder a él desde otra clase.
