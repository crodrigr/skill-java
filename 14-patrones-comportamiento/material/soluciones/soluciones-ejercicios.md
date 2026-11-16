# 🔑 Soluciones de los ejercicios — Módulo 14

> Material docente, no enlazar desde la audiencia estudiante.
## 🟢 Básico 01 — Identificar violación de Chain of Responsibility

Hay que modificar `GestorDePrestamos.aprobar()`, agregando una rama más `else if
(excepcionalidad.equals("CRITICA"))`. Un único método concentra la decisión de todos los niveles de
aprobación posibles, así que cada nivel nuevo repite este mismo patrón.

## 🟡 Intermedio 01 — Aplicar Chain of Responsibility

```java
package com.biblioteca;

public interface ManejadorDePrestamo {
    String aprobar(SolicitudDePrestamoEspecial solicitud);
}
```

```java
package com.biblioteca;

public class AprobadorDeSala implements ManejadorDePrestamo {
    private ManejadorDePrestamo siguiente;

    public AprobadorDeSala(ManejadorDePrestamo siguiente) {
        this.siguiente = siguiente;
    }

    public String aprobar(SolicitudDePrestamoEspecial solicitud) {
        if (solicitud.getExcepcionalidad().equals("BAJA")) {
            return "Aprobado por la sala: " + solicitud.getTituloEjemplar();
        }
        return siguiente.aprobar(solicitud);
    }
}
```

```java
package com.biblioteca;

public class AprobadorDeSeccion implements ManejadorDePrestamo {
    private ManejadorDePrestamo siguiente;

    public AprobadorDeSeccion(ManejadorDePrestamo siguiente) {
        this.siguiente = siguiente;
    }

    public String aprobar(SolicitudDePrestamoEspecial solicitud) {
        if (solicitud.getExcepcionalidad().equals("MEDIA")) {
            return "Aprobado por la seccion: " + solicitud.getTituloEjemplar();
        }
        return siguiente.aprobar(solicitud);
    }
}
```

```java
package com.biblioteca;

public class AprobadorDeDireccion implements ManejadorDePrestamo {
    public String aprobar(SolicitudDePrestamoEspecial solicitud) {
        if (solicitud.getExcepcionalidad().equals("ALTA")) {
            return "Aprobado por la direccion: " + solicitud.getTituloEjemplar();
        }
        throw new IllegalArgumentException("Ningun nivel pudo aprobar: " + solicitud.getExcepcionalidad());
    }
}
```

```java
package com.biblioteca;

public class SolicitudDePrestamoEspecial {
    private String tituloEjemplar;
    private String excepcionalidad;

    public SolicitudDePrestamoEspecial(String tituloEjemplar, String excepcionalidad) {
        this.tituloEjemplar = tituloEjemplar;
        this.excepcionalidad = excepcionalidad;
    }

    public String getTituloEjemplar() {
        return tituloEjemplar;
    }

    public String getExcepcionalidad() {
        return excepcionalidad;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ManejadorDePrestamo direccion = new AprobadorDeDireccion();
        ManejadorDePrestamo seccion = new AprobadorDeSeccion(direccion);
        ManejadorDePrestamo sala = new AprobadorDeSala(seccion);

        SolicitudDePrestamoEspecial s1 = new SolicitudDePrestamoEspecial("Manuscrito comun", "BAJA");
        SolicitudDePrestamoEspecial s2 = new SolicitudDePrestamoEspecial("Primera edicion", "MEDIA");
        SolicitudDePrestamoEspecial s3 = new SolicitudDePrestamoEspecial("Manuscrito unico", "ALTA");

        System.out.println(sala.aprobar(s1));
        System.out.println(sala.aprobar(s2));
        System.out.println(sala.aprobar(s3));
    }
}
```

Salida real:

```text
Aprobado por la sala: Manuscrito comun
Aprobado por la seccion: Primera edicion
Aprobado por la direccion: Manuscrito unico
```

## 🟢 Básico 02 — Identificar violación de Command

No existe ninguna forma uniforme de deshacer la última acción: el bibliotecario solo podría llamar
manualmente a `liberar()`, y solo si recuerda cuál fue la acción anterior — no hay ningún objeto que
represente la acción ejecutada.

