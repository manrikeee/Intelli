package com.example.mk.proyectofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
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
import android.widget.Toast;

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
    static Button notifCount;
    static int mNotifCount = 0;
    public static MenuItem carro;
    public static int id_pedido=1;
    View view;


    static MyDialogFragment editNameDialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         fm = this.getSupportFragmentManager();





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
        crearPedido();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            finish();
        } else {
            super.onBackPressed();
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        carro=  menu.findItem(R.id.action_settings);



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






        } else if (id == R.id.cambiar_mesa) {
            createDialog();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        if (fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
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


    public  void crearPedido(){


        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        CartaService servicio = retrofit.create(CartaService.class);
        Call<String> respuesta = servicio.setPedido(Login_QR.mesa);
        final String[] respuesta2 = new String[1];
        respuesta.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               Log.e("PEDIDO","CREADO");
                respuesta2[0] =response.body();
                id_pedido=Integer.parseInt(respuesta2[0]);
                setPreferences();


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("allEvents", "ERROR12 : " + t.getMessage());
            }
        });

    }
    public void setPreferences(){
        SharedPreferences.Editor editor = getSharedPreferences("estado", MODE_PRIVATE).edit();
        editor.putInt("id_pedido", id_pedido);

        editor.commit();
        Toast.makeText(MainActivity.this, "setPreferences", Toast.LENGTH_SHORT).show();
    }
    public  void getPreferences(){
        int name=0;
        SharedPreferences prefs = getSharedPreferences("estado", MODE_PRIVATE);
        String restoredText = prefs.getString("id_pedido", null);
        if (restoredText != null) {
            name = prefs.getInt("id_pedido", 0);//"No name defined" is the default value.
            //0 is the default value.
        }else {
            setPreferences();
        }
        Toast.makeText(MainActivity.this, "getPreferences: "+name, Toast.LENGTH_SHORT).show();
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
}

