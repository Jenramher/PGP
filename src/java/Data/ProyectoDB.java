/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author andreaescribano
 */
public class ProyectoDB {

    public static void insert(Proyecto proyecto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO Proyectos (nombre, fechaInicio, fechaFin, estado, login) VALUES ('"
                + proyecto.getNombre() + "','"
                + proyecto.getFechaInicio() + "','"
                + proyecto.getFechaFin() + "','"
                + proyecto.getEstado() + "','"
                + proyecto.getLogin() + "')";

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
