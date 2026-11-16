# 🔑 Soluciones de los ejercicios — Módulo 18

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar violación de configuración/conexión

El programa usa una lista fija de dos títulos en el código, en vez de conectarse a la base de datos real:
por eso no menciona el tercer préstamo, que existe en la tabla `prestamos` pero nunca se consulta.

## 🟡 Intermedio 01 — Aplicar configuración del controlador y conexión

```java
package com.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "curso_java_root";

    public static void main(String[] args) {
        System.out.println("Prestamos activos (consulta real a la base de datos):");
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery("SELECT titulo FROM prestamos ORDER BY id")) {
            while (resultado.next()) {
                System.out.println("- " + resultado.getString("titulo"));
            }
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
        }
    }
}
```

```text
Prestamos activos (consulta real a la base de datos):
- El Quijote
- Rayuela
- Cien anios de soledad
```

## 🟢 Básico 02 — Identificar uso incorrecto de JDBC

El programa nunca ejecuta ninguna consulta SQL: los datos están escritos directamente en el código, en
vez de leerse con `Statement`/`ResultSet` desde la tabla real, que tiene un libro más.

## 🟡 Intermedio 02 — Aplicar uso de JDBC

```java
package com.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "curso_java_root";

    public static void main(String[] args) {
        System.out.println("Catalogo de libros (consulta real a la base de datos):");
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery("SELECT titulo, autor FROM libros ORDER BY id")) {
            while (resultado.next()) {
                System.out.println("- " + resultado.getString("titulo") + ", " + resultado.getString("autor"));
            }
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
        }
    }
}
```

```text
Catalogo de libros (consulta real a la base de datos):
- El Quijote, Cervantes
- Cien anios de soledad, Garcia Marquez
- Rayuela, Cortazar
```

## 🟢 Básico 03 — Identificar una consulta insegura

El programa concatena el valor buscado directamente en el texto SQL: con `"Nada' OR '1'='1"`, la
condición real de la consulta se vuelve "trae cualquier fila", devolviendo los tres libros en vez de
ninguno — una inyección SQL real.

## 🟡 Intermedio 03 — Aplicar consultas parametrizadas

```java
package com.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo {
    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "curso_java_root";

    public static void main(String[] args) {
        String tituloBuscado = "Nada' OR '1'='1";

        String sql = "SELECT titulo FROM libros WHERE titulo = ?";
        System.out.println("Consulta ejecutada: " + sql + " con parametro: " + tituloBuscado);

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, tituloBuscado);
            try (ResultSet resultado = sentencia.executeQuery()) {
                int filas = 0;
                while (resultado.next()) {
                    filas++;
                    System.out.println("- " + resultado.getString("titulo"));
                }
                System.out.println("Filas devueltas: " + filas);
            }
        } catch (SQLException e) {
            System.out.println("Error real de base de datos: " + e.getMessage());
        }
    }
}
```

```text
Consulta ejecutada: SELECT titulo FROM libros WHERE titulo = ? con parametro: Nada' OR '1'='1
Filas devueltas: 0
```

## 🟢 Básico 04 — Identificar operaciones sin parametrizar

Las tres operaciones (`INSERT`, `UPDATE`, `SELECT`) concatenan los valores directamente en el texto SQL.
Un valor con una comilla simple rompería la sintaxis, y un valor pensado a propósito podría alterar el
significado de cualquiera de las tres, igual que en una consulta de lectura.

## 🟡 Intermedio 04 — Aplicar operaciones con PreparedStatement

```java
package com.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "curso_java_root";

    public static void main(String[] args) {
        String titulo = "El Principito";
        String socio = "Marco Leiva";
        String fecha = "2026-09-28";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE)) {

            int id;
            try (PreparedStatement insertar = conexion.prepareStatement(
                    "INSERT INTO prestamos (titulo, socio, fecha_prestamo) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                insertar.setString(1, titulo);
                insertar.setString(2, socio);
                insertar.setString(3, fecha);
                insertar.executeUpdate();
                try (ResultSet claves = insertar.getGeneratedKeys()) {
                    claves.next();
                    id = claves.getInt(1);
                }
            }

            try (PreparedStatement actualizar = conexion.prepareStatement(
                    "UPDATE prestamos SET socio = ? WHERE id = ?")) {
                actualizar.setString(1, "Marco Leiva Rojas");
                actualizar.setInt(2, id);
                actualizar.executeUpdate();
            }

            try (PreparedStatement consultar = conexion.prepareStatement(
                    "SELECT titulo, socio FROM prestamos WHERE id = ?")) {
                consultar.setInt(1, id);
                try (ResultSet resultado = consultar.executeQuery()) {
                    resultado.next();
                    System.out.println("Prestamo registrado: " + resultado.getString("titulo")
                            + ", " + resultado.getString("socio"));
                }
            }

            try (PreparedStatement borrar = conexion.prepareStatement(
                    "DELETE FROM prestamos WHERE id = ?")) {
                borrar.setInt(1, id);
                borrar.executeUpdate();
            }
            System.out.println("Prestamo de prueba borrado. La tabla vuelve a su estado original.");
        } catch (SQLException e) {
            System.out.println("Error real de base de datos: " + e.getMessage());
        }
    }
}
```

