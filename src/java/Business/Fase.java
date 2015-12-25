/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.FaseDB;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author andreaescribano
 */
public class Fase {

    private int id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private char estado;
    private int idProyecto;

    public Fase(String nombre, Date fechaInicio, Date fechaFin, char estado, int idProyecto) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idProyecto = idProyecto;
    }
    
    public Fase(int id, String nombre, Date fechaInicio, Date fechaFin, char estado, int idProyecto) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idProyecto = idProyecto;
    }

    public int getId() {
        return id;
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

    public int getIdProyecto() {
        return idProyecto;
    }
    
    public static void crearNuevaFase(int idProyecto, String nombre, Date fechaInicio, Date fechaFin, char estado) {
        FaseDB.insert(new Fase(nombre, fechaInicio, fechaFin, estado, idProyecto));
    }
    
    public static ArrayList<Fase> getFase(int idProyecto) {
        return FaseDB.selectFases(idProyecto);
    }

}
