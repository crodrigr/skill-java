# 🧱 Ejemplo ejecutable — Interfaces, capas e inyección

Código completo de la [guía de repaso](../guia-repaso-interfaces-capas.md): una lista de
tareas por consola. Sin dependencias externas. Requiere JDK 17 o superior.

```bash
./ejecutar.sh            # guarda en tareas.txt (persiste entre ejecuciones)
./ejecutar.sh memoria    # guarda en memoria (se pierde al salir)
```

Menú: `1` crear, `2` listar, `3` completar, `0` salir.

## 🗂️ Estructura

```
src/com/tareas/
├── Principal.java                       ← Paso 4: ensambla e inyecta
├── vista/VistaConsola.java
├── controlador/ControladorTareas.java
├── service/ServicioTareas.java          ← Paso 3
├── repository/
│   ├── RepositorioTareas.java           ← Paso 1: contrato
│   ├── RepositorioTareasMemoria.java    ← Paso 2a
│   └── RepositorioTareasArchivo.java    ← Paso 2b
└── entity/Tarea.java
antes/ServicioAcoplado.java              ← Paso 0: el ❌ acoplado
Prueba.java                              ← prueba del servicio sin archivos
```

## 🪜 Paso a paso

```bash
# Paso 0 — el ❌ acoplado
javac -d out0 antes/ServicioAcoplado.java && java -cp out0 ServicioAcoplado

# Paso 1 y 2 — contrato e implementaciones (compilan; aún no hay nada que ejecutar)
javac -d out2 src/com/tareas/entity/*.java src/com/tareas/repository/*.java

# Paso 3 — el servicio
javac -d out3 src/com/tareas/entity/*.java src/com/tareas/repository/*.java src/com/tareas/service/*.java

# Paso 4 — todo conectado
./ejecutar.sh

# Prueba sin menú ni archivos (usa out/ generado por ejecutar.sh)
javac -cp out -d out Prueba.java && java -cp out Prueba
```

Limpieza: `rm -rf out0 out2 out3 out tareas.txt`.