```text
Prestamo registrado: El Principito, Marco Leiva Rojas
Prestamo de prueba borrado. La tabla vuelve a su estado original.
```

## 🟢 Básico 05 — Identificar capas MVC mezcladas

La conexión JDBC, la consulta y el formato de impresión están todos mezclados en el mismo método.
Cambiar el orden mostrado obligaría a tocar el mismo código que accede a la base de datos, en vez de un
componente de presentación separado.

## 🟡 Intermedio 05 — Aplicar arquitectura MVC

```java
package com.biblioteca.modelo;

import java.sql.Date;

public class PrestamoBiblioteca {
    private final String titulo;
    private final String socio;
    private final Date fechaPrestamo;

    public PrestamoBiblioteca(String titulo, String socio, Date fechaPrestamo) {
        this.titulo = titulo;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSocio() {
        return socio;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }
}
```

```java
package com.biblioteca.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "curso_java_root";

    public List<PrestamoBiblioteca> listar() throws SQLException {
        List<PrestamoBiblioteca> prestamos = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(
                     "SELECT titulo, socio, fecha_prestamo FROM prestamos ORDER BY id")) {
            while (resultado.next()) {
                prestamos.add(new PrestamoBiblioteca(resultado.getString("titulo"), resultado.getString("socio"),
                        resultado.getDate("fecha_prestamo")));
            }
        }
        return prestamos;
    }
}
```

```java
package com.biblioteca.vista;

import com.biblioteca.modelo.PrestamoBiblioteca;
import java.util.List;

public class PrestamoVista {

    public void mostrar(List<PrestamoBiblioteca> prestamos) {
        System.out.println("=== Prestamos activos ===");
        for (PrestamoBiblioteca prestamo : prestamos) {
            System.out.println(prestamo.getTitulo() + " - " + prestamo.getSocio()
                    + " (" + prestamo.getFechaPrestamo() + ")");
        }
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error real de base de datos: " + mensaje);
    }
}
```

```java
package com.biblioteca.controlador;

import com.biblioteca.modelo.PrestamoDAO;
import com.biblioteca.vista.PrestamoVista;
import java.sql.SQLException;

public class Controlador {
    public static void main(String[] args) {
        PrestamoDAO modelo = new PrestamoDAO();
        PrestamoVista vista = new PrestamoVista();

        try {
            vista.mostrar(modelo.listar());
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}
```

```text
=== Prestamos activos ===
El Quijote - Carla Nunez (2026-09-20)
Rayuela - Diego Paredes (2026-09-25)
Cien anios de soledad - Elena Gomez (2026-09-27)
```

## 🔴 Avanzado 01 — Corregir un diseño con dos sub-temas técnicos ausentes

**Corrección 1 (consultas inseguras) y Corrección 2 (capas mezcladas)**, aplicadas juntas:

```java
package com.biblioteca.modelo;

import java.sql.Date;

public class PrestamoBiblioteca {
    private final String titulo;
    private final String socio;
    private final Date fechaPrestamo;

    public PrestamoBiblioteca(String titulo, String socio, Date fechaPrestamo) {
        this.titulo = titulo;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSocio() {
        return socio;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }
}
```

```java
package com.biblioteca.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "curso_java_root";

    public void renovar(String titulo, String nuevaFecha) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement actualizar = conexion.prepareStatement(
                     "UPDATE prestamos SET fecha_prestamo = ? WHERE titulo = ?")) {
            actualizar.setString(1, nuevaFecha);
            actualizar.setString(2, titulo);
            actualizar.executeUpdate();
        }
    }

    public List<PrestamoBiblioteca> buscarPorTitulo(String titulo) throws SQLException {
        List<PrestamoBiblioteca> prestamos = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement consultar = conexion.prepareStatement(
                     "SELECT titulo, socio, fecha_prestamo FROM prestamos WHERE titulo = ?")) {
            consultar.setString(1, titulo);
            try (ResultSet resultado = consultar.executeQuery()) {
                while (resultado.next()) {
                    prestamos.add(new PrestamoBiblioteca(resultado.getString("titulo"), resultado.getString("socio"),
                            resultado.getDate("fecha_prestamo")));
                }
            }
        }
        return prestamos;
    }
}
```

