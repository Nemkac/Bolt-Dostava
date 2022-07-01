package com.example.demo.dto;

import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;

import java.util.List;

public class MenadzerPrikazDto {

    private Restoran restoran;

    private List<PorudzbinaDto> porudzbinaList;

    public MenadzerPrikazDto(Menadzer menadzer, List<PorudzbinaDto> porudzbine){
        restoran = menadzer.getRestoran();
        this.porudzbinaList = porudzbine;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public List<PorudzbinaDto> getPorudzbinaList() {
        return porudzbinaList;
    }

    public void setPorudzbinaList(List<PorudzbinaDto> porudzbinaList) {
        this.porudzbinaList = porudzbinaList;
    }
}
