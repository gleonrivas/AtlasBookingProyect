package com.app.atlasultimate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RequestMapping("reservas")
public class ReservaController {
    @GetMapping("datos")
    public String registroreserva(Model model){
        return "/reservas.html";
    }
}
