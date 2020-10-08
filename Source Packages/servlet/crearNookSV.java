/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conexionDB.clasificacionCategoriasDB;
import conexionDB.nookDB;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ClasificacionCategorias;
import modelo.Nook;

/**
 *
 * @author Fnac
 */
@WebServlet(name = "crearNookSV", urlPatterns = {"/crearNookSV"})
@MultipartConfig
public class crearNookSV extends HttpServlet {

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
        
        
        HttpSession session = request.getSession(false);
        if(session==null){
            String url = "/inicioSesion.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else{
            String userName = (String) session.getAttribute("usuario");

            String nombre = request.getParameter("nombre");
            String etiquetas1 = request.getParameter("etiquetas1");
            String etiquetas2 = request.getParameter("etiquetas2");
            String etiquetas3 = request.getParameter("etiquetas3");
            String resumen = request.getParameter("resumen");

            java.util.Date date = new java.util.Date();  
            Date fecha = new Date(date.getTime());

            Nook nook = new Nook();

            nook.setNombre(nombre);
            nook.setResumen(resumen);        
            nook.setAutor(userName);
            nook.setFechaCreacion(fecha);
            nook.setFechaModificacion(fecha);
            nook.setDescargas(0);
            nook.setValoracionMedia(0.0);

            nookDB.insert(nook);

            Nook nookCreado = nookDB.ultimoNook(userName);
            int idNook = nookCreado.getIdNook();


            ClasificacionCategorias categoria1 = new ClasificacionCategorias();

            categoria1.setIdNook(idNook);
            categoria1.setCategoria(etiquetas1);

            clasificacionCategoriasDB.insert(categoria1);

            ClasificacionCategorias categoria2 = new ClasificacionCategorias();

            categoria2.setIdNook(idNook);
            categoria2.setCategoria(etiquetas2);

            clasificacionCategoriasDB.insert(categoria2);

            ClasificacionCategorias categoria3 = new ClasificacionCategorias();

            categoria3.setIdNook(idNook);
            categoria3.setCategoria(etiquetas3);

            clasificacionCategoriasDB.insert(categoria3);

            String url = "/agregarArchivo.jsp";
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

    private ClasificacionCategorias ClasificacionCategorias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
