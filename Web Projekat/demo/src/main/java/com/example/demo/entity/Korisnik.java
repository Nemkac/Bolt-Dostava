package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Columns:
    @Column(name = "korisnickoIme", unique = true)
    private String korisnickoIme;
    @Column(name = "lozinka")
    private String lozinka;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;

    public enum Pol{MUSKO, ZENSKO, DRUGO}
    @Enumerated(EnumType.ORDINAL)
    @Column
    private Pol pol;

    public enum Uloga{ADMIN, MENADZER, DOSTAVLJAC, KUPAC}
    @Enumerated(EnumType.ORDINAL)
    @Column
    private Uloga uloga;

    @Column
    private Date datumRodjenja;

    public Korisnik(){}

    public Korisnik(Korisnik k){
        korisnickoIme = k.korisnickoIme;
        lozinka = k.lozinka;
        ime = k.ime;
        prezime = k.prezime;
        pol = k.pol;
        uloga = k.uloga;
        datumRodjenja = k.datumRodjenja;
    }

    public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Uloga uloga, Date datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.uloga = uloga;
        this.datumRodjenja = datumRodjenja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", pol='" + pol + '\'' +
                ", uloga=" + uloga +
                ", datumRodjenja=" + datumRodjenja +
                '}';
    }
}