## 🟡 Intermedio 02 — Aplicar Command

```java
package com.biblioteca;

public interface AccionDePuesto {
    void ejecutar();
    void deshacer();
}
```

```java
package com.biblioteca;

public class AccionOcuparPuesto implements AccionDePuesto {
    private PuestoDeLectura puesto;
    private String usuario;
    private String usuarioAnterior;

    public AccionOcuparPuesto(PuestoDeLectura puesto, String usuario) {
        this.puesto = puesto;
        this.usuario = usuario;
    }

    public void ejecutar() {
        usuarioAnterior = puesto.getUsuarioAsignado();
        puesto.ocupar(usuario);
    }

    public void deshacer() {
        if (usuarioAnterior == null) {
            puesto.liberar();
        } else {
            puesto.ocupar(usuarioAnterior);
        }
    }
}
```

```java
package com.biblioteca;

public class AccionLiberarPuesto implements AccionDePuesto {
    private PuestoDeLectura puesto;
    private String usuarioAnterior;

    public AccionLiberarPuesto(PuestoDeLectura puesto) {
        this.puesto = puesto;
    }

    public void ejecutar() {
        usuarioAnterior = puesto.getUsuarioAsignado();
        puesto.liberar();
    }

    public void deshacer() {
        puesto.ocupar(usuarioAnterior);
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class RegistroDeAcciones {
    private List<AccionDePuesto> acciones = new ArrayList<>();

    public void ejecutar(AccionDePuesto accion) {
        accion.ejecutar();
        acciones.add(accion);
    }

    public void deshacerUltima() {
        if (acciones.isEmpty()) {
            return;
        }
        AccionDePuesto ultima = acciones.remove(acciones.size() - 1);
        ultima.deshacer();
    }
}
```

```java
package com.biblioteca;

public class PuestoDeLectura {
    private String numero;
    private String usuarioAsignado;

    public PuestoDeLectura(String numero) {
        this.numero = numero;
    }

    public void ocupar(String usuario) {
        this.usuarioAsignado = usuario;
        System.out.println("Puesto " + numero + " ocupado por " + usuario);
    }

    public void liberar() {
        System.out.println("Puesto " + numero + " liberado (estaba " + usuarioAsignado + ")");
        this.usuarioAsignado = null;
    }

    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        PuestoDeLectura puesto = new PuestoDeLectura("12");
        RegistroDeAcciones registro = new RegistroDeAcciones();

        registro.ejecutar(new AccionOcuparPuesto(puesto, "Ana Torres"));
        System.out.println("ocupado por=" + puesto.getUsuarioAsignado());

        registro.deshacerUltima();
        System.out.println("ocupado por=" + puesto.getUsuarioAsignado());
    }
}
```

Salida real:

```text
Puesto 12 ocupado por Ana Torres
ocupado por=Ana Torres
Puesto 12 liberado (estaba Ana Torres)
ocupado por=null
```

## 🟢 Básico 03 — Identificar violación de Iterator

`Demo` accede directamente a `coleccion.getFichas()[i]`, acoplado a que `ColeccionDeFichas` use,
específicamente, un arreglo. Si la representación interna cambiara, `Demo` también tendría que
cambiar, porque depende de esa estructura.

## 🟡 Intermedio 03 — Aplicar Iterator

```java
package com.biblioteca;

public interface IteradorDeFichas {
    boolean haySiguiente();
    String siguiente();
}
```

```java
package com.biblioteca;

public class ColeccionDeFichasIterador implements IteradorDeFichas {
    private String[] fichas;
    private int cantidad;
    private int posicionActual;

    public ColeccionDeFichasIterador(String[] fichas, int cantidad) {
        this.fichas = fichas;
        this.cantidad = cantidad;
        this.posicionActual = 0;
    }

    public boolean haySiguiente() {
        return posicionActual < cantidad;
    }

    public String siguiente() {
        String ficha = fichas[posicionActual];
        posicionActual = posicionActual + 1;
        return ficha;
    }
}
```

