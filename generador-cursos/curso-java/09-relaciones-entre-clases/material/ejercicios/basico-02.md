# 🟢 Básico 02 — Identificar una asociación unidireccional en código

## 🧩 Problema

Mira estas tres clases de MediSalud:

## 💻 Código o contexto de partida

```java
package com.medisalud;

public class Paciente {
    private String nombre;

    public Paciente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

```java
package com.medisalud;

public class Factura {
    private int numero;
    private Paciente paciente;

    public Factura(int numero, Paciente paciente) {
        this.numero = numero;
        this.paciente = paciente;
    }

    public int getNumero() {
        return numero;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
```

```java
package com.medisalud;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Paciente paciente = new Paciente("Marta Diaz");
        Factura factura = new Factura(5001, paciente);

        System.out.println("Factura N" + factura.getNumero() + " - " + factura.getPaciente().getNombre());
    }
}
```

**Pregunta**: respondé, sin ejecutar nada todavía:

1. ¿`Factura` conoce a `Paciente`, `Paciente` conoce a `Factura`, o ambas?
2. ¿Es una asociación unidireccional o bidireccional?
3. ¿Qué imprime `Demo`?

## 🧪 Casos de prueba

| Expresión | Resultado esperado |
|---|---|
| `factura.getPaciente().getNombre()` | El nombre del paciente de la factura |
| `paciente.getFactura()` | No existe: no se puede navegar en ese sentido |

## 📏 Criterios de evaluación de la solución

- Identifica que `Factura` conoce a `Paciente`, pero `Paciente` no tiene ningún método hacia `Factura`.
- Identifica la relación como asociación unidireccional.
- Predice correctamente la salida de `Demo`.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de lectura y predicción.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: reconocer una asociación unidireccional.
- **RA-10**: distinguir asociación, agregación y composición dado un escenario.
