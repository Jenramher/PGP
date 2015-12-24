/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.ActividadBD;
import java.sql.Date;

/**
 *
 * @author andreaescribano
 */
public class Actividad {

    private int identificador;
    private String descripcion;
    private String rolNecesario;
    private int duracionEstimada;
    private Date fechaComienzo;
    private Date fechaFin;
    private int duracionReal;
    private boolean estado;
    private int idFase;

    public Actividad(String descripcion, String rolNecesario, int duracionEstimada, Date fechaComienzo, Date fechaFin, int duracionReal, boolean estado, int idFase) {
        this.descripcion = descripcion;
        this.rolNecesario = rolNecesario;
        this.duracionEstimada = duracionEstimada;
        this.fechaComienzo = fechaComienzo;
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

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public int getDuracionReal() {
        return duracionReal;
    }
    
    public boolean getEstado() {
        return estado;
    }
    
    public int getIdFase(){
        return idFase;
    }
    
    public static void guardarNuevaActividad(String descripcion, String rol, int duracionEstimada, Date fechaInicio, Date fechaFin, int duracionReal, boolean estado, int idFase) {
        ActividadBD.insert(new Actividad(descripcion, rol, duracionEstimada, fechaInicio, fechaFin, duracionReal, estado, idFase));
    }
   
}
