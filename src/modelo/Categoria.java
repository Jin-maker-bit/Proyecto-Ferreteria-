/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase modelo que representa una categoría de productos en el sistema.
 * Actúa como contenedor de datos para transportar la información entre  la base de datos y la interfaz gráfica.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class Categoria {
    private String categoria;
    private String descripcion;

    /**
     * Constructor vacío por defecto.
     */
    public Categoria() {
    }

    /**
     * Constructor parametrizado.
     * Crea un objeto Categoria inicializando todos sus atributos de una vez.
     * 
     * @param categoria Nombre o título de la categoría
     * @param descripcion Descripción detallada de lo que engloba esta categoría.
     */
    public Categoria(String categoria, String descripcion) {
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    /**
     * @return El nombre identificativo de la categoría.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria El nombre de la categoría a establecer.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return La descripción detallada de la categoría.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion La descripción a establecer para la categoría.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
