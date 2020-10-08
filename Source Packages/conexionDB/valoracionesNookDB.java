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
import modelo.ValoracionNook;

/**
 *
 * @author andres
 */
public class valoracionesNookDB {

    public static int insert(ValoracionNook valoracion){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        int res = 0;
        String consulta= ""
                + "SELECT * FROM valoracionesNook "
                + "WHERE nook = ? and usuario = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, valoracion.getNook());
            ps.setString(2, valoracion.getUsuario());
            rs = ps.executeQuery();
            if(!rs.next()){
                consulta = "INSERT INTO valoracionesNook (nook, usuario, puntuacion, fecha)"
                        + " VALUES (?, ?, ?, ?)";
                ps = connection.prepareStatement(consulta);
                ps.setInt(1, valoracion.getNook());
                ps.setString(2, valoracion.getUsuario());
                ps.setInt(3, valoracion.getPuntuacion());
                ps.setDate(4, (Date) valoracion.getFecha());
            } else{
                consulta = "UPDATE valoracionesNook SET puntuacion = ?, fecha = ? "
                        + "WHERE nook = ? AND usuario = ?";
                ps = connection.prepareStatement(consulta);
                ps.setInt(1, valoracion.getPuntuacion());
                ps.setDate(2, (Date) valoracion.getFecha());
                ps.setInt(3, valoracion.getNook());
                ps.setString(4, valoracion.getUsuario());
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

    public static int getValoracionUsuarioNook(String usuario, int idNook){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        int puntuacion = -1;
        String consulta ="SELECT puntuacion FROM ValoracionesNook WHERE nook = ? AND usuario = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, idNook);
            ps.setString(2, usuario);
            rs = ps.executeQuery();
            while(rs.next()){
                puntuacion = rs.getInt(1);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return puntuacion;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public static double valoracionMediaNook(int idNook){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        double media = -1.0;
        ResultSet rs = null;
        String consulta="SELECT AVG(cast(puntuacion as float)) as media FROM ValoracionesNook WHERE nook = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, idNook);
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
