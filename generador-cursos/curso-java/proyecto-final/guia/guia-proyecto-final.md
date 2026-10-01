# 📘 Proyecto final — Sistema de Gestión de Citas Médicas MediSalud

## 🎯 Objetivo

Construir, en un solo recorrido, la aplicación de consola completa que integra todo lo
aprendido en el curso: variables, decisiones y bucles, cadenas, programación orientada a
objetos, colecciones, relaciones entre clases, SOLID, patrones de diseño, concurrencia,
archivos, JDBC con arquitectura Modelo-Vista-Controlador, lambdas y Stream API.

## 🌍 Contexto

MediSalud, la red de clínicas que ya conocés de los ejemplos del curso, necesita un
sistema de consola para gestionar las citas médicas de sus pacientes: registrar
pacientes y médicos, agendar y dar seguimiento a sus citas, mantener la historia clínica
de cada paciente, calcular y facturar el costo de una consulta, notificar a los
pacientes, y guardar todo en una base de datos para que no se pierda al cerrar el
programa.

## ✅ Prerrequisitos

- Haber completado los Módulos 1 a 20 del Curso de Java (esta guía usa, sin volver a
  explicarlos, los conceptos de clases, encapsulamiento, herencia, colecciones, SOLID,
  patrones de diseño, hilos, archivos, JDBC, lambdas y Stream API).
- Tener instalado el JDK 17 o superior.
- Tener instalado Visual Studio Code con la Extension Pack for Java.
- Tener disponible un servidor MySQL local y el conector `mysql-connector-j` (el mismo
  requisito que ya exige el Módulo 18 del curso).

## 🧱 Resultado final

Un proyecto de Visual Studio Code (`SistemaMediSaludFinal`) con **un solo código**: una
aplicación de consola completa, organizada en capas bajo el paquete `com.medisalud`, con
un menú interactivo real, que persiste sus datos en una base de datos MySQL vía JDBC,
aplica patrones de diseño sobre problemas reales del propio proyecto, ejecuta una tarea
en un hilo separado, y resuelve dos operaciones con lambdas y Stream API — exactamente el
mismo código que encontrarás ya ensamblado en `../solucion/`.

## 🌳 Árbol de archivos del proyecto

Así se ve el proyecto completo, de principio a fin — un único proyecto Java
(`SistemaMediSaludFinal`), con un único punto de entrada (`Principal`), organizado en
capas por responsabilidad:

```text
com.medisalud/
├── Principal.java                    # unico punto de entrada
├── entity/
│   ├── EstadoCita.java
│   ├── HistoriaClinica.java
│   ├── Persona.java
│   ├── Paciente.java
│   ├── Medico.java
│   ├── Cita.java
│   └── Factura.java
├── exception/
│   ├── PacienteNoEncontradoException.java
│   ├── MedicoNoEncontradoException.java
│   ├── CitaNoEncontradaException.java
│   └── TransicionInvalidaException.java
├── repository/
│   ├── RepositorioPacientes.java
│   ├── RepositorioMedicos.java
│   ├── RepositorioCitas.java
│   ├── RepositorioPacientesMemoria.java
│   ├── RepositorioMedicosMemoria.java
│   └── RepositorioCitasMemoria.java
├── service/
│   ├── ServicioPacientes.java
│   ├── ServicioMedicos.java
│   ├── ServicioCitas.java
│   └── ServicioFacturacion.java
├── patron/
│   ├── creacional/
│   │   ├── ConstructorHistoriaClinica.java
│   │   └── GestorClinica.java
│   ├── estructural/
│   │   ├── FacturaConRecargoNocturno.java
│   │   ├── FacturaConDescuentoAfiliado.java
│   │   └── FachadaAgendamiento.java
│   └── comportamiento/
│       ├── EstrategiaCosto.java
│       ├── EstrategiaCostoConsultaGeneral.java
│       ├── EstrategiaCostoConsultaEspecialista.java
│       ├── ObservadorCita.java
│       └── ObservadorCitaNotificacion.java
├── concurrencia/
│   ├── ContadorCodigos.java
│   └── HiloNotificaciones.java
├── persistencia/
│   ├── archivo/
│   │   ├── AlmacenPacientesArchivo.java
│   │   └── AlmacenCitasSerializado.java
│   └── jdbc/
│       ├── ConexionBD.java
│       ├── PacienteDAO.java
│       ├── MedicoDAO.java
│       └── CitaDAO.java
├── controlador/
│   └── ControladorMediSalud.java
└── vista/
    └── VistaConsola.java
```

