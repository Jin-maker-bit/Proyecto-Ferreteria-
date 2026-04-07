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

/**
 * Clase de gestión de la conexión con la base de datos MySQL. 
 * Proporciona métodos estáticos para establecer y cerrar la conexión, así como para realizar las consultas relacionadas con la autenticación y recuperación de datos de los usuarios logados.
 * La mayoría de métodos gestionan íntegramente el ciclo de vida de la conexión: Apertura/Cierre.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 * 
 */
public class Conexion {

    /**
     * Objeto de conexión estático.
     */
    public static Connection conn;
    private static String ip;

    
    /**
     * Método que establece la conexión con el servidor MySQL. 
     * Utiliza el driver JDBC para conectar con la base de datos ferreteria.
     * Configurado para soportar entornos locales MAMP (puerto 8889) y XAMPP (3307).
     */
    public static void conectar() {
        
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // PC DE JOSÉ: Cambiado 8809 por 8889 (Puerto por defecto de MAMP) - Con contraseña "root" (mac Jose).
            // PC DE PATRI: Puerto 3307 - Xampp - Sin contraseña.
            String url = "jdbc:mysql://localhost:8889/ferreteria?serverTimezone=UTC&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "root");

        } catch (ClassNotFoundException | SQLException ex) {

            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, "Error de conexión con la base de datos", ex);

        }
    }

   
    /**
     * Método que finaliza la conexión activa con el servidor de base de datos.
     * Sirve también para liberar recursos del sistema. 
     * Se recomienda llamar a este método siempre después de completar cualquier operación con la base de datos.
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
     * Método que valida las credenciales de un usuario en base a la base de datos de Ferretería. 
     * Solo permite el acceso si el usuario existe, la contraseña coincide y su estado es activo.
     * 
     * @param user Nombre de usuario introducida en la ventana de login.
     * @param pass Contraseña introducida en la ventana de login.
     * @return validación correcta y entrada a la aplicación si las credenciales son correctas. En caso contrario no permitir acceso.
     */
    public static boolean acceder(String user, String pass) {
        
        conectar();
        
        String consulta = "SELECT usuario, pass FROM usuarios WHERE usuario=? and pass=? and estado ='activo'";
        boolean loginCorrecto = false; // Variable auxiliar para el retorno
        
        
        try {

            PreparedStatement pst;
            ResultSet rs;

            pst = conn.prepareStatement(consulta);
            pst.setString(1, user);
            pst.setString(2, pass);

            rs = pst.executeQuery();

            if (rs.next()) {
                loginCorrecto = true; // Si hay resultado, las credenciales son válidas
            }

        } catch (SQLException ex) {

            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            loginCorrecto = false;
        
        } finally {
            
            cerrarConexion(); // Cerramos siempre
        }
        
        return loginCorrecto;
    }

    
    
    

    /**
     * Extrae los valores permitidos de una columna tipo ENUM en la base de datos. 
     * Utiliza la sentencia SQL para obtener la definición estructural de la columna. 
     * Posteriormente, procesa la cadena devuelta por el motor de base de datos para aislar los valores. 
     * 
     * @param tabla El nombre de la tabla en la base de datos.
     * @param columna El nombre de la columna.
     * @return ArrayList con los valores limpios del ENUM.
     */
    public static java.util.ArrayList<String> obtenerValoresEnum(String tabla, String columna) {

        java.util.ArrayList<String> lista = new java.util.ArrayList<>();

        String consulta = "SHOW COLUMNS FROM " + tabla + " LIKE '" + columna + "'";

        conectar();

        try {

            java.sql.Statement st = Conexion.conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(consulta);

            if (rs.next()) {

                String tipoEnum = rs.getString("Type");

                tipoEnum = tipoEnum.replace("enum(", "").replace(")", "").replace("'", "");

                String[] valores = tipoEnum.split(",");

                for (String valor : valores) {

                    lista.add(valor.trim());
                }
            }
        } catch (java.sql.SQLException ex) {

            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar ENUM: " + ex.getMessage());

        } finally {

            cerrarConexion();
        }

        return lista;
    }
}
