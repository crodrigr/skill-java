# 🟢 Básico 05 — El objeto que vale null

## 🧩 Problema

El siguiente programa de **MediSalud** busca un paciente por su historia clínica y saluda al que
encuentra. Identifica el error y explica cómo corregirlo.

## 💻 Código o contexto de partida

> ⚠️ **Este programa se detiene con un error.** Es intencional: sirve para mostrar el mensaje real.

```java falla-en-ejecucion
public class ObjetoQueValeNull {
    public String nombrePaciente;

    public void saludar() {
        System.out.println("Hola, " + nombrePaciente);
    }

    static ObjetoQueValeNull buscarPorHistoria(String historia) {
        if (historia.equals("HC-2026-000123")) {
            ObjetoQueValeNull paciente = new ObjetoQueValeNull();
            paciente.nombrePaciente = "Ana Torres";
            return paciente;
        }
        return null;
    }

    public static void main(String[] args) {
        ObjetoQueValeNull paciente = buscarPorHistoria("HC-2026-000999");
        paciente.saludar();
    }
}
```

```text
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "ObjetoQueValeNull.saludar()" because "<local1>" is null
```

## 📏 Criterios de evaluación de la solución

- Identifica que `buscarPorHistoria(...)` devuelve `null` cuando no encuentra la historia clínica
  pedida, y que el programa invoca `saludar()` sobre ese resultado sin comprobarlo.
- Explica que el panel Problems no marca ningún error aquí, porque el `null` llega de una búsqueda, no
  de una asignación obvia en la misma línea.
- Propone comprobar si el resultado es distinto de `null` antes de invocar un método sobre él.

## 🚧 Restricciones

- No necesitas corregir el código, solo identificar el error y explicarlo.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-12**: identificar y corregir los errores frecuentes de esta etapa.
