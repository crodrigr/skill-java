# 🛠️ Taller 01 — Sistema de Gestión de Solicitudes Clínicas de MediSalud

## 🎯 Objetivo

Diseñar, desde cero, un sistema de gestión de solicitudes clínicas que combine tres de los cuatro
patrones de comportamiento: Chain of Responsibility, Mediator y Memento (RA-4, RA-13, RA-16).

## 🌍 Contexto

MediSalud quiere renovar la aprobación de solicitudes de procedimiento. El sistema debe: aprobar cada
solicitud según su complejidad, a través de una cadena de niveles de aprobación; coordinar, una vez
aprobada, la reserva de insumos y la notificación correspondiente, sin que estas dos tareas se conozcan
entre sí directamente; y permitir editar el texto de una solicitud antes de aprobarla, con la
posibilidad de deshacer un cambio y volver a una versión anterior.

## 🪜 Pasos

1. **Chain of Responsibility**: Declara la interfaz `ManejadorDeProcedimiento` y sus tres
   implementaciones (`AprobadorClinicoNivel1`, `AprobadorClinicoNivel2`, `AprobadorClinicoNivel3`),
   cada una decidiendo si aprueba una `SolicitudDeProcedimiento` según su complejidad, o la pasa al
   siguiente nivel.
2. **Mediator**: Declara `MediadorClinico`, que coordina `AreaInventarioClinico` y
   `AreaNotificacionesClinicas` cuando una solicitud es aprobada, sin que estas dos áreas se conozcan
   entre sí.
3. **Memento**: Declara `BorradorDeProcedimiento`, que permite guardar (`guardarInstante()`) y
   restaurar (`restaurar(instante)`) instantes (`InstanteDeProcedimiento`) del texto de una solicitud en
   edición, a través de un `HistorialDeInstantes`.
4. **Integración**: Con las tres piezas anteriores, arma al menos dos `SolicitudDeProcedimiento` de
   complejidad distinta; edita el borrador de una de ellas, deshace un cambio antes de aprobarla;
   aprueba ambas a través de la cadena de manejadores; y coordina, para cada una, la notificación
   resultante a través del mediador. Ejecuta con los casos de prueba.

## 💡 Ejemplo resuelto

Así se ve el primer nivel de la cadena de aprobación, una vez resuelto el paso 1: decide si aprueba la
solicitud o la pasa al siguiente nivel, sin conocer a los niveles posteriores:

```java
// dentro de AprobadorClinicoNivel1 implements ManejadorDeProcedimiento
private ManejadorDeProcedimiento siguiente;

public AprobadorClinicoNivel1(ManejadorDeProcedimiento siguiente) {
    this.siguiente = siguiente;
}

public String aprobar(SolicitudDeProcedimiento solicitud) {
    if (solicitud.getComplejidad().equals("BASICA")) {
        return "Aprobado nivel 1: " + solicitud.getNombreProcedimiento();
    }
    return siguiente.aprobar(solicitud);
}
```

El resto del diseño (el mediador clínico, el borrador con instantes) queda para ti.

## 📦 Entregable

```text
GestionDeSolicitudesClinicas/
└── com/medisalud/
    ├── SolicitudDeProcedimiento.java       (contexto compartido)
    ├── ManejadorDeProcedimiento.java       (interfaz, Chain of Responsibility)
    ├── AprobadorClinicoNivel1/2/3.java     (Chain of Responsibility)
    ├── AreaInventarioClinico.java          (Mediator)
    ├── AreaNotificacionesClinicas.java     (Mediator)
    ├── MediadorClinico.java                (Mediator)
    ├── InstanteDeProcedimiento.java        (Memento)
    ├── HistorialDeInstantes.java           (Memento)
    ├── BorradorDeProcedimiento.java        (Memento)
    └── Demo.java
```

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| Editar el borrador de una solicitud, guardar un instante, editar de nuevo y restaurar | Texto de la solicitud | Exactamente el texto guardado en el instante, no el segundo |
| Solicitud de complejidad `"BASICA"` | Aprobación a través de la cadena | `"Aprobado nivel 1: <nombre>"` |
| Solicitud de complejidad `"AVANZADA"` | Aprobación a través de la cadena | `"Aprobado nivel 3: <nombre>"` |
| Cualquier solicitud aprobada | Coordinación a través del mediador | Reserva de insumos y notificación, en ese orden |

## 📏 Criterios de evaluación

- `AprobadorClinicoNivel1`/`2`/`3` implementan la misma interfaz, y cada uno solo conoce al siguiente
  de la cadena (cuando corresponde).
- `AreaInventarioClinico` y `AreaNotificacionesClinicas` no se conocen entre sí directamente; toda su
  coordinación pasa por `MediadorClinico`.
- `InstanteDeProcedimiento` no expone su texto con un método público accesible desde fuera de su
  paquete.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
