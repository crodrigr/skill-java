# 🟡 Intermedio 03 — Migrar un arreglo a ArrayList

## 🧩 Problema

**MediSalud** registra las citas del día en un arreglo de tamaño fijo.

## 💻 Código o contexto de partida

```java
package com.medisalud;

public class Demo {
    public static void main(String[] args) {
        String[] citas = new String[3];
        citas[0] = "Ana Torres";
        citas[1] = "Luis Peña";
        citas[2] = "Marta Salinas";

        // TODO: llegó un cuarto paciente sin cita previa, pero el arreglo ya está lleno

        for (String cita : citas) {
            System.out.println(cita);
        }
    }
}
```

**Tarea**: llegó un cuarto paciente sin cita previa, pero el arreglo `String[3]` ya está lleno y no puede
crecer. Migra `citas` a un `ArrayList<String>` y agrega al cuarto paciente, `"Carlos Ibáñez"`.

## 🧪 Casos de prueba

| Salida esperada |
|---|
| `Ana Torres` |
| `Luis Peña` |
| `Marta Salinas` |
| `Carlos Ibáñez` |
| `4` |

## 📏 Criterios de evaluación de la solución

- `citas` pasa de `String[3]` a `List<String>` (implementada con `ArrayList`).
- Los tres pacientes originales se agregan en el mismo orden, más `"Carlos Ibáñez"`.
- El programa imprime los cuatro nombres (recorrido con `for-each`) y luego `citas.size()`.

## 🚧 Restricciones

- No cambies el orden de los tres pacientes originales.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-10**: migrar un arreglo a una `List`.
- **RA-15**: reconocer cuándo un arreglo de tamaño fijo no alcanza y una `List` es la alternativa
  correcta.
