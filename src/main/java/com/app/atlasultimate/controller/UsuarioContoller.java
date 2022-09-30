package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Cliente;
import com.app.atlasultimate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
public class UsuarioContoller {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/usuarios")
    public List<Cliente> obtenerUsuarios(){
        return usuarioService.obtenerUsuario();
    }
}
