package com.app.atlasultimate.Habitaciones;

import com.app.atlasultimate.Utilidades.UtilidadesFaker;
import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HabitacionRepository;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HabitacionService;
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
public class PruebaHabitacionService {
    @Mock
    HabitacionRepository habitacionRepository;

    @InjectMocks
    HabitacionService habitacionService;


    @Test
    @DisplayName("Habitacion Service -> Test listarHabitacion")
    public void listarHabitacion(){

        //Given
        List<Habitacion> habitaciones = UtilidadesFaker.litarHabitacion(10);
        Mockito.when(habitacionRepository.findAll()).thenReturn(habitaciones);

        //When
        List<Habitacion> habitacionesObtenidas = habitacionService.listarHabitacion();

        //Then
        assertEquals("La lista de habitaciones no es la misma",habitaciones,habitacionesObtenidas);

    }

    @Test
    @DisplayName("Habitacion Service -> Test guardarHabitacion")
    public void guardarHabitacion(){
        //Given
        Habitacion habitacionCreada = UtilidadesFaker.crearHabitacion();
        Mockito.when(habitacionRepository.save(any())).thenReturn(habitacionCreada);

        //When
        Habitacion habitacionObtenida = habitacionService.guardarhab(habitacionCreada);

        //Then
        assertEquals("La habitacion no se ha guardado",habitacionCreada,habitacionObtenida);

    }

    @Test
    @DisplayName("Habitacion Service -> Test editarHabitacionl")
    public void editarHabitacion(){

        //Given
        Habitacion habitacionCreada = UtilidadesFaker.crearHabitacion();
        Mockito.when(habitacionRepository.save(any())).thenReturn(habitacionCreada);

        habitacionCreada  = UtilidadesFaker.editarHabitacion(habitacionCreada);

        //When
        Habitacion habitacionEditada = habitacionService.actualizarHabitacion(habitacionCreada);

        //Then
        assertNotEquals("La habitacion no se ha editado",habitacionCreada,habitacionEditada);

    }

    @Test
    @DisplayName("Habitacion Service -> Test eliminarHabitacion")
    public void eliminarHabitacion(){

        //Given
        Habitacion habitacionEsperada = UtilidadesFaker.crearHabitacionconId();
        Integer id = habitacionEsperada.getId();
        Mockito.when(habitacionRepository.save(any())).thenReturn(habitacionEsperada);

        //When
        Hotel hotelEliminado = habitacionService.eliminarHabitacion(id);

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