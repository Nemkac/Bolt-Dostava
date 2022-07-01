package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MenadzerRepository menadzerRepository;
    @Autowired
    private DostavljacRepository dostavljacRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private LokacijaRepository lokacijaRepository;
    @Autowired
    private RestoranRepository restoranRepository;

    public Menadzer napraviMenadzera(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datum){
        Menadzer noviMenadzer = new Menadzer(korisnickoIme, lozinka, ime, prezime, pol, datum);
        korisnikRepository.save(noviMenadzer);
        menadzerRepository.save(noviMenadzer);
        return noviMenadzer;
    }

    public Dostavljac napraviDostavljaca(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datum){
        Dostavljac noviDostavljac = new Dostavljac(korisnickoIme, lozinka, ime, prezime, pol, datum);
        noviDostavljac.setUloga(Uloga.DOSTAVLJAC);
        korisnikRepository.save(noviDostavljac);
        dostavljacRepository.save(noviDostavljac);
        return noviDostavljac;
    }

    public Restoran napraviRestoran(String naziv, String tip, long geoDuzina, long geoSirina, String adresa, String menadzer, boolean radi){
        Lokacija novaLokacija = new Lokacija(geoDuzina, geoSirina, adresa);
        Restoran noviRestoran = new Restoran(naziv, tip, novaLokacija, radi);
        lokacijaRepository.save(novaLokacija);
        restoranRepository.save(noviRestoran);


        return noviRestoran;
    }

}

