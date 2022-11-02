package com.app.atlasultimate.controller.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class HotelBusquedaDTO {


    private String fecha_inicio;

    private String fecha_fin;

    private String ciudad;

    private Integer n_max_personas;



    public HotelBusquedaDTO() {

    }

    public HotelBusquedaDTO(String fecha_inicio, String fecha_fin, String ciudad, Integer n_max_personas) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.ciudad = ciudad;
        this.n_max_personas = n_max_personas;
    }


}
