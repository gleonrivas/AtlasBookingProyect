package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Spin {


    private Integer descuento;
    private String descripcion;
    private Usuario usuario= new Usuario();
    private Boolean spin;
}
