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

    public Artikal saveArtikal(Artikal artikal){
        return artikalRepository.save(artikal);
    }

    public Artikal findOne(Long id){
        Optional<Artikal> artikal = artikalRepository.findById(id);

        if(artikal.isPresent()){
            return artikal.get();
        }

        return null;
    }

    public void deleteArtikal(Artikal artikal){
        artikalRepository.delete(artikal);
    }

}
