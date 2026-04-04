/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import static bbdd.Conexion.cerrarConexion;
import static bbdd.Conexion.conectar;
import static bbdd.Conexion.conn;
import modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Clase principal encargada de gestionar todas las consultas a la base de datos relacionadas con la tabla usuarios.
 * Hereda de la clase Conexion para facilitar el acceso a la BBDD.
 * Todos los métodos gestionan íntegramente el ciclo de vida de la conexión: Apertura/Cierre.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class ConsultasUsuarios extends Conexion {

    
    /**
     * Obtiene todos los datos de un usuario específico a partir de su nombre de login.
     * Utilizado para cargar los datos del perfil o validar permisos puntuales.
     * 
     * @param loginUsuario El alias o nombre de inicio de sesión del usuario.
     * @return Objeto - Usuario - mapeado con los datos de la Base de datos o Null si no existe.
     */
    public static Usuario obtenerDatosUsuario(String loginUsuario) {

        conectar();

        Usuario user = null;

        String sql = "SELECT * FROM usuarios WHERE usuario = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, loginUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                user = new Usuario();
                user.setNombreCompleto(rs.getString("nombre_appellidos"));
                user.setTipo(rs.getString("tipo"));
                user.setTienda(rs.getString("tienda"));
                user.setUsuario(rs.getString("usuario"));

            }

        } catch (SQLException e) {

            System.err.println("Error al obtener datos: " + e.getMessage());

        } finally {

            cerrarConexion();
        }
        return user;
    }
    
    
    
    /**
     * Cuenta el número total de usuarios registrados en el sistema.
     * Se utiliza para cargar las estadísticas rápidas, JLabel, en el panel del Administrador.
     * 
     * @return Un entero con la cantidad total de usuarios.
     */
    public static int rescatarUsuariosTotales() {

        int rescatarProducto = 0;
        String consulta = "SELECT count(idUsuario) FROM usuarios";

        conectar();

        try {
            PreparedStatement comando = conn.prepareStatement(consulta);
            
            try (ResultSet reader = comando.executeQuery()) {
                if (reader.next()) {
                    rescatarProducto = reader.getInt(1);
                }
            }
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al obtener el número de usuarios totales.\n" + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return rescatarProducto;
    }

    
    
    
   /**
    * Cuenta el número de usuarios que actualmente tienen el estado Activo.
    * Se utiliza para cargar las estadísticas rápidas, JLabel, en el panel del Administrador.
    * 
    * @return Un entero con la cantidad de usuarios activos.
    */
    public static int rescatarUsuariosActivos() {

        int rescatarProducto = 0;
        
        String consulta = "SELECT count(idUsuario) FROM usuarios "
                + "where estado = 'Activo'";

        conectar();

        try {
            
            PreparedStatement comando = conn.prepareStatement(consulta);
            
            try (ResultSet reader = comando.executeQuery()) {
                if (reader.next()) {
                    rescatarProducto = reader.getInt(1);
                }
            }
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al obtener el número de usuarios activos.\n" + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }

        return rescatarProducto;
    }
    
    
  
     /**
      * Registra un nuevo empleado, usuario, en la base de datos de la ferretería.
      * Convierte automáticamente la fecha de Java a formato SQL.
      * 
      * @param usu Objeto Usuario con los datos a insertar.
      * @return true si se insertó correctamente, false si hubo un error.
      */
    public static boolean registrarUsuario(modelo.Usuario usu) {
        
        boolean exito = false;
        
        String consulta = "INSERT INTO usuarios (nombre_apellidos, usuario, pass, tipo, estado, fecha_alta, tienda) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        conectar();
        
        try {
            
            java.sql.PreparedStatement pst = Conexion.conn.prepareStatement(consulta);
            pst.setString(1, usu.getNombreCompleto());
            pst.setString(2, usu.getUsuario());
            pst.setString(3, usu.getPass());
            pst.setString(4, usu.getTipo());
            pst.setString(5, usu.getEstado());
            
            // FECHA: Convertimos el Date normal de Java al Date de SQL
            java.sql.Date fechaSql = new java.sql.Date(usu.getFechaAlta().getTime());
            pst.setDate(6, fechaSql);
            
            pst.setString(7, usu.getTienda());
            
            if (pst.executeUpdate() > 0) {
                exito = true;
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error BBDD: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        return exito;
    }
    
    
    /**
     * Rescata la información pública del perfil del usuario (Nombre, login, tipo y tienda).
     * Se utiliza para mostrar los datos en la ventana de Mi Perfil.
     * 
     * @param userLogado El nombre de usuario que ha iniciado sesión.
     * @return Un objeto Usuario con la información del perfil.
     */
    public static Usuario obtenerDatosPerfil(String userLogado) {
        
        Usuario u = null;

        String sql = "SELECT nombre_apellidos, usuario, tipo, tienda FROM usuarios WHERE usuario = ?";

        conectar();

        try (PreparedStatement ps = Conexion.conn.prepareStatement(sql)) {
            
            ps.setString(1, userLogado);

            try (ResultSet rs = ps.executeQuery()) {
                
                if (rs.next()) {
                    
                    u = new Usuario();
                    u.setNombreCompleto(rs.getString("nombre_apellidos"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setTipo(rs.getString("tipo"));
                    u.setTienda(rs.getString("tienda"));
                }
            }
            
        } catch (SQLException e) {
            
            System.err.println("Error al obtener datos del perfil: " + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        
        return u;
    }

    
    /**
     * Actualiza el nombre real y la contraseña de un usuario desde su panel de perfil.
     * Implementa una medida de seguridad doble verificando la contraseña actual antes de aplicar cambios.
     * 
     * @param user El nombre de login del usuario.
     * @param nuevoNombre El nuevo nombre y apellidos a registrar.
     * @param nuevaPass La nueva contraseña deseada.
     * @param passActual La contraseña actual (para verificar la identidad).
     * @return true si la actualización fue exitosa, false si falló
     */
    public static boolean actualizarDatos(String user, String nuevoNombre, String nuevaPass, String passActual) {
        
        boolean actualizado = false;
        
        String sql = "UPDATE usuarios SET nombre_apellidos = ?, pass = ? WHERE usuario = ? AND pass = ?";

        conectar();
        
        try (PreparedStatement ps = Conexion.conn.prepareStatement(sql)) {
            
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevaPass);
            ps.setString(3, user);
            ps.setString(4, passActual);

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                
                actualizado = true;
            }
        } catch (SQLException e) {
            
            System.err.println("Error al actualizar datos: " + e.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        return actualizado;
    }
    
    
    
    /**
     * Carga el listado completo de empleados en el modelo de tabla proporcionado.
     * Extrae información detallada incluyendo credenciales, roles y estados ordenando los registros por su ID de forma ascendente.
     * 
     * @param modelo El DefaultTableModel de la JTable donde se volcarán los datos (requiere 8 columnas).
     */
    public static void listadoUsuarios(javax.swing.table.DefaultTableModel modelo) {

        modelo.setRowCount(0);

        Object[] fila = new Object[8];

        conectar();

        try {

            String consulta = "SELECT idUsuario, nombre_apellidos, tienda, usuario, pass, tipo, estado, fecha_alta "
                    + "FROM usuarios "
                    + "ORDER BY idUsuario ASC";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getString(7);
                fila[7] = rs.getString(8);
                              
                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar las lista de usuarios " + e.getMessage());

        } finally {

            cerrarConexion();
        }

    }
    
    
      
    /**
     * Recupera el listado completo de usuarios de la base de datos y los vuelca en el modelo de la tabla.
     * A diferencia de otros métodos, este utiliza el nombre explícito de las columnas de la base de datos para garantizar la integridad del mapeo de datos.
     * 
     * @param modelo El DefaultTableModel que se desea poblar (debe tener 8 columnas configuradas).
     */
    public static void cargarListadoUsuarios(javax.swing.table.DefaultTableModel modelo) {
        
        modelo.setRowCount(0);
        
        String consulta = "SELECT idUsuario, nombre_apellidos, tienda, usuario, pass, tipo, estado, fecha_alta FROM usuarios";
        
        conectar();
        try {
            
            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                Object[] fila = new Object[8]; 
                fila[0] = rs.getInt("idUsuario");
                fila[1] = rs.getString("nombre_apellidos");
                fila[2] = rs.getString("tienda");
                fila[3] = rs.getString("usuario");
                fila[4] = rs.getString("pass");
                fila[5] = rs.getString("tipo");
                fila[6] = rs.getString("estado");
                fila[7] = rs.getDate("fecha_alta"); 
                
                modelo.addRow(fila);
            }
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar la tabla de usuarios: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
    }

    
    /**
     * Actualiza los permisos de administración (Tienda, Tipo/Rol y Estado) de un usuario.
     * Este método protege la privacidad del empleado al no permitir modificar su contraseña desde el panel de gestión de administrador.
     * 
     * @param idUsuario idUsuario El ID numérico único del usuario en la base de datos.
     * @param tienda La nueva tienda a la que pertenece.
     * @param tipo El nuevo rol (Admin / User).
     * @param estado El nuevo estado de acceso.
     * @return true si la actualización fue correcta, false en caso de error.
     */
    public static boolean actualizarUsuarioRoles(String idUsuario, String tienda, String tipo, String estado) {
        
        boolean exito = false;
        
        String consulta = "UPDATE usuarios SET tienda = ?, tipo = ?, estado = ? WHERE idUsuario = ?";
        
        conectar();
        
        try {
            
            java.sql.PreparedStatement pst = Conexion.conn.prepareStatement(consulta);
            
            pst.setString(1, tienda);
            pst.setString(2, tipo);
            pst.setString(3, estado);
            pst.setString(4, idUsuario); // Aunque sea int en BBDD, el PreparedStatement lo convierte
            
            if (pst.executeUpdate() > 0) {
                
                exito = true;
            }
            
        } catch (java.sql.SQLException ex) {
            
            javax.swing.JOptionPane.showMessageDialog(null, "Error BBDD al actualizar roles de usuario: " + ex.getMessage());
            
        } finally {
            
            cerrarConexion();
        }
        
        return exito;
    }
    

}
    
   
