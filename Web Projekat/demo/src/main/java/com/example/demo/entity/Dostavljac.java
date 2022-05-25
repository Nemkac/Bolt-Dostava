package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Jedan dostavljac moze da ima vise porudzbina
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac(){
        super();
        super.setUloga(Uloga.DOSTAVLJAC);
    }

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        super.setUloga(Uloga.DOSTAVLJAC);
    }


    public Set<Porudzbina> getPorudzbine(){
        return porudzbine;
    }

    @Override
    public String toString() {
        return "Dostavljac{" +
                "id=" + id +
                ", porudzbine=" + porudzbine +
                '}';
    }
}
