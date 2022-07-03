package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;
    @Column
    private int popust;
    @Column
    private int trazeniBrBodova;

    public TipKupca(){}

    public TipKupca(String ime, int popust, int trazeniBrBodova) {
        this.ime = ime;
        this.popust = popust;
        this.trazeniBrBodova = trazeniBrBodova;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public int getTrazeniBrBodova() {
        return trazeniBrBodova;
    }

    public void setTrazeniBrBodova(int trazeniBrBodova) {
        this.trazeniBrBodova = trazeniBrBodova;
    }

    @Override
    public String toString() {
        return "TipKupca{" +
                "id=" + id +
                ", ime=" + ime +
                ", popust=" + popust +
                ", trazeniBrBodova=" + trazeniBrBodova +
                '}';
    }
}
