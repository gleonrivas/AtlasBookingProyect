package com.app.atlasultimate.Usuario;

import com.app.atlasultimate.Utilidades.UtilidadesFaker;
import com.app.atlasultimate.Utilidades.UtilidadesFakerUsuario;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
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


public class PruebaUsuarioRepository {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void guardarUsuarioTest(){

        //Given
        Usuario usuarioEsperado = UtilidadesFakerUsuario.crearUsuario();

        //When
        Usuario usuarioObtenido = usuarioRepository.save(usuarioEsperado);

        //Then
        assertNotNull("No se ha guardado el usuario en base de datos",usuarioEsperado);
        assertEquals("Los usuarios no coinciden",usuarioEsperado.getEmail(), usuarioObtenido.getEmail());


    }


}
