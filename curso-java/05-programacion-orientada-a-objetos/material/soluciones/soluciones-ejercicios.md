# 🔑 Soluciones — Ejercicios Módulo 5

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/05-programacion-orientada-a-objetos.md`).

## 🟢 Básico 01 — Distinguir clase de objeto

`Sala` es la **clase** (el diseño): declara que toda sala tendrá un `nombre` y una `capacidad`, sin fijar
ningún valor. `sala1` y `sala2` son **objetos**: dos instancias distintas creadas con `new Sala()`, cada
una con sus propios valores.

Ventaja de usar una clase: evita declarar `nombreSala1`, `capacidadSala1`, `nombreSala2`,
`capacidadSala2` como variables sueltas; agregar una tercera sala es crear un tercer objeto, no declarar
dos variables más.

Salida real del programa:

```text
Consultorio 1 - capacidad: 2
Sala de espera - capacidad: 15
```

## 🟡 Intermedio 01 — Declarar una clase con atributos y un método

```java
public class UsuarioBiblioteca {
    public String nombreUsuario;
    public int prestamosActivos;

    public boolean tieneMultaPendiente() {
        return prestamosActivos > 3;
    }

    public static void main(String[] args) {
        UsuarioBiblioteca usuario = new UsuarioBiblioteca();
        usuario.nombreUsuario = "Carlos Ramirez";
        usuario.prestamosActivos = 2;
        System.out.println(usuario.nombreUsuario + " tiene multa pendiente: " + usuario.tieneMultaPendiente());
    }
}
```

Salida real del programa:

```text
Carlos Ramirez tiene multa pendiente: false
```

## 🟢 Básico 02 — Predecir la salida de invocar un método

Salida real del programa:

```text
El principito: 3 días restantes
```

## 🟢 Básico 03 — ¿Estático o de instancia?

`mostrarHorarioDeAtencion()` es estático: se invoca `ReglamentoBiblioteca.mostrarHorarioDeAtencion();`,
sin crear ningún objeto, porque no depende de ningún dato particular. `mostrarSaludoPersonalizado()` es
de instancia: se invoca `usuario.mostrarSaludoPersonalizado();`, sobre un objeto ya creado, porque usa
el atributo `nombreUsuario` de ese objeto.

Salida real del programa:

```text
Horario: 9am a 8pm, de lunes a viernes
Hola, Lucía Gómez
```

## 🟢 Básico 04 — Qué inicializa el constructor

`cita.nombrePaciente` vale `"Ana Torres"` y `cita.especialidad` vale `"Pediatría"`: el constructor
asigna los argumentos en el mismo orden en que se declararon los parámetros.

Salida real del programa:

```text
Ana Torres - Pediatría
```

## 🟢 Básico 05 — El objeto que vale null

`buscarPorHistoria("HC-2026-000999")` no encuentra ninguna coincidencia y devuelve `null`.
`paciente.saludar()` se invoca sobre ese `null` sin comprobarlo antes, así que el programa se detiene
con `NullPointerException`. El panel Problems no marca nada porque el `null` llega de una búsqueda, no
de una asignación obvia. Corrección: comprobar `if (paciente != null)` antes de invocar el método.

```text
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "ObjetoQueValeNull.saludar()" because "<local1>" is null
```

## 🟡 Intermedio 02 — Agregar un constructor

```java
public class Cita {
    public int anio;
    public int numero;
    public String nombrePaciente;

    public Cita(int anio, int numero, String nombrePaciente) {
        this.anio = anio;
        this.numero = numero;
        this.nombrePaciente = nombrePaciente;
    }

    public static void main(String[] args) {
        Cita cita = new Cita(2026, 42, "Ana Torres");
        System.out.println("CIT-" + cita.anio + "-" + cita.numero + " - " + cita.nombrePaciente);
    }
}
```

Salida real del programa:

```text
CIT-2026-42 - Ana Torres
```

## 🟡 Intermedio 03 — Varios objetos, independencia

```java
public class Consulta {
    public String nombrePaciente;
    public double saldoPendiente;

    public Consulta(String nombrePaciente, double saldoPendiente) {
        this.nombrePaciente = nombrePaciente;
        this.saldoPendiente = saldoPendiente;
    }

    public void pagar(double monto) {
        saldoPendiente = saldoPendiente - monto;
    }

