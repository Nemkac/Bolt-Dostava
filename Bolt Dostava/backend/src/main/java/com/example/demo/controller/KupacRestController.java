package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class KupacRestController {
    @Autowired
    private KupacService kupacService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KorpaService korpaService;

    @PostMapping("/api/restoran/{idRestorana}/korpa/dodajUKorpu/{korisnickoIme}")
    public ResponseEntity<String> dodajUKorpu(@PathVariable(name = "idRestorana") Long idRestorana, @PathVariable(name = "korisnickoIme") String korisnickoIme, @RequestBody DodajUKorpuDto dukdto, HttpSession session) {

        Kupac ulogovanKupac = kupacRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanKupac == null) {
            return new ResponseEntity<>("Nema ulogovanih kupaca!", HttpStatus.BAD_REQUEST);
        }

        Restoran restoran = restoranService.findById(idRestorana);

        //Prepraviti da vazi ako ne radi restoran
        if (restoran.isRadi())
            return new ResponseEntity("Restoran je trenutno zatvoren", HttpStatus.BAD_REQUEST);

        Artikal artikal = artikalService.getArtikalByNaziv(dukdto.getNazivArtikla(), idRestorana);
        if (artikal == null)
            return new ResponseEntity("Artikal nije pronadjen..", HttpStatus.BAD_REQUEST);
        else {
            korpaService.addArtikalUKorpu(artikal, ulogovanKupac.getId(), dukdto.getKolicina(), idRestorana);
            return ResponseEntity.ok("Uspesno ubaceno u korpu");
        }
    }

    @GetMapping("/api/restoran/{idRestorana}/korpa/pregledKorpe/{korisnickoIme}")
    public ResponseEntity<PrikazKorpeDto> pregledKorpe(@PathVariable(name = "idRestorana") Long idRestorana, @PathVariable(name = "korisnickoIme") String korisnickoIme, HttpSession session){

        Kupac ulogovanKupac = kupacRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanKupac == null) {
            return new ResponseEntity("Nema ulogovanih kupaca!", HttpStatus.BAD_REQUEST);
        }

        List<Korpa> aktivnaKorpa = korpaService.getAktivnaKorpa(ulogovanKupac.getId());
        if(aktivnaKorpa.isEmpty())
            return new ResponseEntity("Korpa je prazna", HttpStatus.BAD_REQUEST);

        List<PrikazArtikalaDto> aktivnaKorpaDto = new ArrayList<>();
        int ukupnaCena = 0;
        for(Korpa ko : aktivnaKorpa){
            PrikazArtikalaDto kodto = new PrikazArtikalaDto(ko);
            aktivnaKorpaDto.add(kodto);
            ukupnaCena += ko.getCenaArtikla();
        }

        PrikazKorpeDto korpaPregledDto = new PrikazKorpeDto(aktivnaKorpaDto, ukupnaCena);
        return ResponseEntity.ok(korpaPregledDto);
    }

    @DeleteMapping("/api/restoran/{idRestorana}/korpa/izbaciIzKorpe/{idKorpa}/{korisnickoIme}")
    public ResponseEntity<String> izbaciIzKorpe(@PathVariable(name = "idRestorana") Long idRestorana, @PathVariable(name = "idKorpa") Long idKorpa, @PathVariable(name = "korisnickoIme") String korisnickoIme, HttpSession session){
        Kupac ulogovanKupac = kupacRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanKupac == null) {
            return new ResponseEntity("Nema ulogovanih kupaca!", HttpStatus.BAD_REQUEST);
        }

        if(korpaService.deleteArtikalIzKorpe(ulogovanKupac.getId(), idKorpa))
            return ResponseEntity.ok("Upseno izbaceno iz korpe");
        return new ResponseEntity("Doslo je do greske..", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/kupac/prikazPorudzbina/{korisnickoIme}")
    public ResponseEntity<Set<ListaPorudzbinaDto>> listaPorudzbina(HttpSession session, @PathVariable(name = "korisnickoIme") String korisnickoIme){
        Kupac ulogovanKupac = kupacRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanKupac == null) {
            return new ResponseEntity("Nema ulogovanih kupaca!", HttpStatus.BAD_REQUEST);
        }

        Kupac kupac = kupacService.findKupacById(ulogovanKupac.getId());
        Set<Porudzbina> porudzbine = kupac.getSvePorudzbine();

        if(porudzbine.isEmpty())
            return new ResponseEntity("Nije pronadjena nijedna porudzbina", HttpStatus.BAD_REQUEST);

        Set<ListaPorudzbinaDto> dtos = new HashSet<>();
        for(Porudzbina porudzbina : porudzbine){
            ListaPorudzbinaDto pdto = new ListaPorudzbinaDto(porudzbina);
            dtos.add(pdto);
        }
        return ResponseEntity.ok(dtos);
    }

    //Proveriti sta nije kako treba!!!
    @PutMapping("/api/restoran/{idRestorana}/korpa/kreirajPorudzbinu/{korisnickoIme}")
    public ResponseEntity<String> kreirajPorudzbinu(@PathVariable(name = "idRestorana") Long idRestorana, @PathVariable(name = "korisnickoIme") String korisnickoIme, HttpSession session) {

        Kupac ulogovanKupac = kupacRepository.getByKorisnickoIme(korisnickoIme);

        if (ulogovanKupac == null) {
            return new ResponseEntity("Nema ulogovanih kupaca!", HttpStatus.BAD_REQUEST);
        }

        if (!korpaService.poruci(ulogovanKupac.getId())){
            return new ResponseEntity("Korpa je prazna", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Uspesno ste porucili");}
}
