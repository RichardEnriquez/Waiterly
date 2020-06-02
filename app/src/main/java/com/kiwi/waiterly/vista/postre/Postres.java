package com.kiwi.waiterly.vista.postre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kiwi.waiterly.DetallePlatoSeleccionado;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.controladores.AdaptadorDatos;
import com.kiwi.waiterly.controladores.WaiterlyManager;
import com.kiwi.waiterly.modelo.Plato;
import com.kiwi.waiterly.vista.MainActivity;
import com.kiwi.waiterly.vista.carrito.CarritoActivity;
import com.kiwi.waiterly.vista.entrante.Entrantes;
import com.kiwi.waiterly.vista.principal.Principales;

import java.util.ArrayList;

public class Postres extends AppCompatActivity {
    WaiterlyManager waiterlyManager = WaiterlyManager.getInstance();
    ArrayList<Plato> listaEntrantes = waiterlyManager.getPostres();
    RecyclerView recyclerViewPostres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postres);

        recyclerViewPostres = findViewById(R.id.recyclerViewPostres);
        recyclerViewPostres.setLayoutManager(new LinearLayoutManager(this/*,RecyclerView.VERTICAL,false*/));

        AdaptadorDatos adaptadorDatos = new AdaptadorDatos(listaEntrantes);
        recyclerViewPostres.setAdapter(adaptadorDatos);

        adaptadorDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Toast.makeText(getApplicationContext(),
                        "Seleccion: "+listaEntrantes.get(recyclerViewEntrantes.getChildAdapterPosition(view)).getTitulo(),
                        Toast.LENGTH_SHORT).show();*/

                //pasaremos los datos del plato seleccionad
                Plato objeto = listaEntrantes.get(recyclerViewPostres.getChildAdapterPosition(view));

                Intent intent = new Intent(Postres.this, DetallePlatoSeleccionado.class);
                intent.putExtra("objeto", objeto);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //seteamos entrante seleccionado por defecto
        bottomNavigationView.setSelectedItemId(R.id.postre);

        //funcion a

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
}
