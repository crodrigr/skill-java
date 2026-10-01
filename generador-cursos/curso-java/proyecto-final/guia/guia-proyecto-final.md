# 📘 Proyecto final — Sistema de Gestión de Citas Médicas MediSalud

## 🎯 Objetivo

Construir, paso a paso y en paralelo con los Módulos 1 a 20 del curso, una aplicación de
consola en Java que integra todo lo aprendido: variables, decisiones y bucles, cadenas,
programación orientada a objetos, colecciones, relaciones entre clases, SOLID, patrones
de diseño, concurrencia, archivos, JDBC con arquitectura Modelo-Vista-Controlador,
lambdas y Stream API.

## 🌍 Contexto

MediSalud, la red de clínicas que ya conocés de los ejemplos del curso, necesita un
sistema de consola para gestionar las citas médicas de sus pacientes: registrar
pacientes y médicos, agendar y dar seguimiento a sus citas, mantener la historia clínica
de cada paciente, calcular y facturar el costo de una consulta, notificar a los
pacientes, y guardar todo de forma que no se pierda al cerrar el programa — primero en
archivos, y más adelante en una base de datos.

## ✅ Prerrequisitos

- Haber completado los Módulos 1 a 20 del Curso de Java.
- Tener instalado el JDK 17 o superior.
- Tener instalado Visual Studio Code con la Extension Pack for Java.
- A partir del Paso 18, tener disponible un servidor MySQL local (la misma dependencia
  que ya exige el propio Módulo 18 del curso).

## 🧱 Resultado final

Un proyecto de Visual Studio Code (`SistemaMediSaludFinal`) con **un solo código**: una
aplicación de consola completa, organizada en capas bajo el paquete `com.medisalud`, con
un menú interactivo real, que persiste sus datos en una base de datos MySQL vía JDBC,
aplica patrones de diseño sobre problemas reales del propio proyecto, ejecuta una tarea
en un hilo separado, y resuelve dos recorridos con lambdas y Stream API — exactamente el
mismo código que encontrarás ya ensamblado en `../solucion/`.

## 🌳 Árbol de archivos del proyecto

Así se ve el proyecto completo al terminar el Paso 20 — un único proyecto Java
(`SistemaMediSaludFinal`), con un único punto de entrada (`Principal`), organizado en
capas por responsabilidad. Cada paso de esta guía agrega, de a una, las carpetas y
archivos marcados con el módulo que los introduce:

```text
com.medisalud/
├── Principal.java                              # unico punto de entrada (Paso 1, actualizado en cada paso)
├── entity/                                      # Modulos 5, 7, 9, 10, 13
│   ├── Persona.java
│   ├── Paciente.java
│   ├── Medico.java
│   ├── Cita.java
│   ├── EstadoCita.java
│   ├── HistoriaClinica.java
│   └── Factura.java
├── exception/                                   # Modulo 10
│   ├── PacienteNoEncontradoException.java
│   ├── MedicoNoEncontradoException.java
│   ├── CitaNoEncontradaException.java
│   └── TransicionInvalidaException.java
├── repository/                                   # Modulos 8, 11, 16
│   ├── RepositorioPacientes.java
│   ├── RepositorioMedicos.java
│   ├── RepositorioCitas.java
│   ├── RepositorioPacientesMemoria.java
│   ├── RepositorioMedicosMemoria.java
│   └── RepositorioCitasMemoria.java
├── service/                                      # Modulos 9, 10, 11, 14, 19, 20
│   ├── ServicioPacientes.java
│   ├── ServicioMedicos.java
│   ├── ServicioCitas.java
│   └── ServicioFacturacion.java
├── patron/
│   ├── creacional/                               # Modulo 12
│   │   ├── ConstructorHistoriaClinica.java
│   │   └── GestorClinica.java
│   ├── estructural/                              # Modulos 13, 14
│   │   ├── FacturaConRecargoNocturno.java
│   │   ├── FacturaConDescuentoAfiliado.java
│   │   └── FachadaAgendamiento.java
│   └── comportamiento/                           # Modulos 14, 15
│       ├── EstrategiaCosto.java
│       ├── EstrategiaCostoConsultaGeneral.java
│       ├── EstrategiaCostoConsultaEspecialista.java
│       ├── ObservadorCita.java
│       └── ObservadorCitaNotificacion.java
├── concurrencia/                                 # Modulos 15, 16
│   ├── HiloNotificaciones.java
│   └── ContadorCodigos.java
├── persistencia/
│   ├── archivo/                                  # Modulo 17
│   │   ├── AlmacenPacientesArchivo.java
│   │   └── AlmacenCitasSerializado.java
│   └── jdbc/                                      # Modulo 18
│       ├── ConexionBD.java
│       ├── PacienteDAO.java
│       ├── MedicoDAO.java
│       └── CitaDAO.java
├── controlador/                                   # Modulo 18
│   └── ControladorMediSalud.java
└── vista/                                         # Modulo 18
    └── VistaConsola.java
```

Dos clases transitorias (`Repositorio<T>` del Paso 8 y una versión temprana de
`ObservadorCitaNotificacion`) se reemplazan antes de llegar a este árbol final — la
guía lo explica en el momento en que ocurre cada reemplazo.

## 📐 Alcance del proyecto

- Aplicación de **consola** (sin interfaz gráfica) para la gestión de citas médicas de
  MediSalud: pacientes, médicos, citas, historias clínicas y facturación.
- Persistencia progresiva: en memoria (Pasos 8-16), en archivo de texto y serializado
  (Paso 17), y finalmente en una base de datos MySQL vía JDBC (Paso 18 en adelante).
- Un **único dominio de negocio** (MediSalud); no se usa Biblioteca Universitaria en
  este proyecto.
- Sin frameworks externos al temario del curso (sin Spring, sin un ORM): JDBC puro.
- Un único concepto fuera de los Módulos 1-20: `Scanner`, introducido únicamente en
  `VistaConsola` (Paso 18) para el menú interactivo — ver la explicación de ese paso.

## ⚙️ Funcionalidades

El resultado final ofrece un menú interactivo (`VistaConsola`) con estas operaciones,
en bucle, hasta elegir "Salir":

1. **Registrar paciente** — nombre, código y edad; opcionalmente antecedentes y
   alergias (construidos con el patrón Builder).
2. **Registrar médico** — nombre, código y especialidad.
3. **Agendar cita** — código de cita, paciente, médico y fecha; permite confirmarla y
   registrar su atención (con diagnóstico) en el mismo flujo.
4. **Consultar historia clínica** — por código de paciente.
5. **Facturar consulta** — por código de cita, con estrategia de costo (general o
   especialista) y recargos/descuentos opcionales (patrón Decorator).
6. **Generar reporte** — citas de un médico, total facturado por médico (Stream API) y
   pacientes a partir de una edad mínima (lambda + Stream API).
7. **Salir** — detiene el hilo de notificaciones de forma ordenada y cierra el programa.

## 📏 Reglas de negocio

- La edad de un paciente no puede ser negativa; el nombre completo y la especialidad
  tampoco pueden estar vacíos.
- Una `Cita` nace en estado `PENDIENTE` y solo puede seguir las transiciones válidas:
  `PENDIENTE → CONFIRMADA` o `CANCELADA`; `CONFIRMADA → ATENDIDA` o `CANCELADA`;
  `ATENDIDA` y `CANCELADA` son estados finales. Cualquier otra transición se rechaza.
- Buscar un paciente, médico o cita por un código inexistente nunca detiene el
  programa: siempre se informa con un mensaje claro.
- El acceso al repositorio compartido está protegido para el caso de hilos
  concurrentes; las notificaciones de cambio de estado de una cita se procesan en un
  hilo separado, sin bloquear el menú.
- El total facturado por médico y el filtro de pacientes por edad siempre se calculan
  con el mismo resultado, ya sea con un bucle manual (versión temprana) o con
  `Stream`/`Predicate` (versión final) — nunca cambia el dato, solo cómo se calcula.

## 📝 Cómo usar esta guía

Cada paso corresponde a un módulo del curso (Paso *N* ↔ Módulo *N*) y muestra el código
**completo y actualizado** de cada archivo que crea o modifica, para que lo transcribas
en tu propio proyecto de Visual Studio Code, seguido de una explicación breve de qué
cambia respecto al paso anterior y por qué. No hay ejemplos adicionales, ejercicios ni
quiz en esta guía — eso ya lo viste en el material de cada módulo. Hacé cada paso recién
termines el módulo correspondiente, y compilá y ejecutá antes de continuar con el
siguiente.

---

## 🪜 Pasos

### Paso 1 — Primeras variables del paciente (Módulo 1)

Creá en Visual Studio Code un proyecto Java llamado `SistemaMediSaludFinal` (lo vas a
reutilizar en todos los pasos siguientes) y agregá la clase `Principal`.

#### Archivo: Principal.java

```java
package com.medisalud;

public class Principal {

    public static void main(String[] args) {
        String nombreCompleto = "Ana Torres";
        String codigo = "P001";
        int edad = 34;

        System.out.println("Paciente: " + nombreCompleto);
        System.out.println("Codigo: " + codigo);
        System.out.println("Edad: " + edad);
    }
}
```

📖 **Explicación**: así arranca todo el proyecto: variables simples (`String`, `int`)
para describir el primer paciente de MediSalud. Todavía no hay clases propias ni
estructuras: solo los tipos de dato básicos del Módulo 1.

---

### Paso 2 — Decisiones sobre el plan de cobertura (Módulo 2)

#### Archivo: Principal.java

```java
package com.medisalud;

public class Principal {

    public static void main(String[] args) {
        String nombreCompleto = "Ana Torres";
        String codigo = "P001";
        int edad = 34;
        String tipoPlan = "AFILIADO";

        String categoria;
        if (edad < 18) {
            categoria = "pediatrico";
        } else if (edad < 60) {
            categoria = "adulto";
        } else {
            categoria = "adulto mayor";
        }

        String descuento = tipoPlan.equals("AFILIADO") ? "con descuento de afiliado" : "sin descuento";

        switch (tipoPlan) {
            case "AFILIADO":
                System.out.println("Plan: afiliado, aplica descuento especial");
                break;
            case "PARTICULAR":
                System.out.println("Plan: particular, tarifa completa");
                break;
            default:
                System.out.println("Plan: no reconocido");
        }

        System.out.println("Paciente: " + nombreCompleto);
        System.out.println("Codigo: " + codigo);
        System.out.println("Edad: " + edad + " (" + categoria + ")");
        System.out.println("Facturacion: " + descuento);
    }
}
```

📖 **Explicación**: se agregan tres formas de decisión sobre el mismo dato (`edad`,
`tipoPlan`): `if`/`else if`/`else` para clasificar al paciente por edad, el operador
ternario para el mensaje de descuento, y `switch` para el mensaje según el tipo de plan.

---

### Paso 3 — Recordatorios e intentos de contacto (Módulo 3)

#### Archivo: Principal.java

```java
package com.medisalud;

public class Principal {

    public static void main(String[] args) {
        String nombreCompleto = "Ana Torres";
        String codigo = "P001";
        int edad = 34;
        String tipoPlan = "AFILIADO";

        String categoria;
        if (edad < 18) {
            categoria = "pediatrico";
        } else if (edad < 60) {
            categoria = "adulto";
        } else {
            categoria = "adulto mayor";
        }

        String descuento = tipoPlan.equals("AFILIADO") ? "con descuento de afiliado" : "sin descuento";

        switch (tipoPlan) {
            case "AFILIADO":
                System.out.println("Plan: afiliado, aplica descuento especial");
                break;
            case "PARTICULAR":
                System.out.println("Plan: particular, tarifa completa");
                break;
            default:
                System.out.println("Plan: no reconocido");
        }

        System.out.println("Paciente: " + nombreCompleto);
        System.out.println("Codigo: " + codigo);
        System.out.println("Edad: " + edad + " (" + categoria + ")");
        System.out.println("Facturacion: " + descuento);

        System.out.println("== Recordatorios de la cita ==");
        for (int numeroRecordatorio = 1; numeroRecordatorio <= 3; numeroRecordatorio++) {
            System.out.println("Recordatorio " + numeroRecordatorio + " enviado a " + nombreCompleto);
        }

        System.out.println("== Intentos de contacto ==");
        int intento = 0;
        boolean contactado = false;
        while (intento < 5 && !contactado) {
            intento++;
            if (intento == 3) {
                contactado = true;
                System.out.println("Contacto exitoso en el intento " + intento);
                continue;
            }
            System.out.println("Intento " + intento + " sin respuesta");
        }

        int reintento = 0;
        do {
            reintento++;
            if (reintento == 2) {
                System.out.println("Confirmacion registrada en el reintento " + reintento);
                break;
            }
            System.out.println("Esperando confirmacion, reintento " + reintento);
        } while (reintento < 4);
    }
}
```

📖 **Explicación**: `for` recorre un número fijo de recordatorios; `while` con `continue`
simula intentos de contacto hasta lograr uno exitoso; `do-while` con `break` simula
reintentos de confirmación. Los tres bucles del Módulo 3 sobre el mismo escenario.

---

### Paso 4 — Formato de nombre y código (Módulo 4)

#### Archivo: Principal.java

