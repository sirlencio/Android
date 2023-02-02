package com.example.spotifly;

import android.os.Parcel;
import android.os.Parcelable;

public class usuario implements Parcelable {
    public int id;
    public String nombre, fechanac, video;
    public byte [] imagen;

    public usuario(String nombre, byte [] imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public usuario(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        fechanac = in.readString();
        imagen = in.createByteArray();
        video = in.readString();
        if(in.dataAvail() > 0) // is there data left to read?
            id = in.readInt();
    }

    public static final Creator<usuario> CREATOR = new Creator<usuario>() {
        @Override
        public usuario createFromParcel(Parcel in) {
            return new usuario(in);
        }

        @Override
        public usuario[] newArray(int size) {
            return new usuario[size];
        }
    };



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechanac='" + fechanac + '\'' +
                ", imagen='" + imagen + '\'' +
                ", video='" + video + '\'' +
                '}';
    }

    //Ignora esto
    @Override
    public int describeContents() {
        return 0;
    }

    //write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeString(nombre);
        out.writeString(fechanac);
        out.writeByteArray(imagen);
    }
}
