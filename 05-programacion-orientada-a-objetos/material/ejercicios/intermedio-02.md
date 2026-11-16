# 🟡 Intermedio 02 — Agregar un constructor

## 🧩 Problema

**MediSalud** tiene la clase `Cita`, sin constructor propio. Completa el `TODO` agregando un constructor
que inicialice sus tres atributos.

## 💻 Código o contexto de partida

```java
public class Cita {
    public int anio;
    public int numero;
    public String nombrePaciente;

    // TODO: 1. declara el constructor que reciba anio, numero y nombrePaciente, usando this

    public static void main(String[] args) {
        // TODO: 2. crea un objeto Cita con el constructor y muestra sus tres atributos
    }
}
```

## 🧪 Casos de prueba

```text
CIT-2026-42 - Ana Torres
```

## 📏 Criterios de evaluación de la solución

- El constructor recibe `anio`, `numero` y `nombrePaciente`, en ese orden.
- Usa `this` para distinguir cada atributo de su parámetro correspondiente.
- El objeto se crea con `new Cita(...)`, sin asignar los atributos por punto después.

## 🚧 Restricciones

- No cambies los nombres de los atributos.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-10**: declarar un constructor con parámetros.
