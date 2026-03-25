/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author jintae
 */
public class Usuario {
    private String nombreCompleto;
    private String usuario;
    private String pass;
    private String tipo;    
    private String estado;  
    private Date fechaAlta;
    private String tienda;

    public Usuario() {
    }

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
