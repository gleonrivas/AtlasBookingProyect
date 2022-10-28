package com.app.atlasultimate.service;

import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private UsuarioRepository usuarioRepositorio;


    public void guardar(UsuarioRegistroDTO usuarioRegistroDTO) {

        Usuario usuario = new Usuario();

        try {
            usuario = new Usuario(
                    usuarioRegistroDTO.getNombre(),
                    usuarioRegistroDTO.getApellido(),
                    usuarioRegistroDTO.getDni(),
                    usuarioRegistroDTO.getRol(),
                    usuarioRegistroDTO.getTelefono(),
                    usuarioRegistroDTO.getEmail(),
                    passwordEncoder.encode(usuarioRegistroDTO.getContrasena())
            );

        } catch (Exception e) {
            e.printStackTrace();
        }


        usuarioRepositorio.save(usuario);

    }
}