    public static void main(String[] args) {
        Consulta consulta1 = new Consulta("Ana Torres", 50000.0);
        consulta1.pagar(20000.0);
        System.out.println(consulta1.nombrePaciente + ": saldo " + consulta1.saldoPendiente);

        Consulta consulta2 = new Consulta("Luis Gómez", 30000.0);
        consulta2.pagar(30000.0);
        System.out.println(consulta1.nombrePaciente + ": saldo " + consulta1.saldoPendiente);
        System.out.println(consulta2.nombrePaciente + ": saldo " + consulta2.saldoPendiente);
    }
}
```

Salida real del programa:

```text
Ana Torres: saldo 30000.0
Ana Torres: saldo 30000.0
Luis Gómez: saldo 0.0
```

## 🔴 Avanzado 01 — Corregir un programa con errores de objetos

**Ronda 1 (compilación).** `RevisionDePrestamos.mostrarDatosUsuario();` invoca un método de instancia
como si fuera estático:

```text
✖ Cannot make a static reference to the non-static method mostrarDatosUsuario() from the type RevisionDePrestamos Java(603979977) [Ln 24, Col 9]
```

Corrección: crear un objeto e invocar el método sobre él.

**Ronda 2 (advertencia del panel).** `usuario.mostrarReglamento();` invoca un método estático sobre un
objeto. Compila y da la misma salida, pero el panel marca una advertencia:

```text
⚠ The static method mostrarReglamento() from the type RevisionDePrestamos should be accessed in a static way Java(603979893) [Ln 27, Col 9]
```

Corrección: invocarlo sobre la clase, `RevisionDePrestamos.mostrarReglamento();`.

**Ronda 3 (falla en ejecución).** `buscarUsuarioPorId("U-999")` no encuentra el usuario y devuelve
`null`; invocar `mostrarDatosUsuario()` sobre ese resultado detiene el programa:

```text
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.biblioteca.RevisionDePrestamos.mostrarDatosUsuario()" because "<local1>" is null
```

Corrección: buscar un identificador que sí exista (`"U-001"`), o comprobar el resultado antes de usarlo.

Programa corregido:

```java
package com.biblioteca;

public class RevisionDePrestamos {
    public String nombreUsuario;

    public static void mostrarReglamento() {
        System.out.println("Reglamento: máximo 3 préstamos activos por usuario.");
    }

    public void mostrarDatosUsuario() {
        System.out.println("Usuario: " + nombreUsuario);
    }

    static RevisionDePrestamos buscarUsuarioPorId(String id) {
        if (id.equals("U-001")) {
            RevisionDePrestamos usuario = new RevisionDePrestamos();
            usuario.nombreUsuario = "Carlos Ramírez";
            return usuario;
        }
        return null;
    }

    public static void main(String[] args) {
        RevisionDePrestamos.mostrarReglamento();

        RevisionDePrestamos usuario = buscarUsuarioPorId("U-001");
        usuario.mostrarDatosUsuario();
    }
}
```

```text
Reglamento: máximo 3 préstamos activos por usuario.
Usuario: Carlos Ramírez
```

## 🏆 Desafío 01 — Diseñar una clase nueva con salida exacta

```java
package com.biblioteca;

public class Prestamo {
    public String tituloLibro;
    public String nombreUsuario;
    public int diasRestantes;

    public Prestamo(String tituloLibro, String nombreUsuario, int diasRestantes) {
        this.tituloLibro = tituloLibro;
        this.nombreUsuario = nombreUsuario;
        this.diasRestantes = diasRestantes;
    }

    public boolean estaVencido() {
        return diasRestantes < 0;
    }

    public void mostrarEstado() {
        String estado;
        if (estaVencido()) {
            estado = "vencido";
        } else if (diasRestantes == 0) {
            estado = "vence hoy";
        } else {
            estado = diasRestantes + " días restantes";
        }
        System.out.println(tituloLibro + " (" + nombreUsuario + "): " + estado);
    }
}
```

```java
package com.biblioteca;

public class DemoPrestamo {

    public static void main(String[] args) {
        Prestamo prestamo1 = new Prestamo("Cien años de soledad", "Ana Torres", 5);
        Prestamo prestamo2 = new Prestamo("El principito", "Carlos Ramírez", 0);
        Prestamo prestamo3 = new Prestamo("Rayuela", "Lucía Gómez", -2);

        prestamo1.mostrarEstado();
        prestamo2.mostrarEstado();
        prestamo3.mostrarEstado();
    }
}
```

```text
Cien años de soledad (Ana Torres): 5 días restantes
El principito (Carlos Ramírez): vence hoy
Rayuela (Lucía Gómez): vencido
```
