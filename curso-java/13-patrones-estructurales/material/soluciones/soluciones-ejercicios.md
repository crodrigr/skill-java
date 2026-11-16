# 🔑 Soluciones de los ejercicios — Módulo 13

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar violación de Adapter

Hay que modificar `SistemaPrestamos.java`: cada método que consulta el catálogo (`prestarMaterial`,
`reservarMaterial`) arma a mano el texto `"CODIGO=...;ACCION=..."` que espera `CatalogoExterno`. Si el
catálogo externo cambiara su formato, habría que modificar los dos métodos, y cualquier método nuevo que
se agregue repetiría la misma construcción.

## 🟡 Intermedio 01 — Aplicar Adapter

```java
package com.biblioteca;

public class CatalogoExterno {
    public String consultarStock(String datos) {
        if (datos.contains("ACCION=PRESTAMO")) {
            return "DISPONIBLE";
        }
        return "RESERVADO";
    }
}
```

```java
package com.biblioteca;

public interface ICatalogo {
    String consultar(String codigo, String accion);
}
```

```java
package com.biblioteca;

public class CatalogoAdapter implements ICatalogo {
    private CatalogoExterno catalogoExterno = new CatalogoExterno();

    public String consultar(String codigo, String accion) {
        String datos = "CODIGO=" + codigo + ";ACCION=" + accion;
        return catalogoExterno.consultarStock(datos);
    }
}
```

```java
package com.biblioteca;

public class SistemaPrestamos {
    private ICatalogo catalogo = new CatalogoAdapter();

    public String prestarMaterial(String codigo) {
        return catalogo.consultar(codigo, "PRESTAMO");
    }

    public String reservarMaterial(String codigo) {
        return catalogo.consultar(codigo, "RESERVA");
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        SistemaPrestamos sistema = new SistemaPrestamos();
        System.out.println(sistema.prestarMaterial("L001"));
        System.out.println(sistema.reservarMaterial("L002"));
    }
}
```

Salida real:

```text
DISPONIBLE
RESERVADO
```

## 🟢 Básico 02 — Identificar violación de Bridge

Hacen falta 2 clases nuevas: `ReporteDiarioPdf` y `ReporteMensualPdf` — una por cada período existente,
porque el período y el formato están mezclados en la misma jerarquía de herencia. Si se agregara un
período nuevo en vez de un formato, el costo sería simétrico: una clase nueva por cada formato existente.

## 🟡 Intermedio 02 — Aplicar Bridge

```java
package com.biblioteca;

public interface FormatoSalida {
    String formatear(String contenido);
}
```

```java
package com.biblioteca;

public class FormatoTexto implements FormatoSalida {
    public String formatear(String contenido) {
        return "REPORTE (texto): " + contenido;
    }
}
```

```java
package com.biblioteca;

public class FormatoHtml implements FormatoSalida {
    public String formatear(String contenido) {
        return "<html><body>" + contenido + "</body></html>";
    }
}
```

```java
package com.biblioteca;

public abstract class Reporte {
    protected FormatoSalida formato;

    public Reporte(FormatoSalida formato) {
        this.formato = formato;
    }

    public abstract String generar();
}
```

```java
package com.biblioteca;

public class ReporteDiario extends Reporte {
    public ReporteDiario(FormatoSalida formato) {
        super(formato);
    }

    public String generar() {
        return formato.formatear("12 prestamos hoy");
    }
}
```

```java
package com.biblioteca;

public class ReporteMensual extends Reporte {
    public ReporteMensual(FormatoSalida formato) {
        super(formato);
    }

    public String generar() {
        return formato.formatear("340 prestamos este mes");
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        System.out.println(new ReporteDiario(new FormatoTexto()).generar());
        System.out.println(new ReporteDiario(new FormatoHtml()).generar());
        System.out.println(new ReporteMensual(new FormatoTexto()).generar());
        System.out.println(new ReporteMensual(new FormatoHtml()).generar());
    }
}
```

