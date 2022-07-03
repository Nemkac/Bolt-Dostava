package com.example.demo.dto;

import java.util.List;

public class PrikazKorpeDto {
    private List<PrikazArtikalaDto> artikli;
    private double ukupnaCena;

    public PrikazKorpeDto(){}

    public PrikazKorpeDto(List<PrikazArtikalaDto> artikli, double ukupnaCena){
        this.artikli = artikli;
        this.ukupnaCena = ukupnaCena;
    }

    public List<PrikazArtikalaDto> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<PrikazArtikalaDto> artikli) {
        this.artikli = artikli;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
}