```java
package com.biblioteca;

public class ColeccionDeFichas {
    private String[] fichas;
    private int cantidad;

    public ColeccionDeFichas(int capacidad) {
        this.fichas = new String[capacidad];
        this.cantidad = 0;
    }

    public void agregar(String ficha) {
        fichas[cantidad] = ficha;
        cantidad = cantidad + 1;
    }

    public IteradorDeFichas crearIterador() {
        return new ColeccionDeFichasIterador(fichas, cantidad);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ColeccionDeFichas coleccion = new ColeccionDeFichas(3);
        coleccion.agregar("Rayuela");
        coleccion.agregar("El Aleph");
        coleccion.agregar("Ficciones");

        IteradorDeFichas iterador = coleccion.crearIterador();
        while (iterador.haySiguiente()) {
            System.out.println(iterador.siguiente());
        }
    }
}
```

Salida real:

```text
Rayuela
El Aleph
Ficciones
```

## 🟢 Básico 04 — Identificar violación de Mediator

`AreaPrestamos` conoce y llama directamente a `AreaReservas` y a `AreaMultas`. Agregar un área nueva
exigiría modificar `AreaPrestamos` (el constructor y el método que coordina la devolución), en vez de
agregar el área nueva sin tocar las existentes.

## 🟡 Intermedio 04 — Aplicar Mediator

```java
package com.biblioteca;

public class MediadorDeBiblioteca {
    private AreaReservas areaReservas;
    private AreaMultas areaMultas;

    public void registrarReservas(AreaReservas areaReservas) {
        this.areaReservas = areaReservas;
    }

    public void registrarMultas(AreaMultas areaMultas) {
        this.areaMultas = areaMultas;
    }

    public void coordinarDevolucion(String ejemplar) {
        areaReservas.recibirAvisoDeDevolucion(ejemplar);
        areaMultas.recibirAvisoDeDevolucion(ejemplar);
    }
}
```

```java
package com.biblioteca;

public class AreaReservas {
    public void recibirAvisoDeDevolucion(String ejemplar) {
        System.out.println("Reservas: notificando al proximo interesado en " + ejemplar);
    }
}
```

```java
package com.biblioteca;

public class AreaMultas {
    public void recibirAvisoDeDevolucion(String ejemplar) {
        System.out.println("Multas: verificando atraso de " + ejemplar);
    }
}
```

```java
package com.biblioteca;

public class AreaPrestamos {
    private MediadorDeBiblioteca mediador;

    public AreaPrestamos(MediadorDeBiblioteca mediador) {
        this.mediador = mediador;
    }

    public void registrarDevolucion(String ejemplar) {
        System.out.println("Prestamos: registrando devolucion de " + ejemplar);
        mediador.coordinarDevolucion(ejemplar);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        MediadorDeBiblioteca mediador = new MediadorDeBiblioteca();
        AreaReservas areaReservas = new AreaReservas();
        AreaMultas areaMultas = new AreaMultas();
        mediador.registrarReservas(areaReservas);
        mediador.registrarMultas(areaMultas);

        AreaPrestamos areaPrestamos = new AreaPrestamos(mediador);
        areaPrestamos.registrarDevolucion("Cien anios de soledad");
    }
}
```

Salida real:

```text
Prestamos: registrando devolucion de Cien anios de soledad
Reservas: notificando al proximo interesado en Cien anios de soledad
Multas: verificando atraso de Cien anios de soledad
```

## 🟢 Básico 05 — Identificar violación de Memento

`escribir()` sobrescribe la descripción sin dejar ningún registro anterior. No existe ninguna forma de
volver a la descripción anterior una vez sobrescrita.

## 🟡 Intermedio 05 — Aplicar Memento

```java
package com.biblioteca;

public class InstanteDeFicha {
    private String descripcion;

    InstanteDeFicha(String descripcion) {
        this.descripcion = descripcion;
    }

    String getDescripcion() {
        return descripcion;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class HistorialDeEdiciones {
    private List<InstanteDeFicha> instantes = new ArrayList<>();

    public void guardar(InstanteDeFicha instante) {
        instantes.add(instante);
    }

    public InstanteDeFicha obtenerUltimo() {
        return instantes.remove(instantes.size() - 1);
    }
}
```

