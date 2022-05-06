package com.example.demo.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Porudzbina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UUID;

    //ManyToMany, posebna klasa koja ima u sebi artikal i koliko je poruceno artikla
    //Na jednoj porudzbini moze biti vise artikala
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    //Vise porudzbina moze biti iz istog restorana
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Restoran restoran;

    //Date
    @Column
    private LocalDate datum;
    @Column
    private LocalTime vreme;
    @Column
    private Float cena;

    //Vise porudzbina moze da bude naruceno od jednog kupac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    enum Status{OBRADA, UPRIPREMI, CEKADOSTAVLJACA, UTRANSPORTU, DOSTAVLJENA, OTKAZANA}
    @Enumerated(EnumType.ORDINAL)
    @Column
    private Status status;

    public Porudzbina(){}

    public Porudzbina(Restoran restoran, Float cena, Kupac kupac, Status status) {
        this.restoran = restoran;
        this.cena = cena;
        this.kupac = kupac;
        this.status = status;
        this.vreme = LocalTime.now();
        this.datum = LocalDate.now();
    }


    public int getUUID() {
        return UUID;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getVreme() {
        return vreme;
    }

    public void setVreme(LocalTime vreme) {
        this.vreme = vreme;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
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

    @Override
    public String toString() {
        return "Porudzbina{" +
                ", UUID='" + UUID + '\'' +
                ", poruceniArtikli=" + poruceniArtikli +
                ", restoran=" + restoran +
                ", datum=" + datum +
                ", vreme=" + vreme +
                ", cena=" + cena +
                ", kupac=" + kupac +
                ", status=" + status +
                '}';
    }
}
