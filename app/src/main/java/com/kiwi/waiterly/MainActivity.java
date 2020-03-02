package com.kiwi.waiterly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kiwi.waiterly.modelo.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conectServer();
    }


    public void conectServer() {
        Log.d("test","entro");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://waiterly.tech/api/";

        StringRequest request = new StringRequest ( Request.Method. GET , url,
                new Response.Listener <String> () {
                    @Override public void onResponse (String response) {

                        //parseamos los datos de gson a objetos
                        Gson gson = new Gson();
                        Test test = gson.fromJson(response, Test.class);
                        Log.d("test", test.getMsg());


                    }
                }, new Response.ErrorListener () {
            @Override public void onErrorResponse (VolleyError error) {
                //errores de red
                Log.d("test", "error de red");

            }
        });
        queue.add(request);
    }
}


//valores msg