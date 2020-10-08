/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conexionDB.nookDB;
import conexionDB.usuarioDB;
import conexionDB.valoracionesNookDB;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ValoracionNook;

/**
 *
 * @author andres
 */
@WebServlet(name = "valorarNookSV", urlPatterns = {"/valorarNookSV"})
public class valorarNookSV extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        if(session==null){
            String url = "/inicioSesion.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            String usr = (String) session.getAttribute("usuario");        
            ValoracionNook valoracion = new ValoracionNook();
            int idNook = Integer.parseInt(request.getParameter("idNook"));
            int val = Integer.parseInt(request.getParameter("estrellas"+idNook));

            java.util.Date date = new java.util.Date();  
            Date fecha = new Date(date.getTime());

            valoracion.setNook(idNook);
            valoracion.setUsuario(usr);
            valoracion.setPuntuacion(val);
            valoracion.setFecha(fecha);

            valoracionesNookDB.insert(valoracion);

            double media = valoracionesNookDB.valoracionMediaNook(idNook);
            if(media!=-1){
                nookDB.actualizarValoracionMedia(idNook, media);
            }else{
                nookDB.actualizarValoracionMedia(idNook, 0);
            }
            String autor = nookDB.getNook(idNook).getAutor();

            double mediaAutor = nookDB.valoracionMediaAutor(autor);
            if(mediaAutor != -1){
                usuarioDB.actualizarValoracionMedia(autor, media);
            }else{
                usuarioDB.actualizarValoracionMedia(autor, 0);
            }

            String url = request.getParameter("urlPagina");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
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
