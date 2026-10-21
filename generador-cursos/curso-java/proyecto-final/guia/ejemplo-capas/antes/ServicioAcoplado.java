/**
 * ❌ El "antes": el servicio crea su propia dependencia con new.
 * Para cambiar de almacenamiento hay que EDITAR esta clase.
 * Ejecutar:  javac antes/ServicioAcoplado.java && java -cp antes ServicioAcoplado
 */
public class ServicioAcoplado {

    static class PacienteDAO {
        void guardar(String nombre) { System.out.println("INSERT INTO pacientes ... " + nombre); }
    }

    private final PacienteDAO repositorio = new PacienteDAO();   // atado a MySQL para siempre

    void registrar(String nombre) { repositorio.guardar(nombre); }

    public static void main(String[] args) {
        new ServicioAcoplado().registrar("Ana Pérez");
    }
}
