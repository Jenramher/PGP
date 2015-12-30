/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.ProyectoDB;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author andreaescribano
 */
public class Proyecto implements Serializable{

    private int identificador;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private char estado;
    private String login;

    public Proyecto(String nombre,String fechaInicio, String fechaFin, String login) {
       this.nombre=nombre;
       this.fechaInicio=fechaInicio;
       this.fechaFin=fechaFin;
       this.login=login;
       this.estado='S';
       
    }
    
    public Proyecto(String nombre,String fechaInicio, String fechaFin, char estado ,String login) {
           this.nombre=nombre;
           this.fechaInicio=fechaInicio;
       this.fechaFin=fechaFin;
           this.login=login;
           this.estado=estado;
    }
    
    public Proyecto(int identificador, String nombre,String fechaInicio, String fechaFin, char estado ,String login) {
           this.identificador=identificador;
            this.nombre=nombre;
           this.fechaInicio=fechaInicio;
       this.fechaFin=fechaFin;
           this.login=login;
           this.estado=estado;
    }

    public int getIdentificador() {
        return identificador;
    }

   public String getNombre(){
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

    public String getLogin() {
        return login;
    }
    
    public static void guardarNuevoProyecto(String nombre, String fechaInicio, String fechaFin, char estado, String login) {
        ProyectoDB.insert(new Proyecto(nombre, fechaInicio, fechaFin, estado, login));
    }
    
    public static void guardarNuevoProyecto(Proyecto p){
        ProyectoDB.insert(p);
    }
    
    public static ArrayList<Proyecto> getProyectos(String usuario) {
        return ProyectoDB.selectProyectos(usuario);
    }
    
    public static Proyecto getProject(int idProyecto){
        return ProyectoDB.selectProyecto(idProyecto);
    }
    
    public static void actualizarProyecto(Proyecto p){
        ProyectoDB.updateProyecto(p);
    }

    
}
