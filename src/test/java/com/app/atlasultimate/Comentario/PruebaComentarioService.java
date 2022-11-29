package com.app.atlasultimate.Comentario;

import com.app.atlasultimate.Utilidades.UtilidadesFakerComentario;
import com.app.atlasultimate.model.Review;
import com.app.atlasultimate.repository.ReviewRepository;
import com.app.atlasultimate.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PruebaComentarioService {

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewService reviewService;

   @Test
    public void guardarComentario(){

        //PREPARACIÓN
        Review reviewEsperada = UtilidadesFakerComentario.crearReviewService();
        Mockito.when(reviewRepository.save(any())).thenReturn(reviewEsperada);
        Review reviewDTO = new Review(
                reviewEsperada.getEstrellas(),
                reviewEsperada.getComentario(),
                reviewEsperada.getUsuario(),
               reviewEsperada.getHotel()
        );
        //EJECUCIÓN
        Review reviewObtenida = reviewService.guardar(reviewDTO);

        //COMPROBACIÓN
        assertEquals("La review no se ha guardado", reviewEsperada, reviewObtenida);
    }

}
