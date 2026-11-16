# 🛠️ Taller 01 — Rediseño SOLID del sistema de citas de MediSalud

## 🎯 Objetivo

Diseñar, desde cero, un sistema de citas para MediSalud que aplique los cinco principios SOLID a la vez:
Responsabilidad Única, Abierto/Cerrado, Sustitución de Liskov, Segregación de Interfaces e Inversión de
Dependencias (RA-4, RA-7, RA-10, RA-12, RA-14, RA-15).

## 🌍 Contexto

MediSalud quiere renovar su sistema de agendamiento de citas. El sistema debe: agendar citas respetando
un mínimo de días de anticipación que varía según el tipo de médico; calcular el monto de la consulta
aplicando un descuento que depende del tipo de paciente, con la posibilidad de agregar tipos de descuento
nuevos en el futuro; y repartir las responsabilidades de atender la consulta, realizar procedimientos y
emitir la factura entre distintos roles del personal, sin obligar a ningún rol a implementar una
capacidad que no le corresponde.

## 🪜 Pasos

1. **SRP**: Declara una clase `GestorCitas` con una única responsabilidad: agendar citas. No debe
   imprimir facturas, calcular descuentos "a mano" dentro de `agendarCita`, ni decidir por su cuenta
   quién atiende cada consulta — esas tareas se delegan a otras clases.
2. **DIP**: Declara una interfaz `EstrategiaDescuento` con un único método para calcular un descuento
   sobre un monto, y al menos dos implementaciones (por ejemplo, para paciente regular y paciente VIP).
   `GestorCitas` debe recibir la implementación por constructor, no crearla con `new` internamente.
3. **OCP**: Agrega una tercera implementación de `EstrategiaDescuento` (por ejemplo, para paciente
   corporativo) sin modificar ninguna de las clases que ya existían — ni la interfaz ni las
   implementaciones anteriores. Comprobalo: las clases existentes deben quedar exactamente iguales.
4. **LSP**: Declara una jerarquía de médicos donde cada tipo tenga su propio mínimo de días de
   anticipación para agendar una cita. El mínimo debe ser un dato consultable (no una condición
   sobrescrita en silencio), de forma que un código que trate a cualquier médico de manera uniforme
   pueda consultar su mínimo real antes de intentar agendar.
5. **ISP**: Repartí las responsabilidades de atender la consulta, realizar procedimientos y emitir la
   factura en interfaces separadas, una por capacidad. Un médico implementa las tres; un rol que no
   realiza procedimientos médicos (por ejemplo, personal de recepción) implementa solo las que le
   corresponden.
6. **Integración**: Con todas las piezas anteriores, agendá al menos dos citas con médicos de tipos
   distintos (uno de cada nivel de la jerarquía del paso 4), calcula el monto de cada una con dos
   estrategias de descuento distintas, y verifica que los resultados coincidan con los `## 🧪 Casos de
   prueba`.

## 💡 Ejemplo resuelto

Así se ve el método de `GestorCitas` que agenda una cita, una vez resuelto el paso 1 (SRP): recibe el
médico, el paciente y los días de anticipación, verifica si el médico puede agendar con ese margen, y si
puede, delega la atención al propio médico — sin imprimir nada por su cuenta más allá de lo que el médico
decida imprimir:

```java
public boolean agendarCita(MedicoClinica medico, String paciente, int diasDesdeHoy) {
    if (!medico.puedeAgendar(diasDesdeHoy)) {
        return false;
    }
    citas.add(medico.getNombre() + " - " + paciente);
    medico.atenderConsulta(paciente);
    return true;
}
```

El resto del diseño (la interfaz de descuento, la jerarquía de médicos, las interfaces segregadas) queda
para vos.

## 📦 Entregable

```text
RediseñoCitasMediSalud/
└── com/medisalud/
    ├── EstrategiaDescuento.java      (interfaz, DIP + OCP)
    ├── Descuento....java             (al menos dos implementaciones)
    ├── Atiende....java               (interfaz segregada, ISP)
    ├── Realiza....java               (interfaz segregada, ISP)
    ├── Emite....java                 (interfaz segregada, ISP)
    ├── Medico....java                (jerarquía con mínimo consultable, LSP)
    ├── GestorCitas.java              (SRP)
    └── Demo.java
```

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| Médico de nivel base, cita con anticipación suficiente | `agendarCita(...)` | `true`, y el médico confirma la atención |
| Médico de nivel superior (mayor mínimo de anticipación), misma cantidad de días que el caso anterior | `agendarCita(...)` | `false`: el mínimo real de ese médico no se cumple |
| Un monto de consulta con dos estrategias de descuento distintas | `calcularMontoConDescuento(...)` | Dos resultados distintos, coherentes con cada estrategia |
| Se agrega una tercera estrategia de descuento | Comparación de las clases existentes antes y después | Ninguna clase existente cambia |

## 📏 Criterios de evaluación

- `GestorCitas` no calcula descuentos ni decide facturación por su cuenta: delega en las clases
  correspondientes.
- Agregar una estrategia de descuento nueva no modifica ninguna clase existente.
- Ningún tipo de médico sobrescribe el método que decide si puede agendar una cita: el mínimo de
  anticipación es un dato consultable.
- Ningún rol del personal implementa una interfaz con métodos que no le corresponden a su función real.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
