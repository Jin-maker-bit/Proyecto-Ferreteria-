/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

/**
 *
 * @author jintae
 */
public class ConsultasOrigen extends Conexion {
    
    /**
     * Carga todos los orígenes de la tabla origen.
     * @return ArrayList con los nombres de los orígenes.
     */
    public static java.util.ArrayList<String> obtenerOrigenes() {
        
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        
        String consulta = "SELECT origen FROM origen";
        
        Conexion.conectar(); 
        
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                lista.add(rs.getString(1));
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar orígenes: " + ex.getMessage());
            
        } finally {
            
            Conexion.cerrarConexion(); 
        }
        
        return lista;
    }
    
}
