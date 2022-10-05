package com.app.atlasultimate.registro;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
@RequestMapping("registro")
public class RegistroClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    public RegistroClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @ModelAttribute("cliente")
    public ClienteRegistroDTO devolverNuevoClienteRegistroDTO(){
        return new ClienteRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioRegistro(){
        return "InicioSesion";
    }

    @PostMapping
    public String registrarCuentaCliente(@ModelAttribute("cliente") ClienteRegistroDTO registroDTO){
        clienteServicio.GuardarCliente(registroDTO);
        return "redirect:/registro?exito";
    }


}
