# 💡 Ejemplo 02 — Operadores de igualdad y relacionales

## 🌍 Contexto

Para que un programa decida, primero tiene que **comparar**. Los operadores de comparación
reciben dos valores y producen siempre un resultado `boolean`: `true` (verdadero) o `false`
(falso).

| Operador | Significa | Ejemplo | Resultado con `diasRetraso = 20` |
|---|---|---|---|
| `==` | igual a | `diasRetraso == 0` | `false` |
| `!=` | distinto de | `diasRetraso != 0` | `true` |
| `<` | menor que | `diasRetraso < 30` | `true` |
| `>` | mayor que | `diasRetraso > 20` | `false` |
| `<=` | menor o igual que | `diasRetraso <= 20` | `true` |
| `>=` | mayor o igual que | `diasRetraso >= 20` | `true` |

`==` y `!=` son los operadores de **igualdad**; los otros cuatro son **relacionales**.

Cuidado con dos signos que se parecen: **`=` asigna** un valor a una variable y **`==` compara**
dos valores. Para comparar el **contenido de un texto** no se usa `==` sino `equals`, como
verás abajo.

**Qué busca demostrar este ejemplo**: cómo comparar valores con cada operador, por qué elegir
entre `>` y `>=` cambia el resultado en el valor límite y qué errores produce confundir `=` con
`==`.

## 📚 Caso de estudio

La **Biblioteca Universitaria** controla los préstamos. Cada usuario puede tener como máximo 3
libros, y cada día de retraso genera una multa de $1.500 con un tope de $30.000. Vas a comparar
los datos de un usuario con esos límites y a mostrar cada resultado.

## 🌳 Árbol de archivos (como se vería en VS Code)

Agrega la clase `ControlPrestamo.java` al proyecto `Modulo02Decisiones`, en el paquete de la
biblioteca:

```text
Modulo02Decisiones
└── src
    └── com
        ├── biblioteca
        │   └── ControlPrestamo.java   ← nuevo en este ejemplo
        └── medisalud
            └── FacturaConsulta.java
```

## 💻 Archivo: ControlPrestamo.java

```java
package com.biblioteca;

public class ControlPrestamo {

    public static void main(String[] args) {
        // Datos de entrada
        final int MAX_LIBROS_PRESTAMO = 3;
        final double MULTA_POR_DIA = 1500.0;
        final double MULTA_MAXIMA = 30000.0;
        int librosPrestados = 3;
        int diasRetraso = 20;
        String tipoUsuario = "DOCENTE";

        double multa = diasRetraso * MULTA_POR_DIA;
        System.out.printf("Multa: %.0f%n", multa);

        // Igualdad: == y !=
        System.out.println("¿Está al día? " + (diasRetraso == 0));
        System.out.println("¿Tiene retraso? " + (diasRetraso != 0));

        // Para comparar texto se usa equals, no ==
        System.out.println("¿Es docente? " + tipoUsuario.equals("DOCENTE"));

        // Relacionales: <, >, <= y >=
        System.out.println("¿Le quedan libros por pedir? " + (librosPrestados < MAX_LIBROS_PRESTAMO));
        System.out.println("¿Superó el límite de libros? " + (librosPrestados > MAX_LIBROS_PRESTAMO));
        System.out.println("¿Alcanzó el límite de libros? " + (librosPrestados >= MAX_LIBROS_PRESTAMO));
        System.out.println("¿La multa supera el tope? " + (multa > MULTA_MAXIMA));
        System.out.println("¿La multa alcanza el tope? " + (multa >= MULTA_MAXIMA));
    }
}
```

## 🧭 Explicación paso a paso

1. `double multa = diasRetraso * MULTA_POR_DIA;` calcula la multa con lo que aprendiste en el
   Ejemplo 01: `20 * 1500.0 = 30000.0`.
2. `diasRetraso == 0` y `diasRetraso != 0` son las dos caras de la igualdad: el usuario está al
   día o tiene retraso. Se envuelven en paréntesis para que `+` una el texto con el resultado.
3. `tipoUsuario.equals("DOCENTE")` compara **lo que dice** el texto. Es la forma correcta de
   comparar textos; por ahora cópiala tal cual.
4. Con `librosPrestados = 3` y `MAX_LIBROS_PRESTAMO = 3`: `<` da `false` (ya no le quedan
   libros), `>` da `false` (no se pasó del límite) y `>=` da `true` (lo alcanzó).
5. Con la multa exactamente en el tope (`30000.0`): `multa > MULTA_MAXIMA` da `false` pero
   `multa >= MULTA_MAXIMA` da `true`. **Elegir `>` o `>=` cambia el resultado justo en el
   valor límite**, por eso siempre se prueba ese valor.

## ✅ Resultado esperado

```text
Multa: 30000
¿Está al día? false
¿Tiene retraso? true
¿Es docente? true
¿Le quedan libros por pedir? false
¿Superó el límite de libros? false
¿Alcanzó el límite de libros? true
¿La multa supera el tope? false
¿La multa alcanza el tope? true
```

## 🧪 Casos de prueba

Cambia `diasRetraso` en los datos de entrada y ejecuta de nuevo. El caso `20` es el valor
límite del tope:

| diasRetraso | Multa | ¿Supera el tope (`>`)? | ¿Alcanza el tope (`>=`)? |
|---|---|---|---|
| `0` | `0` | `false` | `false` |
| `1` | `1500` | `false` | `false` |
| `20` | `30000` | `false` | `true` |
| `21` | `31500` | `true` | `true` |

## 🔍 Análisis: errores frecuentes

**Error 1 — `=` en lugar de `==` con un entero (error de compilación).**

