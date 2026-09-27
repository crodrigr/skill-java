# 🛠️ Taller 01 — Sistema de Gestión de Turnos de MediSalud

## 🎯 Objetivo

Diseñar, desde cero, un sistema de gestión de turnos que combine cuatro de los cinco patrones de
comportamiento: Strategy, Observer, Command y State (RA-4, RA-7, RA-10, RA-13).

## 🌍 Contexto

MediSalud quiere renovar la gestión de sus turnos médicos. El sistema debe: calcular la tarifa de un
turno según su modalidad de atención, sin ningún condicional; notificar a varios interesados (el
paciente por SMS, la pantalla de recepción) cada vez que un turno cambia de estado; permitir confirmar
y cancelar turnos como acciones registradas en una bitácora; y hacer que el propio turno decida qué
transiciones son válidas según su estado actual, sin condicionales dispersos.

## 🪜 Pasos

1. **Strategy**: Declara la interfaz `ReglaDeTarifa` (con un método `calcular(montoBase)`) y sus dos
   implementaciones (`TarifaTurnoPresencial`, `TarifaTurnoVirtual`).
2. **State**: Declara la interfaz `EstadoDeTurnoMedico` y sus cuatro implementaciones (`Reservado`,
   `Confirmado`, `Atendido`, `Cancelado`), cada una decidiendo sus propias transiciones válidas.
3. **Observer**: Declara la interfaz `ObservadorDeTurnoMedico` y sus dos implementaciones
   (`NotificadorPacienteSms`, `PantallaDeRecepcion`), notificadas cuando un `TurnoMedico` cambia de
   estado.
4. **Command**: Declara la interfaz `AccionSobreTurnoMedico` y sus dos implementaciones
   (`AccionConfirmarTurnoMedico`, `AccionCancelarTurnoMedico`), ejecutadas a través de una
   `BitacoraDeAcciones` que registra cada acción.
5. **Integración**: Con las cuatro piezas anteriores, arma al menos dos `TurnoMedico` con modalidades
   distintas, registra observadores en cada uno, ejecuta acciones de confirmación y cancelación a
   través de la bitácora, y verifica que los resultados coincidan con los `## 🧪 Casos de prueba`.

## 💡 Ejemplo resuelto

Así se ve el estado `Reservado`, una vez resuelto el paso 2: decide sus propias transiciones válidas,
sin que `TurnoMedico` necesite ningún condicional:

```java
// dentro de Reservado implements EstadoDeTurnoMedico
public String confirmar(TurnoMedico turno) {
    turno.setEstado(new Confirmado());
    return "Turno confirmado";
}

public String atender(TurnoMedico turno) {
    return "No se puede atender: el turno no esta confirmado";
}
```

El resto del diseño (la regla de tarifa, los observadores, las acciones de la bitácora) queda para ti.

## 📦 Entregable

```text
GestionDeTurnos/
└── com/medisalud/
    ├── ReglaDeTarifa.java              (interfaz, Strategy)
    ├── TarifaTurnoPresencial.java      (Strategy)
    ├── TarifaTurnoVirtual.java         (Strategy)
    ├── EstadoDeTurnoMedico.java        (interfaz, State)
    ├── Reservado.java, Confirmado.java, Atendido.java, Cancelado.java  (State)
    ├── ObservadorDeTurnoMedico.java    (interfaz, Observer)
    ├── NotificadorPacienteSms.java, PantallaDeRecepcion.java          (Observer)
    ├── AccionSobreTurnoMedico.java     (interfaz, Command)
    ├── AccionConfirmarTurnoMedico.java, AccionCancelarTurnoMedico.java (Command)
    ├── BitacoraDeAcciones.java         (Command)
    ├── TurnoMedico.java                (orquesta los cuatro patrones)
    └── Demo.java
```

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| Turno presencial confirmado a través de la bitácora | `turnoPresencial.calcularTarifa()` | `5000.0` |
| Turno virtual confirmado a través de la bitácora | `turnoVirtual.calcularTarifa()` | `3500.0` (70% del monto base) |
| Turno virtual cancelado después de confirmado | `turnoVirtual.getEstado()` | `"CANCELADO"` |
| Tres acciones ejecutadas a través de la bitácora | `bitacora.totalAcciones()` | `3` |

## 📏 Criterios de evaluación

- `TurnoMedico.calcularTarifa()` no contiene ningún condicional sobre la modalidad.
- Ninguna clase de estado (`Reservado`, `Confirmado`, `Atendido`, `Cancelado`) permite una transición
  inválida sin rechazarla explícitamente.
- Agregar un observador nuevo no exige modificar `TurnoMedico`.
- `BitacoraDeAcciones` registra cada acción ejecutada, verificable con `totalAcciones()`.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
