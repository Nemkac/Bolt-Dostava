package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.TipArtikla;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MenadzerService {
    @Autowired
    private MenadzerRepository menadzerRepository;
    @Autowired
    private ArtikalRepository artikalRepository;
    @Autowired
    private RestoranRepository restoranRepository;

    public Menadzer findByUsername(String username) {
        Optional<Menadzer> pronadjeni = Optional.ofNullable(menadzerRepository.getByKorisnickoIme(username));
        return pronadjeni.orElse(null);
    }

    public void dodajArtikal(String naziv, Long cena, TipArtikla tip,String opis, Menadzer menadzer) {
        Artikal artikal = new Artikal(naziv, cena, tip,opis);
        artikal.setRestoran(menadzer.getRestoran());
        artikalRepository.save(artikal);
        menadzer.getRestoran().getArtikliUPonudi().add(artikal);
        restoranRepository.save(menadzer.getRestoran());
    }

    public void obrisiArtikal(Artikal artikal, Menadzer menadzer){
        menadzer.getRestoran().getArtikliUPonudi().remove(artikal);
        artikalRepository.delete(artikal);
    }
}
