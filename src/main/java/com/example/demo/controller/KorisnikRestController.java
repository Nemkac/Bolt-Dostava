package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.RestoranService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/api/registracija")
    public ResponseEntity<String> registracija(@RequestBody RegisterDto registerDtoDto, HttpSession sesija){
        if(registerDtoDto.getKorisnickoIme().isEmpty() ||
                registerDtoDto.getLozinka().isEmpty() ||
                registerDtoDto.getIme().isEmpty() ||
                registerDtoDto.getPrezime().isEmpty() ||
                registerDtoDto.getPol() == null ||
                registerDtoDto.getDatum() == null){
                return new ResponseEntity<>("Uneti podaci nisu ispravni!", HttpStatus.BAD_REQUEST);
        }

        Korisnik noviKorisnik =  korisnikService.registracija(registerDtoDto.getKorisnickoIme(), registerDtoDto.getLozinka(), registerDtoDto.getIme(), registerDtoDto.getPrezime(), registerDtoDto.getPol(), registerDtoDto.getDatum());
        if(noviKorisnik == null){
            return new ResponseEntity<>("Uneto korisnicko ime vec postoji!", HttpStatus.BAD_REQUEST);
        }

        sesija.setAttribute("Korisnik", noviKorisnik);
        return ResponseEntity.ok("Registrovanje upesno!");
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession sesija) {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()) {
            return new ResponseEntity("Uneti podaci nisu ispravni!", HttpStatus.BAD_REQUEST);
        }

        Korisnik korisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());

        if (korisnik == null)
            return new ResponseEntity<>("Uneli ste nepostojece korinicko ime!", HttpStatus.NOT_FOUND);

        sesija.setAttribute("Korisnik",korisnik);
        sesija.setAttribute("korisnickoIme", korisnik.getKorisnickoIme());
        sesija.setAttribute("uloga", korisnik.getUloga());

        return ResponseEntity.ok("Logovanje uspesno!");
    }

    @PostMapping("/api/logout")
    public ResponseEntity<String> logout(HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        sesija.invalidate();

        return ResponseEntity.ok("Uspesno ste se izlogovali");
    }

    @GetMapping("/api/prikaz_podataka")
    public Korisnik prikazPodataka(HttpSession sesija){
        if(sesija.getAttribute("Korisnik") != null){
            Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");
            List<Korisnik> listaKorisnika = korisnikService.prikaziKorisnike();

            for(Korisnik korisnik : listaKorisnika){
                if(korisnik.getKorisnickoIme().equals(ulogovan.getKorisnickoIme())){
                    return korisnik;
                }
            }
        }

        return null;
    }

    @PutMapping("/api/izmena_podataka")
    public ResponseEntity<String> izmenaPodataka(@RequestBody KorisnikDto korisnikDto, HttpSession sesija){
        if(sesija.getAttribute("Korisnik") != null){
            Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");
            List<Korisnik> listaKorisnika = korisnikService.prikaziKorisnike();

            for(Korisnik korisnik : listaKorisnika){
                if(korisnik.getKorisnickoIme().equals(ulogovan.getKorisnickoIme())){
                    ulogovan.setKorisnickoIme(korisnikDto.getKorisnickoIme());
                    ulogovan.setLozinka(korisnikDto.getLozinka());
                    ulogovan.setIme(korisnikDto.getIme());
                    ulogovan.setPrezime(korisnikDto.getPrezime());
                    ulogovan.setPol(korisnikDto.getPol());
                    ulogovan.setDatum(korisnikDto.getDatum());

                    korisnikService.sacuvajKorisnika(ulogovan);
                    sesija.setAttribute("Korisnik", ulogovan);
                    return ResponseEntity.ok("Podaci uspesno izmenjeni!");
                }
            }
        }

        return new ResponseEntity<>("Greska prilikom izmene podataka!", HttpStatus.EXPECTATION_FAILED);
    }

    //Isto kao prethodna funkcija, trebalo bi da radi samo sto ne znam kako da podesim postmen da mi ispise dobro
    @GetMapping("/api/listaKorisnika")
    public ResponseEntity<List<Korisnik>> listaKorisnika(HttpSession sesija){
        if(!sessionService.validacijaSesije(sesija)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(!sessionService.getUloga(sesija).equals(Uloga.ADMIN)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<Korisnik> listaKorisnika = korisnikService.prikaziKorisnike();

        return ResponseEntity.ok(listaKorisnika);
    }

    @GetMapping("/api/lista_restorana")
    public List<Restoran> listaRestorana(){
        List<Restoran> listaRestorana = restoranService.findAll();
        return listaRestorana;
    }

    @GetMapping("/api/pregled_restorana")
    public ResponseEntity<Restoran> pregledRestorana(HttpSession sesija){
        Menadzer ulogovan = (Menadzer) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.MENADZER){
            return new ResponseEntity("Ova funkcionalnost dozvoljena je samo menadzerima", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(ulogovan.getRestoran());

    }

    @GetMapping("/api/restoran/pretraga/poImenu")
    public List<Restoran> pretraziPoImenu(@RequestBody pretragaPoImenuDto ppidto){
        List<Restoran> listaRestorana = restoranService.findAll();

        List<Restoran> restorans = new ArrayList<>();

        for(Restoran restoran : listaRestorana){
            if(restoran.getNaziv().contains(ppidto.getIme())){
                restorans.add(restoran);
            }
        }

        return restorans;

    }

    @GetMapping("/api/restoran/pretraga/poTipu")
    public List<Restoran> pretraziPoTipu(@RequestBody pretragaPoTipuDto pptDto){
        List<Restoran> listaRestorana = restoranService.findAll();

        List<Restoran> restorans = new ArrayList<>();

        for(Restoran restoran : listaRestorana){
            if(restoran.getTip().equals(pptDto.getTip())){
                restorans.add(restoran);
            }
        }
        return restorans;
    }

    @GetMapping("/api/restoran/pretraga/poLokaciji")
    public List<Restoran> pretraziPoLokaciji(@RequestBody pretragaPoLokacijiDto pplDto){
        List<Restoran> listaRestorana = restoranService.findAll();

        List<Restoran> restorans = new ArrayList<>();

        for(Restoran restoran : listaRestorana){
            if(restoran.getTip().equals(pplDto.getLokacija())){
                restorans.add(restoran);
            }
        }
        return restorans;
    }

    @PostMapping("/api/restoran/dodaj_u_korpu")
    public ResponseEntity<String> dodajUKorpu(@RequestBody KorpaDto korpaDto, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan == null){
            return new ResponseEntity<>("Morate biti ulogovani da bi ste dodali artikle u korpu!", HttpStatus.BAD_REQUEST);
        }

        if(ulogovan.getUloga() != Uloga.KUPAC){
            return new ResponseEntity<>("Samo kupci mogu dodavat artikle u korpu!", HttpStatus.BAD_REQUEST);
        }

        if(korpaDto.getKolicina() <= 0){
            return new ResponseEntity<>("Morate poruciti 1 ili vise artikala!", HttpStatus.BAD_REQUEST);
        }

        //Implementirati

        return ResponseEntity.ok("Uspesno dodavanje artikla u korpu");

    }

    //Dodat prikaz restorana za sve korisnike
    @GetMapping("api/listaRestorana")
    public ResponseEntity<List<Restoran>> listaRestorana(HttpSession sesija){
        List<Restoran> restoraniZaPrikaz = restoranService.findAll();

        return ResponseEntity.ok(restoraniZaPrikaz);
    }
}
