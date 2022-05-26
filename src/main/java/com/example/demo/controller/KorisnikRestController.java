package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.RestoranService;
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
    public RestoranService restoranService;

    @PostMapping("/api/registracija")
    public ResponseEntity<String> registracija(@RequestBody KorisnikDto korisnikDto){
        if(korisnikDto.getKorisnickoIme().isEmpty() ||
            korisnikDto.getLozinka().isEmpty() ||
            korisnikDto.getIme().isEmpty() ||
            korisnikDto.getPrezime().isEmpty() ||
            korisnikDto.getPol() == null ||
            korisnikDto.getDatum() == null){
                return new ResponseEntity<>("Uneti podaci nisu ispravni!", HttpStatus.BAD_REQUEST);
        }

        Korisnik noviKorisnik =  korisnikService.registracija(korisnikDto.getKorisnickoIme(), korisnikDto.getLozinka(), korisnikDto.getIme(), korisnikDto.getPrezime(), korisnikDto.getPol(), korisnikDto.getDatum());
        noviKorisnik.setUloga(Uloga.KUPAC);
        if(noviKorisnik == null){
            return new ResponseEntity<>("Uneto korisnicko ime vec postoji!", HttpStatus.BAD_REQUEST);
        }

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

        return ResponseEntity.ok("Logovanje uspesno!");
    }

    @PostMapping("/api/logout")
    public ResponseEntity<String> logout(HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        sesija.invalidate();

        return ResponseEntity.ok("Uspesno ste se izlogovali");
    }
    //Napraviti fju za prikaz podataka svakog korisnika.

    //Treba proveriti zasto ne radi izmena u postmenu
    //Funkcija sama po sebi ne bi trebala da ima bilo kakve greske
    @PutMapping("/api/izmena_podataka")
    public ResponseEntity<String> izmenaPodataka(@RequestBody KorisnikDto korisnikDto, HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan == null){
            return new ResponseEntity<>("Trenutno niste ulogovani!", HttpStatus.BAD_REQUEST);
        }

        ulogovan.setKorisnickoIme(korisnikDto.getKorisnickoIme());
        ulogovan.setLozinka(korisnikDto.getLozinka());
        ulogovan.setIme(korisnikDto.getIme());
        ulogovan.setPrezime(korisnikDto.getPrezime());
        ulogovan.setPol(korisnikDto.getPol());
        ulogovan.setDatum(korisnikDto.getDatum());

        sesija.setAttribute("Korisnik", ulogovan);

        korisnikService.sacuvajKorisnika(ulogovan);
        return ResponseEntity.ok("Podaci uspesno izmenjeni!");
    }

    //Isto kao prethodna funkcija, trebalo bi da radi samo sto ne znam kako da podesim postmen da mi ispise dobro
    @GetMapping("/api/lista_korisnika")
    public ResponseEntity<List<KorisnikDto>> listaKorisnika(HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.ADMIN){
            return new ResponseEntity("Ova funkcionalnost dostupna je samo adminima!", HttpStatus.BAD_REQUEST);
        }

        List<Korisnik> korisnici = this.korisnikService.prikaziKorisnike();

        List<KorisnikDto> listaKorisnika = new ArrayList<>();
        for(Korisnik k : korisnici){
            KorisnikDto korisnikDto = new KorisnikDto(k);
            listaKorisnika.add(korisnikDto);
        }
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

    @GetMapping("/api/restoran/prikaz/{id}")
    public Optional<Restoran> prikaziRestoran(@PathVariable(name = "id") Long id){
        Optional<Restoran> restoran = Optional.ofNullable(restoranService.findById(id));

        return restoran;
    }
}

