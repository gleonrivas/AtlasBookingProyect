package com.app.atlasultimate.Reserva;


import com.app.atlasultimate.Utilidades.UtilidadesFakerRegistro;
import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.repository.ReservaRepository;
import com.app.atlasultimate.service.ReservaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


@RunWith(MockitoJUnitRunner.Silent.class)

public class PruebaReservaService {

    @Mock
    ReservaRepository reservaRepository;

    @InjectMocks
    ReservaService reservaService;

    @Test
    public void guardarReserva(){
        //PREPARACION
        Registro registroEsperado = UtilidadesFakerRegistro.crearRegistroTest();
        Mockito.when(reservaRepository.save(any())).thenReturn(registroEsperado);

        Registro registroObtenido = new Registro(
                registroEsperado.getF_entrada(),
                registroEsperado.getF_salida(),
                registroEsperado.getN_personas(),
                registroEsperado.getT_pago(),
                registroEsperado.getT_pension(),
                registroEsperado.getPrecio_total_dias(),
                registroEsperado.getN_dias(),
                registroEsperado.getUsuario(),
                registroEsperado.getHabitacion(),
                registroEsperado.getActiva()

                );

        assertEquals("La reserva no se ha guardado",registroEsperado.getCodigo(), registroObtenido.getCodigo());

    }



}
