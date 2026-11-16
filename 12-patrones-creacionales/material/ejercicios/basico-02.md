# 🟢 Básico 02 — Identificar violación de Abstract Factory

## 🧩 Problema

La Biblioteca Universitaria arma kits de bienvenida (carnet + comprobante) para sus usuarios:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public interface Carnet {
    String describir();
}
```

```java
package com.biblioteca;

public class CarnetRegular implements Carnet {
    public String describir() {
        return "Carnet linea REGULAR";
    }
}
```

```java
package com.biblioteca;

public class CarnetVip implements Carnet {
    public String describir() {
        return "Carnet linea VIP";
    }
}
```

```java
package com.biblioteca;

public interface Comprobante {
    String describir();
}
```

```java
package com.biblioteca;

public class ComprobanteRegular implements Comprobante {
    public String describir() {
        return "Comprobante linea REGULAR";
    }
}
```

```java
package com.biblioteca;

public class ComprobanteVip implements Comprobante {
    public String describir() {
        return "Comprobante linea VIP";
    }
}
```

```java
package com.biblioteca;

public class ArmadorDeKits {
    public String armarKit(String lineaCarnet, String lineaComprobante) {
        Carnet carnet = lineaCarnet.equals("VIP") ? new CarnetVip() : new CarnetRegular();
        Comprobante comprobante = lineaComprobante.equals("VIP") ? new ComprobanteVip() : new ComprobanteRegular();
        return carnet.describir() + " + " + comprobante.describir();
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        ArmadorDeKits armador = new ArmadorDeKits();

        System.out.println("kit1=" + armador.armarKit("REGULAR", "REGULAR"));
        // Error de un lugar del codigo que arma el kit con lineas mezcladas por accidente:
        System.out.println("kit2 (mezclado por error)=" + armador.armarKit("VIP", "REGULAR"));
    }
}
```

## ✅ Salida real del programa

```text
kit1=Carnet linea REGULAR + Comprobante linea REGULAR
kit2 (mezclado por error)=Carnet linea VIP + Comprobante linea REGULAR
```

Sin escribir código, respondé: ¿qué le impide a `ArmadorDeKits.armarKit` producir un kit con el carnet de
una línea y el comprobante de otra? ¿Qué evidencia real, en la salida de arriba, muestra que ya ocurrió?

## 📏 Criterios de evaluación de la solución

- Identifica que cada insumo se crea con su propio parámetro de línea, independiente del otro.
- Señala la línea de la salida (`kit2`) donde el carnet y el comprobante quedaron de líneas distintas.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: reconocer qué resuelve Abstract Factory.
- **RA-6**: reconocer cuándo usar Abstract Factory en vez de Factory Method.
