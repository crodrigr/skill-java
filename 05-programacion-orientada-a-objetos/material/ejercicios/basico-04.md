# 🟢 Básico 04 — Qué inicializa el constructor

## 🧩 Problema

**MediSalud** tiene la siguiente clase y la crea con el constructor indicado. Predice los valores de los
atributos del objeto creado, antes de ejecutar.

## 💻 Código o contexto de partida

```java
public class Cita {
    public String nombrePaciente;
    public String especialidad;

    public Cita(String nombrePaciente, String especialidad) {
        this.nombrePaciente = nombrePaciente;
        this.especialidad = especialidad;
    }
}
```

```java
public class QueInicializaElConstructor {
    public static void main(String[] args) {
        Cita cita = new Cita("Ana Torres", "Pediatría");
        System.out.println(cita.nombrePaciente + " - " + cita.especialidad);
    }
}
```

## 📏 Criterios de evaluación de la solución

- Predice correctamente los valores de `nombrePaciente` y `especialidad` del objeto `cita`.
- Explica que el constructor recibe los argumentos en el mismo orden en que se declararon los
  parámetros.

## 🚧 Restricciones

- No modifiques el código: solo predice sus valores.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-9**: crear un objeto con `new`.
- **RA-10**: declarar un constructor con parámetros.
