package com.example.mk.proyectofinal;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Mk on 13/06/2016.
 */
public  class MyDialogFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private HorasAdapter adapter;
    static TextView total;

    static MyDialogFragment newInstance() {
        MyDialogFragment f = new MyDialogFragment();
        return f;
    }
        // this method create view for your Dialog
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate layout with recycler view
       getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.reciclerticket, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.RecView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        total= (TextView) v.findViewById(R.id.ptotal);
        //setadapter
         adapter = new HorasAdapter(ExpandableListAdapter.fromColumns,this);

        mRecyclerView.setAdapter(adapter);

        //get your recycler view and populate it.
        return v;
    }
    public static void Actualizartotal(){
        HorasAdapter.total=0;
        for (Productos producto: ExpandableListAdapter.productos_pedidos){
            Double precio=Double.parseDouble(String.valueOf(producto.getPrecio()));
            HorasAdapter.total=HorasAdapter.total+ precio;
        }
        total.setText(String.valueOf(HorasAdapter.total)+" €");
    }

}