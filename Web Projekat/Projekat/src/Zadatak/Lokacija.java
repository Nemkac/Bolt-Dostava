package Zadatak;

public class Lokacija {
    private double geoDuzina;
    private double geoSirina;
    private String adresa;

    public Lokacija(double geoSirina, double geoDuzina, String adresa){
        this.geoSirina = geoSirina;
        this.geoDuzina = geoDuzina;
        this.adresa = adresa;
    }
    public double getGeoDuzina() {
        return geoDuzina;
    }

    public double getGeoSirina() {
        return geoSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setGeoDuzina(double geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public void setGeoSirina(double geoSirina) {
        this.geoSirina = geoSirina;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
