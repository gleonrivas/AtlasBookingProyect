package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.UsuarioRegistroDTO;
import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.service.HabitacionServiceImp;
import com.app.atlasultimate.service.HotelServiceImp;
import com.app.atlasultimate.service.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

//INYECTA SERVICIO HOTEL:
    @Autowired
    private HotelServiceImp servicio;
    @Autowired
    private HabitacionServiceImp servicioHab;

//PAGINA INICIO ROL ADMIN
    @GetMapping("inicio")
    public String inicio(Model modelo){
        List<Hotel> hoteles = servicio.listarHoteles();
        modelo.addAttribute("hoteles", hoteles);

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
    private UsuarioServiceImp usuarioService;

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
        usuarioService.guardar(usuarioDTO);

        return "redirect:/usuario/registro?exito";
    }


    //Eliminar hotel
    @GetMapping("/inicio/{id}")
    public String eliminarHotel (@PathVariable Integer id){
        servicio.eliminarHotel(id);
        return "redirect:/usuario/inicio";
    }

}
