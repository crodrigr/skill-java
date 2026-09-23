# 📚 Explicación conceptual — Módulo 8

## 🧠 Concepto: Estructuras de datos

Una estructura de datos organiza varios valores relacionados para almacenarlos y procesarlos como un
conjunto, en vez de como variables independientes.

- Cuando varios datos son del mismo tipo y están relacionados, declarar una variable distinta por cada
  uno obliga a repetir código y no escala si la cantidad de datos crece.
- Una estructura de datos (un arreglo, o más adelante una `List`) permite acceder a cada valor por su
  posición, sin declarar una variable nueva por cada dato.
- La ventaja no es "menos código" en un ejemplo pequeño, sino que el programa escale cuando la cantidad
  de datos cambia en el tiempo.

📎 Ver en la práctica: [Ejemplo 01 — Estructuras de datos](01-estructuras-de-datos.md)

## 🧠 Concepto: Asignación de valores a un arreglo

- Un literal (`int[] edades = {34, 41, 29};`) declara el arreglo y le asigna todos sus valores en la
  misma línea.
- `new int[3]` reserva espacio para 3 elementos, inicializados con el valor por defecto del tipo (`0`
  para `int`), sin asignarles todavía un valor útil.
- La asignación por índice (`edades[0] = 34;`) asigna una posición a la vez, útil cuando los valores se
  conocen después de crear el arreglo (por ejemplo, dentro de un bucle).

📎 Ver en la práctica: [Ejemplo 02 — Asignación de valores a un arreglo](02-asignacion-de-valores.md)

## 🧠 Concepto: Acceso a elementos del arreglo

- Cada elemento se lee o se escribe con su índice entre corchetes; el primer elemento está en el
  índice `0`, el último en `.length - 1`.
- Acceder a un índice fuera de ese rango no lo detecta el compilador ni el panel Problems: el programa
  compila sin problema.
- El error se manifiesta **en tiempo de ejecución**, como una excepción real
  (`ArrayIndexOutOfBoundsException`) que termina el programa, sin capturarla (`try`/`catch` queda para
  un módulo posterior).

📎 Ver en la práctica: [Ejemplo 03 — Acceso a elementos del arreglo](03-acceso-a-elementos.md)

## 🧠 Concepto: Obtener el tamaño del arreglo

- `.length` es un **atributo** (sin paréntesis) que devuelve la cantidad de elementos de un arreglo.
- Es fijo desde que el arreglo se crea: no cambia durante la ejecución del programa.
- `arreglo[arreglo.length - 1]` accede al último elemento sin depender de un índice literal.
- No confundir con `.length()` (método, de `String`) ni con `.size()` (método, de `List`).

📎 Ver en la práctica: [Ejemplo 04 — Obtener el tamaño del arreglo](04-tamano-del-arreglo.md)

## 🧠 Concepto: Iteración de un arreglo

- Un `for` indexado recorre el arreglo con un índice `i`, usando `.length` como límite
  (`i < arreglo.length`, nunca `<=`).
- Un `for-each` (`for (Tipo x : arreglo)`) recorre los elementos directamente, sin manejar un índice.
- Ambas formas visitan los mismos elementos en el mismo orden.
- El `for` indexado sigue siendo necesario cuando se necesita el índice (modificar el arreglo, comparar
  posiciones); el `for-each` es más simple para solo leer cada elemento.

📎 Ver en la práctica: [Ejemplo 05 — Iteración de un arreglo](05-iteracion-de-un-arreglo.md)

## 🧠 Concepto: Arreglos multidimensionales

- Un arreglo multidimensional es un arreglo cuyos elementos son, a su vez, arreglos; el caso más común
  es la matriz de dos dimensiones (`int[][]`).
- `matriz[fila][columna]`: el primer índice es la fila, el segundo la columna.
- Recorrer una matriz completa necesita dos bucles anidados: uno para las filas
  (`matriz.length`), otro para las columnas de cada fila (`matriz[fila].length`).

📎 Ver en la práctica: [Ejemplo 06 — Arreglos multidimensionales](06-arreglos-multidimensionales.md)

## 🧠 Concepto: Jerarquía de colecciones

- El framework de colecciones (`java.util`) ofrece estructuras de datos de tamaño dinámico, alternativa
  a los arreglos cuando la cantidad de elementos cambia en tiempo de ejecución.
- `Collection` es la interfaz base; `List`, `Set` y `Map` son sus tres grandes familias (este módulo
  solo profundiza en `List`).
- `ArrayList` y `LinkedList` son dos clases distintas que implementan la **misma** interfaz `List`.
- `List` es una interfaz: no se puede instanciar directamente (`new List<>()` no compila); siempre se
  instancia una implementación concreta.

📎 Ver en la práctica: [Ejemplo 07 — Jerarquía de colecciones](07-jerarquia-de-colecciones.md)

## 🧠 Concepto: ArrayList

- `ArrayList` es una implementación de `List` respaldada por un arreglo interno que crece
  automáticamente.
- Operaciones básicas: `add(...)` agrega al final; `get(indice)` lee por índice; `remove(...)` elimina;
  `size()` devuelve la cantidad actual de elementos (método, con paréntesis).
- Acceso por índice eficiente, igual que un arreglo, pero con tamaño dinámico.
- Un índice fuera de rango con `get(...)` lanza una excepción real (`IndexOutOfBoundsException`), sin
  detectarse antes de ejecutar.

📎 Ver en la práctica: [Ejemplo 08 — ArrayList](08-arraylist.md)

## 🧠 Concepto: LinkedList

- `LinkedList` es otra implementación de `List`, respaldada por nodos enlazados entre sí.
- Comparte las mismas operaciones básicas que `ArrayList` (`add`, `get`, `remove`, `size`), además de
  `addFirst()`/`removeFirst()`.
- Es especialmente eficiente para insertar o eliminar elementos al principio o al final; menos
  eficiente que `ArrayList` para acceder por índice en el medio de la lista.
- La elección entre `ArrayList` y `LinkedList` depende del patrón de uso: más lecturas por índice
  (`ArrayList`) o más inserciones/eliminaciones en los extremos (`LinkedList`).

📎 Ver en la práctica: [Ejemplo 09 — LinkedList](09-linkedlist.md)

## 🧠 Concepto: Clases genéricas

- Una clase genérica declara un **parámetro de tipo** (`T`, por convención) en vez de fijar de antemano
  el tipo de uno de sus atributos.
- La misma clase sirve para guardar cualquier tipo de referencia; el tipo concreto se fija al
  instanciarla (`new Caja<String>()`, `new Caja<Integer>()`), sin duplicar la clase por cada tipo.
- Un método de la clase (`getContenido()`/`setContenido(T)`) usa `T` como cualquier otro tipo: el
  compilador verifica que cada instancia solo reciba y devuelva su propio tipo concreto.
- Al igual que en `List<T>`, el argumento de tipo debe ser un tipo de referencia, nunca un primitivo.

📎 Ver en la práctica: [Ejemplo 10 — Clases genéricas](10-clases-genericas.md)