```java
package com.biblioteca;

public class BorradorDeFicha {
    private String descripcion;

    public void escribir(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public InstanteDeFicha guardarInstante() {
        return new InstanteDeFicha(descripcion);
    }

    public void restaurar(InstanteDeFicha instante) {
        this.descripcion = instante.getDescripcion();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        BorradorDeFicha borrador = new BorradorDeFicha();
        HistorialDeEdiciones historial = new HistorialDeEdiciones();

        borrador.escribir("Novela de realismo magico");
        historial.guardar(borrador.guardarInstante());
        System.out.println(borrador.getDescripcion());

        borrador.escribir("Novela de ciencia ficcion");
        System.out.println(borrador.getDescripcion());

        borrador.restaurar(historial.obtenerUltimo());
        System.out.println(borrador.getDescripcion());
    }
}
```

Salida real:

```text
Novela de realismo magico
Novela de ciencia ficcion
Novela de realismo magico
```

## 🟢 Básico 06 — Identificar violación de Observer

`DisponibilidadDeEjemplar.notificarDisponible()` llama directamente a dos interesados
(`System.out.println` simulando a la lista de espera y al bibliotecario) dentro de su propio cuerpo.
Agregar un tercer interesado exigiría modificar ese mismo método, en vez de registrarlo desde afuera.

## 🟡 Intermedio 06 — Aplicar Observer

```java
package com.biblioteca;

public interface ObservadorDeDisponibilidad {
    void notificar(String titulo);
}
```

```java
package com.biblioteca;

public class ListaDeEspera implements ObservadorDeDisponibilidad {
    public void notificar(String titulo) {
        System.out.println("Lista de espera: " + titulo + " ya esta disponible");
    }
}
```

```java
package com.biblioteca;

public class AvisoBibliotecario implements ObservadorDeDisponibilidad {
    public void notificar(String titulo) {
        System.out.println("Bibliotecario: preparar " + titulo + " para el proximo retiro");
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class DisponibilidadDeEjemplar {
    private List<ObservadorDeDisponibilidad> observadores = new ArrayList<>();

    public void agregarObservador(ObservadorDeDisponibilidad observador) {
        observadores.add(observador);
    }

    public void notificarDisponible(String titulo) {
        for (ObservadorDeDisponibilidad observador : observadores) {
            observador.notificar(titulo);
        }
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        DisponibilidadDeEjemplar disponibilidad = new DisponibilidadDeEjemplar();
        disponibilidad.agregarObservador(new ListaDeEspera());
        disponibilidad.agregarObservador(new AvisoBibliotecario());
        disponibilidad.notificarDisponible("Cien anios de soledad");
    }
}
```

Salida real:

```text
Lista de espera: Cien anios de soledad ya esta disponible
Bibliotecario: preparar Cien anios de soledad para el proximo retiro
```

## 🟢 Básico 07 — Identificar violación de State

Hay que revisar los tres métodos (`prestar()`, `devolver()`, `enviarAReparacion()`), porque cada uno
repite su propio condicional sobre el campo `estado`. Un estado nuevo exige revisar y modificar el
condicional repetido en cada método afectado.

## 🟡 Intermedio 07 — Aplicar State

```java
package com.biblioteca;

public interface EstadoDeItem {
    String prestar(ItemDeCatalogo item);
    String devolver(ItemDeCatalogo item);
    String enviarAReparacion(ItemDeCatalogo item);
    String nombre();
}
```

```java
package com.biblioteca;

public class Disponible implements EstadoDeItem {
    public String prestar(ItemDeCatalogo item) {
        item.setEstado(new Prestado());
        return "Prestamo registrado, pasa a PRESTADO";
    }

    public String devolver(ItemDeCatalogo item) {
        return "No se puede devolver: el item no esta prestado";
    }

    public String enviarAReparacion(ItemDeCatalogo item) {
        item.setEstado(new EnReparacion());
        return "Enviado a reparacion";
    }

    public String nombre() {
        return "DISPONIBLE";
    }
}
```

```java
package com.biblioteca;

public class Prestado implements EstadoDeItem {
    public String prestar(ItemDeCatalogo item) {
        return "No se puede prestar: el item no esta disponible";
    }

    public String devolver(ItemDeCatalogo item) {
        item.setEstado(new Disponible());
        return "Devolucion registrada, pasa a DISPONIBLE";
    }

    public String enviarAReparacion(ItemDeCatalogo item) {
        return "No se puede enviar a reparacion: el item no esta disponible";
    }

    public String nombre() {
        return "PRESTADO";
    }
}
```

