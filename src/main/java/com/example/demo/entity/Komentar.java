package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Vise komentara moze biti ostavljeno od strane jednog kupca
    @ManyToOne(fetch = FetchType.LAZY)
    private Kupac kupac;

    //Vise komentara moze biti ostavljeno ispod jednog restorana
    @OneToOne
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;
    @Column
    private String tekstKomentara;
    @Column
    private int ocena;

    public Komentar(){}

    public Komentar(Kupac kupac, Restoran restoran, int ocena, String tekstKomentara) {
        this.kupac = kupac;
        this.restoran = restoran;
        this.ocena = ocena;
        this.tekstKomentara = tekstKomentara;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Komentar{" +
                "id=" + id +
                ", kupac=" + kupac +
                ", restoran=" + restoran +
                ", tekstKomentara='" + tekstKomentara + '\'' +
                ", ocena=" + ocena +
                '}';
    }
}