```java no-compila
package com.biblioteca;

public class ControlPrestamo {

    public static void main(String[] args) {
        int diasRetraso = 0;
        boolean estaAlDia = diasRetraso = 0;
        System.out.println("¿Está al día? " + estaAlDia);
    }
}
```

Mensaje del panel Problems (la posición se refiere al archivo completo):

```text
✖ Type mismatch: cannot convert from int to boolean Java(16777233) [Ln 7, Col 29]
```

`diasRetraso = 0` es una **asignación**, y su resultado es un número, no un `boolean`. Java lo
rechaza. Se corrige con `==`.

**Error 2 — `=` en lugar de `==` con un `boolean` (error lógico).** Aquí el error **compila**:

```java error-logico
package com.biblioteca;

public class ControlPrestamo {

    public static void main(String[] args) {
        boolean disponible = false;
        boolean estaDisponible = (disponible = true);
        System.out.println("¿Está disponible? " + estaDisponible);
        System.out.println("disponible ahora vale: " + disponible);
    }
}
```

```text
¿Está disponible? true
disponible ahora vale: true
```

`disponible = true` no compara: **cambia** `disponible` a `true` y entrega ese valor. El
programa da una respuesta que parece razonable y además modificó el dato. El panel Problems no
marca ningún problema. Se corrige con `disponible == true` (o, mejor, usando `disponible`
directamente).

**Error 3 — Un número no es una condición (error de compilación).**

```java no-compila
package com.biblioteca;

public class ControlPrestamo {

    public static void main(String[] args) {
        int librosPrestados = 2;
        boolean tienePrestamos = librosPrestados;
        System.out.println("¿Tiene préstamos? " + tienePrestamos);
    }
}
```

```text
✖ Type mismatch: cannot convert from int to boolean Java(16777233) [Ln 7, Col 34]
```

En Java, `0` no es "falso" ni un número distinto de cero es "verdadero". Hay que comparar:
`librosPrestados > 0`.

**Error 4 — Comparaciones encadenadas (error de compilación).** En matemáticas se escribe
`18 <= edad < 65`, pero en Java no:

```java no-compila
package com.biblioteca;

public class ControlPrestamo {

    public static void main(String[] args) {
        int edad = 30;
        boolean esAdulto = 18 <= edad < 65;
        System.out.println("¿Es adulto? " + esAdulto);
    }
}
```

```text
✖ The operator < is undefined for the argument type(s) boolean, int Java(536871072) [Ln 7, Col 28]
```

`18 <= edad` da un `boolean`, y ese `boolean` no se puede comparar con `65`. La forma correcta
usa un operador condicional, que verás en el Ejemplo 03: `edad >= 18 && edad < 65`.

**Error 5 — Comparar decimales con `==` (error lógico).**

```java error-logico
package com.biblioteca;

public class ControlPrestamo {

    public static void main(String[] args) {
        double multaParcial = 0.1;
        double multaRestante = 0.2;
        double multaTotal = 0.3;
        System.out.println("Suma: " + (multaParcial + multaRestante));
        System.out.println("¿Suma igual al total? " + (multaParcial + multaRestante == multaTotal));
    }
}
```

```text
Suma: 0.30000000000000004
¿Suma igual al total? false
```

Los decimales se guardan de forma aproximada: `0.1 + 0.2` no es exactamente `0.3`. El panel
Problems no marca ningún problema. Con decimales prefiere `<` o `>`, o usa enteros (por
ejemplo, pesos).

**Error 6 — Comparar texto con `==` (error lógico).**

```java error-logico
package com.biblioteca;

public class ControlPrestamo {

    public static void main(String[] args) {
        String prefijo = "Doc";
        String tipoUsuario = prefijo + "ente";
        System.out.println("Con ==: " + (tipoUsuario == "Docente"));
        System.out.println("Con equals: " + tipoUsuario.equals("Docente"));
    }
}
```

```text
Con ==: false
Con equals: true
```

Aquí el texto se arma al ejecutar el programa (como ocurre con los datos que llegan de otro
lugar). `==` pregunta si es **el mismo** texto en memoria, no si **dicen lo mismo**; por eso da
`false`. `equals` compara lo que dice y da `true`. El panel Problems no marca ningún problema.
**Para comparar texto usa siempre `equals`.**

## ❓ Preguntas de repaso

**1. [Selección]** Un usuario tiene `librosPrestados = 3` y el límite es
`MAX_LIBROS_PRESTAMO = 3`. **Pregunta:** ¿qué expresión da `true`?

- **A.** `librosPrestados > MAX_LIBROS_PRESTAMO`
- **B.** `librosPrestados < MAX_LIBROS_PRESTAMO`
- **C.** `librosPrestados >= MAX_LIBROS_PRESTAMO`
- **D.** `librosPrestados != MAX_LIBROS_PRESTAMO`

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** Los dos valores son iguales: `>=` da `true`, y `>`, `<` y `!=` dan
`false`.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿qué afirmaciones sobre `=` y `==` son verdaderas?

- **A.** `=` guarda un valor en una variable.
- **B.** `==` compara dos valores y da un `boolean`.
- **C.** `if (x = 5)` con `int x` compila.
- **D.** Para comparar el contenido de un texto se usa `equals`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: con un `int` el resultado de `x = 5` es un
número, no un `boolean`, y el compilador lo rechaza.

</details>

**3. [Abierta]** ¿Por qué es peligroso escribir `disponible = true` cuando se quería comparar,
si `disponible` es un `boolean`?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque compila. En lugar de comparar, asigna `true` a la variable, entrega ese valor y además
**modifica el dato**. El programa no muestra ningún error y responde de forma incorrecta, así
que hay que descubrirlo probando el programa con casos de prueba.

</details>
