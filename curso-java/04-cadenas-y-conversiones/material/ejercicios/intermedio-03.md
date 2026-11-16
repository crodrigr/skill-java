# 🟡 Intermedio 03 — Validar y convertir un importe

## 🧩 Problema

**MediSalud** recibe la cantidad de días de préstamo de un equipo médico escrita como texto. Completa
el programa de partida: reemplaza el `TODO` con la validación de cada carácter (con
`Character.isDigit`) antes de convertir con `Integer.parseInt`. El programa ya compila, pero por ahora
convierte sin validar.

## 💻 Código o contexto de partida

```java
public class ValidarImporte {

    public static void main(String[] args) {
        // Datos de entrada
        String diasPrestamoTexto = "15";

        boolean esValido = true;
        // TODO: valida cada carácter con Character.isDigit; si es válido, conviértelo con Integer.parseInt

        if (esValido) {
            int dias = Integer.parseInt(diasPrestamoTexto);
            System.out.println("Días de préstamo: " + dias);
        } else {
            System.out.println("El valor '" + diasPrestamoTexto + "' no es válido");
        }
    }
}
```

## 🧪 Casos de prueba

| Texto | Resultado esperado |
|---|---|
| [] | El valor '' no es válido |
| [15] | Días de préstamo: 15 |
| [15 dias] | El valor '15 dias' no es válido |

## 📏 Criterios de evaluación de la solución

- El texto vacío y el texto con letras se rechazan **sin que el programa se detenga**.
- El texto numérico válido se convierte correctamente.
- La validación revisa **todos** los caracteres del texto, no solo el primero.

## 🚧 Restricciones

- No uses `try`/`catch`: valida antes de convertir.
- No cambies los datos de entrada del programa principal; solo agrega la validación.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-11**: validar antes de convertir texto a número.
- **RA-14**: comprobar la validación con texto vacío, válido e inválido.
