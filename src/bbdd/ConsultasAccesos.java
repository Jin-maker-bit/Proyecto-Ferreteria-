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
    public static void registrarAcceso(Acceso a) {
        
    String consulta = "INSERT INTO accesos (usuario, fecha, ip) VALUES (?, ?, ?)";
    
        conectar(); 
    
        try {
            
            PreparedStatement pst = conn.prepareStatement(consulta);
            pst.setString(1, a.getUsuario());
            pst.setDate(2, new java.sql.Date(a.getFecha().getTime())); // (a.getFecha().getTime()));
            pst.setString(3, a.getIp());
            
            pst.execute();
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, 
                "Error al registrar acceso: " + ex.getMessage());
            
        } finally {
            
        cerrarConexion(); 
    }
        
    }
    
}
