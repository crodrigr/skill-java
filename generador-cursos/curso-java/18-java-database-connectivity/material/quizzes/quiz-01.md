# ❓ Quiz 01 — Java DataBase Connectivity (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué es JDBC?

- **A.** Un motor de base de datos, alternativa a MySQL.
- **B.** La API estándar de Java para conectarse a una base de datos relacional y ejecutar SQL contra
  ella.
- **C.** Un formato de archivo para guardar datos estructurados.
- **D.** Una librería externa que reemplaza la necesidad de una base de datos.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** JDBC es la API estándar de Java (`java.sql`) para conectarse a una base de
datos relacional; no es un motor de base de datos ni un formato de archivo.

</details>

---

**2. [Abierta]** **Pregunta:** ¿qué tres datos necesita `DriverManager.getConnection()` para conectarse a
una base de datos, y qué pasa si son incorrectos?

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

Necesita la URL de conexión (servidor y base de datos), el usuario y la clave. Si alguno es incorrecto,
`getConnection()` lanza una `SQLException` real, que el programa debe capturar y manejar en vez de asumir
que la conexión siempre tiene éxito.

</details>

---

**3. [Abierta]** **Pregunta:** dado un `Statement` con una conexión abierta, ¿cómo usarías `executeQuery()`
para listar todos los libros de una tabla `libros`, y qué devuelve `executeUpdate()` a diferencia de
`executeQuery()`?

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

`executeQuery("SELECT * FROM libros")` devuelve un `ResultSet`, que se recorre con `next()` fila por fila
hasta que devuelve `false`. `executeUpdate()` se usa para `INSERT`/`UPDATE`/`DELETE` y devuelve un `int`
con la cantidad de filas afectadas, no el contenido de las filas.

</details>

---

**4. [Selección múltiple]** ¿Cuáles de las siguientes consultas son inseguras (vulnerables a inyección
SQL)?

- **A.** `"SELECT * FROM libros WHERE titulo = '" + tituloBuscado + "'"`
- **B.** `conexion.prepareStatement("SELECT * FROM libros WHERE titulo = ?")` con `setString(1, tituloBuscado)`
- **C.** `"DELETE FROM libros WHERE id = " + idRecibido`
- **D.** `"SELECT * FROM libros WHERE id = " + 5` (el `5` está fijo en el código, no viene de afuera)

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** Ambas concatenan un valor externo directamente en el SQL. B es segura
porque usa un parámetro. D no es insegura en la práctica: el valor `5` está fijo en el código, no viene de
ninguna fuente externa que un usuario pueda manipular.

</details>

---

**5. [Abierta]** **Pregunta:** dado un `Connection` abierto, ¿cómo implementarías con `PreparedStatement`
una actualización del diagnóstico de un paciente, dado su `id` y el nuevo diagnóstico?

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

```java
try (PreparedStatement actualizar = conexion.prepareStatement(
        "UPDATE pacientes SET diagnostico = ? WHERE id = ?")) {
    actualizar.setString(1, nuevoDiagnostico);
    actualizar.setInt(2, id);
    actualizar.executeUpdate();
}
```

Cada valor se asigna por posición con `setString()`/`setInt()`, nunca concatenado en el texto SQL.

</details>

---

**6. [Selección]** ¿Qué resuelve la arquitectura MVC, y qué responsabilidad tiene cada capa?

- A. Modelo = presentación; Vista = datos y acceso a ellos; Controlador = coordinación.
- B. Modelo = datos y acceso a ellos; Vista = presentación; Controlador = coordinación entre ambos.
- C. MVC resuelve únicamente el diseño visual de una interfaz gráfica.
- D. Modelo, Vista y Controlador son tres nombres distintos para la misma responsabilidad.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El Modelo representa los datos y su acceso; la Vista los presenta; el
Controlador coordina las llamadas entre ambos, sin ejecutar SQL ni imprimir directamente.

</details>

---

**7. [Abierta]** **Pregunta:** dado un fragmento de código que conecta a MySQL, ejecuta una consulta y
además imprime el resultado por consola, todo en el mismo método, ¿cómo lo reorganizarías en Modelo, Vista
y Controlador?

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

El acceso a datos (conexión + consulta) pasa a una clase de Modelo (por ejemplo, un DAO) que devuelve
objetos del dominio. El formato e impresión pasa a una clase de Vista, que recibe esos objetos ya
construidos, sin conocer JDBC. Un Controlador con el `main()` llama al Modelo para obtener los datos y a
la Vista para mostrarlos, sin ejecutar SQL ni imprimir directamente él mismo.

</details>

---

**8. [Abierta, integradora]** **Pregunta:** te piden diseñar, desde cero, un sistema que persista datos
en MySQL y esté organizado en capas. ¿Qué decisiones tomarías para cumplir ambos requisitos a la vez?

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

Toda operación que toque la base de datos (consultar, insertar, actualizar, borrar) se implementa con
`PreparedStatement`, parametrizando cualquier valor externo. El acceso a datos vive en el Modelo (por
ejemplo, un DAO), la presentación en la Vista (sin ningún import de `java.sql`), y un Controlador coordina
ambas capas sin ejecutar SQL ni imprimir directamente. Ninguna capa asume responsabilidades de otra.

</details>

---