```java
package com.biblioteca;

public class EnReparacion implements EstadoDeItem {
    public String prestar(ItemDeCatalogo item) {
        return "No se puede prestar: el item no esta disponible";
    }

    public String devolver(ItemDeCatalogo item) {
        return "No se puede devolver: el item no esta prestado";
    }

    public String enviarAReparacion(ItemDeCatalogo item) {
        return "No se puede enviar a reparacion: el item no esta disponible";
    }

    public String nombre() {
        return "EN_REPARACION";
    }
}
```

```java
package com.biblioteca;

public class ItemDeCatalogo {
    private EstadoDeItem estado = new Disponible();

    public String prestar() {
        return estado.prestar(this);
    }

    public String devolver() {
        return estado.devolver(this);
    }

    public String enviarAReparacion() {
        return estado.enviarAReparacion(this);
    }

    public void setEstado(EstadoDeItem estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado.nombre();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ItemDeCatalogo item = new ItemDeCatalogo();
        System.out.println(item.devolver());
        System.out.println(item.prestar());
        System.out.println(item.devolver());
        System.out.println("estado final=" + item.getEstado());
    }
}
```

Salida real:

```text
No se puede devolver: el item no esta prestado
Prestamo registrado, pasa a PRESTADO
Devolucion registrada, pasa a DISPONIBLE
estado final=DISPONIBLE
```

## 🟢 Básico 08 — Identificar violación de Strategy

Hay que modificar `SolicitudDeSala.calcularCosto()`, agregando una rama más `else if
(horario.equals("FERIADO"))`. Cualquier horario nuevo repite este mismo patrón: el condicional crece
sin límite.

## 🟡 Intermedio 08 — Aplicar Strategy

```java
package com.biblioteca;

public interface EstrategiaDeCosto {
    double calcular();
}
```

```java
package com.biblioteca;

public class CostoDiurno implements EstrategiaDeCosto {
    public double calcular() {
        return 500.0;
    }
}
```

```java
package com.biblioteca;

public class CostoNocturno implements EstrategiaDeCosto {
    public double calcular() {
        return 700.0;
    }
}
```

```java
package com.biblioteca;

public class CostoFinDeSemana implements EstrategiaDeCosto {
    public double calcular() {
        return 900.0;
    }
}
```

```java
package com.biblioteca;

public class SolicitudDeSala {
    private EstrategiaDeCosto estrategiaDeCosto;

    public SolicitudDeSala(EstrategiaDeCosto estrategiaDeCosto) {
        this.estrategiaDeCosto = estrategiaDeCosto;
    }

    public double calcularCosto() {
        return estrategiaDeCosto.calcular();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        SolicitudDeSala solicitudDiurna = new SolicitudDeSala(new CostoDiurno());
        SolicitudDeSala solicitudNocturna = new SolicitudDeSala(new CostoNocturno());
        System.out.println(solicitudDiurna.calcularCosto());
        System.out.println(solicitudNocturna.calcularCosto());
    }
}
```

Salida real:

```text
500.0
700.0
```

## 🟢 Básico 09 — Identificar violación de Template Method

A `FichaDeRevista` le falta la línea `=== Ficha de Revista ===`: al copiarla y pegarla desde
`FichaDeLibro`, ese paso del esqueleto se omitió por error. Nada en el lenguaje lo impidió, porque cada
clase repite el esqueleto completo por su cuenta, sin ninguna fuente única de verdad sobre los cuatro
pasos.

## 🟡 Intermedio 09 — Aplicar Template Method

```java
package com.biblioteca;

public abstract class GeneradorDeFicha {
    public final String generar(String titulo) {
        StringBuilder ficha = new StringBuilder();
        ficha.append("Catalogando ").append(titulo).append("\n");
        String datoPropio = obtenerDatoPropio();
        ficha.append("=== ").append(tipoDeFicha()).append(" ===\n");
        ficha.append("Titulo: ").append(titulo).append("\n");
        ficha.append(datoPropio);
        return ficha.toString();
    }

    protected abstract String tipoDeFicha();

    protected abstract String obtenerDatoPropio();
}
```

