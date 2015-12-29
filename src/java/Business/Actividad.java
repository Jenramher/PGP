/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.ActividadBD;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author andreaescribano
 */
public class Actividad implements Serializable{

    private int identificador;
    private String descripcion;
    private String rolNecesario;
    private int duracionEstimada;
    private String fechaInicio;
    private String fechaFin;
    private int duracionReal;
    private boolean estado;
    private int idFase;

    public Actividad(String descripcion, String rolNecesario, int duracionEstimada, String fechaInicio, String fechaFin, int duracionReal, boolean estado, int idFase) {
        this.descripcion = descripcion;
        this.rolNecesario = rolNecesario;
        this.duracionEstimada = duracionEstimada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracionReal = duracionReal;
        this.estado = estado;
        this.idFase = idFase;
    }

    public Actividad(int identificador, String descripcion, String rolNecesario, int duracionEstimada, String fechaInicio, String fechaFin, int duracionReal, boolean estado, int idFase) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.rolNecesario = rolNecesario;
        this.duracionEstimada = duracionEstimada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracionReal = duracionReal;
        this.estado = estado;
        this.idFase = idFase;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRolNecesario() {
        return rolNecesario;
    }

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public boolean isEstado() {
        return estado;
    }


    public int getDuracionReal() {
        return duracionReal;
    }

    public boolean getEstado() {
        return estado;
    }

    public int getIdFase() {
        return idFase;
    }

    public static void guardarNuevaActividad(Actividad a) {
        ActividadBD.insertActividad(a);
    }

    public static ArrayList<Actividad> getFase(int idFase) {
        return ActividadBD.selectActividades(idFase);   
    }

    public static void actualizarActividad(Actividad a) {
        ActividadBD.updateActividad(a);
    }

    public static Actividad getActivity(int idActividad) {
        return ActividadBD.selectActividad(idActividad);
    }

    
}
