package com.example.demo.configuration;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    private AdminRepository adminRepository;

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

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Bean
    public boolean instantiate(){
        TipKupca tip1 = new TipKupca("Zlatni", 6, 500);
        //tipKupcaRepository.save(tip1);
        TipKupca tip2 = new TipKupca("Srebrni", 4, 300);
        //tipKupcaRepository.save(tip2);

        Artikal artikal = new Artikal("Margarita", 800L, TipArtikla.JELO);
        Artikal artikal1 = new Artikal("Kapricoza", 950L, TipArtikla.JELO);
        Artikal artikal2 = new Artikal("Sremica", 900L, TipArtikla.JELO);
        Artikal artikal3 = new Artikal("Salami", 600L, TipArtikla.JELO);
        Artikal artikal4 = new Artikal("Italiana", 650L,  TipArtikla.JELO);
        Artikal pice = new Artikal("Cappuccino", 150L, TipArtikla.PICE);
        artikalRepository.save(artikal);
        artikalRepository.save(artikal1);
        artikalRepository.save(artikal2);
        artikalRepository.save(artikal3);
        artikalRepository.save(artikal4);
        artikalRepository.save(pice);

        Lokacija lokacija = new Lokacija((long) 32.112, (long) 69.420, "Hilandarska 2a");
        lokacijaRepository.save(lokacija);

        Set<Artikal> jelovnikCiao = new HashSet<>();
        Set<Artikal> jelovnikFoodic = new HashSet<>();
        jelovnikCiao.add(artikal);
        jelovnikCiao.add(artikal1);
        jelovnikCiao.add(artikal2);
        jelovnikCiao.add(artikal3);
        jelovnikCiao.add(artikal4);
        jelovnikCiao.add(pice);
        jelovnikFoodic.add(artikal);
        jelovnikFoodic.add(artikal1);
        jelovnikFoodic.add(artikal2);

        Restoran restoran = new Restoran("Picerija 'Ciao'", "Italijanski", lokacija);
        Restoran restoran2 = new Restoran("Foodic", "Fast-Food", lokacija);
        restoran.getArtikliUPonudi().add(artikal);
        restoran.getArtikliUPonudi().add(artikal1);
        restoran.getArtikliUPonudi().add(artikal2);
        restoran.getArtikliUPonudi().add(artikal3);
        restoran.getArtikliUPonudi().add(artikal4);
        restoran.getArtikliUPonudi().add(pice);
        restoran2.getArtikliUPonudi().add(artikal);
        restoran2.getArtikliUPonudi().add(artikal1);
        restoran2.getArtikliUPonudi().add(artikal2);


        restoran.setArtikliUPonudi(jelovnikCiao);
        restoran.setArtikliUPonudi(jelovnikFoodic);


        restoranRepository.save(restoran);
        restoranRepository.save(restoran2);

        Dostavljac dostavljac1 = new Dostavljac("Cveja", "cveja123", "Nikola", "Cvejic", Pol.MUSKO, new Date(2001, Calendar.MARCH, 20));
        dostavljac1.setUloga(Uloga.DOSTAVLJAC);
        dostavljacRepository.save(dostavljac1);
        Dostavljac dostavljac2 = new Dostavljac("Sunjalica", "nikola123", "Nikola", "Radovic", Pol.MUSKO, new Date(2001, Calendar.MAY, 12));
        dostavljac2.setUloga(Uloga.DOSTAVLJAC);

        Kupac kupac1 = new Kupac("dooka", "dooka1", "Teodora", "Zunic", Pol.ZENSKO, new Date(2002, Calendar.JULY, 4), 50, tip1);
        kupac1.setUloga(Uloga.KUPAC);
        kupacRepository.save(kupac1);
        Kupac kupac2 = new Kupac("tadiic", "tadiic1", "Ana", "Tadic", Pol.ZENSKO, new Date (2001, Calendar.MAY, 24), 20, tip2);
        kupac2.setUloga(Uloga.KUPAC);
        kupacRepository.save(kupac2);

        Komentar komentar1 = new Komentar(kupac1, restoran, 10, "Odlican restoran, najbolja pica u gradu! Sve preporuke <3");
        komentarRepository.save(komentar1);

        Menadzer menadzer1 = new Menadzer("cuka", "cuka123", "Nikola", "Kojic", Pol.MUSKO, new Date(2001, 12, 1));
        menadzer1.setRestoran(restoran);
        menadzer1.setUloga(Uloga.MENADZER);
        menadzer1.setRestoran(restoran);
        menadzerRepository.save(menadzer1);

        Admin adminNemanja = new Admin("nemkac", "nemkac123", "Nemanja", "Todorovic", Pol.MUSKO,new Date(2002, 1,18));
        Admin adminVladimir = new Admin("blanusha", "blanusha123", "Vladimir", "Blanusa", Pol.MUSKO,new Date(2002, 1,28));

        adminRepository.save(adminNemanja);
        adminRepository.save(adminVladimir);

        Korisnik k1 = new Korisnik("nemanjica", "nemanjica12", "Nemanja", "Ranitovic", Pol.MUSKO, new Date(2001, 8, 13));
        k1.setUloga(Uloga.KUPAC);
        korisnikRepository.save(k1);
        //korisnikRepository.save(dostavljac1);
        //korisnikRepository.save(dostavljac2);
        korisnikRepository.save(kupac1);
        korisnikRepository.save(kupac2);
        korisnikRepository.save(menadzer1);
        korisnikRepository.save(adminNemanja);
        korisnikRepository.save(adminVladimir);

        return true;
    }
}
