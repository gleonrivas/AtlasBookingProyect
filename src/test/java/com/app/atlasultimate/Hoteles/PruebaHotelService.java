package com.app.atlasultimate.Hoteles;

import com.app.atlasultimate.Utilidades.UtilidadesFaker;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.service.HotelService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.Silent.class)
public class PruebaHotelService {

    @Mock
    HotelRepository hotelRepository;

    @Mock
    UsuarioRepository usuarioRepository;

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
        Mockito.when(hotelRepository.findHotelById(hotelEsperado.getId())).thenReturn(hotelEsperado);

        //When
        Hotel hotelObtenido = hotelService.buscarHotel(hotelEsperado.getId());

        //Then
        assertEquals("El hotel no ha sido encontrado", hotelEsperado, hotelObtenido);

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
        assertNotEquals("los hoteles no son iguales", hotelEsperado,hotelEliminado);


    }

    @Test
    @DisplayName("Hotel Service -> Test editarHotel")
    public void editarHotel(){

        //Given
        Hotel hotelcreado = UtilidadesFaker.crearHotelconId();
        Mockito.when(hotelRepository.save(any())).thenReturn(hotelcreado);

        hotelcreado = UtilidadesFaker.editarHotel(hotelcreado);

        //When
        Hotel hotelCambiado = hotelService.actualizarHotel(hotelcreado);

        //Then
        assertNotEquals("El hotel no se ha editado",hotelcreado,hotelCambiado);

    }

    @Test
    @DisplayName("Hotel Service -> Test listarHotel")
    public void listarHotel(){

        //Given
        Usuario user = UtilidadesFaker.crearUsuario(1);
        Mockito.when(usuarioRepository.save(any())).thenReturn(user);
        List<Hotel> hotelescreado = UtilidadesFaker.litarHoteles(10,1);
        Mockito.when(hotelRepository.findHotelById_usuario(user.getId())).thenReturn(hotelescreado);
        //When
        List<Hotel> hotelesObtenidos = hotelService.listarHotel(1);

        //Then
        assertEquals("La lista de hoteles no es la misma",hotelescreado,hotelesObtenidos);
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
