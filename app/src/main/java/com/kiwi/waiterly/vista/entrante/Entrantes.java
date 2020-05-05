package com.kiwi.waiterly.vista.entrante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kiwi.waiterly.controladores.AdaptadorDatos;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.modelo.EntrantesList;
import com.kiwi.waiterly.vista.postre.Postres;
import com.kiwi.waiterly.vista.principal.Principales;

import java.util.ArrayList;

public class Entrantes extends AppCompatActivity {
    ArrayList<EntrantesList> listaEntrantes;
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
                Toast.makeText(getApplicationContext(),
                        "Seleccion: "+listaEntrantes.get(recyclerViewEntrantes.getChildAdapterPosition(view)).getTitulo(),
                        Toast.LENGTH_SHORT).show();

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

        listaEntrantes.add(new EntrantesList("Test","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test2","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test3","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test4","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test5","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test6","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test7","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test8","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test9","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test10","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test11","estan muy buenas",R.drawable.logo_waiterly));
        listaEntrantes.add(new EntrantesList("Test12","estan muy buenas",R.drawable.logo_waiterly));

    }
}
