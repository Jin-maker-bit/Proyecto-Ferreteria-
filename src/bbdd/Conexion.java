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
 *
 * @author jintae
 */
public class Conexion {

    public static Connection conn;

    public static void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/ferreteria?serverTimezone=UTC";
            conn = DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, "¡Error al conectar a la base de datos!", ex);
        }
    }

    /**
     * Finaliza la conexión activa para liberar recursos en el servidor.
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

    public static boolean acceder(String user, String pass) {

        try {
            String consulta = "SELECT usuario, pass FROM usuarios WHERE usuario=? and pass=?";
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

    public static String recuperaTipo(String user) {

        String tipo = null;

        String consultaTipo = "SELECT tipo FROM usuarios WHERE usuario='" + user + "'";

        Statement st;
        ResultSet rs;

        try {

            st = conn.createStatement(); // st = conn.createStatement();

            rs = st.executeQuery(consultaTipo);

            if (rs.next()) {
                tipo = rs.getString(1);
            }
        } // Fin de public static String recuperaTipo
        catch (SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return tipo;

    }

    public static String[] recuperaDatosLogado(String user) {

        String[] usuario = new String[3];
        String consultaRecuperaDatos = "select nombre, apellidos, tipo from usuarios where usuario = '" + user + "'";

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(consultaRecuperaDatos);
            if (rs.next()) {
                usuario[0] = rs.getString(1);
                usuario[1] = rs.getString(2);
                usuario[2] = rs.getString(3);
            }
        } catch (SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return usuario;
    }

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

}
