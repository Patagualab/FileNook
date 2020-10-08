/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conexionDB.documentoDB;
import conexionDB.nookDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Documento;
import modelo.Nook;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "descargarNookSV", urlPatterns = {"/descargarNookSV"})
public class descargarNookSV extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        HttpSession session = request.getSession(false);
        if(session==null){
            String url = "/inicioSesion.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }else{
            String userName = (String) session.getAttribute("usuario");
            boolean doc = true;
            int nook = Integer.parseInt(request.getParameter("idNook"));
            ArrayList<Documento> documentos = documentoDB.getDocumentosNook(nook);
            for(int i=0; i<documentos.size(); i++){
                if(documentos.get(i).getDocumento()==null){
                    doc=false;
                }
            }
            if(doc){
                documentos = documentoDB.getFiles(nook);
                ArrayList<String> archivos = new ArrayList();
                Nook nookO =  nookDB.getNook(nook);
                String zipName = nookO.getNombre() + ".zip";
                String rutaZip = "temp" + File.separator + nook + File.separator + zipName;
                FileOutputStream fos = new FileOutputStream(rutaZip);
                ZipOutputStream zos = new ZipOutputStream(fos);
                byte[] bufferZ = new byte[1024];

                for(int i=0; i<documentos.size(); i++){
                    archivos.add("temp" + File.separator + nook + File.separator + documentos.get(i).getNombre());
                }
                for (int i=0; i < archivos.size(); i++) {

                        File srcFile = new File(archivos.get(i));

                        FileInputStream fis = new FileInputStream(srcFile);
                        // begin writing a new ZIP entry, positions the stream to the start of the entry data
                        zos.putNextEntry(new ZipEntry(srcFile.getName()));

                        int length;
                        while ((length = fis.read(bufferZ)) > 0) {
                             zos.write(bufferZ, 0, length);
                        }

                        zos.closeEntry();

                        // close the InputStream
                        fis.close();

                    }
                zos.close();
                File file = new File(rutaZip);
                OutputStream outStream = null;
                FileInputStream inputStream = null;
                if(file.exists()){
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName() + "\"");
                    try{
                        outStream = response.getOutputStream();
                        inputStream = new FileInputStream(file);
                        byte[] buffer = new byte[1024*100];
                        int bytesRead = -1;
                        while((bytesRead= inputStream.read(buffer))!=-1){
                            outStream.write(buffer, 0, bytesRead);
                        }

                    } catch(IOException e){
                        e.printStackTrace();
                    } finally{
                        if(inputStream != null) {
                            inputStream.close();
                        }

                        outStream.flush();
                        if(outStream != null){
                            outStream.close();
                        }
                    }
                } else{
                    response.setContentType("text/html");
                    response.getWriter().println("<h3>File \"+ fileName +\" Is Not Present .....!</h3>");
                }

                deleteDirectory(new File("temp" + File.separator + nook));

                nookDB.actualizarDescargas(nook, nookO.getDescargas()+1);
            } else{
                String url = "/nookSV?"+nook;
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        }
    }
    
    boolean deleteDirectory(File directorio) {
        File[] contenidos = directorio.listFiles();
        if (contenidos != null) {
            for (File file : contenidos) {
                deleteDirectory(file);
            }
        }
        return directorio.delete();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(descargarNookSV.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(descargarNookSV.class.getName()).log(Level.SEVERE, null, ex);
        }
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
