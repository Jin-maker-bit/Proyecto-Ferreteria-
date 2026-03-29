/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.awt.BasicStroke;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.AbstractBorder;

/**
 * Clase de utilidades generales de la aplicación Ferretería JP Fusión.
 * Proporciona métodos reutilizables para el diseño y comportamiento de los
 * componentes gráficos de la interfaz.
 *
 * @author Jose y Patricia
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
        campo.setBackground(Color.magenta);
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
        campo.setBackground(Color.magenta);
    }

    static String patronDNI = "^[0-9]{8}[A-Za-z]$";

    public static boolean compruebaDNI(JTextField campo) {
        return campo.getText().matches(patronDNI);
    }

    public static void lanzaAlertaFormatoDNI(JTextField campo) {
        JOptionPane.showMessageDialog(null, "El formato DNI es invalido");
        campo.setBackground(Color.magenta);
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
        campo.setBackground(Color.magenta);

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
        campo.setBackground(Color.magenta);
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

        campo.setBackground(Color.magenta);

    }

    /**
     * Comprueba si el contenido de un JTextField es un número decimal válido.
     *
     * @param campo El JTextField a comprobar.
     * @return true si es un double válido, false si no lo es.
     */
    public static boolean compruebaDouble(JTextField campo) {
        try {
            Double.parseDouble(campo.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Muestra alerta de número decimal no válido.
     *
     * @param campo El JTextField con el valor incorrecto.
     */
    public static void lanzaAlertaDoubleNoValido(JTextField campo) {
        JOptionPane.showMessageDialog(null,
                "El campo " + campo.getName() + " debe ser un número decimal válido");
        campo.setBackground(Color.magenta);
    }

    public class AplicarBorde {

        public static void aplicarBordeOvalado(JLabel label, int arco) {
            // 1. IMPORTANTE: Quitar la opacidad estándar para que no dibuje el cuadrado
            label.setOpaque(false);

            label.setBorder(new AbstractBorder() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    Graphics2D g2 = (Graphics2D) g.create();

                    // Suavizado para que no se vea "pixelado"
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // --- EL SECRETO SENIOR ---
                    // En lugar de un fondo sólido que tape el texto, 
                    // pintamos el fondo redondeado AQUÍ, pero el JLabel 
                    // ya se encargará de pintar su texto después por su cuenta.
                    // 1. Pintar el fondo con el color que elegiste en NetBeans
                    g2.setColor(c.getBackground());
                    g2.fillRoundRect(x, y, width - 1, height - 1, arco, arco);

                    // 2. Pintar el contorno (puedes usar el color de texto o uno fijo)
                    g2.setColor(new Color(184, 154, 108)); // Dorado
                    g2.setStroke(new BasicStroke(1));
                    g2.drawRoundRect(x, y, width - 1, height - 1, arco, arco);

                    g2.dispose();
                }

                @Override
                public Insets getBorderInsets(Component c) {
                    // Reducimos los márgenes para que el texto tenga espacio
                    // Si pones 20 como antes, el texto se "sale" del label
                    return new Insets(4, 10, 4, 10);
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
     * Muestra un diálogo informativo con los datos de la aplicación corporativa
     * y técnica del sistema. Se ha diseñado utilizando un parámetro genérico
     * para aprovechar el polimorfismo. Reutilizable desde cualquier ventana de
     * la aplicación.
     *
     * @param parent Componente padre desde el que se lanza el diálogo.
     */
    public static void mostrarAcercaDe(java.awt.Component parent) {
        javax.swing.JOptionPane.showMessageDialog(
                parent,
                "Ferretería JP Fusión\n"
                + "Versión 1.0\n"
                + "Desarrollado por Jose & Patricia\n"
                + "Año 2026",
                "Acerca de",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static class ReproductorAudio {

        // Añadimos esta línea para guardar la canción que está sonando
        private static Clip clipActual;

        /**
         * Reproduce un archivo de audio del paquete audio.
         *
         * @param nombreArchivo Nombre Single
         */
        public static void reproducir(String nombreArchivo) {

            try {
                // Si ya hay algo sonando, lo paramos primero
                parar();

                // Buscamos el archivo dentro del paquete audio
                InputStream is = ReproductorAudio.class.getResourceAsStream("/audio/" + nombreArchivo);

                // Si el archivo no existe o hay error en la ruta, evitamos que pete
                if (is == null) {
                    return;
                }

                InputStream bufferedIn = new BufferedInputStream(is);
                AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);

                clipActual = AudioSystem.getClip();
                clipActual.open(ais);
                clipActual.start();

            } catch (Exception e) {

                System.err.println("Error: " + e.getMessage());
            }
        }

        /**
         * Método nuevo para detener la música
         */
        public static void parar() {

            if (clipActual != null && clipActual.isRunning()) {

                clipActual.stop();
                clipActual.close();
            }
        }
    }

    public static void disenoTablas(JTable tabla) {
        Color FONDO_OSCURO = new Color(9, 48, 64);
        Color FONDO_SELECCION = new Color(3, 32, 38);
        Color DORADO = new Color(191, 150, 99);
        tabla.setSelectionBackground(FONDO_SELECCION);
        tabla.setSelectionForeground(DORADO);

        // Configuración de la cabecera (Header)
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setBackground(FONDO_OSCURO);
        tabla.getTableHeader().setForeground(DORADO);
        tabla.setGridColor(FONDO_SELECCION);
    }
}
