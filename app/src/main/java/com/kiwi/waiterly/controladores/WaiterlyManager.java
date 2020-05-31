package com.kiwi.waiterly.controladores;

import com.airbnb.lottie.LottieAnimationView;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.modelo.Plato;

import java.util.ArrayList;

public class WaiterlyManager {
    private static WaiterlyManager instancia = null;
    private ArrayList<Plato> platos;

    //Constructor
    private WaiterlyManager(){
        platos = new ArrayList<Plato>();
    }

    public static WaiterlyManager getInstance(){
        if (instancia == null){
            instancia = new WaiterlyManager();
        }
        return instancia;
    }

    //todo:FUNCIONES
    public void addPlato(Plato plato){
        platos.add(plato);
    }

    public ArrayList<Plato> getPlatos(){
        return platos;
    }

    public void cleanPlatos(){
        platos = new ArrayList<>();
    }
}
