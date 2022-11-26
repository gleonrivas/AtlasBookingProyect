package com.app.atlasultimate.Hoteles;

import com.app.atlasultimate.Utilidades.UtilidadesFaker;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HotelService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class PruebaHotelService {

    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    HotelService hotelService;

    @Test
    @DisplayName("Hotel Service -> Test guardar")
    public void guardarHotel(){
        //Given
        Hotel hotelEsperado = UtilidadesFaker.crearHotel();
        Mockito.when(hotelRepository.save(any())).thenReturn(hotelEsperado);

        //When
        Hotel hotelObtenido = hotelService.guardarHotel(hotelEsperado);

        //Then
        assertEquals("El hotel no se ha guardado",hotelEsperado,hotelObtenido);

    }

    @Test
    @DisplayName("Hotel Service -> Test obtenerPorId")
    public void obtenerHotelPorId(){

        //Given
        Hotel hotelEsperado = UtilidadesFaker.crearHotelconId() ;
        Mockito.when(hotelRepository.save(any())).thenReturn(hotelEsperado);

        //When
        Hotel hotelObtenido = hotelService.buscarHotel(hotelEsperado.getId());

        //Then
        assertNotEquals("El hotel no ha sido encontrado",hotelEsperado,hotelObtenido);

    }

    @Test
    @DisplayName("Hotel Service -> Test eliminarHotel")
    public void eliminarHotel(){

        //Given
        Hotel hotelEsperado = UtilidadesFaker.crearHotelconId();
        Integer id = hotelEsperado.getId();
        Mockito.when(hotelRepository.save(any())).thenReturn(hotelEsperado);

        //When
        Hotel hotelEliminado = hotelService.eliminarHotel(id);

        //Then
        assertNull(hotelEliminado);

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
