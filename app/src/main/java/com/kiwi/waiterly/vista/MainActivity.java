package com.kiwi.waiterly.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.controladores.WaiterlyManager;
import com.kiwi.waiterly.vista.carrito.CarritoActivity;
import com.kiwi.waiterly.vista.entrante.Entrantes;
import com.kiwi.waiterly.vista.mapa.MapsActivity;
import com.kiwi.waiterly.vista.postre.Postres;
import com.kiwi.waiterly.vista.principal.Principales;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    WaiterlyManager waiterlyManager = WaiterlyManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        //Animacion de coccion
        LottieAnimationView lottieFood = (LottieAnimationView) findViewById(R.id.lottieAnimationFood);
        if (waiterlyManager.getPlatosPedidos().isEmpty()){
            lottieFood.setVisibility(View.INVISIBLE);
        }else{
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
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

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

    /**
     * test para poder recibir un token del servidor pasando como parametros
     * nombre de usuario y password
     * retornara un token ("ahora mismo el token se cambia cada vez que se llama")
     * en desarrolo para que el token sea fijo
     */
    public void loginTest() {
        Log.d("test","entro");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.waiterly.tech/auth/login";


        StringRequest request = new StringRequest ( Request.Method. POST , url,
                new Response.Listener <String> () {
                    @Override public void onResponse (String response) {
                        Log.d("response", response);

                    }
                }, new Response.ErrorListener () {
            @Override public void onErrorResponse (VolleyError error) {
                //errores de red
                Log.d("test", error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();

                params.put ( "user", "admin");
                params.put ( "pass", "kiwi");

                return params;
            }
        };
        queue.add(request);
    }
}
