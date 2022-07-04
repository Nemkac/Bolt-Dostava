package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.service.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    public Korisnik getKorisnikByKorisnickoIme(String korisnickoIme){
        List<Korisnik> listaKorisnika = korisnikService.prikaziKorisnike();
        for(Korisnik korisnik : listaKorisnika){
            if(korisnik.getKorisnickoIme().equals(korisnickoIme)){
                return korisnik;
            }
        }
        return null;
    }

    @PostMapping("/api/kreiraj_dostavljaca/{korisnickoIme}")
    public ResponseEntity<String> kreirajDostavljaca(@RequestBody KorisnikDto korisnikDtoDto,@PathVariable String korisnickoIme, HttpSession sesija){


        Admin ulogovanAdmin = adminRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanAdmin == null) {
            return new ResponseEntity<>("Nema ulogovanih admina!", HttpStatus.BAD_REQUEST);
        }
        else{
            adminService.napraviDostavljaca(korisnikDtoDto.getKorisnickoIme(), korisnikDtoDto.getLozinka(), korisnikDtoDto.getIme(), korisnikDtoDto.getPrezime(), korisnikDtoDto.getPol(), korisnikDtoDto.getDatum());
        }

        return ResponseEntity.ok("Dostavljac uspesno kreiran");
    }

    @PostMapping("/api/kreiraj_menadzera/{korisnickoIme}/{nazivRestorana}")
    public ResponseEntity<String> kreirajMenadzera(@RequestBody KorisnikDto korisnikDto,@PathVariable String korisnickoIme,@PathVariable String nazivRestorana, HttpSession sesija){

        Admin ulogovanAdmin = adminRepository.getByKorisnickoIme(korisnickoIme);
        Restoran restoran = restoranService.findByNaziv(nazivRestorana);

        if(ulogovanAdmin == null) {
            return new ResponseEntity<>("Nema ulogovanih admina!", HttpStatus.BAD_REQUEST);
        }
        else{
            Menadzer noviMenadzer = adminService.napraviMenadzera(korisnikDto.getKorisnickoIme(), korisnikDto.getLozinka(), korisnikDto.getIme(), korisnikDto.getPrezime(), korisnikDto.getPol(), korisnikDto.getDatum());
            if(noviMenadzer.getRestoran() == null){
                noviMenadzer.setRestoran(restoran);
                menadzerRepository.save(noviMenadzer);
            }
        }

        return ResponseEntity.ok("Menadzer uspesno kreiran");
    }

    @PutMapping("/api/menadzer/dodeliRestoran/{korisnickoIme}")
    public ResponseEntity<String> dodeliMenadzera(@RequestBody DodelaRestoranaDto drdto, @PathVariable String korisnickoIme, HttpSession session){

        Admin ulogovanAdmin = adminRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovanAdmin == null) {
            return new ResponseEntity<>("Nema ulogovanih admina!", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzerZaDodelu = menadzerService.findByUsername(drdto.getKorisnickoIme());
        Restoran restoran = restoranService.findByNaziv(drdto.getNaziv());

        if(menadzerZaDodelu.getRestoran() == null){
            menadzerZaDodelu.setRestoran(restoran);
        }
        else{
            return new ResponseEntity<>("Menadzer vec ima restoran!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Uspesno dodeljen menadzer!");
    }

    //Ispravljen prikaz svih korisnika kao admin
    @GetMapping("/api/pregledSvihKorisnika/{korisnickoIme}")
    public ResponseEntity<List<KorisnikDto>> listaSvihKorisnika(@PathVariable String korisnickoIme, HttpSession sesija){
        List<Korisnik> korisnici = korisnikService.prikaziKorisnike();

        Admin admin = adminRepository.getByKorisnickoIme(korisnickoIme);

        if(admin == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<KorisnikDto> dtos = new ArrayList<>();

        for(Korisnik korisnik : korisnici){
            KorisnikDto dto = new KorisnikDto(korisnik);
            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);
    }

    //Treba pogledati zasto nece da kreira sa menadzerom
    @PostMapping("/api/kreiraj_restoran/{korisnickoIme}")
    public ResponseEntity<String> kreirajRestoran(@RequestBody RestoranDto restoranDto, @PathVariable String korisnickoIme, HttpSession sesija) {
        if(restoranDto.getAdresa().isEmpty() ||
            restoranDto.getNaziv().isEmpty() ||
            restoranDto.getTip().isEmpty()){
            return new ResponseEntity<>("Pogresno uneti podaci!", HttpStatus.BAD_REQUEST);
        }

        Admin ulogovan = adminRepository.getByKorisnickoIme(korisnickoIme);

        if(ulogovan == null){
            return new ResponseEntity<>("Admin nije ulogovan!", HttpStatus.BAD_REQUEST);
        }
        else{
            adminService.napraviRestoran(restoranDto.getNaziv(), restoranDto.getTip(), restoranDto.getGeoDuzina(), restoranDto.getGeoSirina(), restoranDto.getAdresa(), false);

        }

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
