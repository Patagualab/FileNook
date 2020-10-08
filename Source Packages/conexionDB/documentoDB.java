/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionDB;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.Part;
import modelo.Documento;

/**
 *
 * @author Fnac
 */
public class documentoDB {
    
        public static int insert(Documento documento) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query="INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1, documento.getNook());
            ps.setString(2, documento.getNombre());
            ps.setString(3, documento.getResumen());
            ps.setDate(4, (Date) documento.getFechaCreacion());
            ps.setDate(5, (Date) documento.getFechaModificacion());
           
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static int insertDocu(int idNook, Part documento, String nombre) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
        String consulta="UPDATE  Documento  SET documento=?  WHERE nook= ? AND nombre = ?";
        try {
            ps = connection.prepareStatement(consulta);
           
            ps.setBinaryStream(1, documento.getInputStream() ); 
            
            ps.setInt(2, idNook);
            ps.setString(3, nombre);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static ArrayList<Documento> getDocumentosNook(int idNook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ArrayList<Documento> documentos=new ArrayList();
        ResultSet rs = null;
        String consulta = "SELECT * FROM Documento WHERE nook = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, idNook);
            rs = ps.executeQuery();
            while (rs.next()) {
                Documento documento=new Documento();
                documento.setNook(rs.getInt("nook"));
                documento.setNombre(rs.getString("nombre"));
                documento.setResumen(rs.getString("resumen"));
                documento.setFechaCreacion(rs.getDate("fechaCreacion"));
                documento.setFechaModificacion(rs.getDate("fechaModificacion"));
                documento.setDocumento(rs.getBlob("documento"));
                documentos.add(documento);
                }
            
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            return documentos;
        } catch (SQLException e) {
            e.printStackTrace();
            
        return null;
        }
    }
    
        public static ArrayList<Documento> getFiles(int idNook) throws FileNotFoundException, IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        ArrayList<Documento> documentos=new ArrayList();
        String consulta = "SELECT * FROM Documento WHERE nook = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, idNook);
            rs = ps.executeQuery();
            while (rs.next()) {
                Documento documento=new Documento();
                documento.setNook(rs.getInt("nook"));
                documento.setNombre(rs.getString("nombre"));
                documento.setResumen(rs.getString("resumen"));
                documento.setFechaCreacion(rs.getDate("fechaCreacion"));
                documento.setFechaModificacion(rs.getDate("fechaModificacion"));
                documento.setDocumento(rs.getBlob("documento"));
                documentos.add(documento);
                
                InputStream is =  documento.getDocumento().getBinaryStream();
                String ruta = "temp" + File.separator + idNook;
                new File(ruta).mkdirs();
                FileOutputStream os = new FileOutputStream(ruta + File.separator + documento.getNombre());
                byte[] buffer = new byte[1024];
                int bytesRead;
                while((bytesRead = is.read(buffer)) !=-1){
                    os.write(buffer, 0, bytesRead);
                }
                is.close();
                os.flush();
                os.close();

            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return documentos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
        public static double borrarArchivo(String nombre, int idNook){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;


        String consulta="DELETE FROM Documento WHERE nombre = ? AND nook = ?";
        try {
            ps =connection.prepareStatement(consulta);
        
            ps.setString(1, nombre );
            ps.setInt(2, idNook );

            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
