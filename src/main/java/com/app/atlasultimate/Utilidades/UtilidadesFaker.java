package com.app.atlasultimate.Utilidades;

import com.app.atlasultimate.model.Hotel;
import com.github.javafaker.Faker;

import java.sql.Time;
import java.util.Random;

public class UtilidadesFaker {

    private static Faker faker = new Faker();

    public static Hotel crearHotel(){
        Random random = new Random();
        int millisInDay = 24*60*60*1000;
        Time time = new Time((long)random.nextInt(millisInDay));
        Time time2 = new Time((long)random.nextInt(millisInDay));
        Integer estrellas = (int)(Math.random()*10+1);
        if (estrellas > 6){
            estrellas = estrellas - 5;
        }
        String nombre = faker.name().name();
        String email = nombre + "gmail.com";
        Time apertura = time;
        Time cierre = time2;

        Hotel hotel = new Hotel(nombre,faker.country().name(),faker.address().country(),faker.address().streetName(),
                estrellas,faker.number().numberBetween(1,9),email,faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(), faker.bool().bool(), faker.bool().bool(),faker.bool().bool(),time,time2,
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool());
        return hotel;
    }

}