Salida real:

```text
REPORTE (texto): 12 prestamos hoy
<html><body>12 prestamos hoy</body></html>
REPORTE (texto): 340 prestamos este mes
<html><body>340 prestamos este mes</body></html>
```

## 🟢 Básico 03 — Identificar violación de Composite

Hay que modificar `Estanteria.contarLibros()`, agregando una rama más `else if (elemento instanceof
Revista)`. Cualquier otro método futuro que recorra la misma estructura (por ejemplo, uno que liste
títulos) necesitaría el mismo condicional repetido, con una rama por cada tipo de elemento.

## 🟡 Intermedio 03 — Aplicar Composite

```java
package com.biblioteca;

public interface ElementoDeCatalogo {
    int contarLibros();
}
```

```java
package com.biblioteca;

public class Libro implements ElementoDeCatalogo {
    private String titulo;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int contarLibros() {
        return 1;
    }
}
```

```java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Estanteria implements ElementoDeCatalogo {
    private List<ElementoDeCatalogo> contenido = new ArrayList<>();

    public void agregar(ElementoDeCatalogo elemento) {
        contenido.add(elemento);
    }

    public int contarLibros() {
        int total = 0;
        for (ElementoDeCatalogo elemento : contenido) {
            total += elemento.contarLibros();
        }
        return total;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        Estanteria seccionA = new Estanteria();
        seccionA.agregar(new Libro("Cien anios de soledad"));
        seccionA.agregar(new Libro("Rayuela"));

        Estanteria seccionB = new Estanteria();
        seccionB.agregar(new Libro("El Aleph"));

        Estanteria salaGeneral = new Estanteria();
        salaGeneral.agregar(seccionA);
        salaGeneral.agregar(seccionB);
        salaGeneral.agregar(new Libro("Ficciones"));

        System.out.println("libros en sala general=" + salaGeneral.contarLibros());
    }
}
```

Salida real:

```text
libros en sala general=4
```

## 🟢 Básico 04 — Identificar violación de Decorator

Hacen falta 4 clases nuevas para cubrir todas las combinaciones con el extra de envío a domicilio: envío
solo, envío + renovación, envío + seguro, y envío + renovación + seguro. Cada combinación de extras está
fija en una subclase, decidida en tiempo de compilación, así que un extra nuevo multiplica la cantidad de
subclases necesarias para cubrir las combinaciones con los extras existentes.

## 🟡 Intermedio 04 — Aplicar Decorator

```java
package com.biblioteca;

public class Prestamo {
    public double costo() {
        return 0.0;
    }

    public String descripcion() {
        return "Prestamo estandar";
    }
}
```

```java
package com.biblioteca;

public abstract class PrestamoDecorado extends Prestamo {
    protected Prestamo prestamoBase;

    public PrestamoDecorado(Prestamo prestamoBase) {
        this.prestamoBase = prestamoBase;
    }
}
```

```java
package com.biblioteca;

public class ConRenovacion extends PrestamoDecorado {
    public ConRenovacion(Prestamo prestamoBase) {
        super(prestamoBase);
    }

    public double costo() {
        return prestamoBase.costo() + 50.0;
    }

    public String descripcion() {
        return prestamoBase.descripcion() + " + renovacion automatica";
    }
}
```

```java
package com.biblioteca;

public class ConSeguro extends PrestamoDecorado {
    public ConSeguro(Prestamo prestamoBase) {
        super(prestamoBase);
    }

    public double costo() {
        return prestamoBase.costo() + 80.0;
    }

    public String descripcion() {
        return prestamoBase.descripcion() + " + seguro contra daños";
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        System.out.println(new Prestamo().costo());
        System.out.println(new ConRenovacion(new Prestamo()).costo());
        System.out.println(new ConSeguro(new Prestamo()).costo());
        System.out.println(new ConSeguro(new ConRenovacion(new Prestamo())).costo());
    }
}
```

