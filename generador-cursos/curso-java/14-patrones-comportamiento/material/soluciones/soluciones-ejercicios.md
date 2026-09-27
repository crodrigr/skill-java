# 🔑 Soluciones de los ejercicios — Módulo 14

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar violación de Strategy

Hay que modificar `SolicitudDeSala.calcularCosto()`, agregando una rama más `else if
(horario.equals("FERIADO"))`. Cualquier horario nuevo repite este mismo patrón: el condicional crece
sin límite.

## 🟡 Intermedio 01 — Aplicar Strategy

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

## 🟢 Básico 02 — Identificar violación de Observer

`DisponibilidadDeEjemplar.notificarDisponible()` llama directamente a dos interesados
(`System.out.println` simulando a la lista de espera y al bibliotecario) dentro de su propio cuerpo.
Agregar un tercer interesado exigiría modificar ese mismo método, en vez de registrarlo desde afuera.

## 🟡 Intermedio 02 — Aplicar Observer

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

## 🟢 Básico 03 — Identificar violación de Command

No existe ninguna forma uniforme de deshacer la última acción: el bibliotecario solo podría llamar
manualmente a `liberar()`, y solo si recuerda cuál fue la acción anterior — no hay ningún objeto que
represente la acción ejecutada.

## 🟡 Intermedio 03 — Aplicar Command

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

## 🟢 Básico 04 — Identificar violación de State

Hay que revisar los tres métodos (`prestar()`, `devolver()`, `enviarAReparacion()`), porque cada uno
repite su propio condicional sobre el campo `estado`. Un estado nuevo exige revisar y modificar el
condicional repetido en cada método afectado.

## 🟡 Intermedio 04 — Aplicar State

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

## 🟢 Básico 05 — Identificar violación de Template Method

A `FichaDeRevista` le falta la línea `=== Ficha de Revista ===`: al copiarla y pegarla desde
`FichaDeLibro`, ese paso del esqueleto se omitió por error. Nada en el lenguaje lo impidió, porque cada
clase repite el esqueleto completo por su cuenta, sin ninguna fuente única de verdad sobre los cuatro
pasos.

## 🟡 Intermedio 05 — Aplicar Template Method

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
