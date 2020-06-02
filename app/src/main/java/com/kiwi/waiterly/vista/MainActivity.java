package com.kiwi.waiterly.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.controladores.WaiterlyManager;
import com.kiwi.waiterly.modelo.Data;
import com.kiwi.waiterly.modelo.EntrantesList;
import com.kiwi.waiterly.modelo.Plato;
import com.kiwi.waiterly.modelo.PostreList;
import com.kiwi.waiterly.modelo.PrincipalList;
import com.kiwi.waiterly.vista.carrito.CarritoActivity;
import com.kiwi.waiterly.vista.entrante.Entrantes;
import com.kiwi.waiterly.vista.login.LoginActivity;
import com.kiwi.waiterly.vista.mapa.MapsActivity;
import com.kiwi.waiterly.vista.postre.Postres;
import com.kiwi.waiterly.vista.principal.Principales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    WaiterlyManager waiterlyManager = WaiterlyManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creamos condicion verificando si hay token para hacer el login o no
        if (waiterlyManager.getTOKEN() == null) {
            //indicando que empiece la aplicacion por el login
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        //recuperamos los platos
        if (waiterlyManager.getEntrantes().isEmpty()){
            llenarEntrantes();
        }


        //Animacion de coccion
        TextView texto = findViewById(R.id.textViewPedidoSolicitado);
        LottieAnimationView lottieFood = (LottieAnimationView) findViewById(R.id.lottieAnimationFood);
        if (waiterlyManager.getPlatosPedidos().isEmpty()){
            texto.setVisibility(View.INVISIBLE);
            lottieFood.setVisibility(View.INVISIBLE);
        }else{
            texto.setVisibility(View.VISIBLE);
            lottieFood.setVisibility(View.VISIBLE);
            lottieFood.playAnimation();
        }

        //Boton para funcion de mapas
        Button buttonMaps = findViewById(R.id.buttonMaps);
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        //funcion de navigaton BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //seteamos entrante seleccionado por defecto
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
//                    case R.id.home:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0,0);
//                        finish();
//                        return true;

                    case R.id.entrante:
                        startActivity(new Intent(getApplicationContext(), Entrantes.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.principal:
                        startActivity(new Intent(getApplicationContext(), Principales.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.postre:
                        startActivity(new Intent(getApplicationContext(), Postres.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.carrito:
                        startActivity(new Intent(getApplicationContext(), CarritoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



    }

    private void llenarEntrantes(){
        //final ArrayList<Plato> entrantes = waiterlyManager.getEntrantes();

            //listaEntrantes.add(new EntrantesList("2","Test1","estan muy buenas1",20,"https://sevilla.abc.es/gurme/wp-content/uploads/sites/24/2012/01/comida-rapida-casera.jpg"));
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://api.waiterly.tech/plate";
            StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Log.d("responseeeeeee", response);
                            //parseamos los datos de gson a objetos
                            Gson gson = new Gson();
                            Data[] platos = gson.fromJson(response, Data[].class);
                            Log.d("tamaño de respuesta", "===========" + platos.length);

                            ArrayList<Plato> entrantes = waiterlyManager.getEntrantes();
                            ArrayList<Plato> principales = waiterlyManager.getPrincipales();
                            ArrayList<Plato> postres = waiterlyManager.getPostres();

                            for (Data plato : platos) {
                                String id = plato.get_id();
                                String titulo = plato.getName();
                                String detalle = plato.getDescription();
                                int precio = (int) plato.getPrice();
                                String foto = plato.getImage();
                                Log.d("Link", foto);
                                switch (plato.getType().toLowerCase()){
                                    case "entrande":
                                            EntrantesList entrante = new EntrantesList(id, titulo, detalle, precio, foto);
                                            entrantes.add(entrante);
                                        break;
                                    case "principal":

                                            PrincipalList princpal = new PrincipalList(id, titulo, detalle, precio, foto);
                                            principales.add(princpal);
                                        break;
                                    case "postre":
                                            PostreList postre = new PostreList(id, titulo, detalle, precio, foto);
                                            postres.add(postre);
                                        break;
                                    default:
                                        Log.d("Tipo de plato", "Plato desconocido: "+plato.getType());
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //errores de red
                    Log.d("test", "Error " + error.getMessage());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    Log.d("token", waiterlyManager.getTOKEN());
                    headers.put("Authorization", "Bearer " + waiterlyManager.getTOKEN());
                    return headers;
                }
            };
            queue.add(request);
            //Log.d("platos recuperados", "platos : " + entrantes.size());

    }







    /**
     * funcion que recibe una respuesta tipo get de la api
     */
/*    public void conectServer() {
        Log.d("test","entro");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.waiterly.tech";


        StringRequest request = new StringRequest ( Request.Method. GET , url,
                new Response.Listener <String> () {
                    @Override public void onResponse (String response) {

                        //parseamos los datos de gson a objetos
                        Gson gson = new Gson();
                        Test test = gson.fromJson(response, Test.class);
                        Log.d("test", test.getMsg());

                        TextView textView = findViewById(R.id.textView);
                        textView.setText(test.getMsg());

                    }
                }, new Response.ErrorListener () {
            @Override public void onErrorResponse (VolleyError error) {
                //errores de red
                Log.d("test", error.getMessage());

            }
        });
        queue.add(request);
    }*/
}
