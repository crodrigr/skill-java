# 🔑 Soluciones de los ejercicios — Módulo 11

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar violación de SRP

`GestorPrestamos.registrarPrestamo` mezcla tres responsabilidades: **(1)** registrar el préstamo
(`totalCatalogados` y el mensaje de registro), **(2)** imprimir el comprobante (el bloque
`--- Comprobante ---`), y **(3)** actualizar el contador de ejemplares disponibles. Cambiar el formato
del comprobante (por ejemplo, agregar la fecha) no debería afectar cómo se descuenta un ejemplar del
catálogo, pero al estar las tres en el mismo método, cualquier cambio en una obliga a tocar la misma
clase que las otras dos.

## 🟡 Intermedio 01 — Refactorizar SRP

```java
package com.biblioteca;

public class GestorPrestamos {
    private ComprobanteImpresor impresor;
    private ContadorCatalogo contador;

    public GestorPrestamos(ComprobanteImpresor impresor, ContadorCatalogo contador) {
        this.impresor = impresor;
        this.contador = contador;
    }

    public void registrarPrestamo(String usuario, String libro) {
        System.out.println("Prestamo registrado: " + usuario + " -> " + libro);
        impresor.imprimir(usuario, libro);
        contador.descontarEjemplar();
    }
}
```

```java
package com.biblioteca;

public class ComprobanteImpresor {
    public void imprimir(String usuario, String libro) {
        System.out.println("--- Comprobante ---");
        System.out.println("Usuario: " + usuario);
        System.out.println("Libro: " + libro);
        System.out.println("-------------------");
    }
}
```

```java
package com.biblioteca;

public class ContadorCatalogo {
    private int totalCatalogados = 120;

    public void descontarEjemplar() {
        totalCatalogados = totalCatalogados - 1;
        System.out.println("Ejemplares disponibles: " + totalCatalogados);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        GestorPrestamos gestor = new GestorPrestamos(new ComprobanteImpresor(), new ContadorCatalogo());
        gestor.registrarPrestamo("Elena Ruiz", "Cien anios de soledad");
        gestor.registrarPrestamo("Pablo Sosa", "Rayuela");
    }
}
```

Salida real (idéntica a la del código de partida):

```text
Prestamo registrado: Elena Ruiz -> Cien anios de soledad
--- Comprobante ---
Usuario: Elena Ruiz
Libro: Cien anios de soledad
-------------------
Ejemplares disponibles: 119
Prestamo registrado: Pablo Sosa -> Rayuela
--- Comprobante ---
Usuario: Pablo Sosa
Libro: Rayuela
-------------------
Ejemplares disponibles: 118
```

## 🟢 Básico 02 — Identificar violación de OCP

Hay que modificar `CalculadoraMulta.calcularMulta`, agregando una rama
`else if (tipoUsuario.equals("EXTERNO")) { ... }` antes del `throw` final. Eso es exactamente lo que OCP
busca evitar: el único método existente, que ya funcionaba para `"ESTUDIANTE"`, `"DOCENTE"` e
`"INVITADO"`, tiene que reabrirse y modificarse cada vez que aparece un tipo nuevo.

## 🟡 Intermedio 02 — Diseñar solución OCP

```java
package com.biblioteca;

public interface EstrategiaMulta {
    double calcular(int diasAtraso);
}
```

```java
package com.biblioteca;

public class MultaEstudiante implements EstrategiaMulta {
    public double calcular(int diasAtraso) {
        return diasAtraso * 50.0;
    }
}
```

```java
package com.biblioteca;

public class MultaDocente implements EstrategiaMulta {
    public double calcular(int diasAtraso) {
        return diasAtraso * 20.0;
    }
}
```

```java
package com.biblioteca;

public class MultaInvitado implements EstrategiaMulta {
    public double calcular(int diasAtraso) {
        return diasAtraso * 100.0;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        int diasAtraso = 3;

        System.out.println("ESTUDIANTE=" + new MultaEstudiante().calcular(diasAtraso));
        System.out.println("DOCENTE=" + new MultaDocente().calcular(diasAtraso));
        System.out.println("INVITADO=" + new MultaInvitado().calcular(diasAtraso));
    }
}
```