```java
package com.biblioteca;

public class FichaDeLibro extends GeneradorDeFicha {
    protected String tipoDeFicha() {
        return "Ficha de Libro";
    }

    protected String obtenerDatoPropio() {
        return "Genero: Novela";
    }
}
```

```java
package com.biblioteca;

public class FichaDeRevista extends GeneradorDeFicha {
    protected String tipoDeFicha() {
        return "Ficha de Revista";
    }

    protected String obtenerDatoPropio() {
        return "Periodicidad: Mensual";
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        System.out.println(new FichaDeLibro().generar("Rayuela"));
        System.out.println("---");
        System.out.println(new FichaDeRevista().generar("National Geographic"));
    }
}
```

Salida real:

```text
Catalogando Rayuela
=== Ficha de Libro ===
Titulo: Rayuela
Genero: Novela
---
Catalogando National Geographic
=== Ficha de Revista ===
Titulo: National Geographic
Periodicidad: Mensual
```

## 🔴 Avanzado 01 — Corregir un diseño con dos patrones ausentes

```java
package com.biblioteca;

public interface ManejadorDeSolicitud {
    String aprobar(String tituloEjemplar, String excepcionalidad);
}
```

```java
package com.biblioteca;

public class ModuloInventario {
    public void reservar(String tituloEjemplar) {
        System.out.println("Inventario: reservando " + tituloEjemplar);
    }
}
```

```java
package com.biblioteca;

public class ModuloAvisos {
    public void notificarAprobacion(String tituloEjemplar) {
        System.out.println("Avisos: notificando aprobacion de " + tituloEjemplar);
    }
}
```

```java
package com.biblioteca;

public class MediadorDeSolicitudes {
    private ModuloInventario moduloInventario = new ModuloInventario();
    private ModuloAvisos moduloAvisos = new ModuloAvisos();

    public void coordinarAprobacion(String tituloEjemplar) {
        moduloInventario.reservar(tituloEjemplar);
        moduloAvisos.notificarAprobacion(tituloEjemplar);
    }
}
```

```java
package com.biblioteca;

public class AprobadorNivel1 implements ManejadorDeSolicitud {
    private ManejadorDeSolicitud siguiente;
    private MediadorDeSolicitudes mediador;

    public AprobadorNivel1(ManejadorDeSolicitud siguiente, MediadorDeSolicitudes mediador) {
        this.siguiente = siguiente;
        this.mediador = mediador;
    }

    public String aprobar(String tituloEjemplar, String excepcionalidad) {
        if (excepcionalidad.equals("BAJA")) {
            mediador.coordinarAprobacion(tituloEjemplar);
            return "Aprobado nivel 1: " + tituloEjemplar;
        }
        return siguiente.aprobar(tituloEjemplar, excepcionalidad);
    }
}
```

```java
package com.biblioteca;

public class AprobadorNivel2 implements ManejadorDeSolicitud {
    private MediadorDeSolicitudes mediador;

    public AprobadorNivel2(MediadorDeSolicitudes mediador) {
        this.mediador = mediador;
    }

    public String aprobar(String tituloEjemplar, String excepcionalidad) {
        if (excepcionalidad.equals("ALTA")) {
            mediador.coordinarAprobacion(tituloEjemplar);
            return "Aprobado nivel 2: " + tituloEjemplar;
        }
        throw new IllegalArgumentException("Ningun nivel pudo aprobar: " + excepcionalidad);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        MediadorDeSolicitudes mediador = new MediadorDeSolicitudes();
        ManejadorDeSolicitud nivel2 = new AprobadorNivel2(mediador);
        ManejadorDeSolicitud nivel1 = new AprobadorNivel1(nivel2, mediador);

        System.out.println(nivel1.aprobar("Manuscrito comun", "BAJA"));
        System.out.println(nivel1.aprobar("Manuscrito unico", "ALTA"));
    }
}
```

Salida real:

```text
Inventario: reservando Manuscrito comun
Avisos: notificando aprobacion de Manuscrito comun
Aprobado nivel 1: Manuscrito comun
Inventario: reservando Manuscrito unico
Avisos: notificando aprobacion de Manuscrito unico
Aprobado nivel 2: Manuscrito unico
```

