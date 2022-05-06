package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Jedan kupac moze da ima vise porudzbina
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Porudzbina> svePorudzbine = new HashSet<>();

    @Column
    private int brBodova;

    //Jedan kupac moze biti u trenutku samo jedan tip(zlatni/srebrni/bronzani)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TipKupca tip;

    public Kupac(){}

    public Kupac(Korisnik k, int brBodova){
        super(k);
        this.brBodova = brBodova;
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Korisnik.Uloga uloga, Date datumRodjenja, int brBodova) {
        super(korisnickoIme, lozinka, ime, prezime, pol, uloga, datumRodjenja);
        this.brBodova = brBodova;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrBodova() {
        return brBodova;
    }

    public void setBrBodova(int brBodova) {
        this.brBodova = brBodova;
    }

    public TipKupca getTip() {
        return tip;
    }

    public void setTip(TipKupca tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "id=" + id +
                ", svePorudzbine=" + svePorudzbine +
                ", brBodova=" + brBodova +
                ", tip=" + tip +
                '}';
    }
}
