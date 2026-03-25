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
public class ResponsableTienda {
    private String respTienda;
    private Date fechaAlta;

    public ResponsableTienda() {
    }

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
