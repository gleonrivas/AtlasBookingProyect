package com.app.atlasultimate.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false)Boolean error){
        if(error!=null) model.addAttribute("error", true);
        return "/login.html";
    }


}
