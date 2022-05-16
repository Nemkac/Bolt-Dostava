package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik login(String korisnickoIme, String password){
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(korisnik == null || !korisnik.getLozinka().equals(password)){
            return null;
        }

        return korisnik;
    }

    public Korisnik getByUsername(String korisnickoIme){
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        return korisnik;
    }

    public Korisnik register(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public List<Korisnik> findAll(){
        return korisnikRepository.findAll();
    }

    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public Korisnik findOne(Long id){
        Optional<Korisnik> korisnik = korisnikRepository.findById(id);

        if(korisnik.isPresent()){
            return korisnik.get();
        }

        return null;
    }
}
