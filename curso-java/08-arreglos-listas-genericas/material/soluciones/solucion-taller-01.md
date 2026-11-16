# 🔑 Solución — Taller 01: De arreglo a lista: empleados de MediSalud

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección "Soluciones"
de `specs/08-arreglos-listas-genericas.md`).

## 📦 Árbol de archivos final

```text
DeArregloAArrayList
└── src
    └── com
        └── medisalud
            ├── Empleado.java
            ├── Medico.java
            ├── Enfermero.java
            ├── Demo.java
            └── DemoArrayList.java
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

## 💻 Archivo: Demo.java (versión con arreglo)

```java
package com.medisalud;

public class Demo {
    public static void main(String[] args) {
        Empleado[] equipo = new Empleado[3];
        equipo[0] = new Medico("Ana Torres", "Cardiología");
        equipo[1] = new Enfermero("Luis Peña", 4);
        equipo[2] = new Medico("Marta Salinas", "Pediatría");

        double total = 0.0;
        for (Empleado empleado : equipo) {
            System.out.println(empleado.getNombreCompleto() + ": " + empleado.calcularSueldo());
            total += empleado.calcularSueldo();
        }
        System.out.println("Total: " + total);

        // TODO: llegó un cuarto empleado, pero el arreglo Empleado[3] ya está lleno; migra
        // "equipo" a un ArrayList<Empleado> y agrégalo
    }
}
```

## ✅ Salida real (versión con arreglo)

```text
Ana Torres: 1500.0
Luis Peña: 1200.0
Marta Salinas: 1500.0
Total: 4200.0
```

## 💻 Archivo: DemoArrayList.java (versión migrada)

```java
package com.medisalud;

import java.util.ArrayList;
import java.util.List;

public class DemoArrayList {
    public static void main(String[] args) {
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Medico("Ana Torres", "Cardiología"));
        equipo.add(new Enfermero("Luis Peña", 4));
        equipo.add(new Medico("Marta Salinas", "Pediatría"));
        equipo.add(new Enfermero("Carlos Ibáñez", 3));

        double total = 0.0;
        for (Empleado empleado : equipo) {
            System.out.println(empleado.getNombreCompleto() + ": " + empleado.calcularSueldo());
            total += empleado.calcularSueldo();
        }
        System.out.println("Total: " + total);
    }
}
```

## ✅ Salida real (versión migrada)

```text
Ana Torres: 1500.0
Luis Peña: 1200.0
Marta Salinas: 1500.0
Carlos Ibáñez: 1150.0
Total: 5350.0
```

## 🔍 Errores comunes observados y su corrección

**Intentar agregar un cuarto empleado al arreglo** (`equipo[3] = ...`): ni el compilador ni el panel
Problems lo detectan (el índice es válido como expresión), pero el programa termina con una
`ArrayIndexOutOfBoundsException` real al ejecutarse:

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
```

Corrección: migrar a `ArrayList<Empleado>`, que crece automáticamente con `add(...)`.

- **Olvidar `import java.util.ArrayList;`** (y `import java.util.List;`) al migrar: el compilador
  rechaza `ArrayList` y `List` como símbolos desconocidos. Corrección: agregar ambos imports antes de
  declarar la variable.
- **Declarar la variable migrada como `ArrayList<Empleado>` en vez de `List<Empleado>`**: el programa
  compila igual (relación de subtipo válida), pero se aparta de la convención de este módulo de
  declarar con el tipo de la interfaz (Ejemplo 08).