## 🔴 Avanzado 02 — Corregir un diseño con dos patrones ausentes

```java
package com.biblioteca;

public interface EstrategiaDeRetiro {
    String resolver(String codigo);
}
```

```java
package com.biblioteca;

public class RetiroEnMostrador implements EstrategiaDeRetiro {
    public String resolver(String codigo) {
        return "Retiro de " + codigo + " en mostrador";
    }
}
```

```java
package com.biblioteca;

public class RetiroPorCasillero implements EstrategiaDeRetiro {
    public String resolver(String codigo) {
        return "Retiro de " + codigo + " en casillero";
    }
}
```

```java
package com.biblioteca;

public interface ObservadorDeVencimiento {
    void notificar(String codigo);
}
```

```java
package com.biblioteca;

public class AlertaDeVencimiento implements ObservadorDeVencimiento {
    public void notificar(String codigo) {
        System.out.println("Alerta: el prestamo de " + codigo + " vence pronto");
    }
}
```

```java
package com.biblioteca;

public class PanelDeAlertas implements ObservadorDeVencimiento {
    public void notificar(String codigo) {
        System.out.println("Panel: mostrar aviso de vencimiento de " + codigo);
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class SistemaDePrestamos {
    private List<ObservadorDeVencimiento> observadores = new ArrayList<>();

    public void agregarObservador(ObservadorDeVencimiento observador) {
        observadores.add(observador);
    }

    public String resolverRetiro(EstrategiaDeRetiro estrategiaDeRetiro, String codigo) {
        return estrategiaDeRetiro.resolver(codigo);
    }

    public void avisarVencimientoProximo(String codigo) {
        for (ObservadorDeVencimiento observador : observadores) {
            observador.notificar(codigo);
        }
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        SistemaDePrestamos sistema = new SistemaDePrestamos();
        sistema.agregarObservador(new AlertaDeVencimiento());
        sistema.agregarObservador(new PanelDeAlertas());

        System.out.println(sistema.resolverRetiro(new RetiroEnMostrador(), "L001"));
        System.out.println(sistema.resolverRetiro(new RetiroPorCasillero(), "L002"));

        sistema.avisarVencimientoProximo("L001");
    }
}
```

Salida real:

```text
Retiro de L001 en mostrador
Retiro de L002 en casillero
Alerta: el prestamo de L001 vence pronto
Panel: mostrar aviso de vencimiento de L001
```

## 🏆 Desafío 01 — Elegir y aplicar el patrón adecuado a un caso nuevo

Una solución posible: el problema (guardar y restaurar el estado de un objeto en distintos momentos,
sin romper su encapsulamiento) encaja mejor con **Memento**, no con los otros tres — no hay una
solicitud que deba resolverse por niveles (descarta Chain of Responsibility), no hay ninguna colección
que recorrer (descarta Iterator), y no hay varios objetos que deban comunicarse entre sí (descarta
Mediator).

```java
package com.biblioteca;

public class InstanteDeResena {
    private String texto;

    InstanteDeResena(String texto) {
        this.texto = texto;
    }

    String getTexto() {
        return texto;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class HistorialDeVersiones {
    private List<InstanteDeResena> instantes = new ArrayList<>();

    public void guardar(InstanteDeResena instante) {
        instantes.add(instante);
    }

    public InstanteDeResena obtenerUltimo() {
        return instantes.remove(instantes.size() - 1);
    }
}
```

```java
package com.biblioteca;

public class ResenaDeLibro {
    private String texto;

    public void escribir(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public InstanteDeResena guardarInstante() {
        return new InstanteDeResena(texto);
    }

    public void restaurar(InstanteDeResena instante) {
        this.texto = instante.getTexto();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ResenaDeLibro resena = new ResenaDeLibro();
        HistorialDeVersiones historial = new HistorialDeVersiones();

        resena.escribir("Una obra maestra del realismo magico");
        historial.guardar(resena.guardarInstante());
        System.out.println(resena.getTexto());

        resena.escribir("Un libro dificil de seguir");
        System.out.println(resena.getTexto());

        resena.restaurar(historial.obtenerUltimo());
        System.out.println(resena.getTexto());
    }
}
```

Salida real, probada escribiendo un primer texto, guardando un instante, escribiendo un segundo texto
por error, y restaurando el instante guardado:

