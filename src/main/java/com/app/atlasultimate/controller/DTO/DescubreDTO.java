package com.app.atlasultimate.controller.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescubreDTO {


    private String fecha_inicio;

    private String fecha_fin;

    private Integer n_max_personas;



    public DescubreDTO() {

    }

    public DescubreDTO(String fecha_inicio, String fecha_fin, Integer n_max_personas) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.n_max_personas = n_max_personas;
    }


}
