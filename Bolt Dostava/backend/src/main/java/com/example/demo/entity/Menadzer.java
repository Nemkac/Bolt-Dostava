package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("Menadzer")
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

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        super.setUloga(Uloga.MENADZER);
    }

    public Menadzer(Menadzer m){
        super(m.getKorisnickoIme(), m.getLozinka(), m.getIme(), m.getPrezime(), m.getPol(), m.getDatum());
        this.setUloga(Uloga.MENADZER);
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