Salida real:

```text
0.0
50.0
80.0
130.0
```

## 🟢 Básico 05 — Identificar violación de Facade

`Demo` conoce y llama directamente a las cuatro clases de subsistema (`VerificadorMulta`,
`ActualizadorInventario`, `NotificadorReserva`, `RegistradorHistorial`). Agregar un quinto paso exigiría
modificar `Demo` (y cualquier otro lugar del código que también procese una devolución de la misma
forma), en vez de modificar un único punto central.

## 🟡 Intermedio 05 — Aplicar Facade

```java
package com.biblioteca;

public class VerificadorMulta {
    public String verificar(String socio) {
        return "Sin multas pendientes para " + socio;
    }
}
```

```java
package com.biblioteca;

public class ActualizadorInventario {
    public String actualizar(String codigo) {
        return "Inventario actualizado: " + codigo + " disponible";
    }
}
```

```java
package com.biblioteca;

public class NotificadorReserva {
    public String notificar(String codigo) {
        return "Reserva notificada para el proximo socio en espera de " + codigo;
    }
}
```

```java
package com.biblioteca;

public class RegistradorHistorial {
    public String registrar(String socio, String codigo) {
        return "Historial actualizado: " + socio + " devolvio " + codigo;
    }
}
```

```java
package com.biblioteca;

public class DevolucionFacade {
    private VerificadorMulta verificadorMulta = new VerificadorMulta();
    private ActualizadorInventario actualizadorInventario = new ActualizadorInventario();
    private NotificadorReserva notificadorReserva = new NotificadorReserva();
    private RegistradorHistorial registradorHistorial = new RegistradorHistorial();

    public void procesarDevolucion(String socio, String codigo) {
        System.out.println(verificadorMulta.verificar(socio));
        System.out.println(actualizadorInventario.actualizar(codigo));
        System.out.println(notificadorReserva.notificar(codigo));
        System.out.println(registradorHistorial.registrar(socio, codigo));
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        DevolucionFacade devolucionFacade = new DevolucionFacade();
        devolucionFacade.procesarDevolucion("Ana", "L001");
    }
}
```

Salida real:

```text
Sin multas pendientes para Ana
Inventario actualizado: L001 disponible
Reserva notificada para el proximo socio en espera de L001
Historial actualizado: Ana devolvio L001
```

## 🟢 Básico 06 — Identificar violación de Flyweight

Existen 10.000 objetos `GeneroLiterario` distintos, uno por ejemplar, aunque todos tengan exactamente los
mismos datos (`"Novela"`, `"800"`). Eso desperdicia memoria repitiendo el mismo estado una y otra vez,
cuando los 10.000 ejemplares del mismo género podrían compartir un único objeto.

## 🟡 Intermedio 06 — Aplicar Flyweight

```java
package com.biblioteca;

public class GeneroLiterario {
    private String nombre;
    private String clasificacionDewey;

    public GeneroLiterario(String nombre, String clasificacionDewey) {
        this.nombre = nombre;
        this.clasificacionDewey = clasificacionDewey;
    }

    public String describir() {
        return nombre + " (Dewey " + clasificacionDewey + ")";
    }
}
```

```java
package com.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class FabricaDeGeneros {
    private static Map<String, GeneroLiterario> cache = new HashMap<>();

    public static GeneroLiterario obtener(String nombre) {
        if (!cache.containsKey(nombre)) {
            cache.put(nombre, new GeneroLiterario(nombre, "800"));
        }
        return cache.get(nombre);
    }
}
```

