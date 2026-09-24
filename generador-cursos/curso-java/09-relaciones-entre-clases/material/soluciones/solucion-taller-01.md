# 🔑 Solución — Taller 01

> Material docente, no enlazar desde la audiencia estudiante.

## 📦 Árbol de archivos

```text
RelacionesMediSalud/
└── src/
    └── com/
        └── medisalud/
            ├── Especialidad.java
            ├── HistoriaClinica.java
            ├── Medico.java
            ├── Paciente.java
            └── Demo.java
```

## 💻 Archivo: Especialidad.java

```java
package com.medisalud;

public class Especialidad {
    private String nombre;

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

## 💻 Archivo: HistoriaClinica.java

```java
package com.medisalud;

public class HistoriaClinica {
    private int numeroHistoria;
    private String observaciones;

    public HistoriaClinica(int numeroHistoria) {
        this.numeroHistoria = numeroHistoria;
        this.observaciones = "Sin observaciones registradas";
    }

    public int getNumeroHistoria() {
        return numeroHistoria;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
```

## 💻 Archivo: Medico.java

```java
package com.medisalud;

import java.util.ArrayList;
import java.util.List;

public class Medico {
    private String nombre;
    private Especialidad especialidad;
    private List<Paciente> pacientes = new ArrayList<>();

    public Medico(String nombre, Especialidad especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        paciente.setMedico(this);
    }

    public String getNombre() {
        return nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}
```

## 💻 Archivo: Paciente.java

```java
package com.medisalud;

public class Paciente {
    private String nombre;
    private Medico medico;
    private HistoriaClinica historiaClinica;

    public Paciente(String nombre, int numeroHistoria) {
        this.nombre = nombre;
        this.historiaClinica = new HistoriaClinica(numeroHistoria);
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Medico getMedico() {
        return medico;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public String getNombre() {
        return nombre;
    }
}
```

## 💻 Archivo: Demo.java

```java
package com.medisalud;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Especialidad cardiologia = new Especialidad("Cardiologia");
        Medico medico1 = new Medico("Ana Torres", cardiologia);
        Medico medico2 = new Medico("Carlos Ruiz", cardiologia);

        Paciente paciente1 = new Paciente("Marta Diaz", 1001);
        Paciente paciente2 = new Paciente("Jorge Paz", 1002);

        medico1.agregarPaciente(paciente1);
        medico2.agregarPaciente(paciente2);

        for (Medico medico : new Medico[] { medico1, medico2 }) {
            System.out.println(medico.getNombre() + " (" + medico.getEspecialidad().getNombre() + ") tiene "
                    + medico.getPacientes().size() + " paciente(s)");
            for (Paciente paciente : medico.getPacientes()) {
                System.out.println("  - " + paciente.getNombre() + " -> medico: " + paciente.getMedico().getNombre()
                        + " | Historia N" + paciente.getHistoriaClinica().getNumeroHistoria() + ": "
                        + paciente.getHistoriaClinica().getObservaciones());
            }
        }
    }
}
```

## ✅ Salida real

```text
Ana Torres (Cardiologia) tiene 1 paciente(s)
  - Marta Diaz -> medico: Ana Torres | Historia N1001: Sin observaciones registradas
Carlos Ruiz (Cardiologia) tiene 1 paciente(s)
  - Jorge Paz -> medico: Carlos Ruiz | Historia N1002: Sin observaciones registradas
```

## 🔍 Errores comunes observados

- Llamar a `paciente.setMedico(medico)` directamente en vez de `medico.agregarPaciente(paciente)`: dejaría
  `medico.getPacientes()` sin ese paciente (mismo error lógico del Ejemplo 04).
- Agregar un constructor de `Paciente` que reciba una `HistoriaClinica` por parámetro: rompería la
  composición y permitiría que dos `Paciente` terminaran compartiendo la misma `HistoriaClinica`.
- Olvidar inicializar `pacientes` como una lista vacía en `Medico` (`List<Paciente> pacientes = new
  ArrayList<>();`), causando un `NullPointerException` en el primer `agregarPaciente`.
