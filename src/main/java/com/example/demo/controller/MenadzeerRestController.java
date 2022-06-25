package com.example.demo.controller;

import com.example.demo.dto.ArtikalDto;
import com.example.demo.dto.ArtikalZaBrisanjeDto;
import com.example.demo.entity.*;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class MenadzeerRestController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private ArtikalRepository artikalRepository;


    @GetMapping("/api/menadzer/pregledPorudzbina")
    public List<Porudzbina> pregledajPorudzbine(HttpSession sesija){
        if(!sessionService.validacijaSesije(sesija)){
            return null;
        }

        Menadzer ulogovanMenadzer = menadzerRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(sesija));
        Object kljuc = ulogovanMenadzer.getRestoran().getId();
        List<Porudzbina> tmp = porudzbinaRepository.findAll();
        List<Porudzbina> ispis = new ArrayList<>();
        for(Porudzbina p : tmp){
            if(p.getRestoran().getId().equals(kljuc)){
                ispis.add(p);
            }
        }
        return ispis;
    }
    @GetMapping("/api/menadzer/pregledRestorana")
    public Restoran pregledRestorana(HttpSession sesija){
        if(!sessionService.validacijaSesije(sesija)){
            return null;
        }
        Menadzer loggedMenadzer = menadzerRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(sesija));
        Restoran tmp = loggedMenadzer.getRestoran();
        return tmp;
    }

    @PostMapping("/api/menadzer/dodaj_artikal")
    public ResponseEntity<String> dodajArtikal(@RequestBody ArtikalDto artikalDto, HttpSession sesija){
        if(!sessionService.validacijaSesije(sesija)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Menadzer loggedMenadzer = menadzerRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(sesija));

        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, artikal se ne moze dodati",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.dodajArtikal(artikalDto.getNaziv(),artikalDto.getCena(),artikalDto.getTip(),loggedMenadzer);
        return ResponseEntity.ok("Artikal dodat");
    }

    @PutMapping("/api/menadzer/izmeni_artikal")
    public ResponseEntity<String> izmeniArtikal(@RequestBody ArtikalDto artikalDto, HttpSession session){
        Menadzer loggedMenadzer = (Menadzer) session.getAttribute("Korisnik");
        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce menjanje artikala",HttpStatus.METHOD_NOT_ALLOWED);
        }
        if(loggedMenadzer.getUloga() != Uloga.MENADZER){
            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce menjanje artikala",HttpStatus.METHOD_NOT_ALLOWED);
        }

        //Implementirati izmenu

        return ResponseEntity.ok("Artikal izmenjen");
    }

    //Izmenjeno brisanje artikla(POPRAVLJENO)
    @DeleteMapping("/api/menadzer/obrisiArtikal")
    public ResponseEntity<String> obrisiArtikal(@RequestBody ArtikalZaBrisanjeDto azbDto, HttpSession sesija){
        Menadzer ulogovanMenadzer = (Menadzer) sesija.getAttribute("Korisnik");

        if(ulogovanMenadzer == null){
            return new ResponseEntity("Nema ulogovanih menadzera!", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if(ulogovanMenadzer.getUloga() != Uloga.MENADZER){
            return new ResponseEntity("Ova funkcionalnost dozvoljena je samo menadzerima", HttpStatus.METHOD_NOT_ALLOWED);
        }

        Artikal zaBrisanje = artikalRepository.getByNaziv(azbDto.getNaziv());
        menadzerService.obrisiArtikal(zaBrisanje, ulogovanMenadzer);

        return ResponseEntity.ok("Artikal uspesno obrisan");
    }

}
