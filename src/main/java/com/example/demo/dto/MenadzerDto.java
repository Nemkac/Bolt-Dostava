package com.example.demo.dto;

import com.example.demo.entity.Pol;
import com.example.demo.entity.Restoran;
import com.example.demo.entity.Uloga;

import java.util.Date;

public class MenadzerDto {
    String korisnickoIme;
    String lozinka;
    String ime;
    String prezime;
    Pol pol;
    Date datum;
    Uloga uloga;
    Restoran restoran;

    public MenadzerDto(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datum, Uloga uloga) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datum = datum;
        this.uloga = uloga;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public void setRestoran(Restoran restoran){
        this.restoran = restoran;
    }

    public Restoran getRestoran(){
        return restoran;
    }
}
