package com.example.a71.httpapplication.modules;

import java.io.Serializable;

/**
 * Created by Research_Development on 18/07/2017.
 */
public class place implements Serializable {
    boolean active;
    double lon;
    double lat;
    int id;
    String city;
    String celphone;
    String phone;
    String country;
    String address;
    String point;
    String scheduler;
    String name;

    public place(boolean active, double lon, double lat, int id, String city, String celphone, String phone, String country, String address, String point, String scheduler, String name) {
        this.active = active;
        this.lon = lon;
        this.lat = lat;
        this.id = id;
        this.city = city;
        this.celphone = celphone;
        this.phone = phone;
        this.country = country;
        this.address = address;
        this.point = point;
        this.scheduler = scheduler;
        this.name = name;
    }
    public place() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCelphone() {
        return celphone;
    }

    public void setCelphone(String celphone) {
        this.celphone = celphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getScheduler() {
        return scheduler;
    }

    public void setScheduler(String scheduler) {
        this.scheduler = scheduler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