```java
package com.medisalud;

public class Principal {

    public static void main(String[] args) {
        String nombreCompleto = "  Ana Torres  ";
        String codigo = "p001";
        int edad = 34;
        String tipoPlan = "AFILIADO";

        String nombreFormateado = nombreCompleto.trim();
        String codigoFormateado = codigo.toUpperCase();

        StringBuilder resumen = new StringBuilder();
        resumen.append(codigoFormateado).append(" - ").append(nombreFormateado);
        resumen.append(" (").append(edad).append(" anios)");

        String edadComoTexto = String.valueOf(edad);
        int edadDesdeTexto = Integer.parseInt(edadComoTexto);

        String categoria;
        if (edadDesdeTexto < 18) {
            categoria = "pediatrico";
        } else if (edadDesdeTexto < 60) {
            categoria = "adulto";
        } else {
            categoria = "adulto mayor";
        }

        String descuento = tipoPlan.equals("AFILIADO") ? "con descuento de afiliado" : "sin descuento";

        switch (tipoPlan) {
            case "AFILIADO":
                System.out.println("Plan: afiliado, aplica descuento especial");
                break;
            case "PARTICULAR":
                System.out.println("Plan: particular, tarifa completa");
                break;
            default:
                System.out.println("Plan: no reconocido");
        }

        System.out.println("Resumen: " + resumen);
        System.out.println("Categoria: " + categoria);
        System.out.println("Facturacion: " + descuento);

        System.out.println("== Recordatorios de la cita ==");
        for (int numeroRecordatorio = 1; numeroRecordatorio <= 3; numeroRecordatorio++) {
            System.out.println("Recordatorio " + numeroRecordatorio + " enviado a " + nombreFormateado);
        }

        System.out.println("== Intentos de contacto ==");
        int intento = 0;
        boolean contactado = false;
        while (intento < 5 && !contactado) {
            intento++;
            if (intento == 3) {
                contactado = true;
                System.out.println("Contacto exitoso en el intento " + intento);
                continue;
            }
            System.out.println("Intento " + intento + " sin respuesta");
        }

        int reintento = 0;
        do {
            reintento++;
            if (reintento == 2) {
                System.out.println("Confirmacion registrada en el reintento " + reintento);
                break;
            }
            System.out.println("Esperando confirmacion, reintento " + reintento);
        } while (reintento < 4);
    }
}
```

📖 **Explicación**: `trim()` y `toUpperCase()` limpian y normalizan el nombre y el
código tal como llegarían de una fuente externa; `StringBuilder` arma el resumen sin
concatenar `String` en un bucle; `String.valueOf`/`Integer.parseInt` muestran la
conversión de ida y vuelta entre texto y número del Módulo 4.

---

### Paso 5 — Nace la clase Paciente (Módulo 5)

#### Archivo: Paciente.java

```java
package com.medisalud.entity;

public class Paciente {

    public String nombreCompleto;
    public String codigo;
    public int edad;

    public Paciente(String nombreCompleto, String codigo, int edad) {
        this.nombreCompleto = nombreCompleto;
        this.codigo = codigo;
        this.edad = edad;
    }

    public String describir() {
        return codigo + " - " + nombreCompleto + " (" + edad + " anios)";
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Paciente;

public class Principal {

    public static void main(String[] args) {
        Paciente paciente = new Paciente("Ana Torres", "P001", 34);
        System.out.println(paciente.describir());
    }
}
```

📖 **Explicación**: todo lo que antes eran variables sueltas ahora vive junto, como
atributos de un objeto `Paciente` con su propio constructor y su propio método
`describir()`. `Principal` se simplifica porque esa responsabilidad ya no es suya — el
primer paso de POO del Módulo 5.

---

### Paso 6 — Encapsulamiento (Módulo 6)

#### Archivo: Paciente.java

```java
package com.medisalud.entity;

public class Paciente {

    private String nombreCompleto;
    private String codigo;
    private int edad;

    public Paciente(String nombreCompleto, String codigo, int edad) {
        setNombreCompleto(nombreCompleto);
        this.codigo = codigo;
        setEdad(edad);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        }
        this.nombreCompleto = nombreCompleto;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
    }

    public String describir() {
        return codigo + " - " + nombreCompleto + " (" + edad + " anios)";
    }
}
```

📖 **Explicación**: los atributos pasan a ser `private`; cualquier lectura o escritura
ahora pasa por un `get`/`set`, y `setNombreCompleto`/`setEdad` validan el dato antes de
aceptarlo. `Principal.java` no cambia: sigue llamando a `describir()` de la misma forma,
sin saber que por dentro ahora hay validación.

---

### Paso 7 — Herencia y polimorfismo (Módulo 7)

#### Archivo: Persona.java

```java
package com.medisalud.entity;

public abstract class Persona {

    private String nombreCompleto;
    private String codigo;

    protected Persona(String nombreCompleto, String codigo) {
        setNombreCompleto(nombreCompleto);
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        }
        this.nombreCompleto = nombreCompleto;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombreCompleto;
    }
}
```

#### Archivo: Paciente.java

```java
package com.medisalud.entity;

public class Paciente extends Persona {

    private int edad;

    public Paciente(String nombreCompleto, String codigo, int edad) {
        super(nombreCompleto, codigo);
        setEdad(edad);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
    }

    @Override
    public String toString() {
        return super.toString() + " (paciente, " + edad + " anios)";
    }
}
```

#### Archivo: Medico.java

```java
package com.medisalud.entity;

public class Medico extends Persona {

    private String especialidad;

    public Medico(String nombreCompleto, String codigo, String especialidad) {
        super(nombreCompleto, codigo);
        setEspecialidad(especialidad);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacia");
        }
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return super.toString() + " (medico, " + especialidad + ")";
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.entity.Persona;

public class Principal {

    public static void main(String[] args) {
        Persona[] personas = new Persona[2];
        personas[0] = new Paciente("Ana Torres", "P001", 34);
        personas[1] = new Medico("Carla Gomez", "M001", "Medicina general");

        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }
}
```

📖 **Explicación**: `Persona` es ahora la superclase abstracta común (`nombreCompleto`,
`codigo`, validados una sola vez); `Paciente` y `Medico` la extienden y cada una
sobre-escribe `toString()`. El arreglo `Persona[]` demuestra polimorfismo: el mismo
`System.out.println(persona)` imprime algo distinto segun el objeto real.

---

### Paso 8 — Colecciones y clase genérica (Módulo 8)

#### Archivo: Repositorio.java

```java
package com.medisalud.repository;

import java.util.ArrayList;
import java.util.List;

public class Repositorio<T> {

    private final List<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public List<T> listarTodos() {
        return new ArrayList<>(elementos);
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.repository.Repositorio;

public class Principal {

    public static void main(String[] args) {
        Repositorio<Paciente> repositorioPacientes = new Repositorio<>();
        Repositorio<Medico> repositorioMedicos = new Repositorio<>();

        repositorioPacientes.agregar(new Paciente("Ana Torres", "P001", 34));
        repositorioPacientes.agregar(new Paciente("Luis Fernandez", "P002", 45));
        repositorioMedicos.agregar(new Medico("Carla Gomez", "M001", "Medicina general"));

        for (Paciente paciente : repositorioPacientes.listarTodos()) {
            System.out.println(paciente);
        }
        for (Medico medico : repositorioMedicos.listarTodos()) {
            System.out.println(medico);
        }
    }
}
```

📖 **Explicación**: el arreglo de tamaño fijo del Paso 7 se reemplaza por `ArrayList`
(crece segun haga falta), envuelto en una clase genérica propia `Repositorio<T>` que
sirve tanto para `Paciente` como para `Medico` sin repetir código — el aporte del
Módulo 8. Esta clase es transitoria: el Paso 11 la va a reemplazar por interfaces
específicas.

---

### Paso 9 — Asociación y composición (Módulo 9)

#### Archivo: Cita.java

```java
package com.medisalud.entity;

import java.time.LocalDate;

public class Cita {

    private final String codigo;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDate fecha;

    public Cita(String codigo, Paciente paciente, Medico medico, LocalDate fecha) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Cita " + codigo + " " + paciente.getNombreCompleto() + " con " + medico.getNombreCompleto()
                + " el " + fecha;
    }
}
```

#### Archivo: HistoriaClinica.java

```java
package com.medisalud.entity;

import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica {

    private final List<String> consultas = new ArrayList<>();

    public void agregarConsulta(String diagnostico) {
        consultas.add(diagnostico);
    }

    public List<String> getConsultas() {
        return consultas;
    }

    @Override
    public String toString() {
        return "Historia clinica: " + consultas.size() + " consulta(s) registrada(s)";
    }
}
```

#### Archivo: Paciente.java

```java
package com.medisalud.entity;

public class Paciente extends Persona {

    private int edad;
    private final HistoriaClinica historiaClinica;

    public Paciente(String nombreCompleto, String codigo, int edad) {
        super(nombreCompleto, codigo);
        setEdad(edad);
        this.historiaClinica = new HistoriaClinica();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    @Override
    public String toString() {
        return super.toString() + " (paciente, " + edad + " anios)";
    }
}
```

#### Archivo: ServicioPacientes.java

```java
package com.medisalud.service;

import com.medisalud.entity.Paciente;
import com.medisalud.repository.Repositorio;
import java.util.ArrayList;
import java.util.List;

public class ServicioPacientes {

    private final Repositorio<Paciente> repositorioPacientes;

    public ServicioPacientes(Repositorio<Paciente> repositorioPacientes) {
        this.repositorioPacientes = repositorioPacientes;
    }

    public void registrarPaciente(Paciente paciente) {
        repositorioPacientes.agregar(paciente);
    }

    public List<Paciente> listarTodos() {
        return repositorioPacientes.listarTodos();
    }

    public List<Paciente> listarMayoresDeEdad(int edadMinima) {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente paciente : repositorioPacientes.listarTodos()) {
            if (paciente.getEdad() >= edadMinima) {
                resultado.add(paciente);
            }
        }
        return resultado;
    }
}
```

#### Archivo: ServicioMedicos.java

```java
package com.medisalud.service;

import com.medisalud.entity.Medico;
import com.medisalud.repository.Repositorio;
import java.util.List;

public class ServicioMedicos {

    private final Repositorio<Medico> repositorioMedicos;

    public ServicioMedicos(Repositorio<Medico> repositorioMedicos) {
        this.repositorioMedicos = repositorioMedicos;
    }

    public void registrarMedico(Medico medico) {
        repositorioMedicos.agregar(medico);
    }

    public List<Medico> listarTodos() {
        return repositorioMedicos.listarTodos();
    }
}
```

#### Archivo: ServicioCitas.java

```java
package com.medisalud.service;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.repository.Repositorio;
import java.time.LocalDate;
import java.util.List;

public class ServicioCitas {

    private final Repositorio<Cita> repositorioCitas;

    public ServicioCitas(Repositorio<Cita> repositorioCitas) {
        this.repositorioCitas = repositorioCitas;
    }

    public Cita agendarCita(String codigo, Paciente paciente, Medico medico, LocalDate fecha) {
        Cita cita = new Cita(codigo, paciente, medico, fecha);
        repositorioCitas.agregar(cita);
        return cita;
    }

    public List<Cita> listarTodas() {
        return repositorioCitas.listarTodos();
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.repository.Repositorio;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {
        ServicioPacientes servicioPacientes = new ServicioPacientes(new Repositorio<>());
        ServicioMedicos servicioMedicos = new ServicioMedicos(new Repositorio<>());
        ServicioCitas servicioCitas = new ServicioCitas(new Repositorio<>());

        Paciente ana = new Paciente("Ana Torres", "P001", 34);
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioPacientes.registrarPaciente(ana);
        servicioMedicos.registrarMedico(carla);

        Cita cita = servicioCitas.agendarCita("C001", ana, carla, LocalDate.of(2026, 10, 5));
        System.out.println(cita);
        System.out.println(ana.getHistoriaClinica());
    }
}
```

📖 **Explicación**: `Cita` **asocia** `Paciente` y `Medico` (los conoce, no los posee);
`Paciente` **compone** su propia `HistoriaClinica` (la crea y la posee). Nacen los tres
`Servicio*`, cada uno responsable de una entidad, siguiendo el Módulo 9. También se
anticipa `listarMayoresDeEdad`, un recorrido manual con bucle que el Paso 19 va a
reemplazar por `Stream`.

---

### Paso 10 — Estados, mapas y excepciones propias (Módulo 10)

#### Archivo: EstadoCita.java

```java
package com.medisalud.entity;

public enum EstadoCita {
    PENDIENTE,
    CONFIRMADA,
    ATENDIDA,
    CANCELADA;

    public boolean puedeTransicionarA(EstadoCita nuevoEstado) {
        switch (this) {
            case PENDIENTE:
                return nuevoEstado == CONFIRMADA || nuevoEstado == CANCELADA;
            case CONFIRMADA:
                return nuevoEstado == ATENDIDA || nuevoEstado == CANCELADA;
            default:
                return false;
        }
    }
}
```

#### Archivo: PacienteNoEncontradoException.java

```java
package com.medisalud.exception;

public class PacienteNoEncontradoException extends Exception {

    public PacienteNoEncontradoException(String codigo) {
        super("No existe un paciente con el codigo " + codigo);
    }
}
```

#### Archivo: MedicoNoEncontradoException.java

```java
package com.medisalud.exception;

public class MedicoNoEncontradoException extends Exception {

    public MedicoNoEncontradoException(String codigo) {
        super("No existe un medico con el codigo " + codigo);
    }
}
```

#### Archivo: CitaNoEncontradaException.java

```java
package com.medisalud.exception;

public class CitaNoEncontradaException extends Exception {

    public CitaNoEncontradaException(String codigo) {
        super("No existe una cita con el codigo " + codigo);
    }
}
```

#### Archivo: TransicionInvalidaException.java

```java
package com.medisalud.exception;

import com.medisalud.entity.EstadoCita;

public class TransicionInvalidaException extends Exception {

    public TransicionInvalidaException(String codigoCita, EstadoCita estadoActual, EstadoCita estadoNuevo) {
        super("La cita " + codigoCita + " no puede pasar de " + estadoActual + " a " + estadoNuevo);
    }
}
```

#### Archivo: Cita.java

```java
package com.medisalud.entity;

import java.time.LocalDate;

public class Cita {

    private final String codigo;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDate fecha;
    private EstadoCita estado;

    public Cita(String codigo, Paciente paciente, Medico medico, LocalDate fecha) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.estado = EstadoCita.PENDIENTE;
    }

    public void cambiarEstado(EstadoCita nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getCodigo() {
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Cita " + codigo + " [" + estado + "] " + paciente.getNombreCompleto()
                + " con " + medico.getNombreCompleto() + " el " + fecha;
    }
}
```

