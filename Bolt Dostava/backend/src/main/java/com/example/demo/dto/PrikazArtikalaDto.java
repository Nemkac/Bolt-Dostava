package com.example.demo.dto;

import com.example.demo.entity.Korpa;

public class PrikazArtikalaDto {
    private String nazivArtikla;
    private String nazivRestorana;
    private int kolicina;
    private double cena;

    public PrikazArtikalaDto(){}

    public PrikazArtikalaDto(Korpa korpa){
        this.nazivArtikla = korpa.getNazivArtikla();
        this.nazivRestorana = korpa.getRestoran().getNaziv();
        this.kolicina = korpa.getKolicina();
        this.cena = korpa.getCenaArtikla();
        this.nazivArtikla = korpa.getRestoran().getNaziv();
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }
}
