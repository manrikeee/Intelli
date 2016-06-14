package com.example.mk.proyectofinal;

/**
 * Created by Mk on 02/06/2016.
 */
public class Pedido_Producto {
    int id;
    int id_producto;
    int cantidad;
    int mesa;
    int estado;
    int id_pedido;

    public Pedido_Producto(int id, int id_producto, int cantidad, int mesa, int estado, int id_pedido) {
        this.id = id;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.mesa = mesa;
        this.estado = estado;
        this.id_pedido = id_pedido;

    }

    public Pedido_Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
}
