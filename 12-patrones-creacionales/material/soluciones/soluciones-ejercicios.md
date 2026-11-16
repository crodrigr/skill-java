# 🔑 Soluciones de los ejercicios — Módulo 12

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar violación de Factory Method

Hay que modificar `Biblioteca.java`, en `prestarParaSala` y en `prestarADomicilio`: ambos métodos hacen
`new LibroFisico()` directamente, así que agregar un e-book exige agregar una rama en cada uno. Esto es
exactamente el acoplamiento que Factory Method evita.

## 🟡 Intermedio 01 — Aplicar Factory Method

```java
package com.biblioteca;

public interface MaterialBibliografico {
    String describir();
}
```

```java
package com.biblioteca;

public class LibroFisico implements MaterialBibliografico {
    public String describir() {
        return "Libro fisico, disponible en estanteria";
    }
}
```

```java
package com.biblioteca;

public class EBook implements MaterialBibliografico {
    public String describir() {
        return "E-book, disponible para descarga";
    }
}
```

```java
package com.biblioteca;

public class FabricaDeMateriales {
    public static MaterialBibliografico crear(String tipo) {
        if (tipo.equals("FISICO")) {
            return new LibroFisico();
        } else if (tipo.equals("EBOOK")) {
            return new EBook();
        }
        throw new IllegalArgumentException("Tipo de material desconocido: " + tipo);
    }
}
```

```java
package com.biblioteca;

public class Biblioteca {
    public MaterialBibliografico prestarParaSala(String tipo) {
        return FabricaDeMateriales.crear(tipo);
    }

    public MaterialBibliografico prestarADomicilio(String tipo) {
        return FabricaDeMateriales.crear(tipo);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Biblioteca biblioteca = new Biblioteca();

        System.out.println(biblioteca.prestarParaSala("FISICO").describir());
        System.out.println(biblioteca.prestarADomicilio("FISICO").describir());
        System.out.println(biblioteca.prestarADomicilio("EBOOK").describir());
    }
}
```

Salida real:

```text
Libro fisico, disponible en estanteria
Libro fisico, disponible en estanteria
E-book, disponible para descarga
```

## 🟢 Básico 02 — Identificar violación de Abstract Factory

`ArmadorDeCombos`... perdón, `ArmadorDeKits.armarKit` recibe `lineaCarnet` y `lineaComprobante` como dos
parámetros independientes, y crea cada insumo con su propio `new` según su propio parámetro. Nada obliga
a que ambos coincidan. La línea `kit2 (mezclado por error)=Carnet linea VIP + Comprobante linea REGULAR`
de la salida real muestra el resultado de invocarlo con líneas distintas.

## 🟡 Intermedio 02 — Aplicar Abstract Factory

```java
package com.biblioteca;

public interface Carnet {
    String describir();
}
```

```java
package com.biblioteca;

public class CarnetRegular implements Carnet {
    public String describir() {
        return "Carnet linea REGULAR";
    }
}
```

```java
package com.biblioteca;

public class CarnetVip implements Carnet {
    public String describir() {
        return "Carnet linea VIP";
    }
}
```

```java
package com.biblioteca;

public interface Comprobante {
    String describir();
}
```

```java
package com.biblioteca;

public class ComprobanteRegular implements Comprobante {
    public String describir() {
        return "Comprobante linea REGULAR";
    }
}
```

```java
package com.biblioteca;

public class ComprobanteVip implements Comprobante {
    public String describir() {
        return "Comprobante linea VIP";
    }
}
```

```java
package com.biblioteca;

public interface FabricaDeKits {
    Carnet crearCarnet();
    Comprobante crearComprobante();
}
```

```java
package com.biblioteca;

public class FabricaKitRegular implements FabricaDeKits {
    public Carnet crearCarnet() {
        return new CarnetRegular();
    }

    public Comprobante crearComprobante() {
        return new ComprobanteRegular();
    }
}
```

```java
package com.biblioteca;

public class FabricaKitVip implements FabricaDeKits {
    public Carnet crearCarnet() {
        return new CarnetVip();
    }

    public Comprobante crearComprobante() {
        return new ComprobanteVip();
    }
}
```

```java
package com.biblioteca;

public class ArmadorDeKits {
    public String armarKit(FabricaDeKits fabrica) {
        Carnet carnet = fabrica.crearCarnet();
        Comprobante comprobante = fabrica.crearComprobante();
        return carnet.describir() + " + " + comprobante.describir();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        ArmadorDeKits armador = new ArmadorDeKits();

        System.out.println("kit1=" + armador.armarKit(new FabricaKitRegular()));
        System.out.println("kit2=" + armador.armarKit(new FabricaKitVip()));
    }
}
```