#### Archivo: ServicioPacientes.java

```java
package com.medisalud.service;

import com.medisalud.entity.Paciente;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.repository.Repositorio;
import java.util.ArrayList;
import java.util.List;

public class ServicioPacientes {

    private final Repositorio<Paciente> repositorioPacientes;

    public ServicioPacientes(Repositorio<Paciente> repositorioPacientes) {
        this.repositorioPacientes = repositorioPacientes;
    }

    public void registrarPaciente(Paciente paciente) {
        repositorioPacientes.agregar(paciente);
    }

    public Paciente buscarPorCodigo(String codigo) throws PacienteNoEncontradoException {
        for (Paciente paciente : repositorioPacientes.listarTodos()) {
            if (paciente.getCodigo().equals(codigo)) {
                return paciente;
            }
        }
        throw new PacienteNoEncontradoException(codigo);
    }

    public List<Paciente> listarTodos() {
        return repositorioPacientes.listarTodos();
    }

    public List<Paciente> listarMayoresDeEdad(int edadMinima) {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente paciente : repositorioPacientes.listarTodos()) {
            if (paciente.getEdad() >= edadMinima) {
                resultado.add(paciente);
            }
        }
        return resultado;
    }
}
```

#### Archivo: ServicioCitas.java

```java
package com.medisalud.service;

import com.medisalud.entity.Cita;
import com.medisalud.entity.EstadoCita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.TransicionInvalidaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioCitas {

    private final Map<String, Cita> citasPorCodigo = new HashMap<>();

    public Cita agendarCita(String codigo, Paciente paciente, Medico medico, LocalDate fecha) {
        Cita cita = new Cita(codigo, paciente, medico, fecha);
        citasPorCodigo.put(codigo, cita);
        return cita;
    }

    public Cita buscarPorCodigo(String codigo) throws CitaNoEncontradaException {
        Cita cita = citasPorCodigo.get(codigo);
        if (cita == null) {
            throw new CitaNoEncontradaException(codigo);
        }
        return cita;
    }

    public void confirmarCita(String codigo) throws CitaNoEncontradaException, TransicionInvalidaException {
        cambiarEstado(codigo, EstadoCita.CONFIRMADA);
    }

    public void atenderCita(String codigo, String diagnostico)
            throws CitaNoEncontradaException, TransicionInvalidaException {
        Cita cita = buscarPorCodigo(codigo);
        cambiarEstado(codigo, EstadoCita.ATENDIDA);
        cita.getPaciente().getHistoriaClinica().agregarConsulta(diagnostico);
    }

    public void cancelarCita(String codigo) throws CitaNoEncontradaException, TransicionInvalidaException {
        cambiarEstado(codigo, EstadoCita.CANCELADA);
    }

    public List<Cita> listarTodas() {
        return new ArrayList<>(citasPorCodigo.values());
    }

    private void cambiarEstado(String codigo, EstadoCita nuevoEstado)
            throws CitaNoEncontradaException, TransicionInvalidaException {
        Cita cita = buscarPorCodigo(codigo);
        if (!cita.getEstado().puedeTransicionarA(nuevoEstado)) {
            throw new TransicionInvalidaException(codigo, cita.getEstado(), nuevoEstado);
        }
        cita.cambiarEstado(nuevoEstado);
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.exception.TransicionInvalidaException;
import com.medisalud.repository.Repositorio;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {
        ServicioPacientes servicioPacientes = new ServicioPacientes(new Repositorio<>());
        ServicioMedicos servicioMedicos = new ServicioMedicos(new Repositorio<>());
        ServicioCitas servicioCitas = new ServicioCitas();

        Paciente ana = new Paciente("Ana Torres", "P001", 34);
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioPacientes.registrarPaciente(ana);
        servicioMedicos.registrarMedico(carla);

        Cita cita = servicioCitas.agendarCita("C001", ana, carla, LocalDate.of(2026, 10, 5));

        try {
            servicioCitas.confirmarCita("C001");
            servicioCitas.atenderCita("C001", "Control de presion arterial normal");
            System.out.println(cita);
        } catch (CitaNoEncontradaException | TransicionInvalidaException excepcion) {
            System.out.println("No se pudo actualizar la cita: " + excepcion.getMessage());
        }

        try {
            servicioPacientes.buscarPorCodigo("P999");
        } catch (PacienteNoEncontradoException excepcion) {
            System.out.println("Error esperado: " + excepcion.getMessage());
        }
    }
}
```

📖 **Explicación**: `EstadoCita` (`enum`) representa los cuatro estados posibles de una
cita y ya sabe qué transiciones son válidas; `ServicioCitas` pasa a usar un
`Map<String, Cita>` para buscar por código en lugar de recorrer una lista; cuatro
excepciones propias (tres del enunciado más `TransicionInvalidaException`, exigida por
la regla de validación V5) reemplazan los mensajes de error genéricos — el aporte del
Módulo 10. El `try`/`catch` en `Principal` muestra cómo se manejan sin detener el
programa.

---

### Paso 11 — Refactor SOLID (Módulo 11)

#### Archivo: RepositorioPacientes.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.List;

public interface RepositorioPacientes {

    void guardar(Paciente paciente);

    Paciente buscarPorCodigo(String codigo);

    List<Paciente> listarTodos();
}
```

#### Archivo: RepositorioMedicos.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Medico;
import java.util.List;

public interface RepositorioMedicos {

    void guardar(Medico medico);

    Medico buscarPorCodigo(String codigo);

    List<Medico> listarTodos();
}
```

#### Archivo: RepositorioCitas.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Cita;
import java.util.List;

public interface RepositorioCitas {

    void guardar(Cita cita);

    Cita buscarPorCodigo(String codigo);

    List<Cita> listarTodas();

    List<Cita> listarPorMedico(String codigoMedico);
}
```

#### Archivo: RepositorioPacientesMemoria.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPacientesMemoria implements RepositorioPacientes {

    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void guardar(Paciente paciente) {
        pacientes.add(paciente);
    }

    @Override
    public Paciente buscarPorCodigo(String codigo) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCodigo().equals(codigo)) {
                return paciente;
            }
        }
        return null;
    }

    @Override
    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes);
    }
}
```

#### Archivo: RepositorioMedicosMemoria.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Medico;
import java.util.ArrayList;
import java.util.List;

public class RepositorioMedicosMemoria implements RepositorioMedicos {

    private final List<Medico> medicos = new ArrayList<>();

    @Override
    public void guardar(Medico medico) {
        medicos.add(medico);
    }

    @Override
    public Medico buscarPorCodigo(String codigo) {
        for (Medico medico : medicos) {
            if (medico.getCodigo().equals(codigo)) {
                return medico;
            }
        }
        return null;
    }

    @Override
    public List<Medico> listarTodos() {
        return new ArrayList<>(medicos);
    }
}
```

#### Archivo: RepositorioCitasMemoria.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Cita;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioCitasMemoria implements RepositorioCitas {

    private final Map<String, Cita> citasPorCodigo = new HashMap<>();

    @Override
    public void guardar(Cita cita) {
        citasPorCodigo.put(cita.getCodigo(), cita);
    }

    @Override
    public Cita buscarPorCodigo(String codigo) {
        return citasPorCodigo.get(codigo);
    }

    @Override
    public List<Cita> listarTodas() {
        return new ArrayList<>(citasPorCodigo.values());
    }

    @Override
    public List<Cita> listarPorMedico(String codigoMedico) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citasPorCodigo.values()) {
            if (cita.getMedico().getCodigo().equals(codigoMedico)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }
}
```

#### Archivo: ServicioPacientes.java

```java
package com.medisalud.service;

import com.medisalud.entity.Paciente;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.repository.RepositorioPacientes;
import java.util.ArrayList;
import java.util.List;

public class ServicioPacientes {

    private final RepositorioPacientes repositorioPacientes;

    public ServicioPacientes(RepositorioPacientes repositorioPacientes) {
        this.repositorioPacientes = repositorioPacientes;
    }

    public void registrarPaciente(Paciente paciente) {
        repositorioPacientes.guardar(paciente);
    }

    public Paciente buscarPorCodigo(String codigo) throws PacienteNoEncontradoException {
        Paciente paciente = repositorioPacientes.buscarPorCodigo(codigo);
        if (paciente == null) {
            throw new PacienteNoEncontradoException(codigo);
        }
        return paciente;
    }

    public List<Paciente> listarTodos() {
        return repositorioPacientes.listarTodos();
    }

    public List<Paciente> listarMayoresDeEdad(int edadMinima) {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente paciente : repositorioPacientes.listarTodos()) {
            if (paciente.getEdad() >= edadMinima) {
                resultado.add(paciente);
            }
        }
        return resultado;
    }
}
```

#### Archivo: ServicioMedicos.java

```java
package com.medisalud.service;

import com.medisalud.entity.Medico;
import com.medisalud.exception.MedicoNoEncontradoException;
import com.medisalud.repository.RepositorioMedicos;
import java.util.List;

public class ServicioMedicos {

    private final RepositorioMedicos repositorioMedicos;

    public ServicioMedicos(RepositorioMedicos repositorioMedicos) {
        this.repositorioMedicos = repositorioMedicos;
    }

    public void registrarMedico(Medico medico) {
        repositorioMedicos.guardar(medico);
    }

    public Medico buscarPorCodigo(String codigo) throws MedicoNoEncontradoException {
        Medico medico = repositorioMedicos.buscarPorCodigo(codigo);
        if (medico == null) {
            throw new MedicoNoEncontradoException(codigo);
        }
        return medico;
    }

    public List<Medico> listarTodos() {
        return repositorioMedicos.listarTodos();
    }
}
```

#### Archivo: ServicioCitas.java

```java
package com.medisalud.service;

import com.medisalud.entity.Cita;
import com.medisalud.entity.EstadoCita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.TransicionInvalidaException;
import com.medisalud.repository.RepositorioCitas;
import java.time.LocalDate;
import java.util.List;

public class ServicioCitas {

    private final RepositorioCitas repositorioCitas;

    public ServicioCitas(RepositorioCitas repositorioCitas) {
        this.repositorioCitas = repositorioCitas;
    }

    public Cita agendarCita(String codigo, Paciente paciente, Medico medico, LocalDate fecha) {
        Cita cita = new Cita(codigo, paciente, medico, fecha);
        repositorioCitas.guardar(cita);
        return cita;
    }

    public Cita buscarPorCodigo(String codigo) throws CitaNoEncontradaException {
        Cita cita = repositorioCitas.buscarPorCodigo(codigo);
        if (cita == null) {
            throw new CitaNoEncontradaException(codigo);
        }
        return cita;
    }

    public void confirmarCita(String codigo) throws CitaNoEncontradaException, TransicionInvalidaException {
        cambiarEstado(codigo, EstadoCita.CONFIRMADA);
    }

    public void atenderCita(String codigo, String diagnostico)
            throws CitaNoEncontradaException, TransicionInvalidaException {
        Cita cita = buscarPorCodigo(codigo);
        cambiarEstado(codigo, EstadoCita.ATENDIDA);
        cita.getPaciente().getHistoriaClinica().agregarConsulta(diagnostico);
    }

    public void cancelarCita(String codigo) throws CitaNoEncontradaException, TransicionInvalidaException {
        cambiarEstado(codigo, EstadoCita.CANCELADA);
    }

    public List<Cita> listarPorMedico(String codigoMedico) {
        return repositorioCitas.listarPorMedico(codigoMedico);
    }

    private void cambiarEstado(String codigo, EstadoCita nuevoEstado)
            throws CitaNoEncontradaException, TransicionInvalidaException {
        Cita cita = buscarPorCodigo(codigo);
        if (!cita.getEstado().puedeTransicionarA(nuevoEstado)) {
            throw new TransicionInvalidaException(codigo, cita.getEstado(), nuevoEstado);
        }
        cita.cambiarEstado(nuevoEstado);
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.exception.TransicionInvalidaException;
import com.medisalud.repository.RepositorioCitasMemoria;
import com.medisalud.repository.RepositorioMedicosMemoria;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {
        ServicioPacientes servicioPacientes = new ServicioPacientes(new RepositorioPacientesMemoria());
        ServicioMedicos servicioMedicos = new ServicioMedicos(new RepositorioMedicosMemoria());
        ServicioCitas servicioCitas = new ServicioCitas(new RepositorioCitasMemoria());

        Paciente ana = new Paciente("Ana Torres", "P001", 34);
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioPacientes.registrarPaciente(ana);
        servicioMedicos.registrarMedico(carla);

        Cita cita = servicioCitas.agendarCita("C001", ana, carla, LocalDate.of(2026, 10, 5));

        try {
            servicioCitas.confirmarCita("C001");
            servicioCitas.atenderCita("C001", "Control de presion arterial normal");
            System.out.println(cita);
        } catch (CitaNoEncontradaException | TransicionInvalidaException excepcion) {
            System.out.println("No se pudo actualizar la cita: " + excepcion.getMessage());
        }

        try {
            servicioPacientes.buscarPorCodigo("P999");
        } catch (PacienteNoEncontradoException excepcion) {
            System.out.println("Error esperado: " + excepcion.getMessage());
        }
    }
}
```

📖 **Explicación**: `Repositorio<T>` se reemplaza por tres interfaces específicas
(`RepositorioPacientes`/`RepositorioMedicos`/`RepositorioCitas`) con su implementación en
memoria, e inyección de dependencias en cada `Servicio*` (reciben la interfaz por
constructor, nunca la implementación concreta). Esto resuelve varios de los cinco
principios SOLID del Módulo 11 a la vez: responsabilidad única (cada repositorio, una
entidad), abierto/cerrado y sustitución (cualquier implementación de la interfaz sirve),
segregación de interfaces (métodos específicos por entidad) e inversión de dependencias
(los `Servicio*` dependen de la interfaz, no de `RepositorioPacientesMemoria`) — esta
última es la que va a permitir cambiar a JDBC en el Paso 18 sin tocar ningún `Servicio*`.

