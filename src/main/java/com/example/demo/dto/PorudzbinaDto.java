package com.example.demo.dto;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Restoran;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class PorudzbinaDto {
    private UUID uuid;
    private Date datum;
    private Restoran restoran;
    private Set<Artikal> poruceniArtikli;

    public PorudzbinaDto(UUID uuid, Date datum, Restoran restoran, Set<Artikal> poruceniArtikli) {
        this.uuid = uuid;
        this.datum = datum;
        this.restoran = restoran;
        this.poruceniArtikli = poruceniArtikli;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Set<Artikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }
}
