package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.AdministradorRegistroDTO;
import com.app.atlasultimate.model.Administrador;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.AdministradorService;
import com.app.atlasultimate.service.AdministradorServiceImpl;
import com.app.atlasultimate.service.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdministradorController {
    @Autowired
    private HotelServiceImp servicio;

    @GetMapping("inicio")
    public String inicio( Model modelo){
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


    @Autowired
    private AdministradorServiceImpl adminService;


    @ModelAttribute("administrador")
    public AdministradorRegistroDTO retornarNuevoAdmin (){
            return new AdministradorRegistroDTO();
    }

    @GetMapping("registro")
    public String regis(){
        return "/registro_admin.html";
    }
    @PostMapping("registro")
    public String registrarCuentaAdmin(@ModelAttribute("administrador") AdministradorRegistroDTO adminDTO){
        adminService.save(adminDTO);
        return "redirect:/admin/registro?exito";
    }

    //Eliminar hotel
    @GetMapping("/inicio/{id}")
    public String eliminarHotel (@PathVariable Integer id){
        servicio.eliminarHotel(id);
        return "redirect:/admin/inicio";
    }



    }
