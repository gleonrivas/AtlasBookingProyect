package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.ReviewDTO;
import com.app.atlasultimate.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("review")
public class ReviewController {

    @GetMapping("create")
    public String create(){
        return "/ComentariosAdmin.html";
    }

    @Autowired
    private ReviewService reviewService;

    @ModelAttribute("review")
    public ReviewDTO reviewRegistroDTO(){
        return new ReviewDTO();

    }

    @GetMapping("/comentario")
    public String mostrarComentario(){
        return "/comentario.html";
    }

    @PostMapping("/comentario")
    public String guardarReview(@ModelAttribute("review") ReviewDTO reviewDTO) {
        reviewService.guardar(reviewDTO);
        return "redirect:/review/comentario?exito";

    }

}
