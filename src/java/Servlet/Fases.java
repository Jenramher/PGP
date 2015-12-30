/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Business.Fase;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Fases", urlPatterns = {"/Fases"})
public class Fases extends HttpServlet {

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
        int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
        String usuario = /*(String) sesion.getAttribute("usuario")*/ "jefe_1";
        String url = null;
        if (idProyecto != 0) {
            String accion = request.getParameter("fase");
            if (accion != null) {
                if (accion.equals("crearFase")) {
                    Fase.crearNuevaFase(getFaseFromParameters(request, idProyecto, 0));
                    url = getFases(sesion, idProyecto);
                } else if (accion.equals("verFases")) {
                    url = getFases(sesion, idProyecto);
                } else if (accion.equals("crearNuevaFase")) {
                    ArrayList<Fase> fases = Fase.getFase(idProyecto);
                    sesion.setAttribute("numFase", fases.size() + 1);
                    sesion.setAttribute("idProyecto", idProyecto);
                    sesion.setAttribute("actualizar", false);
                    url = "/fase.jsp";
                } else if(accion.equals("actualizarUnaFase")){
                    int idFase = Integer.parseInt(request.getParameter("idFase"));
                    sesion.setAttribute("idProyecto", idProyecto);
                    sesion.setAttribute("actualizar", true);
                    sesion.setAttribute("idFase", idFase);
                    Fase f = Fase.getPhase(idFase);
                    sesion.setAttribute("fase", f);
                    url = "/fase.jsp";
                }else if(accion.equals("actualizarFase")){
                    int idFase = Integer.parseInt(request.getParameter("idFase"));
                    Fase.actualizarFase(getFaseFromParameters(request, idProyecto, idFase));
                    url = getFases(sesion, idProyecto);
                }

                RequestDispatcher respuesta = getServletContext().getRequestDispatcher(url);
                respuesta.forward(request, response);
            }
        }

    }

    private Fase getFaseFromParameters(HttpServletRequest request, int idProyecto, int idFase) {
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

        if(idFase == 0){
            return new Fase(nombre, fechaInicio, fechaFin, estado, idProyecto);
        }else{
            return new Fase(idFase, nombre, fechaInicio, fechaFin, estado, idProyecto);
        }
    }

    private String getFases(HttpSession sesion, int idProyecto) {
        ArrayList<Fase> fases = Fase.getFase(idProyecto);
        sesion.setAttribute("idProyecto", idProyecto);
        sesion.setAttribute("fases", fases);
        return "/vistaFases.jsp";
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
