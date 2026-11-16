# 🟡 Intermedio 01 — Normalizar y formatear un nombre

## 🧩 Problema

La **Biblioteca Universitaria** quiere mostrar el nombre de cada usuario con un formato consistente:
sin espacios de más y con la primera letra en mayúscula, el resto en minúscula. Completa el programa
de partida: reemplaza el `TODO` con las transformaciones necesarias.

## 💻 Código o contexto de partida

```java
public class NormalizarUsuario {

    public static void main(String[] args) {
        // Datos de entrada
        String nombreSucio = "  carlos ramírez  ";

        String nombreNormalizado = nombreSucio;
        // TODO: quita los espacios de los extremos y pon la primera letra en mayúscula, el resto en minúscula

        System.out.println("[" + nombreNormalizado + "]");
    }
}
```

## 🧪 Casos de prueba

| Nombre de entrada | Nombre normalizado |
|---|---|
| [  carlos ramírez  ] | Carlos ramírez |
| [  ANA  ] | Ana |
| [lucía] | Lucía |

## 📏 Criterios de evaluación de la solución

- El nombre queda correcto en los tres casos, incluido uno de una sola palabra y uno con espacios
  extra en los dos lados.
- Se usa `trim`, `substring` y `toUpperCase`/`toLowerCase` (o una combinación equivalente), guardando
  siempre el resultado de cada método.
- El programa compila y produce el formato de salida indicado.

## 🚧 Restricciones

- No cambies los datos de entrada.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-6**: transformar una cadena con `trim`, `toUpperCase`/`toLowerCase` y combinarlos.
- **RA-14**: comprobar la regla con varios casos de prueba.
