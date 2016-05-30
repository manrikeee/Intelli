package com.example.mk.proyectofinal;

/**
 * Created by Mk on 30/05/2016.
 */
public class Productos {
    int id;
    String producto;
    double precio;
    int estado;
    String tipo;

    public Productos(int id, String producto, double precio, int estado,String tipo) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.estado = estado;
        this.tipo=tipo;
    }

    public Productos() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