```java
package com.biblioteca.vista;

import com.biblioteca.modelo.PrestamoBiblioteca;
import java.util.List;

public class PrestamoVista {

    public void mostrar(List<PrestamoBiblioteca> prestamos) {
        System.out.println("=== Renovacion de prestamo ===");
        for (PrestamoBiblioteca prestamo : prestamos) {
            System.out.println(prestamo.getTitulo() + " - " + prestamo.getSocio()
                    + " (" + prestamo.getFechaPrestamo() + ")");
        }
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error real de base de datos: " + mensaje);
    }
}
```

```java
package com.biblioteca.controlador;

import com.biblioteca.modelo.PrestamoDAO;
import com.biblioteca.vista.PrestamoVista;
import java.sql.SQLException;

public class Controlador {
    public static void main(String[] args) {
        PrestamoDAO modelo = new PrestamoDAO();
        PrestamoVista vista = new PrestamoVista();
        String titulo = "Rayuela";

        try {
            modelo.renovar(titulo, "2026-10-05");
            vista.mostrar(modelo.buscarPorTitulo(titulo));
            modelo.renovar(titulo, "2026-09-25");
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}
```

```text
=== Renovacion de prestamo ===
Rayuela - Diego Paredes (2026-10-05)
```

Comparado con la versión original: el `UPDATE` y el `SELECT` ahora usan `PreparedStatement` en vez de
concatenar el título y la fecha, y el acceso a datos, la presentación y la coordinación viven en clases
separadas (Modelo, Vista, Controlador) en vez de una sola clase mezclada.

## 🏆 Desafío 01 — Diseñar un caso nuevo combinando JDBC y MVC

Una solución posible: un sistema de gestión de socios de la Biblioteca, con un DAO que ejecuta las cuatro
operaciones (registrar, consultar, actualizar, borrar) con `PreparedStatement`, una Vista dedicada a
formatear e imprimir, y un Controlador que coordina ambas.

```java
package com.biblioteca.modelo;

public class Socio {
    private final int id;
    private final String nombre;
    private final String categoria;

    public Socio(int id, String nombre, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }
}
```

```java
package com.biblioteca.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SocioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "curso_java_root";

    public int registrar(String nombre, String categoria) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement insertar = conexion.prepareStatement(
                     "INSERT INTO socios (nombre, categoria) VALUES (?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            insertar.setString(1, nombre);
            insertar.setString(2, categoria);
            insertar.executeUpdate();
            try (ResultSet claves = insertar.getGeneratedKeys()) {
                claves.next();
                return claves.getInt(1);
            }
        }
    }

    public Socio buscarPorId(int id) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement consultar = conexion.prepareStatement(
                     "SELECT id, nombre, categoria FROM socios WHERE id = ?")) {
            consultar.setInt(1, id);
            try (ResultSet resultado = consultar.executeQuery()) {
                resultado.next();
                return new Socio(resultado.getInt("id"), resultado.getString("nombre"),
                        resultado.getString("categoria"));
            }
        }
    }

    public void actualizarCategoria(int id, String nuevaCategoria) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement actualizar = conexion.prepareStatement(
                     "UPDATE socios SET categoria = ? WHERE id = ?")) {
            actualizar.setString(1, nuevaCategoria);
            actualizar.setInt(2, id);
            actualizar.executeUpdate();
        }
    }

    public void borrar(int id) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement borrar = conexion.prepareStatement("DELETE FROM socios WHERE id = ?")) {
            borrar.setInt(1, id);
            borrar.executeUpdate();
        }
    }
}
```

```java
package com.biblioteca.vista;

import com.biblioteca.modelo.Socio;

public class SocioVista {

    public void mostrar(Socio socio) {
        System.out.println(socio.getNombre() + " (" + socio.getCategoria() + ")");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error real de base de datos: " + mensaje);
    }
}
```

```java
package com.biblioteca.controlador;

import com.biblioteca.modelo.SocioDAO;
import com.biblioteca.vista.SocioVista;
import java.sql.SQLException;

public class Controlador {
    public static void main(String[] args) {
        SocioDAO modelo = new SocioDAO();
        SocioVista vista = new SocioVista();

        try {
            int id = modelo.registrar("Felipe Castro", "ESTUDIANTE");
            vista.mostrarMensaje("Socio registrado:");
            vista.mostrar(modelo.buscarPorId(id));

            modelo.actualizarCategoria(id, "DOCENTE");
            vista.mostrarMensaje("Socio actualizado:");
            vista.mostrar(modelo.buscarPorId(id));

            modelo.borrar(id);
            vista.mostrarMensaje("Socio de prueba borrado. La tabla vuelve a su estado original.");
        } catch (SQLException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}
```

```text
Socio registrado:
Felipe Castro (ESTUDIANTE)
Socio actualizado:
Felipe Castro (DOCENTE)
Socio de prueba borrado. La tabla vuelve a su estado original.
```
