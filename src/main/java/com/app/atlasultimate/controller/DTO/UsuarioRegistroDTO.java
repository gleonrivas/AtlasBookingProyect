package com.app.atlasultimate.controller.DTO;

import com.app.atlasultimate.model.Rol;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class UsuarioRegistroDTO {

    private String nombre;
    private String apellido;
    private String dni;
    private Rol rol;
    private String telefono;
    private String email;
    private String contrasena;

    public UsuarioRegistroDTO() {

    }


}
