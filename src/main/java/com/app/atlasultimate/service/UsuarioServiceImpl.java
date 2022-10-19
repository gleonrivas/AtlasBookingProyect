package com.app.atlasultimate.service;

import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl {

    @Autowired
    private UsuarioRepository usuarioRepositorio;


    public Usuario guardar(UsuarioRegistroDTO usuarioRegistroDTO) {

        Usuario usuario = null;

        try {
            usuario = new Usuario(
                    usuarioRegistroDTO.getNombre(),
                    usuarioRegistroDTO.getApellido(),
                    usuarioRegistroDTO.getDni(),
                    usuarioRegistroDTO.getRol(),
                    usuarioRegistroDTO.getTelefono(),
                    usuarioRegistroDTO.getEmail(),
                    usuarioRegistroDTO.getContrasena()
            );

        } catch (Exception e) {
            System.out.println("Error al registrar usuario" + e);
        }

        return usuarioRepositorio.save(usuario);

    }
    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }*/
}