Salida real:

```text
kit1=Carnet linea REGULAR + Comprobante linea REGULAR
kit2=Carnet linea VIP + Comprobante linea VIP
```

## 🟢 Básico 03 — Identificar violación de Builder

En `confundido`, el valor `"Ana Sosa - 555-2222"` (un contacto) quedó en el campo `observacionBibliotecario`,
y `"Sin observaciones"` (una observación) quedó en `contactoAlternativo`: ambos parámetros del
constructor son `String` consecutivos, así que el compilador no puede detectar que se invocó con el
orden equivocado.

## 🟡 Intermedio 03 — Aplicar Builder

```java
package com.biblioteca;

public class RegistroPrestamo {
    private String usuario;
    private String material;
    private String fechaDevolucionExtendida;
    private boolean renovado;
    private String observacionBibliotecario;
    private String contactoAlternativo;

    RegistroPrestamo(String usuario, String material, String fechaDevolucionExtendida,
                      boolean renovado, String observacionBibliotecario, String contactoAlternativo) {
        this.usuario = usuario;
        this.material = material;
        this.fechaDevolucionExtendida = fechaDevolucionExtendida;
        this.renovado = renovado;
        this.observacionBibliotecario = observacionBibliotecario;
        this.contactoAlternativo = contactoAlternativo;
    }

    public String resumen() {
        return usuario + " - " + material
            + ", fecha extendida: " + (fechaDevolucionExtendida == null ? "-" : fechaDevolucionExtendida)
            + ", renovado: " + renovado
            + ", observacion: " + (observacionBibliotecario == null ? "-" : observacionBibliotecario)
            + ", contacto alternativo: " + (contactoAlternativo == null ? "-" : contactoAlternativo);
    }
}
```

```java
package com.biblioteca;

public class RegistroPrestamoBuilder {
    private String usuario;
    private String material;
    private String fechaDevolucionExtendida;
    private boolean renovado;
    private String observacionBibliotecario;
    private String contactoAlternativo;

    public RegistroPrestamoBuilder(String usuario, String material) {
        this.usuario = usuario;
        this.material = material;
    }

    public RegistroPrestamoBuilder conFechaDevolucionExtendida(String fecha) {
        this.fechaDevolucionExtendida = fecha;
        return this;
    }

    public RegistroPrestamoBuilder conRenovado(boolean renovado) {
        this.renovado = renovado;
        return this;
    }

    public RegistroPrestamoBuilder conObservacionBibliotecario(String observacion) {
        this.observacionBibliotecario = observacion;
        return this;
    }

    public RegistroPrestamoBuilder conContactoAlternativo(String contacto) {
        this.contactoAlternativo = contacto;
        return this;
    }

    public RegistroPrestamo construir() {
        return new RegistroPrestamo(usuario, material, fechaDevolucionExtendida, renovado,
            observacionBibliotecario, contactoAlternativo);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        RegistroPrestamo completo = new RegistroPrestamoBuilder("Elena Ruiz", "Cien anios de soledad")
            .conFechaDevolucionExtendida("2026-10-15")
            .conRenovado(true)
            .conObservacionBibliotecario("Ninguna")
            .conContactoAlternativo("Pablo Ruiz - 555-1111")
            .construir();
        System.out.println("completo: " + completo.resumen());

        RegistroPrestamo parcial = new RegistroPrestamoBuilder("Pablo Sosa", "Rayuela")
            .conContactoAlternativo("Ana Sosa - 555-2222")
            .construir();
        System.out.println("parcial: " + parcial.resumen());
    }
}
```

Salida real:

```text
completo: Elena Ruiz - Cien anios de soledad, fecha extendida: 2026-10-15, renovado: true, observacion: Ninguna, contacto alternativo: Pablo Ruiz - 555-1111
parcial: Pablo Sosa - Rayuela, fecha extendida: -, renovado: false, observacion: -, contacto alternativo: Ana Sosa - 555-2222
```

## 🟢 Básico 04 — Identificar violación de Prototype

`ListaDeLectura.clonar()` construye la lista nueva con `new ListaDeLectura(nuevoCurso, this.titulos)`,
reutilizando la misma referencia a la lista de títulos en vez de crear una copia. Por eso, agregar un
título a `copia` (`copia.agregarTitulo("Ficciones")`) también lo agrega a `original`: ambas variables
apuntan a la misma lista en memoria.

