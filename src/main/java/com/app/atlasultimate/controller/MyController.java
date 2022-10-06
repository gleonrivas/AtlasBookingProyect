package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Administrador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller("")
public class MyController {
    @GetMapping("")
    public String index() {
        return "/index.html";
    }




}

