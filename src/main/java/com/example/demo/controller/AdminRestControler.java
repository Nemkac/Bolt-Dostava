package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.AdminService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminRestControler {
    @Autowired
    private AdminService adminService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private MenadzerService menadzerService;


    @PostMapping("/api/kreiraj_dostavljaca")
    public ResponseEntity<String> kreirajDostavljaca(DostavljacDto dostavljacDto, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan == null){
            return new ResponseEntity<>("Nepostojeci korisnik", HttpStatus.BAD_REQUEST);
        }

        if(ulogovan.getUloga() != Uloga.ADMIN){
            return new ResponseEntity<>("Ulogovani korisnik nije admin", HttpStatus.BAD_REQUEST);
        }

        Dostavljac dostavljac = new Dostavljac(dostavljacDto.getKorisnickoIme(), dostavljacDto.getLozinka(), dostavljacDto.getIme(), dostavljacDto.getPrezime(), dostavljacDto.getPol(), dostavljacDto.getDatum());
        adminService.napraviDostavljaca(dostavljac);

        return ResponseEntity.ok("Dostavljac uspesno kreiran");
    }

    @PostMapping("/api/kreiraj_menadzera")
    public ResponseEntity<String> kreirajMenadzera(MenadzerDto menadzerDto, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.ADMIN){
            return new ResponseEntity<>("Ulogovani korisnik nije admin!", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = new Menadzer(menadzerDto.getKorisnickoIme(), menadzerDto.getLozinka(), menadzerDto.getIme(), menadzerDto.getPrezime(), menadzerDto.getPol(), menadzerDto.getDatum());
        adminService.napraviMenadzera(menadzer);

        return ResponseEntity.ok("Menadzer uspesno kreiran!");
    }

    @PostMapping("/api/kreiraj_restoran")
    public ResponseEntity<String> kreirajRestoran(@RequestBody RestoranDto restoranDto, MenadzerDto menadzerDto, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.ADMIN){
            return new ResponseEntity<>("Ulogovani korisnik nije admin!", HttpStatus.BAD_REQUEST);
        }

        if(restoranDto.getNaziv().isEmpty() || restoranDto.getTip().isEmpty() || restoranDto.getLokacija() == null){
            return new ResponseEntity<>("Uneti podaci nisu ispravni!", HttpStatus.BAD_REQUEST);
        }

        Restoran noviRestoran = new Restoran(restoranDto.getNaziv(), restoranDto.getTip(), restoranDto.getLokacija());
        restoranService.sacuvajRestoran(noviRestoran);

        Menadzer zaduzeniMenadzer = (Menadzer) menadzerService.findOne(restoranDto.getUsernameMenadzera());
        zaduzeniMenadzer.setRestoran(noviRestoran);

        return ResponseEntity.ok("Restoran uspesno kreiran!");
    }

    /*@PostMapping("/api/obrisi_restoran")
    public ResponseEntity<String> obrisiRestoran(HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.ADMIN){
            return new ResponseEntity<>("Ulogovani korisnik nije admin!", HttpStatus.BAD_REQUEST);
        }

        restoranService.obrisiRestoran()
    }*/

}
