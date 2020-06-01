package com.kiwi.waiterly.controladores;

import com.airbnb.lottie.LottieAnimationView;
import com.kiwi.waiterly.R;
import com.kiwi.waiterly.modelo.Plato;

import java.util.ArrayList;

public class WaiterlyManager {
    private static WaiterlyManager instancia = null;

    private String TOKEN_RESSTAURANTE;
    private ArrayList<Plato> carrito;
    private ArrayList<Plato> platosPedidos;



    //Constructor
    private WaiterlyManager(){
        carrito = new ArrayList<Plato>();
        platosPedidos = new ArrayList<Plato>();
    }

    public static WaiterlyManager getInstance(){
        if (instancia == null){
            instancia = new WaiterlyManager();
        }
        return instancia;
    }

    public String getTOKEN() {
        return TOKEN_RESSTAURANTE;
    }
    //todo:FUNCIONES
    public void crearToken(String token){
        TOKEN_RESSTAURANTE = token;
    }
    public void addPlatoCarrito(Plato plato){
        carrito.add(plato);
    }

    public ArrayList<Plato> getPlatosCarrito(){
        return carrito;
    }

    public ArrayList<Plato> getPlatosPedidos(){
        return platosPedidos;
    }

    public void addPlatoPedido(Plato plato){
        platosPedidos.add(plato);
    }

    public void cleanCarrito(){
        carrito = new ArrayList<>();
    }
}
