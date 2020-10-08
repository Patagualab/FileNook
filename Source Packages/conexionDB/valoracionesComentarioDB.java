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
import modelo.ValoracionComentario;

/**
 *
 * @author andres
 */
public class valoracionesComentarioDB {

    public static int insert(ValoracionComentario valoracion){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        int res = 0;
        String consulta= ""
                + "SELECT * FROM ValoracionesComentario "
                + "WHERE comentario = ? and usuario = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, valoracion.getComentario());
            ps.setString(2, valoracion.getUsuario());
            rs = ps.executeQuery();
            if(!rs.next()){
                consulta = "INSERT INTO ValoracionesComentario (comentario, puntuacion, fecha, usuario)"
                        + " VALUES (?, ?, ?, ?)";
                ps = connection.prepareStatement(consulta);
                ps.setInt(1, valoracion.getComentario());
                ps.setInt(2, valoracion.getPuntuacion());
                ps.setDate(3, (Date) valoracion.getFecha());
                ps.setString(4, valoracion.getUsuario());
            } else{
                consulta = "UPDATE ValoracionesComentario SET puntuacion = ? "
                        + "WHERE comentario = ? AND usuario = ?";
                ps = connection.prepareStatement(consulta);
                ps.setInt(1, valoracion.getPuntuacion());
                ps.setInt(2, valoracion.getComentario());
                ps.setString(3, valoracion.getUsuario());
            }
            res = ps.executeUpdate();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        
    }
    
    public static int getValoracionUsuarioComentario(String usuario, int idComentario){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        int puntuacion = -1;
        String consulta ="SELECT puntuacion FROM ValoracionesComentario WHERE comentario = ? AND usuario = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, idComentario);
            ps.setString(2, usuario);
            rs = ps.executeQuery();
            while(rs.next()){
                puntuacion = rs.getInt("puntuacion");
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return puntuacion;
        }catch (SQLException e){
            return -1;
        }
    }
    
    
    public static double valoracionMediaComentario(int comentario){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        double media = -1.0;
        ResultSet rs = null;
        String consulta="SELECT AVG(cast(puntuacion as float)) as media FROM ValoracionesComentario WHERE comentario = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, comentario);
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
}
