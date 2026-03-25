/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

/**
 *
 * @author jintae
 */
public class ConsultasCategorias extends Conexion {
    
    
    /**
     * Carga todas las categorías de la tabla categorias.
     * @return ArrayList con los nombres de las categorías.
     */
    public static java.util.ArrayList<String> obtenerCategorias() {
        
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        
        String consulta = "SELECT categoria FROM categorias";
        
        Conexion.conectar(); 
        
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                lista.add(rs.getString(1));
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar categorías: " + ex.getMessage());
            
        } finally {
            
            Conexion.cerrarConexion(); 
        }
        return lista;
    }
    
    
    
    /**
     * Inserta una nueva categoría en la base de datos.
     * @param cat Objeto Categoria con los datos a insertar.
     * @return true si se insertó correctamente, false si hubo un error.
     */
    public static boolean registrarCategoria(modelo.Categoria cat) {
       
        boolean exito = false;
        
        String consulta = "INSERT INTO categorias (categoria, descripcion) VALUES (?, ?)";
        
        Conexion.conectar(); 
        
        try {
            
            java.sql.PreparedStatement pst = Conexion.conn.prepareStatement(consulta);
            
            pst.setString(1, cat.getCategoria());
            pst.setString(2, cat.getDescripcion());
            
            int filasAfectadas = pst.executeUpdate();
            
            if (filasAfectadas > 0) {
                exito = true; 
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Error al registrar una nueva categoría: " + ex.getMessage());
            
        } finally {
            
            Conexion.cerrarConexion(); 
        }
        
        return exito;
    }
    
}
