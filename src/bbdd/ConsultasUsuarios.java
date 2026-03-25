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
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jintae
 */
public class ConsultasUsuarios extends Conexion {

    public static Usuario obtenerDatosUsuario(String loginUsuario) {
        conectar();
        Usuario user = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, loginUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setNombre(rs.getString("nombre_appellidos"));
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
            JOptionPane.showMessageDialog(null, "Error al obtener el número de ventas.\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return rescatarProducto;
    }

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
            JOptionPane.showMessageDialog(null, "Error al obtener el número de ventas.\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return rescatarProducto;
    }
}
