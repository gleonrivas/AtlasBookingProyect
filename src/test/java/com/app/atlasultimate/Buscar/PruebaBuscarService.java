package com.app.atlasultimate.Buscar;

import com.app.atlasultimate.Utilidades.UtilidadesFaker;
import com.app.atlasultimate.controller.DTO.HotelBusquedaDTO;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.service.HotelService;
import jdk.jshell.execution.Util;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PruebaBuscarService {


    @Mock
    HotelRepository hotelRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    HotelService hotelService;

    @Test
    @DisplayName("Hotel Service -> Test buscar Hoteles")
    public void busquedaHoteles(){
        //Given
        HotelBusquedaDTO busqueda = UtilidadesFaker.busqueda();
        List<Hotel> hoteles1 = hotelRepository.primerBuscador(busqueda.getFecha_inicio(), busqueda.getFecha_fin(), busqueda.getCiudad(), busqueda.getN_max_personas());
        List<Hotel> hoteles2 = hotelRepository.segundoBuscador(busqueda.getCiudad(), busqueda.getN_max_personas());
        hoteles1.addAll(hoteles2);
        List<Hotel> hotelesEsperado = hoteles1;

        //When
        List<Hotel> hotelesObtenidos = hotelService.buscadorcompleto(busqueda.getFecha_inicio(), busqueda.getFecha_fin(), busqueda.getCiudad(), busqueda.getN_max_personas());
        //Then
        assertEquals("Los hoteles no han sido encontrado", hotelesEsperado, hotelesObtenidos);
    }


}
