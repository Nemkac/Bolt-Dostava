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

    /*//Pri kraju, Sta je lista naziva???
    public void napraviPorudzbinu(String restoran, Set<String> artikli, List<Integer> kolicina, Kupac ulogovanKupac) {
        List<Restoran> listaRestorana = restoranRepository.findAll();
        Porudzbina porudzbina = new Porudzbina();
        Restoran trenutniRestoran = new Restoran();
        ArtikliUPorudzbini tmp = new ArtikliUPorudzbini();

        for(Restoran rest : listaRestorana){
            if(rest.getNaziv().equals(restoran)){
                trenutniRestoran = rest;
            }
        }
        List<Artikal> listaPostojecihArtikala = new ArrayList<>(trenutniRestoran.getArtikliUPonudi());
        for(int i = 0; i < listaNaziva.size();i++){
            int j = 0;
            for(String naziv : listaNaziva){
                if(listaPostojecihArtikala.get(i).getName().equals(naziv)){
                    tmp.setArtikal(listaPostojecihArtikala.get(i));
                    tmp.setBroj(kolicine.get(j));
                    porudzbina.getListaArtikalaUPorudzbini().add(tmp);
                }
                j++;
            }
        }
        artikliUPorudzbiniRepository.save(tmp);
        porudzbina.setStatus(Status.OBRADA);
        porudzbina.setKupac(ulogovanKupac);
        porudzbina.setRestoran(trenutniRestoran);
        porudzbina.setDatum(new java.util.Date(System.currentTimeMillis()));
        porudzbinaRepository.save(porudzbina);
        ulogovanKupac.getSvePorudzbine().add(porudzbina);
        kupacRepository.save(ulogovanKupac);
    }*/
}
