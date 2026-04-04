/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase modelo que representa el origen de un producto (Nacional o Importación).
 * Sirve como contenedor de datos para transferir la información de los orígenes entre la base de datos y la interfaz de usuario.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class Origen {
    private String origen;
    private String descripcion;

    /**
     * Constructor vacío por defecto.
     * Permite instanciar un objeto de tipo Origen sin datos iniciales.
     */
    public Origen() {
    }

    /**
     * Constructor parametrizado.
     * Crea un objeto Origen inicializando todos sus atributos de una sola vez.
     * 
     * @param origen El nombre del origen.
     * @param descripcion La descripción detallada del origen.
     */
    public Origen(String origen, String descripcion) {
        this.origen = origen;
        this.descripcion = descripcion;
    }

    /**
     * @return El nombre identificativo del origen.
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen El nombre del origen a establecer.
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return La descripción asociada a este tipo de origen.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion La descripción a establecer para el origen.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
