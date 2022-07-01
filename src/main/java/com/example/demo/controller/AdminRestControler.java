package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.AdminRepository;
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

    @PostMapping("/api/kreiraj_dostavljaca")
    public ResponseEntity<String> kreirajDostavljaca(@RequestBody KorisnikDto korisnikDtoDto, HttpSession sesija){
        if(!sessionService.validacijaSesije(sesija)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Admin ulogovanAdmin = adminRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(sesija));

        if(ulogovanAdmin == null) {
            return new ResponseEntity<>("Nema ulogovanih admina!", HttpStatus.BAD_REQUEST);
        }
        else{
            adminService.napraviDostavljaca(korisnikDtoDto.getKorisnickoIme(), korisnikDtoDto.getLozinka(), korisnikDtoDto.getIme(), korisnikDtoDto.getPrezime(), korisnikDtoDto.getPol(), korisnikDtoDto.getDatum());
        }

        return ResponseEntity.ok("Dostavljac uspesno kreiran");
    }

    @PostMapping("/api/kreiraj_menadzera")
    public ResponseEntity<String> kreirajMenadzera(@RequestBody KorisnikDto korisnikDto, HttpSession sesija){
        if(!sessionService.validacijaSesije(sesija)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Admin ulogovanAdmin = adminRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(sesija));

        if(ulogovanAdmin == null) {
            return new ResponseEntity<>("Nema ulogovanih admina!", HttpStatus.BAD_REQUEST);
        }
        else{
            adminService.napraviMenadzera(korisnikDto.getKorisnickoIme(), korisnikDto.getLozinka(), korisnikDto.getIme(), korisnikDto.getPrezime(), korisnikDto.getPol(), korisnikDto.getDatum());
        }

        return ResponseEntity.ok("Menadzer uspesno kreiran");
    }

    //Ispravljen prikaz svih korisnika kao admin
    @GetMapping("/api/pregledSvihKorisnika")
    public ResponseEntity<List<KorisnikDto>> listaSvihKorisnika(HttpSession sesija){
        List<Korisnik> korisnici = korisnikService.prikaziKorisnike();

        if(!sessionService.validacijaSesije(sesija)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(sesija));

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
    @PostMapping("/api/kreiraj_restoran")
    public ResponseEntity<String> kreirajRestoran(@RequestBody RestoranDto restoranDto, HttpSession sesija) {
        if(restoranDto.getAdresa().isEmpty() ||
            restoranDto.getNaziv().isEmpty() ||
            restoranDto.getTip().isEmpty()){
            return new ResponseEntity<>("Pogresno uneti podaci!", HttpStatus.BAD_REQUEST);
        }

        if(!sessionService.validacijaSesije(sesija)){
            return new ResponseEntity<>("Nema sesije", HttpStatus.FORBIDDEN);
        }

        Admin ulogovan = adminRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(sesija));

        if(ulogovan == null){
            return new ResponseEntity<>("Admin nije ulogovan!", HttpStatus.BAD_REQUEST);
        }
        else{
            adminService.napraviRestoran(restoranDto.getNaziv(), restoranDto.getTip(), restoranDto.getGeoDuzina(), restoranDto.getGeoSirina(), restoranDto.getAdresa(), restoranDto.getMenadzer(), restoranDto.isStatus());

        }

        return ResponseEntity.ok("Restoran uspesno kreiran!");

    }

    @PutMapping("/api/menadzer/dodeliRestoran")
    public ResponseEntity<String> dodeliMenadzera(@RequestBody DodelaRestoranaDto drdto, HttpSession session){
        if(!sessionService.validacijaSesije(session)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Admin ulogovanAdmin = adminRepository.getByKorisnickoIme(sessionService.getKorisnickoIme(session));

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

    /*@PostMapping("/api/obrisi_restoran")
    public ResponseEntity<String> obrisiRestoran(HttpSession sesija){
        Korisnik ulogovan = (Korisnik) sesija.getAttribute("Korisnik");

        if(ulogovan.getUloga() != Uloga.ADMIN){
            return new ResponseEntity<>("Ulogovani korisnik nije admin!", HttpStatus.BAD_REQUEST);
        }

        restoranService.obrisiRestoran()
    }*/

}
