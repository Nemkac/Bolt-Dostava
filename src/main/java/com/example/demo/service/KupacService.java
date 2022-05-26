package com.example.demo.service;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class KupacService {
    @Autowired
    private KupacRepository kupacRepository;

    public Set<Porudzbina> pregledajPorudzbine(Long id) {
        Kupac kupac = kupacRepository.getById(id);

        return kupac.getSvePorudzbine();
    }
}
