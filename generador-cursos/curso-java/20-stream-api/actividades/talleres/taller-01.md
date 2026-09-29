# 🛠️ Taller 01 — Sistema de Reportes de Pacientes de MediSalud con Stream API

## 🎯 Objetivo

Diseñar un pipeline de Stream API para MediSalud que combine al menos tres operadores y convierta el
resultado final a una `List<String>` (RA-6, RA-7, RA-9, RA-10, RA-11).

## 🌍 Contexto

MediSalud organiza sus pacientes por consultorio y quiere generar un reporte de texto con los pacientes
mayores de 40 años de **todos** los consultorios juntos, sin importar en cuál están.

## 🪜 Pasos

1. **Creación**: parte de una `List<List<PacienteMuestra>>` con al menos dos consultorios, cada uno con
   su propia lista de pacientes.
2. **flatMap**: aplana los consultorios en un único `Stream<PacienteMuestra>` con todos los pacientes.
3. **filter**: selecciona, con un `Predicate<PacienteMuestra>`, los pacientes mayores o iguales a 40
   años.
4. **map**: transforma cada paciente seleccionado en una línea de reporte con su nombre y edad, por
   ejemplo `"Luis Rios (45 anios)"`.
5. **Conversión final**: convierte el resultado del pipeline en una `List<String>` con `.toList()`, y
   muéstralo por consola.

## 💡 Ejemplo resuelto

Un fragmento del pipeline, para inspirarte (no es la solución completa):

```java
List<String> reporte = consultorios.stream()
        .flatMap(consultorio -> consultorio.stream())
        .filter(paciente -> paciente.getEdad() >= 40)
        .map(paciente -> /* completa la línea del reporte */ null)
        .toList();
```

## 📦 Entregable

```text
ReportesMediSalud/
└── com/medisalud/
    ├── PacienteMuestra.java
    └── Demo.java
```

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Consultorio 1: Ana Torres (34), Luis Rios (45); Consultorio 2: Marta Diaz (52), Carlos Mena (28) | Reporte generado | Solo `Luis Rios (45 anios)` y `Marta Diaz (52 anios)`, en una `List<String>` |

## 📏 Criterios de evaluación

- Usa al menos tres operadores de Stream API (`flatMap`, `filter`, `map`), cada uno con una
  responsabilidad clara.
- Ningún paciente menor de 40 años aparece en el reporte final.
- El resultado final es una `List<String>`, obtenida con `.toList()`.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
