package com.example.demo.controller;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.KupacService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    //@PostMapping("/api/kupac/kreirajPorudzbinu")
    //public ResponseEntity<String> kreirajPorudzbinu(@RequestBody PorudzbinaDto porudzbinaDto, HttpSession sesija){


}
