package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.ReviewDTO;
import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        reviewService.guardarReview(reviewDTO);
        return "redirect:/review/comentario?exito";

    }

}
