package Zadatak;

import java.util.ArrayList;

public class Restoran extends Lokacija{
    private String naziv;
    private String tip;
    private ArrayList<Artikal> artikli;
    private Lokacija lokacija;

    public Restoran(double geoSirina, double geoDuzina, String adresa, String naziv, String tip) {
        super(geoSirina, geoDuzina, adresa);
        this.naziv = naziv;
        this.tip = tip;
        this.artikli = new ArrayList<Artikal>();
    }

    public void setLokacija(double geoDuzina, double geoSirina, String adresa){
         this.lokacija.setGeoDuzina(geoDuzina);
         this.lokacija.setGeoSirina(geoSirina);
         this.lokacija.setAdresa(adresa);
    }
}
