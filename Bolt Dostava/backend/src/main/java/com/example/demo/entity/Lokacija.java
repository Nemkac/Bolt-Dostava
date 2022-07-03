package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long geoDuzina;
    @Column
    private long geoSirina;
    @Column
    private String adresa;

    public Lokacija(){}

    public Lokacija(long geoDuzina, long geoSirina, String adresa) {
        this.geoDuzina = geoDuzina;
        this.geoSirina = geoSirina;
        this.adresa = adresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Lokacija{" +
                "id=" + id +
                ", geoDuzina=" + geoDuzina +
                ", geoSirina=" + geoSirina +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
