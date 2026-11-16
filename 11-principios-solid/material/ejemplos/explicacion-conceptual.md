# 📚 Explicación conceptual — Módulo 11

## 🧠 Concepto: Qué son los principios SOLID

- SOLID es un acrónimo de cinco principios de diseño orientado a objetos: SRP, OCP, LSP, ISP y DIP.
- No son reglas de sintaxis ni un patrón de diseño con nombre propio: son criterios para repartir
  responsabilidades entre clases e interfaces.
- Se aplican sobre código que **ya compila y funciona**: el problema que resuelven es de diseño (qué tan
  fácil es mantenerlo o extenderlo), no de sintaxis ni de ejecución.
- Ignorarlos no impide que el programa funcione hoy; hace que cada cambio futuro cueste más.

📎 Ver en la práctica: [Ejemplo 01 — Qué son los principios SOLID](01-que-son-los-principios-solid.md)

## 🧠 Concepto: SRP

- Principio de Responsabilidad Única: una clase debe tener una sola razón para cambiar.
- Una responsabilidad no es "un método", sino un motivo de cambio: agendar citas, notificar y facturar
  son tres motivos distintos, aunque quepan en una sola clase.
- Se reconoce una violación cuando una clase mezcla lógica de negocio con lógica de presentación,
  notificación o persistencia en el mismo método.
- Se corrige extrayendo cada responsabilidad a su propia clase y haciendo que la clase original las use
  por colaboración (recibiéndolas por constructor), sin cambiar el comportamiento observable.

📎 Ver en la práctica: [Ejemplo 02 — Responsabilidad Única (SRP)](02-responsabilidad-unica-srp.md)

## 🧠 Concepto: OCP

- Principio de Abierto/Cerrado: una clase debe estar abierta a extensión pero cerrada a modificación.
- Una cadena de `if`/`else` por tipo (de paciente, de usuario, de producto) obliga a modificar el mismo
  método cada vez que aparece un tipo nuevo.
- Una interfaz con una implementación por caso permite agregar un caso nuevo declarando una clase nueva,
  sin tocar ninguna de las existentes.
- El costo de extender se mide en cuántos archivos existentes hay que modificar: cero es la meta.

📎 Ver en la práctica: [Ejemplo 03 — Abierto/Cerrado (OCP)](03-abierto-cerrado-ocp.md)

## 🧠 Concepto: LSP

- Principio de Sustitución de Liskov: un objeto de una subclase debe poder sustituir a uno de la
  superclase sin que el comportamiento observado sorprenda a quien lo usa.
- Sustituir "sin sorpresas" significa no estrechar las precondiciones (exigir más de lo que exigía la
  superclase) ni debilitar las poscondiciones (devolver menos de lo que prometía).
- Una subclase puede violar LSP de más de una forma: lanzando una excepción que la superclase nunca
  lanzaba, o devolviendo un resultado distinto en silencio ante la misma llamada.
- Se corrige moviendo la diferencia real (un límite, una condición) a un dato consultable de la
  superclase, en vez de sobrescribir el comportamiento con una regla más estricta.

📎 Ver en la práctica: [Ejemplo 04 — Sustitución de Liskov (LSP)](04-sustitucion-de-liskov-lsp.md)

## 🧠 Concepto: ISP

- Principio de Segregación de Interfaces: ninguna clase debe verse obligada a implementar métodos que no
  usa.
- Una interfaz "gorda" (con métodos de roles distintos) obliga a cada clase que la implementa a declarar
  todos sus métodos, aunque la mayoría no tenga sentido real para ese rol.
- Se corrige dividiendo la interfaz gorda en varias interfaces pequeñas, una por rol o capacidad, y
  haciendo que cada clase implemente solo las que le corresponden.
- Tras dividir la interfaz, una llamada a un método que ya no le corresponde a una clase deja de
  compilar: la prueba de que ISP se corrigió es un error real de `javac`, no una ejecución fallida.

📎 Ver en la práctica: [Ejemplo 05 — Segregación de Interfaces (ISP)](05-segregacion-de-interfaces-isp.md)

## 🧠 Concepto: DIP

- Principio de Inversión de Dependencias: una clase debe depender de una abstracción (una interfaz), no
  de un detalle concreto.
- Si una clase crea su dependencia con `new` dentro de su propio código, esa dependencia queda fija: para
  usar otra implementación hay que modificar la clase.
- Recibiendo la dependencia por constructor como una interfaz, la misma clase funciona con cualquier
  implementación de esa interfaz, sin que su código cambie entre una y otra.
- DIP no requiere un framework de inyección de dependencias: en Java plano, "inyectar por constructor"
  es simplemente pasar el objeto como parámetro al crear la instancia.

📎 Ver en la práctica: [Ejemplo 06 — Inversión de Dependencias (DIP)](06-inversion-de-dependencias-dip.md)

## 📋 Resumen de los cinco principios

| Principio | Sigla | Qué evita | Ejemplo de MediSalud |
|---|---|---|---|
| Responsabilidad Única | SRP | Una clase que cambia por más de una razón | `GestorCitas` separado de `NotificadorCitas` y `FacturadorCitas` |
| Abierto/Cerrado | OCP | Modificar código existente para agregar un caso nuevo | `EstrategiaDescuento` con una implementación por tipo de paciente |
| Sustitución de Liskov | LSP | Una subclase que rompe las expectativas de su superclase | `MedicoResidente` con el mínimo de días consultable, no sobrescrito en silencio |
| Segregación de Interfaces | ISP | Una clase obligada a implementar métodos que no usa | `Recepcionista` ya no implementa `RealizarCirugias` |
| Inversión de Dependencias | DIP | Una clase atada a un detalle concreto en vez de una abstracción | `SistemaNotificaciones` recibe `CanalNotificacion` por constructor |
