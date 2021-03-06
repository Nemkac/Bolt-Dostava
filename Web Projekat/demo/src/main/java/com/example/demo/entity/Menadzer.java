package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Menadzer extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Restoran restoran;

    public Menadzer(){
        super();
        super.setUloga(Uloga.MENADZER);
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, LocalDate datumRodjenja, Restoran restoran) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.restoran = restoran;
        super.setUloga(Uloga.MENADZER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "Menadzer{" +
                "id=" + id +
                ", restoran=" + restoran +
                '}';
    }
}
