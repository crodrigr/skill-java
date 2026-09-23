# 🔴 Avanzado 01 — Corregir un programa con errores de herencia

## 🧩 Problema

**Biblioteca Universitaria** tiene un programa `RevisionDePrestamos` con **dos errores de compilación**
de herencia, uno detrás del otro. Corrígelos en dos rondas.

## 💻 Código o contexto de partida

**Ronda 1**:

```java
class Libro extends MaterialBibliografico {
    private int paginas;

    public Libro(String titulo, int paginas) {
        this.paginas = paginas;
    }

    @Override
    public int calcularDiasPrestamo() {
        return 14;
    }
}
```

## 🧪 Casos de prueba

| Ronda | Síntoma | Corrección |
|---|---|---|
| 1 | El compilador rechaza el constructor de `Libro` | ? |
| 2 (tras corregir la 1) | El compilador rechaza `calcularDiasPrestamo()` de `Libro` | ? |

## 📏 Criterios de evaluación de la solución

- **Ronda 1**: agrega `super(titulo);` como primera línea del constructor de `Libro`.
- **Ronda 2**: cambia el modificador de `calcularDiasPrestamo()` de `Libro` de `protected` a `public`
  (no puede ser más restrictivo que el de `MaterialBibliografico`).
- El programa final compila y produce `Cien años de soledad` y `14`.

## 🚧 Restricciones

- No cambies el nombre de ningún método o clase.
- Corrige un error a la vez, verificando el panel Problems después de cada corrección.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-4**: invocar `super(...)` en el constructor de una subclase.
- **RA-9**: sobreescribir un método con `@Override`, respetando su firma y su acceso.
- **RA-15**: identificar y corregir los errores frecuentes de esta etapa.
