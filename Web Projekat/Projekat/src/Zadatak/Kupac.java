package Zadatak;


import java.util.ArrayList;
import java.util.Date;

public class Kupac extends Korisnik{
    private ArrayList<String> porudzbine;
    private int brSakupljenihBodova;
    enum TIP {Zlatni, Srebrni, Bronzani};
    private int procenatPopusta;
    private TIP tip;

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, int brSakupljenihBodova) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, ULOGA.KUPAC);
        this.porudzbine = new ArrayList<>();

        if(brSakupljenihBodova > 300){
            this.tip = TIP.Zlatni;
        }
        else if(brSakupljenihBodova > 100 && brSakupljenihBodova < 300){
            this.tip = TIP.Srebrni;
        }
        else if(brSakupljenihBodova > 0 && brSakupljenihBodova < 100){
            this.tip = TIP.Bronzani;
        }
    }

    public void generisiPopust(){
        switch (this.tip) {
            case Zlatni -> procenatPopusta = 7;
            case Srebrni -> procenatPopusta = 5;
            case Bronzani -> procenatPopusta = 3;
        }
    }


}
