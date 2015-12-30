/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.FaseDB;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author andreaescribano
 */
public class Fase implements Serializable{

    private int id;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private char estado;
    private int idProyecto;

   public Fase (String nombre, String fechaInicio, String fechaFin, int idProyecto){
       this.nombre=nombre;
       this.fechaInicio=fechaInicio;
       this.fechaFin=fechaFin;
       this.idProyecto=idProyecto;
       this.estado='S';
       
   }
   
   public Fase (String nombre, String fechaInicio, String fechaFin ,char estado, int idProyecto){
       this.nombre=nombre;
       this.fechaInicio=fechaInicio;
       this.fechaFin=fechaFin;
       this.idProyecto=idProyecto;
       this.estado=estado;
       
   }
   
   public Fase (int id, String nombre, String fechaInicio, String fechaFin ,char estado, int idProyecto){
       this.id=id;
       this.nombre=nombre;
       this.fechaInicio=fechaInicio;
       this.fechaFin=fechaFin;
       this.idProyecto=idProyecto;
       this.estado=estado;
       
   }

    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
   public String getFechaInicio(){
       return fechaInicio;
   }
   
   public String getFechaFin(){
       return fechaFin;
   }

    public char getEstado() {
        return estado;
    }

    public int getIdProyecto() {
        return idProyecto;
    }
    
    public static void crearNuevaFase(int idProyecto, String nombre,String fechaInicio, String fechaFin, char estado) {
        FaseDB.insert(new Fase(nombre, fechaInicio, fechaFin, estado, idProyecto));
    }
    
    public static void crearNuevaFase(Fase f){
        FaseDB.insert(f);
    }
    
    public static ArrayList<Fase> getFase(int idProyecto) {
        return FaseDB.selectFases(idProyecto);
    }
    
    public static Fase getPhase(int idFase) {		
        return FaseDB.selectFase(idFase);		
    }		
 		
     public static void actualizarFase(Fase f) {		
         FaseDB.updateFase(f);		
     }

}
