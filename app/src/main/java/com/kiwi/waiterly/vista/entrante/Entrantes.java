package com.kiwi.waiterly.vista.entrante;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.kiwi.waiterly.DetallePlatoSeleccionado;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.controladores.AdaptadorDatos;
import com.kiwi.waiterly.controladores.WaiterlyManager;
import com.kiwi.waiterly.modelo.Data;
import com.kiwi.waiterly.modelo.EntranteListParse;
import com.kiwi.waiterly.modelo.EntrantesList;
import com.kiwi.waiterly.modelo.Plato;
import com.kiwi.waiterly.vista.MainActivity;
import com.kiwi.waiterly.vista.carrito.CarritoActivity;
import com.kiwi.waiterly.vista.postre.Postres;
import com.kiwi.waiterly.vista.principal.Principales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Entrantes extends AppCompatActivity {
    WaiterlyManager waiterlyManager = WaiterlyManager.getInstance();
    ArrayList<Plato> listaEntrantes = waiterlyManager.getEntrantes();
    RecyclerView recyclerViewEntrantes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrantes);

        recyclerViewEntrantes = findViewById(R.id.recyclerViewEntrantes);
        recyclerViewEntrantes.setLayoutManager(new LinearLayoutManager(this/*,RecyclerView.VERTICAL,false*/));

        AdaptadorDatos adaptadorDatos = new AdaptadorDatos(listaEntrantes);
        recyclerViewEntrantes.setAdapter(adaptadorDatos);

        adaptadorDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Toast.makeText(getApplicationContext(),
                        "Seleccion: "+listaEntrantes.get(recyclerViewEntrantes.getChildAdapterPosition(view)).getTitulo(),
                        Toast.LENGTH_SHORT).show();*/

                //pasaremos los datos del plato seleccionad
                Plato objeto = listaEntrantes.get(recyclerViewEntrantes.getChildAdapterPosition(view));

                Intent intent = new Intent(Entrantes.this, DetallePlatoSeleccionado.class);
                intent.putExtra("objeto", objeto);
                startActivity(intent);
            }
        });


        //BOTTOM
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //seteamos entrante seleccionado por defecto
        bottomNavigationView.setSelectedItemId(R.id.entrante);
        //escuchamos por si tocan el bottonnavigation para cambiar de pantalla
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.entrante:
                        startActivity(new Intent(getApplicationContext(), Entrantes.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.principal:
                        startActivity(new Intent(getApplicationContext(), Principales.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.postre:
                        startActivity(new Intent(getApplicationContext(), Postres.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.carrito:
                        startActivity(new Intent(getApplicationContext(), CarritoActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }

}
