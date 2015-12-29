/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Business.Proyecto;
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
@WebServlet(name = "Proyectos", urlPatterns = {"/Proyectos"})
public class Proyectos extends HttpServlet {

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
        String usuario = /*(String) sesion.getAttribute("usuario")*/ "jefe_1";
        String url = null;
        if (usuario != null) {
            String accion = request.getParameter("proyecto");
            if (accion != null) {
                if (accion.equals("crearProyecto")) {
                    Proyecto.guardarNuevoProyecto(getProyectoFromParameters(request, 0, usuario));
                    url = getProyectos(usuario, sesion);
                } else if (accion.equals("verProyectos")) {
                    url = getProyectos(usuario, sesion);
                } else if (accion.equals("crearNuevoProyecto")) {
                    sesion.setAttribute("usuario", usuario);
                    sesion.setAttribute("actualizar", false);
                    url = "/proyecto.jsp";
                } else if(accion.equals("actualizarUnProyecto")){
                    int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
                    sesion.setAttribute("usuario", usuario);
                    sesion.setAttribute("actualizar", true);
                    sesion.setAttribute("idProyecto", idProyecto);
                    Proyecto p = Proyecto.getProject(idProyecto);
                    sesion.setAttribute("proyecto", p);
                    url = "/proyecto.jsp";
                }else if(accion.equals("actualizarProyecto")){
                    int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
                    Proyecto.actualizarProyecto(getProyectoFromParameters(request, idProyecto, usuario));
                    url = getProyectos(usuario, sesion);
                }
                RequestDispatcher respuesta = getServletContext().getRequestDispatcher(url);
                respuesta.forward(request, response);
            }
        }
    }

    private Proyecto getProyectoFromParameters(HttpServletRequest request, int idProyecto, String usuario) {
        String nombre = request.getParameter("nombre");
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

        char estado = request.getParameter("estado").charAt(0);
        if (idProyecto == 0) {
            return new Proyecto(nombre, fechaInicio, fechaFin, estado, usuario);
        } else {
            return new Proyecto(idProyecto, nombre, fechaInicio, fechaFin, estado, usuario);
        }
    }

    private String getProyectos(String usuario, HttpSession sesion) {
        ArrayList<Proyecto> proyectos = Proyecto.getProyectos(usuario);
        sesion.setAttribute("usuario", usuario);
        sesion.setAttribute("proyectos", proyectos);
        return "/vistaProyectos.jsp";
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
