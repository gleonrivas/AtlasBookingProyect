package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Cliente;
import com.app.atlasultimate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Cliente> obtenerUsuario(){
     return usuarioRepository.findAll();
    }

}
