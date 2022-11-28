package com.app.atlasultimate.Habitaciones;


import com.app.atlasultimate.Utilidades.UtilidadesFakerHabitacion;
import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.repository.HabitacionRepository;
import com.app.atlasultimate.service.HabitacionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class PruebaHabitacionService {

    @Mock
    HabitacionRepository habitacionRepository;

    @InjectMocks
    HabitacionService habitacionService;

    /*@Test
    public void guardarHabitacion(){
        //PREPARACIÓN
        Habitacion habitacion = UtilidadesFakerHabitacion.crearHabitacion();
        Mockito.when(habitacionRepository.save(any())).thenReturn(habitacion);

        //EJECUCIÓN
        Habitacion habitacion1 = habitacionService.guardarhab(habitacion);

        //COMPROBACIÓN
        assertEquals(habitacion, habitacion1);
    }*/
}
