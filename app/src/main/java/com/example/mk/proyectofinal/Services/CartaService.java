package com.example.mk.proyectofinal.Services;

import com.example.mk.proyectofinal.Modelo.Pedido_Producto;
import com.example.mk.proyectofinal.Modelo.Productos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mk on 30/05/2016.
 */
public interface CartaService {
    @GET("/proyectofinal/obtener_carta.php")
    Call<List<Productos>> getCarta() ;

    @GET("/proyectofinal/hacer_pedido.php")
    Call<String> crearPedido(@Query("id_producto") int id_producto,@Query("cantidad") int cantidad,@Query("mesa") int mesa,@Query("id_pedido") int id_pedido,@Query("nombre") String nombre) ;

    @GET("/proyectofinal/crear_pedido.php")
    Call<String> setPedido(@Query("mesa") int mesa) ;
    @GET("/proyectofinal/crear_pedido.php")
    Call<String> getPedido(@Query("mesa") int mesa) ;

    @GET("/proyectofinal/obtener_cuenta.php")
    Call<List<Pedido_Producto>> getCuenta(@Query("id_pedido") int id_pedido) ;

    @GET("/proyectofinal/pedir_cuenta.php")
    Call<String> pedirCuenta() ;

}