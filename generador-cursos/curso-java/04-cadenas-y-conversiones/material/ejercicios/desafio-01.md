# 🏆 Desafío 01 — Etiqueta de historia clínica con salida exacta

## 🧩 Problema

**MediSalud** quiere generar una etiqueta impresa a partir de la historia clínica, el nombre del
paciente y el importe de la consulta escrito como texto. Escribe un programa (proyecto nuevo, paquete
`com.medisalud`, clase `EtiquetaHistoriaClinica`) que:

- Valide el importe **antes** de hacer cualquier otra cosa; si no es válido, muestra un mensaje y
  **no** construye la etiqueta.
- Si es válido, descompone la historia clínica con `substring`, obtiene las iniciales del paciente
  (con o sin apellido) y arma la etiqueta con `StringBuilder`, incluyendo el importe con el 10% de
  descuento.

Elige tú los métodos y justifícalos en un comentario de una línea sobre cada parte.

## 💻 Código o contexto de partida

No hay código de partida. Estos son los **datos de entrada** de los cuatro casos:

| Caso | `historiaClinica` | `nombreCompletoPaciente` | `importeTexto` |
|---|---|---|---|
| 1 | `"HC-2026-000123"` | `"Ana Torres"` | `"85000"` |
| 2 | `"HC-2026-000000"` | `"Ana Torres"` | `"85000"` |
| 3 | `"HC-2026-000123"` | `"Cristina"` | `"85000"` |
| 4 | `"HC-2026-000123"` | `"Ana Torres"` | `"85000 pesos"` |

## ✅ Salida esperada al ejecutar

**Caso 1** (caso general):

```text
=== Etiqueta de historia clínica ===
Historia: HC 2026 000123
Paciente: Ana Torres (AT)
Importe con descuento: 76500
```

**Caso 2** (número de historia en puros ceros):

```text
=== Etiqueta de historia clínica ===
Historia: HC 2026 000000
Paciente: Ana Torres (AT)
Importe con descuento: 76500
```

**Caso 3** (nombre de una sola palabra):

```text
=== Etiqueta de historia clínica ===
Historia: HC 2026 000123
Paciente: Cristina (C)
Importe con descuento: 76500
```

**Caso 4** (importe inválido: no se construye la etiqueta):

```text
Importe no válido: no se puede generar la etiqueta.
```

## 📏 Criterios de evaluación de la solución

- Con los datos de cada caso, el programa produce **exactamente** la salida indicada, línea por línea.
- El caso 4 no imprime ninguna parte de la etiqueta: se detiene en el mensaje de importe no válido.
- El caso 3 obtiene una sola inicial cuando el nombre no tiene apellido.
- Cada comentario justifica el método elegido (por ejemplo, `substring` para descomponer un formato
  fijo, `StringBuilder` porque la etiqueta se arma en varias partes).

## 🚧 Restricciones

- Usa solo lo aprendido en el módulo; no uses `try`/`catch` ni arreglos.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Desafío

## 🎓 Resultados de aprendizaje

- **RA-7**: descomponer un texto con formato fijo usando `substring`.
- **RA-11**: validar antes de convertir texto a número.
- **RA-13**: construir texto con `StringBuilder`.
- **RA-14**: comprobar la solución con casos de prueba, incluidos los límite.
