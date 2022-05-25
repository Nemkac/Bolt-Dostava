package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Pol;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik registracija(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datum){
        Korisnik noviKorisnik = new Korisnik(korisnickoIme, lozinka, ime, prezime, pol, datum);
        korisnikRepository.save(noviKorisnik);

        return noviKorisnik;
    }

    public Korisnik login(String korisnickoIme, String lozinka){
        Korisnik ulogovan = korisnikRepository.findBykorisnickoIme(korisnickoIme);

        if(ulogovan == null || !ulogovan.getLozinka().equals(lozinka))
            return null;

        return ulogovan;
    }

    public Korisnik sacuvajKorisnika(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public List<Korisnik> prikaziKorisnike() {
        return korisnikRepository.findAll();
    }
}
