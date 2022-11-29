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
        Integer id = (int)(Math.random()*10+1);
        return new Usuario(
                id,
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
