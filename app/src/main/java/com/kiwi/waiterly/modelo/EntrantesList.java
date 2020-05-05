package com.kiwi.waiterly.modelo;

public class EntrantesList {
    private String titulo;
    private String detalle;
    private int foto;

    public EntrantesList(String titulo, String detalle, int foto) {
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

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
