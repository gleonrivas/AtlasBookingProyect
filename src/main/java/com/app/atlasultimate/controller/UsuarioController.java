package com.app.atlasultimate.controller;
import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.service.HotelServiceImp;
import com.app.atlasultimate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private HotelServiceImp servicio;

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

}
