/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

/**
 *
 * @author jintae
 */
public class ConsultasResponsableTienda extends Conexion{
    
    
    /**
     * Obtiene la lista de responsables desde la base de datos.
     * Separa la lógica de datos de la interfaz gráfica - Patrón MVC.
     * @return ArrayList con los nombres de los responsables.
     */
    public static java.util.ArrayList<String> obtenerResponsables() {
        
        java.util.ArrayList<String> listaResponsables = new java.util.ArrayList<>();
        
        String consulta = "SELECT nombre_apellidos FROM responsables_tienda";
        
        try {
            
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                listaResponsables.add(rs.getString("nombre_apellidos"));
            }
        } catch (java.sql.SQLException ex) {
           
            System.err.println("Error al cargar responsables: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion(); 
        }
        return listaResponsables;
    }
    
}
