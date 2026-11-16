# 🟡 Intermedio 03 — Varios objetos, independencia

## 🧩 Problema

**MediSalud** tiene la clase `Consulta`, con un constructor y un método `pagar(monto)`. Completa el
`TODO` creando un segundo objeto y comprobando que pagar uno no afecta el saldo del otro.

## 💻 Código o contexto de partida

```java
public class Consulta {
    public String nombrePaciente;
    public double saldoPendiente;

    public Consulta(String nombrePaciente, double saldoPendiente) {
        this.nombrePaciente = nombrePaciente;
        this.saldoPendiente = saldoPendiente;
    }

    public void pagar(double monto) {
        saldoPendiente = saldoPendiente - monto;
    }

    public static void main(String[] args) {
        Consulta consulta1 = new Consulta("Ana Torres", 50000.0);
        consulta1.pagar(20000.0);
        System.out.println(consulta1.nombrePaciente + ": saldo " + consulta1.saldoPendiente);

        // TODO: 1. crea un segundo objeto Consulta con otro paciente y otro saldo
        // TODO: 2. paga solo el segundo objeto
        // TODO: 3. muestra el saldo de los dos objetos, y comprueba que el primero no cambió
    }
}
```

## 🧪 Casos de prueba

| Objeto | Saldo tras el pago |
|---|---|
| consulta1 (saldo inicial 50000.0, paga 20000.0) | `30000.0` |
| consulta2 (saldo inicial 30000.0, paga 30000.0) | `0.0` |

## 📏 Criterios de evaluación de la solución

- Crea un segundo objeto `Consulta` con `new`, con un paciente y un saldo distintos del primero.
- Paga solo el segundo objeto con `pagar(...)`.
- Comprueba (mostrando su saldo) que el primer objeto no cambió después de pagar el segundo.

## 🚧 Restricciones

- No cambies los datos del primer objeto ni el orden en que se crea.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-9**: crear un objeto con `new`.
- **RA-11**: crear y usar varios objetos de la misma clase, cada uno con su propio estado.
