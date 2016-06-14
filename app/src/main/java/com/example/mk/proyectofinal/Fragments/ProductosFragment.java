package com.example.mk.proyectofinal.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.mk.proyectofinal.Adapters.ExpandList;
import com.example.mk.proyectofinal.Modelo.Productos;
import com.example.mk.proyectofinal.Modelo.RestClient;
import com.example.mk.proyectofinal.R;
import com.example.mk.proyectofinal.Services.CartaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static RecyclerView card;
    static RecyclerView.Adapter myadaptador;
    public static List<Productos> productos;
    public static List<String> tipos;
    View view;
    ExpandableListView elv;
    HashMap <String,List<Productos>> productosHash = new HashMap<String,List<Productos>>();

    private OnFragmentInteractionListener mListener;

    public ProductosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductosFragment newInstance(String param1, String param2) {
        ProductosFragment fragment = new ProductosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_productos, container, false);
//
//
        productos = new ArrayList<>();
//
//
//        card = (RecyclerView) view.getRootView().findViewById(R.id.reciclador);
//        LinearLayoutManager layout = new LinearLayoutManager(getContext());
//        layout.setOrientation(LinearLayoutManager.VERTICAL);
//        card.setLayoutManager(layout);
       elv  = (ExpandableListView) view.findViewById(R.id.lvExp);
        getCarta();

//        getCarta();
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

    public void getCarta(){

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        CartaService servicio = retrofit.create(CartaService.class);
        Call<List<Productos>> respuesta = servicio.getCarta();
        productos = new ArrayList<>();
        respuesta.enqueue(new Callback<List<Productos>>() {

            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                productos = response.body();

                //myadaptador = new CartaAdapter(productos,0);
                ProductosFragment.ObtenerTipos();
                productosHash=getData2();
                ExpandList adapter2=new ExpandList(getContext(),ProductosFragment.tipos,productosHash);
                elv.setAdapter(adapter2);
                //card.setAdapter(myadaptador);
            }

            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {
                Log.i("allEvents", "ERROR12 : " + t.getMessage());
            }
        });


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


        public HashMap<String,List<Productos>> getData2() {
            HashMap<String, List<Productos>> carta = new HashMap<String, List<Productos>>();
            List<Productos> productoss ;
            for (Productos i : productos) {

                if (carta.containsKey(i.getTipo())) {
                    productoss = carta.get(i.getTipo());
                } else {
                   productoss = new ArrayList<Productos>();
                }
                productoss.add(i);
                carta.put(i.getTipo(), productoss);
            }


        System.out.println("HASH : " +carta.toString());

                return carta;
            }
    public static void ObtenerTipos(){
        tipos=new ArrayList<>();


        for (Productos producto: ProductosFragment.productos){
            if (!tipos.contains(producto)) {

                tipos.add(producto.getTipo());
            }

        }
    }

    }

