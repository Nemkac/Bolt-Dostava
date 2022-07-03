package com.example.demo.dto;

import com.example.demo.entity.TipArtikla;

public class ArtikalDto {
    private String naziv;
    private Long cena;
    private double kolicina;
    private String opis;
    public enum Tip{JELO, PICE}
    private TipArtikla tip;

    public ArtikalDto(String naziv, Long cena, double kolicina, String opis, TipArtikla tip) {
        this.naziv = naziv;
        this.cena = cena;
        this.kolicina = kolicina;
        this.opis = opis;
        this.tip = tip;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getCena() {
        return cena;
    }

    public void setCena(Long cena) {
        this.cena = cena;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipArtikla getTip() {
        return tip;
    }

    public void setTip(TipArtikla tip) {
        this.tip = tip;
    }
}