Salida real (idéntica a la del código de partida):

```text
ESTUDIANTE=150.0
DOCENTE=60.0
INVITADO=300.0
```

Verificado agregando un cuarto tipo (`MultaExterno implements EstrategiaMulta`, compilado junto a las
cuatro clases anteriores): las cuatro clases existentes (`EstrategiaMulta`, `MultaEstudiante`,
`MultaDocente`, `MultaInvitado`) quedan **idénticas, byte a byte** (`diff` sin salida) — el tipo nuevo se
agrega solo con una clase nueva.

## 🟢 Básico 03 — Identificar violación de LSP

`Usuario.renovarPrestamo` permite renovar mientras `renovacionesPrevias < 3` (hasta 2 renovaciones
previas). `UsuarioInvitado.renovarPrestamo` la sobrescribe exigiendo `renovacionesPrevias < 0`, una
condición imposible de cumplir para cualquier valor válido: en la práctica, un invitado nunca puede
renovar. El `Demo` recorre una `List<Usuario>` mixta y llama a `renovarPrestamo(0)` sobre cada uno sin
distinguir el tipo real; obtiene `true` para `Usuario` y `false` para `UsuarioInvitado` ante la misma
llamada, sin ninguna excepción que lo avise.

## 🟡 Intermedio 03 — Corregir violación de LSP

```java
package com.biblioteca;

public class Usuario {
    protected String nombre;
    private int renovacionesPermitidas;

    public Usuario(String nombre) {
        this(nombre, 3);
    }

    protected Usuario(String nombre, int renovacionesPermitidas) {
        this.nombre = nombre;
        this.renovacionesPermitidas = renovacionesPermitidas;
    }

    public int getRenovacionesPermitidas() {
        return renovacionesPermitidas;
    }

    public boolean renovarPrestamo(int renovacionesPrevias) {
        if (renovacionesPrevias >= renovacionesPermitidas) {
            return false;
        }
        System.out.println(nombre + ": prestamo renovado (van " + (renovacionesPrevias + 1) + ")");
        return true;
    }

    public String getNombre() {
        return nombre;
    }
}
```

```java
package com.biblioteca;

public class UsuarioInvitado extends Usuario {
    public UsuarioInvitado(String nombre) {
        super(nombre, 0);
    }
}
```

```java
package com.biblioteca;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        List<Usuario> usuarios = List.of(new Usuario("Elena Ruiz"), new UsuarioInvitado("Pablo Sosa"));

        for (Usuario usuario : usuarios) {
            int permitidas = usuario.getRenovacionesPermitidas();
            System.out.println(usuario.getNombre() + ": renovaciones permitidas=" + permitidas);
            boolean exito = usuario.renovarPrestamo(0);
            System.out.println("resultado=" + exito);
        }
    }
}
```

Salida real:

```text
Elena Ruiz: renovaciones permitidas=3
Elena Ruiz: prestamo renovado (van 1)
resultado=true
Pablo Sosa: renovaciones permitidas=0
resultado=false
```

## 🟢 Básico 04 — Identificar violación de ISP

`multar` no tiene sentido real para `Voluntario`: un voluntario no aplica multas. Sin embargo, la
interfaz `PersonalBiblioteca` obliga a toda clase que la implemente a declarar los tres métodos
(`catalogar`, `atenderPublico`, `multar`), sin importar si el rol de esa clase incluye realmente esa
capacidad — por eso `Voluntario.multar` termina siendo un método sin ninguna implementación con sentido
real, que solo imprime una disculpa.

## 🟡 Intermedio 04 — Dividir interfaz que viola ISP

```java
package com.biblioteca;

public interface Cataloga {
    void catalogar(String libro);
}
```

