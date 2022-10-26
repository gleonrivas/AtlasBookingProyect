package com.app.atlasultimate.service;

import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.model.Rol;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioServiceImp implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findTopByName(username);
        GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getRol().toString());
        UserDetails userDetails = new User(usuario.getNombre(), usuario.getContrasena(), Collections.singletonList(rol));
        return userDetails;
    }




    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }*/
}
