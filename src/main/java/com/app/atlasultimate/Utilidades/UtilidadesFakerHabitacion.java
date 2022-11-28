package com.app.atlasultimate.Utilidades;


import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.service.HabitacionService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

public class UtilidadesFakerHabitacion {

    @Autowired
    HabitacionService habitacionService;

    public static Habitacion crearHabitacion(){

        int cero= 0;
        int uno = 1;

        Faker faker = new Faker();

        Habitacion hab = new Habitacion(
                (int)(Math.random()*10+1),
                uno,
                cero,
                uno,
                String.valueOf(uno),
                false,
                false,
                uno,
                uno,
                faker.name().firstName()
                );

        return hab;
    }
}
