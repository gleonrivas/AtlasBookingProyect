package com.app.atlasultimate.Reserva;


import com.app.atlasultimate.Utilidades.UtilidadesFakerRegistro;
import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.repository.RegistroPasadoRepository;
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

public class PruebaReservaRepository {

    @Autowired
    RegistroPasadoRepository registroPasadoRepository;

    @Test
    public void guardarReservaTest(){
        Registro registroEsperado = UtilidadesFakerRegistro.crearRegistroTest();

        //Registro registroObtenido = registroPasadoRepository.save(registroEsperado);

       // assertNotNull("No se ha guardado la reserva",registroEsperado);
       // assertEquals("Las reservas no coinciden",registroEsperado.getCodigo(), registroObtenido.getCodigo());

    }















    /*assertArrayEquals	Sirve para comparar dos arreglos y afirmar distintas propiedades del mismo.
    assertEquals	Sirve para comparar dos tipos de datos u objetos y afirmar que son iguales.
    assertFalse	Sirve para afirmar que un tipo de dato u objeto es falso.
    assertNotNull	Sirve para afirmar que un tipo de dato u objeto no es nulo.
    assertNotSame	Sirve para comparar dos tipos de datos y afirmar que son distintos.
    assertNull	Sirve para afirmar que un tipo de dato u objeto es nulo.
    assertThat	Sirve para comparar un tipo de dato u objeto. A diferencia de los assertions normales este trabaja con Matcher
    assertTrue	Sirve para afirmar que un tipo de dato u objeto es verdadero.*/
}
