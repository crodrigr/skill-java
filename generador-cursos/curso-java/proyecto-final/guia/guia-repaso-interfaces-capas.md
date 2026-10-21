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

Archivo: `Principal.java`, el único lugar que sabe qué implementación se usa. Esta es la
clase **completa**, para ver cómo se integran todas las capas:

```java
package com.medisalud;

import com.medisalud.controlador.ControladorMediSalud;
import com.medisalud.repository.AlmacenPacientesLog;
import com.medisalud.repository.PacienteDAO;
import com.medisalud.repository.RepositorioPacientes;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioPacientes;
import com.medisalud.vista.VistaConsola;

/**
 * Paso 4 — El ÚNICO lugar que sabe qué implementación se usa (la inyección).
 *
 * Uso: java com.medisalud.Principal [dao | memoria | log]      (por defecto: dao)
 */
public class Principal {

    public static void main(String[] args) {
        String modo = args.length > 0 ? args[0] : "dao";

        RepositorioPacientes repositorio = switch (modo) {
            case "memoria" -> new RepositorioPacientesMemoria();
            case "log"     -> new AlmacenPacientesLog();
            default        -> new PacienteDAO();
        };
        System.out.println("Repositorio en uso: " + repositorio.getClass().getSimpleName());

        ServicioPacientes servicioPacientes = new ServicioPacientes(repositorio);   // inyección
        ControladorMediSalud controlador = new ControladorMediSalud(servicioPacientes, new VistaConsola());
        controlador.iniciar();
    }
}
```

### 🔍 Cómo se integra, línea por línea

| Pieza | Qué hace | Qué sabe de las demás |
|---|---|---|
| `RepositorioPacientes repositorio = switch (modo) {...}` | Elige y **crea** la implementación | Es la única que nombra `PacienteDAO`, `RepositorioPacientesMemoria` y `AlmacenPacientesLog` |
| `new ServicioPacientes(repositorio)` | **Inyecta**: entrega la dependencia al constructor | El servicio solo recibe un `RepositorioPacientes` |
| `new ControladorMediSalud(servicioPacientes, new VistaConsola())` | Conecta el controlador con el servicio y la vista | El controlador no conoce el repositorio |
| `controlador.iniciar()` | Arranca el menú | — |

El orden de armado va **de abajo hacia arriba**: primero el repositorio, luego el
servicio que lo recibe, luego el controlador que recibe el servicio. Las llamadas en
tiempo de ejecución van en sentido contrario: vista → controlador → servicio → repositorio.

**Eso es inyectar**: el `main` crea la dependencia y se la entrega al constructor.
Para cambiar a memoria, se modifica una sola línea (o se ejecuta con el argumento `memoria`):

```java
RepositorioPacientes repositorio = new RepositorioPacientesMemoria();
ServicioPacientes servicioPacientes = new ServicioPacientes(repositorio);
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

## 📦 Proyecto completo para llevar a Visual Studio Code

Todo el código de la guía en un proyecto que compila y corre. No necesita Docker ni
MySQL: `PacienteDAO` **simula** JDBC (imprime el SQL y guarda en un mapa). Existe también
una copia lista en [ejemplo-capas/](ejemplo-capas/README.md).

### 🌳 Árbol de archivos

```
ejemplo-capas/
├── ejecutar.sh                                  ← compila y ejecuta (opcional)
└── src/
    └── com/
        └── medisalud/
            ├── Principal.java                   ← Paso 4: ensambla e inyecta
            ├── entity/
            │   └── Paciente.java
            ├── repository/
            │   ├── RepositorioPacientes.java        ← Paso 1: contrato «interface»
            │   ├── RepositorioPacientesMemoria.java ← Paso 2a
            │   ├── PacienteDAO.java                 ← Paso 2b
            │   └── AlmacenPacientesLog.java         ← ejercicio corto
            ├── service/
            │   └── ServicioPacientes.java       ← Paso 3
            ├── controlador/
            │   └── ControladorMediSalud.java
            └── vista/
                └── VistaConsola.java
```

### 🛠️ Cómo armarlo en VS Code

1. Instalá el **Extension Pack for Java** y tené un JDK 17 o superior.
2. Creá la carpeta `ejemplo-capas/` y abrila con *File → Open Folder*.
3. Creá cada archivo con la **ruta exacta** del árbol (el `package` de cada archivo
   debe coincidir con su carpeta) y pegá el código de abajo.
4. Ejecutá desde la terminal de VS Code, parado en `ejemplo-capas/`:

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out com.medisalud.Principal dao        # dao | memoria | log
```

   O abrí `Principal.java` y pulsá **▶ Run** sobre el `main`. Menú: `1` registrar,
   `2` listar, `3` buscar, `0` salir.

