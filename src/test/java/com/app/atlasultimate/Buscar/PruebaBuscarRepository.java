package com.app.atlasultimate.Buscar;

import com.app.atlasultimate.Utilidades.UtilidadesFaker;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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



public class PruebaBuscarRepository {

    @Autowired
    HotelRepository hotelRepository;

    @Test
    public void buscarHotelesTest(){

        //Given
        Hotel hotelEsperado = UtilidadesFaker.crearHotel();

        //When
        Hotel hotelObtenido = hotelRepository.save(hotelEsperado);

        //Then
        assertNotNull("No se ha guardado el hotel en base de datos",hotelEsperado);
        assertEquals("Los hoteles no coinciden",hotelEsperado.getNombre(), hotelObtenido.getNombre());


    }


}
