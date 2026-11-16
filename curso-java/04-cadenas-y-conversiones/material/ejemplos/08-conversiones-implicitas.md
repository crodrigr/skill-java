# 💡 Ejemplo 08 — Conversiones implícitas

## 🌍 Contexto

Una **conversión implícita** ocurre cuando Java cambia el tipo de un valor **por sí solo**, sin que se
lo pidas. Pasa cuando el tipo destino puede contener cualquier valor del tipo de origen sin perder
información:

```text
byte → short → int → long → float → double
```

Cada tipo a la derecha "cabe" a los de su izquierda. Un `int` se convierte solo a `double` (ya lo viste
en el Módulo 2 con `x += 2.5`); un `char` se convierte solo a `int` (su valor numérico Unicode).

**Qué busca demostrar este ejemplo**: cuándo Java convierte un valor sin que lo pidas, y qué tipo
resulta cuando una expresión mezcla `int` y `double`.

## 📚 Caso de estudio

La **Biblioteca Universitaria** calcula el promedio de préstamos por usuario activo.

## 🌳 Árbol de archivos (como se vería en VS Code)

Agrega la clase `PromedioDePrestamos.java` al paquete `com.biblioteca`:

```text
Modulo04CadenasConversiones
└── src
    └── com
        ├── biblioteca
        │   ├── ConteoDeLetras.java
        │   ├── LongitudDelTitulo.java
        │   └── PromedioDePrestamos.java   ← nuevo en este ejemplo
        └── medisalud
            └── (...)
```

## 💻 Archivo: PromedioDePrestamos.java

```java
package com.biblioteca;

public class PromedioDePrestamos {

    public static void main(String[] args) {
        // Datos de entrada
        int prestamosTotales = 17;
        int usuariosActivos = 5;

        // int -> double: conversión implícita, sin pérdida
        double promedio = (double) prestamosTotales / usuariosActivos;
        System.out.println("Promedio de préstamos: " + promedio);

        // char -> int: conversión implícita
        char categoria = 'N';
        int codigoAscii = categoria;
        System.out.println("Código de la categoría '" + categoria + "': " + codigoAscii);

        // Mezclar int y double en una expresión: el resultado es double
        int librosNuevos = 3;
        double promedioConNuevos = (prestamosTotales + librosNuevos) / (double) usuariosActivos;
        System.out.println("Promedio con libros nuevos: " + promedioConNuevos);
    }
}
```

## 🧭 Explicación paso a paso

1. `(double) prestamosTotales / usuariosActivos` convierte primero `prestamosTotales` a `double`
   (conversión **explícita**, con `cast`), y esa conversión "arrastra" a `double` toda la división.
2. `int codigoAscii = categoria;` convierte el `char` a su valor numérico Unicode **sin pedirlo**: es
   una conversión implícita. `'N'` vale `78`.
3. `(prestamosTotales + librosNuevos) / (double) usuariosActivos`: la suma entre dos `int` da un `int`;
   pero al dividir por un valor convertido a `double`, el resultado completo pasa a ser `double`.

## ✅ Resultado esperado

```text
Promedio de préstamos: 3.4
Código de la categoría 'N': 78
Promedio con libros nuevos: 4.0
```

## 🧪 Casos de prueba

| usuariosActivos | Promedio (con 17 préstamos) |
|---|---|
| `1` | `17.0` |
| `5` | `3.4` |
| `17` | `1.0` |

## 🔍 Análisis: errores frecuentes

No hay un error nuevo que demostrar aquí: las conversiones implícitas no pierden datos por sí mismas
(van siempre "hacia arriba" en la escalera de tamaños). Los riesgos aparecen al forzar una conversión
en el sentido contrario, que es justamente el tema del Ejemplo 09.

## ❓ Preguntas de repaso

**1. [Selección]** Se ejecuta `int a = 5; double b = 2.0; double resultado = a / b;`. **Pregunta:**
¿qué tipo tiene `a / b`, y qué valor da?

- **A.** `int`, `2`
- **B.** `double`, `2.5`
- **C.** `int`, `2.5` (no compila)
- **D.** `double`, `2`

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Al mezclar `int` y `double`, Java convierte el `int` a `double` de forma
implícita: el resultado es `double` y conserva los decimales.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre las conversiones implícitas son
verdaderas?

- **A.** Ocurren automáticamente, sin que el programador las pida.
- **B.** Un `char` puede convertirse implícitamente a `int`.
- **C.** Un `double` se convierte implícitamente a `int` sin pedirlo.
- **D.** No pierden información.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: ir de `double` a `int` es una conversión **explícita**
(con `cast`), porque se pueden perder los decimales; Java nunca la hace sola.

</details>

**3. [Abierta]** ¿Por qué Java permite convertir un `int` a `double` sin pedirlo, pero no al revés?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque todo valor `int` cabe sin problema en un `double` (no se pierde información al "subir" en la
escalera de tamaños). Ir de `double` a `int`, en cambio, puede perder los decimales, así que Java exige
pedirlo explícitamente con un `cast`, para que quede claro que es una decisión del programador.

</details>
