# 📚 Explicación conceptual — Módulo 17

## 🧠 Concepto: Persistencia de datos

- Mientras un programa corre, sus variables viven en memoria; al terminar, esa memoria se libera por
  completo.
- **Persistir datos** significa guardarlos fuera de la memoria volátil del proceso, en un lugar que
  sobreviva a la ejecución del programa.
- Sin persistencia explícita, cada ejecución de un programa empieza de cero, sin ningún rastro de
  ejecuciones anteriores.
- Este módulo cubre dos formas de persistir datos en Java: como texto plano en un archivo, y como
  objetos completos serializados.

📎 Ver en la práctica: [Ejemplo 01 — Persistencia de datos](01-persistencia-de-datos.md)

## 🧠 Concepto: Archivos en Java

- Un archivo es una unidad de datos identificada por una **ruta** en el sistema de archivos: absoluta
  (desde la raíz del sistema) o relativa (desde la carpeta donde corre el programa).
- Java representa esa ruta con la clase `java.io.File`, sin necesitar abrir el archivo todavía: un
  objeto `File` puede referirse a una ruta que ni siquiera existe.

📎 Ver en la práctica: [Ejemplo 02 — Archivos en Java y la clase File](02-archivos-en-java-y-la-clase-file.md)

## 🧠 Concepto: La clase File

- `exists()` indica si la ruta corresponde a algo real en el sistema de archivos.
- `isFile()`/`isDirectory()` distinguen un archivo de un directorio.
- `listFiles()` devuelve el contenido real y actual de un directorio — sin garantía de orden, por lo que
  conviene ordenarlo explícitamente antes de mostrarlo.
- `getName()`/`getAbsolutePath()` devuelven el nombre o la ruta completa del archivo o directorio.

📎 Ver en la práctica: [Ejemplo 02 — Archivos en Java y la clase File](02-archivos-en-java-y-la-clase-file.md)

## 🧠 Concepto: Lectura de datos de un archivo

- `FileReader` abre un archivo de texto para leerlo; `BufferedReader` lo envuelve para leer línea por
  línea con `readLine()`, que devuelve `null` al llegar al final.
- Leer un archivo que no existe lanza `FileNotFoundException` (una subclase de `IOException`): un
  programa robusto la maneja con `try`/`catch`, en vez de asumir que el archivo siempre está.
- Todo flujo de lectura se abre con try-with-resources, para garantizar que se cierre incluso si ocurre
  una excepción.

📎 Ver en la práctica: [Ejemplo 03 — Lectura de datos de un archivo](03-lectura-de-datos-de-un-archivo.md)

## 🧠 Concepto: Escritura de datos de un archivo

- `FileWriter` abre un archivo de texto para escribirlo; `BufferedWriter` lo envuelve para escribir de
  forma eficiente, con `write()` y `newLine()`.
- Escribir en un archivo persiste los datos más allá de la ejecución del programa — a diferencia de
  imprimirlos por consola, que solo los muestra mientras el programa corre.
- Confirmar que la escritura funcionó (con `File.exists()`, o releyendo el archivo) es una buena
  práctica, no un paso opcional.

📎 Ver en la práctica: [Ejemplo 04 — Escritura de datos de un archivo](04-escritura-de-datos-de-un-archivo.md)

## 🧠 Concepto: Borrado de un archivo

- `File.delete()` borra el archivo (o directorio vacío) al que apunta la ruta, y devuelve un `boolean`
  indicando si el borrado tuvo éxito.
- Un archivo temporal que ya cumplió su propósito (por ejemplo, un respaldo intermedio) debería borrarse
  explícitamente; de lo contrario, se acumula indefinidamente en el disco.
- `exists()` antes y después de `delete()` confirma el resultado real del borrado.

📎 Ver en la práctica: [Ejemplo 05 — Borrado de un archivo](05-borrado-de-un-archivo.md)

## 🧠 Concepto: Interfaz Serializable

- `Serializable` (`java.io.Serializable`) es una interfaz **marcadora**: no declara ningún método: su
  sola presencia le indica a la JVM que los objetos de esa clase pueden convertirse a una secuencia de
  bytes y reconstruirse después.
- Una clase que no implementa `Serializable` no puede serializarse: intentarlo lanza
  `NotSerializableException`, una excepción real, no una advertencia.
- Declarar un `serialVersionUID` explícito en cada clase serializable es una buena práctica.

📎 Ver en la práctica: [Ejemplo 06 — Serializar objetos](06-serializar-objetos.md)

## 🧠 Concepto: Clase ObjectOutputStream

- `ObjectOutputStream.writeObject(objeto)` serializa un objeto completo —todos sus campos, en una sola
  llamada— a un archivo, en vez de tener que escribir cada campo por separado como texto.
- A diferencia de escribir texto a mano, no hace falta recordar el orden de los campos ni convertir cada
  tipo manualmente: la serialización lo hace de forma automática.

📎 Ver en la práctica: [Ejemplo 06 — Serializar objetos](06-serializar-objetos.md)

## 🧠 Concepto: Clase ObjectInputStream

- `ObjectInputStream.readObject()` reconstruye el objeto completo que fue serializado, con todos sus
  campos originales, en una sola llamada.
- Funciona entre ejecuciones completamente distintas del programa: un proceso puede serializar y
  terminar; otro proceso, después, puede deserializar el mismo archivo.
- `readObject()` puede lanzar `ClassNotFoundException` (si la clase del objeto no está disponible) además
  de `IOException`; ambas se manejan explícitamente.

📎 Ver en la práctica: [Ejemplo 07 — Clase ObjectInputStream](07-clase-objectinputstream.md)

## 📋 Resumen de las herramientas de persistencia

| Herramienta | Qué resuelve | Cuándo usarla |
|---|---|---|
| `File` | Consultar y crear metadatos del sistema de archivos | Verificar si un archivo/directorio existe, listar contenido |
| `FileReader`/`BufferedReader` | Leer datos de texto plano | Datos simples, legibles como texto |
| `FileWriter`/`BufferedWriter` | Escribir datos de texto plano | Persistir datos simples, en formato legible |
| `File.delete()` | Borrar un archivo | Un archivo temporal que ya no hace falta |
| `Serializable` + `ObjectOutputStream` | Serializar un objeto completo | Persistir un objeto con varios campos, sin convertirlo a texto a mano |
| `ObjectInputStream` | Deserializar un objeto completo | Recuperar un objeto serializado, entre ejecuciones distintas |