```java
package com.biblioteca;

public class Ejemplar {
    private String titulo;
    private GeneroLiterario genero;

    public Ejemplar(String titulo, String nombreGenero) {
        this.titulo = titulo;
        this.genero = FabricaDeGeneros.obtener(nombreGenero);
    }

    public String describir() {
        return titulo + " - " + genero.describir();
    }

    public GeneroLiterario getGenero() {
        return genero;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        Ejemplar ejemplar1 = new Ejemplar("Rayuela", "Novela");
        Ejemplar ejemplar2 = new Ejemplar("El Aleph", "Novela");

        System.out.println(ejemplar1.describir());
        System.out.println(ejemplar2.describir());

        boolean mismaInstancia = ejemplar1.getGenero() == ejemplar2.getGenero();
        System.out.println("mismaInstancia=" + mismaInstancia);
    }
}
```

Salida real:

```text
Rayuela - Novela (Dewey 800)
El Aleph - Novela (Dewey 800)
mismaInstancia=true
```

## 🟢 Básico 07 — Identificar violación de Proxy

El contenido completo se carga siempre, en el constructor de `DocumentoDigitalReal`, aunque `Demo` nunca
llegue a pedirlo con `verContenido()`: el mensaje `"Cargando contenido completo de..."` aparece antes que
`"Consultando titulo..."`, aun cuando el programa solo consulta el título.

## 🟡 Intermedio 07 — Aplicar Proxy

```java
package com.biblioteca;

public interface DocumentoDigital {
    String verTitulo();
    String verContenido();
}
```

```java
package com.biblioteca;

public class DocumentoDigitalReal implements DocumentoDigital {
    private String titulo;
    private String contenido;

    public DocumentoDigitalReal(String titulo) {
        this.titulo = titulo;
        System.out.println("Cargando contenido completo de " + titulo + "...");
        this.contenido = "Contenido completo de " + titulo;
    }

    public String verTitulo() {
        return titulo;
    }

    public String verContenido() {
        return contenido;
    }
}
```

```java
package com.biblioteca;

public class DocumentoDigitalProxy implements DocumentoDigital {
    private String titulo;
    private DocumentoDigitalReal real;

    public DocumentoDigitalProxy(String titulo) {
        this.titulo = titulo;
    }

    public String verTitulo() {
        return titulo;
    }

    public String verContenido() {
        if (real == null) {
            real = new DocumentoDigitalReal(titulo);
        }
        return real.verContenido();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        DocumentoDigital documento = new DocumentoDigitalProxy("Tesis de grado");
        System.out.println("Consultando titulo...");
        System.out.println(documento.verTitulo());
    }
}
```

Salida real (el mensaje `"Cargando contenido completo de..."` ya no aparece, porque `Demo` nunca llama a
`verContenido()`):

```text
Consultando titulo...
Tesis de grado
```

## 🔴 Avanzado 01 — Corregir un diseño con dos patrones ausentes

```java
package com.biblioteca;

public class CatalogoExternoDevoluciones {
    public String registrarDevolucionExterna(String datosCrudos) {
        return "OK registrado: " + datosCrudos;
    }
}
```

```java
package com.biblioteca;

public interface IDevolucionExterna {
    String registrar(String codigo, String socio, String fecha);
}
```

```java
package com.biblioteca;

public class DevolucionExternaAdapter implements IDevolucionExterna {
    private CatalogoExternoDevoluciones catalogoExterno = new CatalogoExternoDevoluciones();

    public String registrar(String codigo, String socio, String fecha) {
        String datosCrudos = codigo + "|" + socio + "|" + fecha;
        return catalogoExterno.registrarDevolucionExterna(datosCrudos);
    }
}
```

```java
package com.biblioteca;

public class VerificadorEstadoDevolucion {
    public String verificar(String codigo) {
        return "Estado verificado para " + codigo + ": sin danios reportados";
    }
}
```

```java
package com.biblioteca;

public class ActualizadorStockDevolucion {
    public String actualizar(String codigo) {
        return "Stock actualizado: " + codigo + " disponible nuevamente";
    }
}
```

```java
package com.biblioteca;

public class NotificadorProximoLector {
    public String notificar(String codigo) {
        return "Proximo lector en espera notificado sobre " + codigo;
    }
}
```

