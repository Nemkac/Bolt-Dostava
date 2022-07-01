package com.example.demo.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Entity
public class Porudzbina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String UUID;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    //Vise porudzbina moze biti iz istog restorana
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Restoran restoran;

    //Date
    @Column
    private Date datum;
    @Column
    private double cena;

    //Vise porudzbina moze da bude naruceno od jednog kupac
    @ManyToOne
    @JoinColumn(name = "kupac")
    private Kupac kupac;

    @Column
    private Status status;

    @Column
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<ArtikliUPorudzbini> jednaPorudzbina = new HashSet<>();

    @ManyToOne
    private Dostavljac dostavljac;

    public Porudzbina(){}

    public Porudzbina(String UUID, Restoran restoran, Kupac kupac, Status status, Date datum) {
        this.UUID = UUID;
        this.restoran = restoran;
        this.kupac = kupac;
        this.status = status;
        this.datum = datum;
        this.jednaPorudzbina = new HashSet<>();
    }


    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getKorisnickoIme(){
        return kupac.getKorisnickoIme();
    }

    public void setKorisnickoIme(String korisnickoIme){
        kupac.setKorisnickoIme(korisnickoIme);
    }

    public Set<Artikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public Set<ArtikliUPorudzbini> getJednaPorudzbina() {
        return jednaPorudzbina;
    }

    public void setJednaPorudzbina(Set<ArtikliUPorudzbini> jednaPorudzbina) {
        this.jednaPorudzbina = jednaPorudzbina;
    }

    public Dostavljac getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(Dostavljac dostavljac) {
        this.dostavljac = dostavljac;
    }

    @Override
    public String toString() {
        return "Porudzbina{" +
                ", UUID='" + UUID + '\'' +
                ", poruceniArtikli=" + poruceniArtikli +
                ", restoran=" + restoran +
                ", datum=" + datum +
                ", cena=" + cena +
                ", kupac=" + kupac +
                ", status=" + status +
                '}';
    }
}
