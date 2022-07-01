package com.example.demo.dto;

public class DodelaRestoranaDto {
    String korisnickoIme;
    String naziv;

    public DodelaRestoranaDto(){}

    public DodelaRestoranaDto(String korisnickoIme, String naziv) {
        this.korisnickoIme = korisnickoIme;
        this.naziv = naziv;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
