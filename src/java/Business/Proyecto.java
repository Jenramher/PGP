/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.ProyectoDB;
import java.sql.Date;

/**
 *
 * @author andreaescribano
 */
public class Proyecto {

    private int identificador;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private char estado;
    private String login;

    public Proyecto(String nombre, Date fechaInicio, Date fechaFin, char estado, String login) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.login = login;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public char getEstado() {
        return estado;
    }

    public String getLogin() {
        return login;
    }
    
    public static void guardarNuevoProyecto(String nombre, Date fechaInicio, Date fechaFin, char estado, String usuario) {
        ProyectoDB.insert(new Proyecto(nombre, fechaInicio, fechaFin, estado, usuario));
    }
    
}
