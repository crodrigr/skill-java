# 🔴 Avanzado 01 — Corregir un programa con errores de texto

## 🧩 Problema

Un compañero escribió una revisión de catálogo para la **Biblioteca Universitaria**, pero el programa
tiene errores. Debe comparar dos títulos, calcular los cupos reales de una sala y mostrar la última
letra de un título.

Resuélvelo en **tres rondas**:

- **Ronda 1 — el editor te avisa.** Copia el código en VS Code y corrige el error que marca el panel
  **Problems** (`Ctrl+Shift+M`). Anota qué dice el mensaje.
- **Ronda 2 — la salida no coincide.** Cuando el programa compile y se ejecute, compara su salida con
  la esperada (más abajo). El panel Problems no te va a avisar de este error: encuéntralo tú.
- **Ronda 3 — el programa se detiene.** Con el error anterior corregido, ejecuta de nuevo.

  > ⚠️ **Advertencia**: en esta ronda el programa se detiene con un error real. No es un fallo de tu
  > equipo: hay un índice fuera de rango. Lee el mensaje, encuentra la línea responsable y corrígela.

## 💻 Código o contexto de partida

```java no-compila
package com.biblioteca;

public class RevisionCatalogo {

    public static void main(String[] args) {
        // Datos de entrada
        String tituloGuardado = "Cien años de soledad";
        String tituloConsultado = new String("Cien años de soledad");
        double cupos = "29.8";

        boolean mismoTitulo = tituloGuardado == tituloConsultado;
        int cuposReales = (int) cupos;
        char ultimaLetra = tituloGuardado.charAt(tituloGuardado.length());

        System.out.println("¿Mismo título? " + mismoTitulo);
        System.out.println("Cupos reales: " + cuposReales);
        System.out.println("Última letra: " + ultimaLetra);
    }
}
```

## 🧪 Casos de prueba

Salida que debe mostrar el programa corregido:

```text
¿Mismo título? true
Cupos reales: 29
Última letra: d
```

## 📏 Criterios de evaluación de la solución

- El programa compila, termina y muestra exactamente la salida esperada.
- El error de compilación se corrige entendiendo el mensaje, no probando al azar.
- Se identifica que la comparación de títulos usaba `==` en vez de `equals`, y se explica por qué daba
  `false` aunque los títulos "dijeran lo mismo".
- Se corrige el índice de más en `charAt` y se explica por qué el resultado correcto usa
  `length() - 1`.
- Se explica que ni `charAt` ni la comparación modifican `tituloGuardado` ni `tituloConsultado`: una
  cadena nunca cambia después de creada.

## 🚧 Restricciones

- No elimines ninguna de las tres operaciones del programa: corrígelas.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-5**: comparar cadenas con `equals` y no con `==`.
- **RA-9**: aplicar una conversión explícita correctamente.
- **RA-12**: reconocer que una cadena es inmutable: ningún método usado cambia su valor.
- **RA-15**: identificar y corregir errores de compilación, errores lógicos y fallas en ejecución.