```java
package com.biblioteca;

public interface AtiendePublico {
    void atenderPublico(String usuario);
}
```

```java
package com.biblioteca;

public interface Multa {
    void multar(String usuario, double monto);
}
```

```java
package com.biblioteca;

public class Bibliotecario implements Cataloga, AtiendePublico, Multa {
    private String nombre;

    public Bibliotecario(String nombre) {
        this.nombre = nombre;
    }

    public void catalogar(String libro) {
        System.out.println(nombre + " cataloga " + libro);
    }

    public void atenderPublico(String usuario) {
        System.out.println(nombre + " atiende a " + usuario);
    }

    public void multar(String usuario, double monto) {
        System.out.println(nombre + " multa $" + monto + " a " + usuario);
    }
}
```

```java
package com.biblioteca;

public class Voluntario implements Cataloga, AtiendePublico {
    private String nombre;

    public Voluntario(String nombre) {
        this.nombre = nombre;
    }

    public void catalogar(String libro) {
        System.out.println(nombre + " cataloga " + libro);
    }

    public void atenderPublico(String usuario) {
        System.out.println(nombre + " atiende a " + usuario);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Bibliotecario bibliotecario = new Bibliotecario("Marcos Vega");
        Voluntario voluntario = new Voluntario("Nadia Rios");

        bibliotecario.catalogar("Ficciones");
        bibliotecario.multar("Elena Ruiz", 200.0);

        voluntario.atenderPublico("Pablo Sosa");
        // voluntario.multar(...) ya no existe: Voluntario no implementa Multa
    }
}
```

Salida real:

```text
Marcos Vega cataloga Ficciones
Marcos Vega multa $200.0 a Elena Ruiz
Nadia Rios atiende a Pablo Sosa
```

Tras la división, intentar compilar una llamada a `voluntario.multar(...)` sobre una variable de tipo
`Voluntario` produce un error real de `javac` (`cannot find symbol`), porque `Voluntario` ya no
implementa `Multa` — la misma prueba concreta que el Ejemplo 05 del material.

## 🟢 Básico 05 — Identificar violación de DIP

La línea `private BuscadorLocal buscador = new BuscadorLocal();` es la responsable: `SistemaBusqueda`
crea su propia dependencia concreta con `new`, dentro de la declaración del atributo. Para buscar en un
catálogo en línea, habría que modificar esa misma línea (y el tipo del atributo) dentro de
`SistemaBusqueda` — la clase depende directamente de un detalle concreto, no de una abstracción.

## 🟡 Intermedio 05 — Diseñar solución DIP

```java
package com.biblioteca;

public interface EstrategiaBusqueda {
    String buscar(String titulo);
}
```

```java
package com.biblioteca;

public class BuscadorLocal implements EstrategiaBusqueda {
    public String buscar(String titulo) {
        return "Encontrado en el catalogo local: " + titulo;
    }
}
```

```java
package com.biblioteca;

public class BuscadorEnLinea implements EstrategiaBusqueda {
    public String buscar(String titulo) {
        return "Encontrado en el catalogo en linea: " + titulo;
    }
}
```

```java
package com.biblioteca;

public class SistemaBusqueda {
    private EstrategiaBusqueda buscador;

    public SistemaBusqueda(EstrategiaBusqueda buscador) {
        this.buscador = buscador;
    }

    public void buscarLibro(String titulo) {
        System.out.println(buscador.buscar(titulo));
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        SistemaBusqueda local = new SistemaBusqueda(new BuscadorLocal());
        local.buscarLibro("Rayuela");

        SistemaBusqueda enLinea = new SistemaBusqueda(new BuscadorEnLinea());
        enLinea.buscarLibro("Ficciones");
    }
}
```

Salida real:

```text
Encontrado en el catalogo local: Rayuela
Encontrado en el catalogo en linea: Ficciones
```

## 🔴 Avanzado 01 — Corregir un diseño con dos violaciones

