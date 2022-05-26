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

    //ManyToMany, posebna klasa koja ima u sebi artikal i koliko je poruceno artikla
    //Na jednoj porudzbini moze biti vise artikala
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    //Vise porudzbina moze biti iz istog restorana
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Restoran restoran;

    //Date
    @Column
    private Date datum;
    @Column
    private Float cena;

    //Vise porudzbina moze da bude naruceno od jednog kupac
    @ManyToOne
    @JoinColumn(name = "kupac")
    private Kupac kupac;

    @Column
    private Status status;

    public Porudzbina(){}

    public Porudzbina(Restoran restoran, Float cena, Kupac kupac, Status status, Date datum) {
        this.restoran = restoran;
        this.cena = cena;
        this.kupac = kupac;
        this.status = status;
        this.datum = datum;
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
                ", cena=" + cena +
                ", kupac=" + kupac +
                ", status=" + status +
                '}';
    }
}
