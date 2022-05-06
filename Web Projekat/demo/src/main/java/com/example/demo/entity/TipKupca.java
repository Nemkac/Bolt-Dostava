package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TipKupca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Ime{ZLATNI, SREBRNI, BRONZANI}
    @Enumerated(EnumType.ORDINAL)
    @Column
    private Ime ime;
    @Column
    private int popust;
    @Column
    private int trazeniBrBodova;

    public TipKupca(){}

    public TipKupca(Ime ime, int popust, int trazeniBrBodova) {
        super();
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

    public Ime getIme() {
        return ime;
    }

    public void setIme(Ime ime) {
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
