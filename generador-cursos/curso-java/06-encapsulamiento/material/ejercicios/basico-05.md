# 🟢 Básico 05 — El set que no cambia nada

## 🧩 Problema

**MediSalud** valida el monto base de una consulta. Predice qué muestra el siguiente programa,
**antes** de ejecutarlo.

## 💻 Código o contexto de partida

```java
public class Consulta {
    private double montoBase;

    public Consulta(double montoBase) {
        setMontoBase(montoBase);
    }

    public double getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(double montoBase) {
        if (montoBase < 0) {
            System.out.println("Monto inválido (" + montoBase + "): se conserva el monto actual (" + this.montoBase + ")");
            return;
        }
        this.montoBase = montoBase;
    }
}
```

```java
public class SetQueNoCambiaNada {
    public static void main(String[] args) {
        Consulta consulta = new Consulta(80000.0);
        consulta.setMontoBase(-5000.0);

        System.out.println("Monto final: " + consulta.getMontoBase());
    }
}
```

## 📏 Criterios de evaluación de la solución

- Predice que `setMontoBase(-5000.0)` no cambia el monto: `montoBase` conserva `80000.0`.
- Explica que el `set` valida antes de asignar, y que un valor negativo no pasa la validación.

## 🚧 Restricciones

- No modifiques el código: solo predice su salida.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-9**: declarar un método `set` que valide antes de asignar.
- **RA-12**: identificar y corregir los errores frecuentes de esta etapa.
