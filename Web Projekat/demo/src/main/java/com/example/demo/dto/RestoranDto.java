package com.example.demo.dto;

import com.example.demo.entity.Lokacija;

public class RestoranDto {
    private String naziv;
    private String tip;
    private Lokacija lokacija;
    private String usernameMenadzera;
    // treba dodati listu porudzbina



    public RestoranDto(String naziv, String tip, Lokacija lokacija) {
        this.naziv = naziv;
        this.tip = tip;
        this.lokacija = lokacija;
    }

    public RestoranDto(restoran r) {
        naziv = r.getNaziv();
        tip = r.getTip;
        lokacija = r.getLokacija;

    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public void setUsernameMenadzera(String usernameMenadzera){
        this.usernameMenadzera = usernameMenadzera;
    }

    public String getUsernameMenadzera(){
        return usernameMenadzera;
    }
}
