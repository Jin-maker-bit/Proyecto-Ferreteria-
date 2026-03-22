/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author jintae
 */
public class ConsultasProducto extends Conexion {
    
    
    /**
     * Recupera los tres últimos artículos registrados en la base de datos ordenados por fecha de alta de forma descendente y los carga en la tabla.
     * La tabla que carga este método aparece tanto en VentanaAdmin como en VentanaUser.
     * @param modelo 
     */
    public static void ultimos3Articulos(javax.swing.table.DefaultTableModel modelo) {
        
        // Limpiamos la tabla para que no se dupliquen los datos al pulsar el botón
        modelo.setRowCount(0);

        // Definimos el array de objetos (fila) con 5 columnas según nuestro SELECT
        Object[] fila = new Object[5]; 

        conectar(); 

        try {
            
            String consulta = "SELECT codProducto, nombre, categoria, stock, precio_venta " +
                              "FROM producto " +
                              "ORDER BY fecha_alta DESC " +
                              "LIMIT 3";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            // Recorremos el ResultSet y llenamos el modelo
            while (rs.next()) {
                fila[0] = rs.getInt(1);    
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3); 
                fila[3] = rs.getInt(4);    
                fila[4] = rs.getDouble(5); 

                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            // Estilo Reynaldo: Mensaje de error visual
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar últimos artículos: " + e.getMessage());
        } finally {
            // 5. Cerramos siempre la conexión para evitar errores de Executor
            cerrarConexion();
        }
    }
    
    
}
