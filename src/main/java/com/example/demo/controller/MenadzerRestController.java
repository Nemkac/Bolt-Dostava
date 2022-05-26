package com.example.demo.controller;

import com.example.demo.dto.ArtikalDto;
import com.example.demo.entity.*;
import com.example.demo.service.ArtikalService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class MenadzerRestController {

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private RestoranService restoranService;

    @PostMapping("/api/artikal/kreiraj_artikal")
    public ResponseEntity<String> kreirajArtikal(@RequestBody ArtikalDto artikalDto, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.MENADZER){
            return new ResponseEntity<>("Ova funkcionalnost dostupna je samo menadzerima", HttpStatus.BAD_REQUEST);
        }

        if(artikalDto.getCena() == null || artikalDto.getNaziv().isEmpty() || artikalDto.getTip() == null){
            return new ResponseEntity<>("Sva polja moraju biti popunjena!", HttpStatus.BAD_REQUEST);
        }

        Artikal noviArtikal = new Artikal(artikalDto.getNaziv(), artikalDto.getCena(), artikalDto.getKolicina(), artikalDto.getOpis(), artikalDto.getTip());
        artikalService.sacuvajArtikal(noviArtikal);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovan;
        Restoran restoran = ulogovaniMenadzer.getRestoran();

        restoran.getArtikliUPonudi().add(noviArtikal);
        restoranService.sacuvajRestoran(restoran);

        return ResponseEntity.ok("Kreiranje artikla uspesno");
    }

    @PutMapping("/api/artikal/izmena_artikala")
    public ResponseEntity<String> izmeniArtikal(Long id, @RequestBody ArtikalDto artikalDto, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.MENADZER){
            return new ResponseEntity<>("Ova funkcionalnost dostupna je samo menadzerima!", HttpStatus.BAD_REQUEST);
        }

        Artikal artikal = artikalService.findById(id);
        if(!artikalDto.getNaziv().isEmpty()){
            artikal.setNaziv(artikalDto.getNaziv());
        }
        if(artikalDto.getKolicina() != 0){
           artikal.setKolicina(artikalDto.getKolicina());
        }
        if(artikalDto.getTip() != null){
            artikal.setTip(artikalDto.getTip());
        }
        if(!artikalDto.getCena().equals(0)){
            artikal.setCena(artikalDto.getCena());
        }
        if(!artikalDto.getOpis().isEmpty()){
            artikal.setOpis(artikalDto.getOpis());
        }

        return ResponseEntity.ok("Izmena artikla uspesna");
    }

    @DeleteMapping("/api/artikal/obrisi_artikal")
    public ResponseEntity<String> obrisiArtikal(Long id, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.MENADZER){
            return new ResponseEntity<>("Ova funkcionalnost dostupna je samo menadzerima!", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = (Menadzer) ulogovan;
        Restoran restoran = menadzer.getRestoran();

        for(Artikal artikal : restoran.getArtikliUPonudi()){
            if(artikal.getId().equals(id)){
                restoran.getArtikliUPonudi().remove(artikal);
                artikalService.obrisi(artikal);
                restoranService.sacuvajRestoran(restoran);
                return ResponseEntity.ok("Uspesno brisanje artikla");
            }
        }

        return new ResponseEntity<>("Brisanje artikla nije uspesno!", HttpStatus.EXPECTATION_FAILED);
    }


}
