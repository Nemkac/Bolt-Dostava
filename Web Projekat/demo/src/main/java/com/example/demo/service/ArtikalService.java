package com.example.demo.service;

import com.example.demo.entity.Menadzer;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtikalService {
    @Autowired
    private ArtikalRepository ArtikalRepository;

    public Artikal save(Artikal artikal){
        return artikalRepository.save(artikal);
    }

    public Artikal findById(Long id) {
        return artikalRepository.findById(id);
    }

    public deleteById(Long id) {
        artikalRepository.deleteById(Id);
    }

}
