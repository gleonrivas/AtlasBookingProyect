package com.app.atlasultimate.Usuario;

import com.app.atlasultimate.Utilidades.UtilidadesFaker;
import com.app.atlasultimate.Utilidades.UtilidadesFakerUsuario;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.UsuarioService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class PruebaUsuarioService {


    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioService usuarioService;


    @Test
    @DisplayName("Usuario Service -> Test guardar")
    public void guardarUsuario(){
        //Given
        Usuario usuarioEsperado = UtilidadesFakerUsuario.crearUsuario();
        Mockito.when(usuarioRepository.save(any())).thenReturn(usuarioEsperado);

        //When
        Usuario usuarioObtenido = usuarioService.guardarNoEncode(usuarioEsperado);

        //Then
        assertEquals("El hotel no se ha guardado",usuarioEsperado,usuarioObtenido);

    }








}
