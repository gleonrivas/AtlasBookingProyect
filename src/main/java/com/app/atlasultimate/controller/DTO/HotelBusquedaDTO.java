package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Administrador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class HotelBusquedaDTO {


    private Timestamp fecha_inicio;

    private Timestamp fecha_fin;

    private String ciudad;

    private String tipo_cama;



    public HotelBusquedaDTO() {

    }

}