## 📐 Alcance del proyecto

- Aplicación de **consola** (sin interfaz gráfica) para la gestión de citas médicas de
  MediSalud: pacientes, médicos, citas, historias clínicas y facturación.
- Persistencia final en una base de datos MySQL vía JDBC. El proyecto también incluye,
  como parte de su arquitectura en capas, una implementación en memoria de los
  repositorios y una persistencia alternativa en archivo (texto y serializado) — ambas
  usan exactamente las mismas interfaces que la versión JDBC, para mostrar que se puede
  cambiar el almacenamiento sin tocar la capa de servicios (inversión de dependencias).
- Un **único dominio de negocio** (MediSalud); no se usa Biblioteca Universitaria en
  este proyecto.
- Sin frameworks externos al temario del curso (sin Spring, sin un ORM): JDBC puro.
- Un único concepto fuera de los Módulos 1-20: `Scanner`, introducido únicamente en
  `VistaConsola` para el menú interactivo — ver el Bloque 13.

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
  tampoco pueden estar vacíos (se valida en el `set` de cada clase).
- Una `Cita` nace en estado `PENDIENTE` y solo puede seguir las transiciones válidas:
  `PENDIENTE → CONFIRMADA` o `CANCELADA`; `CONFIRMADA → ATENDIDA` o `CANCELADA`;
  `ATENDIDA` y `CANCELADA` son estados finales. Cualquier otra transición se rechaza.
- Buscar un paciente, médico o cita por un código inexistente nunca detiene el
  programa: siempre se informa con un mensaje claro mediante una excepción propia.
- El acceso al repositorio compartido está protegido para el caso de hilos
  concurrentes; las notificaciones de cambio de estado de una cita se procesan en un
  hilo separado, sin bloquear el menú.
- El total facturado por médico y el filtro de pacientes por edad se calculan con
  `Stream`/`Predicate` sobre las colecciones del dominio.

## 📝 Cómo usar esta guía

A diferencia de una guía que avanza módulo por módulo, esta presenta el proyecto
**completo y final de una sola vez**, organizado en bloques de **menor a mayor
dependencia**: el Bloque 1 son las clases que no dependen de ninguna otra clase propia
del proyecto; cada bloque siguiente depende solo de clases de bloques anteriores; el
último bloque es el punto de entrada que conecta todo. Cada bloque muestra el código
**completo** de sus archivos (idéntico al de `../solucion/`), seguido de una explicación
de qué hace y de qué depende. Podés transcribir los bloques en el orden en que aparecen,
o crear directamente todos los archivos del proyecto y compilar al final — a diferencia
de un lenguaje interpretado, Java no exige ningún orden particular para escribir los
archivos, solo para entender cómo se apoyan unos en otros.

---

## 🧱 Construcción del proyecto

### Bloque 1 — Enumeraciones y excepciones

Sin dependencias de ninguna otra clase propia del proyecto (salvo
`TransicionInvalidaException`, que depende del enum de este mismo bloque).

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

📖 **Explicación**: `EstadoCita` es un `enum` (Módulo 10) que además sabe decidir, con
`puedeTransicionarA`, qué transición es válida desde su propio valor (patrón State).
Las cuatro excepciones son excepciones propias del dominio (Módulo 10): tres para datos
no encontrados (ejemplo de FR-012) y una para transiciones de estado inválidas, exigida
por la regla de validación V5. Ninguna de las cinco depende de ninguna otra clase del
proyecto fuera de este bloque.

---

### Bloque 2 — Entidades base: Persona, Paciente, Medico, HistoriaClinica

Dependen solo de tipos de la biblioteca estándar y, entre sí, de `Persona` y
`HistoriaClinica` (ambas del Bloque 1 en espíritu: tampoco dependen de nada propio).

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

📖 **Explicación**: `Persona` (Módulo 7) es la superclase abstracta común de
`Paciente`/`Medico` (herencia + polimorfismo vía `toString()`), con sus atributos
encapsulados y validados (Módulo 6). `HistoriaClinica` (Módulo 9) es independiente y se
compone dentro de `Paciente`: cada paciente crea y posee la suya (composición), con un
segundo constructor que acepta una ya construida — lo usa el patrón Builder del
Bloque 7. `implements Serializable` (Módulo 17) se agrega desde ahora porque estas
clases viajan dentro de `Cita` cuando esta se serializa (Bloque 3).

