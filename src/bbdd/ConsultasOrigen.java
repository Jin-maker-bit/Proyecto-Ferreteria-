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
    
    
    
    /**
     * Inserta un nuevo origen en la base de datos de la ferretería.
     * Utiliza PreparedStatement para evitar inyecciones SQL y maneja su propia conexión.
     * @param o Objeto Origen con los datos a insertar.
     * @return true si se insertó correctamente, false si hubo un error.
     */
    public static boolean registrarOrigen(modelo.Origen o) {
        
        boolean exito = false;
        
        String consulta = "INSERT INTO origen (origen, descripcion) VALUES (?, ?)";
        
        Conexion.conectar(); 
        
        try {
            
            java.sql.PreparedStatement pst = Conexion.conn.prepareStatement(consulta);
            pst.setString(1, o.getOrigen());
            pst.setString(2, o.getDescripcion());
            
            int filasAfectadas = pst.executeUpdate();
            
            if (filasAfectadas > 0) {
                exito = true; 
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Error al registrar el origen: " + ex.getMessage());
            
        } finally {
            
            Conexion.cerrarConexion(); 
        }
        
        return exito;
    }
    
}
