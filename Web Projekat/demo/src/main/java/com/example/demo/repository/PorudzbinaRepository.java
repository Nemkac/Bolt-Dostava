package com.example.demo.repository;

import com.example.demo.entity.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {
    List<Porudzbina> getByRestoranId(Long restoranId);

    List<Porudzbina> getByKupacId(Long kupacId);

    List<Porudzbina> getByStatus(String status);
}
