import com.tareas.persistence.repository.RepositorioTareas;
import com.tareas.persistence.repository.RepositorioTareasMemoria;
import com.tareas.negocio.service.ServicioTareas;

/**
 * Prueba del servicio SIN archivos ni menú: se inyecta la versión en memoria.
 * Ejecutar (con out/ ya compilado):  javac -cp out -d out Prueba.java && java -cp out Prueba
 */
public class Prueba {

    public static void main(String[] args) {
        RepositorioTareas repositorio = new RepositorioTareasMemoria();
        ServicioTareas servicio = new ServicioTareas(repositorio);

        servicio.crear("Estudiar interfaces");
        servicio.crear("Hacer el ejercicio");
        servicio.completar(1);

        servicio.listar().forEach(System.out::println);
        System.out.println(servicio.listar().size() == 2 ? "OK: 2 tareas" : "FALLO");

        try {
            servicio.crear("   ");
            System.out.println("FALLO: debió rechazar el título vacío");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }
    }
}
