package Zadatak;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Porudzbina {
    private String UUID;    //Jedinstveni identifikator narudzbine
    private ArrayList<Artikal> poruceniArtikli;
    private Restoran restoran;
    private LocalDate datum;
    private LocalTime vreme;
    private float cena;
    enum STATUS {Obrada, uPripremi, cekaDostavljaca, uTransportu, Dostavljena, Otkazana};
    private Kupac kupac;
    private String korisnickoImeKupca;
    private int brPorucenihArtikala;
    private STATUS status;

    public Porudzbina(String UUID, Restoran restoran, float cena, STATUS status, Kupac kupac) {
        this.UUID = UUID;
        this.restoran = restoran;
        this.cena = cena;
        this.status = status;
        this.kupac = kupac;
        this.poruceniArtikli = new ArrayList<>();
        this.korisnickoImeKupca = kupac.getKorisnickoIme();
        this.datum = LocalDate.now();
        this.vreme = LocalTime.now();
    }

    public void dodajArtikal(Artikal artikal){
        poruceniArtikli.add(artikal);
        brPorucenihArtikala ++;
        cena = cena + artikal.getCena();
    }

    public void ukloniArtikal(Artikal artikal){
        poruceniArtikli.remove(artikal);
        brPorucenihArtikala --;
        cena = cena - artikal.getCena();
    }
}
