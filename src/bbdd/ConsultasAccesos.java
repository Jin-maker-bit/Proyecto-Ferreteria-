/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Acceso;

/**
 *
 * @author jintae
 */


public class ConsultasAccesos extends Conexion{
    
    
    /**
     * Registra un nuevo acceso en la tabla Accesos de la base de datos.
     * Se llama cada vez que un usuario se loga correctamente en el sistema.
     * 
     * @param usuario
     * @param fecha
     * @param ip 
     */
    public static void registrarAcceso(Acceso acc) {
        
    String consulta = "INSERT INTO accesos (usuario, fecha, ip) VALUES (?, ?, ?)";
    
        conectar(); 
    
        try {
            
            PreparedStatement pst = conn.prepareStatement(consulta);
            
            pst.setString(1, acc.getUsuario());
            pst.setDate(2, new java.sql.Date(acc.getFecha().getTime())); // 
            pst.setString(3, acc.getIp());
            
            pst.execute();
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, 
                "Error al registrar acceso: " + ex.getMessage());
            
        } finally {
            
        cerrarConexion(); 
    }
        
    }
    
    
    /**
     * Verifica si el usuario y la contraseña coinciden en la base de datos.
     * Extrae su rol - tipo y su estado actual, Activo / Bloqueado.
     * @param user Nombre de usuario.
     * @param pass Contraseña.
     * @return Un Array de String donde [0] es el Tipo y [1] es el Estado. Devuelve null si no existe.
     */
    public static String[] verificarLogin(String user, String pass) {
        
        String[] datos = null;
        String consulta = "SELECT tipo, estado FROM usuarios WHERE usuario = ? AND pass = ?";
        
        conectar();
        
        try {
            
            java.sql.PreparedStatement pst = Conexion.conn.prepareStatement(consulta);
            
            pst.setString(1, user);
            pst.setString(2, pass);
            
            java.sql.ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                
                datos = new String[2];
                datos[0] = rs.getString("tipo");   // admin o user
                datos[1] = rs.getString("estado"); // Activo o Bloqueado
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al comprobar usuario: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        return datos;
    }
    
    
    
   
    
    
    
    
}
