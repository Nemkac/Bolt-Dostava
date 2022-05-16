package com.example.demo.configuration;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DatabaseConfiguration {

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
    private MenadzerRepository menadzerRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Bean
    public boolean instantiate(){
        TipKupca tip1 = new TipKupca("Zlatni", 6, 500);
        TipKupca tip2 = new TipKupca("Srebrni", 4, 300);

        Artikal artikal = new Artikal("Margarita", 800L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        Artikal artikal1 = new Artikal("Kapricoza", 950L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        Artikal artikal2 = new Artikal("Sremica", 900L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        Artikal artikal3 = new Artikal("Salami", 600L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        Artikal artikal4 = new Artikal("Italiana", 650L, 1F, "Prava italijanska pica", Artikal.Tip.JELO);
        Artikal pice = new Artikal("Cappuccino", 150L, 1F, "Prava italijanska kafa", Artikal.Tip.PICE);
        artikalRepository.save(artikal);
        artikalRepository.save(artikal1);
        artikalRepository.save(artikal2);
        artikalRepository.save(artikal3);
        artikalRepository.save(artikal4);
        artikalRepository.save(pice);

        Lokacija lokacija = new Lokacija((long) 32.112, (long) 69.420, "Hilandarska 2a");
        lokacijaRepository.save(lokacija);

        Set<Artikal> jelovnikCiao = new HashSet<>();
        jelovnikCiao.add(artikal);
        jelovnikCiao.add(artikal1);
        jelovnikCiao.add(artikal2);
        jelovnikCiao.add(artikal3);
        jelovnikCiao.add(artikal4);
        jelovnikCiao.add(pice);

        Restoran restoran = new Restoran("Picerija 'Ciao'", "Italijanski", jelovnikCiao, lokacija);
        restoran.getArtikliUPonudi().add(artikal);
        restoran.getArtikliUPonudi().add(artikal1);
        restoran.getArtikliUPonudi().add(artikal2);
        restoran.getArtikliUPonudi().add(artikal3);
        restoran.getArtikliUPonudi().add(artikal4);
        restoran.getArtikliUPonudi().add(pice);
        restoranRepository.save(restoran);

        restoran.setArtikliUPonudi(jelovnikCiao);

        Dostavljac dostavljac1 = new Dostavljac("Cveja", "cveja123", "Nikola", "Cvejic", Pol.MUSKO, LocalDate.of(2001, 05, 03));
        Dostavljac dostavljac2 = new Dostavljac("Sunjalica", "nikola123", "Nikola", "Radovic", Pol.MUSKO, LocalDate.of(2001, 04, 20));
        dostavljacRepository.save(dostavljac1);
        dostavljacRepository.save(dostavljac2);

        Kupac kupac1 = new Kupac("dooka", "dooka1", "Teodora", "Zunic", Pol.ZENSKO, LocalDate.of(2002, 01, 04), 50, tip1);
        kupacRepository.save(kupac1);
        Kupac kupac2 = new Kupac("tadiic", "tadiic1", "Ana", "Tadic", Pol.ZENSKO, LocalDate.of(2001, 05, 24), 20, tip2);
        kupacRepository.save(kupac2);

        Komentar komentar1 = new Komentar(kupac1, restoran, 10, "Odlican restora, najbolja pica u gradu! Sve preporuke <3");
        komentarRepository.save(komentar1);

        Menadzer menadzer1 = new Menadzer("cuka", "cuka123", "Nikola", "Kojic", Pol.MUSKO, LocalDate.of(2001, 05, 03),restoran);
        menadzerRepository.save(menadzer1);

        return true;
    }
}
