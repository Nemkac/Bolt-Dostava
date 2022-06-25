package com.example.demo.service;

import com.example.demo.entity.Uloga;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    public boolean validacijaUloge(HttpSession sesija, Uloga uloga) {
        Object ulogaKorisnika = sesija.getAttribute("Uloga");

        if (ulogaKorisnika == null) {
            return false;
        }

        if (!ulogaKorisnika.toString().endsWith(uloga.toString())){
            return false;
        }

        return ulogaKorisnika.equals(uloga);
    }

    public String getKorisnickoIme(HttpSession sesija){
        Object korisnickoIme = sesija.getAttribute("korisnickoIme");

        if(korisnickoIme == null){
            return "";
        }

        if(korisnickoIme.toString().isEmpty()) {
            return "";
        }

        return korisnickoIme.toString();

    }

    public Uloga getUloga(HttpSession sesija){
        Object uloga = sesija.getAttribute("uloga");

        if(uloga == null){
            return null;
        }

        if(uloga.toString().isEmpty()){
            return null;
        }

        return (Uloga) uloga;
    }

    public boolean validacijaSesije(HttpSession sesija){
        if(sesija == null){
            return false;
        }

        String korisnickoIme = getKorisnickoIme(sesija);
        Uloga uloga = getUloga(sesija);

        if(korisnickoIme == null || korisnickoIme.isEmpty()){
            return false;
        }

        return true;
    }

}
