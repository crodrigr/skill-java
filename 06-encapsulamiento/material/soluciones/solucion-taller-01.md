# 🔑 Solución — Taller 01: Ficha encapsulada de un paciente

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/06-encapsulamiento.md`).

## 🌳 Árbol de archivos del proyecto final

```text
FichaEncapsuladaDePaciente
└── src
    ├── Paciente.java
    └── DemoPaciente.java
```

## 💻 Archivo: Paciente.java

```java
public class Paciente {
    private String nombreCompleto;
    private int edad;
    private String telefono;

    public Paciente(String nombreCompleto, int edad, String telefono) {
        this.nombreCompleto = nombreCompleto;
        setEdad(edad);
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            System.out.println("Edad inválida (" + edad + "): se conserva la edad actual (" + this.edad + ")");
            return;
        }
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void mostrarFicha() {
        System.out.println("Paciente: " + nombreCompleto);
        System.out.println("Edad: " + edad);
        System.out.println("Teléfono: " + telefono);
    }
}
```

## 💻 Archivo: DemoPaciente.java

```java
public class DemoPaciente {

    public static void main(String[] args) {
        Paciente paciente1 = new Paciente("Ana Torres", 34, "555-0101");
        paciente1.mostrarFicha();
        System.out.println("---");

        Paciente paciente2 = new Paciente("Luis Gómez", -5, "555-0102");
        paciente2.mostrarFicha();
    }
}
```

## ✅ Resultado esperado

```text
Paciente: Ana Torres
Edad: 34
Teléfono: 555-0101
---
Edad inválida (-5): se conserva la edad actual (0)
Paciente: Luis Gómez
Edad: 0
Teléfono: 555-0102
```

## 🧭 Explicación paso a paso

1. El constructor recibe los tres datos, pero llama a `setEdad(edad)` en vez de asignar `edad`
   directamente, para que la validación se aplique también al crear el objeto.
2. `setEdad` rechaza valores negativos, dejando `edad` en `0` (su valor por defecto) y avisando por
   consola.
3. `mostrarFicha()` imprime los tres atributos, siempre a través del objeto que la invoca.
4. `getNombreCompleto()`, `getEdad()` y `getTelefono()` son los únicos accesos externos a los
   atributos, todos `private`.

## 🐞 Errores comunes observados

| Error | Tipo | Cómo se ve | Corrección |
|---|---|---|---|
| Acceder a un atributo `private` directamente desde otra clase | de compilación | `The field Paciente.edad is not visible` en el panel Problems | usar `setEdad(...)` en vez de `paciente.edad = ...` |
| Asignar un atributo directamente en vez de con su `set` (dentro de la propia clase) | lógico | un valor inválido se cuela sin que la validación lo detecte; el panel Problems no marca ningún problema | usar siempre el `set` correspondiente, incluso desde el constructor y desde otros métodos de la misma clase |
| Olvidar usar el `set` desde el constructor | lógico | el objeto se crea con un valor inválido, sin ningún aviso | llamar al `set` en vez de asignar el atributo directamente en el constructor |
