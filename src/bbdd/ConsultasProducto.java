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
import modelo.Producto;

/**
 * Clase principal encargada de gestionar todas las consultas a la base de datos relacionadas con la tabla producto.
 * Maneja el registro, modificación, eliminación, listados filtrados y estadísticas.
 * Hereda de la clase Conexion.
 * @author Jose y Patricia
 */
public class ConsultasProducto extends Conexion {

    
    /**
     * Recupera los tres últimos artículos registrados en la base de datos ordenados por fecha de alta de forma descendente.
     * La tabla que carga este método aparece tanto en VentanaAdmin como en VentanaUser.
     *
     * @param modelo
     */
    public static void ultimos3Articulos(javax.swing.table.DefaultTableModel modelo) {

        // Limpiamos la tabla para que no se dupliquen los datos al pulsar el botón
        modelo.setRowCount(0);

        // Definimos el array de objetos con 5 columnas según nuestro SELECT
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

    
  
    /**
     * Cuenta cuántos artículos hay registrados en total en el catálogo.
     * Se utiliza para actualizar las estadísticas, JLabel, en la interfaz de usuario.
     * @return El total de artículos. Devuelve 0 si hay error.
     */
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
            
            JOptionPane.showMessageDialog(null, "Error al obtener el número de artículos disponibles.\n" + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return numeroVentas;
    }

    
    
    /**
     * Cuenta cuántos artículos tienen actualmente el estado de oferta activo: 'SI'.
     * Se utiliza para los paneles de estadísticas en las ventanas principales.
     * @return El total de artículos en oferta.
     */
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
            
            JOptionPane.showMessageDialog(null, "Error al obtener el número de artículos en oferta.\n" + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return numeroVentas;
    }

    
    
    /**
     * Cuenta cuántos artículos están marcados como destacados: 'SI'.
     * Se utiliza para los paneles de estadísticas en las ventanas principales.
     * @return El total de artículos destacados.
     */
    public static int rescataArticulosDestacados() {

        int numeroVentas = 0;
        
        String consulta = "SELECT count(destacado)FROM producto "
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
            
            JOptionPane.showMessageDialog(null, "Error al obtener el número de artículos destacados.\n" + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return numeroVentas;
    }

    
    
    /**
     * Carga en la tabla especificada únicamente los artículos marcados como Destacados.
     * Versión para consulta general -> 4 columnas.
     * @param modelo 
     */
    public static void ArticulosDestacados(javax.swing.table.DefaultTableModel modelo) {

        modelo.setRowCount(0);

        Object[] fila = new Object[4];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, precio_venta "
                    + "FROM producto "
                    + "WHERE destacado = 'SI' "
                    + "ORDER BY fecha_alta DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

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
    
    

    /**
     * Carga en la tabla especificada únicamente los artículos marcados en Oferta.
     * 
     * @param modelo 
     */
    public static void ArticulosOferta(javax.swing.table.DefaultTableModel modelo) {

        modelo.setRowCount(0);

        Object[] fila = new Object[4];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, precio_venta "
                    + "FROM producto "
                    + "WHERE oferta = 'SI' "
                    + "ORDER BY fecha_alta DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

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

    
    
    
    /**
     * Carga el listado completo de productos en el modelo de la tabla proporcionado.
     * Muestra: código, nombre, categoría y precio de venta.
     * @param modelo 
     */
    public static void ListadoArticulos(javax.swing.table.DefaultTableModel modelo) {

        modelo.setRowCount(0);

        Object[] fila = new Object[4];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, precio_venta "
                    + "FROM producto "
                    + "ORDER BY fecha_alta DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

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

    /**
     * Registra un nuevo producto en la tabla producto de la base de datos de la Ferretería.
     * Inserta todos los campos del producto incluyendo el precio de venta
     * calculado automáticamente.
     * 
     * @param p Producto con todos los datos validados desde la interfaz.
     * @return true si la inserción en BBDD es correcta, false si hay error.
     */
    public static boolean registrarProducto(Producto p) {

        String consulta = "INSERT INTO producto (codProducto, nombre, categoria, descripcion, "
                + "precio_compra, precio_venta, stock, origen, destacado, oferta, fecha_alta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement pst = conn.prepareStatement(consulta);

            pst.setString(1, p.getCodProducto());
            pst.setString(2, p.getNombre());
            pst.setString(3, p.getCategoria());
            pst.setString(4, p.getDescripcion());
            pst.setDouble(5, p.getPrecioCompra());
            pst.setDouble(6, p.getPrecioVenta());
            pst.setInt(7, p.getStock());
            pst.setString(8, p.getOrigen());
            pst.setString(9, p.getDestacado());
            pst.setString(10, p.getOferta());
            pst.setDate(11, new java.sql.Date(p.getFechaAlta().getTime()));

            pst.executeUpdate();

            return true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null,
                    "Error al registrar artículo: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }

    }
    
    

    /**
     * Cuenta cuántos artículos de la base de datos son de origen Nacional.
     * Utilizado para alimentar las estadísticas de la interfaz Admin.
     * @return El total de productos nacionales.
     */
    public static int rescatarProductosNacionales() {

        int rescatarProducto = 0;
        
        String consulta = "SELECT count(origen) FROM producto "
                + "where origen = 'Nacional'";

        conectar();

        try {
            PreparedStatement comando = conn.prepareStatement(consulta);
            try (ResultSet reader = comando.executeQuery()) {
                if (reader.next()) {
                    rescatarProducto = reader.getInt(1);
                }
            }
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al obtener el número de ventas.\n" + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return rescatarProducto;
    }
    
    
    
    /**
     * Busca y rescata todos los datos de un producto específico mediante su código único.
     * @param codigo Código alfanumérico del producto a buscar.
     * @return Objeto Producto con todos sus datos, o null si no se encuentra.
     */
    public static Producto buscarProductoPorCodigo(String codigo) {
       
        Producto p = null;
        String sql = "SELECT * FROM producto WHERE codProducto = ?";

        conectar();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Producto(
                            rs.getString("codProducto"),
                            rs.getString("nombre"),
                            rs.getString("categoria"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio_compra"),
                            rs.getDouble("precio_venta"),
                            rs.getInt("stock"),
                            rs.getString("origen"),
                            rs.getString("destacado"),
                            rs.getString("oferta"),
                            rs.getDate("fecha_alta")
                    );
                }
            }
        } catch (SQLException e) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error SQL al buscar producto: " + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return p;
    }

   
    /**
     * Versión para el panel Administrador: Carga los artículos destacados incluyendo la columna destacado (5 columnas en total).
     * @param modelo 
     */
    public static void ArticulosDestacadosAdmin(javax.swing.table.DefaultTableModel modelo) {

        modelo.setRowCount(0);

        Object[] fila = new Object[5];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, precio_venta, destacado "
                    + "FROM producto "
                    + "WHERE destacado = 'SI' "
                    + "ORDER BY fecha_alta DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4);
                fila[4] = rs.getString(5);

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar artículos destacados: " + e.getMessage());

        } finally {

            cerrarConexion();
        }
    }
    
    
    /**
     * Actualiza EXCLUSIVAMENTE el valor destacado, Sí / No, de un producto.
     * Llamado desde la ventana VerListadoDestacados.
     * @param codigo
     * @param nuevoDestacado
     * @return 
     */
    public static boolean actualizarDestacado(String codigo, String nuevoDestacado) {
        
        boolean exito = false;
        
        String consulta = "UPDATE producto SET destacado = ? WHERE codProducto = ?";
        
        conectar();
        
        try {
            
            java.sql.PreparedStatement pst = Conexion.conn.prepareStatement(consulta);
            pst.setString(1, nuevoDestacado);
            pst.setString(2, codigo);
            
            if (pst.executeUpdate() > 0) {
                
                exito = true;
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error BBDD al actualizar destacado: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
            
        }
        
        return exito;
    }

    
    
    /**
     * Versión para el panel Administrador: Carga los artículos en oferta incluyendo la columna oferta (5 columnas en total).
     * @param modelo 
     */
    public static void ArticulosOfertaAdmin(javax.swing.table.DefaultTableModel modelo) {

        modelo.setRowCount(0);

        Object[] fila = new Object[5];

        conectar();

        try {

            String consulta = "SELECT codProducto, nombre, categoria, precio_venta, oferta "
                    + "FROM producto "
                    + "WHERE oferta = 'SI' "
                    + "ORDER BY fecha_alta DESC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4);
                fila[4] = rs.getString(5);

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar artículos en oferta: " + e.getMessage());

        } finally {

            cerrarConexion();
        }
    }
    
    
    
   
    /**
     * Carga todos los productos de la tabla en el modelo de la tabla.
     * Muestra código, nombre, categoría y precio de venta.
     * @param modelo DefaultTableModel de la JTable donde se cargarán los datos.
     */
    public static void cargarListadoArticulos(javax.swing.table.DefaultTableModel modelo) {
        
        modelo.setRowCount(0);
        
        String consulta = "SELECT codProducto, nombre, categoria, precio_venta FROM producto";
        
        conectar();
        
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            Object[] fila = new Object[4];
            
            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4);
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null,
                "Error al cargar artículos: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
    }

    
    /**
     * Rescata todos los datos de un producto por su código.
     * Devuelve el objeto instanciado con todos los atributos mapeados.
     * @param codProducto Código del producto a buscar.
     * @return Objeto {@link Producto} con todos los datos, o {@code null} si no existe.
     */
    public static Producto rescatarProductoPorCodigo(String codProducto) {
        
        String consulta = "SELECT * FROM producto WHERE codProducto = ?";
        
        conectar();
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(consulta);
            pst.setString(1, codProducto);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {            
                return new Producto(
                    rs.getString("codProducto"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio_compra"),
                    rs.getDouble("precio_venta"),
                    rs.getInt("stock"),
                    rs.getString("origen"),
                    rs.getString("destacado"),
                    rs.getString("oferta"),
                    rs.getDate("fecha_alta")
                );
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null,
                "Error al rescatar producto: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
            
        }
        return null;
    }

    
    /**
     * Actualiza el nombre y descripción de un producto en la tabla {@code producto}.
     * Solo permite modificar nombre y descripción según RF12.
     * @param codProducto Código del producto a actualizar.
     * @param nombre Nuevo nombre del producto.
     * @param descripcion Nueva descripción del producto.
     * @return {@code true} si la actualización fue correcta, {@code false} si hubo error.
     */
    public static boolean actualizarProducto(String codProducto, String nombre, String descripcion) {
        
        String consulta = "UPDATE producto SET nombre = ?, descripcion = ? WHERE codProducto = ?";
        
        conectar();
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(consulta);
            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setString(3, codProducto);
            pst.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null,
                "Error al actualizar producto: " + ex.getMessage());
            
            return false;
            
        } finally {
            
            cerrarConexion();
        }
    }

    
    /**
     * Elimina un producto de la tabla {@code producto} por su código.
     * @param codProducto Código del producto a eliminar.
     * @return {@code true} si la eliminación fue correcta, {@code false} si hubo error.
     */
    public static boolean eliminarProducto(String codProducto) {
        
        String consulta = "DELETE FROM producto WHERE codProducto = ?";
        
        conectar();
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(consulta);
            pst.setString(1, codProducto);
            pst.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null,
                    
                "Error al eliminar producto: " + ex.getMessage());
            
            return false;
                        
        } finally {
            
            cerrarConexion();
        }
    }
    
    
    
