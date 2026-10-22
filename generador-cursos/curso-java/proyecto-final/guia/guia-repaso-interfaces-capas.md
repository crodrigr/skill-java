# 🔁 Guía de repaso — Interfaces, capas e inyección de dependencias

## 🎯 Objetivo

Entender, con un programa pequeño y **completo que se puede ejecutar**, tres ideas que
sostienen el proyecto final: **interfaz**, **separación en capas** e **inyección de
dependencias**. Al terminar, deberías poder explicar por qué cambiar *dónde* se guardan
los datos no obliga a tocar la lógica del programa.

## 🌍 Contexto

El ejemplo es una **lista de tareas por consola**: crear tareas, listarlas y marcarlas
como completadas. Es deliberadamente simple para que el foco esté en la estructura, no
en el problema.

Hay una decisión que puede cambiar: **dónde se guardan las tareas**. Primero en memoria
(rápido, se pierde al cerrar) y después en un archivo (persiste). Si la lógica supiera
*cómo* se guarda, cada cambio obligaría a reescribirla. La interfaz evita eso.

## 🧠 Las tres ideas en una frase

| Idea | En una frase | En el ejemplo |
|---|---|---|
| **Interfaz** | Un contrato: lista de operaciones, sin código | `RepositorioTareas` |
| **Separación de capas** | Cada capa conoce solo a la de abajo (el controlador usa la vista y el servicio) | Controlador → Servicio → Repositorio |
| **Inyección de dependencias** | Una clase **recibe** lo que necesita; no lo crea con `new` | `new ServicioTareas(repositorio)` |

## 🧱 Las capas del proyecto

```
Vista            VistaConsola               (pide y muestra datos)
   │
Controlador      ControladorTareas          (traduce la acción del usuario)
   │
Servicio         ServicioTareas             (reglas de negocio)
   │  usa la INTERFAZ ▼
Repositorio      «interface» RepositorioTareas
   ▲                ▲
   │ implements     │ implements
RepositorioTareasMemoria     RepositorioTareasArchivo
```

El mismo esquema como diagrama de clases:

![Diagrama de clases de las capas del proyecto](img/diagrama-clases-capas.svg)

La flecha que importa: el servicio apunta a la **interfaz**, nunca a una clase concreta.
Las implementaciones también apuntan a la interfaz (la implementan). Por eso se llama
**inversión de dependencias**: el detalle depende del contrato, no al revés.

