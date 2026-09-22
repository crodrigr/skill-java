# 🔑 Soluciones — Ejercicios Módulo 6

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/06-encapsulamiento.md`).

## 🟢 Básico 01 — Por qué encapsular

`diasPrestamo` es público: nada impide asignarle `-3`, un valor sin sentido de negocio. Encapsulándolo
como `private` con un `set` que valide (por ejemplo, que rechace valores negativos), ese valor no se
podría asignar.

Salida real del programa (sin corregir, tal como se entrega):

```text
El principito: -3 días
```

## 🟡 Intermedio 01 — Encapsular una clase

```java
public class Libro {
    private String tituloLibro;

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public static void main(String[] args) {
        Libro libro = new Libro();
        libro.setTituloLibro("Cien años de soledad");
        System.out.println(libro.getTituloLibro());
    }
}
```

Salida real del programa:

```text
Cien años de soledad
```

## 🟢 Básico 02 — ¿Compila o no?

| Atributo | Mismo paquete | Otro paquete |
|---|---|---|
| `atributoPrivate` | No | No |
| `atributoPorDefecto` | Sí | No |
| `atributoProtected` | Sí | No |
| `atributoPublic` | Sí | Sí |

`atributoPrivate` nunca compila fuera de `Multimodificador`, ni siquiera desde el mismo paquete.

Mensajes reales de los casos que no compilan:

```text
⚠ The value of the field Multimodificador.atributoPrivate is not used Java(570425421) [Ln 4, Col 20]
✖ The field Multimodificador.atributoPrivate is not visible Java(33554503) [Ln 10, Col 16]
```

```text
✖ The field Multimodificador.atributoPorDefecto is not visible Java(33554503) [Ln 7, Col 16]
```

```text
✖ The field Multimodificador.atributoProtected is not visible Java(33554503) [Ln 7, Col 16]
```

## 🟢 Básico 03 — El error de un private

`nombreUsuario` es `private` en `Usuario`; `AccesoPrivateInvalido` es una clase distinta (aunque esté en
el mismo archivo), así que no puede acceder a él directamente. Corrección: agregar
`setNombreUsuario(String nombreUsuario)` a `Usuario` e invocarlo en vez del acceso directo.

## 🟡 Intermedio 03 — Elegir el modificador correcto

```java
public class Membresia {
    private String numeroMembresia;
    int contadorRenovaciones;
    public String nombreUsuario;

    public Membresia(String numeroMembresia, String nombreUsuario) {
        this.numeroMembresia = numeroMembresia;
        this.nombreUsuario = nombreUsuario;
        this.contadorRenovaciones = 0;
    }

    public String getNumeroMembresia() {
        return numeroMembresia;
    }

    public void renovar() {
        contadorRenovaciones = contadorRenovaciones + 1;
    }

    public int getContadorRenovaciones() {
        return contadorRenovaciones;
    }
}
```

Salida real del programa:

```text
Ana Torres - M-2026-001
Renovaciones: 2
```

## 🟢 Básico 04 — get o is

`isDisponible()` es la forma correcta: la convención de Java usa el prefijo `is` (no `get`) para el
método `get` de un atributo `boolean`.

Salida real del programa:

```text
Disponible: true
```

## 🟢 Básico 05 — El set que no cambia nada

`setMontoBase(-5000.0)` no asigna: `-5000.0` es negativo, así que la validación lo rechaza y
`montoBase` conserva `80000.0`.

Salida real del programa:

```text
Monto inválido (-5000.0): se conserva el monto actual (80000.0)
Monto final: 80000.0
```

## 🟡 Intermedio 02 — Agregar validación a un set

```java
public class Usuario {
    private int prestamosActivos;

    public int getPrestamosActivos() {
        return prestamosActivos;
    }

