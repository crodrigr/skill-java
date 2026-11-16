# 🟡 Intermedio 04 — Listado con StringBuilder

## 🧩 Problema

**MediSalud** quiere armar un listado de pacientes citados hoy. Completa el programa de partida:
reemplaza el `TODO` para agregar los tres pacientes al `StringBuilder`, uno por línea, con un guion
delante de cada nombre.

## 💻 Código o contexto de partida

```java
public class ListadoConBuilder {

    public static void main(String[] args) {
        // Datos de entrada
        String pacienteA = "Ana Torres";
        String pacienteB = "Bruno Peña";
        String pacienteC = "Carla Ruiz";

        StringBuilder listado = new StringBuilder();
        // TODO: agrega los tres pacientes al listado, uno por línea, con un guion delante

        System.out.print(listado);
    }
}
```

## 🧪 Casos de prueba

Con un solo paciente en el listado:

```text
- Ana Torres
```

## 📏 Criterios de evaluación de la solución

- El listado muestra los tres pacientes, uno por línea, con el formato indicado.
- Se usa `.append(...)` encadenado o en líneas separadas; ninguna concatenación con `+=` sobre
  `String`.
- El programa compila y produce el listado completo.

## 🚧 Restricciones

- Usa `StringBuilder`, no concatenación de `String`.
- No cambies los datos de entrada.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-13**: construir texto con `StringBuilder` dentro de una repetición.
- **RA-14**: comprobar la solución con un caso de un solo elemento.
