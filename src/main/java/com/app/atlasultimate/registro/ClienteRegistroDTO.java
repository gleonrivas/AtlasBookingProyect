package com.app.atlasultimate.registro;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ClienteRegistroDTO {

    private Integer id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String contrasena;
    private String telefono;
    private String email;


}
