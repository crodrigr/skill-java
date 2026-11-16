# 🔑 Soluciones de los ejercicios — Módulo 17

> Material docente, no enlazar desde la audiencia estudiante.

## 🟢 Básico 01 — Identificar uso de la clase File

El programa usa una lista fija de dos nombres en el código, en vez de consultar la carpeta real: por eso
no menciona el tercer préstamo, agregado después de escribir esa lista. La lista quedó desactualizada.

## 🟡 Intermedio 01 — Aplicar la clase File

```java
package com.biblioteca;

import java.io.File;
import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        File carpeta = new File("prestamos_activos");

        System.out.println("La carpeta existe: " + carpeta.exists());
        System.out.println("Prestamos activos (listado real):");

        File[] archivos = carpeta.listFiles();
        Arrays.sort(archivos);
        for (File archivo : archivos) {
            System.out.println("- " + archivo.getName());
        }
    }
}
```

```text
La carpeta existe: true
Prestamos activos (listado real):
- prestamo1.txt
- prestamo2.txt
- prestamo3.txt
```

## 🟢 Básico 02 — Identificar violación de lectura de archivos

El catálogo está hardcodeado directamente en el código: agregar un libro nuevo exigiría modificar y
recompilar el programa, en vez de solo actualizar un archivo de datos externo.

## 🟡 Intermedio 02 — Aplicar lectura de archivos

```java
package com.biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        System.out.println("Catalogo de libros (leido desde libros.txt):");
        try (BufferedReader lector = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer libros.txt: " + e.getMessage());
        }
    }
}
```

```text
Catalogo de libros (leido desde libros.txt):
El Quijote,Cervantes
Cien anios de soledad,Garcia Marquez
Rayuela,Cortazar
```

## 🟢 Básico 03 — Identificar violación de escritura de archivos

Las solicitudes solo se imprimen por consola, sin escribirse en ningún archivo: al cerrar y reabrir el
programa, no queda ningún rastro de las solicitudes anteriores.

## 🟡 Intermedio 03 — Aplicar escritura de archivos

```java
package com.biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        String[] solicitudes = {
            "Solicitud: prestamo de El Quijote para Carla Nunez",
            "Solicitud: prestamo de Rayuela para Diego Perez"
        };

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("solicitudes.txt"))) {
            for (String solicitud : solicitudes) {
                escritor.write(solicitud);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir solicitudes.txt: " + e.getMessage());
            return;
        }

        File archivo = new File("solicitudes.txt");
        System.out.println("El archivo existe despues de escribirlo: " + archivo.exists());

        System.out.println("Contenido real leido de vuelta desde solicitudes.txt:");
        try (BufferedReader lector = new BufferedReader(new FileReader("solicitudes.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer solicitudes.txt: " + e.getMessage());
        }
    }
}
```

```text
El archivo existe despues de escribirlo: true
Contenido real leido de vuelta desde solicitudes.txt:
Solicitud: prestamo de El Quijote para Carla Nunez
Solicitud: prestamo de Rayuela para Diego Perez
```

## 🟢 Básico 04 — Identificar archivo temporal sin borrar

El archivo `solicitudes_procesadas.txt` nunca se borra, aunque ya fue procesado: ejecutado
repetidamente, queda acumulado en el disco sin ningún beneficio real de conservarlo.

## 🟡 Intermedio 04 — Aplicar borrado de archivos

```java
package com.biblioteca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("solicitudes_procesadas.txt"))) {
            escritor.write("Solicitudes ya procesadas");
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo: " + e.getMessage());
            return;
        }

        File archivo = new File("solicitudes_procesadas.txt");
        System.out.println("El archivo existe antes de borrarlo: " + archivo.exists());

        boolean borrado = archivo.delete();
        System.out.println("El borrado tuvo exito: " + borrado);
        System.out.println("El archivo existe despues de borrarlo: " + archivo.exists());
    }
}
```

```text
El archivo existe antes de borrarlo: true
El borrado tuvo exito: true
El archivo existe despues de borrarlo: false
```

## 🟢 Básico 05 — Identificar serialización manual frágil

`SocioBiblioteca` no implementa `Serializable`; cada campo se escribe a mano, en un orden fijo. Agregar un campo
nuevo exigiría actualizar tanto la escritura como cualquier código que reconstruya el objeto, respetando
ese mismo orden — frágil y propenso a errores.

## 🟡 Intermedio 05 — Aplicar serialización

```java
package com.biblioteca;

import java.io.Serializable;

public class SocioBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String tipo;
    private int prestamosActivos;

    public SocioBiblioteca(String nombre, String tipo, int prestamosActivos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.prestamosActivos = prestamosActivos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrestamosActivos() {
        return prestamosActivos;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - " + prestamosActivos + " prestamos activos";
    }
}
```

```java
package com.biblioteca;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Demo {

    public static void main(String[] args) {
        SocioBiblioteca socio = new SocioBiblioteca("Carla Nunez", "ESTUDIANTE", 2);

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("socio.ser"))) {
            salida.writeObject(socio);
            System.out.println("SocioBiblioteca serializado completo en socio.ser: " + socio);
        } catch (IOException e) {
            System.out.println("No se pudo serializar el socio: " + e.getMessage());
        }
    }
}
```

```text
SocioBiblioteca serializado completo en socio.ser: Carla Nunez (ESTUDIANTE) - 2 prestamos activos
```

## 🟢 Básico 06 — Identificar deserialización manual frágil

La reconstrucción depende de conocer y respetar el orden exacto de los campos en el archivo. Si el orden
cambia, o el archivo se generó con otro formato, la reconstrucción falla o produce un objeto con datos
incorrectos, sin ningún aviso claro del error.

