# 🟢 Básico 05 — Tipo y valor de conversiones

## 🧩 Problema

En **MediSalud** se mezclan tipos numéricos en varios cálculos. Sin ejecutar el programa, indica el
**tipo** y el **valor** de cada expresión marcada con una letra, y si en ella **se pierden datos**.

## 💻 Código o contexto de partida

```java
public class TipoYValor {

    public static void main(String[] args) {
        int edadPaciente = 34;
        double pesoKg = 62.5;

        // a) int + double
        System.out.println("a) " + (edadPaciente + pesoKg));

        // b) division entera vs con cast
        int consultasMes = 7;
        int pacientes = 2;
        System.out.println("b) " + (consultasMes / pacientes) + " | " + ((double) consultasMes / pacientes));

        // c) cast que corta decimales
        double montoConDecimales = 85999.99;
        System.out.println("c) " + (int) montoConDecimales);

        // d) char a int
        char inicial = 'M';
        System.out.println("d) " + (int) inicial);

        // e) desbordamiento
        int numeroGrande = 200;
        System.out.println("e) " + (byte) numeroGrande);

        // f) long a double, perdida de precision
        long numeroHistoria = 9999999999999999L;
        System.out.println("f) " + (double) numeroHistoria);
    }
}
```

| Línea | Tipo del resultado | Valor | ¿Se pierden datos? |
|---|---|---|---|
| a) | | | |
| b) | | | |
| c) | | | |
| d) | | | |
| e) | | | |
| f) | | | |

## 📏 Criterios de evaluación de la solución

- Las seis filas son correctas.
- En b) se distingue la división entera de la que usa `cast`.
- En e) se identifica el desbordamiento.
- En f) se identifica la pérdida de precisión, aunque no haya un error visible.

## 🚧 Restricciones

- Resuélvelo a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: predecir el tipo resultante de una conversión implícita.
- **RA-9**: predecir el resultado de una conversión explícita y reconocer sus riesgos.
