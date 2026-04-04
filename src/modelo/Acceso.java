/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 * Clase modelo que representa un registro de auditoría o acceso al sistema.
 * Contiene los datos de quién, cuándo y desde dónde se ha iniciado sesión.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class Acceso {
    private String usuario;
    private Date fecha;
    private String ip;

    /**
     *
     * Constructor vacío por defecto. 
     * Permite crear un objeto Acceso sin inicializar sus atributos de inmediato.
     */
    public Acceso() {
    }

    /**
     * Constructor parametrizado.
     * Crea un objeto Acceso inicializando todos sus atributos en el momento de la creación.
     * 
     * @param usuario Nombre del usuario que ha iniciado sesión.
     * @param fecha Fecha y hora exacta del acceso.
     * @param ip Dirección IP desde la que se realizó la conexión.
     */
    public Acceso(String usuario, Date fecha, String ip) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.ip = ip;
    }

    /**
     * @return El nombre del usuario asociado al acceso.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario El nombre del usuario a establecer.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return La fecha y hora del inicio de sesión.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha La fecha y hora a establecer.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return La dirección IP del equipo cliente.
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip La dirección IP a establecer.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    
}
