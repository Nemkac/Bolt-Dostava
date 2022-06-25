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
    @GeneratedValue(generator = "UUID")
    @Column
    private UUID UUID;

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

    public Porudzbina(){}

    public Porudzbina(Restoran restoran, Kupac kupac, Status status, Date datum, Set<ArtikliUPorudzbini> jednaPorudzbina) {
        this.restoran = restoran;
        this.kupac = kupac;
        this.status = status;
        this.datum = datum;
        this.jednaPorudzbina = jednaPorudzbina;
        for(ArtikliUPorudzbini artikal : jednaPorudzbina){
            this.cena += artikal.getArtikal().getCena();
        }
    }


    public UUID getUUID() {
        return UUID;
    }

    public void setUUID(UUID UUID) {
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
