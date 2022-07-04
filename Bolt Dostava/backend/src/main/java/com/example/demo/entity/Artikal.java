package com.example.demo.entity;

import com.example.demo.dto.ArtikalDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;
    @Column
    private Long cena;
    @Column
    private double kolicina;
    @Column
    private String opis;
    @Column
    private TipArtikla tip;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "Restoran_id")
    private Restoran restoran;

    public Artikal(){}

    public Artikal(String naziv, Long cena, TipArtikla tip) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
    }

    public Artikal(String naziv, Long cena, TipArtikla tip, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.opis = opis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", kolicina=" + kolicina +
                ", opis='" + opis + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
