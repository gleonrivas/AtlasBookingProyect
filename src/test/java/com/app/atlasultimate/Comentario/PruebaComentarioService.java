package com.app.atlasultimate.Comentario;

import com.app.atlasultimate.Utilidades.UtilidadesFakerComentario;
import com.app.atlasultimate.model.Review;
import com.app.atlasultimate.repository.ReviewRepository;
import com.app.atlasultimate.service.ReviewService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;


public class PruebaComentarioService {

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewService reviewService;

    @Test
    public void guardarComentario(){

        //PREPARACIÓN
        Review reviewEsperada = UtilidadesFakerComentario.crearReview();
        Mockito.when(reviewRepository.save(any())).thenReturn(reviewEsperada);

        //EJECUCIÓN
       // Review reviewObtenida = reviewService.guardarReview(reviewEsperada);

        //COMPROBACIÓN
       // assertEquals("La review no se ha guardado", reviewEsperada, reviewObtenida);
    }

}