---

### Paso 12 — Builder y Singleton (Módulo 12)

#### Archivo: HistoriaClinica.java

```java
package com.medisalud.entity;

import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica {

    private String antecedentes;
    private String alergias;
    private String observaciones;
    private final List<String> consultas = new ArrayList<>();

    public HistoriaClinica() {
    }

    public HistoriaClinica(String antecedentes, String alergias, String observaciones) {
        this.antecedentes = antecedentes;
        this.alergias = alergias;
        this.observaciones = observaciones;
    }

    public void agregarConsulta(String diagnostico) {
        consultas.add(diagnostico);
    }

    public List<String> getConsultas() {
        return consultas;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getObservaciones() {
        return observaciones;
    }

    @Override
    public String toString() {
        return "Historia clinica: " + consultas.size() + " consulta(s) registrada(s)";
    }
}
```

#### Archivo: Paciente.java

```java
package com.medisalud.entity;

public class Paciente extends Persona {

    private int edad;
    private final HistoriaClinica historiaClinica;

    public Paciente(String nombreCompleto, String codigo, int edad) {
        this(nombreCompleto, codigo, edad, new HistoriaClinica());
    }

    public Paciente(String nombreCompleto, String codigo, int edad, HistoriaClinica historiaClinica) {
        super(nombreCompleto, codigo);
        setEdad(edad);
        this.historiaClinica = historiaClinica;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    @Override
    public String toString() {
        return super.toString() + " (paciente, " + edad + " anios)";
    }
}
```

#### Archivo: ConstructorHistoriaClinica.java

```java
package com.medisalud.patron.creacional;

import com.medisalud.entity.HistoriaClinica;

public class ConstructorHistoriaClinica {

    private String antecedentes;
    private String alergias;
    private String observaciones;

    public ConstructorHistoriaClinica conAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
        return this;
    }

    public ConstructorHistoriaClinica conAlergias(String alergias) {
        this.alergias = alergias;
        return this;
    }

    public ConstructorHistoriaClinica conObservaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public HistoriaClinica construir() {
        return new HistoriaClinica(antecedentes, alergias, observaciones);
    }
}
```

#### Archivo: GestorClinica.java

```java
package com.medisalud.patron.creacional;

import com.medisalud.repository.RepositorioCitas;
import com.medisalud.repository.RepositorioMedicos;
import com.medisalud.repository.RepositorioPacientes;

public final class GestorClinica {

    private static GestorClinica instancia;

    private final RepositorioPacientes repositorioPacientes;
    private final RepositorioMedicos repositorioMedicos;
    private final RepositorioCitas repositorioCitas;

    private GestorClinica(RepositorioPacientes repositorioPacientes,
                           RepositorioMedicos repositorioMedicos,
                           RepositorioCitas repositorioCitas) {
        this.repositorioPacientes = repositorioPacientes;
        this.repositorioMedicos = repositorioMedicos;
        this.repositorioCitas = repositorioCitas;
    }

    public static synchronized GestorClinica inicializar(RepositorioPacientes repositorioPacientes,
                                                           RepositorioMedicos repositorioMedicos,
                                                           RepositorioCitas repositorioCitas) {
        if (instancia == null) {
            instancia = new GestorClinica(repositorioPacientes, repositorioMedicos, repositorioCitas);
        }
        return instancia;
    }

    public static GestorClinica obtenerInstancia() {
        if (instancia == null) {
            throw new IllegalStateException("GestorClinica no fue inicializado todavia");
        }
        return instancia;
    }

    public RepositorioPacientes getRepositorioPacientes() {
        return repositorioPacientes;
    }

    public RepositorioMedicos getRepositorioMedicos() {
        return repositorioMedicos;
    }

    public RepositorioCitas getRepositorioCitas() {
        return repositorioCitas;
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Cita;
import com.medisalud.entity.HistoriaClinica;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.exception.TransicionInvalidaException;
import com.medisalud.patron.creacional.ConstructorHistoriaClinica;
import com.medisalud.patron.creacional.GestorClinica;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import com.medisalud.repository.RepositorioCitasMemoria;
import com.medisalud.repository.RepositorioMedicosMemoria;
import com.medisalud.repository.RepositorioPacientesMemoria;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {
        RepositorioPacientesMemoria repositorioPacientes = new RepositorioPacientesMemoria();
        RepositorioMedicosMemoria repositorioMedicos = new RepositorioMedicosMemoria();
        RepositorioCitasMemoria repositorioCitas = new RepositorioCitasMemoria();
        GestorClinica.inicializar(repositorioPacientes, repositorioMedicos, repositorioCitas);

        ServicioPacientes servicioPacientes = new ServicioPacientes(
                GestorClinica.obtenerInstancia().getRepositorioPacientes());
        ServicioMedicos servicioMedicos = new ServicioMedicos(
                GestorClinica.obtenerInstancia().getRepositorioMedicos());
        ServicioCitas servicioCitas = new ServicioCitas(
                GestorClinica.obtenerInstancia().getRepositorioCitas());

        HistoriaClinica historiaAna = new ConstructorHistoriaClinica()
                .conAntecedentes("Hipertension controlada")
                .conAlergias("Ninguna conocida")
                .construir();
        Paciente ana = new Paciente("Ana Torres", "P001", 34, historiaAna);
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioPacientes.registrarPaciente(ana);
        servicioMedicos.registrarMedico(carla);

        Cita cita = servicioCitas.agendarCita("C001", ana, carla, LocalDate.of(2026, 10, 5));

        try {
            servicioCitas.confirmarCita("C001");
            servicioCitas.atenderCita("C001", "Control de presion arterial normal");
            System.out.println(cita);
        } catch (CitaNoEncontradaException | TransicionInvalidaException excepcion) {
            System.out.println("No se pudo actualizar la cita: " + excepcion.getMessage());
        }

        System.out.println(ana.getHistoriaClinica());

        try {
            servicioPacientes.buscarPorCodigo("P999");
        } catch (PacienteNoEncontradoException excepcion) {
            System.out.println("Error esperado: " + excepcion.getMessage());
        }
    }
}
```

📖 **Explicación**: **Builder** (`ConstructorHistoriaClinica`) resuelve un problema real:
`HistoriaClinica` tiene varios campos opcionales (`antecedentes`, `alergias`,
`observaciones`) y un constructor con todos ellos sería confuso de llamar — el builder
los va fijando uno a uno con métodos encadenados. **Singleton** (`GestorClinica`)
garantiza un único punto de acceso a los tres repositorios de toda la aplicación, en vez
de pasarlos sueltos por todos lados. Ambos del Módulo 12.

---

### Paso 13 — Decorator y Facade (Módulo 13)

#### Archivo: Factura.java

```java
package com.medisalud.entity;

public class Factura {

    private final Cita cita;
    private final double montoBase;

    public Factura(Cita cita, double montoBase) {
        this.cita = cita;
        this.montoBase = montoBase;
    }

    public Cita getCita() {
        return cita;
    }

    public double getMontoBase() {
        return montoBase;
    }

    public double calcularMonto() {
        return montoBase;
    }

    @Override
    public String toString() {
        return "Factura de la cita " + cita.getCodigo() + ": $" + calcularMonto();
    }
}
```

#### Archivo: FacturaConRecargoNocturno.java

```java
package com.medisalud.patron.estructural;

import com.medisalud.entity.Factura;

public class FacturaConRecargoNocturno extends Factura {

    private static final double RECARGO_NOCTURNO = 15.0;
    private final Factura facturaOriginal;

    public FacturaConRecargoNocturno(Factura facturaOriginal) {
        super(facturaOriginal.getCita(), facturaOriginal.getMontoBase());
        this.facturaOriginal = facturaOriginal;
    }

    @Override
    public double calcularMonto() {
        return facturaOriginal.calcularMonto() + RECARGO_NOCTURNO;
    }

    @Override
    public String toString() {
        return facturaOriginal.toString() + " + recargo nocturno = $" + calcularMonto();
    }
}
```

#### Archivo: FacturaConDescuentoAfiliado.java

```java
package com.medisalud.patron.estructural;

import com.medisalud.entity.Factura;

public class FacturaConDescuentoAfiliado extends Factura {

    private static final double DESCUENTO_AFILIADO = 10.0;
    private final Factura facturaOriginal;

    public FacturaConDescuentoAfiliado(Factura facturaOriginal) {
        super(facturaOriginal.getCita(), facturaOriginal.getMontoBase());
        this.facturaOriginal = facturaOriginal;
    }

    @Override
    public double calcularMonto() {
        double montoConDescuento = facturaOriginal.calcularMonto() - DESCUENTO_AFILIADO;
        return Math.max(0.0, montoConDescuento);
    }

    @Override
    public String toString() {
        return facturaOriginal.toString() + " - descuento afiliado = $" + calcularMonto();
    }
}
```

#### Archivo: FachadaAgendamiento.java

```java
package com.medisalud.patron.estructural;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.MedicoNoEncontradoException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class FachadaAgendamiento {

    private final ServicioPacientes servicioPacientes;
    private final ServicioMedicos servicioMedicos;
    private final ServicioCitas servicioCitas;

    public FachadaAgendamiento(ServicioPacientes servicioPacientes,
                                ServicioMedicos servicioMedicos,
                                ServicioCitas servicioCitas) {
        this.servicioPacientes = servicioPacientes;
        this.servicioMedicos = servicioMedicos;
        this.servicioCitas = servicioCitas;
    }

    public Cita agendar(String codigoCita, String codigoPaciente, String codigoMedico, LocalDate fecha)
            throws PacienteNoEncontradoException, MedicoNoEncontradoException {
        Paciente paciente = servicioPacientes.buscarPorCodigo(codigoPaciente);
        Medico medico = servicioMedicos.buscarPorCodigo(codigoMedico);
        return servicioCitas.agendarCita(codigoCita, paciente, medico, fecha);
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.entity.HistoriaClinica;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.MedicoNoEncontradoException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.exception.TransicionInvalidaException;
import com.medisalud.patron.creacional.ConstructorHistoriaClinica;
import com.medisalud.patron.creacional.GestorClinica;
import com.medisalud.patron.estructural.FachadaAgendamiento;
import com.medisalud.patron.estructural.FacturaConDescuentoAfiliado;
import com.medisalud.repository.RepositorioCitasMemoria;
import com.medisalud.repository.RepositorioMedicosMemoria;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) throws PacienteNoEncontradoException, MedicoNoEncontradoException {
        RepositorioPacientesMemoria repositorioPacientes = new RepositorioPacientesMemoria();
        RepositorioMedicosMemoria repositorioMedicos = new RepositorioMedicosMemoria();
        RepositorioCitasMemoria repositorioCitas = new RepositorioCitasMemoria();
        GestorClinica.inicializar(repositorioPacientes, repositorioMedicos, repositorioCitas);

        ServicioPacientes servicioPacientes = new ServicioPacientes(repositorioPacientes);
        ServicioMedicos servicioMedicos = new ServicioMedicos(repositorioMedicos);
        ServicioCitas servicioCitas = new ServicioCitas(repositorioCitas);
        FachadaAgendamiento fachadaAgendamiento =
                new FachadaAgendamiento(servicioPacientes, servicioMedicos, servicioCitas);

        HistoriaClinica historiaAna = new ConstructorHistoriaClinica()
                .conAntecedentes("Hipertension controlada")
                .conAlergias("Ninguna conocida")
                .construir();
        Paciente ana = new Paciente("Ana Torres", "P001", 34, historiaAna);
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioPacientes.registrarPaciente(ana);
        servicioMedicos.registrarMedico(carla);

        Cita cita = fachadaAgendamiento.agendar("C001", ana.getCodigo(), carla.getCodigo(), LocalDate.of(2026, 10, 5));

        try {
            servicioCitas.confirmarCita("C001");
            servicioCitas.atenderCita("C001", "Control de presion arterial normal");
            System.out.println(cita);
        } catch (CitaNoEncontradaException | TransicionInvalidaException excepcion) {
            System.out.println("No se pudo actualizar la cita: " + excepcion.getMessage());
        }

        System.out.println(ana.getHistoriaClinica());

        Factura factura = new Factura(cita, 50.0);
        Factura facturaConDescuento = new FacturaConDescuentoAfiliado(factura);
        System.out.println(facturaConDescuento);
    }
}
```

📖 **Explicación**: nace `Factura` (recién ahora, porque recién ahora el proyecto
necesita calcular y mostrar un costo). **Decorator**
(`FacturaConRecargoNocturno`/`FacturaConDescuentoAfiliado`) agrega recargos/descuentos
envolviendo una `Factura` sin modificar su clase. **Facade** (`FachadaAgendamiento`)
oculta detrás de un único método `agendar(...)` los pasos de buscar paciente, buscar
médico y crear la cita — por eso `Principal` ya no necesita su propio `try`/`catch` para
esas búsquedas. Ambos patrones del Módulo 13.

---

### Paso 14 — Strategy, Observer y State (Módulo 14)

#### Archivo: EstrategiaCosto.java

```java
package com.medisalud.patron.comportamiento;

import com.medisalud.entity.Cita;

public interface EstrategiaCosto {

    double calcularCosto(Cita cita);
}
```

#### Archivo: EstrategiaCostoConsultaGeneral.java

```java
package com.medisalud.patron.comportamiento;

import com.medisalud.entity.Cita;

public class EstrategiaCostoConsultaGeneral implements EstrategiaCosto {

    private static final double COSTO_CONSULTA_GENERAL = 50.0;

    @Override
    public double calcularCosto(Cita cita) {
        return COSTO_CONSULTA_GENERAL;
    }
}
```

#### Archivo: EstrategiaCostoConsultaEspecialista.java

