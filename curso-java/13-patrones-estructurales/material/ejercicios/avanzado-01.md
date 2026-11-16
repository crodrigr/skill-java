# 🔴 Avanzado 01 — Corregir un diseño con dos patrones ausentes

## 🧩 Problema

La Biblioteca Universitaria procesa devoluciones con este código, que tiene dos problemas de diseño
distintos:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class CatalogoExternoDevoluciones {
    public String registrarDevolucionExterna(String datosCrudos) {
        return "OK registrado: " + datosCrudos;
    }
}
```

```java
package com.biblioteca;

public class VerificadorEstadoDevolucion {
    public String verificar(String codigo) {
        return "Estado verificado para " + codigo + ": sin danios reportados";
    }
}
```

```java
package com.biblioteca;

public class ActualizadorStockDevolucion {
    public String actualizar(String codigo) {
        return "Stock actualizado: " + codigo + " disponible nuevamente";
    }
}
```

```java
package com.biblioteca;

public class NotificadorProximoLector {
    public String notificar(String codigo) {
        return "Proximo lector en espera notificado sobre " + codigo;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Violación 1 (Adapter): el cliente arma a mano el formato "CODIGO|SOCIO|FECHA"
        // que espera el catálogo externo, en dos lugares distintos.
        CatalogoExternoDevoluciones catalogoExterno = new CatalogoExternoDevoluciones();
        String datos1 = "L001|Ana|2026-03-01";
        System.out.println(catalogoExterno.registrarDevolucionExterna(datos1));

        String datos2 = "L002|Beto|2026-03-02";
        System.out.println(catalogoExterno.registrarDevolucionExterna(datos2));

        // Violación 2 (Facade): el cliente orquesta directamente los tres subsistemas.
        VerificadorEstadoDevolucion verificadorEstado = new VerificadorEstadoDevolucion();
        ActualizadorStockDevolucion actualizadorStock = new ActualizadorStockDevolucion();
        NotificadorProximoLector notificadorProximoLector = new NotificadorProximoLector();

        System.out.println(verificadorEstado.verificar("L001"));
        System.out.println(actualizadorStock.actualizar("L001"));
        System.out.println(notificadorProximoLector.notificar("L001"));
    }
}
```

Encontrá y corrige las dos violaciones por separado:

1. `Demo` arma a mano, en dos lugares, el formato `"CODIGO|SOCIO|FECHA"` que espera
   `CatalogoExternoDevoluciones` — viola Adapter.
2. `Demo` orquesta directamente los tres subsistemas de devolución (`VerificadorEstadoDevolucion`,
   `ActualizadorStockDevolucion`, `NotificadorProximoLector`) — viola Facade.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Procesar la devolución de `"L001"` para `"Ana"` el `"2026-03-01"`, y de `"L002"` para `"Beto"` el `"2026-03-02"` | Salida completa del programa antes y después de corregir | Idéntica, carácter por carácter |
| `Demo.java` después de corregir | Cantidad de clases de subsistema que crea directamente | Ninguna: `Demo` solo crea la fachada |
| `CatalogoExternoDevoluciones.java`, `VerificadorEstadoDevolucion.java`, `ActualizadorStockDevolucion.java`, `NotificadorProximoLector.java` | Modificaciones respecto de la versión original | Ninguna en ninguno de los cuatro archivos |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `IDevolucionExterna`) y una clase adaptadora que centraliza la
  traducción hacia `CatalogoExternoDevoluciones`.
- Declara una única fachada que orquesta el adaptador y los tres subsistemas, en el mismo orden que la
  versión original.
- `Demo` solo conoce la fachada.
- La salida por consola no cambia respecto de la versión original.
- Ninguna de las cuatro clases originales se modifica.

## 🚧 Restricciones

- Las dos correcciones (Adapter y Facade) se aplican por separado: el adaptador no conoce la fachada, y
  la fachada usa al adaptador a través de su interfaz, nunca de la clase externa directamente.
- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar Adapter para un caso dado.
- **RA-16**: implementar Facade para un caso dado.
- **RA-23**: dado un problema de diseño nuevo, elegir el patrón estructural adecuado y justificarlo.
