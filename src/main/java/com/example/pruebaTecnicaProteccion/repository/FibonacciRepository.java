package com.example.pruebaTecnicaProteccion.repository;

import com.example.pruebaTecnicaProteccion.entity.Fibonacci;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FibonacciRepository extends MongoRepository<Fibonacci, String> {
}