```java
package com.medisalud.patron.comportamiento;

import com.medisalud.entity.Cita;

public class EstrategiaCostoConsultaEspecialista implements EstrategiaCosto {

    private static final double COSTO_CONSULTA_ESPECIALISTA = 90.0;

    @Override
    public double calcularCosto(Cita cita) {
        return COSTO_CONSULTA_ESPECIALISTA;
    }
}
```

#### Archivo: ObservadorCita.java

```java
package com.medisalud.patron.comportamiento;

import com.medisalud.entity.Cita;

public interface ObservadorCita {

    void notificarCambioEstado(Cita cita);
}
```

#### Archivo: ObservadorCitaNotificacion.java

```java
package com.medisalud.patron.comportamiento;

import com.medisalud.entity.Cita;

public class ObservadorCitaNotificacion implements ObservadorCita {

    @Override
    public void notificarCambioEstado(Cita cita) {
        System.out.println("[notificacion] La cita " + cita.getCodigo() + " ahora esta " + cita.getEstado());
    }
}
```

#### Archivo: Cita.java

```java
package com.medisalud.entity;

import com.medisalud.patron.comportamiento.ObservadorCita;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cita {

    private final String codigo;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDate fecha;
    private EstadoCita estado;
    private final List<ObservadorCita> observadores = new ArrayList<>();

    public Cita(String codigo, Paciente paciente, Medico medico, LocalDate fecha) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.estado = EstadoCita.PENDIENTE;
    }

    public void agregarObservador(ObservadorCita observador) {
        observadores.add(observador);
    }

    public void cambiarEstado(EstadoCita nuevoEstado) {
        this.estado = nuevoEstado;
        for (ObservadorCita observador : observadores) {
            observador.notificarCambioEstado(this);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Cita " + codigo + " [" + estado + "] " + paciente.getNombreCompleto()
                + " con " + medico.getNombreCompleto() + " el " + fecha;
    }
}
```

#### Archivo: FachadaAgendamiento.java

```java
package com.medisalud.patron.estructural;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.MedicoNoEncontradoException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.patron.comportamiento.ObservadorCita;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class FachadaAgendamiento {

    private final ServicioPacientes servicioPacientes;
    private final ServicioMedicos servicioMedicos;
    private final ServicioCitas servicioCitas;
    private final ObservadorCita observadorCita;

    public FachadaAgendamiento(ServicioPacientes servicioPacientes,
                                ServicioMedicos servicioMedicos,
                                ServicioCitas servicioCitas,
                                ObservadorCita observadorCita) {
        this.servicioPacientes = servicioPacientes;
        this.servicioMedicos = servicioMedicos;
        this.servicioCitas = servicioCitas;
        this.observadorCita = observadorCita;
    }

    public Cita agendar(String codigoCita, String codigoPaciente, String codigoMedico, LocalDate fecha)
            throws PacienteNoEncontradoException, MedicoNoEncontradoException {
        Paciente paciente = servicioPacientes.buscarPorCodigo(codigoPaciente);
        Medico medico = servicioMedicos.buscarPorCodigo(codigoMedico);
        Cita cita = servicioCitas.agendarCita(codigoCita, paciente, medico, fecha);
        cita.agregarObservador(observadorCita);
        return cita;
    }
}
```

#### Archivo: ServicioFacturacion.java

```java
package com.medisalud.service;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.patron.comportamiento.EstrategiaCosto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioFacturacion {

    private final List<Factura> facturasEmitidas = new ArrayList<>();

    public Factura emitirFactura(Cita cita, EstrategiaCosto estrategiaCosto) {
        double montoBase = estrategiaCosto.calcularCosto(cita);
        Factura factura = new Factura(cita, montoBase);
        facturasEmitidas.add(factura);
        return factura;
    }

    public List<Factura> listarFacturas() {
        return new ArrayList<>(facturasEmitidas);
    }

    public Map<String, Double> totalFacturadoPorMedico() {
        Map<String, Double> totales = new HashMap<>();
        for (Factura factura : facturasEmitidas) {
            String codigoMedico = factura.getCita().getMedico().getCodigo();
            double totalActual = totales.getOrDefault(codigoMedico, 0.0);
            totales.put(codigoMedico, totalActual + factura.calcularMonto());
        }
        return totales;
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.entity.HistoriaClinica;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.MedicoNoEncontradoException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.exception.TransicionInvalidaException;
import com.medisalud.patron.comportamiento.EstrategiaCosto;
import com.medisalud.patron.comportamiento.EstrategiaCostoConsultaGeneral;
import com.medisalud.patron.comportamiento.ObservadorCitaNotificacion;
import com.medisalud.patron.creacional.ConstructorHistoriaClinica;
import com.medisalud.patron.creacional.GestorClinica;
import com.medisalud.patron.estructural.FachadaAgendamiento;
import com.medisalud.patron.estructural.FacturaConDescuentoAfiliado;
import com.medisalud.repository.RepositorioCitasMemoria;
import com.medisalud.repository.RepositorioMedicosMemoria;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioFacturacion;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) throws PacienteNoEncontradoException, MedicoNoEncontradoException {
        RepositorioPacientesMemoria repositorioPacientes = new RepositorioPacientesMemoria();
        RepositorioMedicosMemoria repositorioMedicos = new RepositorioMedicosMemoria();
        RepositorioCitasMemoria repositorioCitas = new RepositorioCitasMemoria();
        GestorClinica.inicializar(repositorioPacientes, repositorioMedicos, repositorioCitas);

        ServicioPacientes servicioPacientes = new ServicioPacientes(repositorioPacientes);
        ServicioMedicos servicioMedicos = new ServicioMedicos(repositorioMedicos);
        ServicioCitas servicioCitas = new ServicioCitas(repositorioCitas);
        ObservadorCitaNotificacion observador = new ObservadorCitaNotificacion();
        FachadaAgendamiento fachadaAgendamiento = new FachadaAgendamiento(
                servicioPacientes, servicioMedicos, servicioCitas, observador);

        HistoriaClinica historiaAna = new ConstructorHistoriaClinica()
                .conAntecedentes("Hipertension controlada")
                .conAlergias("Ninguna conocida")
                .construir();
        Paciente ana = new Paciente("Ana Torres", "P001", 34, historiaAna);
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioPacientes.registrarPaciente(ana);
        servicioMedicos.registrarMedico(carla);

        Cita cita = fachadaAgendamiento.agendar("C001", ana.getCodigo(), carla.getCodigo(), LocalDate.of(2026, 10, 5));

        try {
            servicioCitas.confirmarCita("C001");
            servicioCitas.atenderCita("C001", "Control de presion arterial normal");
            System.out.println(cita);
        } catch (CitaNoEncontradaException | TransicionInvalidaException excepcion) {
            System.out.println("No se pudo actualizar la cita: " + excepcion.getMessage());
        }

        System.out.println(ana.getHistoriaClinica());

        EstrategiaCosto estrategiaCosto = new EstrategiaCostoConsultaGeneral();
        Factura factura = new ServicioFacturacion().emitirFactura(cita, estrategiaCosto);
        Factura facturaConDescuento = new FacturaConDescuentoAfiliado(factura);
        System.out.println(facturaConDescuento);
    }
}
```

📖 **Explicación**: **Strategy** (`EstrategiaCosto`) separa el cálculo del costo en
variantes intercambiables (consulta general vs. especialista) en vez de un `if` dentro
de `Factura`. **Observer** (`ObservadorCita`) permite que `Cita` notifique cambios de
estado sin conocer quién escucha. **State** ya estaba aplicado desde el Paso 10/11: los
propios valores de `EstadoCita` (`puedeTransicionarA`) deciden qué transición es válida,
sin que `ServicioCitas` necesite un gran `if`/`switch` repetido — los tres patrones de
comportamiento del Módulo 14. También nace `ServicioFacturacion`, que ahora centraliza
el cálculo y guarda cada factura emitida para el reporte del Paso 20.

---

### Paso 15 — Un hilo para las notificaciones (Módulo 15)

#### Archivo: HiloNotificaciones.java

```java
package com.medisalud.concurrencia;

import java.util.ArrayList;
import java.util.List;

public class HiloNotificaciones extends Thread {

    private final List<String> pendientes = new ArrayList<>();
    private volatile boolean activo = true;

    public HiloNotificaciones() {
        super("hilo-notificaciones");
    }

    public void encolar(String mensaje) {
        pendientes.add(mensaje);
    }

    public void detener() {
        activo = false;
    }

    @Override
    public void run() {
        while (activo) {
            procesarPendientes();
            try {
                Thread.sleep(200);
            } catch (InterruptedException excepcion) {
                Thread.currentThread().interrupt();
                activo = false;
            }
        }
        procesarPendientes();
    }

    private void procesarPendientes() {
        for (String mensaje : pendientes) {
            System.out.println("[notificacion] " + mensaje);
        }
        pendientes.clear();
    }
}
```

#### Archivo: ObservadorCitaNotificacion.java

```java
package com.medisalud.patron.comportamiento;

import com.medisalud.concurrencia.HiloNotificaciones;
import com.medisalud.entity.Cita;

public class ObservadorCitaNotificacion implements ObservadorCita {

    private final HiloNotificaciones hiloNotificaciones;

    public ObservadorCitaNotificacion(HiloNotificaciones hiloNotificaciones) {
        this.hiloNotificaciones = hiloNotificaciones;
    }

    @Override
    public void notificarCambioEstado(Cita cita) {
        hiloNotificaciones.encolar("La cita " + cita.getCodigo() + " ahora esta " + cita.getEstado());
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.concurrencia.HiloNotificaciones;
import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.entity.HistoriaClinica;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.patron.comportamiento.EstrategiaCosto;
import com.medisalud.patron.comportamiento.EstrategiaCostoConsultaGeneral;
import com.medisalud.patron.comportamiento.ObservadorCitaNotificacion;
import com.medisalud.patron.creacional.ConstructorHistoriaClinica;
import com.medisalud.patron.creacional.GestorClinica;
import com.medisalud.patron.estructural.FachadaAgendamiento;
import com.medisalud.patron.estructural.FacturaConDescuentoAfiliado;
import com.medisalud.repository.RepositorioCitasMemoria;
import com.medisalud.repository.RepositorioMedicosMemoria;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioFacturacion;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) throws Exception {
        RepositorioPacientesMemoria repositorioPacientes = new RepositorioPacientesMemoria();
        RepositorioMedicosMemoria repositorioMedicos = new RepositorioMedicosMemoria();
        RepositorioCitasMemoria repositorioCitas = new RepositorioCitasMemoria();
        GestorClinica.inicializar(repositorioPacientes, repositorioMedicos, repositorioCitas);

        ServicioPacientes servicioPacientes = new ServicioPacientes(repositorioPacientes);
        ServicioMedicos servicioMedicos = new ServicioMedicos(repositorioMedicos);
        ServicioCitas servicioCitas = new ServicioCitas(repositorioCitas);
        ServicioFacturacion servicioFacturacion = new ServicioFacturacion();

        HiloNotificaciones hiloNotificaciones = new HiloNotificaciones();
        hiloNotificaciones.start();
        ObservadorCitaNotificacion observador = new ObservadorCitaNotificacion(hiloNotificaciones);
        FachadaAgendamiento fachadaAgendamiento = new FachadaAgendamiento(
                servicioPacientes, servicioMedicos, servicioCitas, observador);

        HistoriaClinica historiaAna = new ConstructorHistoriaClinica()
                .conAntecedentes("Hipertension controlada")
                .conAlergias("Ninguna conocida")
                .construir();
        Paciente ana = new Paciente("Ana Torres", "P001", 34, historiaAna);
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioPacientes.registrarPaciente(ana);
        servicioMedicos.registrarMedico(carla);

        Cita cita = fachadaAgendamiento.agendar("C001", ana.getCodigo(), carla.getCodigo(), LocalDate.of(2026, 10, 5));

        servicioCitas.confirmarCita("C001");
        servicioCitas.atenderCita("C001", "Control de presion arterial normal");
        System.out.println(cita);
        System.out.println(ana.getHistoriaClinica());

        EstrategiaCosto estrategiaCosto = new EstrategiaCostoConsultaGeneral();
        Factura factura = servicioFacturacion.emitirFactura(cita, estrategiaCosto);
        Factura facturaConDescuento = new FacturaConDescuentoAfiliado(factura);
        System.out.println(facturaConDescuento);

        hiloNotificaciones.detener();
        hiloNotificaciones.join();
    }
}
```

📖 **Explicación**: `HiloNotificaciones` extiende `Thread` y corre en paralelo al hilo
principal (`run()` procesa mensajes pendientes cada 200 ms hasta que `detener()` lo
apaga); `ObservadorCitaNotificacion` ya no imprime directamente, encola el mensaje para
que el hilo lo procese — el ciclo de vida de un hilo del Módulo 15 (`start`, `run`,
`join`). Desde este paso, `main` declara `throws Exception` en vez de repetir cada
`try`/`catch` ya demostrado en los Pasos 10-11.

---

### Paso 16 — Sincronización y códigos atómicos (Módulo 16)

#### Archivo: ContadorCodigos.java

```java
package com.medisalud.concurrencia;

import java.util.concurrent.atomic.AtomicLong;

public class ContadorCodigos {

    private final AtomicLong contador = new AtomicLong(0);
    private final String prefijo;

    public ContadorCodigos(String prefijo) {
        this.prefijo = prefijo;
    }

    public String siguienteCodigo() {
        long numero = contador.incrementAndGet();
        String numeroConRelleno = String.format("%03d", numero);
        return prefijo + numeroConRelleno;
    }
}
```

#### Archivo: HiloNotificaciones.java

