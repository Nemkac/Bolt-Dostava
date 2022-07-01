package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private DostavljacRepository dostavljacRepository;


    public List<Porudzbina> porudzbineKupca(Long id) {
        return porudzbinaRepository.getByKupacId(id);
    }

    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }

    public List<Porudzbina> porudzbinePoRestoranu(Long id) {
        return porudzbinaRepository.findByRestoranId(id);
    }

    public Porudzbina findByStatus(Kupac kupac, Status obrada) {
        for(Porudzbina porudzbina : kupac.getSvePorudzbine()){
            if(porudzbina.getStatus() == obrada){
                return porudzbina;
            }
        }
        return new Porudzbina();
    }

    public void save(Porudzbina porudzbina){
        porudzbinaRepository.save(porudzbina);
    }

    public Artikal findArtikalById(Long id){
        return artikalService.findArtikalById(id);
    }

    public boolean isArtikalURestoranu(Long id1, Long id2){
        return restoranService.isArtikalURestoranu(id1, id2);
    }

    public Restoran findRestoranById(Long id){
        return restoranService.findById(id);
    }

    public void updateStauts(Status status, Long id){
        Porudzbina p = getPorudzbinaById(id);
        p.setStatus(status);
        porudzbinaRepository.save(p);
    }

    public Porudzbina getPorudzbinaById(Long id){
        Optional<Porudzbina> p = porudzbinaRepository.findById(id);
        if(p.isPresent()){
            return p.get();
        }
        return null;
    }

    public List<Porudzbina> getPorudzbineByRestoranId(Long id){
        List<Porudzbina> porudzbine = porudzbinaRepository.findAll();
        List<Porudzbina> lista = new ArrayList<>();
        for(Porudzbina p : porudzbine){
            if(p.getRestoran().getId() == id){
                lista.add(p);
            }
        }
        return lista;
    }

    public Set<Porudzbina> getPorudzbineUCekanju(){
        List<Porudzbina> porudzbines = porudzbinaRepository.findAll();
        Set<Porudzbina> setUCekanju = new HashSet<>();
        for(Porudzbina p : porudzbines){
            if(p.getStatus() == Status.CEKADOSTAVLJACA)
                setUCekanju.add(p);
        }
        return setUCekanju;
    }

    public void setDostaljacPorudzbine(Long idDostavljac, Long idPorudzbine){
        Dostavljac d = dostavljacRepository.getById(idDostavljac);
        Porudzbina p = getPorudzbinaById(idPorudzbine);
        p.setDostavljac(d);
        porudzbinaRepository.save(p);
        d.getPorudzbine().add(p);
        dostavljacRepository.save(d);
    }
}
