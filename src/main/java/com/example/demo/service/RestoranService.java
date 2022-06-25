package com.example.demo.service;

import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Lokacija;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.LokacijaRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
