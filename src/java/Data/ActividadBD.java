/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Actividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author andreaescribano
 */
public class ActividadBD {

    public static void insert(Actividad actividad) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO Actividades (descripcion, rol, duracionEstimada, fechaInicio, fechaFin, duracionReal, estado, idFase) VALUES ('"
                + actividad.getDescripcion() + "','"
                + actividad.getRolNecesario() + "',"
                + actividad.getDuracionEstimada() + ",'"
                + actividad.getFechaComienzo() + "','"
                + actividad.getFechaFin() + "',"
                + actividad.getDuracionReal() + ",'"
                + actividad.getEstado() + "',"
                + actividad.getIdFase() + ")";

        try {
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
