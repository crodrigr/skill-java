# 🔁 Guía de repaso — Interfaces, capas e inyección de dependencias

## 🎯 Objetivo

Reforzar, con ejemplos cortos, una idea que sostiene todo el proyecto MediSalud:
**las capas no se conocen entre sí por su implementación, sino por una interfaz**, y
alguien de afuera (el `main`) las conecta. Eso es la **inyección de dependencias**.

## 🌍 Contexto

En el proyecto final el servicio de pacientes necesita guardar pacientes, pero hoy
pueden guardarse en memoria, en archivo o en MySQL. Si el servicio supiera *cómo* se
guarda, cambiar el almacenamiento obligaría a reescribir el servicio. La interfaz
resuelve eso: el servicio dice **qué** necesita, no **cómo** se hace.

## 🧠 Las tres ideas en una frase

| Idea | En una frase | En MediSalud |
|---|---|---|
| **Interfaz** | Un contrato: lista de operaciones, sin código | `RepositorioPacientes` |
| **Separación de capas** | Cada capa habla solo con la de abajo, y a través de contratos | Controlador → Servicio → Repositorio |
| **Inyección de dependencias** | Una clase **recibe** lo que necesita; no lo crea con `new` | `new ServicioPacientes(pacienteDAO)` |

## 🧱 Las capas del proyecto

```
Vista            VistaConsola                 (pide y muestra datos)
   │
Controlador      ControladorMediSalud         (traduce la acción del usuario)
   │
Servicio         ServicioPacientes            (reglas de negocio)
   │  usa la INTERFAZ ▼
Repositorio      «interface» RepositorioPacientes
   ▲                ▲
   │ implements     │ implements
RepositorioPacientesMemoria     PacienteDAO (JDBC / MySQL)
```

El mismo esquema como diagrama de clases:

![Diagrama de clases de las capas del proyecto](img/diagrama-clases-capas.svg)

La flecha que importa: el servicio apunta a la **interfaz**, nunca a una clase concreta.
Las implementaciones también apuntan a la interfaz (la implementan). Por eso se llama
**inversión de dependencias**: el detalle depende del contrato, no al revés.

> ▶️ **Todo el código de esta guía se puede ejecutar**: ver [ejemplo-capas/](ejemplo-capas/README.md)
> (`./ejecutar.sh dao|memoria|log`, sin Docker ni MySQL).

## 1️⃣ Paso 1 — El contrato (la interfaz)

Archivo: `repository/RepositorioPacientes.java`

```java
public interface RepositorioPacientes {
    void guardar(Paciente paciente);
    Paciente buscarPorCodigo(String codigo);
    List<Paciente> listarTodos();
}
```

Solo firmas. Sin `{ }` de código, sin `ArrayList`, sin SQL. Dice **qué** se puede hacer.

## 2️⃣ Paso 2 — Dos implementaciones del mismo contrato

```java
// En memoria
public class RepositorioPacientesMemoria implements RepositorioPacientes {
    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void guardar(Paciente paciente) { pacientes.add(paciente); }
    // ...
}

// En MySQL
public class PacienteDAO implements RepositorioPacientes {
    @Override
    public void guardar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (codigo, nombre_completo, edad) VALUES (?, ?, ?)";
        // ... JDBC ...
    }
    // ...
}
```

Mismo contrato, **cómo** distinto. El compilador exige que ambas cumplan todos los
métodos de la interfaz.

## 3️⃣ Paso 3 — El servicio depende de la interfaz

Archivo: `service/ServicioPacientes.java`

```java
public class ServicioPacientes {

    private final RepositorioPacientes repositorioPacientes;   // tipo = interfaz

    public ServicioPacientes(RepositorioPacientes repositorioPacientes) {
        this.repositorioPacientes = repositorioPacientes;      // lo RECIBE
    }

    public void registrarPaciente(Paciente paciente) {
        repositorioPacientes.guardar(paciente);
    }
}
```

Fijate en dos detalles:

- El campo es de tipo `RepositorioPacientes`, **no** `PacienteDAO`.
- No hay ningún `new PacienteDAO()` dentro del servicio. Si lo hubiera, quedaría
  atado a MySQL para siempre.

## 4️⃣ Paso 4 — Alguien lo conecta (la inyección)

Archivo: `Principal.java`, el único lugar que sabe qué implementación se usa:

