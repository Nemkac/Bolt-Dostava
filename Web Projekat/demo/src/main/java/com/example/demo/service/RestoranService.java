package com.example.demo.service;

import com.example.demo.entity.Restoran;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    public Restoran save(Restoran restoran){
        return restoranRepository.save(restoran);
    }

    public Restoran findById(Long id){
        Optional<Restoran> restoran = restoranRepository.findById(id);

        if(restoran.isPresent()){
            return restoran.get();
        }

        return null;
    }

    public Restoran getByNaziv(String naziv){
        return restoranRepository.getByNaziv(naziv);
    }

    public Restoran getByLokacijaId(Long lokacija_id){
        return restoranRepository.getByLokacijaId(lokacija_id);
    }

    public List<Restoran> getAll(){
        return restoranRepository.findAll();
    }

    //Dodati pretragu po tipu
}
