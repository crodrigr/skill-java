# ❓ Quiz 01 — Programación orientada a objetos (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿cuál de estas opciones describe mejor la programación orientada a
objetos frente al estilo usado en los Módulos 1 a 4?

- **A.** Escribir todo el programa dentro de `main`, con variables sueltas.
- **B.** Agrupar los datos y el comportamiento de un mismo concepto de negocio en una clase.
- **C.** Usar únicamente métodos estáticos.
- **D.** Evitar el uso de `new` en todo el programa.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La POO agrupa datos (atributos) y comportamiento (métodos) de un mismo
concepto en una clase, en vez de repetir variables sueltas para cada instancia.

</details>

---

**2. [Abierta]** ¿Cuál es una ventaja concreta de agrupar los datos de un paciente en una clase, en vez
de usar variables sueltas para cada paciente?

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Evita repetir las mismas variables (nombre, edad, historia clínica...) para cada paciente nuevo: agregar
un paciente es crear un objeto más, no declarar más variables ni repetir el código que las usa.

</details>

---

**3. [Selección]** Un programa declara `class Libro { ... }` y luego `Libro libro1 = new Libro();`.
**Pregunta:** ¿qué es `libro1`?

- **A.** Una clase.
- **B.** Un objeto (una instancia de `Libro`).
- **C.** Un atributo.
- **D.** Un método.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `libro1` es un objeto: una instancia concreta creada con `new Libro()`.

</details>

---

**4. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre clases y objetos son verdaderas?

- **A.** Una clase puede dar lugar a varios objetos distintos.
- **B.** Un objeto y su clase son la misma cosa.
- **C.** Cada objeto tiene su propia copia de los atributos de la clase.
- **D.** Una clase se declara una sola vez, aunque se creen muchos objetos de ella.

_RA: RA-3, RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: la clase es el diseño; el objeto es una instancia
concreta creada a partir de ella.

</details>

---

**5. [Selección]** **Pregunta:** ¿qué es un atributo de una clase?

- **A.** Un método que no devuelve ningún valor.
- **B.** Una variable declarada dentro de la clase que describe una característica de cada objeto.
- **C.** El nombre de la clase.
- **D.** Un argumento que se pasa al invocar un método.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un atributo describe una característica de cada objeto (por ejemplo,
`nombre` o `edad`).

</details>

---

**6. [Abierta]** Una clase `Producto` tiene el atributo público `precio` (`double`). Declara un método
de instancia `aplicarDescuento(double porcentaje)` que devuelva el precio con el descuento aplicado.

_RA: RA-5, RA-8_

<details>
<summary>🔑 Ver respuesta modelo</summary>

```java
public double aplicarDescuento(double porcentaje) {
    return precio - (precio * porcentaje);
}
```

Es un método de instancia (usa el atributo `precio` del objeto), con un parámetro y un tipo de retorno
`double`.

</details>

---

**7. [Selección múltiple]** Con `Prestamo prestamo = new Prestamo();`. **Pregunta:** ¿cuáles
invocaciones de un método `mostrarEstado()` (sin parámetros, con retorno `String`) son correctas?

- **A.** `prestamo.mostrarEstado();`
- **B.** `String estado = prestamo.mostrarEstado();`
- **C.** `Prestamo.mostrarEstado();`
- **D.** `mostrarEstado(prestamo);`

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** C y D no son válidas: `mostrarEstado()` es un método de instancia, se
invoca sobre un objeto con notación de punto, no sobre la clase ni como una función suelta.

</details>

---

**8. [Selección]** Una clase `Reporte` declara `public static void generarEncabezado()`.
**Pregunta:** ¿cuál es la forma correcta de invocarlo?

- **A.** `Reporte.generarEncabezado();`
- **B.** `new Reporte().generarEncabezado();` únicamente.
- **C.** `generarEncabezado();` sin ninguna referencia.
- **D.** No se puede invocar sin crear un objeto.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Un método estático se invoca sobre la clase, sin necesidad de crear un
objeto (aunque invocarlo sobre un objeto también compila, no es la forma recomendada).

</details>

---

**9. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones son verdaderas sobre invocar un método
estático desde un objeto?

- **A.** Es un error de compilación.
- **B.** Compila y se ejecuta igual que invocarlo sobre la clase.
- **C.** Es una mala práctica porque sugiere que el método depende del objeto.
- **D.** Por esta razón `main` se declara `static`.

_RA: RA-7, RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B y C.** A es falsa: Java lo permite, aunque no es recomendable. D es falsa en
este contexto: `main` es estático porque Java lo ejecuta sin haber creado antes ningún objeto, no por
esta mala práctica.

</details>

---

**10. [Selección]** **Pregunta:** ¿qué hace `new Paciente("Ana Torres", 34, "HC-2026-000123")`?

- **A.** Invoca un método estático de `Paciente`.
- **B.** Reserva memoria para un objeto nuevo y ejecuta su constructor.
- **C.** Modifica un objeto `Paciente` que ya existía.
- **D.** Declara la clase `Paciente`.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `new` crea un objeto nuevo y ejecuta su constructor, que inicializa sus
atributos.

</details>

---

**11. [Abierta]** Una clase `Producto` tiene los atributos públicos `nombre` (`String`) y `precio`
(`double`). Declara un constructor que reciba ambos como parámetros y los inicialice con `this`.
Explica qué recibe cada objeto creado con ese constructor.

_RA: RA-10, RA-11_

<details>
<summary>🔑 Ver respuesta modelo</summary>

```java
public Producto(String nombre, double precio) {
    this.nombre = nombre;
    this.precio = precio;
}
```

Cada objeto creado con `new Producto(...)` recibe sus propios valores de `nombre` y `precio`,
independientes de los de cualquier otro objeto `Producto` que se haya creado antes.

</details>

---

**12. [Selección múltiple]** **Pregunta:** ¿cuáles de estas situaciones son errores frecuentes de esta
etapa del curso?

- **A.** Un constructor con el nombre de la clase mal escrito.
- **B.** Crear un objeto con `new NombreDeClase()` sin ningún argumento cuando la clase no tiene
  constructor propio.
- **C.** Invocar un método sobre una variable que vale `null` porque no se creó ningún objeto.
- **D.** Declarar dos atributos públicos en la misma clase.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** B no es un error: es válido y usa el constructor por defecto. D
tampoco es un error: una clase puede tener varios atributos públicos.

</details>
