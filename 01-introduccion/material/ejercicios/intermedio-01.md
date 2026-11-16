# 🟡 Intermedio 01 — Bienvenida de la clínica

## 🧩 Problema

La sede Centro de **MediSalud** quiere un programa de consola que muestre, al iniciar,
los datos básicos de la clínica. Vas a crear el proyecto en VS Code, completar el
código de partida, ejecutarlo y practicar la lectura de un error de compilación.

## 💻 Código o contexto de partida

**Paso 1.** Creá un proyecto **Java: Create Java Project... → No build tools** llamado
`BienvenidaClinica`. Dentro de `src`, creá la carpeta `com/medisalud` y el archivo
`BienvenidaClinica.java`, y borrá `App.java` (como en el Ejemplo 05).

**Paso 2.** Reemplazá el contenido de la clase por este código de partida. Ya compila,
pero todavía no muestra nada:

```java
package com.medisalud;

public class BienvenidaClinica {

    public static void main(String[] args) {
        // TODO 1: mostrar el mensaje  Clínica MediSalud - Sede Centro
        // TODO 2: mostrar el mensaje  Atención: lunes a viernes de 7:00 a 19:00
        // TODO 3: mostrar el mensaje  Gracias por elegirnos
    }
}
```

> 📝 El panel **Problems** lista los comentarios `TODO` como mensajes de **información**
> (ícono azul ℹ). No son errores: desaparecen cuando reemplazás los comentarios.

**Paso 3.** Completá los tres `TODO` para que cada mensaje aparezca en una línea propia
de la terminal, y ejecutá el proyecto con **Run**.

**Paso 4.** Borrá a propósito el punto y coma de la segunda instrucción y anotá lo que
muestra el panel **Problems**: el texto del error, la línea y la columna. Después
corregilo y volvé a ejecutar.

## 📏 Criterios de evaluación de la solución

- El proyecto se llama `BienvenidaClinica`, con el paquete `com.medisalud` y la clase
  principal `BienvenidaClinica` en `src/com/medisalud`, sin espacios ni tildes en los
  nombres.
- Los tres mensajes aparecen exactamente como se piden, cada uno en su propia línea.
- Cada instrucción está dentro de `main` y termina con punto y coma.
- Se identifica correctamente, en el paso 4, la línea y la columna del error y que el
  problema es un punto y coma faltante (`insert ";"`).
- Tras corregir el error, el programa vuelve a mostrar la salida esperada.

## 🚧 Restricciones

- Usá únicamente `System.out.println`.
- No cambies el nombre del paquete ni de la clase.

## 📊 Dificultad

Intermedio

## ✅ Salida esperada al ejecutar

```text
Clínica MediSalud - Sede Centro
Atención: lunes a viernes de 7:00 a 19:00
Gracias por elegirnos
```

## 🎓 Resultados de aprendizaje

RA-6, RA-7
