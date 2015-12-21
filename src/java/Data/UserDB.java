/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

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
        String query = "SELECT correo FROM usuario " 
                + "WHERE correo = ?"; 
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
        String query = "SELECT password FROM usuario " 
                + "WHERE password = ?"; 
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
    
}