```java
package com.medisalud.concurrencia;

import java.util.ArrayList;
import java.util.List;

public class HiloNotificaciones extends Thread {

    private final List<String> pendientes = new ArrayList<>();
    private volatile boolean activo = true;

    public HiloNotificaciones() {
        super("hilo-notificaciones");
    }

    public synchronized void encolar(String mensaje) {
        pendientes.add(mensaje);
    }

    public void detener() {
        activo = false;
    }

    @Override
    public void run() {
        while (activo) {
            procesarPendientes();
            try {
                Thread.sleep(200);
            } catch (InterruptedException excepcion) {
                Thread.currentThread().interrupt();
                activo = false;
            }
        }
        procesarPendientes();
    }

    private synchronized void procesarPendientes() {
        for (String mensaje : pendientes) {
            System.out.println("[notificacion] " + mensaje);
        }
        pendientes.clear();
    }
}
```

#### Archivo: RepositorioPacientesMemoria.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPacientesMemoria implements RepositorioPacientes {

    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public synchronized void guardar(Paciente paciente) {
        pacientes.add(paciente);
    }

    @Override
    public synchronized Paciente buscarPorCodigo(String codigo) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCodigo().equals(codigo)) {
                return paciente;
            }
        }
        return null;
    }

    @Override
    public synchronized List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes);
    }
}
```

#### Archivo: RepositorioMedicosMemoria.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Medico;
import java.util.ArrayList;
import java.util.List;

public class RepositorioMedicosMemoria implements RepositorioMedicos {

    private final List<Medico> medicos = new ArrayList<>();

    @Override
    public synchronized void guardar(Medico medico) {
        medicos.add(medico);
    }

    @Override
    public synchronized Medico buscarPorCodigo(String codigo) {
        for (Medico medico : medicos) {
            if (medico.getCodigo().equals(codigo)) {
                return medico;
            }
        }
        return null;
    }

    @Override
    public synchronized List<Medico> listarTodos() {
        return new ArrayList<>(medicos);
    }
}
```

#### Archivo: RepositorioCitasMemoria.java

```java
package com.medisalud.repository;

import com.medisalud.entity.Cita;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioCitasMemoria implements RepositorioCitas {

    private final Map<String, Cita> citasPorCodigo = new HashMap<>();

    @Override
    public synchronized void guardar(Cita cita) {
        citasPorCodigo.put(cita.getCodigo(), cita);
    }

    @Override
    public synchronized Cita buscarPorCodigo(String codigo) {
        return citasPorCodigo.get(codigo);
    }

    @Override
    public synchronized List<Cita> listarTodas() {
        return new ArrayList<>(citasPorCodigo.values());
    }

    @Override
    public synchronized List<Cita> listarPorMedico(String codigoMedico) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citasPorCodigo.values()) {
            if (cita.getMedico().getCodigo().equals(codigoMedico)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.concurrencia.ContadorCodigos;
import com.medisalud.concurrencia.HiloNotificaciones;
import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.entity.HistoriaClinica;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.patron.comportamiento.EstrategiaCosto;
import com.medisalud.patron.comportamiento.EstrategiaCostoConsultaGeneral;
import com.medisalud.patron.comportamiento.ObservadorCitaNotificacion;
import com.medisalud.patron.creacional.ConstructorHistoriaClinica;
import com.medisalud.patron.creacional.GestorClinica;
import com.medisalud.patron.estructural.FachadaAgendamiento;
import com.medisalud.patron.estructural.FacturaConDescuentoAfiliado;
import com.medisalud.repository.RepositorioCitasMemoria;
import com.medisalud.repository.RepositorioMedicosMemoria;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioFacturacion;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) throws Exception {
        RepositorioPacientesMemoria repositorioPacientes = new RepositorioPacientesMemoria();
        RepositorioMedicosMemoria repositorioMedicos = new RepositorioMedicosMemoria();
        RepositorioCitasMemoria repositorioCitas = new RepositorioCitasMemoria();
        GestorClinica.inicializar(repositorioPacientes, repositorioMedicos, repositorioCitas);

        ServicioPacientes servicioPacientes = new ServicioPacientes(repositorioPacientes);
        ServicioMedicos servicioMedicos = new ServicioMedicos(repositorioMedicos);
        ServicioCitas servicioCitas = new ServicioCitas(repositorioCitas);
        ServicioFacturacion servicioFacturacion = new ServicioFacturacion();

        HiloNotificaciones hiloNotificaciones = new HiloNotificaciones();
        hiloNotificaciones.start();
        ObservadorCitaNotificacion observador = new ObservadorCitaNotificacion(hiloNotificaciones);
        FachadaAgendamiento fachadaAgendamiento = new FachadaAgendamiento(
                servicioPacientes, servicioMedicos, servicioCitas, observador);

        ContadorCodigos contadorPacientes = new ContadorCodigos("P");
        ContadorCodigos contadorCitas = new ContadorCodigos("C");

        HistoriaClinica historiaAna = new ConstructorHistoriaClinica()
                .conAntecedentes("Hipertension controlada")
                .conAlergias("Ninguna conocida")
                .construir();
        Paciente ana = new Paciente("Ana Torres", contadorPacientes.siguienteCodigo(), 34, historiaAna);
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioPacientes.registrarPaciente(ana);
        servicioMedicos.registrarMedico(carla);

        String codigoCita = contadorCitas.siguienteCodigo();
        Cita cita = fachadaAgendamiento.agendar(
                codigoCita, ana.getCodigo(), carla.getCodigo(), LocalDate.of(2026, 10, 5));

        servicioCitas.confirmarCita(codigoCita);
        servicioCitas.atenderCita(codigoCita, "Control de presion arterial normal");
        System.out.println(cita);
        System.out.println(ana.getHistoriaClinica());

        EstrategiaCosto estrategiaCosto = new EstrategiaCostoConsultaGeneral();
        Factura factura = servicioFacturacion.emitirFactura(cita, estrategiaCosto);
        Factura facturaConDescuento = new FacturaConDescuentoAfiliado(factura);
        System.out.println(facturaConDescuento);

        hiloNotificaciones.detener();
        hiloNotificaciones.join();
    }
}
```

📖 **Explicación**: `ContadorCodigos` usa `AtomicLong` para generar códigos únicos sin
colisión aunque varios hilos lo llamen a la vez; los tres repositorios en memoria y
`HiloNotificaciones` agregan `synchronized` a sus métodos de modificación y lectura,
protegiendo el acceso concurrente al mismo `ArrayList`/`Map` (el repositorio, llamado
desde `main`) o a la misma lista de pendientes (`HiloNotificaciones`, leída por su
propio hilo y escrita por `ObservadorCitaNotificacion` desde `main`) — resuelve el Edge
Case de dos hilos modificando datos compartidos al mismo tiempo, y es el aporte del
Módulo 16.

---

### Paso 17 — Persistencia en archivo (Módulo 17)

#### Archivo: Persona.java

```java
package com.medisalud.entity;

import java.io.Serializable;

public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreCompleto;
    private String codigo;

    protected Persona(String nombreCompleto, String codigo) {
        setNombreCompleto(nombreCompleto);
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        }
        this.nombreCompleto = nombreCompleto;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombreCompleto;
    }
}
```

#### Archivo: HistoriaClinica.java

```java
package com.medisalud.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica implements Serializable {

    private static final long serialVersionUID = 1L;

    private String antecedentes;
    private String alergias;
    private String observaciones;
    private final List<String> consultas = new ArrayList<>();

    public HistoriaClinica() {
    }

    public HistoriaClinica(String antecedentes, String alergias, String observaciones) {
        this.antecedentes = antecedentes;
        this.alergias = alergias;
        this.observaciones = observaciones;
    }

    public void agregarConsulta(String diagnostico) {
        consultas.add(diagnostico);
    }

    public List<String> getConsultas() {
        return consultas;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getObservaciones() {
        return observaciones;
    }

    @Override
    public String toString() {
        return "Historia clinica: " + consultas.size() + " consulta(s) registrada(s)";
    }
}
```

#### Archivo: Cita.java

```java
package com.medisalud.entity;

import com.medisalud.patron.comportamiento.ObservadorCita;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String codigo;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDate fecha;
    private EstadoCita estado;
    private transient List<ObservadorCita> observadores = new ArrayList<>();

    public Cita(String codigo, Paciente paciente, Medico medico, LocalDate fecha) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.estado = EstadoCita.PENDIENTE;
    }

    public void agregarObservador(ObservadorCita observador) {
        observadores.add(observador);
    }

    public void cambiarEstado(EstadoCita nuevoEstado) {
        this.estado = nuevoEstado;
        for (ObservadorCita observador : observadores) {
            observador.notificarCambioEstado(this);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Cita " + codigo + " [" + estado + "] " + paciente.getNombreCompleto()
                + " con " + medico.getNombreCompleto() + " el " + fecha;
    }

    private void readObject(ObjectInputStream entrada) throws IOException, ClassNotFoundException {
        entrada.defaultReadObject();
        observadores = new ArrayList<>();
    }
}
```

#### Archivo: AlmacenPacientesArchivo.java

```java
package com.medisalud.persistencia.archivo;

import com.medisalud.entity.Paciente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlmacenPacientesArchivo {

    private final String rutaArchivo;

    public AlmacenPacientesArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarTodos(List<Paciente> pacientes) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Paciente paciente : pacientes) {
                String linea = paciente.getCodigo() + "|" + paciente.getNombreCompleto() + "|" + paciente.getEdad();
                escritor.write(linea);
                escritor.newLine();
            }
        }
    }

    public List<Paciente> cargarTodos() throws IOException {
        List<Paciente> pacientes = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return pacientes;
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea = lector.readLine();
            while (linea != null) {
                String[] campos = linea.split("\\|");
                String codigo = campos[0];
                String nombreCompleto = campos[1];
                int edad = Integer.parseInt(campos[2]);
                pacientes.add(new Paciente(nombreCompleto, codigo, edad));
                linea = lector.readLine();
            }
        }
        return pacientes;
    }
}
```

#### Archivo: AlmacenCitasSerializado.java

```java
package com.medisalud.persistencia.archivo;

import com.medisalud.entity.Cita;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AlmacenCitasSerializado {

    private final String rutaArchivo;

    public AlmacenCitasSerializado(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarTodas(List<Cita> citas) throws IOException {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            escritor.writeObject(new ArrayList<>(citas));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Cita> cargarTodas() throws IOException, ClassNotFoundException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Cita>) lector.readObject();
        }
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.concurrencia.ContadorCodigos;
import com.medisalud.concurrencia.HiloNotificaciones;
import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.entity.HistoriaClinica;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.patron.comportamiento.EstrategiaCosto;
import com.medisalud.patron.comportamiento.EstrategiaCostoConsultaGeneral;
import com.medisalud.patron.comportamiento.ObservadorCitaNotificacion;
import com.medisalud.patron.creacional.ConstructorHistoriaClinica;
import com.medisalud.patron.creacional.GestorClinica;
import com.medisalud.patron.estructural.FachadaAgendamiento;
import com.medisalud.patron.estructural.FacturaConDescuentoAfiliado;
import com.medisalud.persistencia.archivo.AlmacenCitasSerializado;
import com.medisalud.persistencia.archivo.AlmacenPacientesArchivo;
import com.medisalud.repository.RepositorioCitasMemoria;
import com.medisalud.repository.RepositorioMedicosMemoria;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioFacturacion;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;
import java.util.List;

public class Principal {

    private static final String ARCHIVO_PACIENTES = "pacientes.txt";
    private static final String ARCHIVO_CITAS = "citas.ser";

    public static void main(String[] args) throws Exception {
        RepositorioPacientesMemoria repositorioPacientes = new RepositorioPacientesMemoria();
        RepositorioMedicosMemoria repositorioMedicos = new RepositorioMedicosMemoria();
        RepositorioCitasMemoria repositorioCitas = new RepositorioCitasMemoria();
        GestorClinica.inicializar(repositorioPacientes, repositorioMedicos, repositorioCitas);

        AlmacenPacientesArchivo almacenPacientes = new AlmacenPacientesArchivo(ARCHIVO_PACIENTES);
        AlmacenCitasSerializado almacenCitas = new AlmacenCitasSerializado(ARCHIVO_CITAS);

        System.out.println("== Cargar datos previos ==");
        for (Paciente paciente : almacenPacientes.cargarTodos()) {
            repositorioPacientes.guardar(paciente);
        }
        for (Cita cita : almacenCitas.cargarTodas()) {
            repositorioCitas.guardar(cita);
        }
        System.out.println("Pacientes cargados: " + repositorioPacientes.listarTodos().size());

        ServicioPacientes servicioPacientes = new ServicioPacientes(repositorioPacientes);
        ServicioMedicos servicioMedicos = new ServicioMedicos(repositorioMedicos);
        ServicioCitas servicioCitas = new ServicioCitas(repositorioCitas);
        ServicioFacturacion servicioFacturacion = new ServicioFacturacion();

        HiloNotificaciones hiloNotificaciones = new HiloNotificaciones();
        hiloNotificaciones.start();
        ObservadorCitaNotificacion observador = new ObservadorCitaNotificacion(hiloNotificaciones);
        FachadaAgendamiento fachadaAgendamiento = new FachadaAgendamiento(
                servicioPacientes, servicioMedicos, servicioCitas, observador);

        ContadorCodigos contadorPacientes = new ContadorCodigos("P");
        ContadorCodigos contadorCitas = new ContadorCodigos("C");

        System.out.println("== Registrar paciente ==");
        HistoriaClinica historiaAna = new ConstructorHistoriaClinica()
                .conAntecedentes("Hipertension controlada")
                .conAlergias("Ninguna conocida")
                .construir();
        Paciente ana = new Paciente("Ana Torres", contadorPacientes.siguienteCodigo(), 34, historiaAna);
        servicioPacientes.registrarPaciente(ana);
        System.out.println(ana);

        System.out.println("== Registrar medico ==");
        Medico carla = new Medico("Carla Gomez", "M001", "Medicina general");
        servicioMedicos.registrarMedico(carla);
        System.out.println(carla);

        System.out.println("== Agendar cita ==");
        String codigoCita = contadorCitas.siguienteCodigo();
        Cita cita = fachadaAgendamiento.agendar(
                codigoCita, ana.getCodigo(), carla.getCodigo(), LocalDate.of(2026, 10, 5));
        servicioCitas.confirmarCita(codigoCita);
        servicioCitas.atenderCita(codigoCita, "Control de presion arterial normal");
        System.out.println(cita);

        System.out.println("== Consultar historia clinica ==");
        System.out.println(ana.getHistoriaClinica());

        System.out.println("== Facturar consulta ==");
        EstrategiaCosto estrategiaCosto = new EstrategiaCostoConsultaGeneral();
        Factura factura = servicioFacturacion.emitirFactura(cita, estrategiaCosto);
        Factura facturaConDescuento = new FacturaConDescuentoAfiliado(factura);
        System.out.println(facturaConDescuento);

        System.out.println("== Generar reporte ==");
        List<Cita> citasDeCarla = servicioCitas.listarPorMedico(carla.getCodigo());
        System.out.println("Citas de Carla Gomez: " + citasDeCarla.size());

        System.out.println("== Guardar datos ==");
        almacenPacientes.guardarTodos(repositorioPacientes.listarTodos());
        almacenCitas.guardarTodas(repositorioCitas.listarTodas());

        hiloNotificaciones.detener();
        hiloNotificaciones.join();

        System.out.println("== Salir ==");
        System.out.println("Cerrando el Sistema de Gestion de Citas Medicas MediSalud. Hasta pronto.");
    }
}
```