> 🔎 Para analizarlo: poné un punto de interrupción en `ServicioPacientes.registrarPaciente`
> y ejecutá con *Debug*. Verás que `repositorioPacientes` apunta a `PacienteDAO`, a
> `RepositorioPacientesMemoria` o a `AlmacenPacientesLog` según el argumento, sin que el
> servicio cambie.

### 📄 Código de cada archivo

#### `src/com/medisalud/entity/Paciente.java` — Entidad

```java
package com.medisalud.entity;

public class Paciente {

    private final String nombreCompleto;
    private final String codigo;
    private final int edad;

    public Paciente(String nombreCompleto, String codigo, int edad) {
        this.nombreCompleto = nombreCompleto;
        this.codigo = codigo;
        this.edad = edad;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public String getCodigo() { return codigo; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return codigo + " | " + nombreCompleto + " | " + edad + " años";
    }
}
```

#### `src/com/medisalud/repository/RepositorioPacientes.java` — Paso 1 — el contrato

```java
package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.List;

/** Paso 1 — El contrato: solo firmas, sin código, sin ArrayList, sin SQL. */
public interface RepositorioPacientes {
    void guardar(Paciente paciente);
    Paciente buscarPorCodigo(String codigo);
    List<Paciente> listarTodos();
}
```

#### `src/com/medisalud/repository/RepositorioPacientesMemoria.java` — Paso 2a — en memoria

```java
package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.ArrayList;
import java.util.List;

/** Paso 2a — Implementación en memoria. */
public class RepositorioPacientesMemoria implements RepositorioPacientes {

    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void guardar(Paciente paciente) { pacientes.add(paciente); }

    @Override
    public Paciente buscarPorCodigo(String codigo) {
        for (Paciente p : pacientes) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    @Override
    public List<Paciente> listarTodos() { return new ArrayList<>(pacientes); }
}
```

#### `src/com/medisalud/repository/PacienteDAO.java` — Paso 2b — "base de datos" (JDBC simulado)

```java
package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Paso 2b — Implementación "de base de datos".
 *
 * Para que el ejemplo se ejecute SIN Docker ni MySQL, la conexión JDBC está simulada:
 * se imprime el SQL que se ejecutaría y los datos se guardan en un mapa que hace de tabla.
 * En el proyecto final, cada método usa PreparedStatement sobre una Connection real.
 */
public class PacienteDAO implements RepositorioPacientes {

    private final Map<String, Paciente> tablaPacientes = new LinkedHashMap<>();

    @Override
    public void guardar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (codigo, nombre_completo, edad) VALUES (?, ?, ?)";
        System.out.println("   [SQL] " + sql + "  -- " + paciente.getCodigo() + ", "
                + paciente.getNombreCompleto() + ", " + paciente.getEdad());
        tablaPacientes.put(paciente.getCodigo(), paciente);
    }

    @Override
    public Paciente buscarPorCodigo(String codigo) {
        System.out.println("   [SQL] SELECT * FROM pacientes WHERE codigo = ?  -- " + codigo);
        return tablaPacientes.get(codigo);
    }

    @Override
    public List<Paciente> listarTodos() {
        System.out.println("   [SQL] SELECT * FROM pacientes");
        return new ArrayList<>(tablaPacientes.values());
    }
}
```

#### `src/com/medisalud/repository/AlmacenPacientesLog.java` — Ejercicio corto resuelto

```java
package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.ArrayList;
import java.util.List;

/** Ejercicio corto resuelto — una tercera implementación; el servicio no cambia. */
public class AlmacenPacientesLog implements RepositorioPacientes {

    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void guardar(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println("   Guardado: " + paciente.getNombreCompleto());
    }

    @Override
    public Paciente buscarPorCodigo(String codigo) {
        for (Paciente p : pacientes) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    @Override
    public List<Paciente> listarTodos() { return new ArrayList<>(pacientes); }
}
```

#### `src/com/medisalud/service/ServicioPacientes.java` — Paso 3 — el servicio

```java
package com.medisalud.service;

import com.medisalud.entity.Paciente;
import com.medisalud.repository.RepositorioPacientes;
import java.util.List;

/** Paso 3 — El servicio depende de la INTERFAZ y la recibe por constructor. */
public class ServicioPacientes {

    private final RepositorioPacientes repositorioPacientes;   // tipo = interfaz

    public ServicioPacientes(RepositorioPacientes repositorioPacientes) {
        this.repositorioPacientes = repositorioPacientes;      // lo RECIBE
    }

    public void registrarPaciente(Paciente paciente) {
        if (paciente.getEdad() < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        if (repositorioPacientes.buscarPorCodigo(paciente.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe el paciente " + paciente.getCodigo());
        }
        repositorioPacientes.guardar(paciente);
    }

    public Paciente buscarPorCodigo(String codigo) {
        return repositorioPacientes.buscarPorCodigo(codigo);
    }

    public List<Paciente> listarTodos() {
        return repositorioPacientes.listarTodos();
    }
}
```

