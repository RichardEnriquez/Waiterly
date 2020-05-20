package com.kiwi.waiterly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kiwi.waiterly.modelo.EntrantesList;
import com.squareup.picasso.Picasso;

public class DetallePlatoSeleccionado extends AppCompatActivity {
    private TextView tituloPlato;
    private TextView detallePlato;
    private ImageView imagenPlato;
    private Button butonCarrito;
    private EntrantesList plato;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_plato_seleccionado);

        tituloPlato = findViewById(R.id.textViewTituloPlato);
        detallePlato = findViewById(R.id.textViewDetallePlato);
        imagenPlato = findViewById(R.id.imageViewPlato);
        butonCarrito = findViewById(R.id.buttonAnadirCarrito);

        recueperarDatos();

        butonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Plato: "+plato.getTitulo()+" añadido ", Toast.LENGTH_SHORT).show();

            }
        });




    }
    //todo:FUNCIONES
    public void recueperarDatos(){
        plato = (EntrantesList) getIntent().getSerializableExtra("objeto");
        //Log.d("datos", "====> "+plato);


        tituloPlato.setText(plato.getTitulo());
        detallePlato.setText(plato.getDetalle());
        Picasso.get()
                .load(plato.getFoto())
                //.load("https://www.196flavors.com/wp-content/uploads/2018/12/pique-macho-2.jpg")
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.menu)
                .into(imagenPlato);
    }
}
