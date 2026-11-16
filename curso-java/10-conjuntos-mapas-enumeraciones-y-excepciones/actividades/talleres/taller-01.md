# 🛠️ Taller 01 — Registro de Citas de MediSalud: Set, Map, enum y excepciones juntos

## 🎯 Objetivo

Combinar las cuatro capacidades del módulo en un mismo diseño de MediSalud: un `Set` de especialidades
disponibles (RA-2), un `Map` que registra citas por paciente (RA-5, RA-7), un `enum` para el estado de
la cita (RA-9, RA-10), y `try`/`catch`/`finally` con una excepción personalizada lanzada con `throw`
(RA-12, RA-13, RA-14).

## 🌍 Contexto

MediSalud necesita un registro de citas que rechace, con un mensaje claro, cualquier intento de agendar
una cita con una especialidad que la clínica no ofrece — sin que el programa se caiga por eso — y que
solo guarde la cita si la especialidad es válida.

## 🪜 Pasos

1. Crea el proyecto `RegistroCitas` en Visual Studio Code y declara `EstadoCita`
   (`enum` con `PROGRAMADA`, `ATENDIDA`, `CANCELADA`) y `CitaInvalidaException`
   (`extends RuntimeException`, con un constructor que reciba un mensaje).
2. Declara `RegistroCitas` con un `Set<String> especialidadesDisponibles` (recibido por constructor) y
   un `Map<String, EstadoCita> citasPorPaciente` inicializado vacío.
3. Declara un método privado `validarEspecialidad(String)` que lance `CitaInvalidaException` (con
   `throw`) si la especialidad no está en `especialidadesDisponibles`.
4. Declara `registrarCita(String paciente, String especialidad)`, que llama a `validarEspecialidad` y,
   si no lanza nada, agrega al paciente a `citasPorPaciente` con estado `EstadoCita.PROGRAMADA`.
5. En `Demo`, crea un `RegistroCitas` con al menos dos especialidades disponibles.
6. Envuelve una llamada a `registrarCita` con una especialidad **inválida** en un
   `try`/`catch (CitaInvalidaException e)`/`finally`: el `catch` imprime el mensaje de la excepción, el
   `finally` imprime que el registro de ese paciente terminó, y el programa sigue corriendo después.
7. Registra, a continuación, una cita con una especialidad **válida**, y comprueba que
   `citasPorPaciente` la incluye con estado `PROGRAMADA`.
8. Ejecuta con los casos de prueba, cambiando solo los datos de entrada.

## 💡 Ejemplo resuelto

Un fragmento parcial para orientarte (no es la solución completa):

```java
private void validarEspecialidad(String especialidad) {
    if (!especialidadesDisponibles.contains(especialidad)) {
        throw new CitaInvalidaException("Especialidad no disponible: " + especialidad);
    }
}
```

## 📦 Entregable

```text
RegistroCitas/
└── src/
    └── com/
        └── medisalud/
            ├── EstadoCita.java
            ├── CitaInvalidaException.java
            ├── RegistroCitas.java
            └── Demo.java
```

## 🧪 Casos de prueba

Con especialidades disponibles `{"Cardiologia", "Pediatria"}`, un intento de cita para "Jorge Paz" con
`"Neurologia"` (inválida) y una cita para "Marta Diaz" con `"Cardiologia"` (válida), la salida esperada
es:

```text
Error: Especialidad no disponible: Neurologia
Registro de Jorge Paz finalizado
Cita de Marta Diaz registrada
Registro de Marta Diaz finalizado
citasPorPaciente.size=1
Marta Diaz -> PROGRAMADA
contiene Jorge Paz=false
```

## 📏 Criterios de evaluación

- La especialidad inválida se rechaza con `CitaInvalidaException`, capturada sin que el programa
  termine.
- El `finally` se ejecuta en ambos casos (especialidad inválida y válida).
- Solo la cita con especialidad válida queda en `citasPorPaciente`, con estado `PROGRAMADA`.
