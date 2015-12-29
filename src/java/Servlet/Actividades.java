/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Business.Actividad;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andreaescribano
 */
@WebServlet(name = "Actividades", urlPatterns = {"/Actividades"})
public class Actividades extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int idFase = Integer.parseInt(request.getParameter("idFase"));
        String url = null;
        if (idFase != 0) {
            String accion = request.getParameter("actividad");
            if (accion != null) {
                if (accion.equals("crearActividad")) {
                    Actividad.guardarNuevaActividad(getActividadFromParameters(request, 0, idFase));
                    url = getActividades(idFase, sesion);
                } else if (accion.equals("verActividades")) {
                    url = getActividades(idFase, sesion);
                } else if (accion.equals("crearNuevaActividad")) {
                    sesion.setAttribute("idFase", idFase);
                    sesion.setAttribute("actualizar", false);
                    url = "/actividad.jsp";
                } else if (accion.equals("actualizarUnaActividad")) {
                    int idActividad = Integer.parseInt(request.getParameter("idActividad"));
                    sesion.setAttribute("idFase", idFase);
                    sesion.setAttribute("actualizar", true);
                    sesion.setAttribute("idActividad", idActividad);
                    Actividad a = Actividad.getActivity(idActividad);
                    sesion.setAttribute("actividad", a);
                    url = "/actividad.jsp";
                } else if(accion.equals("actualizarActividad")){
                    int idActividad = Integer.parseInt(request.getParameter("idActividad"));
                    Actividad.actualizarActividad(getActividadFromParameters(request, idActividad, idFase));
                    url = getActividades(idFase, sesion);
                }
                RequestDispatcher respuesta = getServletContext().getRequestDispatcher(url);
                respuesta.forward(request, response);
            }
        }

    }

    private Actividad getActividadFromParameters(HttpServletRequest request, int idActividad, int idFase) {
        String descripcion = request.getParameter("descripcion");
        String rol = request.getParameter("rol");
        int duracionEstimada = Integer.parseInt(request.getParameter("duracionEstimada"));
        String fechaInicioyFin = request.getParameter("fechaInicioyFin");
        String fechaInicio = "";
        boolean encontrado = false;
        int i = 0;
        while (i < fechaInicioyFin.length() && !encontrado) {
            if (fechaInicioyFin.charAt(i) != ' ') {
                fechaInicio += fechaInicioyFin.charAt(i);
            } else {
                encontrado = true;
            }
            i++;
        }
        String fechaFin = fechaInicioyFin.substring(i + 2);
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
        int duracionReal = 0;
        if (!request.getParameter("duracionReal").equals("") && !request.getParameter("duracionReal").equals(null)) {
            duracionReal = Integer.parseInt(request.getParameter("duracionReal"));
        }
        if(idActividad == 0){
            return new Actividad(descripcion, rol, duracionEstimada, fechaInicio, fechaFin, duracionReal, estado, idFase);
        }else{
            return new Actividad(idActividad, descripcion, rol, duracionEstimada, fechaInicio, fechaFin, duracionReal, estado, idFase);
        }
    }

    private String getActividades(int idFase, HttpSession sesion) {
        ArrayList<Actividad> actividades = Actividad.getFase(idFase);
        sesion.setAttribute("idFase", idFase);
        sesion.setAttribute("actividades", actividades);
        return "/vistaActividades.jsp";
    }

    private Date getFecha(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(s, formatter);

        return new Date(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
