package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.PorudzbinaService;
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

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private RestoranRepository restoranRepository;


    @GetMapping("/api/menadzer/pregledPorudzbina/{korisnickoIme}")
    public List<Porudzbina> pregledajPorudzbine(HttpSession sesija, @PathVariable(name = "korisnickoIme") String korisnickoIme){

        Menadzer ulogovanMenadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);
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
    @GetMapping("/api/menadzer/pregledRestorana/{korisnickoIme}")
    public ResponseEntity<MenadzerPrikazDto> pregledRestorana(HttpSession sesija, @PathVariable(name = "korisnickoIme") String korisnickoIme){

        Menadzer loggedMenadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);

        List<Porudzbina> porudzbine = porudzbinaService.getPorudzbineByRestoranId(loggedMenadzer.getRestoran().getId());

        List<PorudzbinaDto> porudzbineDto = new ArrayList<>();
        for(Porudzbina p : porudzbine){
            PorudzbinaDto pdto = new PorudzbinaDto(p);
            porudzbineDto.add(pdto);
        }

        MenadzerPrikazDto mdto = new MenadzerPrikazDto(loggedMenadzer, porudzbineDto);

        return ResponseEntity.ok(mdto);
    }

    @PostMapping("/api/menadzer/dodaj_artikal/{korisnickoIme}")
    public ResponseEntity<String> dodajArtikal(@RequestBody ArtikalDto artikalDto, HttpSession sesija, @PathVariable(name = "korisnickoIme") String korisnickoIme){

        Menadzer loggedMenadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, artikal se ne moze dodati",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.dodajArtikal(artikalDto.getNaziv(),artikalDto.getCena(),artikalDto.getTip(),artikalDto.getOpis(),loggedMenadzer);
        return ResponseEntity.ok("Artikal dodat");
    }

    @PostMapping("/api/menadzer/otvori_restoran/{restoranID}/{otvoren}")
    public ResponseEntity<String> otvoriRestoran( @PathVariable(name = "restoranID") Long restoranID, @PathVariable(name = "otvoren") boolean otvoren, HttpSession sesija){

        Restoran restoran = restoranRepository.getById(restoranID);

        if(restoran == null){
            return new ResponseEntity<>("Restoran ne postoji.",HttpStatus.METHOD_NOT_ALLOWED);
        }
        restoran.setRadi(otvoren);
        restoranRepository.save(restoran);
        if(otvoren) {
            return ResponseEntity.ok("Restoran otvoren!");
        } else return ResponseEntity.ok("Restoran zatvoren!");

    }

    @GetMapping("/api/menadzer/getRestoran/{restoranID}")
    public ResponseEntity<RestoranDto> getRestoran( @PathVariable(name = "restoranID") Long restoranID, HttpSession sesija){

        Restoran restoran = restoranRepository.getById(restoranID);

        if(restoran == null){
            return new ResponseEntity<>(null);
        }
        RestoranDto ret = new RestoranDto(restoran);
        return ResponseEntity.ok(ret);

    }

    @PutMapping("/api/menadzer/izmeni_artikal/{korisnickoIme}")
    public ResponseEntity<String> izmeniArtikal(@RequestBody ArtikalDto artikalDto, HttpSession session, @PathVariable(name = "korisnickoIme") String korisnickoIme){
//        Menadzer loggedMenadzer = (Menadzer) session.getAttribute("Korisnik");
//        if(loggedMenadzer == null){
//            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce menjanje artikala",HttpStatus.METHOD_NOT_ALLOWED);
//        }
//        if(loggedMenadzer.getUloga() != Uloga.MENADZER){
//            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce menjanje artikala",HttpStatus.METHOD_NOT_ALLOWED);
//        }

        //Implementirati izmenu

        return ResponseEntity.ok("Artikal izmenjen");
    }

    //Izmenjeno brisanje artikla(POPRAVLJENO)
    @DeleteMapping("/api/menadzer/obrisiArtikal/{artikalZaBrisanje}/{korisnickoIme}")
    public ResponseEntity<String> obrisiArtikal(@PathVariable(name = "artikalZaBrisanje") String artikalZaBrisanje, HttpSession sesija, @PathVariable(name = "korisnickoIme") String korisnickoIme){
        Menadzer ulogovanMenadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanMenadzer == null){
            return new ResponseEntity("Nema ulogovanih menadzera!", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if(ulogovanMenadzer.getUloga() != Uloga.MENADZER){
            return new ResponseEntity("Ova funkcionalnost dozvoljena je samo menadzerima", HttpStatus.METHOD_NOT_ALLOWED);
        }

        Artikal zaBrisanje = artikalRepository.getByNaziv(artikalZaBrisanje);
        menadzerService.obrisiArtikal(zaBrisanje, ulogovanMenadzer);

        return ResponseEntity.ok("Artikal uspesno obrisan");
    }

    @PutMapping("/api/menadzer/promeniStatusUPripremi/{idPorudzbine}/{korisnickoIme}")
    public ResponseEntity<String> promeniStatusUPripremi(@PathVariable(name = "idPorudzbine") Long idPorudzbine, HttpSession session, @PathVariable(name = "korisnickoIme") String korisnickoIme){
        Menadzer ulogovanMenadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanMenadzer == null){
            return new ResponseEntity("Nema ulogovanih menadzera!", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if(ulogovanMenadzer.getUloga() != Uloga.MENADZER){
            return new ResponseEntity("Ova funkcionalnost dozvoljena je samo menadzerima", HttpStatus.METHOD_NOT_ALLOWED);
        }


        Porudzbina p = porudzbinaService.getPorudzbinaById(idPorudzbine);
        if(p == null)
            return new ResponseEntity("Nepostojeca porudzbina", HttpStatus.BAD_REQUEST);
        if(p.getStatus() != Status.OBRADA)
            return new ResponseEntity("Status porudzbine mora biti - OBRADA", HttpStatus.BAD_REQUEST);


        porudzbinaService.updateStauts(Status.UPRIPREMI, idPorudzbine);

        return ResponseEntity.ok("Status promenjen - UPRIPREMI");
    }

    @PutMapping("/api/menadzer/promeniStatusUCekaDostavljaca/{idPorudzbine}/{korisnickoIme}")
    public ResponseEntity<String> promeniStatusUCekaDostavljaca(@PathVariable(name = "idPorudzbine") Long idPorudzbine, HttpSession session, @PathVariable(name = "korisnickoIme") String korisnickoIme){
        Menadzer ulogovanMenadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanMenadzer == null){
            return new ResponseEntity("Nema ulogovanih menadzera!", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if(ulogovanMenadzer.getUloga() != Uloga.MENADZER){
            return new ResponseEntity("Ova funkcionalnost dozvoljena je samo menadzerima", HttpStatus.METHOD_NOT_ALLOWED);
        }


        Porudzbina p = porudzbinaService.getPorudzbinaById(idPorudzbine);
        if(p == null)
            return new ResponseEntity("Ne postoji porudzbina", HttpStatus.BAD_REQUEST);
        if(p.getStatus() != Status.UPRIPREMI)
            return new ResponseEntity("Status porudzbine mora biti - UPRIPREMI", HttpStatus.BAD_REQUEST);


        porudzbinaService.updateStauts(Status.CEKADOSTAVLJACA, idPorudzbine);

        return ResponseEntity.ok("Status promenjen - CEKA_DOSTAVLJACA");
    }


}
