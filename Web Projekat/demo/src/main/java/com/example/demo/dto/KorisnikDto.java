package com.example.demo.dto;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Pol;

import java.util.Date;

public class KorisnikDto {

    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String prezime;

    private Pol pol;

    private Date datum;

    public KorisnikDto(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datum) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datum = new java.util.Date(System.currentTimeMillis());
    }

    public KorisnikDto(Korisnik k){
        this.korisnickoIme = k.getKorisnickoIme();
        this.lozinka = k.getLozinka();
        this.ime = k.getIme();
        this.prezime = k.getPrezime();
        this.pol = k.getPol();
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
}
