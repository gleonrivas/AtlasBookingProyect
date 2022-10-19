package com.app.atlasultimate.controller.DTO;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class UsuarioRegistroDTO {

    private String nombre;
    private String apellido;
    private String dni;
    private String rol;
    private String telefono;
    private String email;
    private String contrasena;

    public UsuarioRegistroDTO() {

    }


}
