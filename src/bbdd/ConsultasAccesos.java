/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public static void registrarAcceso(String usuario, Date fecha, String ip) {
        
    String consulta = "INSERT INTO accesos (usuario, fecha, ip) VALUES (?, ?, ?)";
    
        try {
            
            PreparedStatement pst = conn.prepareStatement(consulta);
            pst.setString(1, usuario);
            pst.setDate(2, new java.sql.Date(fecha.getTime()));
            pst.setString(3, ip);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, 
                "Error al registrar acceso: " + ex.getMessage());
        }
    }
   
    
}
