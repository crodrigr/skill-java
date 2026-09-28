# ❓ Quiz 01 — Manejo de Archivos en Java (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué significa persistir datos en un programa?

- **A.** Guardarlos fuera de la memoria volátil del proceso, en un lugar que sobreviva a su ejecución.
- **B.** Imprimirlos por consola para que el usuario los vea.
- **C.** Declararlos como variables `static` en vez de variables locales.
- **D.** Guardarlos en una `List` en vez de en un arreglo.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Persistir datos significa guardarlos en un lugar (típicamente un archivo) que
sobreviva a la ejecución del programa que los creó; ni imprimirlos, ni usar `static`, ni usar `List` en
vez de un arreglo logra eso por sí solo.

</details>

**2. [Selección]** **Pregunta:** ¿qué representa un objeto `File` en Java, y qué garantiza `listFiles()`?

- **A.** `File` representa el contenido cargado en memoria; `listFiles()` garantiza un orden fijo.
- **B.** `File` representa una ruta del sistema de archivos (que puede existir o no); `listFiles()`
  devuelve el contenido real de un directorio, sin garantizar ningún orden.
- **C.** `File` es una conexión abierta al archivo; `listFiles()` requiere cerrarla antes de listar.
- **D.** `File` solo funciona con archivos, nunca con directorios.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `File` es una ruta, no el contenido ni una conexión abierta; `listFiles()`
refleja el estado real del directorio en el momento en que se invoca, sin garantía de orden.

</details>

**3. [Selección múltiple]** Dado un programa que tiene un catálogo de datos hardcodeado directamente en
el código, ¿cuáles afirmaciones son verdaderas?

- A. Agregar un dato nuevo exige modificar y recompilar el programa.
- B. Los datos siempre reflejan el estado más actualizado posible.
- C. Sería preferible leer esos datos desde un archivo externo, si pueden cambiar con el tiempo.
- D. Este es exactamente el problema que la lectura de archivos resuelve.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: datos hardcodeados quedan fijos en el momento en que se
escribió el código, no reflejan ningún cambio posterior sin recompilar.

</details>

**4. [Abierta]** Explica, con tus palabras, cómo leerías el contenido de un archivo de texto línea por
línea en Java.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

Se abre el archivo con un `FileReader`, envuelto en un `BufferedReader` para poder leer línea por línea
con `readLine()`. Se recorre el archivo con un bucle que llama a `readLine()` repetidamente hasta que
devuelve `null` (fin del archivo), y todo el bloque se declara con try-with-resources para que el
archivo se cierre automáticamente al terminar, incluso si ocurre una excepción como
`FileNotFoundException`.

</details>

**5. [Selección múltiple]** Dado un programa que solo imprime datos por consola, sin escribirlos en
ningún archivo, ¿cuáles afirmaciones son verdaderas?

- A. Esos datos se pierden apenas el programa termina.
- B. Ese es exactamente el problema que la escritura de archivos resuelve.
- C. Los datos quedan guardados automáticamente en un archivo de log.
- D. Sería preferible escribirlos en un archivo, si necesitan sobrevivir a la ejecución.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: imprimir por consola no guarda nada en ningún archivo de
forma automática.

</details>

**6. [Abierta]** Explica, con tus palabras, cómo escribirías una lista de datos en un archivo de texto en
Java, y cómo confirmarías que la escritura funcionó.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

Se abre el archivo con un `FileWriter`, envuelto en un `BufferedWriter`, dentro de un bloque
try-with-resources; se recorre la lista de datos escribiendo cada uno con `write()` seguido de
`newLine()`. Para confirmar que la escritura funcionó, se puede consultar `File.exists()` sobre el
archivo después de escribirlo, o releerlo y comparar su contenido con lo que se esperaba escribir.

</details>

**7. [Abierta]** Explica, con tus palabras, cómo borrarías un archivo temporal en Java, y cómo
confirmarías que el borrado funcionó.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Se invoca `archivo.delete()` sobre el objeto `File` que apunta al archivo temporal; el método devuelve un
`boolean` indicando si el borrado tuvo éxito. Para confirmar el resultado, se puede consultar
`exists()` antes de borrar (debería dar `true`) y después de borrar (debería dar `false`).

</details>

**8. [Selección]** **Pregunta:** ¿qué exige `ObjectOutputStream.writeObject()` de la clase del objeto que
se serializa?

- **A.** Que declare un constructor sin argumentos.
- **B.** Que implemente la interfaz `Serializable`.
- **C.** Que sobrescriba `toString()`.
- **D.** Que todos sus campos sean `public`.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `Serializable` es una interfaz marcadora, sin métodos: su sola presencia le
indica a la JVM que la clase puede serializarse; sin ella, `writeObject()` lanza
`NotSerializableException`.

</details>

**9. [Abierta]** Explica, con tus palabras, cómo serializarías un objeto completo en Java, en vez de
escribir cada uno de sus campos a mano.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

La clase del objeto debe implementar `Serializable` (una interfaz marcadora, sin métodos que
sobrescribir). Después, se abre un `ObjectOutputStream` sobre un `FileOutputStream`, dentro de un
try-with-resources, y se invoca `writeObject(objeto)` una sola vez: la JVM se encarga de convertir el
objeto completo (todos sus campos) a una secuencia de bytes, sin necesitar convertir ni ordenar cada
campo manualmente.

</details>

**10. [Abierta]** Explica, con tus palabras, cómo verificarías que un objeto serializado sobrevive entre
dos ejecuciones separadas del programa.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

Se escriben dos programas (o dos clases con su propio `main`): uno que crea el objeto, lo serializa con
`ObjectOutputStream.writeObject()` a un archivo, y termina; y otro, ejecutado después como un proceso
Java completamente nuevo, que abre ese mismo archivo con `ObjectInputStream` y lo deserializa con
`readObject()`. Si los campos del objeto recuperado coinciden exactamente con los originales, la
persistencia entre ejecuciones separadas quedó demostrada con evidencia real, no solo simulada dentro de
un mismo proceso.

</details>

**11. [Abierta, integrador]** Diseña, con tus palabras (sin código), una solución que combine lectura,
escritura, borrado y serialización/deserialización de archivos para un problema práctico nuevo.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

Por ejemplo: un sistema que lee la lista de usuarios citados del día desde un archivo de texto
(lectura), serializa el registro completo de cada uno a su propio archivo `.ser` (serialización), lo
recupera en una segunda ejecución para confirmarlo (deserialización, con `Escritor`/`Lector` separados),
y borra los archivos `.ser` de los usuarios ya dados de baja definitivamente (borrado) — usando texto
plano para los datos simples que cambian seguido, y serialización para los objetos completos que se
necesitan reconstruir exactos.

</details>