## 🟡 Intermedio 04 — Aplicar Prototype (clonado profundo)

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class ListaDeLectura {
    private String curso;
    private List<String> titulos;

    public ListaDeLectura(String curso, List<String> titulos) {
        this.curso = curso;
        this.titulos = titulos;
    }

    public ListaDeLectura clonar(String nuevoCurso) {
        // Clonado profundo: crea una copia nueva de la lista, independiente de la original
        return new ListaDeLectura(nuevoCurso, new ArrayList<>(this.titulos));
    }

    public void agregarTitulo(String titulo) {
        titulos.add(titulo);
    }

    public String resumen() {
        return curso + ": " + titulos;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        List<String> titulos = new ArrayList<>();
        titulos.add("Cien anios de soledad");
        titulos.add("Rayuela");
        ListaDeLectura original = new ListaDeLectura("Literatura I", titulos);

        ListaDeLectura copia = original.clonar("Literatura II");
        copia.agregarTitulo("Ficciones");

        System.out.println("original: " + original.resumen());
        System.out.println("copia: " + copia.resumen());
    }
}
```

Salida real:

```text
original: Literatura I: [Cien anios de soledad, Rayuela]
copia: Literatura II: [Cien anios de soledad, Rayuela, Ficciones]
```

## 🟢 Básico 05 — Identificar violación de Singleton

`mismaInstancia` da `false` porque `SalaLectura` y `Mostrador` declaran cada uno su propio atributo
`private ContadorDePrestamos contador = new ContadorDePrestamos();`: como el constructor de
`ContadorDePrestamos` es público, cada clase crea su propia instancia independiente. Un reporte que solo
consulte `sala.getContador()` vería el total de préstamos registrados en la sala, sin los del mostrador —
una imagen incompleta. Más en general, abusar de Singleton (usarlo como atajo para no pasar dependencias
por constructor) acopla el código a un estado global oculto, difícil de sustituir y de aislar al probar.

## 🟡 Intermedio 05 — Aplicar Singleton

```java
package com.biblioteca;

public class ContadorDePrestamos {
    private static final ContadorDePrestamos instancia = new ContadorDePrestamos();

    private int total = 0;

    private ContadorDePrestamos() {
    }

    public static ContadorDePrestamos getInstancia() {
        return instancia;
    }

    public void registrarPrestamo() {
        total = total + 1;
    }

    public int getTotal() {
        return total;
    }
}
```

```java
package com.biblioteca;

public class SalaLectura {
    public void prestar() {
        ContadorDePrestamos.getInstancia().registrarPrestamo();
    }
}
```

```java
package com.biblioteca;

public class Mostrador {
    public void prestar() {
        ContadorDePrestamos.getInstancia().registrarPrestamo();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        SalaLectura sala = new SalaLectura();
        Mostrador mostrador = new Mostrador();

        sala.prestar();
        mostrador.prestar();
        mostrador.prestar();

        ContadorDePrestamos desdeSala = ContadorDePrestamos.getInstancia();
        ContadorDePrestamos desdeMostrador = ContadorDePrestamos.getInstancia();

        boolean mismaInstancia = desdeSala == desdeMostrador;
        System.out.println("mismaInstancia=" + mismaInstancia);
        System.out.println("total=" + desdeSala.getTotal());
    }
}
```

Salida real:

```text
mismaInstancia=true
total=3
```

## 🔴 Avanzado 01 — Corregir un diseño con dos patrones ausentes

**Patrón ausente 1 (Factory Method)**: en el código de partida, `GestorDeReservas.crearReserva` decide
con `if`/`else` e invoca `new` directamente sobre `ReservaNormal`/`ReservaUrgente`. Se corrige
delegando esa decisión en una fábrica:

```java
package com.biblioteca;

public interface Reserva {
    String describir();
}
```

```java
package com.biblioteca;

public class ReservaNormal implements Reserva {
    public String describir() {
        return "Reserva NORMAL";
    }
}
```

```java
package com.biblioteca;

public class ReservaUrgente implements Reserva {
    public String describir() {
        return "Reserva URGENTE";
    }
}
```

```java
package com.biblioteca;

public class FabricaDeReservas {
    public static Reserva crear(String tipo) {
        if (tipo.equals("NORMAL")) {
            return new ReservaNormal();
        } else if (tipo.equals("URGENTE")) {
            return new ReservaUrgente();
        }
        throw new IllegalArgumentException("Tipo de reserva desconocido: " + tipo);
    }
}
```

**Patrón ausente 2 (Singleton)**: en el código de partida, `GestorDeReservas` tiene un constructor
público, y `ModuloSala`/`ModuloApp` crean cada uno su propia instancia. Se corrige con constructor
privado e instancia estática única:

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class GestorDeReservas {
    private static final GestorDeReservas instancia = new GestorDeReservas();

    private List<String> reservas = new ArrayList<>();

    private GestorDeReservas() {
    }

    public static GestorDeReservas getInstancia() {
        return instancia;
    }

    public void crearReserva(String tipo) {
        Reserva reserva = FabricaDeReservas.crear(tipo);
        reservas.add(reserva.describir());
    }

    public int totalReservas() {
        return reservas.size();
    }
}
```

