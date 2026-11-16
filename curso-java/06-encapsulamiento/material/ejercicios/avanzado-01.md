# 🔴 Avanzado 01 — Corregir un programa con errores de encapsulamiento

## 🧩 Problema

Un compañero escribió una revisión de cupos de sala para la **Biblioteca Universitaria**, pero el
programa tiene errores.

Resuélvelo en **dos rondas**:

- **Ronda 1 — el editor te avisa.** Copia el código en VS Code y corrige el error que marca el panel
  **Problems** (`Ctrl+Shift+M`). Anota qué dice el mensaje.
- **Ronda 2 — la salida no coincide.** Cuando el programa compile, compara su salida con la esperada
  (más abajo). El panel Problems no te va a avisar de este error: encuéntralo tú, dentro del propio
  método `actualizarCupo`.

## 💻 Código o contexto de partida

```java no-compila
package com.biblioteca;

class Sala {
    private int cupoMaximo;

    public Sala(int cupoMaximo) {
        setCupoMaximo(cupoMaximo);
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        if (cupoMaximo < 0) {
            System.out.println("Cupo inválido (" + cupoMaximo + "): se conserva el cupo actual (" + this.cupoMaximo + ")");
            return;
        }
        this.cupoMaximo = cupoMaximo;
    }

    public void actualizarCupo(int nuevoCupo) {
        cupoMaximo = nuevoCupo;
    }
}

public class RevisionDeCupos {
    public static void main(String[] args) {
        Sala sala = new Sala(30);
        System.out.println("Cupo: " + sala.cupoMaximo);
    }
}
```

## 🧪 Casos de prueba

Salida que debe mostrar el programa corregido:

```text
Cupo inicial: 30
Cupo inválido (-10): se conserva el cupo actual (30)
Cupo tras actualizarCupo(-10): 30
```

## 📏 Criterios de evaluación de la solución

- El programa compila y muestra exactamente la salida esperada.
- El error de compilación se corrige entendiendo el mensaje: `main` no puede acceder a
  `sala.cupoMaximo` directamente porque es `private`; se corrige usando `getCupoMaximo()`.
- Se identifica que `actualizarCupo(int)` asignaba `cupoMaximo` directamente, saltándose la validación
  de `setCupoMaximo`; se corrige para que llame a `setCupoMaximo(nuevoCupo)` en vez de asignar
  directamente.

## 🚧 Restricciones

- No elimines ninguna de las operaciones del programa: corrígelas.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-3**: declarar un atributo `private` y explicar el error de acceder a él desde otra clase.
- **RA-9**: declarar un método `set` que valide antes de asignar.
- **RA-12**: identificar y corregir los errores frecuentes de esta etapa.
