package com.example.pruebaTecnicaProteccion.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalTime;
import java.util.List;

@Document
@Data
public class Fibonacci {

    @Id
    private String id;
    @Field
    private LocalTime time;
    @Field
    private long base1;
    @Field
    private long base2;
    @Field
    private Integer qNumerosGenerada;
    @Field
    private List<Long> serieFibonacci;
}