## 🟡 Intermedio 06 — Aplicar deserialización

```java
package com.biblioteca;

import java.io.Serializable;

public class SocioBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String tipo;
    private int prestamosActivos;

    public SocioBiblioteca(String nombre, String tipo, int prestamosActivos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.prestamosActivos = prestamosActivos;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - " + prestamosActivos + " prestamos activos";
    }
}
```

```java
package com.biblioteca;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Escritor {

    public static void main(String[] args) {
        SocioBiblioteca socio = new SocioBiblioteca("Carla Nunez", "ESTUDIANTE", 2);

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("socio.ser"))) {
            salida.writeObject(socio);
            System.out.println("Primera ejecucion: socio serializado y programa terminado.");
        } catch (IOException e) {
            System.out.println("No se pudo serializar el socio: " + e.getMessage());
        }
    }
}
```

```text
Primera ejecucion: socio serializado y programa terminado.
```

```java
package com.biblioteca;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Lector {

    public static void main(String[] args) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("socio.ser"))) {
            SocioBiblioteca socio = (SocioBiblioteca) entrada.readObject();
            System.out.println("Segunda ejecucion: socio deserializado: " + socio);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo deserializar el socio: " + e.getMessage());
        }
    }
}
```

```text
Segunda ejecucion: socio deserializado: Carla Nunez (ESTUDIANTE) - 2 prestamos activos
```

## 🔴 Avanzado 01 — Corregir un diseño con dos sub-temas técnicos ausentes

**Corrección 1 (escritura) y Corrección 2 (borrado)**, aplicadas juntas:

```java
package com.biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        String[] renovaciones = {
            "Renovacion: El Quijote para Carla Nunez",
            "Renovacion: Rayuela para Diego Perez"
        };

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("renovaciones_temp.txt"))) {
            for (String renovacion : renovaciones) {
                escritor.write(renovacion);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo temporal: " + e.getMessage());
            return;
        }

        // El resultado final si se persiste en un archivo real, releido para confirmar.
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("renovaciones_finales.txt"));
             BufferedReader lector = new BufferedReader(new FileReader("renovaciones_temp.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir renovaciones_finales.txt: " + e.getMessage());
            return;
        }

        // El archivo temporal ya no hace falta: se borra.
        File temporal = new File("renovaciones_temp.txt");
        boolean borrado = temporal.delete();
        System.out.println("Archivo temporal borrado: " + borrado);

        File finalResultado = new File("renovaciones_finales.txt");
        System.out.println("El resultado final existe: " + finalResultado.exists());
        System.out.println("El archivo temporal ya no existe: " + !temporal.exists());
    }
}
```

```text
Archivo temporal borrado: true
El resultado final existe: true
El archivo temporal ya no existe: true
```

Comparado con la versión original: el resultado final ahora se escribe en `renovaciones_finales.txt` (en
vez de solo imprimirse), y el archivo temporal `renovaciones_temp.txt` se borra una vez que ya no hace
falta, confirmado con `exists()` antes (`true`) y después (`false`) del borrado.

## 🏆 Desafío 01 — Diseñar un caso nuevo combinando texto plano y serialización

Una solución posible: un archivo de texto `devoluciones_del_dia.txt` registra las devoluciones simples
del día (lectura/escritura), mientras que cada `SocioBiblioteca` completo se serializa a su propio archivo
`.ser` (`Serializable` + `ObjectOutputStream`), recuperable en una segunda ejecución separada
(`ObjectInputStream`).

```java
package com.biblioteca;

import java.io.Serializable;

public class SocioBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String tipo;
    private int devolucionesRealizadas;

    public SocioBiblioteca(String nombre, String tipo, int devolucionesRealizadas) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.devolucionesRealizadas = devolucionesRealizadas;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - " + devolucionesRealizadas + " devoluciones realizadas";
    }
}
```

```java
package com.biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Escritor {

    public static void main(String[] args) {
        String[] devoluciones = {
            "Devolucion: El Quijote, Carla Nunez",
            "Devolucion: Rayuela, Diego Perez",
            "Devolucion: Cien anios de soledad, Elena Ruiz"
        };

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("devoluciones_del_dia.txt"))) {
            for (String devolucion : devoluciones) {
                escritor.write(devolucion);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir devoluciones_del_dia.txt: " + e.getMessage());
            return;
        }

        System.out.println("Devoluciones del dia (releidas desde el archivo):");
        try (BufferedReader lector = new BufferedReader(new FileReader("devoluciones_del_dia.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer devoluciones_del_dia.txt: " + e.getMessage());
        }

        SocioBiblioteca socio = new SocioBiblioteca("Carla Nunez", "ESTUDIANTE", 1);
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("socio.ser"))) {
            salida.writeObject(socio);
            System.out.println("SocioBiblioteca serializado: " + socio);
        } catch (IOException e) {
            System.out.println("No se pudo serializar el socio: " + e.getMessage());
        }
    }
}
```

```text
Devoluciones del dia (releidas desde el archivo):
Devolucion: El Quijote, Carla Nunez
Devolucion: Rayuela, Diego Perez
Devolucion: Cien anios de soledad, Elena Ruiz
SocioBiblioteca serializado: Carla Nunez (ESTUDIANTE) - 1 devoluciones realizadas
```

```java
package com.biblioteca;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Lector {

    public static void main(String[] args) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("socio.ser"))) {
            SocioBiblioteca socio = (SocioBiblioteca) entrada.readObject();
            System.out.println("Segunda ejecucion: socio deserializado: " + socio);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo deserializar el socio: " + e.getMessage());
        }
    }
}
```

```text
Segunda ejecucion: socio deserializado: Carla Nunez (ESTUDIANTE) - 1 devoluciones realizadas
```
