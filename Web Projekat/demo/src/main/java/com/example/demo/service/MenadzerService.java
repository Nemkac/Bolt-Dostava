package com.example.demo.service;

import com.example.demo.entity.Menadzer;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenadzerService {
    @Autowired
    private MenadzerRepository menadzerRepository;

}
