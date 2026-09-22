# 🔑 Solución — Taller 01: Ficha ampliada de un paciente

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/05-programacion-orientada-a-objetos.md`).

## 🌳 Árbol de archivos del proyecto final

```text
FichaAmpliadaDePaciente
└── src
    └── com
        └── medisalud
            ├── Paciente.java
            └── DemoPaciente.java
```

## 💻 Archivo: Paciente.java

```java
package com.medisalud;

public class Paciente {
    public String nombreCompleto;
    public int edad;
    public String historiaClinica;
    public String planCobertura;

    public Paciente(String nombreCompleto, int edad, String historiaClinica, String planCobertura) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.historiaClinica = historiaClinica;
        this.planCobertura = planCobertura;
    }

    public void mostrarFicha() {
        System.out.println("Paciente: " + nombreCompleto);
        System.out.println("Edad: " + edad);
        System.out.println("Historia clínica: " + historiaClinica);
        System.out.println("Plan: " + planCobertura);
    }

    public boolean esMayorDeEdad() {
        return edad >= 18;
    }

    public boolean tienePlanPremium() {
        return planCobertura.equalsIgnoreCase("premium");
    }
}
```

## 💻 Archivo: DemoPaciente.java

```java
package com.medisalud;

public class DemoPaciente {

    public static void main(String[] args) {
        Paciente paciente1 = new Paciente("Ana Torres", 34, "HC-2026-000123", "Premium");
        paciente1.mostrarFicha();
        System.out.println("Es mayor de edad: " + paciente1.esMayorDeEdad());
        System.out.println("Tiene plan premium: " + paciente1.tienePlanPremium());
        System.out.println("---");

        Paciente paciente2 = new Paciente("Luis Gómez", 15, "HC-2026-000456", "Básico");
        paciente2.mostrarFicha();
        System.out.println("Es mayor de edad: " + paciente2.esMayorDeEdad());
        System.out.println("Tiene plan premium: " + paciente2.tienePlanPremium());
        System.out.println("---");

        Paciente paciente3 = new Paciente("Lucía Fernández", 70, "HC-2026-000789", "premium");
        paciente3.mostrarFicha();
        System.out.println("Es mayor de edad: " + paciente3.esMayorDeEdad());
        System.out.println("Tiene plan premium: " + paciente3.tienePlanPremium());
    }
}
```

## ✅ Resultado esperado

```text
Paciente: Ana Torres
Edad: 34
Historia clínica: HC-2026-000123
Plan: Premium
Es mayor de edad: true
Tiene plan premium: true
---
Paciente: Luis Gómez
Edad: 15
Historia clínica: HC-2026-000456
Plan: Básico
Es mayor de edad: false
Tiene plan premium: false
---
Paciente: Lucía Fernández
Edad: 70
Historia clínica: HC-2026-000789
Plan: premium
Es mayor de edad: true
Tiene plan premium: true
```

## 🧭 Explicación paso a paso

1. El constructor recibe los cuatro atributos como parámetros y los asigna con `this` porque comparten
   nombre con los parámetros.
2. `mostrarFicha()` imprime los cuatro atributos del objeto que la invoca.
3. `esMayorDeEdad()` reutiliza exactamente la misma lógica de los Ejemplos 06 y 07 (`edad >= 18`).
4. `tienePlanPremium()` usa `equalsIgnoreCase` (Módulo 4) para no distinguir mayúsculas de minúsculas.
5. Los tres objetos se crean con `new Paciente(...)` y datos distintos; ninguno comparte estado con los
   demás.

## 🐞 Errores comunes observados

| Error | Tipo | Cómo se ve | Corrección |
|---|---|---|---|
| Invocar un método de instancia como si fuera estático | de compilación | `Cannot make a static reference to the non-static method mostrarFicha() from the type Paciente` en el panel Problems | invocarlo sobre un objeto: `paciente1.mostrarFicha();` |
| Olvidar `new` al crear un objeto | de compilación | `variable paciente1 might not have been initialized` | usar `Paciente paciente1 = new Paciente(...);` |
| Olvidar `this` en el constructor cuando el parámetro comparte nombre con el atributo | lógico | el atributo queda con su valor por defecto (`null` o `0`), aunque se haya pasado un valor real; el panel Problems no marca ningún problema | escribir `this.atributo = atributo;` en cada línea del constructor |
| Invocar un método estático sobre un objeto (por ejemplo, si `mostrarHorarioGeneral()` existiera aquí) | lógico | compila y funciona igual, pero el panel Problems sí marca una **advertencia** (`should be accessed in a static way`) | invocarlo sobre la clase |
