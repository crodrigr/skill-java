# 🟡 Intermedio 01 — Aplicar Adapter

## 🧩 Problema

Tienes el mismo sistema de la Biblioteca Universitaria del ejercicio Básico 01:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class CatalogoExterno {
    public String consultarStock(String datos) {
        if (datos.contains("ACCION=PRESTAMO")) {
            return "DISPONIBLE";
        }
        return "RESERVADO";
    }
}
```

```java
package com.biblioteca;

public class SistemaPrestamos {
    private CatalogoExterno catalogo = new CatalogoExterno();

    public String prestarMaterial(String codigo) {
        String datos = "CODIGO=" + codigo + ";ACCION=PRESTAMO";
        return catalogo.consultarStock(datos);
    }

    public String reservarMaterial(String codigo) {
        String datos = "CODIGO=" + codigo + ";ACCION=RESERVA";
        return catalogo.consultarStock(datos);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        SistemaPrestamos sistema = new SistemaPrestamos();
        System.out.println(sistema.prestarMaterial("L001"));
        System.out.println(sistema.reservarMaterial("L002"));
    }
}
```

Rediséñalo para que respete Adapter: declara una interfaz que `SistemaPrestamos` espera, y una clase
adaptadora que traduzca hacia `CatalogoExterno`, de forma que la traducción viva en un único lugar.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| `prestarMaterial("L001")` y `reservarMaterial("L002")` | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter |
| `CatalogoExterno.java` antes y después de refactorizar | Archivo `CatalogoExterno.java` | Sin ninguna modificación |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `ICatalogo`) con el método que `SistemaPrestamos` necesita.
- Declara una clase adaptadora (por ejemplo, `CatalogoAdapter`) que implementa esa interfaz y traduce
  hacia `CatalogoExterno.consultarStock(...)`.
- `SistemaPrestamos` depende únicamente de la interfaz, nunca de `CatalogoExterno` directamente.
- La salida por consola no cambia respecto de la versión original.
- `CatalogoExterno.java` no se modifica.

## 🚧 Restricciones

- No se modifica `CatalogoExterno.java`: es el sistema externo que no se puede tocar.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar Adapter para un caso dado.
