package com.app.atlasultimate.Comentario;

import com.app.atlasultimate.Utilidades.UtilidadesFakerComentario;
import com.app.atlasultimate.controller.DTO.ReviewDTO;
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
        Review reviewEsperada = UtilidadesFakerComentario.crearReview();
        Mockito.when(reviewRepository.save(any())).thenReturn(reviewEsperada);
       ReviewDTO reviewDTO = new ReviewDTO(reviewEsperada.getUsuario().getNombre(),reviewEsperada.getComentario(),
               reviewEsperada.getEstrellas(),reviewEsperada.getUsuario(),reviewEsperada.getHotel());

        //EJECUCIÓN
        Review reviewObtenida = reviewService.guardarReview(reviewDTO);

        //COMPROBACIÓN
        assertEquals("La review no se ha guardado", reviewEsperada, reviewObtenida);
    }

}
