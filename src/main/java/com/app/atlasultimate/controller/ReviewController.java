package com.app.atlasultimate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("review")
public class ReviewController {
    @GetMapping("create")
    public String create(){
        return "/ComentariosAdmin.html";
    }

}
