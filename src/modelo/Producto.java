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

    public Producto() {
    }

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

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestacado() {
        return destacado;
    }

    public void setDestacado(String destacado) {
        this.destacado = destacado;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "===== DETALLE DE ARTICULO =====\n\n"
                + "CODIGO:         " + codProducto + "\n"
                + "NOMBRE:         " + nombre + "\n"
                + "CATEGORÍA:      " + categoria + "\n"
                + "ORIGEN:         " + origen + "\n"
                + "DESTACADO:      " + destacado + "\n"
                + "OFERTA:         " + oferta + "\n"
                + "-------------------------------\n"
                + " \n"
                + "PRECIO COMPRA:  " + precioCompra + " €\n"
                + "PRECIO PVP:     " + precioVenta + " €\n"
                + "STOCK:          " + stock + " unidades\n"
                + "-------------------------------\n"
                + "DESCRIPCIÓN:\n" + descripcion + "\n\n"
                + "FECHA ALTA:     " + fechaAlta;
    }

}
