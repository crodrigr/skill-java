# 🔑 Solución — Taller 01: Jerarquía encapsulada de empleados

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección "Soluciones"
de `specs/07-herencia-y-polimorfismo.md`).

## 📦 Árbol de archivos final

```text
JerarquiaDeEmpleados
└── src
    └── com
        └── medisalud
            ├── Empleado.java
            ├── Medico.java
            ├── Enfermero.java
            └── Demo.java
```

## 💻 Archivo: Empleado.java

```java
package com.medisalud;

public abstract class Empleado {
    protected String nombreCompleto;

    public Empleado(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public abstract double calcularSueldo();
}
```

## 💻 Archivo: Medico.java

```java
package com.medisalud;

public class Medico extends Empleado {
    private String especialidad;

    public Medico(String nombreCompleto, String especialidad) {
        super(nombreCompleto);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public double calcularSueldo() {
        return 1500.0;
    }
}
```

## 💻 Archivo: Enfermero.java

```java
package com.medisalud;

public class Enfermero extends Empleado {
    private int turnosPorSemana;

    public Enfermero(String nombreCompleto, int turnosPorSemana) {
        super(nombreCompleto);
        this.turnosPorSemana = turnosPorSemana;
    }

    public int getTurnosPorSemana() {
        return turnosPorSemana;
    }

    @Override
    public double calcularSueldo() {
        return 1000.0 + turnosPorSemana * 50.0;
    }
}
```

## 💻 Archivo: Demo.java

```java
package com.medisalud;

public class Demo {
    public static void main(String[] args) {
        Empleado empleado1 = new Medico("Laura Gómez", "Cardiología");
        Empleado empleado2 = new Enfermero("Pedro Salas", 5);

        System.out.println(empleado1.getNombreCompleto() + ": " + empleado1.calcularSueldo());
        System.out.println(empleado2.getNombreCompleto() + ": " + empleado2.calcularSueldo());
    }
}
```

## ✅ Salida real

```text
Laura Gómez: 1500.0
Pedro Salas: 1250.0
```

## 🔍 Errores comunes observados y su corrección

- **Instanciar `Empleado` directamente** (`new Empleado(...)`): el compilador lo rechaza porque
  `Empleado` es `abstract`. Corrección: siempre instanciar una subclase concreta (`Medico` o
  `Enfermero`).
- **Olvidar `@Override`** en `calcularSueldo()` de `Medico`/`Enfermero`: el programa compila igual (la
  anotación no es obligatoria si la firma coincide), pero sin ella el compilador no avisa si la firma
  llegara a no coincidir por error. Corrección: usar siempre `@Override` al sobreescribir.
- **Olvidar `super(nombreCompleto)`** en el constructor de `Medico`/`Enfermero`: el compilador lo
  rechaza porque `Empleado` no tiene un constructor sin parámetros. Corrección: invocar `super(...)`
  como primera línea del constructor de la subclase.
