/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jintae
 */
public class ConsultasUsuarios extends Conexion {
    

    public static Usuario obtenerDatosUsuario(String loginUsuario) {
        
        conectar();
        
        Usuario user = null;
        
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, loginUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                
                user = new Usuario();
                user.setNombre(rs.getString("nombre_appellidos"));
                user.setTipo(rs.getString("tipo"));
                user.setTienda(rs.getString("tienda"));
                user.setUsuario(rs.getString("usuario"));

            }
            
        } catch (SQLException e) {
            
            System.err.println("Error al obtener datos: " + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        return user;
    }
}
