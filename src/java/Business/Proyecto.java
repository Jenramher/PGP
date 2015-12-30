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
    private int diaInicio;
    private int mesInicio;
    private int anoInicio;
    private int diaFin;
    private int mesFin;
    private int anoFin;
    private char estado;
    private String login;

    public Proyecto(String nombre,int diaInicio, int mesInicio, int anoInicio,
            int diaFin, int mesFin, int anoFin, String login) {
       this.nombre=nombre;
       this.diaInicio=diaInicio;
       this.mesInicio=mesInicio;
       this.anoInicio=anoInicio;
       this.diaFin=diaFin;
       this.mesFin=mesFin;
       this.anoFin=anoFin;
       this.login=login;
       this.estado='S';
       
    }
    
    public Proyecto(String nombre,int diaInicio, int mesInicio, int anoInicio,
            int diaFin, int mesFin, int anoFin, char estado ,String login) {
           this.nombre=nombre;
           this.diaInicio=diaInicio;
           this.mesInicio=mesInicio;
           this.anoInicio=anoInicio;
           this.diaFin=diaFin;
           this.mesFin=mesFin;
           this.anoFin=anoFin;
           this.login=login;
           this.estado=estado;
    }
    
    public Proyecto(int identificador, String nombre,int diaInicio, int mesInicio, int anoInicio,
            int diaFin, int mesFin, int anoFin, char estado ,String login) {
           this.identificador=identificador;
            this.nombre=nombre;
           this.diaInicio=diaInicio;
           this.mesInicio=mesInicio;
           this.anoInicio=anoInicio;
           this.diaFin=diaFin;
           this.mesFin=mesFin;
           this.anoFin=anoFin;
           this.login=login;
           this.estado=estado;
    }

    public int getIdentificador() {
        return identificador;
    }

   public String getNombre(){
       return nombre;
   }
   
   public int getDiaInicio(){
       return diaInicio;
   }
   
   public int getMesInicio(){
       return mesInicio;
   }
   
   public int getAnoInicio(){
       return anoInicio;
   }
   
   public int getDiaFin(){
       return diaFin;
   }
   
   public int getMesFin(){
       return mesFin;
   }
   
   public int getAnoFin(){
       return anoFin;
   }

    public char getEstado() {
        return estado;
    }

    public String getLogin() {
        return login;
    }
    
    public static void guardarNuevoProyecto(String nombre, int diaInicio, int mesInicio, int anoInicio,
            int diaFin, int mesFin, int anoFin, char estado, String login) {
        ProyectoDB.insert(new Proyecto(nombre, diaInicio, mesInicio, anoInicio, diaFin, mesFin, anoFin, estado, login));
    }
    
    public static ArrayList<Proyecto> getProyectos(String usuario) {
        return ProyectoDB.selectProyectos(usuario);
    }

    
}
