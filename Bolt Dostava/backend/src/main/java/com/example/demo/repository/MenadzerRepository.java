package com.example.demo.repository;

import com.example.demo.entity.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer, Long> {
    Menadzer getByKorisnickoIme(String korisnickoIme);
    Menadzer findByKorisnickoIme(String korisnickoIme);
}
