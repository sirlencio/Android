package com.example.agendacontactos;

import java.io.Serializable;

public class Contacto implements Serializable {
    private String codigo, nombre, apellido, telefono, email, grupo;

    public Contacto(String codigo, String nombre, String apellido, String telefono, String email, String grupo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.grupo = grupo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String aString() {
        String s = "-Codigo: " + this.codigo + " -Nombre: " + this.nombre + " -Apellidos: " + this.apellido + "\n";
        s += "-Telefono: " + this.telefono + " -Email: " + this.email + " \n -Grupo: " + this.grupo + "\n";
        return s;
    }
}
