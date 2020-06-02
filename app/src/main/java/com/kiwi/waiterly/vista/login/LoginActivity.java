package com.kiwi.waiterly.vista.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.controladores.WaiterlyManager;
import com.kiwi.waiterly.vista.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private WaiterlyManager waiterlyManager = WaiterlyManager.getInstance();
    private TextView usuarioText;
    private TextView usuarioPass;
    private Button buttonlogin;
    private LottieAnimationView lottieOctopus;
    private LottieAnimationView lottieError;
    private LottieAnimationView lottieErrorServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        lottieOctopus = findViewById(R.id.animacionLogin);
        lottieOctopus.playAnimation();
        lottieError = findViewById(R.id.animacionError);
        lottieErrorServer = findViewById(R.id.animacionErrorServer);
        lottieErrorServer.setVisibility(View.INVISIBLE);

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

                        Log.d("TOKEN =======: ", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String token = (String) json.get("token");
                            waiterlyManager.crearToken(token);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener () {
            @Override public void onErrorResponse (VolleyError error) {
                //errores de red
                Log.d("test", " Error de red = "+error.getMessage());
                lottieError.playAnimation();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                String user = usuarioText.getText().toString();
                String pass = usuarioPass.getText().toString();
                params.put ( "username", user);
                params.put ( "password", pass);
                return params;
            }
        };
        queue.add(request);

    }
}
