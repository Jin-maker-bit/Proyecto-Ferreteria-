/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Usuario;

/**
 * Clase de gestión de la conexión con la base de datos MySQL. Proporciona
 * métodos estáticos para establecer y cerrar la conexión, así como para
 * realizar las consultas relacionadas con la autenticación y recuperación de
 * datos de los usuarios logados.
 *
 * @author Jose y Patricia
 */
public class Conexion {

    /**
     * Objeto de conexión estático.
     */
    public static Connection conn;
    private static String ip;

    /**
     * Método que establece la conexión con el servidor MySQL. Utiliza el driver
     * JDBC para conectar con la base de datos ferreteria.
     */
    public static void conectar() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // PC DE JOSÉ: Cambiado 8809 por 8889 (Puerto por defecto de MAMP) - Con contraseña "root" (mac Jose).
            // PC DE PATRI: Puerto 3307 - Xampp - Sin contraseña.
            String url = "jdbc:mysql://localhost:3307/ferreteria?serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "");

        } catch (ClassNotFoundException | SQLException ex) {

            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, "¡Error al conectar a la base de datos!", ex);

        }
    }

    /**
     * Método que finaliza la conexión activa con el servidor de base de datos.
     * Sirve también para liberar recursos del sistema. Se recomienda llamar a
     * este método siempre después de completar cualquier operación con la base
     * de datos.
     */
    public static void cerrarConexion() {

        try {

            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {

            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    /**
     * Método que valida las credenciales de un usuario en base a la base de
     * datos de Ferretería. Sólo permite el acceso a usuarios con estado activo
     * y no bloqueado.
     *
     * @param user Nombre de usuario introducida en la ventana de login.
     * @param pass Contraseña introducida en la ventana de login.
     * @return validación correcta y entrada a la aplicación si las credenciales
     * son correctas. En caso contrario no permitir acceso.
     */
    public static boolean acceder(String user, String pass) {

        try {

            String consulta = "SELECT usuario, pass FROM usuarios WHERE usuario=? and pass=? and estado ='activo'";

            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(consulta);
            pst.setString(1, user);
            pst.setString(2, pass);

            rs = pst.executeQuery();

            return (rs.next());

        } catch (SQLException ex) {

            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
        return false;
    }

    /**
     * Método que recupera el tipo o rol de usuario logado desde la base de
     * datos Ferretería.
     *
     * @param user Nombre de usuario del que se recupera el tipo.
     * @return Admin o User según credenciales introducidas o nullo si no se
     * encuentra al usuario.
     */
    public static String recuperaTipo(String user) {

        String tipo = null;

        String consultaTipo = "SELECT tipo FROM usuarios WHERE usuario='" + user + "'";

        Statement st;
        ResultSet rs;

        try {

            st = conn.createStatement();

            rs = st.executeQuery(consultaTipo);

            if (rs.next()) {
                tipo = rs.getString(1);
            }
        } catch (SQLException ex) {

            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
        return tipo;
    }

    /**
     * Método que recupera los datos básicos del usuario logado desde la base de
     * datos de la Ferretería. Devuelve un array con nombre, apellidos y tipo
     * del usuario.
     *
     * @param user Nombre de usuario del que se quieren recuperar los datos.
     * @return Array con nombre, apellidos y tipo o nulo si no se encuentra.
     */
    /**
     * Recupera el objeto Usuario completo según el login. Ajustado para que
     * coincida con el campo único 'nombreCompleto' de la DB.
     */
    

    /**
     * Método que comprueba si un nombre de usuario existe en la base de datos
     * de la Ferretería.
     *
     * @param usuario Nombre de usuario que se quiere comprobar.
     * @return Usuario disponible o nulo.
     */
    public static boolean compruebaUsuario(String usuario) {

        try {

            String consulta = "SELECT usuario FROM usuarios WHERE usuario = ?";

            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(consulta);
            pst.setString(1, usuario);
            rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException ex) {

            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
        return false;
    }

    /**
     * Extrae los valores permitidos de una columna tipo ENUM en la base de
     * datos. Utiliza la sentencia SQL para obtener la definición estructural de
     * la columna. Posteriormente, procesa la cadena devuelta por el motor de
     * base de datos para aislar los valores. Gestiona su propio ciclo de vida
     * de conexión - abrir / cerrar - para garantizar la seguridad y liberar
     * recursos en el bloque.
     *
     * @param tabla El nombre de la tabla
     * @param columna El nombre de la columna
     * @return ArrayList con los valores limpios del ENUM.
     */
    public static java.util.ArrayList<String> obtenerValoresEnum(String tabla, String columna) {

        java.util.ArrayList<String> lista = new java.util.ArrayList<>();

        String consulta = "SHOW COLUMNS FROM " + tabla + " LIKE '" + columna + "'";

        Conexion.conectar();

        try {

            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);

            if (rs.next()) {

                String tipoEnum = rs.getString("Type");

                tipoEnum = tipoEnum.replace("enum(", "").replace(")", "").replace("'", "");

                String[] valores = tipoEnum.split(",");

                for (String valor : valores) {

                    lista.add(valor);
                }
            }
        } catch (java.sql.SQLException ex) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar ENUM: " + ex.getMessage());

        } finally {

            Conexion.cerrarConexion();
        }

        return lista;
    }
}
