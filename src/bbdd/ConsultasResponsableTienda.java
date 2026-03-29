/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

/**
 * Clase encargada de gestionar las consultas a la base de datos relacionadas con la tabla responsables_tienda.
 * Hereda de la clase Conexion para la gestión de la BBDD.
 * @author Jose y Patricia
 */
public class ConsultasResponsableTienda extends Conexion{
    
    
    /**
     * Obtiene la lista completa de los nombres de los responsables desde la base de datos.
     * Separa la lógica de datos de la interfaz gráfica - Patrón MVC, permitiendo rellenar dinámicamente componentes como los JComboBox.
     * @return ArrayList con los nombres de los responsables.
     */
    public static java.util.ArrayList<String> obtenerResponsables() {
        
        java.util.ArrayList<String> listaResponsables = new java.util.ArrayList<>();
        
        String consulta = "SELECT nombre_apellidos FROM responsables_tienda";
        
        conectar();
        
        try {
            
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                listaResponsables.add(rs.getString("nombre_apellidos"));
            }
        } catch (java.sql.SQLException ex) {
           
           javax.swing.JOptionPane.showMessageDialog(null, 
                "Error al cargar responsables: " + ex.getMessage(), 
                "Error BBDD", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            
        } finally {
            
            cerrarConexion(); 
        }
        return listaResponsables;
    }
    
}
