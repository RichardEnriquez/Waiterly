package com.kiwi.waiterly.vista.entrante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kiwi.waiterly.DetallePlatoSeleccionado;
import com.kiwi.waiterly.controladores.AdaptadorDatos;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.modelo.EntrantesList;
import com.kiwi.waiterly.modelo.Plato;
import com.kiwi.waiterly.vista.postre.Postres;
import com.kiwi.waiterly.vista.principal.Principales;

import java.util.ArrayList;

public class Entrantes extends AppCompatActivity {
    ArrayList<Plato> listaEntrantes;
    RecyclerView recyclerViewEntrantes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrantes);

        listaEntrantes = new ArrayList<>();
        recyclerViewEntrantes = findViewById(R.id.recyclerViewEntrantes);
        recyclerViewEntrantes.setLayoutManager(new LinearLayoutManager(this/*,RecyclerView.VERTICAL,false*/));
        llenarEntrantes();

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




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //seteamos entrante seleccionado por defecto
        bottomNavigationView.setSelectedItemId(R.id.entrante);
        //escuchamos por si tocan el bottonnavigation para cambiar de pantalla
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
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
                }
                return false;
            }
        });
    }

    private void llenarEntrantes(){

        listaEntrantes.add(new EntrantesList("Test1","estan muy buenas1",20,"https://sevilla.abc.es/gurme/wp-content/uploads/sites/24/2012/01/comida-rapida-casera.jpg"));
        listaEntrantes.add(new EntrantesList("Test2","estan muy buenas2", 22,"https://tecnohotelnews.com/wp-content/uploads/2018/04/siete-claves-para-ofrecer-platos-saludables-atractivos-a-los-comensales.jpg"));
        listaEntrantes.add(new EntrantesList("Test3","estan muy buenas3",15,"https://es.food-of-dream.com/content/11/11894/7d9d3f174a7387a98cbe5ff60c30cc70.jpg"));
        listaEntrantes.add(new EntrantesList("Test4","estan muy buenas4",8,"https://www.clara.es/medio/2018/12/18/recetas-comidas-saludables_5102de8e_600x900.jpg"));

    }
}