📖 **Explicación**: `Persona`, `HistoriaClinica` y `Cita` implementan `Serializable`
para poder guardarse como objetos; `Cita.observadores` se marca `transient` (un
`ObservadorCita` real no se puede serializar) y se reconstruye vacío en un `readObject`
propio al recargar. `AlmacenPacientesArchivo` guarda/lee `pacientes.txt` línea por línea
con `BufferedReader`/`BufferedWriter`; `AlmacenCitasSerializado` guarda/lee el grafo
completo de citas con `ObjectOutputStream`/`ObjectInputStream` — las dos técnicas del
Módulo 17. `Principal` ahora carga los datos previos al iniciar y los guarda al cerrar,
manejando sin fallar el caso de que los archivos no existan todavía (primer arranque).
Esta es la versión final de `Principal.java` para los Pasos 1 a 17: a partir del
Paso 18 la persistencia JDBC necesita un segundo punto de entrada, explicado ahí.

---

### Paso 18 — JDBC, MySQL y arquitectura MVC (Módulo 18)

Antes de este paso, asegurate de tener un servidor MySQL local disponible (la misma
dependencia que ya exige el Módulo 18) y de crear la base de datos:

```sql
CREATE DATABASE IF NOT EXISTS medisalud;
USE medisalud;

CREATE TABLE pacientes (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    edad INT NOT NULL
);

CREATE TABLE medicos (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    especialidad VARCHAR(50) NOT NULL
);

CREATE TABLE citas (
    codigo VARCHAR(20) PRIMARY KEY,
    paciente_codigo VARCHAR(20) NOT NULL REFERENCES pacientes(codigo),
    medico_codigo VARCHAR(20) NOT NULL REFERENCES medicos(codigo),
    fecha DATE NOT NULL,
    estado VARCHAR(20) NOT NULL
);
```

Agregá `mysql-connector-j` como dependencia del proyecto en Visual Studio Code.

#### Archivo: ConexionBD.java

```java
package com.medisalud.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
```

#### Archivo: PacienteDAO.java

```java
package com.medisalud.persistencia.jdbc;

import com.medisalud.entity.Paciente;
import com.medisalud.repository.RepositorioPacientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements RepositorioPacientes {

    @Override
    public void guardar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (codigo, nombre_completo, edad) VALUES (?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, paciente.getCodigo());
            sentencia.setString(2, paciente.getNombreCompleto());
            sentencia.setInt(3, paciente.getEdad());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo guardar el paciente " + paciente.getCodigo(), excepcion);
        }
    }

    @Override
    public Paciente buscarPorCodigo(String codigo) {
        String sql = "SELECT codigo, nombre_completo, edad FROM pacientes WHERE codigo = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, codigo);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapearPaciente(resultado);
                }
                return null;
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo buscar el paciente " + codigo, excepcion);
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT codigo, nombre_completo, edad FROM pacientes";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                pacientes.add(mapearPaciente(resultado));
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo listar los pacientes", excepcion);
        }
        return pacientes;
    }

    private Paciente mapearPaciente(ResultSet resultado) throws SQLException {
        String codigo = resultado.getString("codigo");
        String nombreCompleto = resultado.getString("nombre_completo");
        int edad = resultado.getInt("edad");
        return new Paciente(nombreCompleto, codigo, edad);
    }
}
```

#### Archivo: MedicoDAO.java

```java
package com.medisalud.persistencia.jdbc;

import com.medisalud.entity.Medico;
import com.medisalud.repository.RepositorioMedicos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO implements RepositorioMedicos {

    @Override
    public void guardar(Medico medico) {
        String sql = "INSERT INTO medicos (codigo, nombre_completo, especialidad) VALUES (?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, medico.getCodigo());
            sentencia.setString(2, medico.getNombreCompleto());
            sentencia.setString(3, medico.getEspecialidad());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo guardar el medico " + medico.getCodigo(), excepcion);
        }
    }

    @Override
    public Medico buscarPorCodigo(String codigo) {
        String sql = "SELECT codigo, nombre_completo, especialidad FROM medicos WHERE codigo = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, codigo);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapearMedico(resultado);
                }
                return null;
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo buscar el medico " + codigo, excepcion);
        }
    }

    @Override
    public List<Medico> listarTodos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT codigo, nombre_completo, especialidad FROM medicos";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                medicos.add(mapearMedico(resultado));
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo listar los medicos", excepcion);
        }
        return medicos;
    }

    private Medico mapearMedico(ResultSet resultado) throws SQLException {
        String codigo = resultado.getString("codigo");
        String nombreCompleto = resultado.getString("nombre_completo");
        String especialidad = resultado.getString("especialidad");
        return new Medico(nombreCompleto, codigo, especialidad);
    }
}
```

#### Archivo: CitaDAO.java

```java
package com.medisalud.persistencia.jdbc;

import com.medisalud.entity.Cita;
import com.medisalud.entity.EstadoCita;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.repository.RepositorioCitas;
import com.medisalud.repository.RepositorioMedicos;
import com.medisalud.repository.RepositorioPacientes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO implements RepositorioCitas {

    private final RepositorioPacientes repositorioPacientes;
    private final RepositorioMedicos repositorioMedicos;

    public CitaDAO(RepositorioPacientes repositorioPacientes, RepositorioMedicos repositorioMedicos) {
        this.repositorioPacientes = repositorioPacientes;
        this.repositorioMedicos = repositorioMedicos;
    }

    @Override
    public void guardar(Cita cita) {
        String sql = "INSERT INTO citas (codigo, paciente_codigo, medico_codigo, fecha, estado) "
                + "VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE estado = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, cita.getCodigo());
            sentencia.setString(2, cita.getPaciente().getCodigo());
            sentencia.setString(3, cita.getMedico().getCodigo());
            sentencia.setDate(4, Date.valueOf(cita.getFecha()));
            sentencia.setString(5, cita.getEstado().name());
            sentencia.setString(6, cita.getEstado().name());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo guardar la cita " + cita.getCodigo(), excepcion);
        }
    }

    @Override
    public Cita buscarPorCodigo(String codigo) {
        String sql = "SELECT codigo, paciente_codigo, medico_codigo, fecha, estado FROM citas WHERE codigo = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, codigo);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapearCita(resultado);
                }
                return null;
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo buscar la cita " + codigo, excepcion);
        }
    }

    @Override
    public List<Cita> listarTodas() {
        String sql = "SELECT codigo, paciente_codigo, medico_codigo, fecha, estado FROM citas";
        return listarConFiltro(sql, null);
    }

    @Override
    public List<Cita> listarPorMedico(String codigoMedico) {
        String sql = "SELECT codigo, paciente_codigo, medico_codigo, fecha, estado FROM citas "
                + "WHERE medico_codigo = ?";
        return listarConFiltro(sql, codigoMedico);
    }

    private List<Cita> listarConFiltro(String sql, String codigoMedico) {
        List<Cita> citas = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            if (codigoMedico != null) {
                sentencia.setString(1, codigoMedico);
            }
            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    citas.add(mapearCita(resultado));
                }
            }
        } catch (SQLException excepcion) {
            throw new RuntimeException("No se pudo listar las citas", excepcion);
        }
        return citas;
    }

    private Cita mapearCita(ResultSet resultado) throws SQLException {
        String codigo = resultado.getString("codigo");
        Paciente paciente = repositorioPacientes.buscarPorCodigo(resultado.getString("paciente_codigo"));
        Medico medico = repositorioMedicos.buscarPorCodigo(resultado.getString("medico_codigo"));
        LocalDate fecha = resultado.getDate("fecha").toLocalDate();
        Cita cita = new Cita(codigo, paciente, medico, fecha);
        cita.cambiarEstado(EstadoCita.valueOf(resultado.getString("estado")));
        return cita;
    }
}
```

#### Archivo: ControladorMediSalud.java

```java
package com.medisalud.controlador;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.entity.HistoriaClinica;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.MedicoNoEncontradoException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.exception.TransicionInvalidaException;
import com.medisalud.patron.comportamiento.EstrategiaCosto;
import com.medisalud.patron.estructural.FachadaAgendamiento;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioFacturacion;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ControladorMediSalud {

    private final ServicioPacientes servicioPacientes;
    private final ServicioMedicos servicioMedicos;
    private final ServicioCitas servicioCitas;
    private final ServicioFacturacion servicioFacturacion;
    private final FachadaAgendamiento fachadaAgendamiento;

    public ControladorMediSalud(ServicioPacientes servicioPacientes,
                                 ServicioMedicos servicioMedicos,
                                 ServicioCitas servicioCitas,
                                 ServicioFacturacion servicioFacturacion,
                                 FachadaAgendamiento fachadaAgendamiento) {
        this.servicioPacientes = servicioPacientes;
        this.servicioMedicos = servicioMedicos;
        this.servicioCitas = servicioCitas;
        this.servicioFacturacion = servicioFacturacion;
        this.fachadaAgendamiento = fachadaAgendamiento;
    }

    public void registrarPaciente(Paciente paciente) {
        servicioPacientes.registrarPaciente(paciente);
    }

    public void registrarMedico(Medico medico) {
        servicioMedicos.registrarMedico(medico);
    }

    public Cita agendarCita(String codigoCita, String codigoPaciente, String codigoMedico, LocalDate fecha)
            throws PacienteNoEncontradoException, MedicoNoEncontradoException {
        return fachadaAgendamiento.agendar(codigoCita, codigoPaciente, codigoMedico, fecha);
    }

    public void confirmarCita(String codigoCita) throws CitaNoEncontradaException, TransicionInvalidaException {
        servicioCitas.confirmarCita(codigoCita);
    }

    public void atenderCita(String codigoCita, String diagnostico)
            throws CitaNoEncontradaException, TransicionInvalidaException {
        servicioCitas.atenderCita(codigoCita, diagnostico);
    }

    public HistoriaClinica consultarHistoriaClinica(String codigoPaciente) throws PacienteNoEncontradoException {
        Paciente paciente = servicioPacientes.buscarPorCodigo(codigoPaciente);
        return paciente.getHistoriaClinica();
    }

    public Factura facturarCita(String codigoCita, EstrategiaCosto estrategiaCosto) throws CitaNoEncontradaException {
        Cita cita = servicioCitas.buscarPorCodigo(codigoCita);
        return servicioFacturacion.emitirFactura(cita, estrategiaCosto);
    }

    public List<Cita> generarReportePorMedico(String codigoMedico) {
        return servicioCitas.listarPorMedico(codigoMedico);
    }

    public Map<String, Double> generarReporteFacturacionPorMedico() {
        return servicioFacturacion.totalFacturadoPorMedico();
    }

    public List<Paciente> listarPacientesMayoresDeEdad(int edadMinima) {
        return servicioPacientes.listarMayoresDeEdad(edadMinima);
    }
}
```

#### Archivo: VistaConsola.java

