# 🛠️ Taller 01 — Sistema de Notificaciones de MediSalud con Expresiones Lambda

## 🎯 Objetivo

Diseñar un sistema de notificaciones de MediSalud que use las cuatro interfaces funcionales estándar y
al menos una interfaz funcional propia, cada una con una responsabilidad clara (RA-2, RA-3, RA-4, RA-5,
RA-6, RA-7).

## 🌍 Contexto

MediSalud quiere notificar a los pacientes elegibles (por ejemplo, mayores de cierta edad) con un
recordatorio, generando un identificador único por notificación, y combinando el mensaje con el canal de
envío antes de enviarlo.

## 🪜 Pasos

1. **Predicate**: declara `Predicate<PacienteConsulta>` para decidir qué pacientes son elegibles para la
   notificación, según una condición sobre sus datos.
2. **Function**: declara `Function<PacienteConsulta, String>` para transformar los datos de un paciente
   elegible en el texto de su notificación.
3. **Supplier**: declara `Supplier<String>` para generar un identificador único de notificación, sin
   recibir ningún dato de entrada.
4. **Interfaz propia**: declara una interfaz funcional propia, anotada `@FunctionalInterface`, que
   combine el texto de la notificación con un canal de envío (por ejemplo, `"email"` o `"SMS"`) en un
   mensaje final — un caso con dos parámetros que no encaja en ninguna interfaz estándar.
5. **Consumer**: declara `Consumer<String>` para enviar (imprimir) el mensaje final.
6. **Integración**: recorre la lista de pacientes, filtra los elegibles, genera su identificador,
   transforma sus datos, combina el canal, y envía cada notificación — con los casos de prueba.

## 💡 Ejemplo resuelto

Un fragmento de la interfaz propia, para inspirarte (no es la solución completa):

```java
@FunctionalInterface
public interface CombinadorCanal {
    String combinar(String mensaje, String canal);
}
```

## 📦 Entregable

```text
NotificacionesMediSalud/
└── com/medisalud/
    ├── PacienteConsulta.java
    ├── CombinadorCanal.java
    └── Demo.java
```

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Paciente de 65 años y paciente de 34 años, condición "mayor o igual a 60" | Notificaciones enviadas | Solo se notifica al paciente de 65 años |
| Notificación enviada | Formato del mensaje | Incluye el identificador generado, el canal y el texto transformado |

## 📏 Criterios de evaluación

- Las cinco piezas (`Predicate`, `Function`, `Supplier`, interfaz propia, `Consumer`) están implementadas
  con expresiones lambda, cada una con una responsabilidad clara.
- Ningún paciente no elegible recibe una notificación.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
