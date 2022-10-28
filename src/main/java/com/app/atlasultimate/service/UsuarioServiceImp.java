package com.app.atlasultimate.service;
import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioServiceImp implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findTopByEmail(username);
        GrantedAuthority rol = new SimpleGrantedAuthority(usuario.getRol().toString());
        UserDetails userDetails = new User(usuario.getEmail(), usuario.getContrasena(), Collections.singletonList(rol));
        return userDetails;
    }





}
