# 🔴 Avanzado 01 — Corregir un programa con errores

## 🧩 Problema

Un desarrollador junior de **MediSalud** escribió el programa `FichaConErrores`, que
debería guardar los datos de un paciente y mostrar su nombre y si es afiliado. No
compila. Tu tarea es corregirlo hasta que compile y explicar cada error.

## 💻 Código o contexto de partida

Creá un proyecto `FichaConErrores` (**Java: Create Java Project... → No build tools**)
con la clase `com.medisalud.FichaConErrores` y pegá este código. **No compila**:

```java
package com.medisalud;

public class FichaConErrores {

    public static void main(String[] args) {
        final double TASA_IVA = 0.19;
        String nombrePaciente = "Ana Torres";
        int edadPaciente = "34";
        long numeroHistoria = 9876543210;
        char grupoSanguineo = "O";
        int pesoKg = 62.5;
        boolean esAfiliado;

        TASA_IVA = 0.21;
        System.out.printn(nombrePaciente);
        System.out.println(esAfiliado);
    }
}
```

Hacé lo siguiente:

1. Abrí el panel **Problems** (`Ctrl+Shift+M`) y **anotá los mensajes** que muestra.
2. Corregí los errores y mirá cómo cambia el panel. **Repetí** hasta que no queden
   errores rojos.
3. El compilador no siempre muestra todos los errores de una vez: cada vez que corrijas,
   anotá **cuántos errores** informa.
4. Cuando no queden errores, ejecutá el programa: debe mostrar el nombre del paciente y
   si es afiliado (`Ana Torres` y `true`).
5. Al final pueden quedar líneas **amarillas**. Explicá qué son y por qué aparecen.

Para **cada error** que encuentres, explicá con tus palabras:

- La **causa** (qué está mal).
- La **corrección** (qué cambiaste y por qué).

## 📏 Criterios de evaluación de la solución

- Se corrigen los siete errores: el entero sin `L`, el texto asignado a un `int`, las
  comillas dobles de un `char`, el decimal en un `int`, la constante reasignada, el
  nombre incorrecto de la instrucción de impresión y la variable sin inicializar.
- Cada corrección es la adecuada (por ejemplo, para el peso se cambia el **tipo** a
  `double`, no el valor a un entero).
- Las causas se explican con criterio, no solo repitiendo el mensaje del compilador.
- Se observa que el compilador informa los errores en **rondas** y se explica que
  corregir unos errores puede hacer aparecer otros.
- Se distingue un **error** (rojo, impide ejecutar) de una **advertencia** (amarillo, no
  impide ejecutar) y se explica la causa de las advertencias que queden.
- El programa corregido compila y muestra la salida indicada.

## 🚧 Restricciones

- No elimines variables ni instrucciones para "evitar" un error: hay que corregirlo.
- Conservá la constante `TASA_IVA` como constante.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

RA-7, RA-9, RA-10, RA-11
