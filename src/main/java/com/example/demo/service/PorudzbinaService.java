package com.example.demo.service;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Status;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorudzbinaService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;


    public List<Porudzbina> porudzbineKupca(Long id) {
        return porudzbinaRepository.getByKupacId(id);
    }

    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }

    public List<Porudzbina> porudzbinePoRestoranu(Long id) {
        return porudzbinaRepository.findByRestoranId(id);
    }

    public Porudzbina findByStatus(Kupac kupac, Status obrada) {
        for(Porudzbina porudzbina : kupac.getSvePorudzbine()){
            if(porudzbina.getStatus() == obrada){
                return porudzbina;
            }
        }
        return new Porudzbina();
    }

    public void save(Porudzbina porudzbina){
        porudzbinaRepository.save(porudzbina);
    }
}
