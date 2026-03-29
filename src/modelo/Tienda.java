/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase modelo que representa una Tienda física de la ferretería.
 * Almacena los datos identificativos del establecimiento, su ubicación y el personal responsable asignado.
 * @author Jose y Patricia
 */
public class Tienda {
    private String denominacion;
    private String direccion;
    private String responsable;

    
    /**
     * Constructor vacío por defecto.
     * Facilita la creación de objetos Tienda para ser poblados posteriormente.
     */
    public Tienda() {
    }

    
    /**
     * Constructor parametrizado.
     * Crea un objeto Tienda con toda su información inicial básica.
     * @param denominacion Nombre identificativo de la tienda.
     * @param direccion Ubicación física del local.
     * @param responsable Nombre del encargado de la sede.
     */
    public Tienda(String denominacion, String direccion, String responsable) {
        this.denominacion = denominacion;
        this.direccion = direccion;
        this.responsable = responsable;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    
}
