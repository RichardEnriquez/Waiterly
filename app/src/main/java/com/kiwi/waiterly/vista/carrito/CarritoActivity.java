package com.kiwi.waiterly.vista.carrito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.controladores.WaiterlyManager;
import com.kiwi.waiterly.modelo.Plato;
import com.kiwi.waiterly.vista.MainActivity;
import com.kiwi.waiterly.vista.entrante.Entrantes;
import com.kiwi.waiterly.vista.postre.Postres;
import com.kiwi.waiterly.vista.principal.Principales;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity {
    WaiterlyManager waiterlyManager = WaiterlyManager.getInstance();

    ArrayList<Plato> listaPlatos = new ArrayList<>();
    RecyclerView recyclerViewCarrito;
    private Button buttonCarritoFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        buttonCarritoFinalizar = findViewById(R.id.buttonCarritoFinalizar);
        buttonCarritoFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaPlatos.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Aun no hay platos",
                            Toast.LENGTH_SHORT).show();
                }else {
                    waiterlyManager.cleanPlatos();
                    Toast.makeText(getApplicationContext(),
                            "Pedido Realizado",
                                Toast.LENGTH_SHORT).show();


                }
            }
        });

        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito);
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(this/*,RecyclerView.VERTICAL,false*/));

        listaPlatos = waiterlyManager.getPlatos();
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
                        return true;
                }
                return false;
            }
        });

    }
}
