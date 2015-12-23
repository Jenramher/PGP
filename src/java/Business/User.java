/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;

/**
 *
 * @author Jennifer
 */
public class User implements Serializable{
    
    private String login;
    private String pass;
    private char tipo;
    private String nif;
    private int maxProy=3;
    private String infoGeneral;
    
    public User(){
        login="";
        pass="";
        tipo=' ';
        nif="";
        infoGeneral="";
    }
    
    public User(String login, String pass, char tipo, String nif){
        this.login=login;
        this.pass=pass;
        this.tipo=tipo;
        this.nif=nif;
        infoGeneral="";
    }
    
    public User(String login, String pass, char tipo, String nif,
            String infoGeneral){
        this.login=login;
        this.pass=pass;
        this.tipo=tipo;
        this.nif=nif;
        this.infoGeneral=infoGeneral;
    }
    
    public String getLogin(){
        return login;
    }
    
    public String getPass(){
        return pass;
    }
    
    public char getTipo(){
        return tipo;
    }
    
    public String getNif(){
        return nif;
    }
    
    public int getMaxProy(){
        return maxProy;
    }
    
    public String getInfoGeneral(){
        return infoGeneral;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public void setPass(String pass){
        this.pass=pass;
    }
    
    public void setTipo(char tipo){
        this.tipo = tipo;
    }
    
    public void setInfoGeneral(String infoGeneral){
        this.infoGeneral=infoGeneral;
    }
}
