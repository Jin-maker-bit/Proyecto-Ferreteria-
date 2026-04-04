/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JTable;

/**
 * Clase de utilidades generales de la aplicación Ferretería JP Fusión.
 * Centraliza la lógica de validación, diseño visual corporativo y gestión de recursos multimedia.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class Utilidades {
    
    
    
    
                // **** Gestión de Comprobaciones - Validación y Color Corporativo **** //
    
    
    
    // Definimos el color de fondo original para que no se ponga blanco sino con los colores corporativos
    private static final Color FONDO_ORIGINAL = new Color(9, 48, 64);
    

    /**
     * Verifica si un campo de texto está vacío o contiene solo espacios.
     * Si tiene texto y es válido, restaura el color de fondo al original corporativo.
     * 
     * @param campo El JTextField a validar.
     * @return true si el campo está vacío, false si contiene texto.
     */
    public static boolean compruebaCampoVacio(JTextField campo) {
        if (!campo.getText().trim().isEmpty()) {
            campo.setBackground(FONDO_ORIGINAL);
            return false;
        }
        return true;
    }

    /**
     * Lanza un mensaje de error y resalta el campo vacío.
     * Cambia el fondo del campo a color Magenta para resaltar el error visualmente.
     * 
     * @param campo El campo obligatorio no rellenado.
     */
    public static void lanzaAlertaVacio(JTextField campo) {
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " es obligatorio");
        campo.setBackground(Color.magenta);
    }
    
    
    /**
     * Verifica JComboBox. 
     * Verifica si un JComboBox tiene una selección válida (índice > 0).
     * Si tiene indice seleccionado restaura el color de fondo al original corporativo.
     * 
     * @param combo El JComboBox a validar.
     * @return true si no hay selección válida.
     */
    public static boolean compruebaComboNoSeleccionado(JComboBox combo) {
        // Si el índice es > 0, es que ha seleccionado algo correcto
        if (combo.getSelectedIndex() > 0) {
            combo.setBackground(FONDO_ORIGINAL); // Volvemos al FondoOriginal
            return false; // No está "no seleccionado", está bien
        }
        return true; // Sigue en el índice 0 o -1 (error)
    }

    
    /**
     * Lanza alerta indicando que no se ha seleccionado un elemento del combo.
     * Cambia el fondo del campo a color Magenta para resaltar el error visualmente.
     * 
     * @param combo El componente JComboBox.
     */
    public static void lanzaAlertaCombo(JComboBox combo) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento desplegable en: " + combo.getName());
        combo.setBackground(Color.magenta); // Marcamos el error
    }
    
   
    /**
     * Valida que el texto introducido sea un número entero positivo.
     * Si tiene texto y es válido, restaura el color de fondo al original corporativo.
     * 
     * @param campo JTextField con el dato numérico.
     * @return true si es un entero válido >= 0.
     */
    public static boolean compruebaEntero(JTextField campo) {
        try {
            int valor = Integer.parseInt(campo.getText().trim());
            if (valor >= 0) {
                campo.setBackground(FONDO_ORIGINAL);
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Informa al usuario mediante una alerta de que el número introducido debe ser un número entero positivo y resalta el error.
     * Resalta el campo en color magenta para facilitar la identificación visual del error en el formulario.
     * @param campo Campo con el error.
     */
    public static void lanzaAlertaNumeroNoValido(JTextField campo) {
        JOptionPane.showMessageDialog(null, "El campo " + campo.getName() + " debe ser un número entero positivo.");
        campo.setBackground(Color.magenta);
    }
    
    
    /**
     * Valida números Decimales, aceptando comas y convirtiéndolas a puntos.
     * Si tiene texto y es válido, restaura el color de fondo al original corporativo.
     * 
     * @param campo JTextField con el precio o dato decimal.
     * @return true si es un decimal válido >= 0.
     */
    public static boolean compruebaDouble(JTextField campo) {
        try {
            // Sustituimos la coma por un punto antes de intentar convertirlo
            String NumeritoConPunto = campo.getText().trim().replace(',', '.');
            double valor = Double.parseDouble(NumeritoConPunto);
            
            if (valor >= 0) {
                campo.setBackground(FONDO_ORIGINAL);
                return true;
            }
            return false;
            
        } catch (NumberFormatException e) {
            
            return false;
        }
    }
    
    /**
     * Muestra una alerta al usuario mediante un mensaje de que el valor introducido no es un número decimal válido o es negativo.  
     * Cambia el fondo del campo a color Magenta para resaltar el error visualmente.
     *
     * @param campo El JTextField con el valor incorrecto.
     */
    public static void lanzaAlertaDoubleNoValido(JTextField campo) {
        JOptionPane.showMessageDialog(null,
                "El campo " + campo.getName() + " debe ser un número válido decimal positivo");
        campo.setBackground(Color.magenta);
    }
    

    /**
     * Formatea un número double 2 decimales usando el punto como separador
     * Útil para mostrar precios en tablas y etiquetas de forma profesional.
     * Usa 'Locale.US' para obligar al sistema a usar siempre el punto (.) aunque el usuario utilice la coma (,).
     * 
     * @param valor El número decimal. (2.498998)
     * @return String formateado, es decir, el número ya formateado (2.50)
     */
    public static String formatoDosDecimales(double valor) {
        // Usamos String.format para redondear y fijar 2 decimales
        // Usamos Locale.US para asegurar que el separador sea SIEMPRE un punto (.)
        return String.format(java.util.Locale.US, "%.2f", valor); 
        // (%) Inicio (.) Decimales (2) El número que vamos a fijar (f) Float Denota que es Double decimal
    }
    
    
    
        
    
                            // **** Gestión de Diseño Visual **** //
    
    
    /**
     * Configura el icono de la ventana proporcionada, sirve tanto para JFramecomo para JDialog. 
     * Aplicamos Polimorfismo al recibir un objeto de tipo 'Window', esto nos permite que el método sea universal y acepte tanto JFrames como JDialogs.
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
     * Aplica el estilo visual corporativo de Ferretería JP a los componentes JTable.
     * Centraliza la configuración de colores (Dorado/Oscuro), impide el movimiento de columnas y unifica el diseño de cabeceras para mantener una interfaz profesional y coherente en toda la aplicación.
     * @param tabla JTable a personalizar.
     */
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
        
    
    
    

                    // **** Gestión de Diálogos e interacción con el Usuario **** //  
    
    
    /**
     * Método centralizado que muestra un diálogo de confirmación antes de cerrar la aplicación y cierra el sistema.     
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
     * Muestra un mensaje diálogo informativo con los créditos del proyecto. 
     * Se ha diseñado utilizando un parámetro genérico para aprovechar el polimorfismo. Reutilizable desde cualquier ventana de la aplicación.
     *
     * @param parent Componente desde el que se lanza.
     */
    public static void mostrarAcercaDe(java.awt.Component parent) {
        javax.swing.JOptionPane.showMessageDialog(
                parent,
                "Ferretería JP Fusión"
                + "\nVersión 1.0"
                + "\nDesarrollado por Jose & Patricia"
                + "\nAño 2026",
                "Acerca de", // No le ponemos \n para que salga el titulo en el cuadrito 
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }

    
    
    
                        // ***** Gestion de Recursos Multimedia ***** //
         
    /**
     * Se declaran estos métodos en la raíz de Utilidades para facilitar la edición futura y el mantenimiento de las funciones sonoras de Ferretería JP.
     */
    private static Clip clipActual;
    // Añadimos esta línea para guardar la canción que está sonando
    // Variable para controlar el audio

    /**
     * Reproduce un archivo WAV del paquete audio directamente desde Utilidades.
     * Uso: Utilidades.reproducir("archivo.wav");
     *
     * @param nombreArchivoWav Nombre del archivo - Single.
     */
    public static void reproducir(String nombreArchivoWav) {

        try {
            // Si ya hay algo sonando, lo paramos primero
            parar();

            // Buscamos el archivo dentro del paquete audio
            InputStream is = Utilidades.class.getResourceAsStream("/audio/" + nombreArchivoWav);

            // Si el archivo no existe o hay error en la ruta, evitamos que pete
            if (is == null) {
                System.err.println("No se pudo encontrar el audio: " + nombreArchivoWav);
                return;
            }

            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);

            clipActual = AudioSystem.getClip();
            clipActual.open(ais);
            clipActual.start();

        } catch (Exception e) {

            System.err.println("Error al reproducir el audio: " + e.getMessage());
        }
    }

    /**
     * Método para detener la música de forma global.
     * Libera los recursos del sistema.
     * Centralizado para el control de silencio en la aplicación.
     */
    public static void parar() {

        if (clipActual != null && clipActual.isRunning()) {

            clipActual.stop();
            clipActual.close();
        }
    }
    
}

    