---

### Bloque 3 — ObservadorCita, Cita y Factura

`ObservadorCita` es una interfaz pequeña que se referencia desde `Cita` (y se
implementa recién en el Bloque 8); `Cita` depende de `Paciente`/`Medico`/`EstadoCita`
(Bloques 1-2); `Factura` depende de `Cita`.

#### Archivo: ObservadorCita.java

```java
package com.medisalud.patron.comportamiento;

import com.medisalud.entity.Cita;

public interface ObservadorCita {

    void notificarCambioEstado(Cita cita);
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

📖 **Explicación**: `Cita` **asocia** `Paciente` y `Medico` (los conoce, no los posee —
Módulo 9) y guarda su `EstadoCita`. `observadores` es `transient` porque un
`ObservadorCita` real (que termina usando un hilo, Bloque 8) no se puede serializar; un
`readObject` propio lo reconstruye vacío al recargar un objeto `Cita` desde
`citas.ser` (Módulo 17). `Factura` representa el costo de una consulta; su
`calcularMonto()` es sobre-escrito por los decoradores del Bloque 9 (patrón Decorator).

**Patrón Observer — `ObservadorCita`**

- **Problema**: cuando una cita cambia de estado, hay que notificar a alguien — pero
  `Cita` no debería saber **cómo** se notifica (¿imprime en consola? ¿manda un correo?
  ¿los dos?) ni **a quién**. Si `Cita` llamara directamente a una clase concreta de
  notificaciones, cualquier cambio en cómo se notifica obligaría a modificar `Cita`
  — una clase que representa una cita, no un sistema de mensajería.
- **Por qué Observer es la mejor opción acá**: `Cita` solo conoce la interfaz
  `ObservadorCita` y le avisa "cambié de estado" a quien esté suscripto
  (`agregarObservador`), sin saber qué hace cada observador con ese aviso. La
  implementación real (`ObservadorCitaNotificacion`, Bloque 8) puede cambiar por
  completo —o agregarse una segunda, una tercera— sin tocar una sola línea de `Cita`.
- **Dónde más aparece este mismo escenario**: cualquier situación donde un cambio de
  estado interesa a otras partes del sistema que no deberían estar acopladas entre sí
  — un carrito de compras que notifica al inventario cuando se confirma una compra, una
  hoja de cálculo que recalcula las celdas dependientes cuando una celda cambia, o los
  *listeners* de clics y otros eventos en cualquier interfaz gráfica.

---

### Bloque 4 — Repositorios

Interfaces e implementación en memoria; dependen de las entidades de los Bloques 2-3.

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

📖 **Explicación**: una interfaz por entidad (segregación de interfaces, Módulo 11) con
su implementación en memoria (`ArrayList`/`Map`, Módulo 8-9), con los métodos de
modificación y lectura sincronizados (`synchronized`, Módulo 16) para el caso de hilos
concurrentes. Que los servicios del Bloque 6 dependan de estas **interfaces** y no de
`RepositorioPacientesMemoria` directamente (inversión de dependencias) es lo que permite
reemplazarlas por los DAOs de JDBC (Bloque 11) sin cambiar ningún `Servicio*`.

---

### Bloque 5 — Estrategias de costo

Dependen solo de `Cita` (Bloque 3).

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

📖 **Explicación**: patrón Strategy (Módulo 14).

- **Problema**: calcular el costo de una consulta depende del tipo de consulta
  (general, especialista...), y esa lista de tipos **va a seguir creciendo** (pediatría,
  urgencias, telemedicina). Si `Factura` calculara el costo con un `if`/`switch` interno
  sobre el tipo, cada tipo nuevo obligaría a modificar `Factura` — una clase que ya
  tiene su propia responsabilidad (representar el costo de una consulta, no decidir
  cómo se calcula).
- **Por qué Strategy es la mejor opción acá**: separa "qué se calcula" (lo hace
  `Factura`/`ServicioFacturacion`) de "cómo se calcula" (lo hace cada
  `EstrategiaCosto`). Agregar `EstrategiaCostoUrgencias` el día de mañana es crear una
  clase nueva que implementa la interfaz — **cero líneas** cambiadas en el código que ya
  funciona (principio abierto/cerrado, Módulo 11). La alternativa (un `if`/`switch`
  gigante en `Factura`) funcionaría hoy, pero cada tipo nuevo agrandaría ese mismo
  método para siempre, y mezclaría la entidad con la lógica de negocio.
- **Dónde más aparece este mismo escenario**: cualquier cálculo o comportamiento que
  **varía según un tipo o categoría y que puede crecer con el tiempo** es candidato a
  Strategy — calcular un envío según el método elegido (correo, moto, retiro en
  tienda), calcular un descuento según el tipo de cliente, validar un documento según
  su formato, u ordenar una lista con distintos criterios. La señal de alarma que avisa
  "acá conviene Strategy" es un `if`/`switch` sobre un tipo que vas a tener que volver a
  tocar cada vez que aparezca un caso nuevo.

---

### Bloque 6 — Servicios

Dependen de las interfaces de repositorio (Bloque 4), las excepciones (Bloque 1), y
`EstrategiaCosto` (Bloque 5).

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

📖 **Explicación**: cada `Servicio*` encapsula las reglas de negocio de una entidad
(responsabilidad única, Módulo 11) y lanza su excepción propia si no encuentra el
código buscado. `ServicioPacientes.listarMayoresDeEdad` y
`ServicioFacturacion.totalFacturadoPorMedico` ya usan `Predicate`/`Stream` (Módulos
19-20) desde esta única versión final.

**Patrón State — `EstadoCita.puedeTransicionarA` + `ServicioCitas.cambiarEstado`**

- **Problema**: una cita no puede pasar de cualquier estado a cualquier otro — una
  cita `ATENDIDA` no puede volver a `PENDIENTE`, por ejemplo. Si esa regla se
  verificara con un `if`/`switch` repetido en cada método que cambia el estado
  (`confirmarCita`, `atenderCita`, `cancelarCita`, y cualquiera que se agregue
  después), la regla terminaría duplicada varias veces, y sería fácil que alguna copia
  quedara desactualizada el día que la regla cambie.
- **Por qué State es la mejor opción acá**: cada valor de `EstadoCita` sabe, con
  `puedeTransicionarA`, a qué otros estados puede pasar — la regla vive en **un solo
  lugar**. `cambiarEstado` (privado, en `ServicioCitas`) es el **único** punto donde se
  aplica esa regla antes de cambiar el estado; `confirmarCita`/`atenderCita`/
  `cancelarCita` son la única forma pública de llegar ahí, así que es imposible
  cambiar el estado de una cita sin pasar por la validación.
- **Dónde más aparece este mismo escenario**: cualquier entidad cuyo comportamiento
  válido depende de un estado actual y tiene transiciones permitidas y prohibidas — un
  pedido de e-commerce (`pendiente → pagado → enviado → entregado`, nunca al revés),
  un documento en un flujo de aprobación (`borrador → en revisión → aprobado/rechazado`),
  o un semáforo que solo puede pasar de verde a amarillo, nunca directo a rojo.

---

### Bloque 7 — Patrones creacionales

`ConstructorHistoriaClinica` depende de `HistoriaClinica` (Bloque 2);
`GestorClinica` depende de las interfaces de repositorio (Bloque 4).

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

📖 **Explicación**: dos patrones creacionales (Módulo 12), cada uno resolviendo un
problema distinto.

**Builder — `ConstructorHistoriaClinica`**

- **Problema**: `HistoriaClinica` tiene tres campos opcionales (`antecedentes`,
  `alergias`, `observaciones`) que casi nunca se llenan todos juntos. Un único
  constructor con los tres sería `new HistoriaClinica(null, "Penicilina", null)` —
  hay que acordarse del orden exacto, y los `null` de relleno no dicen nada sobre qué
  dato es cuál. Si además hubiera que agregar un cuarto o quinto campo opcional más
  adelante, el problema (y los `null`) solo empeorarían.
- **Por qué Builder es la mejor opción acá**: cada método (`conAntecedentes`,
  `conAlergias`, ...) tiene un nombre que dice exactamente qué dato está fijando, se
  pueden llamar en cualquier orden, y se puede omitir cualquiera sin pasar `null` a
  mano. La alternativa de "varios constructores sobrecargados, uno por combinación de
  campos" (*constructor telescópico*) necesitaría 2³ = 8 constructores distintos solo
  para 3 campos opcionales — y crece exponencialmente con cada campo nuevo.
- **Dónde más aparece este mismo escenario**: cualquier objeto con varios atributos
  opcionales que se usan en distintas combinaciones — una petición HTTP con *headers*
  opcionales, un correo con copia/copia oculta/adjuntos opcionales, una consulta a una
  base de datos con filtros opcionales, o la configuración de un componente visual con
  muchas propiedades que casi nunca se usan todas a la vez.

**Singleton — `GestorClinica`**

- **Problema**: los tres repositorios (`RepositorioPacientes`, `RepositorioMedicos`,
  `RepositorioCitas`) deben ser **los mismos** objetos en toda la aplicación. Si cada
  clase que los necesita creara su propia instancia (o los repositorios se pasaran
  "a mano" de clase en clase), sería muy fácil terminar con dos copias distintas del
  repositorio de pacientes en memoria — una se actualiza y la otra no, y el programa
  muestra datos inconsistentes según quién pregunte.
- **Por qué Singleton es la mejor opción acá**: garantiza, en tiempo de compilación y
  de ejecución, que exista **una sola** instancia compartida, accesible desde cualquier
  punto del programa sin tener que pasarla como parámetro de clase en clase. El costo a
  tener en cuenta (y por eso Singleton no es la respuesta correcta para todo): un
  estado global compartido es más difícil de probar de forma aislada y crea una
  dependencia oculta — cualquier clase puede llamar a `GestorClinica.obtenerInstancia()`
  sin que se vea en su constructor que depende de él.
- **Dónde más aparece este mismo escenario**: cualquier recurso del que debe existir
  **una sola instancia compartida** en toda la aplicación — un *pool* de conexiones a
  base de datos, un sistema de *logging*, una caché en memoria, o el objeto de
  configuración que se lee una sola vez al arrancar el programa.

---

### Bloque 8 — Concurrencia y notificaciones

`ContadorCodigos` y `HiloNotificaciones` no dependen de ninguna entidad propia;
`ObservadorCitaNotificacion` depende de `HiloNotificaciones`, `ObservadorCita` (Bloque
3) y `Cita` (Bloque 3).

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

📖 **Explicación**: tres piezas de concurrencia (Módulos 15-16), cada una resolviendo
un problema distinto.

**`HiloNotificaciones` — un hilo separado**

- **Problema**: notificar a un paciente que su cita cambió de estado es, en una
  aplicación real, una operación que puede tardar (enviar un correo, un SMS, llamar a
  una API externa). Si esa notificación se hiciera **en el mismo hilo** que atiende el
  menú, el usuario se quedaría esperando a que termine de "enviarse" el mensaje antes
  de poder seguir usando el programa — el menú se congelaría por algo que no tiene
  nada que ver con lo que el usuario quiere hacer ahora.
- **Por qué un hilo separado es la mejor opción acá**: `HiloNotificaciones` corre en
  paralelo (`extends Thread`, Módulo 15) y procesa su lista de mensajes pendientes cada
  200 ms, sin que el hilo principal (el que atiende `VistaConsola`) tenga que esperarlo.
  `encolar(...)` solo agrega un mensaje a una lista y devuelve el control de inmediato
  — lo "lento" pasa en segundo plano. La alternativa (notificar en el mismo hilo, de
  forma síncrona) sería más simple de programar, pero bloquearía al usuario cada vez
  que se confirma o atiende una cita.
- **Dónde más aparece este mismo escenario**: cualquier tarea que no necesita
  terminarse para que el usuario siga interactuando con el programa — enviar un correo
  de bienvenida al registrarse, escribir un registro de auditoría (*log*), generar un
  reporte pesado, o subir un archivo a un servidor mientras la aplicación sigue
  respondiendo a otras acciones.

**`synchronized` en `HiloNotificaciones.pendientes`**

- **Problema**: la lista `pendientes` la escribe el hilo principal (cuando una cita
  cambia de estado) y la lee/vacía `HiloNotificaciones` (cuando procesa los
  mensajes) — **dos hilos accediendo a la misma lista al mismo tiempo**. Sin ninguna
  protección, un hilo podría estar leyendo la lista mientras el otro la está
  modificando a la mitad, y `ArrayList` no garantiza qué pasa en ese caso (desde un
  resultado incorrecto hasta una excepción en tiempo de ejecución).
- **Por qué `synchronized` es la mejor opción acá**: obliga a que solo un hilo a la vez
  pueda ejecutar `encolar(...)` o `procesarPendientes()`, así nunca se superponen sobre
  la misma lista. Es la herramienta más simple y directa para este caso: una sola
  colección compartida, con pocos métodos que la tocan.
- **Dónde más aparece este mismo escenario**: cualquier estructura de datos (lista,
  mapa, contador) que **más de un hilo lee o escribe a la vez** — el carrito de compras
  de una tienda en línea con varias pestañas abiertas, un contador de visitas de una
  página, o la cola de tareas pendientes de un sistema de procesamiento en segundo
  plano.

**`ContadorCodigos` — `AtomicLong` en vez de `synchronized`**

- **Problema**: generar el siguiente código (`P001`, `P002`...) parece tan simple como
  "leer el número actual, sumarle uno, devolver el resultado" — pero esos son **tres
  pasos separados**. Si dos hilos ejecutan esos tres pasos al mismo tiempo, los dos
  pueden leer el mismo número antes de que cualquiera llegue a incrementarlo, y los dos
  terminan generando **el mismo código** — una condición de carrera clásica que no se
  nota en pruebas rápidas y aparece justo cuando más usuarios hay.
- **Por qué `AtomicLong` es la mejor opción acá**: `incrementAndGet()` hace "leer,
  sumar y devolver" como **una sola operación indivisible**, garantizada por el
  hardware, sin usar `synchronized` ni bloquear ningún hilo — más liviano que poner
  todo el método bajo un bloqueo, porque acá alcanza con proteger un único número, no
  una estructura completa.
- **Dónde más aparece este mismo escenario**: cualquier contador o secuencia que
  **genera identificadores únicos y puede ser llamado desde más de un hilo a la vez** —
  números de pedido en una tienda en línea, números de ticket en un sistema de soporte,
  o IDs de sesión en un servidor que atiende a muchos usuarios en simultáneo.

**`ObservadorCitaNotificacion` conecta ambas piezas**: es la implementación real de
`ObservadorCita` (patrón Observer, Módulo 14) — cuando una `Cita` cambia de estado,
encola el mensaje en `HiloNotificaciones` en vez de imprimirlo directamente, que es lo
que permite que "notificar" sea una operación en segundo plano y no una que bloquea
al usuario.

---

### Bloque 9 — Patrones estructurales

Los decoradores dependen de `Factura` (Bloque 3); `FachadaAgendamiento` depende de los
tres `Servicio*` (Bloque 6) y de `ObservadorCita` (Bloque 3).

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

📖 **Explicación**: dos patrones estructurales (Módulo 13), cada uno resolviendo un
problema distinto.

**Decorator — `FacturaConRecargoNocturno` / `FacturaConDescuentoAfiliado`**

- **Problema**: una factura puede llevar recargo nocturno, descuento de afiliado,
  ambos, o ninguno — cuatro combinaciones con solo dos extras, y cada extra nuevo que
  aparezca (recargo por feriado, descuento por plan familiar...) **duplica** la
  cantidad de combinaciones posibles. Resolverlo con herencia (una subclase por
  combinación: `FacturaConRecargoYDescuento`, `FacturaSoloRecargo`,
  `FacturaSoloDescuento`...) crece exponencialmente y hay que anticipar de antemano
  cada combinación como una clase separada.
- **Por qué Decorator es la mejor opción acá**: cada decorador envuelve una `Factura`
  (la original o ya decorada) y solo agrega su propio ajuste al monto, sin tocar la
  clase que envuelve. Combinar recargo + descuento es simplemente envolver dos veces,
  en el orden que haga falta — **en tiempo de ejecución**, según lo que el usuario
  elija en el menú, no una combinación fija decidida de antemano en el código. No hace
  falta crear ninguna clase nueva para una combinación que ya no existía.
- **Dónde más aparece este mismo escenario**: cualquier objeto al que se le pueden
  agregar **variantes combinables entre sí** — los ingredientes extra de un pedido de
  café o pizza (el ejemplo clásico de este patrón), los filtros que se le aplican a una
  imagen uno encima de otro, o los `BufferedReader`/`InputStreamReader` que ya usaste
  en el Bloque 10: cada uno envuelve al anterior y le agrega una capacidad.

**Facade — `FachadaAgendamiento`**

- **Problema**: agendar una cita no es un solo paso, son cuatro: buscar que el
  paciente exista, buscar que el médico exista, crear la cita, y conectarla con quien
  la va a notificar cuando cambie de estado. Si cada lugar del código que necesita
  agendar una cita repitiera esos cuatro pasos a mano, cualquier cambio en ese orden
  (o un paso olvidado) habría que corregirlo en todos esos lugares por separado.
- **Por qué Facade es la mejor opción acá**: `agendar(...)` es la **única puerta de
  entrada** para agendar una cita; adentro coordina `ServicioPacientes`,
  `ServicioMedicos`, `ServicioCitas` y el observador, en el orden correcto, una sola
  vez. Quien lo llama (por ejemplo `VistaConsola`) no necesita saber que existen esos
  cuatro pasos, ni en qué orden van.
- **Dónde más aparece este mismo escenario**: cualquier operación que internamente
  necesita coordinar **varios pasos o varios subsistemas en un orden específico** — un
  checkout de una tienda en línea (verificar stock, cobrar, actualizar inventario,
  notificar), encender una aplicación (leer configuración, conectar a la base de
  datos, levantar los servicios), o una librería que simplifica una API externa
  compleja detrás de unos pocos métodos propios.

---

### Bloque 10 — Persistencia en archivo

Dependen de `Paciente` y `Cita` (Bloques 2-3). No la usa el punto de entrada final
(Bloque 13 usa JDBC), pero se mantiene en el proyecto como la implementación
alternativa de las mismas interfaces de repositorio.

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

📖 **Explicación**: `AlmacenPacientesArchivo` guarda/lee `pacientes.txt` línea por línea
(`codigo|nombreCompleto|edad`) con `BufferedReader`/`BufferedWriter`;
`AlmacenCitasSerializado` guarda/lee el grafo completo de citas con
`ObjectOutputStream`/`ObjectInputStream` en `citas.ser` — las dos técnicas del
Módulo 17 (archivo de texto y serialización de objetos), cada una aplicada donde tiene
más sentido. Ambas clases manejan el caso de que el archivo no exista todavía
(primer arranque) devolviendo una lista vacía en vez de fallar.

---

### Bloque 11 — Persistencia JDBC

Las tres clases DAO implementan las **mismas** interfaces del Bloque 4, pero con
`PreparedStatement` sobre MySQL. `CitaDAO` depende también de `PacienteDAO`/`MedicoDAO`
para reconstruir las referencias de una `Cita` leída de la base de datos.

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

📖 **Explicación**: `ConexionBD` centraliza `DriverManager.getConnection`;
`PacienteDAO`/`MedicoDAO`/`CitaDAO` implementan las mismas interfaces del Bloque 4 con
`PreparedStatement` (consultas parametrizadas, Módulo 18) sobre las tablas
`pacientes`/`medicos`/`citas` (la última con claves foráneas a las otras dos). Ningún
`Servicio*` del Bloque 6 necesita cambiar para usar estas implementaciones en vez de
las de memoria — la inversión de dependencias del Módulo 11 es la que lo permite.

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

---

### Bloque 12 — Controlador y Vista (MVC)

`ControladorMediSalud` depende de los `Servicio*` (Bloque 6) y de `FachadaAgendamiento`
(Bloque 9). `VistaConsola` depende de `ControladorMediSalud`, de las entidades, y de
los patrones de los Bloques 5, 7 y 9.

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

📖 **Explicación**: `ControladorMediSalud` es la "C" de MVC (Módulo 18): conecta la
vista con los servicios, sin lógica de negocio propia. `VistaConsola` es la "V": el
menú interactivo con `Scanner` (único archivo de todo el proyecto que lee la consola —
justo porque es la única capa de MVC que debe hacerlo); el "modelo" son las clases de
`entity`/`service`/`repository` de los bloques anteriores, que ninguna de las dos
conoce en detalle. Cada opción del menú captura sus propias excepciones y sigue
funcionando ante un error (Edge Cases de la spec).

---

### Bloque 13 — Principal (punto de entrada)

Depende de todos los bloques anteriores: es quien los conecta.

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

📖 **Explicación**: `Principal` es el **único** punto de entrada de todo el proyecto.
Construye los DAOs de JDBC, inicializa el Singleton `GestorClinica`, arma los
`Servicio*`, arranca `HiloNotificaciones`, conecta `FachadaAgendamiento` con el
observador de notificaciones, arma `ControladorMediSalud` con todo lo anterior, y le
entrega el control a `VistaConsola.mostrarMenu()`. Al salir del menú, detiene el hilo
de notificaciones de forma ordenada (`detener()` + `join()`) antes de terminar.

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
