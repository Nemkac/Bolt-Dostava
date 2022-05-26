package com.example.demo.controller;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.*;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PorudzbinaRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping("/api/porudzbine/prikaz")
    public ResponseEntity<List<Porudzbina>> prikaziPorudzbine(@PathVariable(name = "id") Long id, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan == null){
            return new ResponseEntity("Ova funkcionalnost dostupna je samo ulogovanim korisnicima!", HttpStatus.BAD_REQUEST);
        }

        if(ulogovan.getUloga() != Uloga.KUPAC){
            return new ResponseEntity("Ova funkcionalnost dostupna je samo kupcima!", HttpStatus.BAD_REQUEST);
        }

        List<Porudzbina> porudzbine = porudzbinaService.porudzbineKupca(id);
        return ResponseEntity.ok(porudzbine);

    }

    @GetMapping("/api/porudzbine/dostavljac/naCekanju")
    public ResponseEntity<List<Porudzbina>> porudzbineNaCekanju(@PathVariable(name = "id") Long id, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan == null){
            return new ResponseEntity("Morate biti ulogovani!", HttpStatus.BAD_REQUEST);
        }
        if(ulogovan.getUloga() != Uloga.DOSTAVLJAC){
            return new ResponseEntity("Ova funkcija dostupna je samo dostavljacima", HttpStatus.BAD_REQUEST);
        }

        Dostavljac dostavljac = (Dostavljac) ulogovan;
        List<Porudzbina> porudzbineNaCekanju = new ArrayList<>();

        for(Porudzbina porudzbina : porudzbinaService.findAll()){
            if(porudzbina.getStatus() == Status.CEKADOSTAVLJACA){
                porudzbineNaCekanju.add(porudzbina);
            }
        }

        return ResponseEntity.ok(porudzbineNaCekanju);
    }

    @GetMapping("/api/porudzbine/dostavljac/zaduzene")
    public ResponseEntity<List<Porudzbina>> zaduzenePorudzbine(@PathVariable(name = "id") Long id, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan == null){
            return new ResponseEntity("Morate biti ulogovani!", HttpStatus.BAD_REQUEST);
        }
        if(ulogovan.getUloga() != Uloga.DOSTAVLJAC){
            return new ResponseEntity("Ova funkcija dostupna je samo dostavljacima", HttpStatus.BAD_REQUEST);
        }

        List<Porudzbina> zaduzenePorudzbine = new ArrayList<>();

        for(Porudzbina porudzbina : porudzbinaService.findAll()){
            if(porudzbina.getStatus() == Status.UTRANSPORTU){
                zaduzenePorudzbine.add(porudzbina);
            }
        }

        return ResponseEntity.ok(zaduzenePorudzbine);
    }

    @GetMapping("/api/porudzbine/menadzer/prikaz")
    public ResponseEntity<List<Porudzbina>> porudzbinePoRestoranu(Long id, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan == null){
            return new ResponseEntity("Morate biti ulogovani!", HttpStatus.BAD_REQUEST);
        }

        if(ulogovan.getUloga() != Uloga.MENADZER){
            return new ResponseEntity("Ova funkcionalnost dostupna je samo menadzerima", HttpStatus.BAD_REQUEST);
        }

        List<Porudzbina> porudzbine = porudzbinaService.porudzbinePoRestoranu(id);

        return ResponseEntity.ok(porudzbine);

    }

}
