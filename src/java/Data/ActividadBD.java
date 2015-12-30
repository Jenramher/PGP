/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Business.Actividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author andreaescribano
 */
public class ActividadBD {

    public static void insertActividad(Actividad actividad) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int diaInicio, mesInicio, anoInicio, diaFin, mesFin, anoFin;
        int[] fechaInicio = getFechaInt(actividad.getFechaInicio());
        diaInicio = fechaInicio[0];
        mesInicio = fechaInicio[1];
        anoInicio = fechaInicio[2];
        int[] fechaFin = getFechaInt(actividad.getFechaFin());
        diaFin = fechaFin[0];
        mesFin = fechaFin[1];
        anoFin = fechaFin[2];
        String query = "INSERT INTO Actividades (login, descripcion, rol, duracionEstimada, diaInicio, mesInicio, anoInicio, diaFin, mesFin, anoFin, duracionReal, estado, idFase) VALUES ('"
                + actividad.getLogin() + "','"
                + actividad.getDescripcion() + "','"
                + actividad.getRolNecesario() + "',"
                + actividad.getDuracionEstimada() + ","
                + diaInicio + ","
                + mesInicio + ","
                + anoInicio + ","
                + diaFin + ","
                + mesFin + ","
                + anoFin + ","
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

    public static ArrayList<Actividad> selectActividades(int idFase) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Actividades a WHERE idFase=? ORDER BY a.anoInicio, a.mesInicio, a.diaInicio ASC";
        ArrayList<Actividad> actividades = new ArrayList<Actividad>();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idFase);
            rs = ps.executeQuery();
            while (rs.next()) {
                String fechaInicio = String.format("%02d/%02d/%04d", rs.getInt(6), rs.getInt(7),rs.getInt(8));
                String fechaFin = String.format("%02d/%02d/%04d", rs.getInt(9), rs.getInt(10),rs.getInt(11));
                Actividad a = new Actividad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), fechaInicio, fechaFin, rs.getInt(12), rs.getBoolean(13), idFase);
                actividades.add(a);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividades;
    }

    public static Actividad selectActividad(int idActividad) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Actividades WHERE id=?";
        Actividad a = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idActividad);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fechaInicio = String.format("%02d/%02d/%04d", rs.getInt(7), rs.getInt(6),rs.getInt(8));
                String fechaFin = String.format("%02d/%02d/%04d", rs.getInt(10), rs.getInt(9),rs.getInt(11));
                a = new Actividad(idActividad, rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), fechaInicio, fechaFin, rs.getInt(12), rs.getBoolean(13), rs.getInt(14));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    
    public static ArrayList<Actividad> selectActividades(String login) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Actividades WHERE login=?";
        ArrayList<Actividad> actividades = new ArrayList<Actividad>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                //String fechaInicio = String.format("%02d/%02d/%04d", rs.getInt(6), rs.getInt(7),rs.getInt(8));
                //String fechaFin = String.format("%02d/%02d/%04d", rs.getInt(9), rs.getInt(10),rs.getInt(11));
                //Actividad a = new Actividad(rs.getInt(1), login, rs.getString(3), rs.getString(4), rs.getInt(5), fechaInicio, fechaFin, rs.getInt(12), rs.getBoolean(13), rs.getInt(14));
                Actividad a = new Actividad(rs.getInt(1), login, rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(8), rs.getInt(10));
                actividades.add(a);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividades;
    }
    
    public static void updateActividad(Actividad a) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        int diaInicio, mesInicio, anoInicio, diaFin, mesFin, anoFin;
        int[] fechaInicio = getFechaInt(a.getFechaInicio());
        diaInicio = fechaInicio[0];
        mesInicio = fechaInicio[1];
        anoInicio = fechaInicio[2];
        int[] fechaFin = getFechaInt(a.getFechaFin());
        diaFin = fechaFin[0];
        mesFin = fechaFin[1];
        anoFin = fechaFin[2];
        String query = "UPDATE Actividades SET login=?, descripcion=?, rol=?, duracionEstimada=?, diaInicio=?, mesInicio=?, anoInicio=?, diaFin=?, mesFin=?, anoFin=?, duracionReal=?, estado=?, idFase=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, a.getLogin());
            ps.setString(2, a.getDescripcion());
            ps.setString(3, a.getRolNecesario());
            ps.setInt(4, a.getDuracionEstimada());
            ps.setInt(5, diaInicio);
            ps.setInt(6, mesInicio);
            ps.setInt(7, anoInicio);
            ps.setInt(8, diaFin);
            ps.setInt(9, mesFin);
            ps.setInt(10, anoFin);
            ps.setInt(11, a.getDuracionReal());
            ps.setBoolean(12, a.getEstado());
            ps.setInt(13, a.getIdFase());
            ps.setInt(14, a.getIdentificador());
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
