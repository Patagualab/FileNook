/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.ClasificacionCategorias;

/**
 *
 * @author andres
 */
public class clasificacionCategoriasDB {
    
        public static int insert(ClasificacionCategorias categ) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query="INSERT INTO ClasificacionCategorias (idNook, categoria) VALUES (?, ?)";
        try {
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1, categ.getIdNook());
            ps.setString(2, categ.getCategoria());

           
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        
    }
        
        
    public static ArrayList<String> getCategoriasNook(int idNook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ArrayList<String> categorias;
            categorias = new ArrayList();
        ResultSet rs = null;
        Boolean existe;
        String consulta = "SELECT Categoria FROM ClasificacionCategorias WHERE idNook = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setInt(1,idNook);
            rs = ps.executeQuery();
            while(rs.next()){
                categorias.add(rs.getString("categoria"));
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            
            return categorias;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
        
}
