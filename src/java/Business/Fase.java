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

    private int identificador;
    private String nombre;
    private int diaInicio;
    private int mesInicio;
    private int anoInicio;
    private int diaFin;
    private int mesFin;
    private int anoFin;
    private char estado;
    private int idProyecto;

   public Fase (String nombre, int diaInicio, int mesInicio, int anoInicio,
           int diaFin, int mesFin, int anoFin, int idProyecto){
       this.nombre=nombre;
       this.diaInicio=diaInicio;
       this.mesInicio=mesInicio;
       this.anoInicio=anoInicio;
       this.diaFin=diaFin;
       this.mesFin=mesFin;
       this.anoFin=anoFin;
       this.idProyecto=idProyecto;
       this.estado='S';
       
   }
   
   public Fase (String nombre, int diaInicio, int mesInicio, int anoInicio,
           int diaFin, int mesFin, int anoFin,char estado, int idProyecto){
       this.nombre=nombre;
       this.diaInicio=diaInicio;
       this.mesInicio=mesInicio;
       this.anoInicio=anoInicio;
       this.diaFin=diaFin;
       this.mesFin=mesFin;
       this.anoFin=anoFin;
       this.idProyecto=idProyecto;
       this.estado=estado;
       
   }
   
   public Fase (int identificador, String nombre, int diaInicio, int mesInicio, int anoInicio,
           int diaFin, int mesFin, int anoFin,char estado, int idProyecto){
       this.identificador=identificador;
       this.nombre=nombre;
       this.diaInicio=diaInicio;
       this.mesInicio=mesInicio;
       this.anoInicio=anoInicio;
       this.diaFin=diaFin;
       this.mesFin=mesFin;
       this.anoFin=anoFin;
       this.idProyecto=idProyecto;
       this.estado=estado;
       
   }

    public int getIdentificador() {
        return identificador;
    }
    
    public String getNombre() {
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

    public int getIdProyecto() {
        return idProyecto;
    }
    
    public static void crearNuevaFase(int idProyecto, String nombre,int diaInicio, int mesInicio, int anoInicio,
           int diaFin, int mesFin, int anoFin, char estado) {
        FaseDB.insert(new Fase(nombre, diaInicio, mesInicio, anoInicio, diaFin, mesFin, anoFin, estado, idProyecto));
    }
    
    public static ArrayList<Fase> getFase(int idProyecto) {
        return FaseDB.selectFases(idProyecto);
    }

}
