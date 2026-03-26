/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import static bbdd.Conexion.cerrarConexion;
import static bbdd.Conexion.conectar;
import static bbdd.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jintae
 */
public class ConsultasTiendas extends Conexion {

    public static int rescataTienda() {

        int numeroTienda = 0;
        String consulta = "SELECT count(denominacion) FROM tiendas";

        conectar();

        try {
            PreparedStatement comando = conn.prepareStatement(consulta);
            try (ResultSet reader = comando.executeQuery()) {
                if (reader.next()) {
                    numeroTienda = reader.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el número de tiendas.\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return numeroTienda;
    }
    
    
    
    /**
     * Obtiene la lista de nombres de los responsables desde la base de datos.
     * @return ArrayList con los nombres de los responsables.
     */
    
    public static java.util.ArrayList<String> obtenerResponsables() {
        
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        
        String consulta = "SELECT nombre_apellidos FROM responsables_tienda";
        
        Conexion.conectar();
        
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                
                lista.add(rs.getString("nombre_apellidos"));
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar responsables: " + ex.getMessage());
            
        } finally {
            
            Conexion.cerrarConexion();
        }
        
        return lista;
    }

    
    
    /**
     * Inserta una nueva tienda en la base de datos.
     * @param ti Objeto Tienda con los datos a insertar.
     * @return true si se insertó correctamente, false si hubo un error.
     */
    public static boolean registrarTienda(modelo.Tienda ti) {
        
        boolean exito = false;
        
        String consulta = "INSERT INTO tiendas (denominacion, direccion, responsable) VALUES (?, ?, ?)";
        
        Conexion.conectar();
        
        try {
            
            java.sql.PreparedStatement pst = Conexion.conn.prepareStatement(consulta);
            
            pst.setString(1, ti.getDenominacion());
            pst.setString(2, ti.getDireccion());
            pst.setString(3, ti.getResponsable());
            
            int filasAfectadas = pst.executeUpdate();
            
            if (filasAfectadas > 0) {
                exito = true; 
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Error al registrar la tienda: " + ex.getMessage());
            
        } finally {
            
            Conexion.cerrarConexion();
            
        }
        return exito;
    }
    
    
    /**
     * Obtiene la lista de denominaciones de tiendas desde la base de datos.
     * @return ArrayList con los nombres de las tiendas.
     */
    public static java.util.ArrayList<String> obtenerNombresTiendas() {
        
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        
        String consulta = "SELECT denominacion FROM tiendas"; 
        
        Conexion.conectar();
        
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                lista.add(rs.getString("denominacion"));
                
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar tiendas: " + ex.getMessage());
            
        } finally {
            
            Conexion.cerrarConexion();
            
        }
        return lista;
    }
    
    
    
}
