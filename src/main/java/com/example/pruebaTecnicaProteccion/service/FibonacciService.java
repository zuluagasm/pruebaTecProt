package com.example.pruebaTecnicaProteccion.service;


import com.example.pruebaTecnicaProteccion.entity.Fibonacci;

import java.time.LocalTime;
import java.util.List;

public interface FibonacciService {
    // Funciones Base de la Prueba
    public List<Fibonacci> findAll();
    public Fibonacci generateAutomaticSerie();
    public Fibonacci generateManualSerie(LocalTime time);
}