**Violación 1 (LSP)**: en el código de partida, `UsuarioInvitado.devolverPrestamo` sobrescribe el método
heredado con una precondición más estricta (`diasAtraso > 0` rechaza la devolución), devolviendo `false`
en silencio ante cualquier atraso. Se corrige moviendo el límite a un dato consultable de `Usuario`:

```java
package com.biblioteca;

public class Usuario {
    protected String nombre;
    private int atrasoMaximoPermitido;

    public Usuario(String nombre) {
        this(nombre, 5);
    }

    protected Usuario(String nombre, int atrasoMaximoPermitido) {
        this.nombre = nombre;
        this.atrasoMaximoPermitido = atrasoMaximoPermitido;
    }

    public int getAtrasoMaximoPermitido() {
        return atrasoMaximoPermitido;
    }

    public boolean devolverPrestamo(int diasAtraso) {
        if (diasAtraso > atrasoMaximoPermitido) {
            return false;
        }
        System.out.println(nombre + ": devolucion registrada (" + diasAtraso + " dias de atraso)");
        return true;
    }

    public String getNombre() {
        return nombre;
    }
}
```

```java
package com.biblioteca;

public class UsuarioInvitado extends Usuario {
    public UsuarioInvitado(String nombre) {
        super(nombre, 0);
    }
}
```

**Violación 2 (DIP)**: en el código de partida, `GestorDevoluciones` crea `new NotificadorConsola()`
dentro de la declaración de su atributo. Se corrige declarando una interfaz de notificación y
recibiéndola por constructor:

```java
package com.biblioteca;

public interface CanalAviso {
    void avisar(String nombre, boolean exito);
}
```

```java
package com.biblioteca;

public class NotificadorConsola implements CanalAviso {
    public void avisar(String nombre, boolean exito) {
        if (exito) {
            System.out.println("Aviso a " + nombre + ": devolucion procesada correctamente");
        } else {
            System.out.println("Aviso a " + nombre + ": devolucion rechazada");
        }
    }
}
```

```java
package com.biblioteca;

public class GestorDevoluciones {
    private CanalAviso notificador;

    public GestorDevoluciones(CanalAviso notificador) {
        this.notificador = notificador;
    }

    public void procesarDevolucion(Usuario usuario, int diasAtraso) {
        boolean exito = usuario.devolverPrestamo(diasAtraso);
        notificador.avisar(usuario.getNombre(), exito);
    }
}
```

```java
package com.biblioteca;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        GestorDevoluciones gestor = new GestorDevoluciones(new NotificadorConsola());
        List<Usuario> usuarios = List.of(new Usuario("Elena Ruiz"), new UsuarioInvitado("Pablo Sosa"));

        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre() + ": atraso maximo=" + usuario.getAtrasoMaximoPermitido());
            gestor.procesarDevolucion(usuario, 2);
        }
    }
}
```

Salida real:

```text
Elena Ruiz: atraso maximo=5
Elena Ruiz: devolucion registrada (2 dias de atraso)
Aviso a Elena Ruiz: devolucion procesada correctamente
Pablo Sosa: atraso maximo=0
Aviso a Pablo Sosa: devolucion rechazada
```

## 🏆 Desafío 01 — Diseñar un sistema pequeño que respete los 5 principios

Una solución posible: un sistema de turnos donde `EstrategiaPrioridad` (OCP + DIP) calcula la prioridad
de un turno, `AtiendeTurno` (ISP) declara el único método que un atendedor necesita, `Atendedor` (LSP) es
una clase base con un nivel máximo consultable (nunca sobrescrito en silencio), y `GestorTurnos` (SRP)
solo orquesta: decide quién atiende cada turno y delega, sin imprimir nada por su cuenta.

```java
package com.biblioteca;

public interface EstrategiaPrioridad {
    int calcularPrioridad(String tipoTurno);
}
```

```java
package com.biblioteca;

public class PrioridadNormal implements EstrategiaPrioridad {
    public int calcularPrioridad(String tipoTurno) {
        // Trata todos los turnos por igual, sin distinguir su tipo
        return 1;
    }
}
```

