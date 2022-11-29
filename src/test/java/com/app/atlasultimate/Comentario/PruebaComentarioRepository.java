package com.app.atlasultimate.Comentario;


import com.app.atlasultimate.Utilidades.UtilidadesFakerComentario;
import com.app.atlasultimate.model.Review;
import com.app.atlasultimate.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:db",
        "spring.jpa.properties.hibernate.default_schema=",
        "spring.jpa.hibernate.ddl-auto=update"
})

public class PruebaComentarioRepository {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void guardarComentarioTest(){
        //PREPARACION
        Review reviewEsperado = UtilidadesFakerComentario.crearReviewService();
        //EJECUCION
        Review reviewObtenido = reviewRepository.save(reviewEsperado);
        //COMPROBACION
        assertNotNull("No se ha guardado el comentario",reviewEsperado);
        assertEquals("Los comentarios no coinciden",reviewEsperado.getComentario(), reviewObtenido.getComentario());

    }
}



















/*assertArrayEquals	Sirve para comparar dos arreglos y afirmar distintas propiedades del mismo.
    assertEquals	Sirve para comparar dos tipos de datos u objetos y afirmar que son iguales.
    assertFalse	Sirve para afirmar que un tipo de dato u objeto es falso.
    assertNotNull	Sirve para afirmar que un tipo de dato u objeto no es nulo.
    assertNotSame	Sirve para comparar dos tipos de datos y afirmar que son distintos.
    assertNull	Sirve para afirmar que un tipo de dato u objeto es nulo.
    assertThat	Sirve para comparar un tipo de dato u objeto. A diferencia de los assertions normales este trabaja con Matcher
    assertTrue	Sirve para afirmar que un tipo de dato u objeto es verdadero.*/
