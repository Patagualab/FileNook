/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Comentario;
import modelo.Nook;

/**
 *
 * @author Fnac
 */
public class comentarioDB {
    
    public static int insert(Comentario comentario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query="INSERT INTO Comentario (idNook,fecha,autor,texto,valoracionMedia) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1, comentario.getIdNook());
            ps.setDate(2, (Date) comentario.getFecha());
            ps.setString(3, comentario.getAutor());
            ps.setString(4, comentario.getTexto());
            ps.setDouble(5, comentario.getValoracionMedia());
           
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static ArrayList<Comentario> getComentariosNook(int idNook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ArrayList<Comentario> comentarios=new ArrayList();
        ResultSet rs = null;
        String consulta = "SELECT * FROM Comentario WHERE idNook = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, idNook);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comentario comentario=new Comentario();
                comentario.setIdComentario(rs.getInt("idComentario"));
                comentario.setFecha(rs.getDate("fecha"));
                comentario.setAutor(rs.getString("autor"));
                comentario.setTexto(rs.getString("texto"));
                comentario.setValoracionMedia(rs.getDouble("valoracionMedia"));
                
                comentarios.add(comentario);
                }
            
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            return comentarios;
        } catch (SQLException e) {
            e.printStackTrace();
            
        return null;
        }
    }
    
    public static int actualizarValoracionMedia(int idComentario, double media) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query="UPDATE comentario SET valoracionMedia = ? WHERE idComentario = ?";
        try {
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setDouble(1, media);
            ps.setInt(2, idComentario);
            int res = ps.executeUpdate();
            
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static double borrarComentario(int idComentario){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;


        String consulta="DELETE FROM Comentario WHERE idComentario = ?";
        try {
            ps =connection.prepareStatement(consulta);

            ps.setInt(1, idComentario );

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