> ▶️ **Todo el código de esta guía se puede ejecutar**: ver [ejemplo-capas/](ejemplo-capas/README.md)
> y la sección [📦 Proyecto completo](#-proyecto-completo-para-llevar-a-visual-studio-code).

## 0️⃣ Paso 0 — El problema: el servicio acoplado

```java
public class ServicioAcoplado {
    private final AlmacenArchivo almacen = new AlmacenArchivo();   // ❌ new dentro del servicio

    void crear(String titulo) { almacen.guardar(titulo); }
}
```

El servicio **crea** su dependencia. Queda atado al archivo: para guardar en memoria hay
que editar el servicio, y para probarlo hay que tocar un archivo real.

## 1️⃣ Paso 1 — El contrato (la interfaz)

Archivo: `repository/RepositorioTareas.java`

```java
package com.tareas.repository;

import com.tareas.entity.Tarea;
import java.util.List;

/** Paso 1 — El contrato: solo firmas. Dice QUÉ se puede hacer, no CÓMO. */
public interface RepositorioTareas {
    void guardar(Tarea tarea);          // crea la tarea o reemplaza la que tenga el mismo id
    Tarea buscarPorId(int id);          // null si no existe
    List<Tarea> listarTodas();
}
```

Solo firmas. Sin `{ }` de código, sin `Map`, sin archivos. Dice **qué** se puede hacer.

## 2️⃣ Paso 2 — Dos implementaciones del mismo contrato

```java
package com.tareas.repository;

import com.tareas.entity.Tarea;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Paso 2a — Guarda en memoria: al cerrar el programa se pierde todo. */
public class RepositorioTareasMemoria implements RepositorioTareas {

    private final Map<Integer, Tarea> tareas = new LinkedHashMap<>();

    @Override
    public void guardar(Tarea tarea) { tareas.put(tarea.getId(), tarea); }

    @Override
    public Tarea buscarPorId(int id) { return tareas.get(id); }

    @Override
    public List<Tarea> listarTodas() { return new ArrayList<>(tareas.values()); }
}
```

```java
package com.tareas.repository;

import com.tareas.entity.Tarea;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Paso 2b — Guarda en un archivo de texto: sobrevive entre ejecuciones. Formato: id;completada;titulo */
public class RepositorioTareasArchivo implements RepositorioTareas {

    private final Path archivo;

    public RepositorioTareasArchivo(String rutaArchivo) {
        this.archivo = Path.of(rutaArchivo);
    }

    @Override
    public void guardar(Tarea tarea) {
        Map<Integer, Tarea> todas = leer();
        todas.put(tarea.getId(), tarea);
        escribir(todas);
    }

    @Override
    public Tarea buscarPorId(int id) { return leer().get(id); }

    @Override
    public List<Tarea> listarTodas() { return new ArrayList<>(leer().values()); }

    private Map<Integer, Tarea> leer() {
        Map<Integer, Tarea> tareas = new LinkedHashMap<>();
        if (!Files.exists(archivo)) return tareas;
        try {
            for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
                String[] partes = linea.split(";", 3);
                int id = Integer.parseInt(partes[0]);
                tareas.put(id, new Tarea(id, partes[2], Boolean.parseBoolean(partes[1])));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return tareas;
    }

    private void escribir(Map<Integer, Tarea> tareas) {
        List<String> lineas = new ArrayList<>();
        for (Tarea t : tareas.values()) {
            lineas.add(t.getId() + ";" + t.isCompletada() + ";" + t.getTitulo());
        }
        try {
            Files.write(archivo, lineas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
```

Mismo contrato, **cómo** distinto: una usa un `Map`, la otra lee y escribe `tareas.txt`.
El compilador exige que ambas cumplan todos los métodos de la interfaz.

## 3️⃣ Paso 3 — El servicio depende de la interfaz

Archivo: `service/ServicioTareas.java`

```java
package com.tareas.service;

import com.tareas.entity.Tarea;
import com.tareas.repository.RepositorioTareas;
import java.util.List;

/** Paso 3 — Reglas de negocio. Depende de la INTERFAZ y la recibe por constructor. */
public class ServicioTareas {

    private final RepositorioTareas repositorio;   // tipo = interfaz, nunca una clase concreta

    public ServicioTareas(RepositorioTareas repositorio) {
        this.repositorio = repositorio;            // lo RECIBE, no hace new
    }

    public Tarea crear(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        Tarea tarea = new Tarea(siguienteId(), titulo.trim(), false);
        repositorio.guardar(tarea);
        return tarea;
    }

    public Tarea completar(int id) {
        Tarea tarea = repositorio.buscarPorId(id);
        if (tarea == null) {
            throw new IllegalArgumentException("No existe la tarea " + id);
        }
        tarea.marcarCompletada();
        repositorio.guardar(tarea);
        return tarea;
    }

    public List<Tarea> listar() {
        return repositorio.listarTodas();
    }

    private int siguienteId() {
        int mayor = 0;
        for (Tarea t : repositorio.listarTodas()) {
            mayor = Math.max(mayor, t.getId());
        }
        return mayor + 1;
    }
}
```

Fijate en dos detalles:

- El campo es de tipo `RepositorioTareas`, **no** `RepositorioTareasArchivo`.
- No hay ningún `new RepositorioTareas...()` dentro del servicio. Si lo hubiera,
  quedaría atado a esa implementación para siempre.

## 4️⃣ Paso 4 — Alguien lo conecta (la inyección)

Archivo: `Principal.java`, el único lugar que sabe qué implementación se usa. Esta es la
clase **completa**, para ver cómo se integran todas las capas:

```java
package com.tareas;

import com.tareas.controlador.ControladorTareas;
import com.tareas.repository.RepositorioTareas;
import com.tareas.repository.RepositorioTareasArchivo;
import com.tareas.repository.RepositorioTareasMemoria;
import com.tareas.service.ServicioTareas;
import com.tareas.vista.VistaConsola;

/**
 * Paso 4 — El ÚNICO lugar que sabe qué implementación se usa (la inyección).
 *
 * Uso: java com.tareas.Principal [archivo | memoria]      (por defecto: archivo)
 */
public class Principal {

    public static void main(String[] args) {
        String modo = args.length > 0 ? args[0] : "archivo";

        // 1) Se elige y se CREA la implementación (lo más profundo, primero)
        RepositorioTareas repositorio = modo.equals("memoria")
                ? new RepositorioTareasMemoria()
                : new RepositorioTareasArchivo("tareas.txt");
        System.out.println("Guardando con: " + repositorio.getClass().getSimpleName());

        // 2) Se INYECTA en el servicio (el servicio solo ve la interfaz)
        ServicioTareas servicio = new ServicioTareas(repositorio);

        // 3) Se inyecta el servicio en el controlador, junto con la vista
        ControladorTareas controlador = new ControladorTareas(servicio, new VistaConsola());

        // 4) Arranca la aplicación
        controlador.iniciar();
    }
}
```

### 🔍 Cómo se integra

| Pieza | Qué hace | Qué sabe de las demás |
|---|---|---|
| `new RepositorioTareasArchivo(...)` / `Memoria()` | **Crea** la implementación | Es la única clase que las nombra |
| `new ServicioTareas(repositorio)` | **Inyecta**: entrega la dependencia al constructor | El servicio solo ve la interfaz |
| `new ControladorTareas(servicio, new VistaConsola())` | Conecta controlador, servicio y vista | El controlador no conoce el repositorio |
| `controlador.iniciar()` | Arranca el menú | — |

El orden de armado va **de abajo hacia arriba** (repositorio → servicio → controlador).
En ejecución, el controlador es quien dirige: pide datos a la vista, llama al servicio y
este al repositorio. La vista nunca conoce al controlador.

**Eso es inyectar**: el `main` crea la dependencia y se la entrega al constructor.
Para cambiar el almacenamiento solo cambia la creación del repositorio en `Principal`:

```java
RepositorioTareas repositorio = new RepositorioTareasMemoria();   // antes: ...Archivo("tareas.txt")
ServicioTareas servicio = new ServicioTareas(repositorio);        // esta línea no cambia
```

`ServicioTareas` no se toca. No se vuelve a probar su lógica: no se enteró del cambio.

## ❌ Antes y ✅ después

```java
// ❌ Acoplado: el servicio crea su propia dependencia
public class ServicioTareas {
    private final RepositorioTareasArchivo repositorio = new RepositorioTareasArchivo("tareas.txt");
}

// ✅ Desacoplado: el servicio recibe un contrato
public class ServicioTareas {
    private final RepositorioTareas repositorio;
    public ServicioTareas(RepositorioTareas repositorio) {
        this.repositorio = repositorio;
    }
}
```

| | ❌ Acoplado | ✅ Con interfaz + inyección |
|---|---|---|
| Cambiar archivo por memoria | Editar el servicio | Cambiar una línea del `main` |
| Probar el servicio sin tocar archivos | Imposible | Inyectar la versión en memoria |
| Quién decide la implementación | La propia clase | El que ensambla (`main`) |

## 🧪 Beneficio práctico: probar sin archivos ni menú

```java
import com.tareas.repository.RepositorioTareas;
import com.tareas.repository.RepositorioTareasMemoria;
import com.tareas.service.ServicioTareas;

/**
 * Prueba del servicio SIN archivos ni menú: se inyecta la versión en memoria.
 * Ejecutar (con out/ ya compilado):  javac -cp out -d out Prueba.java && java -cp out Prueba
 */
public class Prueba {

    public static void main(String[] args) {
        RepositorioTareas repositorio = new RepositorioTareasMemoria();
        ServicioTareas servicio = new ServicioTareas(repositorio);

        servicio.crear("Estudiar interfaces");
        servicio.crear("Hacer el ejercicio");
        servicio.completar(1);

        servicio.listar().forEach(System.out::println);
        System.out.println(servicio.listar().size() == 2 ? "OK: 2 tareas" : "FALLO");

        try {
            servicio.crear("   ");
            System.out.println("FALLO: debió rechazar el título vacío");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }
    }
}
```

La prueba usa la versión en memoria: no crea `tareas.txt` y el servicio es el mismo que
usa la aplicación real.

> 🔗 **Ejemplos de este concepto en el proyecto final**
>
> - `service/EstrategiaCosto.java` — la interfaz es el contrato de cálculo; hay dos
>   implementaciones (`EstrategiaCostoConsultaGeneral`, `EstrategiaCostoConsultaEspecialista`).
> - `repository/RepositorioPacientes.java` — mismo esquema que `RepositorioTareas`, con
>   versión en memoria y versión DAO (`PacienteDAO`).
> - `notificacion/ObservadorCita.java` — el servicio avisa a "alguien" sin saber a quién.
> - `Principal.java` — el ensamblado completo: crea los DAO y los inyecta en los servicios.

## 📦 Proyecto completo para llevar a Visual Studio Code

Todo el código de la guía en un proyecto que compila y corre, sin dependencias externas.
Existe también una copia lista en [ejemplo-capas/](ejemplo-capas/README.md).

### 🌳 Árbol de archivos

```
ejemplo-capas/
├── Prueba.java                                  ← prueba sin menú ni archivos
├── antes/
│   └── ServicioAcoplado.java                    ← Paso 0: el ❌ acoplado
└── src/
    └── com/
        └── tareas/
            ├── Principal.java                   ← Paso 4: ensambla e inyecta
            ├── entity/
            │   └── Tarea.java
            ├── repository/
            │   ├── RepositorioTareas.java           ← Paso 1: contrato «interface»
            │   ├── RepositorioTareasMemoria.java    ← Paso 2a
            │   └── RepositorioTareasArchivo.java    ← Paso 2b
            ├── service/
            │   └── ServicioTareas.java          ← Paso 3
            ├── controlador/
            │   └── ControladorTareas.java
            └── vista/
                └── VistaConsola.java
```

### 🛠️ Cómo armarlo en VS Code

1. Instalá el **Extension Pack for Java** y tené un JDK 17 o superior.
2. Creá la carpeta `ejemplo-capas/` y abrila con *File → Open Folder*.
3. Creá cada archivo con la **ruta exacta** del árbol (el `package` de cada archivo
   debe coincidir con su carpeta) y pegá el código de abajo.
4. Desde la terminal de VS Code, parado en `ejemplo-capas/`:

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out com.tareas.Principal archivo      # archivo | memoria
```

   O abrí `Principal.java` y pulsá **▶ Run** sobre el `main`. Menú: `1` crear,
   `2` listar, `3` completar, `0` salir.

> 🔎 **Para analizarlo**
> - Ejecutá con `archivo`, creá dos tareas, salí y volvé a entrar: siguen ahí
>   (mirá el `tareas.txt` que aparece). Con `memoria` desaparecen.
> - Poné un punto de interrupción en `ServicioTareas.crear` y usá *Debug*: verás que
>   `repositorio` apunta a una clase u otra según el argumento, sin que el servicio cambie.

### 📄 Código de cada archivo

#### `src/com/tareas/entity/Tarea.java` — Entidad

```java
package com.tareas.entity;

public class Tarea {

    private final int id;
    private final String titulo;
    private boolean completada;

    public Tarea(int id, String titulo, boolean completada) {
        this.id = id;
        this.titulo = titulo;
        this.completada = completada;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public boolean isCompletada() { return completada; }

    public void marcarCompletada() { this.completada = true; }

    @Override
    public String toString() {
        return (completada ? "[x] " : "[ ] ") + id + " - " + titulo;
    }
}
```

#### `src/com/tareas/repository/RepositorioTareas.java` — Paso 1 — el contrato

```java
package com.tareas.repository;

import com.tareas.entity.Tarea;
import java.util.List;

/** Paso 1 — El contrato: solo firmas. Dice QUÉ se puede hacer, no CÓMO. */
public interface RepositorioTareas {
    void guardar(Tarea tarea);          // crea la tarea o reemplaza la que tenga el mismo id
    Tarea buscarPorId(int id);          // null si no existe
    List<Tarea> listarTodas();
}
```

#### `src/com/tareas/repository/RepositorioTareasMemoria.java` — Paso 2a — en memoria

```java
package com.tareas.repository;

import com.tareas.entity.Tarea;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Paso 2a — Guarda en memoria: al cerrar el programa se pierde todo. */
public class RepositorioTareasMemoria implements RepositorioTareas {

    private final Map<Integer, Tarea> tareas = new LinkedHashMap<>();

    @Override
    public void guardar(Tarea tarea) { tareas.put(tarea.getId(), tarea); }

    @Override
    public Tarea buscarPorId(int id) { return tareas.get(id); }

    @Override
    public List<Tarea> listarTodas() { return new ArrayList<>(tareas.values()); }
}
```

#### `src/com/tareas/repository/RepositorioTareasArchivo.java` — Paso 2b — en archivo

```java
package com.tareas.repository;

import com.tareas.entity.Tarea;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Paso 2b — Guarda en un archivo de texto: sobrevive entre ejecuciones. Formato: id;completada;titulo */
public class RepositorioTareasArchivo implements RepositorioTareas {

    private final Path archivo;

    public RepositorioTareasArchivo(String rutaArchivo) {
        this.archivo = Path.of(rutaArchivo);
    }

    @Override
    public void guardar(Tarea tarea) {
        Map<Integer, Tarea> todas = leer();
        todas.put(tarea.getId(), tarea);
        escribir(todas);
    }

    @Override
    public Tarea buscarPorId(int id) { return leer().get(id); }

    @Override
    public List<Tarea> listarTodas() { return new ArrayList<>(leer().values()); }

    private Map<Integer, Tarea> leer() {
        Map<Integer, Tarea> tareas = new LinkedHashMap<>();
        if (!Files.exists(archivo)) return tareas;
        try {
            for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
                String[] partes = linea.split(";", 3);
                int id = Integer.parseInt(partes[0]);
                tareas.put(id, new Tarea(id, partes[2], Boolean.parseBoolean(partes[1])));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return tareas;
    }

    private void escribir(Map<Integer, Tarea> tareas) {
        List<String> lineas = new ArrayList<>();
        for (Tarea t : tareas.values()) {
            lineas.add(t.getId() + ";" + t.isCompletada() + ";" + t.getTitulo());
        }
        try {
            Files.write(archivo, lineas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
```

#### `src/com/tareas/service/ServicioTareas.java` — Paso 3 — el servicio

```java
package com.tareas.service;

import com.tareas.entity.Tarea;
import com.tareas.repository.RepositorioTareas;
import java.util.List;

/** Paso 3 — Reglas de negocio. Depende de la INTERFAZ y la recibe por constructor. */
public class ServicioTareas {

    private final RepositorioTareas repositorio;   // tipo = interfaz, nunca una clase concreta

    public ServicioTareas(RepositorioTareas repositorio) {
        this.repositorio = repositorio;            // lo RECIBE, no hace new
    }

    public Tarea crear(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        Tarea tarea = new Tarea(siguienteId(), titulo.trim(), false);
        repositorio.guardar(tarea);
        return tarea;
    }

    public Tarea completar(int id) {
        Tarea tarea = repositorio.buscarPorId(id);
        if (tarea == null) {
            throw new IllegalArgumentException("No existe la tarea " + id);
        }
        tarea.marcarCompletada();
        repositorio.guardar(tarea);
        return tarea;
    }

    public List<Tarea> listar() {
        return repositorio.listarTodas();
    }

    private int siguienteId() {
        int mayor = 0;
        for (Tarea t : repositorio.listarTodas()) {
            mayor = Math.max(mayor, t.getId());
        }
        return mayor + 1;
    }
}
```

#### `src/com/tareas/vista/VistaConsola.java` — Vista

```java
package com.tareas.vista;

import com.tareas.entity.Tarea;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Capa de vista: solo pide y muestra datos. No conoce el servicio ni el repositorio. */
public class VistaConsola {

    private final Scanner entrada = new Scanner(System.in);

    public int pedirOpcion() {
        System.out.println("\n1) Crear tarea  2) Listar  3) Completar  0) Salir");
        System.out.print("> ");
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        } catch (NoSuchElementException e) {
            return 0;   // se acabó la entrada
        }
    }

    public String pedirTexto(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return entrada.nextLine();
    }

    public int pedirNumero(String etiqueta) {
        try {
            return Integer.parseInt(pedirTexto(etiqueta).trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Debe ser un número");
        }
    }

    public void mostrarTareas(List<Tarea> tareas) {
        if (tareas.isEmpty()) System.out.println("(no hay tareas)");
        tareas.forEach(System.out::println);
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }
}
```

#### `src/com/tareas/controlador/ControladorTareas.java` — Controlador

```java
package com.tareas.controlador;

import com.tareas.service.ServicioTareas;
import com.tareas.vista.VistaConsola;

/** Capa de controlador: traduce lo que hace el usuario en llamadas al servicio. */
public class ControladorTareas {

    private final ServicioTareas servicio;
    private final VistaConsola vista;

    public ControladorTareas(ServicioTareas servicio, VistaConsola vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    public void iniciar() {
        boolean seguir = true;
        while (seguir) {
            try {
                switch (vista.pedirOpcion()) {
                    case 1 -> vista.mostrarMensaje("Creada: " + servicio.crear(vista.pedirTexto("Título")));
                    case 2 -> vista.mostrarTareas(servicio.listar());
                    case 3 -> vista.mostrarMensaje("Completada: " + servicio.completar(vista.pedirNumero("Id")));
                    case 0 -> seguir = false;
                    default -> vista.mostrarMensaje("Opción no válida");
                }
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
        vista.mostrarMensaje("Hasta luego");
    }
}
```

#### `src/com/tareas/Principal.java` — Paso 4 — la inyección

```java
package com.tareas;

import com.tareas.controlador.ControladorTareas;
import com.tareas.repository.RepositorioTareas;
import com.tareas.repository.RepositorioTareasArchivo;
import com.tareas.repository.RepositorioTareasMemoria;
import com.tareas.service.ServicioTareas;
import com.tareas.vista.VistaConsola;

/**
 * Paso 4 — El ÚNICO lugar que sabe qué implementación se usa (la inyección).
 *
 * Uso: java com.tareas.Principal [archivo | memoria]      (por defecto: archivo)
 */
public class Principal {

    public static void main(String[] args) {
        String modo = args.length > 0 ? args[0] : "archivo";

        // 1) Se elige y se CREA la implementación (lo más profundo, primero)
        RepositorioTareas repositorio = modo.equals("memoria")
                ? new RepositorioTareasMemoria()
                : new RepositorioTareasArchivo("tareas.txt");
        System.out.println("Guardando con: " + repositorio.getClass().getSimpleName());

        // 2) Se INYECTA en el servicio (el servicio solo ve la interfaz)
        ServicioTareas servicio = new ServicioTareas(repositorio);

        // 3) Se inyecta el servicio en el controlador, junto con la vista
        ControladorTareas controlador = new ControladorTareas(servicio, new VistaConsola());

        // 4) Arranca la aplicación
        controlador.iniciar();
    }
}
```

#### `antes/ServicioAcoplado.java` — Paso 0 — el ❌ acoplado

```java
/**
 * ❌ El "antes": el servicio crea su propia dependencia con new.
 * Para guardar en otro lugar hay que EDITAR esta clase.
 *
 * Ejecutar:  javac -d out0 antes/ServicioAcoplado.java && java -cp out0 ServicioAcoplado
 */
public class ServicioAcoplado {

    static class AlmacenArchivo {
        void guardar(String titulo) { System.out.println("Escribiendo en tareas.txt: " + titulo); }
    }

    private final AlmacenArchivo almacen = new AlmacenArchivo();   // atado al archivo para siempre

    void crear(String titulo) { almacen.guardar(titulo); }

    public static void main(String[] args) {
        new ServicioAcoplado().crear("Estudiar interfaces");
    }
}
```

#### `Prueba.java` — Prueba sin menú ni archivos

```java
import com.tareas.repository.RepositorioTareas;
import com.tareas.repository.RepositorioTareasMemoria;
import com.tareas.service.ServicioTareas;

/**
 * Prueba del servicio SIN archivos ni menú: se inyecta la versión en memoria.
 * Ejecutar (con out/ ya compilado):  javac -cp out -d out Prueba.java && java -cp out Prueba
 */
public class Prueba {

    public static void main(String[] args) {
        RepositorioTareas repositorio = new RepositorioTareasMemoria();
        ServicioTareas servicio = new ServicioTareas(repositorio);

        servicio.crear("Estudiar interfaces");
        servicio.crear("Hacer el ejercicio");
        servicio.completar(1);

        servicio.listar().forEach(System.out::println);
        System.out.println(servicio.listar().size() == 2 ? "OK: 2 tareas" : "FALLO");

        try {
            servicio.crear("   ");
            System.out.println("FALLO: debió rechazar el título vacío");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }
    }
}
```

## ⚠️ Errores comunes

1. **Declarar el campo con la clase concreta** (`private RepositorioTareasArchivo repo`).
   Aunque inyectes por constructor, seguís acoplado.
2. **Hacer `new` de la implementación dentro del servicio.** Anula todo el beneficio.
3. **Interfaz que filtra detalles** (`guardar(Path archivo, Tarea t)`). Si el contrato
   menciona archivos, la versión en memoria no puede implementarlo.
4. **Crear una interfaz por cada clase** sin necesidad. Se justifica cuando hay (o
   habrá) más de una implementación, o cuando separa una capa de otra.
5. **Saltarse una capa**: que el controlador llame directo al repositorio. Cada capa habla
   solo con la inmediata.

## 🧭 Regla de decisión rápida

> ¿Esta clase necesita algo que pertenece a **otra capa** o que **podría cambiar**
> (dónde se guarda, un cálculo, un aviso)?
> → Que dependa de una **interfaz** y que la **reciba por constructor**.

## ✅ Autoevaluación

1. ¿Por qué `ServicioTareas` no debe importar `RepositorioTareasArchivo`?
2. ¿Qué línea de `Principal.java` hay que cambiar para guardar en memoria?
3. Si se agrega `RepositorioTareasBaseDatos`, ¿qué clases existentes cambian?
   *(Respuesta: ninguna; solo el `main` si se quiere usar.)*
4. ¿Qué diferencia hay entre **implementar** una interfaz e **inyectarla**?
5. Dibujá de memoria las flechas entre `ServicioTareas`, `RepositorioTareas` y
   `RepositorioTareasArchivo`. ¿Hacia dónde apuntan?

## 🏋️ Ejercicio corto

Creá `RepositorioTareasLog implements RepositorioTareas`, que además de guardar en una
lista imprima `"Guardada: <título>"` en cada `guardar`. Agregá un modo `log` en
`Principal` y comprobá que `ServicioTareas` **no necesitó ningún cambio**.
