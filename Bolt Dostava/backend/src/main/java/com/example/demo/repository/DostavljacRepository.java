package com.example.demo.repository;

import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DostavljacRepository extends JpaRepository<Dostavljac, Long> {
    Dostavljac findByKorisnickoIme(String korisnickoIme);
}
