/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionDB;
import java.sql.*;
import modelo.Nook;
import java.util.ArrayList;

public class nookDB {
    
    public static int insert(Nook nook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query="INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionmedia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setString(1, nook.getNombre());
            ps.setString(2, nook.getResumen());
            ps.setString(3, nook.getAutor());
            ps.setDate(4, (Date) nook.getFechaCreacion());
            ps.setDate(5, (Date) nook.getFechaModificaion());
            ps.setInt(6, nook.getDescargas());
            ps.setDouble(7, nook.getValoracionMedia());
           
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static int actualizarValoracionMedia(int idNook, double media) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query="UPDATE Nook SET valoracionMedia = ? WHERE idNook = ?";
        try {
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setDouble(1, media);
            ps.setInt(2, idNook);
            int res = ps.executeUpdate();
            
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
       public static int actualizarDescargas(int idNook, int descargas) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query="UPDATE Nook SET descargas = ? WHERE idNook = ?";
        try {
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setDouble(1, descargas);
            ps.setInt(2, idNook);
            int res = ps.executeUpdate();
            
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
       
    //obtener lista de nooks populares
    public static ArrayList<Nook> getNooksPopulares() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ArrayList<Nook> nooksPopulares =new ArrayList();
        ResultSet rs = null;
        String consulta = "SELECT * FROM Nook ORDER BY valoracionMedia DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Nook nook = new Nook();
                nook.setIdNook(rs.getInt("idNook"));
                nook.setNombre(rs.getString("nombre"));
                nook.setResumen(rs.getString("resumen"));
                nook.setAutor(rs.getString("autor"));
                nook.setFechaCreacion(rs.getDate("fechaCreacion"));
                nook.setFechaModificacion(rs.getDate("fechaModificacion"));
                nook.setDescargas(rs.getInt("descargas"));
                nook.setValoracionMedia(rs.getDouble("valoracionmedia"));

                nooksPopulares.add(nook);
                }
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            return nooksPopulares;
        } catch (SQLException e) {
            e.printStackTrace();
        return null;
        }
    }
    
