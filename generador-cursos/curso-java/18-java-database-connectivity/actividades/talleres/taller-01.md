# 🛠️ Taller 01 — Sistema de Gestión de Pacientes de MediSalud con JDBC y MVC

## 🎯 Objetivo

Diseñar un sistema de gestión de pacientes de MediSalud que persista datos en MySQL con
`PreparedStatement`, organizado en capas Modelo-Vista-Controlador (RA-5, RA-7, RA-8).

## 🌍 Contexto

MediSalud necesita un programa para registrar pacientes nuevos, listar los existentes, actualizar su
diagnóstico y darlos de baja, todo persistido en su base de datos MySQL real (tabla `pacientes`), sin
mezclar el acceso a datos con la presentación.

## 🪜 Pasos

1. **Modelo**: declara `PacienteMediSalud` (datos) y una clase de acceso a datos (`PacienteDAO`) que ejecute,
   con `PreparedStatement`, las cuatro operaciones: registrar un paciente nuevo, listar todos los
   pacientes, actualizar el diagnóstico de uno existente, y borrar uno dado de alta.
2. **Vista**: declara una clase dedicada exclusivamente a formatear e imprimir por consola los datos que
   entrega el Modelo (la lista de pacientes, mensajes de estado, errores), sin ninguna llamada JDBC
   propia.
3. **Controlador**: coordina las llamadas entre Modelo y Vista según la acción pedida (registrar, listar,
   actualizar, borrar), sin ejecutar SQL ni imprimir directamente.
4. **Integración**: ejecuta el flujo completo — registrar un paciente nuevo, listarlo (aparece), actualizar
   su diagnóstico (se refleja en una nueva consulta), y borrarlo (ya no aparece) — verificando en cada
   paso el estado real de la base de datos.

## 💡 Ejemplo resuelto

Un fragmento del Modelo, para inspirarte (no es la solución completa):

```java
public int registrar(String nombre, int edad, String diagnostico) throws SQLException {
    try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
         PreparedStatement insertar = conexion.prepareStatement(
                 "INSERT INTO pacientes (nombre, edad, diagnostico) VALUES (?, ?, ?)",
                 Statement.RETURN_GENERATED_KEYS)) {
        insertar.setString(1, nombre);
        insertar.setInt(2, edad);
        insertar.setString(3, diagnostico);
        insertar.executeUpdate();
        try (ResultSet claves = insertar.getGeneratedKeys()) {
            claves.next();
            return claves.getInt(1);
        }
    }
}
```

## 📦 Entregable

```text
GestionPacientesMediSalud/
└── com/medisalud/
    ├── modelo/
    │   ├── PacienteMediSalud.java
    │   └── PacienteDAO.java
    ├── vista/
    │   └── PacienteVista.java
    └── controlador/
        └── Controlador.java
```

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar un paciente nuevo | Lista de pacientes tras registrar | El paciente nuevo aparece, además de los ya existentes |
| Actualizar su diagnóstico | Lista de pacientes tras actualizar | El diagnóstico del paciente cambió, el resto sigue igual |
| Borrarlo | Lista de pacientes tras borrar | El paciente ya no aparece; la lista vuelve a su estado original |

## 📏 Criterios de evaluación

- Las cuatro operaciones del Modelo usan `PreparedStatement` parametrizado, nunca concatenación.
- La Vista no tiene ningún import de `java.sql`.
- El Controlador no ejecuta SQL ni imprime directamente: solo coordina Modelo y Vista.
- El programa compila, se ejecuta contra la base de datos real y produce los resultados de la tabla de
  casos de prueba.
