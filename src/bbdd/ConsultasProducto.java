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
 * Todos los métodos gestionan íntegramente el ciclo de vida de la conexión: Apertura/Cierre.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class ConsultasProducto extends Conexion {

    
    /**
     * Recupera los tres últimos artículos registrados en la base de datos ordenados por fecha de alta de forma descendente.
     * La tabla que carga este método aparece tanto en VentanaAdmin como en VentanaUser.
     * Ordena por fecha y, ante empates, por código para garantizar el orden cronológico real.
     * 
     * @param modelo El modelo de la tabla a completar (5 columnas).
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
                + "ORDER BY fecha_alta DESC, codProducto DESC " // <--- DESC en ambos
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
     * 
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
     * 
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
     * 
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
     * 
     * @param modelo DefaultTableModel donde volcar los datos.
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
     * Carga en el modelo de tabla proporcionado únicamente los artículos marcados con el estado oferta SI.
     * Los resultados se muestran ordenados por fecha de alta de forma descendente, priorizando las ofertas más recientes.
     * 
     * @param modelo El DefaultTableModel de la JTable (se requieren 4 columnas: Código, Nombre, Categoría y Precio).
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
     * 
     * @param modelo DefaultTableModel donde volcar los datos.
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

        conectar ();
        
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
            
        } finally {           
            cerrarConexion();
        }

    }
    
    

    /**
     * Cuenta cuántos artículos de la base de datos son de origen Nacional.
     * Utilizado para alimentar las estadísticas de la interfaz Admin.
     * 
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
            
            JOptionPane.showMessageDialog(null, "Error al obtener el número de productos nacionales.\n" + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return rescatarProducto;
    }
    
    
    
    /**
     * Busca y rescata todos los datos de un producto específico mediante su código único.
     * 
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
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al buscar producto por su código: " + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return p;
    }

   
    /**
     * Recupera y carga en el modelo de tabla los artículos marcados como destacados específicamente para la vista de Administración.
     * Incluye una quinta columna con el estado SI / NO para facilitar la gestión.
     * Los resultados se ordenan cronológicamente (más recientes primero).
     * 
     * @param modelo El DefaultTableModel de la JTable administrativa (requiere 5 columnas).
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

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar los artículos destacados: " + e.getMessage());

        } finally {

            cerrarConexion();
        }
    }
    
    
    /**
     * Actualiza EXCLUSIVAMENTE el valor destacado, Sí / No, de un producto.
     * Llamado desde la ventana VerListadoDestacados.
     * 
     * @param codigo El código único del producto que se desea modificar.
     * @param nuevoDestacado El nuevo estado a asignar: "SI" o "NO".
     * @return true si la actualización se realizó con éxito. False en caso contrario.
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
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al actualizar el valor destacado: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
            
        }
        
        return exito;
    }

    
    
    /**
     * Recupera y carga en el modelo de tabla los artículos en oferta para la vista de Administración.
     * Incluye una quinta columna con el valor del estado 'oferta' para facilitar su gestión y edición.
     * Los resultados se ordenan por fecha de alta de forma descendente.
     * 
     * @param modelo El DefaultTableModel de la JTable Admin (requiere 5 columnas configuradas).
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
     * Recupera el catálogo completo de productos de la base de datos.
     * Vuelca la información de identificación básica y comercial (código, nombre, categoría y precio) directamente en el modelo de la tabla proporcionado.
     * 
     * @param modelo El DefaultTableModel de la JTable donde se visualizarán los artículos (requiere 4 columnas).
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
                "Error al cargar el listado de artículos: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
    }

    
    /**
     * Rescata todos los datos de un producto por su código.
     * Devuelve el objeto instanciado con todos los atributos mapeados.
     * 
     * @param codProducto Código del producto a buscar.
     * @return Objeto Producto con todos los datos o null si no existe.
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
                "Error al rescatar todos los datos de un producto por su código: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
            
        }
        return null;
    }

    
    /**
     * Actualiza el nombre y descripción de un producto en la tabla {@code producto}.
     * Solo permite modificar nombre y descripción según requisitos requeridos.
     * 
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
     * Elimina de forma permanente un registro de la tabla Producto utilizando su código único.
     * Implementa consultas parametrizadas para garantizar la seguridad de la operación.
     * 
     * @param codProducto Identificador alfanumérico (P000) del producto a eliminar.
     * @return true si la eliminación fue correcta. Devuelve false si hubo error.
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
     * Recupera y vuelca en el modelo de tabla la información de los artículos que tienen una oferta activa.
     * Diseñado para la ventana VerListadoOfertas, permitiendo visualizar los campos clave para la gestión de promociones.
     * 
     * @param modelo El DefaultTableModel de la JTable (requiere 5 columnas: Código, Nombre, Categoría, Precio y Oferta).
     */
    public static void cargarListadoOfertas(javax.swing.table.DefaultTableModel modelo) {
        
        modelo.setRowCount(0);
        
        String consulta = "SELECT codProducto, nombre, categoria, precio_venta, oferta FROM producto WHERE oferta = 'SI' ";
        
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
      * @param codigo Código del Producto a modificar.
      * @param nuevaOferta Nuevo valor 'SI / NO'.
      * @return true si se actualiza correctamente.
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
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al actualizar el valor oferta: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        return exito;
    }
    
    
    
    /**
     * Genera automáticamente por orden el siguiente código de producto para nuevos registros.
     * Consulta el último código almacenado en la base de datos, extrae su valor numérico y lo incrementa en una unidad y le devuelve el formato prefijado. Ej: Pasar de "P020" a "P021".
     * Ante la ausencia de un campo AUTO_INCREMENT en la estructura de la base de datos, este método subsana ya que simula un contador automático. 
     * Rescata el código más alto, incrementa su valor y mantiene el formato "P000".
     * Este sistema garantiza un orden cronológico estricto en las consultas, permitiendo que la tabla de "Últimos 3 artículos" funcione correctamente mediante un doble criterio de ordenación (fecha_alta y codProducto), subsanando así la falta de precisión en el tipo de dato DATE ya que no registra la hora exacta.
     * Por tanto, el incremento del codProducto permite a la aplicación distinguir el orden de creación entre artículos registrados el mismo día, garantizando que el último registro sea siempre el que encabece las tablas y listados.
     * 
     * @return String con el siguiente código generado, Ej: "P021", "P022", "P023", etc.
     */
    public static String generarSiguienteCodigoProducto() {
    
        String nuevoCodigo = "P001"; // Valor por defecto si la tabla está vacía
        
        conectar();
        
        try {
            // Buscamos el código más alto (alfabéticamente y por longitud)
            String sql = "SELECT codProducto FROM producto ORDER BY LENGTH(codProducto) DESC, codProducto DESC LIMIT 1";
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                
                String ultimoCodigo = rs.getString(1); // Ej: P020
                // Quitamos la 'P' y cualquier espacio, y convertimos a número y nos quedamos con el número
                String soloNumero = ultimoCodigo.replace("P", "").trim();
                int numero = Integer.parseInt(soloNumero);
                
                // Sumamos 1 y volvemos a poner la 'P' con formato
                // Así generamos el siguiente con el formato P000
                nuevoCodigo = String.format("P%03d", numero + 1); 
            }
            
        } catch (Exception e) {
            
            System.err.println("Error al generar un nuevo código de Producto: " + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        
        return nuevoCodigo;
    }
    
    
    
}
