package com.example.pruebaTecnicaProteccion.controller;

import com.example.pruebaTecnicaProteccion.entity.Fibonacci;
import com.example.pruebaTecnicaProteccion.service.FibonacciService;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/fibonacci")
public class FibonacciController {

    private FibonacciService fibonacciService;

    public FibonacciController(FibonacciService fibonacciService){
        this.fibonacciService = fibonacciService;
    }

    @GetMapping
    public List<Fibonacci> getAllFibonacci(){

        List<Fibonacci> fibonacciList = new ArrayList<Fibonacci>();
        try {
            fibonacciList = fibonacciService.findAll();
        }
        catch (Exception ex){
            System.err.println("Error al consultar la Lista: " + ex.getMessage());
        }
        return fibonacciList;
    }

    @GetMapping("/generateAutomaticSerie")
    public Fibonacci generateAutomaticSerie(){
        Fibonacci generatedFibonacci = new Fibonacci();
        try {
            generatedFibonacci = fibonacciService.generateAutomaticSerie();
        }
        catch (Exception ex){
            System.err.println("Error al generar la serie automática: " + ex.getMessage());
        }
        return generatedFibonacci;
    }

    @GetMapping("/generateManualSerie")
    public Fibonacci generateManualSerie(@RequestParam("time") LocalTime time){
        Fibonacci generatedFibonacci = new Fibonacci();
        try {
            generatedFibonacci = fibonacciService.generateManualSerie(time);
        }
        catch (Exception ex){
            System.err.println("Error al generar la serie manual: " + ex.getMessage());
        }
        return generatedFibonacci;
    }
}
