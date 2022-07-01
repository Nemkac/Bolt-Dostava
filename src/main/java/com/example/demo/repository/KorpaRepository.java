package com.example.demo.repository;

import com.example.demo.entity.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KorpaRepository extends JpaRepository<Korpa, Long> {
}
