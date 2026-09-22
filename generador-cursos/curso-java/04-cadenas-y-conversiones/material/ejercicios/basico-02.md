# 🟢 Básico 02 — Índices y longitud

## 🧩 Problema

En **MediSalud** se guarda el nombre de un paciente y su historia clínica. Sin ejecutar el programa,
responde qué muestra cada línea marcada con una letra.

## 💻 Código o contexto de partida

```java
public class IndicesYLongitud {

    public static void main(String[] args) {
        String nombrePaciente = "Ana Torres";
        System.out.println("a) " + nombrePaciente.length());
        System.out.println("b) " + nombrePaciente.charAt(0));
        System.out.println("c) " + nombrePaciente.charAt(nombrePaciente.length() - 1));
        System.out.println("d) " + nombrePaciente.isEmpty());
        String historia = "";
        System.out.println("e) " + historia.isEmpty() + " " + historia.length());
    }
}
```

| Línea | Tu predicción |
|---|---|
| a) | |
| b) | |
| c) | |
| d) | |
| e) | |

## 📏 Criterios de evaluación de la solución

- Las cinco respuestas son correctas.
- Se explica que el índice del último carácter es siempre `length() - 1`.
- Se explica que una cadena vacía tiene longitud 0 y `isEmpty()` da `true`.

## 🚧 Restricciones

- Resuélvelo a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-3**: obtener la longitud de una cadena.
- **RA-4**: extraer un carácter con `charAt` usando el índice correcto, incluido el último.
