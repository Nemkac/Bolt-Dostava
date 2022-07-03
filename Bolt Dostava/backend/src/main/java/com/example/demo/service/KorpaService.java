package com.example.demo.service;

import com.example.demo.controller.KupacRestController;
import com.example.demo.entity.*;
import com.example.demo.repository.KorpaRepository;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KorpaService {

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public List<Korpa> getAktivnaKorpa(Long idKupac){
        List<Korpa> korpa = korpaRepository.findAll();
        List<Korpa> aktivnaKorpa = new ArrayList<>();
        for(Korpa k : korpa){
            if(k.getKupac().getId() == idKupac && k.getPorudzbine() == null)
                aktivnaKorpa.add(k);
        }
        return aktivnaKorpa;
    }
    public boolean addArtikalUKorpu(Artikal artikal, Long idKupac, int kolicina, Long idRestoran){
        Kupac kupac = kupacRepository.getById(idKupac);
        if(kolicina < 1)
            return false;
        Restoran r = restoranRepository.getById(idRestoran);

        //Provera da li je poruceno iz nekog drugog restorana
        List<Korpa> aktivnaKopa = getAktivnaKorpa(idKupac);
        for(Korpa k : aktivnaKopa){
            if(k.getRestoran().getId() != idRestoran)
                return false;
        }

        Korpa k = new Korpa(artikal, kolicina, kolicina*artikal.getCena(), kupac, r);
        korpaRepository.save(k);
        return true;
    }

    public boolean deleteArtikalIzKorpe(Long idKupac, Long idKorpa){
        Optional<Korpa> findK = korpaRepository.findById(idKorpa);
        if(!findK.isPresent())
            return false;
        Korpa korpa = korpaRepository.getById(idKorpa);
        if(korpa.getKupac().getId() != idKupac)
            return false;
        korpaRepository.delete(korpa);
        return true;
    }

    public boolean poruci(Long idKupac){
        Kupac kupac = kupacRepository.getById(idKupac);
        List<Korpa> aktivnaKorpa = getAktivnaKorpa(idKupac);
        if(aktivnaKorpa.isEmpty())
            return false;
        Korpa korpa = new Korpa();
        int ukupnaCena = 0;
        for(Korpa k : aktivnaKorpa){
            ukupnaCena += k.getCenaArtikla();
            korpa = k;
        }

        Porudzbina p = new Porudzbina("4356345346", korpa.getRestoran(), kupac, Status.OBRADA, java.util.Date.from(Instant.now()));
        for(Korpa k : aktivnaKorpa){
            p.getPoruceniArtikli().add(k.getArtikal());
        }
        porudzbinaRepository.save(p);
        //kupac.getSvePorudzbine().add(p);

        Restoran r = korpa.getRestoran();
        r.getPorudzbine().add(p);
        kupac.getSvePorudzbine().add(p);
        restoranRepository.save(r);
        kupacRepository.save(kupac);

        korpaUpdate(p, idKupac);
        return true;
    }

    public void korpaUpdate(Porudzbina p, Long idKupac){
        List<Korpa> aktivnaKorpa = getAktivnaKorpa(idKupac);
        for(Korpa k : aktivnaKorpa){
            k.setPorudzbine(p);
            korpaRepository.save(k);
        }
    }
}
