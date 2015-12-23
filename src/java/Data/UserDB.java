/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jennifer
 */
public class UserDB {

    //Es una clase prueba que tiene que tener estos metodos, luego
    // puede que pongamos una entity o lo que sea
    
    /*Esto es como en SSW*/
    public static boolean exist(String user) {
        //
        ConnectionPool pool = ConnectionPool.getInstance(); 
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null; 
        String query = "SELECT login FROM Usuarios " 
                + "WHERE login = ?"; 
        try {
            ps = connection.prepareStatement(query); 
            ps.setString(1, user); 
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close(); 
            ps.close(); 
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return false;
        }

    }

    public static boolean comprobarPassword(String password) {
        ConnectionPool pool = ConnectionPool.getInstance(); 
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null; 
        String query = "SELECT pass FROM usuario " 
                + "WHERE pass = ?"; 
        try {
            ps = connection.prepareStatement(query); 
            ps.setString(1, password); 
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close(); 
            ps.close(); 
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return false;
        }
    
    }
    
    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query="INSERT INTO Usuarios (login, pass, tipo, nif, maxProy,"
                + "infoGeneral) VALUES ('"
                + user.getLogin()+"','"
                + user.getPass()+"','"
                + user.getTipo()+ "','"
                + user.getNif()+"','"
                + user.getMaxProy()+"','"
                + user.getInfoGeneral()+ "')";
                
        try {
            ps = connection.prepareStatement(query);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return 0;
        }
    }

    
}
