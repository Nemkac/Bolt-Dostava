package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ArtikliUPorudzbini implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artikal artikal;

    @Column
    private int brojArtikala;

    public ArtikliUPorudzbini(){}

    public ArtikliUPorudzbini(Artikal artikal, int brojArtikala) {
        this.artikal = artikal;
        this.brojArtikala = brojArtikala;
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

    public int getBrojArtikala() {
        return brojArtikala;
    }

    public void setBrojArtikala(int brojArtikala) {
        this.brojArtikala = brojArtikala;
    }
}
