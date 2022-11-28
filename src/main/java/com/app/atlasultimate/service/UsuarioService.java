package com.app.atlasultimate.service;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepositorio;


    public Usuario guardar(Usuario usuarioRegistroDTO) {

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


        return usuarioRepositorio.save(usuario);


    }

    public Usuario guardarNoEncode(Usuario usuarioRegistroDTO) {

        Usuario usuario = new Usuario();

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
            e.printStackTrace();
        }


        return usuarioRepositorio.save(usuario);


    }


    public Usuario editarUsuario(Usuario usuarioDTO){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepositorio.findTopByEmail(auth.getName());

        return usuarioRepositorio.updateByID(usuarioDTO.getNombre(),
                usuarioDTO.getApellido(),
                usuarioDTO.getDni(),
                usuarioDTO.getEmail(),
                usuarioDTO.getTelefono(),
                passwordEncoder.encode(usuarioDTO.getContrasena()),
                usuario.getId());

    }
}
