package com.example.demo.controller;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.*;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.service.ArtikalService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PorudzbinaService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class PorudzbinaRestController{

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private KupacRepository kupacRepository;

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

    //Ispravljeno sa druge tacke, pregled svih porudzbina jednog kupca.
    @GetMapping("api/porudzbine/kupac")
    public ResponseEntity<List<PorudzbinaDto>> pregledPorudzbinaKupac(HttpSession session){
        if(!sessionService.validacijaSesije(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Kupac loggedKupac = kupacRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(session));

        if(loggedKupac == null){
            return new ResponseEntity("Nema ulogovanih Kupaca",HttpStatus.METHOD_NOT_ALLOWED);
        }

        Kupac kupac = (Kupac) session.getAttribute("Korisnik");
        List<Porudzbina> porudzbine = this.porudzbinaService.findAll();
        List<PorudzbinaDto> dtos = new ArrayList<>();

        for(Porudzbina porudzbina : porudzbine){
            if(porudzbina.getKorisnickoIme().equals(kupac.getKorisnickoIme())){
                PorudzbinaDto dto = new PorudzbinaDto(porudzbina);
                dtos.add(dto);
            }

        }

        if(dtos.isEmpty()){
            return new ResponseEntity("Nemate ni jednu porudzbinu!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(dtos);

    }


}
