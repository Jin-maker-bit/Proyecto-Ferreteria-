/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 * Clase modelo que representa a un Responsable de Tienda.
 * Se utiliza para gestionar el personal encargado de las diferentes sedes de la ferretería y su antigüedad en el sistema.
 * @author Jose y Patricia
 */
public class ResponsableTienda {
    private String respTienda;
    private Date fechaAlta;

    /**
     * Constructor vacío por defecto.
     * Permite instanciar un responsable y asignar sus valores posteriormente.
     */
    public ResponsableTienda() {
    }

    /**
     * Constructor parametrizado.
     * Inicializa un objeto ResponsableTienda con su nombre y fecha de registro.
     * @param respTienda Nombre completo del responsable.
     * @param fechaAlta Fecha de alta en el sistema.
     */
    public ResponsableTienda(String respTienda, Date fechaAlta) {
        this.respTienda = respTienda;
        this.fechaAlta = fechaAlta;
    }

    public String getRespTienda() {
        return respTienda;
    }

    public void setRespTienda(String respTienda) {
        this.respTienda = respTienda;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
}
