package com.example.pkdata;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String name;
    public String lat;
    public String lng;
    public String com;

    public User() { }
    public User(String name, String lat,String lng, String com) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.com = com;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public void setLng(String lng) {
        this.lat = lng;
    }
    public void setCom(String com) {
        this.com = com;
    }


    public String getName() { return name; }
    public String getLat() {
        return lat;
    }
    public String getLng() {
        return lng;
    }
    public String getCom() {
        return com;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", com=" + com +
                '}';
    }
}