/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionDB;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import modelo.Mensaje;

/**
 *
 * @author Patricia
 */
public class mensajeDB {
    public static int insert(Mensaje mensaje) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String consulta ="INSERT INTO Mensaje (asunto,texto,fecha,leido,tipo,autor,destinatario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1, mensaje.getAsunto());
            ps.setString(2, mensaje.getTexto());
            ps.setDate(3, (Date) mensaje.getFecha());
            ps.setInt(4, mensaje.getLeido());
            ps.setString(5, mensaje.getTipo());
            ps.setString(6, mensaje.getAutor());
            ps.setString(7, mensaje.getDestinatario());
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static Mensaje getMensaje(int idMensaje) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        Mensaje mensaje = new Mensaje();
        ResultSet rs = null;
        String consulta ="SELECT * FROM Mensaje WHERE idMensaje = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1, idMensaje);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                mensaje.setIdMensaje(rs.getInt("idMensaje"));
                mensaje.setAsunto(rs.getString("asunto"));
                mensaje.setTexto(rs.getString("texto"));
                mensaje.setFecha(rs.getDate("fecha"));
                mensaje.setLeido(rs.getInt("leido"));
                mensaje.setTipo(rs.getString("tipo"));
                mensaje.setAutor(rs.getString("autor"));
                mensaje.setDestinatario(rs.getString("destinatario"));
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return mensaje;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Mensaje> getMensajesBuzon (String usuario){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        ArrayList <Mensaje> mensajes = new ArrayList();
        ResultSet rs = null;
        String consulta ="SELECT * FROM Mensaje WHERE(destinatario = ? AND tipo = 'Recibido')";
        
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1,usuario);
            rs = ps.executeQuery();
            while(rs.next()){
                Mensaje mensaje = new Mensaje();
                mensaje.setIdMensaje(rs.getInt("idMensaje"));
                mensaje.setAsunto(rs.getString("asunto"));
                mensaje.setTexto(rs.getString("texto"));
                mensaje.setFecha(rs.getDate("fecha"));
                mensaje.setLeido(rs.getInt("leido"));
                mensaje.setTipo(rs.getString("tipo"));
                mensaje.setAutor(rs.getString("autor"));
                mensaje.setDestinatario(rs.getString("destinatario"));
                
                mensajes.add(mensaje);
                
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            
        return mensajes;    
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Mensaje> getMensajesEnviados (String usuario){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        ArrayList <Mensaje> mensajes = new ArrayList();
        ResultSet rs = null;
        String consulta ="SELECT * FROM Mensaje WHERE(autor = ? AND tipo = 'Enviado')";
        
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1,usuario);
            rs = ps.executeQuery();
            while(rs.next()){
                Mensaje mensaje = new Mensaje();
                mensaje.setIdMensaje(rs.getInt("idMensaje"));
                mensaje.setAsunto(rs.getString("asunto"));
                mensaje.setTexto(rs.getString("texto"));
                mensaje.setFecha(rs.getDate("fecha"));
                mensaje.setLeido(rs.getInt("leido"));
                mensaje.setTipo(rs.getString("tipo"));
                mensaje.setAutor(rs.getString("autor"));
                mensaje.setDestinatario(rs.getString("destinatario"));
                
                mensajes.add(mensaje);
                
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            
        return mensajes;    
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int mnsajeLeido(int idMensaje) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        String consulta="UPDATE  Mensaje  SET leido = 1  WHERE idMensaje = ?";
        try {
            PreparedStatement ps =connection.prepareStatement(consulta);
           
            ps.setInt(1, idMensaje);
           
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
