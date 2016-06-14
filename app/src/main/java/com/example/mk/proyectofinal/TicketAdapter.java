package com.example.mk.proyectofinal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mk on 13/06/2016.
 */
public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    List<String> productos;
    Context context;

    public TicketAdapter(List<String> productos) {
        this.productos = productos;
        Log.e("Productos",""+this.productos.toString());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.e("ProductoEEEEEEEEEs",""+this.productos.toString());
        holder.nombre.setText(productos.get(position));
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               productos.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nombre;
        public ImageButton del;


        public ViewHolder(View v) {
            super(v);

            nombre = (TextView) v.findViewById(R.id.text1);
            del = (ImageButton) v.findViewById(R.id.delete);



        }

    }
}



