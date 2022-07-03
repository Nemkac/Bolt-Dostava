package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.ArtikalRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArtikalService {
    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private RestoranRepository restoranRepository;

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

    public Artikal findArtikalById(Long id){
        List<Artikal> artikli = artikalRepository.findAll();
        for (Artikal a : artikli) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public Artikal getArtikalByNaziv(String naziv, Long idRestorana){
        Restoran r = restoranRepository.getById(idRestorana);
        Set<Artikal> artikals = r.getArtikliUPonudi();
        for(Artikal a : artikals){
            if(a.getNaziv().equals(naziv))
                return a;
        }
        return null;
    }


}
