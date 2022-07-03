package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Admin extends Korisnik implements Serializable {
    public Admin(){
        super();
        super.setUloga(Uloga.ADMIN);
    }

    public Admin(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datum){
        super(korisnickoIme, lozinka, ime, prezime, pol, datum);
        super.setUloga(Uloga.ADMIN);
    }
}
