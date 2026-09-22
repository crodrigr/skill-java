# 💡 Ejemplo 06 — Conversión de cadenas

## 🌍 Contexto

"Convertir" una cadena significa **transformar su contenido**. Cada uno de estos métodos devuelve una
cadena **nueva**; nunca cambian la cadena original:

- `.trim()`: quita los espacios de los extremos (no los de en medio).
- `.toUpperCase()` / `.toLowerCase()`: cambia mayúsculas y minúsculas.
- `.replace(viejo, nuevo)`: reemplaza todas las apariciones de un texto por otro.
- `String.valueOf(valor)`: convierte cualquier valor (un número, un `boolean`) a texto.

**Qué busca demostrar este ejemplo**: cómo normalizar un texto combinando estos métodos, y qué ocurre
si olvidas guardar el resultado que devuelven (la inmutabilidad que ya viste con `toUpperCase` en el
Módulo 1, ahora explicada como parte de un concepto más amplio, que se retoma en el Ejemplo 11).

## 🏥 Caso de estudio

**MediSalud** recibe el nombre de un paciente con espacios de más y sin un formato consistente de
mayúsculas. Vas a normalizarlo.

## 🌳 Árbol de archivos (como se vería en VS Code)

Agrega la clase `NormalizarNombre.java` al paquete `com.medisalud`:

```text
Modulo04CadenasConversiones
└── src
    └── com
        ├── biblioteca
        │   ├── ConteoDeLetras.java
        │   └── LongitudDelTitulo.java
        └── medisalud
            ├── CodigoDeCita.java
            ├── CreacionDeCadenas.java
            ├── NormalizarNombre.java   ← nuevo en este ejemplo
            ├── PartesDeLaHistoriaClinica.java
            └── VerificarPlan.java
```

## 💻 Archivo: NormalizarNombre.java

```java
package com.medisalud;

public class NormalizarNombre {

    public static void main(String[] args) {
        // Datos de entrada
        String nombreSucio = "  ana maría torres  ";

        String sinEspacios = nombreSucio.trim();
        String primeraLetra = sinEspacios.substring(0, 1).toUpperCase();
        String resto = sinEspacios.substring(1).toLowerCase();
        String nombreNormalizado = primeraLetra + resto;

        System.out.println("[" + nombreSucio + "]");
        System.out.println("[" + sinEspacios + "]");
        System.out.println(nombreNormalizado);

        String conDobleEspacio = "Ana  Torres";
        String sinDobleEspacio = conDobleEspacio.replace("  ", " ");
        System.out.println(sinDobleEspacio);

        int edad = 34;
        String edadTexto = String.valueOf(edad);
        System.out.println("La edad como texto tiene " + edadTexto.length() + " caracteres");
    }
}
```

## 🧭 Explicación paso a paso

1. `nombreSucio.trim()` quita los espacios de los extremos, pero conserva el que separa "ana" de
   "maría torres".
2. `sinEspacios.substring(0, 1).toUpperCase()` toma la primera letra y la pone en mayúscula.
3. `sinEspacios.substring(1).toLowerCase()` toma el resto y lo pone en minúscula (por si venía con
   alguna mayúscula de más).
4. Sumar ambas partes da el nombre con formato: `"Ana maría torres"` (la mayúscula solo de la
   primera letra, tal como pide la regla).
5. `.replace("  ", " ")` reemplaza un espacio doble por uno simple.
6. `String.valueOf(edad)` convierte el número `34` al texto `"34"`, que tiene 2 caracteres.

## ✅ Resultado esperado

```text
[  ana maría torres  ]
[ana maría torres]
Ana maría torres
Ana Torres
La edad como texto tiene 2 caracteres
```

## 🧪 Casos de prueba

| Nombre de entrada | Resultado normalizado |
|---|---|
| `"lucía"` (una sola palabra) | `"Lucía"` |
| `"  ANA  "` (espacios extra) | `"Ana"` |

## 🔍 Análisis: errores frecuentes

**Error — Ignorar el valor que devuelve el método (error lógico).**

```java error-logico
package com.medisalud;

public class NormalizarNombre {

    public static void main(String[] args) {
        String nombreSucio = "  ana torres  ";
        nombreSucio.trim();
        System.out.println("[" + nombreSucio + "]");
    }
}
```

```text
[  ana torres  ]
```

`nombreSucio.trim();` calcula una cadena nueva sin espacios, pero **no la guarda en ningún lado**, así
que `nombreSucio` sigue teniendo los espacios. El panel Problems no marca ningún problema. Este error se
explica del todo en el Ejemplo 11: ninguno de estos métodos cambia la cadena original, porque `String`
es **inmutable**.

## ❓ Preguntas de repaso

**1. [Selección]** Se ejecuta `String s = "  hola  "; s.trim();`. **Pregunta:** ¿qué vale `s` después
de esa línea?

- **A.** `"hola"`
- **B.** `"  hola  "` (sin cambios)
- **C.** `""`
- **D.** Da un error de compilación.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `trim()` devuelve una cadena nueva; como no se guarda en ningún lado, `s`
sigue igual que antes.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones son verdaderas?

- **A.** `trim()` quita los espacios de los extremos, pero no los del medio.
- **B.** `toUpperCase()` cambia la cadena original.
- **C.** `String.valueOf(42)` da el texto `"42"`.
- **D.** `replace` reemplaza todas las apariciones del texto buscado.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: `toUpperCase()` devuelve una cadena nueva; la original
no cambia.

</details>

**3. [Abierta]** ¿Qué tienen en común `trim`, `toUpperCase`, `toLowerCase` y `replace` respecto de la
cadena sobre la que se llaman?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Ninguno de los cuatro cambia la cadena original: todos devuelven una cadena **nueva** con el resultado
de la transformación. Para conservar el resultado hay que asignarlo a una variable (la misma u otra).

</details>
