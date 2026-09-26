# 🟢 Básico 01 — Identificar violación de Adapter

## 🧩 Problema

La Biblioteca Universitaria consulta un catálogo externo con este sistema:

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

Sin escribir código, responde: si se agrega una tercera operación (por ejemplo, extender un préstamo),
¿qué tiene que hacer el nuevo método y por qué eso es un problema si mañana el catálogo externo cambia su
formato de datos?

## 📏 Criterios de evaluación de la solución

- Identifica que `SistemaPrestamos` arma a mano el texto `"CODIGO=...;ACCION=..."` en cada método, y que
  un método nuevo repetiría esa misma construcción.
- Explica que, si el catálogo externo cambiara su formato, habría que modificar todos los métodos de
  `SistemaPrestamos` que lo arman a mano, uno por uno.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: reconocer qué resuelve Adapter.
- **RA-3**: identificar el problema de traducción duplicada en un fragmento dado.
