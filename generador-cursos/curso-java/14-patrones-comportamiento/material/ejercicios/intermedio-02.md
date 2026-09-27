# 🟡 Intermedio 02 — Aplicar Command

## 🧩 Problema

Tienes la misma clase de la Biblioteca Universitaria del ejercicio Básico 02:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class PuestoDeLectura {
    private String numero;
    private String usuarioAsignado;

    public PuestoDeLectura(String numero) {
        this.numero = numero;
    }

    public void ocupar(String usuario) {
        this.usuarioAsignado = usuario;
        System.out.println("Puesto " + numero + " ocupado por " + usuario);
    }

    public void liberar() {
        System.out.println("Puesto " + numero + " liberado (estaba " + usuarioAsignado + ")");
        this.usuarioAsignado = null;
    }

    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        PuestoDeLectura puesto = new PuestoDeLectura("12");
        puesto.ocupar("Ana Torres");
        System.out.println("ocupado por=" + puesto.getUsuarioAsignado());
    }
}
```

Rediséñalo para que respete Command: encapsula ocupar y liberar como acciones que un registro pueda
ejecutar y deshacer.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Ejecutar `AccionOcuparPuesto` y consultar `getUsuarioAsignado()` | Salida | `"Ana Torres"` |
| Deshacer la última acción y volver a consultar `getUsuarioAsignado()` | Salida | `null` (el estado exacto anterior a la acción) |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `AccionDePuesto`) con `ejecutar()`/`deshacer()`, implementada por
  `AccionOcuparPuesto` y `AccionLiberarPuesto`.
- Declara un registro (por ejemplo, `RegistroDeAcciones`) que ejecuta acciones y guarda un historial
  para poder deshacer la última.
- `deshacerUltima()` revierte el estado exacto anterior a la acción, sin que el código cliente conozca
  la operación contraria de cada acción.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: implementar Command para un caso dado.
