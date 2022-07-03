package com.example.demo.dto;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Status;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class ListaPorudzbinaDostavljacaDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String UUID;

    private Date datum;

    private String ime;

    private String prezime;

    private Status status;

    public ListaPorudzbinaDostavljacaDto(Porudzbina p){
        this.UUID = p.getUUID();
        this.datum = p.getDatum();
        this.ime = p.getKupac().getIme();
        this.prezime = p.getKupac().getPrezime();
        this.status = p.getStatus();
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

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDatum() {
        return datum;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}

