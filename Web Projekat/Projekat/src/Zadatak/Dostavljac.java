package Zadatak;

import java.util.ArrayList;
import java.util.Date;

public class Dostavljac extends Korisnik{
    private ArrayList<Porudzbina> zaDostavu;

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, ULOGA.DOSTAVLJAC);
        this.zaDostavu = new ArrayList<>();
    }
}
