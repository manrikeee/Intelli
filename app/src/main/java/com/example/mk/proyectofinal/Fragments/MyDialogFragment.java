package com.example.mk.proyectofinal.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk.proyectofinal.Adapters.CartaExpandAdapter;
import com.example.mk.proyectofinal.Adapters.TicketAdapter;
import com.example.mk.proyectofinal.Login_QR;
import com.example.mk.proyectofinal.MainActivity;
import com.example.mk.proyectofinal.Modelo.Pedido_Producto;
import com.example.mk.proyectofinal.Modelo.Productos;
import com.example.mk.proyectofinal.Modelo.RestClient;
import com.example.mk.proyectofinal.R;
import com.example.mk.proyectofinal.Services.CartaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mk on 13/06/2016.
 */
public  class MyDialogFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private TicketAdapter adapter;
    static TextView total;
    public TextView pedir;
    static ProgressDialog mProgressDialog;
    static int a=0;


    static MyDialogFragment newInstance() {
        MyDialogFragment f = new MyDialogFragment();
        return f;
    }
        // this method create view for your Dialog
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if( MainActivity.estado==0){
            MainActivity.crearPedido();
        }
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Realizando pedido...");
        //inflate layout with recycler view
       getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.reciclerticket, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.RecView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        total= (TextView) v.findViewById(R.id.ptotal);
        pedir=(TextView) v.findViewById(R.id.bpedir);
        pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crearPedido();
            }
        });
        //setadapter
         adapter = new TicketAdapter(CartaExpandAdapter.fromColumns,this);

        mRecyclerView.setAdapter(adapter);

        //get your recycler view and populate it.
        return v;
    }
    public static void Actualizartotal(){
        TicketAdapter.total=0;
        for (Productos producto: CartaExpandAdapter.productos_pedidos){
            Double precio=Double.parseDouble(String.valueOf(producto.getPrecio()));
            TicketAdapter.total= TicketAdapter.total+ (precio*producto.getCant());
        }
        total.setText(String.valueOf(TicketAdapter.total)+" €");
    }

    public void crearPedido() {

        for (Productos producto1 : CartaExpandAdapter.productos_pedidos) {
            Pedido_Producto pedido = new Pedido_Producto();
            pedido.setCantidad(producto1.getCant());
            pedido.setMesa(Login_QR.mesa);
            pedido.setNombre(producto1.getProducto());
           // Toast.makeText(getContext(), "NOMBRE PRODUCTO:"+pedido.getNombre(), Toast.LENGTH_SHORT).show();
            pedido.setId_producto(producto1.getId());
            Log.e("PEDIDOID",""+pedido.getId_producto());
            pedido.setId_pedido(MainActivity.id_pedido);
            hacerPedido(pedido);



        }
    }

        public void hacerPedido(Pedido_Producto pedido){

            mProgressDialog.show();


                RestClient restClient = new RestClient();
                Retrofit retrofit = restClient.getRetrofit();


                CartaService servicio = retrofit.create(CartaService.class);
                Call<String> respuesta = servicio.crearPedido(pedido.getId_producto(),pedido.getCantidad(),pedido.getMesa(),MainActivity.id_pedido,pedido.getNombre());
               final String respuesta2;
                respuesta.enqueue(new Callback<String>() {

                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
//                        Toast.makeText(getActivity(), "Pedido Realizado", Toast.LENGTH_SHORT).show();;

                        CartaExpandAdapter.productos_pedidos.clear();
                        MainActivity.carro.setVisible(false);
                        CartaExpandAdapter.productos_totales=0;
                        MainActivity.notifCount.setText(String.valueOf(CartaExpandAdapter.productos_totales));




                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        a=1;
                        mProgressDialog.dismiss();
                        dismiss();
                        Toast.makeText(getContext(), "Problemas de conexión, compruebe productos pedidos en ver ticket.", Toast.LENGTH_SHORT).show();
                    }


                });
            if (a==0) {
                Toast.makeText(getContext(), "Pedido realizado satisfactoriamente.", Toast.LENGTH_SHORT).show();

            }else{ Toast.makeText(getContext(), "Problemas de conexión, compruebe productos pedidos en ver ticket.", Toast.LENGTH_SHORT).show();

            }mProgressDialog.dismiss();
            dismiss();

            //dismiss();

            }
}


