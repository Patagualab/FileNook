/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conexionDB.clasificacionCategoriasDB;
import conexionDB.nookDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Nook;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "buscarSV", urlPatterns = {"/buscarSV"})
public class buscarSV extends HttpServlet {

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

            String busqueda= request.getParameter("search");
            ArrayList<String> categoriasNook;
            ArrayList<String> categorias = new ArrayList<>();
            ArrayList<Nook> listNook=nookDB.buscarNook(busqueda);
            StringBuilder str;

            for(int i=0; i < listNook.size(); i++){
                str =  new StringBuilder();
                categoriasNook = clasificacionCategoriasDB.getCategoriasNook(listNook.get(i).getIdNook());
                for(int j = 0; j < categoriasNook.size(); j++ ){
                    str.append(categoriasNook.get(j));
                    str.append(',');
                }
                if(str.length() > 0){
                    str.deleteCharAt(str.length()-1);
                    categorias.add(str.toString());
                }else{
                    categorias.add(null);
                }
            }

            request.setAttribute("nooksB", listNook);
            request.setAttribute("categorias", categorias);




            String url = "/busqueda.jsp";

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
