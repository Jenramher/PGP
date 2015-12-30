/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Actividad;
import Business.Calendario;
import Business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gil
 */
public class CalendarioDB {
    
    public static List<Calendario> obtenerVacaciones(String login){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query ="SELECT * FROM Calendario WHERE tipo='V' AND login=?";
        List<Calendario> calendario = new ArrayList<Calendario>();
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                    Calendario cal = new Calendario(rs.getString(1),rs.getString(2),login,rs.getString(4),rs.getString(5),rs.getInt(6));
                    calendario.add(cal);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return calendario;
        
    }
    
}
