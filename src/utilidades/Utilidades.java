/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jintae
 */
public class Utilidades {

    public static boolean compruebaCampoVacio(JTextField campo) {
        return "".equals(campo.getText());

    }

    public static void lanzaAlertaVacio(JTextField campo) {
        JOptionPane.showMessageDialog(null,
                "El campo " + campo.getName() + " es obligatorio");
        campo.setBackground(Color.red);
    }

    public static boolean compruebaComboNoSeleccionado(JComboBox combo) {
        return combo.getSelectedIndex() == 0;
    }

    public static void lanzaAlertaCombo(JComboBox combo) {
        JOptionPane.showMessageDialog(null,
                "Debe seleccionar un elemento en el desplegable " + combo.getName());
    }
    public static boolean compruebaEntero(JTextField campo) {
        String numeroTecleado = campo.getText();
        int miNumero;
        try {
            miNumero = Integer.parseInt(numeroTecleado);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void lanzaAlertaNumeroNoValido(JTextField campo) {
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " debe ser númerico");
        campo.setBackground(Color.red);
    }

    static String patronDNI = "^[0-9]{8}[A-Za-z]$";

    public static boolean compruebaDNI(JTextField campo) {
        return campo.getText().matches(patronDNI);
    }

    public static void lanzaAlertaFormatoDNI(JTextField campo) {
        JOptionPane.showMessageDialog(null, "El formato DNI es invalido");
        campo.setBackground(Color.red);
    }

    public static boolean compruebaTelefonoValido(JTextField campo) {
        String telefono = campo.getText().trim();
        if (telefono.startsWith("-")) {
            return false;
        }
        String telefonoLimpio = telefono.replaceAll("[\\s-]", "");
        boolean valido = telefonoLimpio.matches("^([6789]\\d{8}|[+]\\d{10,15})$");
        return valido;
    }

    public static void lanzaAlertaTelefonoNoValido(JTextField campo) {
        JOptionPane.showMessageDialog(null, "Formato de teléfono no válido\n");
        campo.setBackground(Color.red);

    }

    public static boolean compruebaEmailValido(JTextField campo) {
        String email = campo.getText().trim().toLowerCase();

        if (email.isEmpty()) {
            return false;
        }

        String patronEmail = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
        boolean valido = email.matches(patronEmail);
        return valido;
    }

    public static void lanzaAlertaEmailNoValido(JTextField campo) {
        JOptionPane.showMessageDialog(null,
                "Formato de email no válido");
        campo.setBackground(Color.red);
    }

    public static boolean compruebaCiclo(JTextField campo) {
        String texto = campo.getText().trim();
        boolean valido = texto.matches("^[a-zA-Z]{3}$");

        if (valido) {
            campo.setText(texto.toUpperCase());
        }
        return valido;
    }

    public static void lanzaAlertaCicloNoValido(JTextField campo) {
        JOptionPane.showMessageDialog(null, "Formato no válido\n");

        campo.setBackground(Color.RED);
    }
}
