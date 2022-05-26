package com.example.demo.dto;

public class KorpaDto {
    private String nazivArtikla;
    private long kolicina;
    private String nazivRestorana;

    public KorpaDto(String nazivArtikla, long kolicina, String nazivRestorana) {
        this.nazivArtikla = nazivArtikla;
        this.kolicina = kolicina;
        this.nazivRestorana = nazivRestorana;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public long getKolicina() {
        return kolicina;
    }

    public void setKolicina(long kolicina) {
        this.kolicina = kolicina;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }
}
