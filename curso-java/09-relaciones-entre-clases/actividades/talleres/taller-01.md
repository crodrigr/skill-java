# 🛠️ Taller 01 — MediSalud completo: asociación, agregación y composición

## 🎯 Objetivo

Combinar las tres relaciones del módulo en un mismo diseño de MediSalud: una asociación bidireccional
consistente entre `Medico` y `Paciente` (RA-4, RA-5), una agregación de `Especialidad` en `Medico`
(RA-6, RA-7) y una composición de `HistoriaClinica` dentro de `Paciente` (RA-8, RA-9).

## 🌍 Contexto

MediSalud necesita un mini-sistema donde cada `Medico` tenga una `Especialidad` (compartible entre
médicos) y una lista de `Paciente` asignados; cada `Paciente`, a su vez, conoce a su `Medico` tratante y
tiene su propia `HistoriaClinica`, creada automáticamente.

## 🪜 Pasos

1. Crea el proyecto `RelacionesMediSalud` en Visual Studio Code y declara las clases `Especialidad` y
   `HistoriaClinica` (atributos simples, sin relaciones propias).
2. Declara `Medico` con un atributo `Especialidad especialidad` recibido por constructor (agregación) y
   una lista `List<Paciente> pacientes` inicializada vacía.
3. Declara `Paciente` con un atributo `Medico medico` y una `HistoriaClinica historiaClinica` creada
   **dentro** de su propio constructor (composición), sin recibirla por parámetro.
4. Implementa `medico.agregarPaciente(paciente)`, que agrega el `Paciente` a `pacientes` **y** le asigna
   `this` como su `medico`, en el mismo método.
5. Crea dos instancias de `Medico` con la **misma** `Especialidad` (para comprobar que sigue siendo
   válida desde las dos) y agrégales pacientes con `agregarPaciente`.
6. Comprueba, imprimiendo `medico.getPacientes().size()` y `paciente.getMedico()`, que ambos extremos
   quedan consistentes tras usar `agregarPaciente` (a diferencia del error lógico del Ejemplo 04).
7. Imprime, para cada `Paciente`, el `numeroHistoria` de su `HistoriaClinica`, comprobando que existe
   sin que el código del taller la haya construido explícitamente.
8. Ejecuta con los casos de prueba, cambiando solo los datos de entrada.

## 💡 Ejemplo resuelto

Un fragmento parcial para orientarte (no es la solución completa):

```java
public void agregarPaciente(Paciente paciente) {
    // TODO: agregar el paciente a la lista Y asignarle este Medico
}
```

## 📦 Entregable

```text
RelacionesMediSalud/
└── src/
    └── com/
        └── medisalud/
            ├── Especialidad.java
            ├── HistoriaClinica.java
            ├── Medico.java
            ├── Paciente.java
            └── Demo.java
```

## 🧪 Casos de prueba

Con dos `Medico` (`"Ana Torres"`, `"Carlos Ruiz"`) compartiendo la `Especialidad` `"Cardiologia"`, y un
`Paciente` agregado a cada uno (`"Marta Diaz"` con historia `1001`, `"Jorge Paz"` con historia `1002`),
la salida esperada es:

```text
Ana Torres (Cardiologia) tiene 1 paciente(s)
  - Marta Diaz -> medico: Ana Torres | Historia N1001: Sin observaciones registradas
Carlos Ruiz (Cardiologia) tiene 1 paciente(s)
  - Jorge Paz -> medico: Carlos Ruiz | Historia N1002: Sin observaciones registradas
```

## 📏 Criterios de evaluación

- Los dos `Medico` comparten la misma instancia de `Especialidad`.
- `medico.getPacientes().size()` y `paciente.getMedico()` quedan consistentes en ambos médicos.
- Cada `Paciente` tiene su `HistoriaClinica` propia, creada sin haberla construido explícitamente desde
  afuera de `Paciente`.
