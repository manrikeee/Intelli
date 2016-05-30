package com.example.mk.proyectofinal;

/**
 * Created by Mk on 30/05/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    static List<Productos> carta;
    static List<String> tipos;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public TextView tipo;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            tipo= (TextView) v.findViewById(R.id.tipo);
            imagen = (ImageView) v.findViewById(R.id.imagen);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Productos> productos) {
        carta = productos;
        ObtenerTipos();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tipo.setText(tipos.get(position).toString());
        insertarImagenes(holder);

        // - get element from your dataset at this position
        // - replace the contents of the view with that element


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return carta.size();
    }
    public static void ObtenerTipos(){
        tipos=new ArrayList<>();

        for (Productos producto: carta){

                tipos.add(producto.getTipo());

        }
    }

    public void insertarImagenes(ViewHolder viewHolder) {
        if (viewHolder.tipo.getText().equals("Bebida")) {
            Context context = viewHolder.imagen.getContext();
            Picasso.with(context)
                    .load(R.drawable.jarra2)
                    .resize(250,300)



                    .into(viewHolder.imagen)
            ;

        }
        if (viewHolder.tipo.getText().equals("Carne")) {
            Context context = viewHolder.imagen.getContext();
            Picasso.with(context)
                    .load(R.drawable.solomillo)
                    .resize(350,400)




                    .into(viewHolder.imagen);

        }

        }

    }

