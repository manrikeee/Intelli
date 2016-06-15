package com.example.mk.proyectofinal.Adapters;

/**
 * Created by Mk on 13/06/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mk.proyectofinal.Fragments.ProductosFragment;
import com.example.mk.proyectofinal.Modelo.Pedido_Producto;
import com.example.mk.proyectofinal.Modelo.Productos;
import com.example.mk.proyectofinal.R;

import java.util.List;

public class CuentaAdapter extends RecyclerView.Adapter<CuentaAdapter.ItemViewHolder> {
    List<Pedido_Producto> pedidos;
    static double precio_total;


    public CuentaAdapter(List<Pedido_Producto> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public CuentaAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recticket, viewGroup, false);
        ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;

    }

    @Override
    public void onBindViewHolder(CuentaAdapter.ItemViewHolder holder, int position) {
        holder.nombre.setText(pedidos.get(position).getNombre());
        double precio=dimePrecio(pedidos.get(position).getId_producto())*pedidos.get(position).getCantidad();
        holder.precio.setText(String.valueOf(precio));
        holder.unidades.setText(String.valueOf(pedidos.get(position).getCantidad()));
        //CuentaFragment.calcularPrecio(precio);



    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, precio, unidades;

        ImageView reloj;

        ItemViewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.recproducto);
            precio = (TextView) itemView.findViewById(R.id.recprecio);
            unidades = (TextView) itemView.findViewById(R.id.recuds);


        }
    }

    public static double dimePrecio(int id) {
        for (Productos producto : ProductosFragment.productos) {
            if (producto.getId() == id) {
                return producto.getPrecio();
            }
        }
        return 0;
    }
}