```java
package com.biblioteca;

public class PrioridadUrgente implements EstrategiaPrioridad {
    public int calcularPrioridad(String tipoTurno) {
        return tipoTurno.equals("URGENTE") ? 10 : 1;
    }
}
```

```java
package com.biblioteca;

public interface AtiendeTurno {
    void atender(String persona);
}
```

```java
package com.biblioteca;

public abstract class Atendedor implements AtiendeTurno {
    protected String nombre;
    private int nivelMaximo;

    protected Atendedor(String nombre, int nivelMaximo) {
        this.nombre = nombre;
        this.nivelMaximo = nivelMaximo;
    }

    public int getNivelMaximo() {
        return nivelMaximo;
    }

    public boolean puedeAtender(int prioridad) {
        return prioridad <= nivelMaximo;
    }

    public void atender(String persona) {
        System.out.println(nombre + " atiende a " + persona);
    }
}
```

```java
package com.biblioteca;

public class Bibliotecario extends Atendedor {
    public Bibliotecario(String nombre) {
        super(nombre, 10);
    }
}
```

```java
package com.biblioteca;

public class Pasante extends Atendedor {
    public Pasante(String nombre) {
        super(nombre, 1);
    }
}
```

```java
package com.biblioteca;

import java.util.List;

public class GestorTurnos {
    private List<Atendedor> atendedores;
    private EstrategiaPrioridad estrategia;

    public GestorTurnos(List<Atendedor> atendedores, EstrategiaPrioridad estrategia) {
        this.atendedores = atendedores;
        this.estrategia = estrategia;
    }

    public void procesarTurno(String persona, String tipoTurno) {
        int prioridad = estrategia.calcularPrioridad(tipoTurno);
        for (Atendedor atendedor : atendedores) {
            if (atendedor.puedeAtender(prioridad)) {
                atendedor.atender(persona);
                return;
            }
        }
        System.out.println("Sin atendedor disponible para " + persona);
    }
}
```

```java
package com.biblioteca;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        List<Atendedor> atendedores = List.of(new Pasante("Nadia Rios"), new Bibliotecario("Marcos Vega"));

        System.out.println("--- Con PrioridadNormal ---");
        GestorTurnos gestorNormal = new GestorTurnos(atendedores, new PrioridadNormal());
        gestorNormal.procesarTurno("Elena Ruiz", "REGULAR");
        gestorNormal.procesarTurno("Pablo Sosa", "URGENTE");

        System.out.println("--- Con PrioridadUrgente ---");
        GestorTurnos gestorUrgente = new GestorTurnos(atendedores, new PrioridadUrgente());
        gestorUrgente.procesarTurno("Carla Diaz", "REGULAR");
        gestorUrgente.procesarTurno("Jorge Paz", "URGENTE");
    }
}
```

Salida real, probada con dos roles (`Pasante`, nivel 1; `Bibliotecario`, nivel 10) y dos estrategias
(`PrioridadNormal`, que trata todo turno como nivel 1; `PrioridadUrgente`, que escala los turnos
`"URGENTE"` a nivel 10):

```text
--- Con PrioridadNormal ---
Nadia Rios atiende a Elena Ruiz
Nadia Rios atiende a Pablo Sosa
--- Con PrioridadUrgente ---
Nadia Rios atiende a Carla Diaz
Marcos Vega atiende a Jorge Paz
```

Con `PrioridadNormal`, incluso un turno `"URGENTE"` queda en nivel 1 y lo atiende el `Pasante`. Con
`PrioridadUrgente`, el mismo turno escala a nivel 10 y excede lo que el `Pasante` puede atender
(`puedeAtender` consulta su `nivelMaximo`, nunca sobrescrito), así que pasa al `Bibliotecario` — la misma
estructura de clases, con dos estrategias intercambiables sin modificar ninguna otra clase.
