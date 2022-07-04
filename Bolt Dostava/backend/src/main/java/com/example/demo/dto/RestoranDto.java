package com.example.demo.dto;

import com.example.demo.entity.Lokacija;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;

public class RestoranDto {
    private String naziv;
    private String tip;
    private long geoDuzina;
    private long geoSirina;
    private String adresa;
    private String menadzer;
    private boolean status;

    public boolean isRadi() {
        return radi;
    }

    public void setRadi(boolean radi) {
        this.radi = radi;
    }

    private boolean radi;

    public RestoranDto(String naziv, String tip, long geoDuzina, long geoSirina, String adresa, boolean status) {
        this.naziv = naziv;
        this.tip = tip;
        this.geoDuzina = geoDuzina;
        this.geoSirina = geoSirina;
        this.adresa = adresa;
        this.status = status;
    }

    public RestoranDto(Restoran restoran) {
        this.naziv = restoran.getNaziv();
        this.tip = restoran.getTip();
        this.radi = restoran.isRadi();
    }

    public RestoranDto(){}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public long getGeoDuzina() {
        return geoDuzina;
    }

    public void setGeoDuzina(long geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public long getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(long geoSirina) {
        this.geoSirina = geoSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(String menadzer) {
        this.menadzer = menadzer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
