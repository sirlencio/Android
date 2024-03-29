package com.example.spotifly;

import java.io.Serializable;

public class Cancion implements Serializable {
    String ruta;
    String titulo;
    String duracion;
    String artista;

    public Cancion(String ruta, String titulo, String duracion, String artista) {
        this.ruta = ruta;
        this.titulo = titulo;
        this.duracion = duracion;
        this.artista = artista;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

}