    /**
     * Carga en la tabla de VerListadoOfertas la información de los artículos para gestionar sus ofertas.
     * Muestra: Código, Nombre, Categoría, Precio de Venta y Oferta.
     */
    public static void cargarListadoOfertas(javax.swing.table.DefaultTableModel modelo) {
        
        modelo.setRowCount(0);
        
        
        String consulta = "SELECT codProducto, nombre, categoria, precio_venta, oferta FROM producto";
        
        conectar();
        
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                
                Object[] fila = new Object[5];
                fila[0] = rs.getString("codProducto");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("categoria");
                fila[3] = rs.getDouble("precio_venta");
                fila[4] = rs.getString("oferta");
                
                modelo.addRow(fila);
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar la tabla de artículos en oferta: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
    }

    
    /**
     * Actualiza EXCLUSIVAMENTE el valor 'oferta' de un producto en la base de datos.
     * Garantiza que el resto de los campos permanezcan inalterados.
     * 
     */
    public static boolean actualizarOferta(String codigo, String nuevaOferta) {
        
        boolean exito = false;
        String consulta = "UPDATE producto SET oferta = ? WHERE codProducto = ?";
        
        conectar();
        
        try {
            
            java.sql.PreparedStatement pst = Conexion.conn.prepareStatement(consulta);
            
            pst.setString(1, nuevaOferta);
            pst.setString(2, codigo);
            
            if (pst.executeUpdate() > 0) {
                exito = true;
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error BBDD al actualizar oferta: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        return exito;
    }
    
}