```text
Una obra maestra del realismo magico
Un libro dificil de seguir
Una obra maestra del realismo magico
```
## 🏆 Desafío 02 — Elegir y aplicar el patrón adecuado a un caso nuevo

Una solución posible: el problema (una reserva cuyo comportamiento válido depende de su estado interno,
sin condicionales dispersos) encaja mejor con **State**, no con los otros cuatro — no hay un algoritmo
intercambiable elegido por el cliente (descarta Strategy), no hay una lista de interesados que deba
notificarse (descarta Observer), no se necesita encapsular una acción para deshacerla o registrarla
(descarta Command), y no hay un esqueleto de pasos compartido entre variantes (descarta Template
Method).

```java
package com.biblioteca;

public interface EstadoDeReserva {
    String confirmar(ReservaDeSala reserva);
    String cancelar(ReservaDeSala reserva);
    String marcarVencida(ReservaDeSala reserva);
    String nombre();
}
```

```java
package com.biblioteca;

public class Pendiente implements EstadoDeReserva {
    public String confirmar(ReservaDeSala reserva) {
        reserva.setEstado(new Confirmada());
        return "Reserva confirmada";
    }

    public String cancelar(ReservaDeSala reserva) {
        reserva.setEstado(new Cancelada());
        return "Reserva cancelada";
    }

    public String marcarVencida(ReservaDeSala reserva) {
        reserva.setEstado(new Vencida());
        return "Reserva vencida por falta de confirmacion";
    }

    public String nombre() {
        return "PENDIENTE";
    }
}
```

```java
package com.biblioteca;

public class Confirmada implements EstadoDeReserva {
    public String confirmar(ReservaDeSala reserva) {
        return "No se puede confirmar: la reserva ya esta confirmada";
    }

    public String cancelar(ReservaDeSala reserva) {
        reserva.setEstado(new Cancelada());
        return "Reserva cancelada";
    }

    public String marcarVencida(ReservaDeSala reserva) {
        return "No se puede vencer: la reserva ya esta confirmada";
    }

    public String nombre() {
        return "CONFIRMADA";
    }
}
```

```java
package com.biblioteca;

public class Vencida implements EstadoDeReserva {
    public String confirmar(ReservaDeSala reserva) {
        return "No se puede confirmar: la reserva esta vencida";
    }

    public String cancelar(ReservaDeSala reserva) {
        return "No se puede cancelar: la reserva esta vencida";
    }

    public String marcarVencida(ReservaDeSala reserva) {
        return "No se puede vencer: la reserva ya esta vencida";
    }

    public String nombre() {
        return "VENCIDA";
    }
}
```

```java
package com.biblioteca;

public class Cancelada implements EstadoDeReserva {
    public String confirmar(ReservaDeSala reserva) {
        return "No se puede confirmar: la reserva esta cancelada";
    }

    public String cancelar(ReservaDeSala reserva) {
        return "No se puede cancelar: la reserva ya esta cancelada";
    }

    public String marcarVencida(ReservaDeSala reserva) {
        return "No se puede vencer: la reserva esta cancelada";
    }

    public String nombre() {
        return "CANCELADA";
    }
}
```

```java
package com.biblioteca;

public class ReservaDeSala {
    private EstadoDeReserva estado = new Pendiente();

    public String confirmar() {
        return estado.confirmar(this);
    }

    public String cancelar() {
        return estado.cancelar(this);
    }

    public String marcarVencida() {
        return estado.marcarVencida(this);
    }

    public void setEstado(EstadoDeReserva estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado.nombre();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ReservaDeSala reserva = new ReservaDeSala();
        System.out.println(reserva.confirmar());
        System.out.println(reserva.marcarVencida());
        System.out.println(reserva.confirmar());
        System.out.println(reserva.cancelar());
        System.out.println("estado final=" + reserva.getEstado());
    }
}
```

Salida real, probada confirmando una reserva pendiente, intentando vencerla y volverla a confirmar (ambos
rechazados), y cancelándola:

```text
Reserva confirmada
No se puede vencer: la reserva ya esta confirmada
No se puede confirmar: la reserva ya esta confirmada
Reserva cancelada
estado final=CANCELADA
```
