package com.example.demo.dto;

import com.example.demo.entity.Lokacija;

public class pretragaPoLokacijiDto {
    private Lokacija lokacija;

    public pretragaPoLokacijiDto(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
