# ❓ Quiz 01 — Stream API (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿cuál de las siguientes NO es una característica de un `Stream`?

- **A.** No almacena datos por sí mismo.
- **B.** No modifica su fuente original.
- **C.** Puede recorrerse las veces que haga falta, igual que una `List`.
- **D.** Puede evaluarse de forma perezosa.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** Un stream es de un solo uso: intentar recorrerlo una segunda vez produce un
error real (`IllegalStateException`).

</details>

---

**2. [Abierta]** **Pregunta:** ¿cómo crearías un `Stream<String>` con `Stream.of()` a partir de los
valores sueltos `"a"`, `"b"` y `"c"`?

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Stream<String> stream = Stream.of("a", "b", "c");
```

`Stream.of(valores...)` crea un stream directamente a partir de valores sueltos.

</details>

---

**3. [Abierta]** **Pregunta:** dado un arreglo `String[] nombres`, ¿cómo crearías un `Stream<String>` a
partir de él?

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Stream<String> stream = Arrays.stream(nombres);
```

`Arrays.stream(arreglo)` crea un stream a partir de un arreglo ya existente.

</details>

---

**4. [Abierta]** **Pregunta:** ¿cómo crearías un `Stream<String>` agregando elementos uno por uno con
`Stream.<String>builder()`?

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Stream<String> stream = Stream.<String>builder()
        .add("a")
        .add("b")
        .build();
```

`Stream.<T>builder()` permite agregar elementos uno por uno con `.add(...)`, y construir el stream al
final con `.build()`.

</details>

---

**5. [Abierta]** **Pregunta:** dada una `List<String> nombres`, ¿cómo crearías un `Stream<String>` a
partir de ella?

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Stream<String> stream = nombres.stream();
```

Toda colección que implementa `Collection` (como `List`) tiene un método `.stream()`.

</details>

---

**6. [Abierta]** **Pregunta:** ¿cómo usarías `map` con una expresión lambda para transformar cada
`LibroCatalogo` de un stream en su título en mayúsculas?

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Stream<String> titulos = libros.stream().map(libro -> libro.getTitulo().toUpperCase());
```

`map` transforma cada elemento con la `Function` recibida, produciendo un nuevo stream con la misma
cantidad de elementos.

</details>

---

**7. [Abierta]** **Pregunta:** ¿cómo usarías `filter` con una expresión lambda para seleccionar, de un
stream de libros, solo los que están disponibles?

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Stream<LibroCatalogo> disponibles = libros.stream().filter(libro -> libro.isDisponible());
```

`filter` selecciona, con el `Predicate` recibido, los elementos que cumplen la condición.

</details>

---

**8. [Abierta]** **Pregunta:** ¿cómo usarías `anyMatch` con una expresión lambda para verificar si
existe al menos un libro de cierto autor en un stream?

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

```java
boolean hayCervantes = libros.stream().anyMatch(libro -> libro.getAutor().equals("Cervantes"));
```

`anyMatch` es una operación terminal: devuelve `true` si al menos un elemento cumple el `Predicate`
recibido.

</details>

---

**9. [Abierta]** **Pregunta:** dada una `List<List<LibroCatalogo>>` con las estanterías de una
biblioteca, ¿cómo usarías `flatMap` para obtener un único `Stream<LibroCatalogo>` con todos los
libros?

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Stream<LibroCatalogo> todos = estanterias.stream()
        .flatMap(estanteria -> estanteria.stream());
```

`flatMap` transforma cada elemento (cada estantería) en un stream, y combina todos esos streams en
uno solo, aplanando la estructura anidada. Usar `map` en su lugar dejaría la estructura anidada
intacta (un `Stream<List<LibroCatalogo>>`).

</details>

---

**10. [Abierta]** **Pregunta:** ¿cómo convertirías una `List<LibroCatalogo>` en una nueva
`List<String>` con los títulos de los libros de más de 300 páginas, encadenando `stream()`, `filter`,
`map` y una conversión final de vuelta a `List`?

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

```java
List<String> titulos = libros.stream()
        .filter(libro -> libro.getPaginas() > 300)
        .map(libro -> libro.getTitulo())
        .toList();
```

`.stream()` crea el stream a partir de la `List`; `filter` y `map` lo transforman; `.toList()` cierra
el ciclo, convirtiéndolo de vuelta en una `List<String>` nueva.

</details>

---

**11. [Abierta]** **Pregunta:** te piden diseñar un pipeline de Stream API que, dada una
`List<List<PacienteMuestra>>` de consultorios, genere una `List<String>` con el nombre de los pacientes
mayores de 40 años de todos los consultorios juntos. ¿Qué operadores encadenarías, y en qué orden?

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

```java
List<String> nombres = consultorios.stream()
        .flatMap(consultorio -> consultorio.stream())
        .filter(paciente -> paciente.getEdad() >= 40)
        .map(paciente -> paciente.getNombre())
        .toList();
```

Primero `flatMap` para aplanar la estructura anidada en un único stream de pacientes; después
`filter` para seleccionar solo los mayores de 40; después `map` para quedarse con el nombre; y por
último `.toList()` para cerrar el ciclo `List → Stream → List`. El orden importa: `filter`/`map` deben
ir después de aplanar, porque operan sobre pacientes individuales, no sobre listas de pacientes.

</details>

---
