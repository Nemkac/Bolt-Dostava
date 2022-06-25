package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtikalService {
    @Autowired
    private ArtikalRepository artikalRepository;

    public void sacuvajArtikal(Artikal noviArtikal) {
        artikalRepository.save(noviArtikal);
    }

    public Artikal findById(Long id) {
        Optional<Artikal> pronadjenArtikal = artikalRepository.findById(id);
        if (pronadjenArtikal.isPresent())
            return pronadjenArtikal.get();
        return null;
    }

    public void obrisi(Artikal artikal) {
        artikalRepository.delete(artikal);
    }

    public Artikal findOne(Long id) {
        Optional<Artikal> pronadjenArtikal = artikalRepository.findById(id);
        if (pronadjenArtikal.isPresent()){
            return pronadjenArtikal.get();
        }
        return null;
    }
}
