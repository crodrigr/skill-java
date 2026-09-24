# 🟡 Intermedio 02 — Declarar y operar un Map

## 🧩 Problema

MediSalud necesita un listado de pacientes por número de historia clínica, que **siempre** tiene que
mostrarse ordenado por historia clínica:

## 💻 Código o contexto de partida

```java
package com.medisalud;

// TODO: declarar un Map (HashMap o TreeMap, segun corresponda) con historia clinica -> nombre de
// paciente, y agregar los pares dados. El listado final debe quedar ordenado por historia clinica.
public class Demo {
    public static void main(String[] args) {
        System.out.println("Pendiente de completar");
    }
}
```

Completa el programa: declara un `Map<String, String>` que asocie cada historia clínica con el nombre
del paciente, agrega los pares `"HC-1002" → "Jorge Paz"`, `"HC-1001" → "Marta Diaz"` y
`"HC-1003" → "Ana Torres"` (en ese orden de inserción), y recorrelo imprimiendo cada par.

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| Los tres pares agregados en el orden dado | Recorrido con `entrySet()` | `HC-1001`, `HC-1002`, `HC-1003`, en ese orden (por historia clínica, no por orden de inserción) |

## 📏 Criterios de evaluación de la solución

- Elige `TreeMap` (no `HashMap`), justificando que el enunciado exige orden por clave.
- El recorrido queda ordenado por historia clínica, sin importar el orden en que se agregaron los pares.

## 🚧 Restricciones

- No se usa `Set` ni `enum` (temas de otros puntos del módulo).
- No se usa `try`/`catch`.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-5**: declarar y usar un `Map`.
- **RA-6**: usar un `TreeMap`.
- **RA-7**: elegir entre `HashMap` y `TreeMap` según si se necesita orden.