    public void setPrestamosActivos(int prestamosActivos) {
        if (prestamosActivos < 0) {
            System.out.println("Préstamos activos inválido (" + prestamosActivos + "): se conserva el valor actual (" + this.prestamosActivos + ")");
            return;
        }
        this.prestamosActivos = prestamosActivos;
    }

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setPrestamosActivos(2);
        System.out.println("Préstamos activos: " + usuario.getPrestamosActivos());

        usuario.setPrestamosActivos(-1);
        System.out.println("Préstamos activos tras valor inválido: " + usuario.getPrestamosActivos());

        usuario.setPrestamosActivos(0);
        System.out.println("Préstamos activos tras valor límite: " + usuario.getPrestamosActivos());
    }
}
```

Salida real del programa:

```text
Préstamos activos: 2
Préstamos activos inválido (-1): se conserva el valor actual (2)
Préstamos activos tras valor inválido: 2
Préstamos activos tras valor límite: 0
```

## 🔴 Avanzado 01 — Corregir un programa con errores de encapsulamiento

**Ronda 1 (compilación).** `main` intenta acceder a `sala.cupoMaximo` directamente:

```text
✖ The field Sala.cupoMaximo is not visible Java(33554503) [Ln 30, Col 44]
```

Corrección: usar `sala.getCupoMaximo()`.

**Ronda 2 (la salida no coincide).** Con el acceso corregido, `actualizarCupo(-10)` asignaba
`cupoMaximo` directamente, saltándose la validación:

```text
Cupo inicial: 30
Cupo tras actualizarCupo(-10): -10
```

Corrección: que `actualizarCupo` llame a `setCupoMaximo(nuevoCupo)` en vez de asignar directamente.

Programa corregido:

```java
package com.biblioteca;

class Sala {
    private int cupoMaximo;

    public Sala(int cupoMaximo) {
        setCupoMaximo(cupoMaximo);
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        if (cupoMaximo < 0) {
            System.out.println("Cupo inválido (" + cupoMaximo + "): se conserva el cupo actual (" + this.cupoMaximo + ")");
            return;
        }
        this.cupoMaximo = cupoMaximo;
    }

    public void actualizarCupo(int nuevoCupo) {
        setCupoMaximo(nuevoCupo);
    }
}

public class RevisionDeCupos {
    public static void main(String[] args) {
        Sala sala = new Sala(30);
        System.out.println("Cupo inicial: " + sala.getCupoMaximo());

        sala.actualizarCupo(-10);
        System.out.println("Cupo tras actualizarCupo(-10): " + sala.getCupoMaximo());
    }
}
```

```text
Cupo inicial: 30
Cupo inválido (-10): se conserva el cupo actual (30)
Cupo tras actualizarCupo(-10): 30
```

## 🏆 Desafío 01 — Encapsular una clase nueva

```java
package com.biblioteca;

public class Reserva {
    private String nombreUsuario;
    private String tituloLibro;
    private int diasReserva;

    public Reserva(String nombreUsuario, String tituloLibro, int diasReserva) {
        this.nombreUsuario = nombreUsuario;
        this.tituloLibro = tituloLibro;
        setDiasReserva(diasReserva);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public int getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(int diasReserva) {
        if (diasReserva < 1) {
            System.out.println("Días de reserva inválidos (" + diasReserva + "): se conserva el valor actual (" + this.diasReserva + ")");
            return;
        }
        this.diasReserva = diasReserva;
    }

    public void mostrarReserva() {
        System.out.println(nombreUsuario + " reservó \"" + tituloLibro + "\" por " + diasReserva + " días");
    }
}
```

```java
package com.biblioteca;

public class DemoReserva {

    public static void main(String[] args) {
        Reserva reserva1 = new Reserva("Ana Torres", "Cien años de soledad", 7);
        reserva1.mostrarReserva();

        Reserva reserva2 = new Reserva("Carlos Ramírez", "El principito", -3);
        reserva2.mostrarReserva();
    }
}
```

```text
Ana Torres reservó "Cien años de soledad" por 7 días
Días de reserva inválidos (-3): se conserva el valor actual (0)
Carlos Ramírez reservó "El principito" por 0 días
```
