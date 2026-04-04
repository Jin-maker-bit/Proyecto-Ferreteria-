/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import static bbdd.Conexion.cerrarConexion;
import static bbdd.Conexion.conectar;
import static bbdd.Conexion.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase encargada de gestionar todas las consultas a la base de datos relacionadas con la tabla origen.
 * Hereda de la clase Conexion para facilitar el acceso a la BBDD.
 * Todos los métodos gestionan íntegramente el ciclo de vida de la conexión: Apertura/Cierre.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class ConsultasOrigen extends Conexion {
      
    /**
     * Rescata todos los nombres de los orígenes registrados en la base de datos.
     * Este método se utiliza para cargar dinámicamente el JComboBox de Origen  en la interfaz de registro de artículos.
     * 
     * @return ArrayList de String con los nombres de los orígenes.
     */
    public static java.util.ArrayList<String> obtenerOrigenes() {
        
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        
        String consulta = "SELECT origen FROM origen";
        
        conectar(); 
        
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                lista.add(rs.getString(1));
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar orígenes: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion(); 
        }
        
        return lista;
    }
    
    
    
    /**
     * Inserta un nuevo origen de producto en la base de datos de la ferretería.
     * Utiliza PreparedStatement para evitar inyecciones SQL.
     * 
     * @param o Objeto Origen con los datos a insertar.
     * @return true si se insertó correctamente, false si hubo un error.
     */
    public static boolean registrarOrigen(modelo.Origen o) {
        
        boolean exito = false;
        
        String consulta = "INSERT INTO origen (origen, descripcion) VALUES (?, ?)";
        
        conectar(); 
        
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
            
            cerrarConexion(); 
        }
        
        return exito;
    }
    
    
    
    /**
     * Recupera todos los registros de la tabla 'origen' (nombre y descripción) y los vuelca directamente en el modelo de una tabla visual.
     * Método de uso exclusivo para la ventana de consulta 'VerListadoOrigenesAdmin'.
     * 
     * @param modelo El DefaultTableModel de la JTable que recibirá los datos.
     */
    public static void ListadoOrigenesAdmin(javax.swing.table.DefaultTableModel modelo) {

        modelo.setRowCount(0);

        Object[] fila = new Object[2];

        conectar();

        try {

            String consulta = "SELECT origen, descripcion "
                    + "FROM origen ";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar la lista de orígenes " + e.getMessage());

        } finally {

            cerrarConexion();
        }

    }
}
