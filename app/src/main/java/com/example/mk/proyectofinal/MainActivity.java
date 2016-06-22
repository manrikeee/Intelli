package com.example.mk.proyectofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mk.proyectofinal.Fragments.CuentaFragment;
import com.example.mk.proyectofinal.Fragments.MyDialogFragment;
import com.example.mk.proyectofinal.Fragments.ProductosFragment;
import com.example.mk.proyectofinal.Modelo.RestClient;
import com.example.mk.proyectofinal.Services.CartaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
  public  static FragmentManager fm;
    public static Button notifCount,notifCount2;
    static int mNotifCount = 0;
    public static MenuItem carro;
    public static MenuItem carro2;
    public static int id_pedido;
    public static int estado=1;

    View view;


    static MyDialogFragment editNameDialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         fm = this.getSupportFragmentManager();

        Fragment fragment = new ProductosFragment();
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment1, fragment)
                .commit();





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "EEEEEEEEEE", Toast.LENGTH_SHORT).show();
            }
        });


      MenuItem item= navigationView.getMenu().findItem(R.id.realizar_pedido);
        MenuItemCompat.setActionView(item, R.layout.feed);
        //MenuItemCompat.setActionView(item, R.layout.feed_update_count);
        RelativeLayout layout = (RelativeLayout) MenuItemCompat.getActionView(item);
        notifCount2= (Button) layout.findViewById(R.id.notif_count);
        notifCount2.setText("1");
        carro2=item;

        carro2.setVisible(false);



        recibirPedido();
    }

    @Override
    public void onBackPressed() {


        createDialog2();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        MenuItemCompat.setActionView(item, R.layout.feed_update_count);
        RelativeLayout layout = (RelativeLayout) MenuItemCompat.getActionView(item);
        notifCount= (Button) layout.findViewById(R.id.notif_count);
        notifCount.setText("1");
        carro=item;

        carro.setVisible(false);
        notifCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });

       // MenuItem menuItem = menu.findItem(R.id.carro);
       // menuItem.setIcon(buildCounterDrawable(count, R.drawable.ic_menu_gallery));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        Log.e("PAPAPA",""+item.toString());

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            showdialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fragment = new ProductosFragment();
            fragmentTransaction = true;
            // Handle the camera action






        } else if (id == R.id.realizar_pedido) {
          showdialog();


        } else if (id == R.id.nav_share) {
            createDialog2();

        } else if (id == R.id.nav_send) {
            fragment = new CuentaFragment();
            fragmentTransaction = true;

        }
        if (fragmentTransaction) {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment1, fragment)
                    .commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    public static void showdialog(){


       editNameDialogFragment = new MyDialogFragment();





        editNameDialogFragment.show(fm, "fragment_edit_name");

    }


    public static void crearPedido() {


        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        CartaService servicio = retrofit.create(CartaService.class);
        Call<String> respuesta = servicio.setPedido(Login_QR.mesa);
        final String[] respuesta2 = new String[1];
        respuesta.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                estado=1;
                respuesta2[0] = response.body();
                id_pedido = Integer.parseInt(respuesta2[0]);
                Log.e("PEDIDO", "CREADO:" + respuesta2[0]);




            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("allEvents", "ERROR12 : " + t.getMessage());
            }
        });
    }
    public void recibirPedido(){


        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        CartaService servicio = retrofit.create(CartaService.class);
        Call<String> respuesta = servicio.getPedido(Login_QR.mesa);
        final String[] respuesta2 = new String[1];
        respuesta.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                respuesta2[0] =response.body();
                id_pedido=Integer.parseInt(respuesta2[0]);
                Log.e("PEDIDO","CREADO:"+respuesta2[0]);


                if(id_pedido==0){
                    crearPedido();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                crearPedido();
            }
        });

    }



    public void createDialog(){
        new AlertDialog.Builder(MainActivity.this)
                //.setTitle("Cambiar de mesa")
                .setMessage("¿Está seguro de que quiere cambiar de mesa?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                })

                .show();
    }
    public void createDialog2(){
        new AlertDialog.Builder(MainActivity.this)
                //.setTitle("Cambiar de mesa")
                .setMessage("¿Está seguro de que quiere salir ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })

                .show();
    }

}

