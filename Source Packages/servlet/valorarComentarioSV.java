/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conexionDB.comentarioDB;
import conexionDB.valoracionesComentarioDB;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ValoracionComentario;

/**
 *
 * @author andres
 */
@WebServlet(name = "valorarComentarioSV", urlPatterns = {"/valorarComentarioSV"})
public class valorarComentarioSV extends HttpServlet {

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
            String usuario = (String) session.getAttribute("usuario");        
            ValoracionComentario valoracion = new ValoracionComentario();
            int idComentario = Integer.parseInt(request.getParameter("comentario"));
            int val = Integer.parseInt(request.getParameter("cestrellas"+idComentario));   

            java.util.Date date = new java.util.Date();  
            Date fecha = new Date(date.getTime());

            valoracion.setComentario(idComentario);
            valoracion.setFecha(fecha);
            valoracion.setUsuario(usuario);
            valoracion.setPuntuacion(val);


            valoracionesComentarioDB.insert(valoracion);

            double media = valoracionesComentarioDB.valoracionMediaComentario(idComentario);

            if(media!=-1){
                comentarioDB.actualizarValoracionMedia(idComentario, media);
            }else{
                comentarioDB.actualizarValoracionMedia(idComentario, 0);
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
