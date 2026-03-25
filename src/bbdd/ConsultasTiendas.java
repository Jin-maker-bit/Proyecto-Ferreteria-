/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbdd;

import static bbdd.Conexion.cerrarConexion;
import static bbdd.Conexion.conectar;
import static bbdd.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jintae
 */
public class ConsultasTiendas extends Conexion {

    public static int rescataTienda() {

        int numeroTienda = 0;
        String consulta = "SELECT count(denominacion) FROM tiendas";

        conectar();

        try {
            PreparedStatement comando = conn.prepareStatement(consulta);
            try (ResultSet reader = comando.executeQuery()) {
                if (reader.next()) {
                    numeroTienda = reader.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el número de tiendas.\n" + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return numeroTienda;
    }
    
    
}
