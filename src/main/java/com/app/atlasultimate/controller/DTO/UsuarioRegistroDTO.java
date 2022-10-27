package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Rol;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@EqualsAndHashCode
public class UsuarioRegistroDTO {

    private String nombre;
    private String apellido;
    private String dni;
    @Enumerated(value = EnumType.STRING)
    private Rol rol;
    private String telefono;
    private String email;
    private String contrasena;

    public UsuarioRegistroDTO() {

    }


}
