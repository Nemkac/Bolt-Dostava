package com.example.demo.dto;

import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Status;

import java.util.Date;
import java.util.UUID;

public class ListaPorudzbinaDto {
    private String UUID;

    private Date datum;

    private PodaciDto dostavljac;

    private String nazivRestorana;

    private Status status;

    public ListaPorudzbinaDto(Porudzbina porudzbina){
        this.UUID = porudzbina.getUUID();
        this.datum = porudzbina.getDatum();
        this.dostavljac = new PodaciDto(porudzbina.getDostavljac().getIme(), porudzbina.getDostavljac().getPrezime());
        this.nazivRestorana = porudzbina.getRestoran().getNaziv();
        this.status = porudzbina.getStatus();
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Date getVremePorudzbine() {
        return datum;
    }

    public void setVremePorudzbine(Date datum) {
        this.datum = datum;
    }

    public PodaciDto getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(PodaciDto dostavljac) {
        this.dostavljac = dostavljac;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
