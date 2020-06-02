package com.kiwi.waiterly.vista.carrito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

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
import com.kiwi.waiterly.vista.MainActivity;
import com.kiwi.waiterly.vista.entrante.Entrantes;
import com.kiwi.waiterly.vista.postre.Postres;
import com.kiwi.waiterly.vista.principal.Principales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CarritoActivity extends AppCompatActivity {
    WaiterlyManager waiterlyManager = WaiterlyManager.getInstance();

    ArrayList<Plato> listaPlatos = new ArrayList<>();
    RecyclerView recyclerViewCarrito;
    private LottieAnimationView lottiePago;
    private Button buttonCarritoFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        lottiePago = findViewById(R.id.animacionPagar);
        lottiePago.setVisibility(View.INVISIBLE);

        buttonCarritoFinalizar = findViewById(R.id.buttonCarritoFinalizar);
        buttonCarritoFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaPlatos.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Aun no hay platos",
                            Toast.LENGTH_SHORT).show();
                }else {

                    for (Plato x : waiterlyManager.getPlatosCarrito()){
                        Plato plato = new Plato(x.getId(),x.getTitulo(),x.getDetalle(),x.getPrecio(),x.getFoto());
                        waiterlyManager.addPlatoPedido(plato);
                    }
                    waiterlyManager.cleanCarrito();

                    //Madar datos al server
                    mandarDatosPedido();


                    //animacion
                    recyclerViewCarrito.setVisibility(View.INVISIBLE);
                    lottiePago.setVisibility(View.VISIBLE);
                    lottiePago.playAnimation();

                    Toast.makeText(getApplicationContext(),
                            "Pedido Realizado",
                                Toast.LENGTH_SHORT).show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            handler.postDelayed(this, 100);
                            Log.d("hand", "------"+lottiePago.getFrame() );
                            if (lottiePago.getFrame() == 89){
                                finish();
                            }
                        }
                    }, 0);
                }
            }
        });

        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito);
        recyclerViewCarrito.setVisibility(View.VISIBLE);
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(this/*,RecyclerView.VERTICAL,false*/));

        listaPlatos = waiterlyManager.getPlatosCarrito();
        //Log.d("platos", "=> "+listaPlatos);

        AdaptadorCarrito adaptadorCarrito = new AdaptadorCarrito(listaPlatos);
        recyclerViewCarrito.setAdapter(adaptadorCarrito);


        //BOTTOM
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //seteamos entrante seleccionado por defecto
        bottomNavigationView.setSelectedItemId(R.id.carrito);
        //escuchamos por si tocan el bottonnavigation para cambiar de pantalla
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
                        finish();
                        return true;
                }
                return false;
            }
        });

    }

    public void mandarDatosPedido(){
        ArrayList<Plato> platosPedidos =  waiterlyManager.getPlatosPedidos();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.waiterly.tech/plate";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("responseeeeeee", response);
                        //parseamos los datos de gson a objetos
//                        Gson gson = new Gson();
//                        Data[] platos = gson.fromJson(response, Data[].class);
//                        Log.d("tamaño de respuesta", "===========" + platos.length);


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
    }
}
