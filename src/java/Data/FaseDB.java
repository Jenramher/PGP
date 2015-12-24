/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Fase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author andreaescribano
 */
public class FaseDB {

    public static void insert(Fase fase) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO Fases (nombre, fechaInicio, fechaFin, estado, idProyecto) VALUES ('"
                + fase.getNombre() + "','"
                + fase.getFechaInicio() + "','"
                + fase.getFechaFin() + "','"
                + fase.getEstado() + "',"
                + fase.getIdProyecto() + ")";

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
