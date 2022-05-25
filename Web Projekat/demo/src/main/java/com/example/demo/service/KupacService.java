package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Menadzer;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KupacService {

    public Set<Porudzbina> pregledajPorudzbine(Long id) {
        Kupac kupac = kupacRepository.getById(id);

        return kupac.getPorudzbine();
    }



}

