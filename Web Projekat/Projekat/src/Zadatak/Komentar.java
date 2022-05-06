package Zadatak;

public class Komentar {
    private Kupac kupac;
    private Restoran restoran;
    private String tekst;
    private int ocena;

    public Komentar(Kupac kupac, Restoran restoran, String tekst, int ocena) {
        this.kupac = kupac;
        this.restoran = restoran;
        this.tekst = tekst;

        if(ocena > 5 || ocena < 1){
            System.out.println("Uneliste neispravnu ocenu!\nOcena moze biti iz intervala od 1 do 5!");
        }else{
            this.ocena = ocena;
        }
    }
}
