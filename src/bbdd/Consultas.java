/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import static bbdd.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jintae
 */
public class Consultas {
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
