/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author gil
 */
public class Calendario {

    private String fechaInicio;
    private String fechaFin;
    private String usuario;
    private String tipo;
    private String tipoT;
    private int duracion;

    public Calendario() {
        this.fechaInicio = "";
        this.fechaFin = "";
        this.usuario = "";
        this.tipo = "";
        this.tipoT="";
        this.duracion=0;
    }

    public Calendario(String fechaInicio, String fechaFin, String usuario, String tipo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.tipo = tipo;
    }
    
    public Calendario(String fechaInicio, String fechaFin, String usuario, String tipo, String tipoT, int duracion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.tipo = tipo;
        this.tipoT=tipoT;
        this.duracion=duracion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoT() {
        return tipoT;
    }

    public void setTipoT(String tipoT) {
        this.tipoT = tipoT;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public long comprobarDiasVacaciones(List<Calendario> cal) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long diff = 0;

        try {
            for (int i = 0; i < cal.size(); i++) {
                java.util.Date fechaI = formatter.parse(cal.get(i).getFechaInicio());
                java.util.Date fechaF = formatter.parse(cal.get(i).getFechaFin());
                diff = diff + (fechaF.getTime() - fechaI.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public boolean comprobarEntreFechas(String f, List<Calendario> cal) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (int i = 0; i < cal.size(); i++) {
                java.util.Date fechaI = formatter.parse(cal.get(i).getFechaInicio());
                java.util.Date fechaF = formatter.parse(cal.get(i).getFechaFin());
                java.util.Date fecha = formatter.parse(f);
                if (fechaI.compareTo(fecha) < 0 && fechaF.compareTo(fecha) > 0) {
                    return false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*date1.comparetp(date2) > 0 --> date1 esta después de date2
     date1.comparetp(date2) < 0 --> date1 esta antes de date2*/
    public boolean comprobarRangosEntreFechas(Actividad a, Calendario cal) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date fechaI = formatter.parse(cal.getFechaInicio());
            java.util.Date fechaF = formatter.parse(cal.getFechaFin());
            java.util.Date fechaIA = formatter.parse(a.getFechaInicio());
            java.util.Date fechaFA = formatter.parse(a.getFechaFin());
            if (fechaI.compareTo(fechaFA) < 0 && fechaF.compareTo(fechaIA) > 0)
                return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean comprobarRangosEntreVacaciones(Actividad a, Calendario cal) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date fechaI = formatter.parse(cal.getFechaInicio());
            java.util.Date fechaF = formatter.parse(cal.getFechaFin());
            java.util.Date fechaIA = formatter.parse(a.getFechaInicio());
            java.util.Date fechaFA = formatter.parse(a.getFechaFin());
            System.out.println("fechaFA: "+a.getFechaFin()+" fechaI: "+cal.getFechaInicio());
            System.out.println("fechaIA: "+a.getFechaInicio()+" fechaF: "+cal.getFechaFin());
            if (fechaI.compareTo(fechaFA) < 0 && fechaF.compareTo(fechaIA) > 0)
                return false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

}
