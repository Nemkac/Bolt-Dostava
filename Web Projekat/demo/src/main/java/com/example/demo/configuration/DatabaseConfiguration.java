package com.example.demo.configuration;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Bean
    public boolean instantiate(){
        Korisnik korisnik = new Korisnik("nemkac", "nemkac123", "Nemanja", "Todorovic", Korisnik.Pol.DRUGO, Korisnik.Uloga.ADMIN, new java.util.Date(System.currentTimeMillis()));
        korisnikRepository.save(korisnik);
        Korisnik korisnik1 = new Korisnik("blanusha", "blanusha123", "Vladimir", "Blanusa", Korisnik.Pol.MUSKO, Korisnik.Uloga.DOSTAVLJAC, new java.util.Date(System.currentTimeMillis()));
        korisnikRepository.save(korisnik1);
        Korisnik korisnik2 = new Korisnik("nemanjica", "nemanjica123", "Nemanja", "Ranitovic", Korisnik.Pol.ZENSKO, Korisnik.Uloga.KUPAC, new java.util.Date(System.currentTimeMillis()));
        korisnikRepository.save(korisnik2);

        TipKupca tip1 = new TipKupca(TipKupca.Ime.ZLATNI, 6, 500);
        tipKupcaRepository.save(tip1);

        /*Kupac kupac = new Kupac(korisnik, 500);
        kupacRepository.save(kupac);*/

        Artikal artikal = new Artikal("Margarita", 800L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        Artikal artikal1 = new Artikal("Kapricoza", 950L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        Artikal artikal2 = new Artikal("Sremica", 900L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        Artikal artikal3 = new Artikal("Salami", 600L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        artikalRepository.save(artikal);
        artikalRepository.save(artikal1);
        artikalRepository.save(artikal2);
        artikalRepository.save(artikal3);

        Lokacija lokacija = new Lokacija((long) 32.112, (long) 69.420, "Hilandarska 2a");
        lokacijaRepository.save(lokacija);

        Restoran restoran = new Restoran("Picerija 'Ciao'", "Italijanski");
        restoran.getArtikliUPonudi().add(artikal);
        restoran.getArtikliUPonudi().add(artikal1);
        restoran.getArtikliUPonudi().add(artikal2);
        restoran.getArtikliUPonudi().add(artikal3);
        restoranRepository.save(restoran);


        return true;
    }
}
