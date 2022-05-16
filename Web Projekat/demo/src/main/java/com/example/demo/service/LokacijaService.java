package com.example.demo.service;

import com.example.demo.entity.Lokacija;
import com.example.demo.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LokacijaService {

    @Autowired
    private LokacijaRepository lokacijaRepository;

    public Lokacija getLokacijaById(Long id){

        Optional<Lokacija> lokacija = lokacijaRepository.findById(id);

        if(lokacija.isPresent()){
            return lokacija.get();
        }

        return null;
    }
}