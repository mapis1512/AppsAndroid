package com.example.a71.listaplication.models;

import java.io.Serializable;

/**
 * Created by 71 on 7/1/2017.
 */

public class Persona implements Serializable {
    public String nombre;
    public String apellido;
    public int edad;

    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

}
