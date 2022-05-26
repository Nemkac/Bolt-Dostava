package com.example.demo.service;

import com.example.demo.entity.Porudzbina;
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
}