```java
package com.medisalud.vista;

import com.medisalud.controlador.ControladorMediSalud;
import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.entity.HistoriaClinica;
import com.medisalud.entity.Medico;
import com.medisalud.entity.Paciente;
import com.medisalud.exception.CitaNoEncontradaException;
import com.medisalud.exception.MedicoNoEncontradoException;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.exception.TransicionInvalidaException;
import com.medisalud.patron.comportamiento.EstrategiaCosto;
import com.medisalud.patron.comportamiento.EstrategiaCostoConsultaEspecialista;
import com.medisalud.patron.comportamiento.EstrategiaCostoConsultaGeneral;
import com.medisalud.patron.creacional.ConstructorHistoriaClinica;
import com.medisalud.patron.estructural.FacturaConDescuentoAfiliado;
import com.medisalud.patron.estructural.FacturaConRecargoNocturno;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VistaConsola {

    private final ControladorMediSalud controlador;
    private final Scanner lector = new Scanner(System.in);

    public VistaConsola(ControladorMediSalud controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("=== Menu principal - MediSalud ===");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar medico");
            System.out.println("3. Agendar cita");
            System.out.println("4. Consultar historia clinica");
            System.out.println("5. Facturar consulta");
            System.out.println("6. Generar reporte");
            System.out.println("7. Salir");
            System.out.print("Elegir una opcion: ");
            String opcion = lector.nextLine();

            switch (opcion) {
                case "1":
                    registrarPaciente();
                    break;
                case "2":
                    registrarMedico();
                    break;
                case "3":
                    agendarCita();
                    break;
                case "4":
                    consultarHistoriaClinica();
                    break;
                case "5":
                    facturarConsulta();
                    break;
                case "6":
                    generarReporte();
                    break;
                case "7":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
        System.out.println("Cerrando el Sistema de Gestion de Citas Medicas MediSalud. Hasta pronto.");
    }

    private void registrarPaciente() {
        try {
            System.out.print("Nombre completo: ");
            String nombreCompleto = lector.nextLine();
            System.out.print("Codigo: ");
            String codigo = lector.nextLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(lector.nextLine());

            System.out.print("Desea registrar antecedentes y alergias? (s/n): ");
            Paciente paciente;
            if (lector.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Antecedentes: ");
                String antecedentes = lector.nextLine();
                System.out.print("Alergias: ");
                String alergias = lector.nextLine();
                HistoriaClinica historia = new ConstructorHistoriaClinica()
                        .conAntecedentes(antecedentes)
                        .conAlergias(alergias)
                        .construir();
                paciente = new Paciente(nombreCompleto, codigo, edad, historia);
            } else {
                paciente = new Paciente(nombreCompleto, codigo, edad);
            }

            controlador.registrarPaciente(paciente);
            System.out.println("Paciente registrado: " + paciente);
        } catch (IllegalArgumentException excepcion) {
            System.out.println("No se pudo registrar el paciente: " + excepcion.getMessage());
        }
    }

    private void registrarMedico() {
        try {
            System.out.print("Nombre completo: ");
            String nombreCompleto = lector.nextLine();
            System.out.print("Codigo: ");
            String codigo = lector.nextLine();
            System.out.print("Especialidad: ");
            String especialidad = lector.nextLine();

            Medico medico = new Medico(nombreCompleto, codigo, especialidad);
            controlador.registrarMedico(medico);
            System.out.println("Medico registrado: " + medico);
        } catch (IllegalArgumentException excepcion) {
            System.out.println("No se pudo registrar el medico: " + excepcion.getMessage());
        }
    }

    private void agendarCita() {
        try {
            System.out.print("Codigo de la cita: ");
            String codigoCita = lector.nextLine();
            System.out.print("Codigo del paciente: ");
            String codigoPaciente = lector.nextLine();
            System.out.print("Codigo del medico: ");
            String codigoMedico = lector.nextLine();
            System.out.print("Fecha (aaaa-mm-dd): ");
            LocalDate fecha = LocalDate.parse(lector.nextLine());

            Cita cita = controlador.agendarCita(codigoCita, codigoPaciente, codigoMedico, fecha);
            System.out.println("Cita agendada: " + cita);

            System.out.print("Confirmar la cita ahora? (s/n): ");
            if (lector.nextLine().equalsIgnoreCase("s")) {
                controlador.confirmarCita(codigoCita);
                System.out.print("Registrar atencion con diagnostico? (s/n): ");
                if (lector.nextLine().equalsIgnoreCase("s")) {
                    System.out.print("Diagnostico: ");
                    String diagnostico = lector.nextLine();
                    controlador.atenderCita(codigoCita, diagnostico);
                }
                System.out.println("Estado actualizado: " + cita.getEstado());
            }
        } catch (PacienteNoEncontradoException | MedicoNoEncontradoException
                | CitaNoEncontradaException | TransicionInvalidaException | DateTimeParseException excepcion) {
            System.out.println("No se pudo agendar la cita: " + excepcion.getMessage());
        }
    }

    private void consultarHistoriaClinica() {
        try {
            System.out.print("Codigo del paciente: ");
            String codigoPaciente = lector.nextLine();
            HistoriaClinica historia = controlador.consultarHistoriaClinica(codigoPaciente);
            System.out.println(historia);
            if (historia.getConsultas().isEmpty()) {
                System.out.println("Sin consultas registradas todavia.");
            }
            for (String consulta : historia.getConsultas()) {
                System.out.println("- " + consulta);
            }
        } catch (PacienteNoEncontradoException excepcion) {
            System.out.println("No se pudo consultar: " + excepcion.getMessage());
        }
    }

    private void facturarConsulta() {
        try {
            System.out.print("Codigo de la cita: ");
            String codigoCita = lector.nextLine();
            System.out.print("Tipo de consulta (G=general, E=especialista): ");
            String tipo = lector.nextLine();
            EstrategiaCosto estrategiaCosto = tipo.equalsIgnoreCase("E")
                    ? new EstrategiaCostoConsultaEspecialista()
                    : new EstrategiaCostoConsultaGeneral();

            Factura factura = controlador.facturarCita(codigoCita, estrategiaCosto);

            System.out.print("Es paciente afiliado? (s/n): ");
            if (lector.nextLine().equalsIgnoreCase("s")) {
                factura = new FacturaConDescuentoAfiliado(factura);
            }
            System.out.print("Es horario nocturno? (s/n): ");
            if (lector.nextLine().equalsIgnoreCase("s")) {
                factura = new FacturaConRecargoNocturno(factura);
            }

            System.out.println(factura);
        } catch (CitaNoEncontradaException excepcion) {
            System.out.println("No se pudo facturar: " + excepcion.getMessage());
        }
    }

    private void generarReporte() {
        System.out.println("-- Reportes --");
        try {
            System.out.print("Codigo del medico para ver sus citas: ");
            String codigoMedico = lector.nextLine();
            List<Cita> citas = controlador.generarReportePorMedico(codigoMedico);
            System.out.println("Citas de " + codigoMedico + ": " + citas.size());
            for (Cita cita : citas) {
                System.out.println("- " + cita);
            }

            Map<String, Double> totalPorMedico = controlador.generarReporteFacturacionPorMedico();
            System.out.println("Total facturado por medico: " + totalPorMedico);

            System.out.print("Edad minima para el reporte de pacientes: ");
            int edadMinima = Integer.parseInt(lector.nextLine());
            List<Paciente> mayores = controlador.listarPacientesMayoresDeEdad(edadMinima);
            System.out.println("Pacientes con " + edadMinima + " anios o mas: " + mayores);
        } catch (NumberFormatException excepcion) {
            System.out.println("Edad invalida: " + excepcion.getMessage());
        }
    }
}
```

#### Archivo: Principal.java

```java
package com.medisalud;

import com.medisalud.concurrencia.HiloNotificaciones;
import com.medisalud.controlador.ControladorMediSalud;
import com.medisalud.patron.comportamiento.ObservadorCitaNotificacion;
import com.medisalud.patron.creacional.GestorClinica;
import com.medisalud.patron.estructural.FachadaAgendamiento;
import com.medisalud.persistencia.jdbc.CitaDAO;
import com.medisalud.persistencia.jdbc.MedicoDAO;
import com.medisalud.persistencia.jdbc.PacienteDAO;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioFacturacion;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import com.medisalud.vista.VistaConsola;

public class Principal {

    public static void main(String[] args) throws InterruptedException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        CitaDAO citaDAO = new CitaDAO(pacienteDAO, medicoDAO);
        GestorClinica.inicializar(pacienteDAO, medicoDAO, citaDAO);

        ServicioPacientes servicioPacientes = new ServicioPacientes(pacienteDAO);
        ServicioMedicos servicioMedicos = new ServicioMedicos(medicoDAO);
        ServicioCitas servicioCitas = new ServicioCitas(citaDAO);
        ServicioFacturacion servicioFacturacion = new ServicioFacturacion();

        HiloNotificaciones hiloNotificaciones = new HiloNotificaciones();
        hiloNotificaciones.start();
        ObservadorCitaNotificacion observador = new ObservadorCitaNotificacion(hiloNotificaciones);
        FachadaAgendamiento fachadaAgendamiento = new FachadaAgendamiento(
                servicioPacientes, servicioMedicos, servicioCitas, observador);

        ControladorMediSalud controlador = new ControladorMediSalud(
                servicioPacientes, servicioMedicos, servicioCitas, servicioFacturacion, fachadaAgendamiento);

        VistaConsola vista = new VistaConsola(controlador);
        vista.mostrarMenu();

        hiloNotificaciones.detener();
        hiloNotificaciones.join();
    }
}
```

📖 **Explicación**: `PacienteDAO`/`MedicoDAO`/`CitaDAO` implementan las **mismas**
interfaces `RepositorioPacientes`/`RepositorioMedicos`/`RepositorioCitas` del Paso 11,
pero con `PreparedStatement` sobre MySQL en vez de colecciones en memoria — ningún
`Servicio*` cambia una sola línea, la inversión de dependencias del Módulo 11 es la que
hace esto posible. `VistaConsola` (la V) y `ControladorMediSalud` (la C) son la
reorganización explícita en Modelo-Vista-Controlador que exige el Módulo 18: el
"modelo" son las clases `entity`/`service`/`repository` que ya existían.
`VistaConsola.mostrarMenu()` usa `Scanner` para leer la opción elegida (1-7) en un
bucle **real** — la única clase de todo el proyecto que lee la consola, justo porque es
la única capa de MVC que debe hacerlo (research.md D4). `Principal.java` se reescribe
por completo en este paso (Edge Case de la spec: un paso posterior puede reemplazar
toda una clase anterior) para delegar en `VistaConsola`: sigue siendo el **único** punto
de entrada del proyecto, de principio a fin.

---

### Paso 19 — Lambdas y Predicate (Módulo 19)

#### Archivo: ServicioPacientes.java

```java
package com.medisalud.service;

import com.medisalud.entity.Paciente;
import com.medisalud.exception.PacienteNoEncontradoException;
import com.medisalud.repository.RepositorioPacientes;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServicioPacientes {

    private final RepositorioPacientes repositorioPacientes;

    public ServicioPacientes(RepositorioPacientes repositorioPacientes) {
        this.repositorioPacientes = repositorioPacientes;
    }

    public void registrarPaciente(Paciente paciente) {
        repositorioPacientes.guardar(paciente);
    }

    public Paciente buscarPorCodigo(String codigo) throws PacienteNoEncontradoException {
        Paciente paciente = repositorioPacientes.buscarPorCodigo(codigo);
        if (paciente == null) {
            throw new PacienteNoEncontradoException(codigo);
        }
        return paciente;
    }

    public List<Paciente> listarTodos() {
        return repositorioPacientes.listarTodos();
    }

    public List<Paciente> listarMayoresDeEdad(int edadMinima) {
        Predicate<Paciente> esMayorDeEdad = paciente -> paciente.getEdad() >= edadMinima;
        return repositorioPacientes.listarTodos().stream()
                .filter(esMayorDeEdad)
                .collect(Collectors.toList());
    }
}
```

📖 **Explicación**: el filtro de pacientes mayores de cierta edad, que desde el Paso 9
recorría la lista con un `for` y un `if` acumulando en una lista aparte, se reemplaza
por un `Predicate<Paciente>` (interfaz funcional del Módulo 19) aplicado con
`Stream.filter`. `ControladorMediSalud.listarPacientesMayoresDeEdad` (Paso 18) no
cambia ni una línea: llama al mismo método público, que ahora resuelve el filtro por
dentro de otra forma, con el mismo resultado observable (mismos pacientes, mismo
orden).

---

### Paso 20 — Stream API y reportes (Módulo 20)

#### Archivo: ServicioFacturacion.java

```java
package com.medisalud.service;

import com.medisalud.entity.Cita;
import com.medisalud.entity.Factura;
import com.medisalud.patron.comportamiento.EstrategiaCosto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServicioFacturacion {

    private final List<Factura> facturasEmitidas = new ArrayList<>();

    public Factura emitirFactura(Cita cita, EstrategiaCosto estrategiaCosto) {
        double montoBase = estrategiaCosto.calcularCosto(cita);
        Factura factura = new Factura(cita, montoBase);
        facturasEmitidas.add(factura);
        return factura;
    }

    public List<Factura> listarFacturas() {
        return new ArrayList<>(facturasEmitidas);
    }

    public Map<String, Double> totalFacturadoPorMedico() {
        return facturasEmitidas.stream()
                .collect(Collectors.groupingBy(
                        factura -> factura.getCita().getMedico().getCodigo(),
                        Collectors.summingDouble(Factura::calcularMonto)));
    }
}
```

📖 **Explicación**: el total facturado por médico, que desde el Paso 14 acumulaba en un
`Map<String, Double>` recorriendo `facturasEmitidas` con un `for` manual
(`totales.put(codigo, totalActual + monto)`), se reemplaza por un único *pipeline* de
Stream API: `collect(Collectors.groupingBy(..., Collectors.summingDouble(...)))` agrupa
por código de médico y suma los montos en el mismo paso — el cierre del curso con el
Módulo 20. `ControladorMediSalud.generarReporteFacturacionPorMedico` (Paso 18) sigue
llamando al mismo método público: el `Map<String, Double>` que recibe tiene las mismas
claves y los mismos totales que con la versión anterior basada en bucles (FR-011,
SC-007).

---

## 🏁 Verificación final

- [ ] El proyecto `SistemaMediSaludFinal` compila y ejecuta `Principal` sin errores, y
  muestra el menú interactivo de 7 opciones.
- [ ] Desde el menú se puede registrar un paciente y un médico, y los datos quedan
  guardados.
- [ ] Desde el menú se puede agendar, confirmar y atender una cita, y la historia
  clínica del paciente queda con el diagnóstico registrado.
- [ ] Se puede facturar una consulta desde el menú y ver aplicados un recargo o un
  descuento (Decorator) sobre el monto base calculado por una `EstrategiaCosto`
  (Strategy).
- [ ] Al cerrar el programa (opción 7) y volver a ejecutarlo, los datos de la ejecución
  anterior siguen presentes en las tablas de MySQL (persistencia entre ejecuciones).
- [ ] Buscar un paciente, médico o cita con un código inexistente, o elegir una opción
  de menú no válida, muestra un mensaje claro sin detener el programa.
- [ ] El reporte de citas por médico y el de total facturado por médico muestran datos
  correctos, calculados con `Stream`/`Predicate` en vez de bucles manuales.
- [ ] `HiloNotificaciones` imprime las notificaciones de cambio de estado de una cita en
  segundo plano, sin bloquear el menú, y se detiene de forma ordenada al elegir "Salir".

