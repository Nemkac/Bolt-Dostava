package com.example.demo.service;

import com.example.demo.entity.Komentar;
import com.example.demo.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KomentarService {

    @Autowired
    private KomentarRepository komentarRepository;

    public List<Komentar> getAllByRestoranId(Long restoranId) {
        return komentarRepository.getByRestoranId(restoranId);
    }

}