```java
PacienteDAO pacienteDAO = new PacienteDAO();
ServicioPacientes servicioPacientes = new ServicioPacientes(pacienteDAO);
```

**Eso es inyectar**: el `main` crea la dependencia y se la entrega al constructor.
Para cambiar a memoria, se modifica una sola línea:

```java
ServicioPacientes servicioPacientes = new ServicioPacientes(new RepositorioPacientesMemoria());
```

`ServicioPacientes` no se toca. No se recompila su lógica, no se vuelve a probar su
código: no se enteró del cambio.

## ❌ Antes y ✅ después

```java
// ❌ Acoplado: el servicio crea su propia dependencia
public class ServicioPacientes {
    private final PacienteDAO repositorio = new PacienteDAO();
}

// ✅ Desacoplado: el servicio recibe un contrato
public class ServicioPacientes {
    private final RepositorioPacientes repositorio;
    public ServicioPacientes(RepositorioPacientes repositorio) {
        this.repositorio = repositorio;
    }
}
```

| | ❌ Acoplado | ✅ Con interfaz + inyección |
|---|---|---|
| Cambiar MySQL por memoria | Editar el servicio | Editar una línea del `main` |
| Probar el servicio sin base de datos | Imposible | Inyectar la versión en memoria |
| Quién decide la implementación | La propia clase | El que ensambla (`main`) |

## 🧪 Beneficio práctico: probar sin MySQL

Una tercera implementación, escrita solo para probar, cabe en pocas líneas:

```java
RepositorioPacientes falso = new RepositorioPacientesMemoria();
ServicioPacientes servicio = new ServicioPacientes(falso);

servicio.registrarPaciente(new Paciente("Ana Pérez", "P-001", 30));
System.out.println(servicio.listarTodos().size());   // 1, sin Docker ni base de datos
```

## 🔗 Dónde más aparece el mismo patrón en el proyecto

> 🔗 **Ejemplos de este concepto en la solución**
>
> - `service/EstrategiaCosto.java` — la interfaz es el contrato de cálculo; hay dos
>   implementaciones (`EstrategiaCostoConsultaGeneral`, `EstrategiaCostoConsultaEspecialista`).
> - `repository/RepositorioCitas.java` y `RepositorioMedicos.java` — mismo esquema que
>   pacientes, con versión en memoria y versión DAO.
> - `notificacion/ObservadorCita.java` — el servicio avisa a "alguien" sin saber a quién.
> - `Principal.java` — el ensamblado completo: crea los DAO y los inyecta en los servicios.

## ⚠️ Errores comunes

1. **Declarar el campo con la clase concreta** (`private PacienteDAO repo`). Aunque
   inyectes por constructor, seguís acoplado.
2. **Hacer `new` de la implementación dentro del servicio.** Anula todo el beneficio.
3. **Interfaz que filtra detalles** (`guardar(Connection c, Paciente p)`). Si el
   contrato menciona SQL, la memoria no puede implementarlo.
4. **Crear una interfaz por cada clase** sin necesidad. Se justifica cuando hay (o
   habrá) más de una implementación, o cuando separa una capa de otra.
5. **Saltarse una capa**: que la vista llame directo al repositorio. Cada capa habla
   solo con la inmediata.

## 🧭 Regla de decisión rápida

> ¿Esta clase necesita algo que pertenece a **otra capa** o que **podría cambiar**
> (base de datos, archivo, cálculo, notificación)?
> → Que dependa de una **interfaz** y que la **reciba por constructor**.

## ✅ Autoevaluación

1. ¿Por qué `ServicioPacientes` no debe importar `PacienteDAO`?
2. ¿Qué línea de `Principal.java` habría que cambiar para guardar en memoria?
3. Si se agrega `RepositorioPacientesArchivo`, ¿qué clases existentes cambian?
   *(Respuesta: ninguna; solo el `main` si se quiere usar.)*
4. ¿Qué diferencia hay entre **implementar** una interfaz e **inyectarla**?
5. Dibujá de memoria las flechas entre `ServicioPacientes`, `RepositorioPacientes` y
   `PacienteDAO`. ¿Hacia dónde apuntan?

## 🏋️ Ejercicio corto

Creá `AlmacenPacientesLog implements RepositorioPacientes`, que además de guardar en
una lista imprima `"Guardado: <nombre>"` en cada `guardar`. Inyectalo en
`ServicioPacientes` desde un `main` de prueba y comprobá que el servicio **no
necesitó ningún cambio**.
