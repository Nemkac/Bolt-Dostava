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

    public void sacuvajRestoran(Restoran noviRestoran) {
        restoranRepository.save(noviRestoran);
    }

    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }

    public Restoran findById(Long id){
        Optional<Restoran> foundRestoran = restoranRepository.findById(id);
        if (foundRestoran.isPresent())
            return foundRestoran.get();

        return null;
    }

}