```java
package com.biblioteca;

public class ModuloSala {
    public void reservar(String tipo) {
        GestorDeReservas.getInstancia().crearReserva(tipo);
    }
}
```

```java
package com.biblioteca;

public class ModuloApp {
    public void reservar(String tipo) {
        GestorDeReservas.getInstancia().crearReserva(tipo);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        ModuloSala sala = new ModuloSala();
        ModuloApp app = new ModuloApp();

        sala.reservar("NORMAL");
        app.reservar("URGENTE");
        app.reservar("NORMAL");

        GestorDeReservas desdeSala = GestorDeReservas.getInstancia();
        GestorDeReservas desdeApp = GestorDeReservas.getInstancia();

        boolean mismaInstancia = desdeSala == desdeApp;
        System.out.println("mismaInstancia=" + mismaInstancia);
        System.out.println("total=" + desdeSala.totalReservas());
    }
}
```

Salida real:

```text
mismaInstancia=true
total=3
```

## 🏆 Desafío 01 — Elegir y aplicar el patrón adecuado a un caso nuevo

Una solución posible: el problema (armar un evento con datos opcionales que se combinan de formas
distintas) encaja mejor con **Builder**, no con los otros cuatro patrones — no hay una familia de
objetos relacionados (descarta Abstract Factory), no hay una única clase concreta a elegir entre varias
(descarta Factory Method), no se parte de un evento ya armado para copiarlo (descarta Prototype), y no
hay ningún requisito de instancia única (descarta Singleton).

```java
package com.biblioteca;

public class EventoBiblioteca {
    private String titulo;
    private String fecha;
    private Integer cupo;
    private boolean requiereInscripcion;
    private boolean esVirtual;
    private String enlace;
    private String moderador;

    EventoBiblioteca(String titulo, String fecha, Integer cupo, boolean requiereInscripcion,
                      boolean esVirtual, String enlace, String moderador) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.cupo = cupo;
        this.requiereInscripcion = requiereInscripcion;
        this.esVirtual = esVirtual;
        this.enlace = enlace;
        this.moderador = moderador;
    }

    public String resumen() {
        return titulo + " (" + fecha + ")"
            + ", cupo: " + (cupo == null ? "sin limite" : cupo)
            + ", inscripcion: " + requiereInscripcion
            + ", virtual: " + esVirtual
            + ", enlace: " + (enlace == null ? "-" : enlace)
            + ", moderador: " + (moderador == null ? "-" : moderador);
    }
}
```

```java
package com.biblioteca;

public class EventoBibliotecaBuilder {
    private String titulo;
    private String fecha;
    private Integer cupo;
    private boolean requiereInscripcion;
    private boolean esVirtual;
    private String enlace;
    private String moderador;

    public EventoBibliotecaBuilder(String titulo, String fecha) {
        this.titulo = titulo;
        this.fecha = fecha;
    }

    public EventoBibliotecaBuilder conCupo(int cupo) {
        this.cupo = cupo;
        return this;
    }

    public EventoBibliotecaBuilder conInscripcionRequerida() {
        this.requiereInscripcion = true;
        return this;
    }

    public EventoBibliotecaBuilder virtual(String enlace) {
        this.esVirtual = true;
        this.enlace = enlace;
        return this;
    }

    public EventoBibliotecaBuilder conModerador(String moderador) {
        this.moderador = moderador;
        return this;
    }

    public EventoBiblioteca construir() {
        return new EventoBiblioteca(titulo, fecha, cupo, requiereInscripcion, esVirtual, enlace, moderador);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        EventoBiblioteca presencial = new EventoBibliotecaBuilder("Club de lectura", "2026-11-05")
            .conCupo(20)
            .conInscripcionRequerida()
            .conModerador("Elena Ruiz")
            .construir();
        System.out.println("presencial: " + presencial.resumen());

        EventoBiblioteca virtual = new EventoBibliotecaBuilder("Charla de autor", "2026-11-12")
            .virtual("https://biblioteca.edu/charla")
            .construir();
        System.out.println("virtual: " + virtual.resumen());
    }
}
```

Salida real, probada con un evento presencial (cupo, inscripción, moderador) y uno virtual (enlace, sin
cupo ni moderador):

```text
presencial: Club de lectura (2026-11-05), cupo: 20, inscripcion: true, virtual: false, enlace: -, moderador: Elena Ruiz
virtual: Charla de autor (2026-11-12), cupo: sin limite, inscripcion: false, virtual: true, enlace: https://biblioteca.edu/charla, moderador: -
```
