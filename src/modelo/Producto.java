/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 * Clase modelo que representa un Producto dentro del inventario de la ferretería.
 * Almacena toda la información técnica, comercial y de stock de los artículos.
 * 
 * @author Jose y Patricia
 * @version 1.0
 * @since 2026
 */
public class Producto {

    private String codProducto;
    private String nombre;
    private String categoria;
    private String descripcion;
    private double precioCompra;
    private double precioVenta;
    private int stock;
    private String origen;
    private String destacado;
    private String oferta;
    private Date fechaAlta;

    /**
     * Constructor vacío por defecto.
     */
    public Producto() {
    }

    /**
     * Constructor parametrizado con todos los atributos del producto.
     * 
     * @param codProducto Código único alfanumérico.
     * @param nombre Nombre comercial del artículo.
     * @param categoria Categoría a la que pertenece.
     * @param descripcion Detalle técnico del producto.
     * @param precioCompra Precio de adquisición al proveedor.
     * @param precioVenta Precio final de venta al público (PVP).
     * @param stock Cantidad de unidades disponibles en almacén.
     * @param origen Origen del producto (Nacional/Internacional).
     * @param destacado Indica si es un producto destacado (Sí/No).
     * @param oferta Indica si el producto tiene un precio especial (Sí/No).
     * @param fechaAlta Fecha en la que se registró el producto en el sistema.
     */
    public Producto(String codProducto, String nombre, String categoria, String descripcion, double precioCompra, double precioVenta, int stock, String origen, String destacado, String oferta, Date fechaAlta) {
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.origen = origen;
        this.destacado = destacado;
        this.oferta = oferta;
        this.fechaAlta = fechaAlta;
    }

    /**
     * 
     * @return El código único del producto.
     */
    public String getCodProducto() {
        return codProducto;
    }

    /**
     * 
     * @param codProducto 
     */
    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    /**
     * 
     * @return El nombre comercial del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return La categoría asociada.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * 
     * @param categoria 
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * 
     * @return La descripción técnica.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return El coste de compra para la ferretería.
     */
    public double getPrecioCompra() {
        return precioCompra;
    }

    /**
     * 
     * @param precioCompra 
     */
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    /**
     * 
     * @return El precio de venta al público.
     */
    public double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * 
     * @param precioVenta 
     */
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * 
     * @return Las unidades disponibles.
     */
    public int getStock() {
        return stock;
    }

    /**
     * 
     * @param stock 
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * 
     * @return El origen del producto.
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * 
     * @param origen 
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * 
     * @return SI, si es destacado; NO en caso contrario.
     */
    public String getDestacado() {
        return destacado;
    }

    /**
     * 
     * @param destacado 
     */
    public void setDestacado(String destacado) {
        this.destacado = destacado;
    }

    /**
     * 
     * @return SI, si está en oferta; NO en caso contrario.
     */
    public String getOferta() {
        return oferta;
    }

    /**
     * 
     * @param oferta 
     */
    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    /**
     * 
     * @return La fecha de registro en el sistema.
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
     * Sobrescribe el método toString para generar una ficha detallada del producto.
     * Útil para mostrar información en consolas, logs o áreas de texto de la interfaz.
     * 
     * @return String formateado con la ficha técnica y comercial del artículo.
     */
    @Override
    public String toString() {
        
        return "•==•==•==• DETALLES DE ARTICULO •==•==•==•\n"
                + "\n • CODIGO:         " + codProducto 
                + "\n • NOMBRE:         " + nombre 
                + "\n • CATEGORÍA:      " + categoria 
                + "\n • ORIGEN:         " + origen 
                + "\n • DESTACADO:      " + destacado 
                + "\n • OFERTA:         " + oferta  
                + "\n================================\n"
                + "\n • PRECIO COMPRA:  " + precioCompra + " € "
                + "\n • PRECIO PVP:     " + precioVenta + " € "
                + "\n • STOCK:          " + stock + " unidades "
                + "\n================================\n"
                + "\n • DESCRIPCIÓN:    " + descripcion 
                + "\n • FECHA ALTA:     " + fechaAlta
                + "\n================================\n";
    }

}
