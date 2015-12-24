/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Business.Actividad;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
@WebServlet(name = "CreateActivity", urlPatterns = {"/createActivity"})
public class CreateActivity extends HttpServlet {

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
        int idFase = /*(Integer) sesion.getAttribute("idFase")*/1;
        String descripcion = request.getParameter("descripcion");
        String rol = request.getParameter("rol");
        int duracionEstimada = 0;
        if (!request.getParameter("duracionEstimada").equals("")) {
            duracionEstimada = Integer.parseInt(request.getParameter("duracionEstimada"));
        }

        String fechaInicioyFin = request.getParameter("fechaInicioyFin");
        String s = "";
        boolean encontrado = false;
        int i = 0;
        while (i < fechaInicioyFin.length() && !encontrado) {
            if (fechaInicioyFin.charAt(i) != ' ') {
                s += fechaInicioyFin.charAt(i);
            } else {
                encontrado = true;
            }
            i++;
        }
        String t = fechaInicioyFin.substring(i + 2);
        String estado = request.getParameter("estado");
        int duracionReal = 0;
        if (!request.getParameter("duracionReal").equals("")) {
            duracionReal = Integer.parseInt(request.getParameter("duracionReal"));
        }
        
        if(estado.equals("noRealizada"))
            Actividad.guardarNuevaActividad(descripcion, rol, duracionEstimada, getFecha(s), getFecha(t), duracionReal, false, idFase);

    }
    
    
    private Date getFecha(String s){
        ArrayList<Integer> fecha = new ArrayList<>();
        String[] fila = s.split("/");
        for (String f : fila) {
            fecha.add(Integer.parseInt(f));
        }

        return new Date(fecha.get(2), fecha.get(0), fecha.get(1));
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
