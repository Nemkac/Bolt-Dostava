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
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MenadzerRepository menadzerRepository;
    @Autowired
    private DostavljacRepository dostavljacRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Menadzer napraviMenadzera(Menadzer menadzer){
        menadzerRepository.save(menadzer);
        korisnikRepository.save(menadzer);

        return menadzer;
    }

    public Dostavljac napraviDostavljaca(Dostavljac dostavljac){
        dostavljacRepository.save(dostavljac);
        korisnikRepository.save(dostavljac);

        return dostavljac;
    }

}

