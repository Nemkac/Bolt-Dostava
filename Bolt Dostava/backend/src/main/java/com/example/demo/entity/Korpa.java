package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Korpa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn (name = "artikal_id")
    private Artikal artikal;

    @OneToOne
    @JoinColumn (name = "restoran_id")
    private Restoran restoran;

    @Column
    private int kolicina;

    @Column
    private double cenaArtikla;

    @OneToOne
    @JoinColumn (name = "kupad_id")
    private Kupac kupac;

    @OneToOne
    @JoinColumn (name = "porudzbina_id")
    private Porudzbina porudzbina;

    public Korpa(){}

    public Korpa(Artikal artikal, int kolicina, double cenaArtikla, Kupac kupac, Restoran restoran){
        this.artikal = artikal;
        this.kolicina = kolicina;
        this.cenaArtikla = cenaArtikla;
        this.kupac = kupac;
        this.restoran = restoran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaArtikla() {
        return cenaArtikla;
    }

    public void setCenaArtikla(double cenaArtikla) {
        this.cenaArtikla = cenaArtikla;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Porudzbina getPorudzbine() {
        return porudzbina;
    }

    public void setPorudzbine(Porudzbina porudzbine) {
        this.porudzbina = porudzbine;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getNazivArtikla(){
        return artikal.getNaziv();
    }
}