```java
package com.biblioteca;

public class ProcesoDevolucionFacade {
    private IDevolucionExterna devolucionExterna = new DevolucionExternaAdapter();
    private VerificadorEstadoDevolucion verificadorEstado = new VerificadorEstadoDevolucion();
    private ActualizadorStockDevolucion actualizadorStock = new ActualizadorStockDevolucion();
    private NotificadorProximoLector notificadorProximoLector = new NotificadorProximoLector();

    public void procesar(String codigo, String socio, String fecha) {
        System.out.println(devolucionExterna.registrar(codigo, socio, fecha));
        System.out.println(verificadorEstado.verificar(codigo));
        System.out.println(actualizadorStock.actualizar(codigo));
        System.out.println(notificadorProximoLector.notificar(codigo));
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ProcesoDevolucionFacade procesoDevolucion = new ProcesoDevolucionFacade();
        procesoDevolucion.procesar("L001", "Ana", "2026-03-01");
        procesoDevolucion.procesar("L002", "Beto", "2026-03-02");
    }
}
```

Salida real:

```text
OK registrado: L001|Ana|2026-03-01
Estado verificado para L001: sin danios reportados
Stock actualizado: L001 disponible nuevamente
Proximo lector en espera notificado sobre L001
OK registrado: L002|Beto|2026-03-02
Estado verificado para L002: sin danios reportados
Stock actualizado: L002 disponible nuevamente
Proximo lector en espera notificado sobre L002
```

## 🏆 Desafío 01 — Elegir y aplicar el patrón adecuado a un caso nuevo

Una solución posible: el problema (controlar el acceso a un recurso costoso de activar, antes de decidir
si vale la pena hacerlo) encaja mejor con **Proxy** (variante de control de acceso), no con los otros
seis — no hay ninguna interfaz incompatible que traducir (descarta Adapter), no hay dos dimensiones de
variación independientes (descarta Bridge), no hay una estructura parte-todo (descarta Composite), no se
combinan responsabilidades opcionales (descarta Decorator), no hay varios subsistemas que orquestar
(descarta Facade), y no hay estado idéntico repetido entre muchos objetos (descarta Flyweight).

```java
package com.biblioteca;

public interface SalaConsulta {
    String ingresar(String socio);
}
```

```java
package com.biblioteca;

public class SalaConsultaReal implements SalaConsulta {
    public SalaConsultaReal() {
        System.out.println("Abriendo la Sala de Consulta de Manuscritos Antiguos...");
    }

    public String ingresar(String socio) {
        return "Bienvenido a la Sala de Consulta, " + socio;
    }
}
```

```java
package com.biblioteca;

import java.util.List;

public class SalaConsultaProxy implements SalaConsulta {
    private List<String> sociosConPermiso;
    private SalaConsultaReal salaReal;

    public SalaConsultaProxy(List<String> sociosConPermiso) {
        this.sociosConPermiso = sociosConPermiso;
    }

    public String ingresar(String socio) {
        if (!sociosConPermiso.contains(socio)) {
            return "Acceso denegado para " + socio + ": no tiene permiso para manuscritos antiguos";
        }
        if (salaReal == null) {
            salaReal = new SalaConsultaReal();
        }
        return salaReal.ingresar(socio);
    }
}
```

```java
package com.biblioteca;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<String> sociosConPermiso = Arrays.asList("Ana");
        SalaConsulta sala = new SalaConsultaProxy(sociosConPermiso);

        System.out.println(sala.ingresar("Carlos"));
        System.out.println(sala.ingresar("Ana"));
    }
}
```

Salida real, probada con un socio sin permiso (Carlos) y uno con permiso (Ana): el control climático de
la sala real solo se activa para Ana, nunca para Carlos.

```text
Acceso denegado para Carlos: no tiene permiso para manuscritos antiguos
Abriendo la Sala de Consulta de Manuscritos Antiguos...
Bienvenido a la Sala de Consulta, Ana
```