#### `src/com/medisalud/vista/VistaConsola.java` — Vista

```java
package com.medisalud.vista;

import com.medisalud.entity.Paciente;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Pide y muestra datos. No conoce el servicio ni el repositorio. */
public class VistaConsola {

    private final Scanner entrada = new Scanner(System.in);

    public int pedirOpcion() {
        System.out.println("\n1) Registrar paciente  2) Listar  3) Buscar por código  0) Salir");
        System.out.print("> ");
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        } catch (NoSuchElementException e) {
            return 0;   // fin de la entrada
        }
    }

    public Paciente pedirDatosPaciente() {
        System.out.print("Nombre completo: ");
        String nombre = entrada.nextLine();
        System.out.print("Código: ");
        String codigo = entrada.nextLine();
        System.out.print("Edad: ");
        int edad;
        try {
            edad = Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La edad debe ser un número");
        }
        return new Paciente(nombre, codigo, edad);
    }

    public String pedirCodigo() {
        System.out.print("Código: ");
        return entrada.nextLine();
    }

    public void mostrarPacientes(List<Paciente> pacientes) {
        if (pacientes.isEmpty()) System.out.println("(sin pacientes)");
        pacientes.forEach(System.out::println);
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }
}
```

#### `src/com/medisalud/controlador/ControladorMediSalud.java` — Controlador

```java
package com.medisalud.controlador;

import com.medisalud.entity.Paciente;
import com.medisalud.service.ServicioPacientes;
import com.medisalud.vista.VistaConsola;

/** Traduce la acción del usuario en llamadas al servicio. */
public class ControladorMediSalud {

    private final ServicioPacientes servicioPacientes;
    private final VistaConsola vista;

    public ControladorMediSalud(ServicioPacientes servicioPacientes, VistaConsola vista) {
        this.servicioPacientes = servicioPacientes;
        this.vista = vista;
    }

    public void iniciar() {
        boolean seguir = true;
        while (seguir) {
            switch (vista.pedirOpcion()) {
                case 1 -> registrar();
                case 2 -> vista.mostrarPacientes(servicioPacientes.listarTodos());
                case 3 -> buscar();
                case 0 -> seguir = false;
                default -> vista.mostrarMensaje("Opción no válida");
            }
        }
        vista.mostrarMensaje("Hasta luego");
    }

    private void registrar() {
        try {
            Paciente p = vista.pedirDatosPaciente();
            servicioPacientes.registrarPaciente(p);
            vista.mostrarMensaje("Paciente registrado");
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void buscar() {
        Paciente p = servicioPacientes.buscarPorCodigo(vista.pedirCodigo());
        vista.mostrarMensaje(p == null ? "No encontrado" : p.toString());
    }
}
```

#### `src/com/medisalud/Principal.java` — Paso 4 — la inyección

```java
package com.medisalud;

import com.medisalud.controlador.ControladorMediSalud;
import com.medisalud.repository.AlmacenPacientesLog;
import com.medisalud.repository.PacienteDAO;
import com.medisalud.repository.RepositorioPacientes;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioPacientes;
import com.medisalud.vista.VistaConsola;

/**
 * Paso 4 — El ÚNICO lugar que sabe qué implementación se usa (la inyección).
 *
 * Uso: java com.medisalud.Principal [dao | memoria | log]      (por defecto: dao)
 */
public class Principal {

    public static void main(String[] args) {
        String modo = args.length > 0 ? args[0] : "dao";

        RepositorioPacientes repositorio = switch (modo) {
            case "memoria" -> new RepositorioPacientesMemoria();
            case "log"     -> new AlmacenPacientesLog();
            default        -> new PacienteDAO();
        };
        System.out.println("Repositorio en uso: " + repositorio.getClass().getSimpleName());

        ServicioPacientes servicioPacientes = new ServicioPacientes(repositorio);   // inyección
        ControladorMediSalud controlador = new ControladorMediSalud(servicioPacientes, new VistaConsola());
        controlador.iniciar();
    }
}
```

#### `ejecutar.sh` — Script de ejecución (opcional)

```bash
#!/usr/bin/env bash
# Uso: ./ejecutar.sh [dao|memoria|log]     (compila todo y ejecuta el menú)
set -e
cd "$(dirname "$0")"
rm -rf out && mkdir out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out com.medisalud.Principal "${1:-dao}"
```

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
