package com.example.mk.proyectofinal.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk.proyectofinal.Adapters.CuentaAdapter;
import com.example.mk.proyectofinal.MainActivity;
import com.example.mk.proyectofinal.Modelo.Pedido_Producto;
import com.example.mk.proyectofinal.Modelo.RestClient;
import com.example.mk.proyectofinal.R;
import com.example.mk.proyectofinal.Services.CartaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CuentaFragment extends Fragment {
    static RecyclerView mRecyclerView;
    private CuentaAdapter adapter;
    static List<Pedido_Producto> productos;
    public static ProgressDialog mProgressDialog;
    LinearLayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;
    public static double total;
    public static TextView totalview;
    public static FloatingActionButton pedircuenta;
    public static int estado=0;

    public CuentaFragment()
    {


        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuentaFragment newInstance(String param1, String param2) {
        CuentaFragment fragment = new CuentaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_cuenta, container, false);
        mRecyclerView = (RecyclerView) view.getRootView().findViewById(R.id.RecView);
        totalview= (TextView) view.findViewById(R.id.total_cuenta);
        pedircuenta= (FloatingActionButton) view.findViewById(R.id.pedircuenta);
        LinearLayoutManager llmanager = new LinearLayoutManager(getActivity());
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llmanager);
        total=0;
        ObtenerCuenta();
        pedircuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedirCuenta();

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void ObtenerCuenta() {

            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Obteniendo cuenta...");
            mProgressDialog.show();
       // mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.RecView2);

            RestClient restClient = new RestClient();
            Retrofit retrofit = restClient.getRetrofit();


            CartaService servicio = retrofit.create(CartaService.class);
            Call<List<Pedido_Producto>> respuesta = servicio.getCuenta(MainActivity.id_pedido);
            productos = new ArrayList<>();

            respuesta.enqueue(new Callback<List<Pedido_Producto>>() {


                @Override
                public void onResponse(Call<List<Pedido_Producto>> call, Response<List<Pedido_Producto>> response) {
                productos=response.body();
                    mProgressDialog.dismiss();
                    calcularPrecio(productos);
                    adapter= new CuentaAdapter(productos);

                    mRecyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Pedido_Producto>> call, Throwable t) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getContext(), "No tiene cuenta pendiente: "+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });
        }
    public static void calcularPrecio(List<Pedido_Producto>productos){

        for (Pedido_Producto pedido: productos){
          double precio=CuentaAdapter.dimePrecio(pedido.getId_producto())* pedido.getCantidad();
            total=total+precio;
        }
        totalview.setText(String.valueOf(total));


    }
    public void pedirCuenta(){
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Pidiendo cuenta...");
        mProgressDialog.show();
        // mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.RecView2);

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        CartaService servicio = retrofit.create(CartaService.class);
        Call<String> respuesta = servicio.pedirCuenta();
        productos = new ArrayList<>();

        respuesta.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mProgressDialog.dismiss();
                Toast.makeText(getContext(), "Cuenta pedida", Toast.LENGTH_SHORT).show();
                productos.clear();
                adapter= new CuentaAdapter(productos);

                mRecyclerView.setAdapter(adapter);
                totalview.setText("");
               // adapter.notifyDataSetChanged();
            }



            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(getContext(), "Error pedir cuenta", Toast.LENGTH_SHORT).show();
            }

            });
        }
    }

