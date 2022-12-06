package com.app.atlasultimate.Utilidades;

import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.model.tipo_pago;
import com.app.atlasultimate.model.tipo_pension;

public class UtilidadesFakerRegistro {

    public static Registro crearRegistroTest(){

        return new Registro(
                "2023-01-01",
                "2023-01-10",
                1,
                tipo_pago.tarjeta,
                tipo_pension.ad,
                10.0,
                1,
                UtilidadesFakerUsuario.crearUsuari0(),
                UtilidadesFakerHabitacion.crearHabitacion(),
                true
        );

    }

}
