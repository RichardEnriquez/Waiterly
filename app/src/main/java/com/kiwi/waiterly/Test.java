package com.kiwi.waiterly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kiwi.waiterly.vista.entrante.Entrantes;
import com.kiwi.waiterly.vista.postre.Postres;
import com.kiwi.waiterly.vista.principal.Principales;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //seteamos entrante seleccionado por defecto
        bottomNavigationView.setSelectedItemId(R.id.entrante);

        //funcion a

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.entrante:
                        startActivity(new Intent(getApplicationContext(), Entrantes.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.principal:
                        startActivity(new Intent(getApplicationContext(), Principales.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.postre:
                        startActivity(new Intent(getApplicationContext(), Postres.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }
}
