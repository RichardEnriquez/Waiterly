package com.kiwi.waiterly.vista.postre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.vista.entrante.Entrantes;
import com.kiwi.waiterly.vista.principal.Principales;

public class Postres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postres);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //seteamos entrante seleccionado por defecto
        bottomNavigationView.setSelectedItemId(R.id.postre);

        //funcion a

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
}
