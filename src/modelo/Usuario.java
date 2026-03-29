/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 * Clase modelo que representa a un Usuario, empleado, del sistema.
 * Contiene la información personal, credenciales de acceso, rol dentro de la empresa y su estado actual (Activo/Bloqueado).
 * @author Jose y Patricia
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
     * @param nombreCompleto Nombre y apellidos reales.
     * @param usuario Nombre de acceso al sistema.
     * @param pass Contraseña de acceso.
     * @param tipo Rol o nivel de permisos.
     * @param estado Estado de habilitación de la cuenta.
     * @param fechaAlta Fecha de registro.
     * @param tienda Tienda física asignada.
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    
}
