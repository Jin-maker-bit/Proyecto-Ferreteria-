/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import static bbdd.Conexion.cerrarConexion;
import static bbdd.Conexion.conectar;
import static bbdd.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jintae
 */
public class ConsultasProducto extends Conexion {

    /**
     * Recupera los tres últimos artículos registrados en la base de datos
     * ordenados por fecha de alta de forma descendente y los carga en la tabla.
     * La tabla que carga este método aparece tanto en VentanaAdmin como en
     * VentanaUser.
     *
     * @param modelo
     */
    public static void ultimos3Articulos(javax.swing.table.DefaultTableModel modelo) {

        // Limpiamos la tabla para que no se dupliquen los datos al pulsar el botón
        modelo.setRowCount(0);

        // Definimos el array de objetos (fila) con 5 columnas según nuestro SELECT
        Object[] fila = new Object[5];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, stock, precio_venta "
                    + "FROM producto "
                    + "ORDER BY fecha_alta DESC "
                    + "LIMIT 3";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            // Recorremos el ResultSet y llenamos el modelo
            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getInt(4);
                fila[4] = rs.getDouble(5);

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar últimos artículos: " + e.getMessage());

        } finally {

            cerrarConexion();
        }
    }

    public static int rescataArticulosDisponibles() {

        int numeroVentas = 0;
        String consulta = "SELECT count(stock) FROM producto";

        conectar();

        try {
            PreparedStatement comando = conn.prepareStatement(consulta);
            try (ResultSet reader = comando.executeQuery()) {
                if (reader.next()) {
                    numeroVentas = reader.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el número de ventas.\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return numeroVentas;
    }

    public static int rescataArticulosOferta() {

        int numeroVentas = 0;
        String consulta = "SELECT count(oferta) FROM producto "
                + "where UPPER(oferta) = 'SI'";

        conectar();

        try {
            PreparedStatement comando = conn.prepareStatement(consulta);
            try (ResultSet reader = comando.executeQuery()) {
                if (reader.next()) {
                    numeroVentas = reader.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el número de ventas.\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return numeroVentas;
    }

    public static int rescataArticulosDestacados() {

        int numeroVentas = 0;
        String consulta = "SELECT count(destacado) FROM producto "
                + "where UPPER(destacado) = 'SI'";

        conectar();

        try {
            PreparedStatement comando = conn.prepareStatement(consulta);
            try (ResultSet reader = comando.executeQuery()) {
                if (reader.next()) {
                    numeroVentas = reader.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el número de ventas.\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return numeroVentas;
    }

    public static void ArticulosDestacados(javax.swing.table.DefaultTableModel modelo) {

        // Limpiamos la tabla para que no se dupliquen los datos al pulsar el botón
        modelo.setRowCount(0);

        // Definimos el array de objetos (fila) con 4 columnas según nuestro SELECT
        Object[] fila = new Object[4];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, precio_venta "
                    + "FROM producto "
                    + "WHERE destacado = 'SI' "
                    + "ORDER BY fecha_alta DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            // Recorremos el ResultSet y llenamos el modelo
            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4);

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar artículos destacados: " + e.getMessage());

        } finally {

            cerrarConexion();
        }
    }

    public static void ArticulosOferta(javax.swing.table.DefaultTableModel modelo) {

        // Limpiamos la tabla para que no se dupliquen los datos al pulsar el botón
        modelo.setRowCount(0);

        // Definimos el array de objetos (fila) con 4 columnas según nuestro SELECT
        Object[] fila = new Object[4];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, precio_venta "
                    + "FROM producto "
                    + "WHERE oferta = 'SI' "
                    + "ORDER BY fecha_alta DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            // Recorremos el ResultSet y llenamos el modelo
            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4);

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar artículos en oferta: " + e.getMessage());

        } finally {

            cerrarConexion();
        }
    }

    public static void ListadoArticulos(javax.swing.table.DefaultTableModel modelo) {

        // Limpiamos la tabla para que no se dupliquen los datos al pulsar el botón
        modelo.setRowCount(0);

        // Definimos el array de objetos (fila) con 4 columnas según nuestro SELECT
        Object[] fila = new Object[4];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, precio_venta "
                    + "FROM producto "
                    + "ORDER BY fecha_alta DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            // Recorremos el ResultSet y llenamos el modelo
            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4);

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar las lista de productos " + e.getMessage());

        } finally {

            cerrarConexion();
        }
    }
}
