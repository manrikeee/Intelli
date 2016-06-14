package com.example.mk.proyectofinal;

/**
 * Created by Mk on 02/06/2016.
 */
public class Pedido {
    int id;
    int mesa;
    int precio_total;
    int estado;

    public Pedido(int id, int mesa, int precio_total, int estado) {
        this.id = id;
        this.mesa = mesa;
        this.precio_total = precio_total;
        this.estado = estado;
    }

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(int precio_total) {
        this.precio_total = precio_total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
