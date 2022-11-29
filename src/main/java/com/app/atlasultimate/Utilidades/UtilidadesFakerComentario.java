package com.app.atlasultimate.Utilidades;

import com.app.atlasultimate.controller.DTO.ReviewDTO;
import com.app.atlasultimate.model.Review;
import com.app.atlasultimate.service.ReviewService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class UtilidadesFakerComentario {

    @Autowired
    ReviewService reviewService;

    @Autowired
    UtilidadesFaker utilidadesFaker;

    @Autowired
    UtilidadesFakerUsuario utilidadesFakerUsuario;

    public static Review crearReviewService(){

        Faker faker = new Faker();

        int estrellas = (int)(Math.random()*10+1);
        if (estrellas > 6){
            estrellas = estrellas - 5;
        }

        return new Review(
                //(int)(Math.random()*10+1),
                estrellas,
                faker.weather().description(),
                UtilidadesFakerUsuario.crearUsuario(),
                UtilidadesFaker.crearHotel()
                );
    }

}
