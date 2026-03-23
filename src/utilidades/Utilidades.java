/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;

/**
 * Clase de utilidades generales de la aplicación Ferretería JP Fusión.
 * Proporciona métodos reutilizables para el diseño y comportamiento de los
 * componentes gráficos de la interfaz.
 *
 * @author jintae
 */
// ACTUALMENTE EN CONSTRUCCIÓN - SE IRÁN AÑADIENDO MÉTODOS CONFORME AVANCE EL DESARROLLO
// DEL PROYECTO 
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

    public class AplicarBorde {

        public static void aplicarEstiloUniversal(JComponent componente, int arco) {
            // 1. Quitamos la opacidad estándar para que no dibuje el rectángulo gris
            componente.setOpaque(false);

            // 2. Si es un botón, limpiamos los estilos de fábrica
            if (componente instanceof AbstractButton) {
                AbstractButton b = (AbstractButton) componente;
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                b.setFocusPainted(false);
            }

            // 3. Aplicamos el dibujo mediante un Border personalizado
            componente.setBorder(new AbstractBorder() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // IMPORTANTE: Dibujamos el fondo redondeado
                    // Usamos el color que hayas puesto en la pestaña "Properties" del IDE
                    g2.setColor(c.getBackground());
                    g2.fillRoundRect(x, y, width - 1, height - 1, arco, arco);

                    // Dibujamos el borde (Color dorado de tu diseño)
                    g2.setColor(new Color(184, 154, 108));
                    g2.drawRoundRect(x, y, width - 1, height - 1, arco, arco);

                    g2.dispose();
                }

                @Override
                public Insets getBorderInsets(Component c) {
                    // Margen interno para que el texto no toque los bordes (Arriba, Izquierda, Abajo, Derecha)
                    return new Insets(8, 25, 8, 25);
                }
            });
        }
    }

    /**
     * Configura el icono de la ventana proporcionada, sirve tanto para JFrame
     * como para JDialog. Aplicamos POLIMORFISMO al recibir un objeto de tipo
     * 'Window', esto nos permite que el método sea universal y acepte tanto
     * JFrames como JDialogs.
     *
     * @param ventana La ventana, Frame o Dialog, a la que se le asigna el logo.
     */
    public static void establecerIcono(java.awt.Window ventana) {

        try {
            // Buscamos la imagen en el paquete imágenes
            java.net.URL url = ventana.getClass().getResource("/imagenes/LogoEmpresa.jpg");

            if (url != null) {

                java.awt.Image icono = new javax.swing.ImageIcon(url).getImage();

                // Comprobamos si es un Frame o un Dialog para aplicar el icono
                if (ventana instanceof javax.swing.JFrame) {

                    ((javax.swing.JFrame) ventana).setIconImage(icono);

                } else if (ventana instanceof javax.swing.JDialog) {
                    // En JDialog el método se llama igual pero a veces requiere casting
                    ((javax.swing.JDialog) ventana).setIconImage(icono);
                }

            } else {

                System.err.println("Error: Logo no encontrado en /imagenes/LogoEmpresa.jpg");
            }
        } catch (Exception e) {

            System.err.println("Error al cargar icono: " + e.getMessage());
        }
    }

    /**
     * Método centralizado que muestra un diálogo de confirmación antes de
     * cerrar la aplicación. Cumple con el requerimiento RI2 - comunicación con
     * el usuario.
     *
     * @param ventana La ventana desde la que se invoca el cierre.
     */
    public static void salirAplicacion(java.awt.Window ventana) {
        int confirmar = javax.swing.JOptionPane.showConfirmDialog(
                ventana,
                "¿Desea salir de la aplicación Ferretería JP?",
                "Confirmar Salida",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (confirmar == javax.swing.JOptionPane.YES_OPTION) {

            System.exit(0);
        }
    }
    
    
    /**
     * Muestra un diálogo informativo con los datos de la aplicación corporativa y técnica del sistema.
     * Se ha diseñado utilizando un parámetro genérico para aprovechar el polimorfismo.
     * Reutilizable desde cualquier ventana de la aplicación.
     * @param parent Componente padre desde el que se lanza el diálogo.
     */
    public static void mostrarAcercaDe(java.awt.Component parent) {
        javax.swing.JOptionPane.showMessageDialog(
            parent,
            "Ferretería JP Fusión\n" +
            "Versión 1.0\n" +
            "Desarrollado por Jose & Patricia\n" +
            "Año 2026",
            "Acerca de",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }

}
