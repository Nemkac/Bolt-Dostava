package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;
    @Column
    private String tip;

    @Column
    @OneToMany
    @JoinColumn(name = "restoran")
    private Set<Artikal> artikliUPonudi = new HashSet<>();

    //Jedan restoran moze biti postojati na vise lokacija
    @OneToOne
    private Lokacija lokacija;

    public Restoran(){}

    public Restoran(String naziv, String tip, Lokacija lokacija) {
        this.naziv = naziv;
        this.tip = tip;
        this.lokacija = lokacija;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public Set<Artikal> getArtikliUPonudi() {
        return artikliUPonudi;
    }

    public void setArtikliUPonudi(Set<Artikal> artikliUPonudi) {
        this.artikliUPonudi = artikliUPonudi;
    }

    @Override
    public String toString() {
        return "Restoran{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", tip='" + tip + '\'' +
                ", artikliUPonudi=" + artikliUPonudi +
                ", lokacija=" + lokacija +
                '}';
    }

}
