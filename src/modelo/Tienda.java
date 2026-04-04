/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase modelo que representa una Tienda física de la ferretería.
 * Almacena los datos identificativos del establecimiento, su ubicación y el personal responsable asignado.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
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
     * 
     * @param denominacion Nombre identificativo de la tienda.
     * @param direccion Ubicación física del local.
     * @param responsable Nombre del encargado de la sede.
     */
    public Tienda(String denominacion, String direccion, String responsable) {
        this.denominacion = denominacion;
        this.direccion = direccion;
        this.responsable = responsable;
    }

    /**
     * 
     * @return El nombre identificativo de la tienda.
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * 
     * @param denominacion El nombre de la tienda a establecer.
     */
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    /**
     * 
     * @return La ubicación física o dirección del local.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * 
     * @param direccion La dirección a establecer.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * 
     * @return El nombre del responsable asignado a la tienda.
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * 
     * @param responsable El nombre del responsable a asignar.
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    
}
