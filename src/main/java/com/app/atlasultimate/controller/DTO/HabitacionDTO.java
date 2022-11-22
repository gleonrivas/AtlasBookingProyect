package com.app.atlasultimate.controller.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
@NoArgsConstructor
public class HabitacionDTO {


    private Integer id;

    private Integer c_individual;

    private Integer c_doble;

    private Double precio_base;

    private String img;

    private Boolean bano;

    private Boolean vistas;

    private Integer n_max_personas;

    private Integer num_habitaciones_iguales;

    private String nombreHotel;
}
