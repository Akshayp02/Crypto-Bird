package com.example.cryptobird.models;

public class Modelitem {
    String symbbol, name, change, marketcap, volume;
    String price;

    public Modelitem(String symbbol, String name, String price, String change, String marketcap, String volume) {
        this.symbbol = symbbol;
        this.name = name;
        this.price = price;
        this.change = change;
        this.marketcap = marketcap;
        this.volume = volume;
    }

    public Modelitem(String symbbol, String name, String price) {
        this.symbbol = symbbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbbol() {
        return symbbol;
    }

    public void setSymbbol(String symbbol) {
        this.symbbol = symbbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String  getPrice() {
        return price;
    }

    public void setPrice( String price) {
        this.price = price;
    }
}
