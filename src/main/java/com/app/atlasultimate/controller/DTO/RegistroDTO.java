package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.model.tipo_pago;
import com.app.atlasultimate.model.tipo_pension;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {


    private String f_entrada;
    private String f_salida;
    private Integer n_personas;
    @Enumerated(value = EnumType.STRING)
    private tipo_pago t_pago;
    @Enumerated(value = EnumType.STRING)
    private tipo_pension t_pension;
    private Double precio_total_dias;
    private Integer n_dias;
    private Usuario usuario;
    private Habitacion habitacion;
    private Integer descuento;
}
