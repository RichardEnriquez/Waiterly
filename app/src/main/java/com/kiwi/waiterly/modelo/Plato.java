package com.kiwi.waiterly.modelo;

import java.io.Serializable;

public class Plato  implements Serializable {
    private String id;
    private String titulo;
    private String detalle;
    private int precio;
    private String foto;

    public Plato(String id, String titulo, String detalle, int precio, String foto) {
        this.id = id;
        this.titulo = titulo;
        this.detalle = detalle;
        this.precio = precio;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Plato{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", detalle='" + detalle + '\'' +
                ", precio=" + precio +
                ", foto='" + foto + '\'' +
                '}';
    }
}
