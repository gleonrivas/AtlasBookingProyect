package com.app.atlasultimate.Utilidades;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HotelService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.List;
import java.util.Random;

public class UtilidadesFaker {

    private static Faker faker = new Faker();

    @Autowired
    HotelService hotelService;


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
    public static Hotel crearHotelconId(){

        Random random = new Random();
        Integer id = (int)(Math.random()*10+1);
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

        Hotel hotel = new Hotel(id,nombre,faker.country().name(),faker.address().country(),faker.address().streetName(),
                estrellas,faker.number().numberBetween(1,9),email,faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(), faker.bool().bool(), faker.bool().bool(),faker.bool().bool(),time,time2,
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),faker.bool().bool(),
                faker.bool().bool(),faker.bool().bool(),faker.bool().bool());


        return hotel;
    }

    public static List<Hotel> litarHoteles(Integer num_hoteles){
        List<Hotel> lista = null;

        for(int i=0; i==num_hoteles; i++){
            Hotel hotel = crearHotel();
            lista.add(hotel);
        }
        return lista;
    }


}
