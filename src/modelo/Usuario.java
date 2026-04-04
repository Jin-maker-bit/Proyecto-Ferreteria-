/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 * Clase modelo que representa a un Usuario, empleado, del sistema.
 * Contiene la información personal, credenciales de acceso, rol dentro de la empresa y su estado actual (Activo/Bloqueado).
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class Usuario {
    private String nombreCompleto;
    private String usuario;
    private String pass;
    private String tipo;    
    private String estado;  
    private Date fechaAlta;
    private String tienda;

    /**
     * Constructor vacío por defecto.
     */
    public Usuario() {
    }

    /**
     * Constructor parametrizado con todos los atributos.
     * 
     * @param nombreCompleto Nombre y apellidos reales del empleado.
     * @param usuario Nombre de acceso al sistema.
     * @param pass Contraseña de acceso.
     * @param tipo Rol o nivel de permisos: Administrador o User.
     * @param estado Estado de habilitación de la cuenta: Activo o bloqueado.
     * @param fechaAlta Fecha de registro en el sistema. 
     * @param tienda Tienda física asignada al empleado.
     */
    public Usuario(String nombreCompleto, String usuario, String pass, String tipo, String estado, Date fechaAlta, String tienda) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.pass = pass;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.tienda = tienda;
    }

    /**
     * 
     * @return El nombre completo del usuario.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * 
     * @param nombreCompleto 
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * 
     * @return El alias o login de acceso.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * 
     * @param usuario 
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * 
     * @return La contraseña del usuario.
     */
    public String getPass() {
        return pass;
    }

    /**
     * 
     * @param pass 
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * 
     * @return El rol o tipo de usuario.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * 
     * @param tipo 
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * 
     * @return El estado actual de la cuenta.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * 
     * @param estado 
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * 
     * @return La fecha de alta en el sistema.
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * 
     * @param fechaAlta 
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * 
     * @return La denominación de la tienda asignada.
     */
    public String getTienda() {
        return tienda;
    }

    /**
     * 
     * @param tienda 
     */
    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    
}
