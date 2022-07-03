package com.example.demo.repository;

import com.example.demo.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran, Long> {
    Restoran getByNaziv(String naziv);

    Restoran getByLokacijaId(Long lokacija_id);
}
