package com.app.atlasultimate.Utilidades;

import com.app.atlasultimate.model.Rol;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.service.UsuarioService;
import com.github.javafaker.Faker;
import org.hibernate.boot.cfgxml.spi.CfgXmlAccessService;
import org.springframework.beans.factory.annotation.Autowired;

public class UtilidadesFakerUsuario {

    @Autowired
    UsuarioService usuarioService;

    public static Usuario crearUsuario(){

        Faker faker = new Faker();

        return new Usuario(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().digit(),
                Rol.usuario,
                faker.phoneNumber().phoneNumber(),
                faker.internet().emailAddress(),
                faker.hacker().adjective()
        );

    }

}
