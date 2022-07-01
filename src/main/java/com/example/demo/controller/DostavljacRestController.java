package com.example.demo.controller;

import com.example.demo.dto.ListaPorudzbinaDostavljacaDto;
import com.example.demo.entity.*;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.service.DostavljacService;
import com.example.demo.service.KupacService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@RestController
public class DostavljacRestController {

    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private PorudzbinaService porudzbineService;

    @Autowired
    private KupacService kupacService;

    @GetMapping("/api/dostavljac")
    public ResponseEntity<Set<ListaPorudzbinaDostavljacaDto>> listaPorudzbina(HttpSession session){
        Dostavljac loggedDostavljac = (Dostavljac) session.getAttribute("Korisnik");
        if(loggedDostavljac == null){
            return new ResponseEntity("Nema ulogovanih dostavljaca!",HttpStatus.METHOD_NOT_ALLOWED);
        }
        if(loggedDostavljac.getUloga() != Uloga.DOSTAVLJAC){
            return new ResponseEntity("Ova funkcionalnost dostupna je samo dostavljacima",HttpStatus.METHOD_NOT_ALLOWED);
        }

        Dostavljac dostavljac = dostavljacService.findDostavljacById(loggedDostavljac.getId());
        Set<Porudzbina> porudzbines = dostavljac.getPorudzbine();
        Set<ListaPorudzbinaDostavljacaDto> porudzbineDto = new HashSet<>();
        for(Porudzbina p : porudzbines){
            ListaPorudzbinaDostavljacaDto pdto = new ListaPorudzbinaDostavljacaDto(p);
            porudzbineDto.add(pdto);
        }
        Set<Porudzbina> porudzbineUCekanju = porudzbineService.getPorudzbineUCekanju();
        for(Porudzbina p : porudzbineUCekanju){
            ListaPorudzbinaDostavljacaDto pdto = new ListaPorudzbinaDostavljacaDto(p);
            porudzbineDto.add(pdto);
        }

        if(porudzbineDto.isEmpty())
            return new ResponseEntity("Nema trenutno porudzbina", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(porudzbineDto);
    }

    @PutMapping("api/dostavljac/promeniStatusUTransportu/{idPorudzbine}")
    public ResponseEntity<String> promeniStatusUTransportu(@PathVariable(name = "idPorudzbine") Long idPorudzbine, HttpSession session){
        Dostavljac loggedDostavljac = (Dostavljac) session.getAttribute("Korisnik");
        if(loggedDostavljac == null){
            return new ResponseEntity("Nema ulogovanih dostavljaca!",HttpStatus.METHOD_NOT_ALLOWED);
        }
        if(loggedDostavljac.getUloga() != Uloga.DOSTAVLJAC){
            return new ResponseEntity("Ova funkcionalnost dostupna je samo dostavljacima",HttpStatus.METHOD_NOT_ALLOWED);
        }


        Porudzbina p = porudzbineService.getPorudzbinaById(idPorudzbine);
        if(p == null)
            return new ResponseEntity("Nepostojeca porudzbina!", HttpStatus.BAD_REQUEST);
        if(p.getStatus() != Status.CEKADOSTAVLJACA)
            return new ResponseEntity("Status porudzbine mora biti - CEKADOSTAVLJACA", HttpStatus.BAD_REQUEST);


        porudzbineService.updateStauts(Status.UTRANSPORTU, idPorudzbine);
        porudzbineService.setDostaljacPorudzbine(loggedDostavljac.getId(), idPorudzbine);

        return ResponseEntity.ok("Uspesno azuriran status. Status = U_TRANSPORTU");
    }

    @PutMapping("api/dostavljac/promeniStatusUDostavljena/{idPorudzbine}")
    public ResponseEntity<String> promeniStatusUDostavljena(@PathVariable(name = "idPorudzbine") Long idPorudzbine, HttpSession session){
        Dostavljac loggedDostavljac = (Dostavljac) session.getAttribute("Korisnik");
        if(loggedDostavljac == null){
            return new ResponseEntity("Nema ulogovanih dostavljaca!",HttpStatus.METHOD_NOT_ALLOWED);
        }
        if(loggedDostavljac.getUloga() != Uloga.DOSTAVLJAC){
            return new ResponseEntity("Ova funkcionalnost dostupna je samo dostavljacima",HttpStatus.METHOD_NOT_ALLOWED);
        }


        Porudzbina p = porudzbineService.getPorudzbinaById(idPorudzbine);
        if(p == null)
            return new ResponseEntity("Ne postoji porudzbina", HttpStatus.BAD_REQUEST);
        if(p.getStatus() != Status.UTRANSPORTU)
            return new ResponseEntity("Status porudzbine nije: U_TRANSPORTU", HttpStatus.BAD_REQUEST);


        porudzbineService.updateStauts(Status.DOSTAVLJENA, idPorudzbine);
        kupacService.updateKupacBodovi(p);

        return ResponseEntity.ok("Uspesno azuriran status. Status = DOSTAVLJENA");
    }
}
