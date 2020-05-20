package com.kiwi.waiterly.controladores;

public class WaiterlyManager {
    private static WaiterlyManager instancia = null;
    //private Array
    //private Has

    //Constructor
    private WaiterlyManager(){
        //damos valor al array de platos para no repetir
    }

    public static WaiterlyManager getInstance(){
        if (instancia == null){
            instancia = new WaiterlyManager();
        }
        return instancia;
    }

    //todo:FUNCIONES
}
