package com.example.pruebaTecnicaProteccion.service.impl;

import com.example.pruebaTecnicaProteccion.entity.Fibonacci;
import com.example.pruebaTecnicaProteccion.helpers.MailManager;
import com.example.pruebaTecnicaProteccion.repository.FibonacciRepository;
import com.example.pruebaTecnicaProteccion.service.FibonacciService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciServiceImpl implements FibonacciService {

    private FibonacciRepository fibonacciRepository;
    private MailManager mailManager;

    //Inyección de dependencia del repositorio por constructor.
    public FibonacciServiceImpl(FibonacciRepository fibonacciRepository, MailManager mailManager){
        this.fibonacciRepository = fibonacciRepository;
        this.mailManager = mailManager;
    }

    // Servicio privado para generar la serie.
    private Fibonacci generateFibonacci(LocalTime time){
        List<Long> fibonacciSerie = new ArrayList<>();
        Fibonacci fibonacciRegister = new Fibonacci();
        Integer minutes;
        Integer seconds;

        try{
            if (time != null){
                minutes = time.getMinute();
                seconds = time.getSecond();
                fibonacciRegister.setTime(time);
            }
            else {
                LocalTime baseTime = LocalTime.now();
                minutes = baseTime.getMinute();
                seconds = baseTime.getSecond();
                fibonacciRegister.setTime(baseTime);
            }

            // Desgloce de los minutos para establecer las bases
            long fibonacciBase1 = minutes/10;
            long fibonacciBase2 = minutes%10;

            fibonacciSerie.add(0, fibonacciBase1);
            fibonacciSerie.add(0, fibonacciBase2);

            for (int i = 0; i < seconds; i++){
                fibonacciSerie.add(0, fibonacciSerie.get(0) + fibonacciSerie.get(1));
            }

            fibonacciRegister.setBase1(fibonacciBase1);
            fibonacciRegister.setBase2(fibonacciBase2);
            fibonacciRegister.setQNumerosGenerada(seconds);
            fibonacciRegister.setSerieFibonacci(fibonacciSerie);
        }
        catch (Exception ex){
            System.err.println("Error al generar Fibonacci: " + ex.getMessage());
        }
        return fibonacciRegister;
    }

    private void sendEmail(String email, String subject, String message){
        mailManager.sendMail(email, subject, message);
    }

    @Override
    public List<Fibonacci> findAll() {
        List<Fibonacci> fibonacciList = new ArrayList<Fibonacci>();
        try {
            fibonacciList = fibonacciRepository.findAll();
        }
        catch(Exception ex){
            System.err.println("Error al generar Fibonacci: " + ex.getMessage());
        }
        return fibonacciList;
    }

    @Override
    public Fibonacci generateAutomaticSerie(){
        Fibonacci generatedSerie = new Fibonacci();
        try {
            //Generacion y guardado de la serie
            generatedSerie = generateFibonacci(null);
            fibonacciRepository.save(generatedSerie);
            //Envío de emails
            sendEmail("santimzuluaga01@hotmail.com", "Prueba Técnica - Santiago Zuluaga Hoyos - Generacion Hora Automatica",  "Hora Generacion: " + generatedSerie.getTime().toString() + "// Serie Generada" + generatedSerie.getSerieFibonacci().toString());
            sendEmail("didier.correa@proteccion.com.co", "Prueba Técnica - Santiago Zuluaga Hoyos - Generacion Hora Automatica",  "Hora Generacion: " + generatedSerie.getTime().toString() + "// Serie Generada" + generatedSerie.getSerieFibonacci().toString());
            sendEmail("correalondon@gmail.com", "Prueba Técnica - Santiago Zuluaga Hoyos - Generacion Hora Automatica",  "Hora Generacion: " + generatedSerie.getTime().toString() + "// Serie Generada" + generatedSerie.getSerieFibonacci().toString());
        }
        catch(Exception ex){
            System.err.println("Error al generar serie Fibonacci con hora del sistema: " + ex.getMessage());
        }
        return generatedSerie;
    }

    @Override
    public Fibonacci generateManualSerie(LocalTime time){
        Fibonacci generatedSerie = new Fibonacci();
        try {

            //Generacion y guardado de la serie
            generatedSerie = generateFibonacci(time);
            fibonacciRepository.save(generatedSerie);

            //Envío de emails
            sendEmail("santimzuluaga01@hotmail.com", "Prueba Técnica - Santiago Zuluaga Hoyos - Generacion Hora Manual",  "Hora Generacion: " + generatedSerie.getTime().toString() + "// Serie Generada" + generatedSerie.getSerieFibonacci().toString());
            sendEmail("didier.correa@proteccion.com.co", "Prueba Técnica - Santiago Zuluaga Hoyos - Generacion Hora Manual",  "Hora Generacion: " + generatedSerie.getTime().toString() + "// Serie Generada" + generatedSerie.getSerieFibonacci().toString());
            sendEmail("correalondon@gmail.com", "Prueba Técnica - Santiago Zuluaga Hoyos - Generacion Hora Manual",  "Hora Generacion: " + generatedSerie.getTime().toString() + "// Serie Generada" + generatedSerie.getSerieFibonacci().toString());
        }
        catch(Exception ex){
            System.err.println("Error al generar serie Fibonacci con hora externa: " + ex.getMessage());
        }
        return generatedSerie;
    }

}
