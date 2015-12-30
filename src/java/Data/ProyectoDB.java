/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author andreaescribano
 */
public class ProyectoDB {

    public static void insert(Proyecto proyecto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO Proyectos (nombre,diaInicio,mesInicio,anoInicio,diaFin,mesFin,anoFin,estado,login) VALUES ('"
                + proyecto.getNombre()+ "','"
                + proyecto.getDiaInicio() + "','"
                + proyecto.getMesInicio() + "','"
                + proyecto.getAnoInicio() + "','"
                + proyecto.getDiaFin() + "','"
                + proyecto.getMesFin() + "','"
                + proyecto.getAnoFin() + "','"
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

    public static ArrayList<Proyecto> selectProyectos(String usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Proyectos WHERE login=?";
        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            while(rs.next()){
                Proyecto p = new Proyecto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9).charAt(0), usuario);
                proyectos.add(p);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proyectos;
    }
    
}
