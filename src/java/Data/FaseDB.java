/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Fase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author andreaescribano
 */
public class FaseDB {

    public static void insert(Fase fase) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO Fases (nombre, diaInicio, mesInicio, anoInicio, "
                + "diaFin, mesFin, anoFin, estado, idProyecto) VALUES ('"
                + fase.getNombre() + "','"
                + fase.getDiaInicio()+ "','"
                + fase.getMesInicio()+ "','"
                + fase.getAnoInicio()+ "','"
                + fase.getDiaFin() + "','"
                + fase.getMesFin() + "','"
                + fase.getAnoFin() + "','"
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

    public static ArrayList<Fase> selectFases(int idProyecto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Fases WHERE idProyecto=?";
        ArrayList<Fase> fases = new ArrayList<Fase>();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idProyecto);
            rs = ps.executeQuery();
            while(rs.next()){
                Fase f = new Fase(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9).charAt(0),idProyecto);
                fases.add(f);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fases;
    }

}
