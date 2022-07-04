package com.example.demo.dto;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import com.example.demo.entity.Status;

import java.util.*;

public class PorudzbinaDto {
    Set<Artikal> poruceniArtikli = new HashSet<>();
    Restoran restoran;
    Date datum;
    double cena;
    Status status;
    String korisnickoIme;
    Long id;

    public PorudzbinaDto(Set<Artikal> poruceniArtikli, Restoran restoran, Date datumIVreme, double cena, Status status, String korisnickoIme) {
        this.poruceniArtikli = poruceniArtikli;
        this.restoran = restoran;
        this.datum = datumIVreme;
        this.cena = cena;
        this.status = status;
        this.korisnickoIme = korisnickoIme;
    }

    public PorudzbinaDto(Porudzbina porudzbina){
        this.poruceniArtikli = porudzbina.getPoruceniArtikli();
        this.restoran = porudzbina.getRestoran();
        this.datum = porudzbina.getDatum();
        this.cena = porudzbina.getCena();
        this.status = porudzbina.getStatus();
        this.korisnickoIme = porudzbina.getKorisnickoIme();
        this.id = porudzbina.getId();
    }

    public Set<Artikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Date getDatumIVreme() {
        return datum;
    }

    public void setDatumIVreme(Date datum) {
        this.datum = datum;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
}