    //obtener lista de nooks con mas descargas
    public static ArrayList<Nook> getNooksDescargas() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ArrayList<Nook> nooksDescargas =new ArrayList();
        ResultSet rs = null;
        String consulta = "SELECT * FROM Nook ORDER BY descargas DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Nook nook = new Nook();
                nook.setIdNook(rs.getInt("idNook"));
                nook.setNombre(rs.getString("nombre"));
                nook.setResumen(rs.getString("resumen"));
                nook.setAutor(rs.getString("autor"));
                nook.setFechaCreacion(rs.getDate("fechaCreacion"));
                nook.setFechaModificacion(rs.getDate("fechaModificacion"));
                nook.setDescargas(rs.getInt("descargas"));
                nook.setValoracionMedia(rs.getDouble("valoracionmedia"));
                nooksDescargas.add(nook);
                }
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            return nooksDescargas;
        } catch (SQLException e) {
            e.printStackTrace();
        return null;
        }
    }

    public static ArrayList<Nook> getNooksUsuario(String nombre) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ArrayList<Nook> nooks=new ArrayList();
        ResultSet rs = null;
        String consulta = "SELECT * FROM Nook WHERE autor = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                Nook nook=new Nook();
                nook.setIdNook(rs.getInt("idNook"));
                nook.setNombre(rs.getString("nombre"));
                nook.setResumen(rs.getString("resumen"));
                nook.setAutor(rs.getString("autor"));
                nook.setFechaCreacion(rs.getDate("fechaCreacion"));
                nook.setFechaModificacion(rs.getDate("fechaModificacion"));
                nook.setDescargas(rs.getInt("descargas"));
                nook.setValoracionMedia(rs.getDouble("valoracionmedia"));
                nooks.add(nook);
                }
            
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            return nooks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Nook getNook(int idNook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Nook nook = new Nook();
        ResultSet rs = null;
        String consulta = "SELECT * FROM Nook WHERE idNook = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1,idNook);
            rs = ps.executeQuery();
            while (rs.next()) {
                nook.setIdNook(rs.getInt("idNook"));
                nook.setNombre(rs.getString("nombre"));
                nook.setResumen(rs.getString("resumen"));
                nook.setAutor(rs.getString("autor"));
                nook.setFechaCreacion(rs.getDate("fechaCreacion"));
                nook.setFechaModificacion(rs.getDate("fechaModificacion"));
                nook.setDescargas(rs.getInt("descargas"));
                nook.setValoracionMedia(rs.getDouble("valoracionmedia"));

                }
            
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            return nook;
        } catch (SQLException e) {
            e.printStackTrace();
            
        return null;
        }
    }

    public static ArrayList<Nook> buscarNook(String busqueda) {
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       ArrayList<Nook> listaNooks=new ArrayList();
       
       
       String consulta = "SELECT DISTINCT N.idNook, N.nombre, N.resumen, N.autor, N.fechaCreacion, N.fechaModificacion, N.descargas, N.valoracionMedia "
               + "FROM Nook N, ClasificacionCategorias C "
               + "WHERE (N.idNook = C.idNook AND C.categoria LIKE ?) "
               + "OR N.nombre LIKE ? "
               + "OR N.autor LIKE ?";    
       /*String consulta = "SELECT DISTINCT * FROM Nook NATURAL JOIN ClasificacionCategorias WHERE categoria LIKE ? OR nombre LIKE ? OR autor LIKE ?";*/        
       /*String consulta = "SELECT DISTINCT * FROM Nook WHERE nombre LIKE ? OR autor LIKE ?";*/ 
       /*String consulta = "SELECT * FROM Nook WHERE nombre LIKE ? OR autor LIKE ? ";*/
       
       ResultSet rs = null;
       /*String consulta= "SELECT * FROM Nook WHERE nombre LIKE ? ";*/
       
       
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1,"%" + busqueda + "%");
            ps.setString(2,"%" + busqueda + "%");
            ps.setString(3,"%" + busqueda + "%");
            rs=ps.executeQuery();
            
            while(rs.next()){
                Nook nook=new Nook();
                
                nook.setIdNook(rs.getInt("idNook"));
                nook.setNombre(rs.getString("nombre"));
                nook.setResumen(rs.getString("resumen"));
                nook.setAutor(rs.getString("autor"));
                nook.setFechaCreacion(rs.getDate("fechaCreacion"));
                nook.setFechaModificacion(rs.getDate("fechaModificacion"));
                nook.setDescargas(rs.getInt("descargas"));
                nook.setValoracionMedia(rs.getDouble("valoracionmedia"));
                
                listaNooks.add(nook);
            }
            
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            return listaNooks;
        } catch (SQLException e) {
            e.printStackTrace();
            
        return null;
        }
       
    }
    
        public static Nook ultimoNook(String autor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Nook nook = new Nook();
        ResultSet rs = null;
        String consulta = "SELECT * FROM Nook WHERE autor = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1,autor);
            rs = ps.executeQuery();
            while (rs.next()) {
                nook.setIdNook(rs.getInt("idNook"));
                nook.setNombre(rs.getString("nombre"));
                nook.setResumen(rs.getString("resumen"));
                nook.setAutor(rs.getString("autor"));
                nook.setFechaCreacion(rs.getDate("fechaCreacion"));
                nook.setFechaModificacion(rs.getDate("fechaModificacion"));
                nook.setDescargas(rs.getInt("descargas"));
                nook.setValoracionMedia(rs.getDouble("valoracionmedia"));

                }
            
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            return nook;
        } catch (SQLException e) {
            e.printStackTrace();
            
        return null;
        }
    }
    public static double valoracionMediaAutor(String autor){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        double media = -1.0;
        ResultSet rs = null;
        String consulta="SELECT AVG(cast(valoracionMedia as float)) as media FROM nook WHERE autor = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1, autor);
            rs = ps.executeQuery();
            while(rs.next()){
                media = rs.getDouble("media");    
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return media;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1.0;
        }
        }
        
        
    public static double borrarNook(int idNook){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;


        String consulta="DELETE FROM Nook WHERE idNook = ?";
        try {
            ps =connection.prepareStatement(consulta);

            ps.setInt(1, idNook );

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
