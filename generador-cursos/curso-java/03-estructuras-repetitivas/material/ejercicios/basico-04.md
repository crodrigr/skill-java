# 🟢 Básico 04 — ¿Qué se omite?

## 🧩 Problema

En **MediSalud**, dos recorridos de los turnos 1 a 6 son casi iguales: uno usa `break` y otro usa
`continue` cuando el turno vale 4. Sin ejecutar el programa, indica **qué turnos se suman** en cada
recorrido, cuáles **no** se suman y cuánto vale la suma al final.

## 💻 Código o contexto de partida

```java
public class BreakYContinue {

    public static void main(String[] args) {
        // Recorrido A: con break
        int sumaA = 0;
        for (int turno = 1; turno <= 6; turno++) {
            if (turno == 4) {
                break;
            }
            sumaA += turno;
        }
        System.out.println("A: suma = " + sumaA);

        // Recorrido B: con continue
        int sumaB = 0;
        for (int turno = 1; turno <= 6; turno++) {
            if (turno == 4) {
                continue;
            }
            sumaB += turno;
        }
        System.out.println("B: suma = " + sumaB);
    }
}
```

Completa la tabla:

| Recorrido | Turnos que se suman | Turnos que no se suman | Suma final |
|---|---|---|---|
| A (con `break`) | | | |
| B (con `continue`) | | | |

## 📏 Criterios de evaluación de la solución

- Las dos filas de la tabla son correctas.
- Se explica que `break` termina el bucle y por eso los turnos 5 y 6 tampoco se suman en A.
- Se explica que `continue` solo omite el turno 4 y el recorrido sigue con el 5 y el 6 en B.
- Se explica qué instrucción es la primera que se ejecuta después de cada bucle.

## 🚧 Restricciones

- Resuélvelo a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-7**: predecir el resultado de un bucle siguiendo sus vueltas.
- **RA-8**: explicar qué instrucciones deja de ejecutar un `break`.
- **RA-9**: explicar qué omite un `continue` y en qué se diferencia de `break`.
