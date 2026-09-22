# 🔴 Avanzado 01 — Corregir un programa con errores de objetos

## 🧩 Problema

Un compañero escribió una revisión de préstamos para la **Biblioteca Universitaria**, pero el programa
tiene errores. Debe buscar un usuario, mostrar sus datos y mostrar el reglamento general.

Resuélvelo en **tres rondas**:

- **Ronda 1 — el editor te avisa.** Copia el código en VS Code y corrige el error que marca el panel
  **Problems** (`Ctrl+Shift+M`). Anota qué dice el mensaje.
- **Ronda 2 — el editor también te avisa, pero con una advertencia.** Cuando el programa compile, el
  panel Problems marca una **advertencia** (no un error) sobre una de las invocaciones. Corrígela aunque
  el programa ya funcione: es una mala práctica que conviene arreglar.
- **Ronda 3 — el programa se detiene.** Con los errores anteriores corregidos, ejecuta de nuevo.

  > ⚠️ **Advertencia**: en esta ronda el programa se detiene con un error real. No es un fallo de tu
  > equipo: hay un usuario que no se encontró. Lee el mensaje, encuentra la línea responsable y
  > corrígela.

## 💻 Código o contexto de partida

```java no-compila
package com.biblioteca;

public class RevisionDePrestamos {
    public String nombreUsuario;

    public static void mostrarReglamento() {
        System.out.println("Reglamento: máximo 3 préstamos activos por usuario.");
    }

    public void mostrarDatosUsuario() {
        System.out.println("Usuario: " + nombreUsuario);
    }

    static RevisionDePrestamos buscarUsuarioPorId(String id) {
        if (id.equals("U-001")) {
            RevisionDePrestamos usuario = new RevisionDePrestamos();
            usuario.nombreUsuario = "Carlos Ramírez";
            return usuario;
        }
        return null;
    }

    public static void main(String[] args) {
        RevisionDePrestamos.mostrarDatosUsuario();

        RevisionDePrestamos.mostrarReglamento();

        RevisionDePrestamos usuario = buscarUsuarioPorId("U-999");
        usuario.mostrarDatosUsuario();
    }
}
```

## 🧪 Casos de prueba

Salida que debe mostrar el programa corregido:

```text
Reglamento: máximo 3 préstamos activos por usuario.
Usuario: Carlos Ramírez
```

## 📏 Criterios de evaluación de la solución

- El programa compila, termina y muestra exactamente la salida esperada.
- El error de compilación se corrige entendiendo el mensaje, no probando al azar: se identifica que
  `mostrarDatosUsuario()` es un método de instancia y no se puede invocar sobre la clase.
- Se identifica y corrige la advertencia del panel: `mostrarReglamento()` es estático y se invoca sobre
  la clase, no sobre un objeto.
- Se corrige la búsqueda del usuario para que no se invoque un método sobre un resultado `null`.

## 🚧 Restricciones

- No elimines ninguna de las tres operaciones del programa: corrígelas.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-7**: distinguir método estático de método de instancia.
- **RA-8**: leer y escribir una clase completa.
- **RA-12**: identificar y corregir los errores frecuentes de esta etapa.
