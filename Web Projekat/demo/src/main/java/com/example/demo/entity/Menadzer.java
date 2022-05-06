package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Menadzer extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Restoran restoran;

    public Menadzer(){}

    public Menadzer(Long id, Restoran restoran) {
        this.id = id;
        this.restoran = restoran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "Menadzer{" +
                "id=" + id +
                ", restoran=" + restoran +
                '}';
    }
}
