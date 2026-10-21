package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Paso 2b — Implementación "de base de datos".
 *
 * Para que el ejemplo se ejecute SIN Docker ni MySQL, la conexión JDBC está simulada:
 * se imprime el SQL que se ejecutaría y los datos se guardan en un mapa que hace de tabla.
 * En el proyecto final, cada método usa PreparedStatement sobre una Connection real.
 */
public class PacienteDAO implements RepositorioPacientes {

    private final Map<String, Paciente> tablaPacientes = new LinkedHashMap<>();

    @Override
    public void guardar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (codigo, nombre_completo, edad) VALUES (?, ?, ?)";
        System.out.println("   [SQL] " + sql + "  -- " + paciente.getCodigo() + ", "
                + paciente.getNombreCompleto() + ", " + paciente.getEdad());
        tablaPacientes.put(paciente.getCodigo(), paciente);
    }

    @Override
    public Paciente buscarPorCodigo(String codigo) {
        System.out.println("   [SQL] SELECT * FROM pacientes WHERE codigo = ?  -- " + codigo);
        return tablaPacientes.get(codigo);
    }

    @Override
    public List<Paciente> listarTodos() {
        System.out.println("   [SQL] SELECT * FROM pacientes");
        return new ArrayList<>(tablaPacientes.values());
    }
}
