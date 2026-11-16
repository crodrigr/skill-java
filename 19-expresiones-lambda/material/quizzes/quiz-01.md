# ❓ Quiz 01 — Expresiones Lambda (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué es una expresión lambda?

- **A.** Un tipo de dato nuevo de Java, distinto de una clase o una interfaz.
- **B.** Una forma concisa de implementar una interfaz de un único método abstracto.
- **C.** Un mecanismo exclusivo de las interfaces `Consumer` y `Supplier`.
- **D.** Una forma de declarar una clase sin nombre.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Una expresión lambda implementa una interfaz funcional (de un único método
abstracto) de forma concisa; no es un tipo de dato nuevo ni está limitado a interfaces específicas.

</details>

---

**2. [Abierta]** **Pregunta:** dada una lista de libros, ¿cómo usarías `Consumer<LibroBiblioteca>` con
una expresión lambda para imprimir el título de cada uno?

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Consumer<LibroBiblioteca> accion = libro -> System.out.println(libro.getTitulo());
for (LibroBiblioteca libro : libros) {
    accion.accept(libro);
}
```

`Consumer<T>` recibe el valor con `accept(T t)` y ejecuta una acción, sin devolver nada.

</details>

---

**3. [Abierta]** **Pregunta:** ¿cómo usarías `Supplier<String>` con una expresión lambda para generar un
código de préstamo, sin recibir ningún parámetro de entrada?

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Supplier<String> generador = () -> "PRESTAMO-" + (++contador);
String codigo = generador.get();
```

`Supplier<T>` declara `T get()`: no recibe ningún parámetro y devuelve un valor de tipo `T`.

</details>

---

**4. [Abierta]** **Pregunta:** ¿cómo usarías `Function<LibroBiblioteca, String>` con una expresión
lambda para transformar un libro en una línea de recibo (`"titulo - autor"`)?

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Function<LibroBiblioteca, String> formateador = l -> l.getTitulo() + " - " + l.getAutor();
String recibo = formateador.apply(libro);
```

`Function<T, R>` declara `R apply(T t)`: recibe un valor de tipo `T` y devuelve un valor de tipo `R`.

</details>

---

**5. [Abierta]** **Pregunta:** ¿cómo usarías `Predicate<LibroBiblioteca>` con una expresión lambda para
filtrar los libros disponibles de una lista?

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

```java
Predicate<LibroBiblioteca> condicion = libro -> libro.isDisponible();
for (LibroBiblioteca libro : libros) {
    if (condicion.test(libro)) {
        System.out.println(libro.getTitulo());
    }
}
```

`Predicate<T>` declara `boolean test(T t)`: recibe un valor y evalúa una condición.

</details>

---

**6. [Abierta]** **Pregunta:** dado un caso que recibe dos parámetros de tipos distintos y devuelve un
tercer tipo, que no encaja en `Consumer`/`Supplier`/`Function`/`Predicate`, ¿cómo diseñarías una interfaz
funcional propia para resolverlo?

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

```java
@FunctionalInterface
public interface DescriptorPrestamo {
    String describir(String titulo, String socio);
}
```

```java
// dentro de Demo
DescriptorPrestamo descriptor = (titulo, socio) -> titulo + " prestado a " + socio;
```

Se declara una interfaz nueva con un único método abstracto, anotada `@FunctionalInterface`, y se
implementa con una expresión lambda.

</details>

---

**7. [Abierta, integradora]** **Pregunta:** te dan un problema nuevo que necesita filtrar valores según
una condición, transformarlos, y combinar dos de ellos en un resultado final. ¿Qué interfaz funcional
usarías para cada parte, y por qué?

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Filtrar según una condición: `Predicate<T>`. Transformar un valor en otro: `Function<T, R>`. Combinar dos
valores de tipos distintos en un resultado: ninguna interfaz estándar encaja (todas reciben como máximo
un parámetro), así que hace falta diseñar una interfaz funcional propia, anotada
`@FunctionalInterface`, con un único método abstracto que reciba los dos valores.

</details>

---
