# 🛠️ Taller 01 — Sistema de Historias Clínicas Persistentes de MediSalud

## 🎯 Objetivo

Diseñar, desde cero, un sistema que combine lectura y escritura de texto plano con
serialización/deserialización de objetos completos, y el borrado de archivos que ya no hacen falta
(RA-11).

## 🌍 Contexto

MediSalud necesita, al cierre del día: guardar (y volver a leer) la lista de pacientes citados hoy en un
archivo de texto; serializar la historia clínica completa de cada paciente atendido; recuperar esas
historias en una segunda ejecución del programa; y borrar la historia de cualquier paciente ya dado de
alta definitivamente.

## 🪜 Pasos

1. **Lectura/Escritura**: escribir la lista de pacientes citados hoy en `pacientes_del_dia.txt`, y
   releerla para confirmar el contenido.
2. **Serialización**: declarar `FichaMedica implements Serializable` y serializar la historia
   completa de cada paciente atendido a su propio archivo `.ser`, con `ObjectOutputStream`.
3. **Deserialización**: en un segundo programa (segunda ejecución, proceso Java separado), recuperar las
   historias clínicas serializadas con `ObjectInputStream`, confirmando que sus datos son exactamente los
   originales.
4. **Borrado**: cuando un paciente ya fue dado de alta definitivamente, borrar su archivo `.ser` con
   `File.delete()`, confirmando con `exists()` que realmente desapareció.
5. **Integración**: ejecutar el flujo completo con los casos de prueba.

## 💡 Ejemplo resuelto

Así se ve el borrado de una historia clínica ya dada de alta, una vez resuelto el paso 4:

```java
// dentro de Escritor
File archivo = new File("historia_marta.ser");
boolean borrado = archivo.delete();
System.out.println("Historia borrada: " + borrado);
System.out.println("Historia existe despues de borrarla: " + archivo.exists());
```

El resto del diseño (la lectura/escritura de pacientes del día, la serialización de las demás historias,
y la deserialización en un segundo programa) queda para ti.

## 📦 Entregable

```text
HistoriasClinicasPersistentes/
└── com/medisalud/
    ├── FichaMedica.java   (Serialización)
    ├── Escritor.java          (Lectura/Escritura, Serialización, Borrado)
    └── Lector.java            (Deserialización, segunda ejecución)
```

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Escribir y releer `pacientes_del_dia.txt` | Contenido releído | Exactamente los pacientes escritos |
| Ejecutar `Escritor` (serializa y borra la historia ya dada de alta) | `exists()` sobre esa historia | `false`, después de borrarla |
| Ejecutar, en un proceso separado, `Lector` | Campos de las historias deserializadas | Exactamente iguales a las originales |

## 📏 Criterios de evaluación

- `pacientes_del_dia.txt` se escribe y se relee correctamente.
- Cada historia clínica se serializa completa, con una sola llamada a `writeObject()`.
- `Lector` deserializa correctamente en un proceso separado de `Escritor`.
- La historia ya dada de alta se borra, confirmado con `exists()` antes y después.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
