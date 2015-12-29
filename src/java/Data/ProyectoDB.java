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
        int diaInicio, mesInicio, anoInicio, diaFin, mesFin, anoFin;
        int[] fechaInicio = getFechaInt(proyecto.getFechaInicio());
        diaInicio = fechaInicio[0];
        mesInicio = fechaInicio[1];
        anoInicio = fechaInicio[2];
        int[] fechaFin = getFechaInt(proyecto.getFechaFin());
        diaFin = fechaFin[0];
        mesFin = fechaFin[1];
        anoFin = fechaFin[2];
        String query = "INSERT INTO Proyectos (nombre, diaInicio, mesInicio, anoInicio, diaFin, mesFin, anoFin, estado, login) VALUES ('"
                + proyecto.getNombre() + "',"
                + diaInicio + ","
                + mesInicio + ","
                + anoInicio + ","
                + diaFin + ","
                + mesFin + ","
                + anoFin + ",'"
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
        String query = "SELECT * FROM Proyectos WHERE login=? ORDER BY anoInicio, mesInicio, diaInicio ASC";
        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                String fechaInicio = String.format("%02d/%02d/%04d", rs.getInt(3), rs.getInt(4),rs.getInt(5));
                String fechaFin = String.format("%02d/%02d/%04d", rs.getInt(6), rs.getInt(7),rs.getInt(8));
                Proyecto p = new Proyecto(rs.getInt(1), rs.getString(2), fechaInicio, fechaFin, rs.getString(9).charAt(0), usuario);
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

    public static Proyecto selectProyecto(int idProyecto) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Proyectos WHERE id=?";
        Proyecto p = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idProyecto);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fechaInicio = String.format("%02d/%02d/%04d", rs.getInt(4), rs.getInt(3),rs.getInt(5));
                String fechaFin = String.format("%02d/%02d/%04d", rs.getInt(7), rs.getInt(6),rs.getInt(8));
                p = new Proyecto(idProyecto, rs.getString(2), fechaInicio, fechaFin, rs.getString(9).charAt(0), rs.getString(10));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static void updateProyecto(Proyecto p) {
        System.out.println(p.getEstado());
        System.out.println(p.getIdentificador());
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int diaInicio, mesInicio, anoInicio, diaFin, mesFin, anoFin;
        int[] fechaInicio = getFechaInt(p.getFechaInicio());
        diaInicio = fechaInicio[0];
        mesInicio = fechaInicio[1];
        anoInicio = fechaInicio[2];
        int[] fechaFin = getFechaInt(p.getFechaFin());
        diaFin = fechaFin[0];
        mesFin = fechaFin[1];
        anoFin = fechaFin[2];
        String query = "UPDATE Proyectos SET nombre=?, diaInicio=?, mesInicio=?, anoInicio=?, diaFin=?, mesFin=?, anoFin=?, estado=?, login=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, p.getNombre());
            ps.setInt(2, diaInicio);
            ps.setInt(3, mesInicio);
            ps.setInt(4, anoInicio);
            ps.setInt(5, diaFin);
            ps.setInt(6, mesFin);
            ps.setInt(7, anoFin);
            ps.setString(8, ""+p.getEstado());
            ps.setString(9, p.getLogin());
            ps.setInt(10, p.getIdentificador());
            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static int[] getFechaInt(String fecha) {
        int[] fechas = new int[3];
        int cont = 0;
        String num = "";
        for (int i = 0; i < fecha.length(); i++) {
            if (fecha.charAt(i) != '/') {
                num += fecha.charAt(i);
                if (cont == 1) {
                    fechas[1] = Integer.parseInt(num);
                    num = "";
                } else if (cont == 3) {
                    fechas[0] = Integer.parseInt(num);
                    num = "";
                } else if (cont == 7) {
                    fechas[2] = Integer.parseInt(num);
                    num = "";
                }
                cont++;
            }
        }
        return fechas;
    }

}
