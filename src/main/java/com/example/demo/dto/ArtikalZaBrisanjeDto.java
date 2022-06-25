package com.example.demo.dto;

public class ArtikalZaBrisanjeDto {
    private String naziv;

    public ArtikalZaBrisanjeDto(String naziv) {
        this.naziv = naziv;
    }

    public ArtikalZaBrisanjeDto(){}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
