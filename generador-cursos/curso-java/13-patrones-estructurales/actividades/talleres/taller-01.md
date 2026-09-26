# 🛠️ Taller 01 — Sistema de Gestión de Documentos Clínicos de MediSalud

## 🎯 Objetivo

Diseñar, desde cero, un sistema de gestión de documentos clínicos que combine cuatro de los siete
patrones estructurales: Adapter, Composite, Decorator y Proxy (RA-4, RA-10, RA-13, RA-22).

## 🌍 Contexto

MediSalud quiere organizar la carpeta clínica digital de cada paciente. La carpeta debe: incorporar
resultados de un sistema externo de laboratorio con un formato de datos propio, sin que el resto del
sistema conozca ese formato; contener documentos y sub-carpetas anidadas, contando el total de documentos
de un paciente sin distinguir un tipo de otro con condicionales; marcar cualquier documento como urgente
sin necesitar una subclase por cada tipo de documento urgente; y retrasar la carga de una imagen de
estudio pesada (por ejemplo, una radiografía escaneada) hasta que alguien realmente la visualiza.

## 🪜 Pasos

1. **Adapter**: Declara la interfaz común `DocumentoClinico` (con `getNombre()`, `contarDocumentos()` y
   `ver()`). Declara `ResultadoLaboratorioExterno` (el sistema externo, con su propio formato) y
   `ResultadoLaboratorioAdapter`, que implementa `DocumentoClinico` traduciendo hacia el sistema externo.
2. **Composite**: Declara `CarpetaClinica`, que implementa `DocumentoClinico`, contiene una lista de
   `DocumentoClinico` (documentos y/o sub-carpetas), y calcula `contarDocumentos()` sumando
   recursivamente el conteo de cada elemento, sin ningún `instanceof`.
3. **Decorator**: Declara `DocumentoConMarcaUrgente`, que implementa `DocumentoClinico`, envuelve
   cualquier otro `DocumentoClinico` y antepone una marca de urgencia a su `ver()`, delegando el resto.
4. **Proxy**: Declara `ImagenEstudio` (la carga real, costosa) e `ImagenEstudioProxy`, que implementa
   `DocumentoClinico` y crea la imagen real recién dentro de `ver()`, la primera vez que se llama.
5. **Integración**: Con las cuatro piezas anteriores, arma una carpeta clínica con al menos tres
   documentos de tipos distintos (uno adaptado del laboratorio externo, uno con marca de urgencia
   envolviendo un proxy de imagen, y una sub-carpeta anidada con otro documento), consultá el total de
   documentos, y verifica que los resultados coincidan con los `## 🧪 Casos de prueba`.

## 💡 Ejemplo resuelto

Así se ve el decorador que agrega la marca de urgencia, una vez resuelto el paso 3: envuelve cualquier
`DocumentoClinico` y delega en él, salvo en `ver()`, donde antepone la marca:

```java
// dentro de DocumentoConMarcaUrgente implements DocumentoClinico
private DocumentoClinico documentoBase;

public DocumentoConMarcaUrgente(DocumentoClinico documentoBase) {
    this.documentoBase = documentoBase;
}

public String getNombre() {
    return documentoBase.getNombre();
}

public int contarDocumentos() {
    return documentoBase.contarDocumentos();
}

public String ver() {
    return "[URGENTE] " + documentoBase.ver();
}
```

Fíjate que `DocumentoConMarcaUrgente` no le importa si `documentoBase` es un documento simple, un
adaptador de laboratorio, o incluso un proxy de imagen: solo conoce la interfaz `DocumentoClinico`. El
resto del diseño (el adaptador de laboratorio, la carpeta compuesta, el proxy de imagen) queda para ti.

## 📦 Entregable

```text
GestionDocumentosClinicos/
└── com/medisalud/
    ├── DocumentoClinico.java              (interfaz común)
    ├── ConsultaMedica.java                (documento simple)
    ├── ResultadoLaboratorioExterno.java   (sistema externo, Adapter)
    ├── ResultadoLaboratorioAdapter.java   (Adapter)
    ├── CarpetaClinica.java                (Composite)
    ├── DocumentoConMarcaUrgente.java      (Decorator)
    ├── ImagenEstudio.java                 (carga real, Proxy)
    ├── ImagenEstudioProxy.java            (Proxy)
    └── Demo.java
```

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| Carpeta con una consulta, un resultado de laboratorio adaptado, una radiografía marcada urgente (proxy) y una sub-carpeta con un documento más | `carpetaPaciente.contarDocumentos()` | `4`, sin ningún `instanceof` en `CarpetaClinica` |
| Antes de llamar a `ver()` sobre la radiografía | Mensaje `"Cargando imagen pesada de..."` | No aparece todavía |
| Al llamar a `ver()` sobre la radiografía por primera vez | Orden de los mensajes impresos | `"Cargando imagen pesada de..."` aparece recién ahí, seguido del contenido con la marca `[URGENTE]` |

## 📏 Criterios de evaluación

- `ResultadoLaboratorioAdapter` es el único lugar que conoce el formato del sistema externo de
  laboratorio.
- `CarpetaClinica.contarDocumentos()` no usa ningún `instanceof`: trata documentos y sub-carpetas de
  forma uniforme a través de `DocumentoClinico`.
- `DocumentoConMarcaUrgente` funciona sobre cualquier `DocumentoClinico`, sin necesitar una subclase por
  cada tipo de documento que se quiera marcar como urgente.
- `ImagenEstudioProxy` nunca crea la `ImagenEstudio` real hasta que `ver()` se llama por primera vez.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.
