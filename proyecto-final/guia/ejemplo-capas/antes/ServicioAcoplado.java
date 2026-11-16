/**
 * ❌ El "antes": el servicio crea su propia dependencia con new.
 * Para guardar en otro lugar hay que EDITAR esta clase.
 *
 * Ejecutar:  javac -d out0 antes/ServicioAcoplado.java && java -cp out0 ServicioAcoplado
 */
public class ServicioAcoplado {

    static class AlmacenArchivo {
        void guardar(String titulo) { System.out.println("Escribiendo en tareas.txt: " + titulo); }
    }

    private final AlmacenArchivo almacen = new AlmacenArchivo();   // atado al archivo para siempre

    void crear(String titulo) { almacen.guardar(titulo); }

    public static void main(String[] args) {
        new ServicioAcoplado().crear("Estudiar interfaces");
    }
}
