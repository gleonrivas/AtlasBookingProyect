package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.AdministradorRegistroDTO;
import com.app.atlasultimate.model.Administrador;
import com.app.atlasultimate.service.AdministradorService;
import com.app.atlasultimate.service.AdministradorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdministradorController {

    @GetMapping("inicio")
    public String inicio(){
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



    }
