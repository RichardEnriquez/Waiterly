package com.kiwi.waiterly.modelo;

import java.io.Serializable;

public class EntrantesList implements Serializable {
    private String titulo;
    private String detalle;
    private String foto;

    public EntrantesList(String titulo, String detalle, String foto) {
        this.titulo = titulo;
        this.detalle = detalle;
        this.foto = foto;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "EntrantesList{" +
                "titulo='" + titulo + '\'' +
                ", detalle='" + detalle + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
