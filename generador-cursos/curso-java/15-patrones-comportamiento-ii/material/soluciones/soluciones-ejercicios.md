# 🔑 Soluciones de los ejercicios — Módulo 15

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

## 🟢 Básico 02 — Identificar violación de Iterator

`Demo` accede directamente a `coleccion.getFichas()[i]`, acoplado a que `ColeccionDeFichas` use,
específicamente, un arreglo. Si la representación interna cambiara, `Demo` también tendría que
cambiar, porque depende de esa estructura.

## 🟡 Intermedio 02 — Aplicar Iterator

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

## 🟢 Básico 03 — Identificar violación de Mediator

`AreaPrestamos` conoce y llama directamente a `AreaReservas` y a `AreaMultas`. Agregar un área nueva
exigiría modificar `AreaPrestamos` (el constructor y el método que coordina la devolución), en vez de
agregar el área nueva sin tocar las existentes.

## 🟡 Intermedio 03 — Aplicar Mediator

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

## 🟢 Básico 04 — Identificar violación de Memento

`escribir()` sobrescribe la descripción sin dejar ningún registro anterior. No existe ninguna forma de
volver a la descripción anterior una vez sobrescrita.

## 🟡 Intermedio 04 — Aplicar Memento

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
