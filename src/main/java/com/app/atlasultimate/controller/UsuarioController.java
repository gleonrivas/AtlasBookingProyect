package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.service.HabitacionServiceImp;
import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

//INYECTA SERVICIO HOTEL:
    @Autowired
    private HotelService servicio;
    @Autowired
    private HabitacionServiceImp servicioHab;

//PAGINA INICIO ROL ADMIN
    @GetMapping("inicio")
    public String inicio(Model modelo){
        modelo.addAttribute("hoteles", servicio.listarHoteles());
        return "/InicioAdministrador.html";
    }

    @GetMapping("servicios")
    public String servs(){
        return "/ofertas_servicios_admin.html";
    }

    @GetMapping("informes")
    public String informes(){
        return "/InformesAdmin.html";
    }

    @GetMapping("facturas")
    public String facturas(){
        return "/FacturasAdmin.html";
    }

    @GetMapping("crearhotel")
    public String crearHotel(){
        return "/crearhotel.html";
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuario(){
            return new UsuarioRegistroDTO();
    }

    //ENVIAR A FORMULARIO REGISTRO
    @GetMapping("registro")
    public String regis(){
        return "/registroUsuario.html";
    }


    //RECIBIR DATOS USUARIO DE FORMULARIO REGISTRO
    @PostMapping("registro")
    public String registrarCuentaUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO usuarioDTO){


        boolean existe = usuarioRepository.existsByEmail(usuarioDTO.getEmail());
        if(existe){
            return "redirect:/usuario/registro?fallo";
        }else {
            usuarioService.guardar(usuarioDTO);
            return "redirect:/usuario/registro?exito";
        }

    }


    //Eliminar hotel
    @GetMapping("/inicio/{id}")
    public String eliminarHotel (@PathVariable Integer id){
        servicio.eliminarHotel(id);
        return "redirect:/usuario/inicio";
    }


    @GetMapping("perfil")
    public String perfil(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        model.addAttribute("usuario", usuario);
        return "/PerfilUsuario.html";
    }


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //RECIBIR DATOS USUARIO DE FORMULARIO REGISTRO
    @PostMapping("perfil")
    public String modificarUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO usuarioDTO){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());

            usuarioRepository.updateByID(usuarioDTO.getNombre(),
                    usuarioDTO.getApellido(),
                    usuarioDTO.getDni(),
                    usuarioDTO.getEmail(),
                    usuarioDTO.getTelefono(),
                    passwordEncoder.encode(usuarioDTO.getContrasena()),
                    usuario.getId());

            return "redirect:/usuario/perfil?exito";
    }

}

