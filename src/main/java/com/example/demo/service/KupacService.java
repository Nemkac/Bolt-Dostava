package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.ArtikliUPorudzbiniRepository;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class KupacService {
    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private ArtikliUPorudzbiniRepository artikliUPorudzbiniRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public Set<Porudzbina> pregledajPorudzbine(Long id) {
        Kupac kupac = kupacRepository.getById(id);

        return kupac.getSvePorudzbine();
    }

    public Kupac findKupacById(Long id){
        return kupacRepository.getById(id);
    }

    public void updateKupacBodovi(Porudzbina p){
        Kupac k = kupacRepository.getById(p.getKupac().getId());
        int brojBodova = (int)(p.getCena()/1000 * 133);
        k.setBrBodova(k.getBrBodova() + brojBodova);
        kupacRepository.save(k);
    }

}
