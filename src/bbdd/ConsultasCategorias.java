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
import javax.swing.table.DefaultTableModel;

/**
 * Clase encargada de gestionar todas las consultas a la base de datos relacionadas con la tabla categorias.
 * Hereda de la clase Conexion para facilitar el acceso a la BBDD.
 * @author Jose y Patricia
 */
public class ConsultasCategorias extends Conexion {

    
    /**
     * Rescata todos los nombres de las categorías registradas en la base de datos.
     * Este método es especialmente útil para rellenar dinámicamente los JComboBox en las ventanas de registro o edición de artículos.
     * @return ArrayList con los nombres de las categorías.
     */
    public static java.util.ArrayList<String> obtenerCategorias() {

        java.util.ArrayList<String> lista = new java.util.ArrayList<>();

        String consulta = "SELECT categoria FROM categorias";

        conectar();

        try {

            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {

                lista.add(rs.getString(1));
            }

        } catch (java.sql.SQLException ex) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar categorías: " + ex.getMessage());

        } finally {

            cerrarConexion();
        }
        return lista;
    }

    
    
    /**
     * Inserta una nueva categoría en la base de datos.
     * Utiliza consultas parametrizadas - PreparedStatement- para mayor seguridad.
     * @param cat Objeto Categoria con los datos a insertar.
     * @return true si se insertó correctamente, false si hubo un error.
     */
    public static boolean registrarCategoria(modelo.Categoria cat) {

        boolean exito = false;

        String consulta = "INSERT INTO categorias (categoria, descripcion) VALUES (?, ?)";

        conectar();

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

            cerrarConexion();
        }

        return exito;
    }

    
    /**
     * Rescata todas las categorías y sus descripciones para mostrarlas en una tabla.
     * Modifica directamente el modelo de la tabla que se le pasa por parámetro.
     * @param categoria 
     */
    public static void listadoCategorias(DefaultTableModel categoria) {

        categoria.setRowCount(0);

        Object[] fila = new Object[2];

        conectar();

        try {

            String consulta = "SELECT categoria, descripcion "
                    + "FROM categorias";
  

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
             

                categoria.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar categorias: " + e.getMessage());

        } finally {

            cerrarConexion();
        }
    }
}
