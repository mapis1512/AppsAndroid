package com.example.a71.intentaplication.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 71 on 7/9/2017.
 */

public class Person implements Serializable{
    private String nombre;
    private String web;
    private String telefono;

    public Person(String nombre, String web, String telefono) {
        this.nombre = nombre;
        this.web = web;
        this.telefono = telefono;
    }

    public Person() {
    }

    public  static ArrayList<Person> getDataSet(){
        ArrayList<Person> arrayList=new ArrayList<Person>();
        arrayList.add(new Person("Google","www.google.com","123456789"));
        arrayList.add(new Person("Hotmail","www.Hotmail.com","987654321"));
        arrayList.add(new Person("Facebook","www.facebook.com","456789123"));
        arrayList.add(new Person("Twitter","www.twitter.com","321478965"));
        return arrayList;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
