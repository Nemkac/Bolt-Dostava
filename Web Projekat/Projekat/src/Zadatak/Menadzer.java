package Zadatak;

import java.util.Date;

public class Menadzer extends Korisnik{
    private Restoran restoran;

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, ULOGA.MENADZER);
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
