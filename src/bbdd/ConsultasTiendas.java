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
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Clase encargada de gestionar todas las consultas a la base de datos relacionadas con la tabla tiendas.
 * Hereda de la clase Conexion para facilitar el acceso a la BBDD.
 * Todos los métodos gestionan íntegramente el ciclo de vida de la conexión: Apertura/Cierre.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class ConsultasTiendas extends Conexion {

    
    /**
     * Cuenta el número total de tiendas registradas en la base de datos.
     * Este método se utiliza principalmente para mostrar estadísticas rápidas en el panel principal (JLabel) del usuario Administrador.
     * 
     * @return Un número entero que representa el número total de tiendas encontradas en la tabla.
     */
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
            
            JOptionPane.showMessageDialog(null, "Error al obtener estadísticas de tiendas.\n" + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return numeroTienda;
    }
    
    
    
    /**
     * Rescata la lista de nombres y apellidos de todos los responsables de tienda.
     * Útil para rellenar dinámicamente JComboBox al registrar o editar tiendas.
     * 
     * @return ArrayList con los nombres de los responsables.
     */
    public static java.util.ArrayList<String> obtenerResponsables() {
        
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        
        String consulta = "SELECT nombre_apellidos FROM responsables_tienda";
        
        conectar();
        
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                
                lista.add(rs.getString("nombre_apellidos"));
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar responsables de tienda: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        
        return lista;
    }

    
    
    /**
     * Inserta un nuevo registro de tienda en la base de datos de la ferretería.
     * Emplea PreparedStatement para prevenir inyección SQL.
     * 
     * @param ti Objeto de tipo Tienda que contiene denominación, dirección y responsable.
     * @return true si se insertó correctamente, false si hubo un error.
     */
    public static boolean registrarTienda(modelo.Tienda ti) {
        
        boolean exito = false;
        
        String consulta = "INSERT INTO tiendas (denominacion, direccion, responsable) VALUES (?, ?, ?)";
        
        conectar();
        
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
            
            cerrarConexion();
            
        }
        return exito;
    }
    
    
    /**
     * Rescata la lista de nombres (denominaciones) de todas las tiendas.
     * Método diseñado para rellenar JComboBox en ventanas como la de Registro de Usuarios.
     * 
     * @return ArrayList con los nombres de las tiendas.
     */
    public static java.util.ArrayList<String> obtenerNombresTiendas() {
        
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        
        String consulta = "SELECT denominacion FROM tiendas"; 
        
        conectar();
        
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                lista.add(rs.getString("denominacion"));
                
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar los nombres de nuestras tiendas: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
            
        }
        return lista;
    }
    
    
    /**
     * Recupera todos los datos principales de las tiendas y los carga directamente en un modelo de tabla visual.
     * 
     * @param modelo El DefaultTableModel de la JTable (requiere 3 columnas).
     */
    public static void listadoTiendasAdmin(javax.swing.table.DefaultTableModel modelo) {

        modelo.setRowCount(0);

        Object[] fila = new Object[3];

        conectar();

        try {

            String consulta = "SELECT denominacion, direccion, responsable "
                    + "FROM tiendas ";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar el listado de tiendas: " + e.getMessage());

        } finally {

            cerrarConexion();
        }

    }
    
}
