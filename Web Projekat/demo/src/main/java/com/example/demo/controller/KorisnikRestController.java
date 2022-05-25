package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Pol;
import com.example.demo.entity.Restoran;
import com.example.demo.entity.Uloga;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
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

        if(ulogovan == null){
            return new ResponseEntity<>("Korisnik trenutno nije ulogovan", HttpStatus.BAD_REQUEST);
        }

        sesija.invalidate();

        return ResponseEntity.ok("Uspesno ste se izlogovali");
    }
    //Napraviti fju za prikaz podataka svakog korisnika.

    //Treba proveriti zasto ne radi izmena u postmenu
    //Funkcija sama po sebi ne bi trebalo da ima bilo kakve greske
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
    public ResponseEntity<List<Korisnik>> listaKorisnika(HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan == null){
            return new ResponseEntity("Trenutno niste ulogovani!", HttpStatus.BAD_REQUEST);
        }
        if(ulogovan.getUloga() != Uloga.ADMIN){
            return new ResponseEntity("Ova funkcionalnost dostupna je samo adminima!", HttpStatus.BAD_REQUEST);
        }

        List<Korisnik> korisnici = korisnikService.prikaziKorisnike();

        return ResponseEntity.ok(korisnici);
    }

    @GetMapping("/api/lista_restorana")
    public List<Restoran> listaRestorana(){
        List<Restoran> listaRestorana = restoranService.findAll();
        return listaRestorana;
    }

    @GetMapping("api/pretragaPoNazivuRestorana")
    public ResponseEntity<List<RestoranDto>> pretragaPoNazivu(@RequestBody RestoranDto restoranDto){
        List<Restoran> restorani = restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran restoran : restorani){
            if(restoran.getNaziv().equals(restoranDto.getNaziv())){
                RestoranDto dto = new RestoranDto(restoran.getNaziv(),restoran.getTip(), restoran.getLokacija());
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("api/pretragaPoTipuRestorana")
    public ResponseEntity<List<RestoranDto>> pretragaPoTipuRestorana(@RequestBody RestoranDto restoranDto){
        List<Restoran> restorani = restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran restoran : restorani){
            if(restoran.getTip().equals(restoranDto.getTip())){
                RestoranDto dto = new RestoranDto(restoran.getNaziv(),restoran.getTip(), restoran.getLokacija());
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/pretragaPoLokaciji")
    public ResponseEntity<List<RestoranDto>> pretragaPoLokaciji(@RequestBody RestoranDto restoranDto){
        List<Restoran> restoraniList = restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran restoran : restoraniList){
            if(restoran.getLokacija().getAdresa().equals(restoranDto.getLokacija().getAdresa())){
                RestoranDto dto = new RestoranDto(restoran.getNaziv(),restoran.getTip(), restoran.getLokacija());
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/ispisIzabranogRestorana/{id}")
    public ResponseEntity<Restoran> ispisiRestoran(@PathVariable(name = "id") Long id){
        Restoran restoran = restoranService.findById(id);
        return ResponseEntity.ok(restoran);
    }

    // uloga ima posebnu klasu, nije string !!!
    @PostMapping("/dodavanjeArtikala")
    public ResponseEntity<String> dodavanje_artikala(@RequestBody ArtikalDto artikalDto, HttpSession sesija) {
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("korisnik");

        if(ulogovan.getUloga() == Uloga.MENADZER) {
            Menadzer menadzer = new Menadzer(ulogovan);
            Artikal artikal = new Artikal();
            Restoran restoran = menadzer.getRestoran();

            artikal.setNaziv(artikalDto.getNaziv());
            artikal.setCena(artikalDto.getCena());
            artikal.setTipArtikla(artikalDto.getTipArtikla());
            artikal.setOpis(artikalDto.getOpis());
            artikal.setKolicina(artikalDto.getKolicina());
            artikal.setRestoran(restoran);

            this.artikalService.save(artikal);
            return ResponseEntity.ok("Uspesno dodavanje artikla!");
        }
        return new ResponseEntity<>("Ulogovani korisnik nije menadzer!", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/api/izmenaArtikla/{id}")
    public ResponseEntity<String> izmenaArtikla(@PathVariable(name = "id") Long id,@RequestBody ArtikalDto artikalDto, HttpSession sesija) {
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("korisnik");
        if (ulogovan.getUloga().equals(Korisnik.Uloga.MENADZER)) {
            Artikal artikal = new ArtikalService.findById();
            Menadzer menadzer = new Menadzer(ulogovan);
            Restoran restoran = menadzer.getRestoran();
            artikal.setNaziv(artikalDto.getNaziv());
            artikal.setCena(artikalDto.getCena());
            artikal.setTip(artikalDto.getTipArtikla());
            artikal.setOpis(artikalDto.getOpis());
            artikal.setKolicina(artikalDto.getKolicina());
            artikal.setRestoran(restoran);
            this.artikalService.save(artikal);
            return ResponseEntity.ok("Uspesna izmena artikla!");
        }
        return new ResponseEntity<>("Ulogovani korisnik nije admin!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/obrisi_artikal/{id}")
    public ResponseEntity<String> brisanjeArtikla(@PathVariable(name = "id") Long id, HttpSession sesija) {
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("korisnik");
        if (ulogovan.getUloga().equals(Korisnik.Uloga.MENADZER)) {
            //treba nam delete po id, mms da je to jedini nacin
            return ResponseEntity.ok("Uspesno obrisan artikal !");
        }
        return new ResponseEntity<>("Ulogovani korisnik nije menadzer !", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/kupac/pregled-porudzbina")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession sesija)
    {
        Kupac ulogovani = (Kupac) sesija.getAttribute("kupac");

        Long id = ulogovani.getId();

        if(ulogovani==null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        Set<Porudzbina> porudzbine = kupacService.listaPorudzbina(id);

        return new ResponseEntity.ok(porudzbine);
    }

    @GetMapping("/api/dostavljac/dostupnePorudzbine")
    public ResponseEntity<Set<Porudzbina>> dostupne_porudzbine(HttpSession sesija)
    {
        Dostavljac ulogovani = (Dostavljac) sesija.getAttribute("dostavljac");

        Lond Id = ulogovani.getId();

        if(ulogovani == null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        //treva da preko servica restorana da pokupimo sve porudzbine koje cekaju dostavljac
        Set<Porudzbina> porudzbine = ...

        return ResponseEntity.ok(porudzbine);
    }

    @GetMapping("/api/dostavljac/njegovePorudzbine")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession sesija)
    {
        Dostavljac ulogovani = (Dostavljac) sesija.getAttribute("dostavljac");

        Long id = ulogovani.getId();

        if(ulogovani == null)
        {
            return new ResponseEntity("Niste ulogovani!",HttpStatus.FORBIDDEN);
        }

        //treba u service da se ubaci da preko id dostavljaca pokupimo sve njegove porudzbine
        Set<Porudzbina> porudzbine = ...

        return ResponseEntity.ok(porudzbine);
    }

    @PostMapping("/api/pregledajPorudzbineRestorana")
    public ResponseEntity<List<Porudzbina>> porudzbineMenadzera(HttpSession sesija){
        Korisnik ulogovani = (Korisnik) sesija.getAttribute("korisnik");
        if (ulogovani.getUloga() == Uloga.MENADZER)) {
            List<Restoran> restorani = restoranService.findAll();
            for(Restoran restoran : restorani) {
                if (restoran == ulogovani.getRestoran()) {
                    // mislio sam da postoji lista porudzbina u resoranu
                    return ResponseEntity.ok();
                }
            }
        }
        return new ResponseEntity<>("Ulogovani korisnik nije menadzer!", HttpStatus.BAD_REQUEST);

    }




}

