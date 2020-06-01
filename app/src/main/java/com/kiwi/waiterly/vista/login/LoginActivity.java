package com.kiwi.waiterly.vista.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.controladores.WaiterlyManager;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private WaiterlyManager waiterlyManager = WaiterlyManager.getInstance();
    private TextView usuarioText;
    private TextView usuarioPass;
    private Button buttonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioText = findViewById(R.id.textUsuario);
        usuarioPass = findViewById(R.id.textPassword);
        buttonlogin = findViewById(R.id.buttonLogin);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearToken();

            }
        });



    }

    public void crearToken(){
        Log.d("test","entro login ");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.waiterly.tech/auth/login";

        StringRequest request = new StringRequest ( Request.Method. POST , url,
                new Response.Listener <String> () {
                    @Override public void onResponse (String response) {

                        Log.d("response", response);
                        waiterlyManager.crearToken(response);
                        finish();

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

                params.put ( "username", usuarioText.toString());
                params.put ( "password", usuarioPass.toString());

                return params;
            }
        };
        queue.add(request);

    }
}
