package com.tareas.persistence.dao;

import com.tareas.negocio.model.Tarea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO: el ÚNICO lugar con SQL y JDBC. Habla con la tabla `tarea` y nada más.
 * No aplica reglas de negocio ni sabe del contrato RepositorioTareas.
 *
 * Tabla: tarea(id INT PRIMARY KEY, titulo VARCHAR(200), completada BOOLEAN)
 */
public class TareaDAO {

    private final String url;
    private final String usuario;
    private final String clave;

    public TareaDAO(String url, String usuario, String clave) {
        this.url = url;
        this.usuario = usuario;
        this.clave = clave;
        crearTablaSiNoExiste();
    }

    /** INSERT o UPDATE según exista el id. */
    public void guardar(Tarea tarea) {
        String sql = "INSERT INTO tarea (id, titulo, completada) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE titulo = VALUES(titulo), completada = VALUES(completada)";
        try (Connection c = conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, tarea.getId());
            ps.setString(2, tarea.getTitulo());
            ps.setBoolean(3, tarea.isCompletada());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo guardar la tarea", e);
        }
    }

    /** null si no existe. */
    public Tarea buscarPorId(int id) {
        try (Connection c = conectar();
             PreparedStatement ps = c.prepareStatement("SELECT id, titulo, completada FROM tarea WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? aTarea(rs) : null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo buscar la tarea " + id, e);
        }
    }

    public List<Tarea> listarTodas() {
        List<Tarea> tareas = new ArrayList<>();
        try (Connection c = conectar();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, titulo, completada FROM tarea ORDER BY id")) {
            while (rs.next()) tareas.add(aTarea(rs));
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo listar las tareas", e);
        }
        return tareas;
    }

    private Tarea aTarea(ResultSet rs) throws SQLException {
        return new Tarea(rs.getInt("id"), rs.getString("titulo"), rs.getBoolean("completada"));
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, clave);
    }

    private void crearTablaSiNoExiste() {
        try (Connection c = conectar(); Statement st = c.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS tarea ("
                    + "id INT PRIMARY KEY, titulo VARCHAR(200) NOT NULL, completada BOOLEAN NOT NULL)");
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo conectar a la base de datos", e);
        }
    }
}
