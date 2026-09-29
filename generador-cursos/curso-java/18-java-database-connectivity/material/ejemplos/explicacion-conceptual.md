# 📚 Explicación conceptual — Módulo 18

## 🧠 Concepto: ¿Qué es JDBC?

- **JDBC** (Java DataBase Connectivity) es la API estándar de Java para conectarse a una base de datos
  relacional y ejecutar sentencias SQL contra ella.
- A diferencia de un archivo local (Módulo 17), una base de datos vive en un servidor aparte: varios
  programas, en varias computadoras, pueden leer y escribir los mismos datos actualizados.
- Para conectarse hacen falta tres cosas: el controlador (driver) del motor de base de datos, una URL de
  conexión, y credenciales (usuario y clave).
- Este módulo usa MySQL como motor de base de datos.

📎 Ver en la práctica: [Ejemplo 01 — ¿Qué es JDBC?](01-que-es-jdbc.md)

## 🧠 Concepto: Configuración de controlador (MySQL)

- Para conectarse a MySQL, un proyecto Java necesita el controlador (driver) `mysql-connector-j` en su
  classpath de ejecución.
- La conexión se abre con `DriverManager.getConnection(url, usuario, clave)`, donde la URL indica el
  servidor y la base de datos (`jdbc:mysql://host:puerto/base`).
- Una conexión puede fallar (servidor apagado, credenciales incorrectas): JDBC lo señala con una
  `SQLException` real, que el programa debe manejar.
- Toda `Connection` se abre con try-with-resources, para garantizar que se cierre aunque ocurra un error.

📎 Ver en la práctica: [Ejemplo 02 — Configuración del controlador y conexión](02-configuracion-del-controlador-y-conexion.md)

## 🧠 Concepto: Uso de JDBC

- `Statement` ejecuta sentencias SQL contra una conexión abierta.
- `executeQuery()` ejecuta un `SELECT` y devuelve un `ResultSet` con las filas; `ResultSet.next()`
  avanza fila por fila, devolviendo `false` cuando no hay más.
- `executeUpdate()` ejecuta `INSERT`, `UPDATE` o `DELETE`, y devuelve la cantidad de filas afectadas
  (no su contenido).
- `Statement.RETURN_GENERATED_KEYS` permite recuperar la clave autogenerada de un `INSERT`.

📎 Ver en la práctica: [Ejemplo 03 — Uso de JDBC](03-uso-de-jdbc.md)

## 🧠 Concepto: Consultas parametrizadas

- Concatenar directamente un valor externo (entrada de usuario, archivo, etc.) en el texto de una
  consulta SQL es una práctica insegura: ese valor puede alterar el significado real de la consulta
  (inyección SQL).
- Una **consulta parametrizada** usa un marcador (`?`) en el lugar del valor, y lo pasa por separado con
  `PreparedStatement`.
- MySQL trata un parámetro siempre como un valor literal, nunca como parte de la sintaxis SQL — por eso
  un valor pensado para alterar la consulta no tiene ningún efecto especial.

📎 Ver en la práctica: [Ejemplo 04 — Consultas parametrizadas](04-consultas-parametrizadas.md)

## 🧠 Concepto: Operaciones con PreparedStatement

- `PreparedStatement` declara una consulta una sola vez, con marcadores `?` en el lugar de cada valor.
- `setString(posición, valor)`, `setInt(posición, valor)` (y similares) asignan cada valor por posición,
  antes de ejecutar.
- Las cuatro operaciones básicas —consultar (`executeQuery()`), insertar, actualizar y borrar
  (`executeUpdate()`)— se implementan igual con `PreparedStatement`, siempre parametrizando cualquier
  valor externo.
- `Statement.RETURN_GENERATED_KEYS` también funciona al preparar un `PreparedStatement`, para recuperar
  la clave autogenerada de un `INSERT`.

📎 Ver en la práctica: [Ejemplo 05 — Operaciones con PreparedStatement](05-operaciones-con-preparedstatement.md)

## 🧠 Concepto: Arquitectura MVC

- **MVC** (Modelo-Vista-Controlador) organiza un programa en tres capas con responsabilidades separadas.
- El **Modelo** representa los datos del dominio y el acceso a ellos (por ejemplo, con JDBC).
- La **Vista** presenta esos datos a quien usa el programa (por consola, en este módulo), sin saber nada
  de JDBC.
- El **Controlador** coordina las llamadas entre Modelo y Vista, sin ejecutar SQL ni imprimir
  directamente.

📎 Ver en la práctica: [Ejemplo 06 — Arquitectura MVC](06-arquitectura-mvc.md)

## 🧠 Concepto: Arquitectura MVC en un proyecto Java

- Aplicar MVC a un proyecto Java significa declarar clases (o paquetes) separados para cada capa:
  `modelo/`, `vista/`, `controlador/`.
- Reorganizar un programa mezclado en capas MVC no cambia su comportamiento observable, solo su
  estructura interna — verificable ejecutando ambas versiones y comparando la salida.
- Un error frecuente es que el Controlador termine haciendo el trabajo del Modelo o de la Vista, en vez de
  solo coordinar las llamadas entre ambos.

📎 Ver en la práctica: [Ejemplo 06 — Arquitectura MVC](06-arquitectura-mvc.md)

## 📋 Resumen de las herramientas

| Herramienta | Qué resuelve | Cuándo usarla |
|---|---|---|
| `DriverManager.getConnection()` | Abre una conexión real a MySQL | Al iniciar cualquier operación contra la base de datos |
| `Statement` + `executeQuery()`/`ResultSet` | Ejecuta un `SELECT` y recorre sus filas | Consultas simples, sin valores externos que parametrizar |
| `Statement` + `executeUpdate()` | Ejecuta `INSERT`/`UPDATE`/`DELETE` | Operaciones simples, sin valores externos que parametrizar |
| Consulta parametrizada | Evita que un valor externo altere el SQL (inyección) | Cualquier consulta que incorpore un valor que no esté fijo en el código |
| `PreparedStatement` | Ejecuta consultas y operaciones parametrizadas, de forma segura y sin conversión manual de tipos | Siempre que la consulta incorpore un valor externo, en cualquiera de las cuatro operaciones |
| Arquitectura MVC | Separa el acceso a datos, la presentación y la coordinación entre ambos | Al organizar un proyecto que combina persistencia con lógica y presentación |
