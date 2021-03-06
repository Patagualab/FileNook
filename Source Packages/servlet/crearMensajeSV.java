/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conexionDB.mensajeDB;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Mensaje;

/**
 *
 * @author Patricia
 */
@WebServlet(name = "crearMensajeSV", urlPatterns = {"/crearMensajeSV"})
public class crearMensajeSV extends HttpServlet {

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
        }else{
            String userName = (String) session.getAttribute("usuario");
            String destinatario = request.getParameter("destinatario");
            String asunto = request.getParameter("asunto");
            String texto = request.getParameter("texto");

            java.util.Date date = new java.util.Date();
            Date fecha = new Date(date.getTime());

            Mensaje mensajeEnv = new Mensaje();

            mensajeEnv.setAsunto(asunto);
            mensajeEnv.setTexto(texto);
            mensajeEnv.setFecha(fecha);
            mensajeEnv.setLeido(1);
            mensajeEnv.setTipo("Enviado");

            mensajeEnv.setAutor(userName);
            mensajeEnv.setDestinatario(destinatario);

            mensajeDB.insert(mensajeEnv);

            Mensaje mensajeRec = new Mensaje();

            mensajeRec.setAsunto(asunto);
            mensajeRec.setTexto(texto);
            mensajeRec.setFecha(fecha);
            mensajeRec.setLeido(0);
            mensajeRec.setTipo("Recibido");
            mensajeRec.setAutor(userName);
            mensajeRec.setDestinatario(destinatario);

            mensajeDB.insert(mensajeRec);

            String url = "/enviadosSV";
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
